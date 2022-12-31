package com.raptorbk.CyanWarriorSwordsRedux.items.swords.LightType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
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

public class LIGHT_NETHER extends SWORD_CWSR {
    public LIGHT_NETHER(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.LIGHT_NETHER_SWORD_USE_COST, entity);

        }

        return callerRC(world,entity,handIn,ModItems.light_NETHER.getRegistryName(),CwsrConfig.LIGHT_NETHER_SWORD_COOLDOWN);
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


        Random xRNG=new Random();
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 0.4F / (xRNG.nextFloat() * 0.4F + 0.8F));

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,60,2));
        entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,600,4));
        entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,800,0));
        entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,800,2));

        if(entity.isPotionActive(MobEffects.WITHER)){
            entity.removePotionEffect(MobEffects.WITHER);
        }
        if(entity.isPotionActive(MobEffects.BLINDNESS)){
            entity.removePotionEffect(MobEffects.BLINDNESS);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.LIGHT_NETHER_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.LIGHT_NETHER_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        if(target.isPotionActive(MobEffects.INVISIBILITY)){
            target.removeActivePotionEffect(MobEffects.INVISIBILITY);
        }
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, CwsrConfig.LIGHT_NETHER_SWORD_BLINDNESS_HIT_TK,4));
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,CwsrConfig.LIGHT_NETHER_SWORD_WEAKNESS_HIT_TK,4));
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,10,4));
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.light_NETHER.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }
}
