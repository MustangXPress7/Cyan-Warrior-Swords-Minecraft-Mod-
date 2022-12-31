package com.raptorbk.CyanWarriorSwordsRedux.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.METEOR_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;

import net.minecraft.util.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class METEOR_SWORD extends METEOR_CLASS_SWORD {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.METEOR_SWORD_DUR.get();
        }

        @Override
        public float getSpeed() {
            return 10.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 4.0F;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(this.repairItem);
        }
    };

    public METEOR_SWORD() {
        super(iItemTier, SwordConfig.METEOR_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public int fireballStrength = 2;


    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.METEOR_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cwsr.meteor_sword"));
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);

        Vec3 vec3 = entity.getViewVector(1.0F);
        LargeFireball LargeFireball = new LargeFireball(world, entity,vec3.x,vec3.y,vec3.z,fireballStrength);
        LargeFireball.setXRot(entity.getXRot());
        LargeFireball.setYRot(entity.getYRot());
        LargeFireball.setPos(entity.getX(),entity.getY()+2,entity.getZ());
        LargeFireball.xPower=vec3.x;
        LargeFireball.yPower=vec3.y;
        LargeFireball.zPower=vec3.z;

        ItemStack x = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);
        if(entity.getInventory().contains(x)){
            if(entity.getOffhandItem().getItem() instanceof METEOR_CLASS_SWORD && entity.getMainHandItem().getItem() instanceof METEOR_CLASS_SWORD){
                if(entity.getOffhandItem().getItem() instanceof METEOR_SWORD){
                    this.setDelayMeteor(true);
                    this.setDelayedMeteor(LargeFireball);
                }else{
                    world.addFreshEntity(LargeFireball);
                }

            }else{
                world.addFreshEntity(LargeFireball);
            }
        }else{
            world.addFreshEntity(LargeFireball);
        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.METEOR_SWORD_COOLDOWN.get();
    }


    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.METEOR_SWORD_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.meteor_SWORD.getId(),SwordConfig.METEOR_SWORD_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.setSecondsOnFire(SwordConfig.METEOR_SWORD_HIT_SEC.get());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player entity) {
        world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL, 0.5F, 0.4F / (Mth.nextFloat(new Random(),0.0F,1.0F) * 0.4F + 0.8F));
        //world.explode(entity,entity.getX(),entity.getY(),entity.getZ(),1.0F, Explosion.BlockInteraction.NONE);
    }

    public void addEffectsTick(Player playerIn){
        playerIn.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,10,1));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(this.getDelayMeteor() && entityIn instanceof Player && (((Player) entityIn).getMainHandItem()==stack || ((Player) entityIn).getOffhandItem()==stack)){
            if(this.getCount() >= 5){
                this.setCount(0);
                this.setDelayMeteor(false);
                worldIn.addFreshEntity(this.getDelayedMeteor());
                this.setDelayedMeteor(null);
            }else{
                this.setCount(this.getCount()+1);
            }
        }


        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;
                addEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.meteor_SWORD.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
}
