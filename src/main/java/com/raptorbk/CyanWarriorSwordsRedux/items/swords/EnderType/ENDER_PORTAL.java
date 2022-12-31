package com.raptorbk.CyanWarriorSwordsRedux.items.swords.EnderType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemChorusFruit;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class ENDER_PORTAL extends SWORD_CWSR {
    public ENDER_PORTAL(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack ogSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            ogSword.damageItem(CwsrConfig.ENDER_PORTAL_SWORD_USE_COST,entity);
        }

        return callerRC(world,entity,handIn, ModItems.ender_PORTAL.getRegistryName(),CwsrConfig.ENDER_PORTAL_SWORD_COOLDOWN);
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Themoretrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.ENDER_PORTAL_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.ENDER_PORTAL_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);


        World worldIn = attacker.getEntityWorld();

        Random pushRNG = new Random();
        int gameRNG = pushRNG.nextInt(100);
        if(gameRNG < 25){
            worldIn.playSound((EntityPlayer) null, target.posX, target.posY, target.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 0.4F);
            target.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            attacker.world.setEntityState(target, (byte)46);
            target.setPosition(target.posX,target.posY+3, target.posZ);
            target.knockBack(target,1,attacker.posX - target.posX, attacker.posZ - target.posZ);

        }else{
            ItemStack itemstack = new ItemStack(Items.CHORUS_FRUIT);
            ItemChorusFruit X = new ItemChorusFruit(1,1);
            X.onItemUseFinish(itemstack,worldIn,target);
            itemstack=stack;
        }

        Random r = new Random();
        int game = r.nextInt(100);
        if(game < 85){
            return true;
        }else{
            target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,CwsrConfig.ENDER_PORTAL_SWORD_HIT_TK,1));
            return true;
        }
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack ogSword = entity.getHeldItem(handIn);



        ItemStack itemstack = new ItemStack(Items.ENDER_EYE);
        //RayTraceResult raytraceresult = rayTrace(world, entity, RayTraceContext.FluidMode.NONE);
        entity.setHeldItem(handIn,itemstack);
        itemstack.useItemRightClick(world,entity,handIn);
        entity.setHeldItem(handIn,ogSword);

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 85){
            return new ActionResult<>(EnumActionResult.SUCCESS, ogSword);
        }else{
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,160,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, ogSword);
        }
    }
}
