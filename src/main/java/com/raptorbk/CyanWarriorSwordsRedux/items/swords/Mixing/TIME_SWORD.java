package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;

public class TIME_SWORD extends SWORD_CWSR {
    public TIME_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.TIME_SWORD_USE_COST,entity);
        }


        return callerRC(world,entity,handIn,ModItems.time_SWORD.getRegistryName(),CwsrConfig.TIME_SWORD_COOLDOWN);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);

        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        WorldServer worldSV = (WorldServer) world;

        if (world.isDaytime()){
            worldSV.setWorldTime(13000);
        }
        else worldSV.setWorldTime(0);



        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        if(target.isPotionActive(MobEffects.INVISIBILITY)){
            target.removeActivePotionEffect(MobEffects.INVISIBILITY);
        }
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,CwsrConfig.TIME_SWORD_WEAKNESS_HIT_TK,4));
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,CwsrConfig.TIME_SWORD_BLINDNESS_HIT_TK,4));
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER,CwsrConfig.TIME_SWORD_WITHER_HIT_TK,4));
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Bestbothtrigger.trigger(serverEntityPlayer);
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.GLOWING,30,0));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY,40,1));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;
                addMobEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.time_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }
}
