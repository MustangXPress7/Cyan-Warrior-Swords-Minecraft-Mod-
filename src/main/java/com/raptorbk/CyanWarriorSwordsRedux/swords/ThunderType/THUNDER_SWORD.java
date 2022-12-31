package com.raptorbk.CyanWarriorSwordsRedux.swords.ThunderType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import net.minecraft.util.*;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class THUNDER_SWORD extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.THUNDER_SWORD_DUR.get();
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

    public THUNDER_SWORD() {
        super(iItemTier, SwordConfig.THUNDER_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cwsr.thunder_sword"));
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.THUNDER_SWORD_USE_COST.get();
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,180,0));

        if(!(world instanceof ServerLevel)) return new InteractionResultHolder<>(InteractionResult.PASS, entity.getItemInHand(handIn));

        ItemStack currentSword = entity.getItemInHand(handIn);



        ServerLevel worldSV = (ServerLevel) world;

        LightningBolt entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY(), entity.getZ()-2);



        LightningBolt entityBolt2 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt2.moveTo(entity.getX(), entity.getY(), entity.getZ()+2);


        LightningBolt entityBolt3 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt3.moveTo(entity.getX()+2, entity.getY(), entity.getZ());


        LightningBolt entityBolt4 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt4.moveTo(entity.getX()-2, entity.getY(), entity.getZ());

        worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt2);
        worldSV.addFreshEntity(entityBolt3);
        worldSV.addFreshEntity(entityBolt4);

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
    }



    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.THUNDER_SWORD_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.thunder_SWORD.getId(),SwordConfig.THUNDER_SWORD_COOLDOWN.get());
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player entity) {
        if(!(world instanceof ServerLevel)) return;
        unlockSEACH(entity,world);
        ServerLevel worldSV = (ServerLevel) world;
        LightningBolt entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY()+5, entity.getZ());
        worldSV.addFreshEntity(entityBolt);
        world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.NEUTRAL, 0.5F, 0.4F / (Mth.nextFloat(new Random(),0.0F,1.0F) * 0.4F + 0.8F));
    }

    public void addEffectsTick(Player playerIn){
        playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,10,2));
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.THUNDER_SWORD_COOLDOWN.get();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;
                addEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.thunder_SWORD.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);        });
        return true;
    }
}
