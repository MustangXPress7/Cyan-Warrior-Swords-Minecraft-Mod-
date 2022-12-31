package com.raptorbk.CyanWarriorSwordsRedux.items.swords.DarkType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class DARK_NETHER extends SWORD_CWSR {
    public DARK_NETHER(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!(worldIn instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        ItemStack currentSword = playerIn.getHeldItem(handIn);

        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);


        if(!lfAbilityTotem(playerIn) && ((playerIn.getHeldItemMainhand() != playerIn.getHeldItem(handIn) && playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && playerIn.inventory.hasItemStack(ActiveSynergyTotemStack)) || playerIn.getHeldItemMainhand() == playerIn.getHeldItem(handIn) || (playerIn.getHeldItemOffhand()==playerIn.getHeldItem(handIn) && !(playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.DARK_NETHER_SWORD_USE_COST,playerIn);

        }
        return callerRC(worldIn,playerIn,handIn, ModItems.dark_NETHER.getRegistryName(),CwsrConfig.DARK_NETHER_SWORD_COOLDOWN);
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Themoretrigger.trigger(serverEntityPlayer);
    }


    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getHeldItem(handIn);


        Random rx=new Random();
        //Creo que esto no genera efecto de wither en el enemigo
        entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,20,1));
        Vec3d vec3 = entity.getLook(1.0F);
        EntityWitherSkull witherEntity = new EntityWitherSkull(world, entity,vec3.x,vec3.y,vec3.z);
        witherEntity.rotationPitch = entity.rotationPitch;
        witherEntity.rotationYaw = entity.rotationYaw;
        witherEntity.setPosition(entity.posX,entity.posY+2,entity.posZ);
        witherEntity.accelerationX=vec3.x;
        witherEntity.accelerationY=vec3.y;
        witherEntity.accelerationZ=vec3.z;
        world.spawnEntity(witherEntity);
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (rx.nextFloat() * 0.4F + 0.8F));

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 70){
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addPotionEffect(new PotionEffect(MobEffects.WITHER,80,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,80,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else{
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,80,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
    }


    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.DARK_NETHER_SWORD_USE_COST;
    }

    @Override
    public void setCD(){
        this.swordCD=CwsrConfig.DARK_NETHER_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER,CwsrConfig.DARK_NETHER_SWORD_HIT_TK,0));
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }
}
