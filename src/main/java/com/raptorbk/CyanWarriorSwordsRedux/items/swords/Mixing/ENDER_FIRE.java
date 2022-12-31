package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.SwordClasses.ENDER_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import com.raptorbk.CyanWarriorSwordsRedux.util.ExecuteSeffect;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;
import java.util.Random;

public class ENDER_FIRE extends ENDER_CLASS_SWORD {
    public ENDER_FIRE(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack ogSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            ogSword.damageItem(CwsrConfig.ENDER_FIRE_USE_COST,entity);
        }

        return callerRC(world,entity,handIn, ModItems.ender_FIRE.getRegistryName(),CwsrConfig.ENDER_FIRE_COOLDOWN);
    }

    public static void callEffect(SurroundEffect seffect, World world, EntityPlayer entity, EnumHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }


    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        callEffect(new ExecuteSeffect(), world,entity,handIn, Blocks.FIRE);
        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,120,3));

        ItemStack ogSword = entity.getHeldItem(handIn);


        ItemStack itemstack = new ItemStack(Items.ENDER_PEARL);
        entity.setHeldItem(handIn,itemstack);
        itemstack.useItemRightClick(world,entity,handIn);
        entity.setHeldItem(handIn,ogSword);

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,120,3));

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 95){
            return new ActionResult<>(EnumActionResult.SUCCESS, ogSword);
        }else{
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,160,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, ogSword);
        }

    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Bestbothtrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.ENDER_FIRE_COOLDOWN;
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.ENDER_FIRE_USE_COST;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        target.setFire(CwsrConfig.ENDER_FIRE_HIT_SEC);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,10,0));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        this.throwEnderPearlEvent(entityIn,worldIn, stack);
        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;
                addMobEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.ender_FIRE.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        Random x=new Random();
        unlockSEACH(entity,world);
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (x.nextFloat() * 0.4F + 0.8F));
        stack.addEnchantment(Enchantments.FIRE_ASPECT,2);
        world.createExplosion(entity,entity.posX,entity.posY,entity.posZ,0.1F, false);
    }
}
