package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.SwordClasses.ENDER_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;
import java.util.Random;

public class ENDER_THUNDER extends ENDER_CLASS_SWORD {
    public ENDER_THUNDER(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack ogSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            ogSword.damageItem(CwsrConfig.ENDER_THUNDER_USE_COST,entity);
        }

        return callerRC(world,entity,handIn,ModItems.ender_THUNDER.getRegistryName(), CwsrConfig.ENDER_THUNDER_COOLDOWN);
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

        ItemStack ogSword = entity.getHeldItem(handIn);


        WorldServer worldSV = (WorldServer) world;
        EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX+5, entity.posY, entity.posZ-1, false);
        EntityLightningBolt entityBolt2 = new EntityLightningBolt(worldSV, entity.posX+5, entity.posY, entity.posZ-1, false);
        EntityLightningBolt entityBolt3 = new EntityLightningBolt(worldSV, entity.posX-5, entity.posY, entity.posZ+1, false);
        EntityLightningBolt entityBolt4 = new EntityLightningBolt(worldSV, entity.posX-5, entity.posY, entity.posZ-3, false);

        worldSV.addWeatherEffect(entityBolt);
        worldSV.addWeatherEffect(entityBolt2);
        worldSV.addWeatherEffect(entityBolt3);
        worldSV.addWeatherEffect(entityBolt4);


        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,120,3));


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
    public void setDamagePU() {
        this.damagePU=CwsrConfig.ENDER_THUNDER_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.ENDER_THUNDER_COOLDOWN;
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.ender_THUNDER.getRegistryName())){
                    addMobEffectsTick(playerIn);
                    this.throwEnderPearlEvent(entityIn,worldIn, stack);
                }
            }
        }
    }
}
