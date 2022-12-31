package com.raptorbk.CyanWarriorSwordsRedux.items.swords.ThunderType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
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

public class THUNDER_SWORD extends SWORD_CWSR {
    public THUNDER_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.THUNDER_SWORD_USE_COST,entity);
        }

        return callerRC(world,entity,handIn,ModItems.thunder_SWORD.getRegistryName(),CwsrConfig.THUNDER_SWORD_COOLDOWN);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,180,0));

        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);



        WorldServer worldSV = (WorldServer) world;
        EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX, entity.posY, entity.posZ-2, false);
        EntityLightningBolt entityBolt2 = new EntityLightningBolt(worldSV, entity.posX, entity.posY, entity.posZ+2, false);
        EntityLightningBolt entityBolt3 = new EntityLightningBolt(worldSV, entity.posX+2, entity.posY, entity.posZ, false);
        EntityLightningBolt entityBolt4 = new EntityLightningBolt(worldSV, entity.posX-2, entity.posY, entity.posZ, false);


        entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,20,4));
        worldSV.addWeatherEffect(entityBolt);
        worldSV.addWeatherEffect(entityBolt2);
        worldSV.addWeatherEffect(entityBolt3);
        worldSV.addWeatherEffect(entityBolt4);


        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.THUNDER_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.THUNDER_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED,10,2));
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.thunder_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        if(!(world instanceof WorldServer)) return;
        Random x = new Random();
        unlockSEACH(entity,world);
        WorldServer worldSV = (WorldServer) world;
        EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX, entity.posY+5, entity.posZ, false);
        worldSV.addWeatherEffect(entityBolt);
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.NEUTRAL, 0.5F, 0.4F / (x.nextFloat() * 0.4F + 0.8F));
    }
}
