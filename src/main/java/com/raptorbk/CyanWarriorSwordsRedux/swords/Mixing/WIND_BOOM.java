package com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.List;


public class WIND_BOOM extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.WIND_BOOM_DUR.get();
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

    public WIND_BOOM() {
        super(iItemTier, SwordConfig.WIND_BOOM_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.WIND_BOOM_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cwsr.wind_boom"));
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);


        entity.push(0,1,0);
        entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,100,1));
        entity.fallDistance = 0.0F;

        world.explode(entity,entity.getX(),entity.getY(),entity.getZ(),4.0F, Explosion.BlockInteraction.NONE);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
    }


    @Override
    public void setCD() {
        this.swordCD=SwordConfig.WIND_BOOM_COOLDOWN.get();
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.WIND_BOOM_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }


        return callerRC(world,entity,handIn,RegistryHandler.wind_BOOM.getId(),SwordConfig.WIND_BOOM_COOLDOWN.get());
    }

    @Override
    public void unlockSEACH(Player entity, Level world) {
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayer);
        ModTrigger.Bestbothtrigger.trigger(serverPlayer);
    }


    public void addEffectsTick(Player playerIn){
        playerIn.addEffect(new MobEffectInstance(MobEffects.JUMP,10,2));
    }

    public void unlockACH(Player entity, Level world){
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Ablasttrigger.trigger(serverPlayer);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;
                addEffectsTick(playerIn);
                unlockACH(playerIn,worldIn);
            }
        }else{
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(OffHandItem.getItem() instanceof WIND_BOOM){
                    addEffectsTick(playerIn);
                    unlockACH(playerIn,worldIn);
                }
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.knockback(2,attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);        });
        return true;
    }
}
