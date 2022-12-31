package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;

public class THUNDERSTORM_SWORD extends SWORD_CWSR {
    public THUNDERSTORM_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.THUNDERSTORM_SWORD_USE_COST,entity);
        }

        return callerRC(world,entity,handIn,ModItems.thunderstorm_SWORD.getRegistryName(),CwsrConfig.THUNDERSTORM_SWORD_COOLDOWN);
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Bestbothtrigger.trigger(serverEntityPlayer);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));
        ItemStack currentSword = entity.getHeldItem(handIn);



        WorldServer worldSV = (WorldServer) world;

        float var4 = 1.0F;
        int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - entity.getYOffset());

        EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX, entity.posY, entity.posZ, false);
        //Para tocar los valores de impulso, serían los últimos valores de "double motion" (en este caso, 10.5F)
        entity.fallDistance=0.0F;
        double motionX = (double)(-MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 7F);
        double motionZ = (double)(MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 7F);
        double motionY = (double)(-MathHelper.sin((entity.rotationPitch + 0F) / 180.0F * (float)Math.PI) * 0F);
        //entity.addVelocity(((double)(-MathHelper.sin(entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F, 0.1D, ((double)(MathHelper.cos(entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F);
        entity.addVelocity(motionX,motionY,motionZ);
        entity.velocityChanged=true;


        entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,180,0));
        entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,5));
        worldSV.addWeatherEffect(entityBolt);

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,80,3));
        entity.fallDistance=0.0F;


        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.knockBack(target,2,attacker.posX - target.posX, attacker.posZ - target.posZ);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED,10,4));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,10,4));

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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.thunderstorm_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        if(!world.isRemote)
        {
            unlockSEACH(entity,world);
            WorldServer worldSV = (WorldServer) world;
            EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX, entity.posY+5, entity.posZ, false);
            worldSV.addWeatherEffect(entityBolt);
        }
    }
}
