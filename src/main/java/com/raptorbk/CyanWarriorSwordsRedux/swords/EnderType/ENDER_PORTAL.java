package com.raptorbk.CyanWarriorSwordsRedux.swords.EnderType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import com.sun.jna.Structure;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ConfiguredStructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.phys.HitResult;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ENDER_PORTAL extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;

        @Override
        public int getUses() {
            return SwordConfig.ENDER_PORTAL_SWORD_DUR.get();
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

    public ENDER_PORTAL() {
        super(iItemTier, SwordConfig.ENDER_PORTAL_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk) {
        seffect.execute(world, entity, handIn, blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.ENDER_PORTAL_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cwsr.ender_portal"));
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        

        ItemStack itemstack = new ItemStack(Items.ENDER_EYE);


        HitResult HitResult = getPlayerPOVHitResult(world, entity, ClipContext.Fluid.NONE);

            entity.startUsingItem(handIn);
            if (world instanceof ServerLevel) {
                BlockPos blockpos = ((ServerLevel) world).findNearestMapFeature(ConfiguredStructureTags.EYE_OF_ENDER_LOCATED, entity.blockPosition(), 100, false);
                if (blockpos != null) {
                    EyeOfEnder eyeofenderentity = new EyeOfEnder(world, entity.getX(), entity.getY(0.5D), entity.getZ());
                    eyeofenderentity.setItem(itemstack);
                    eyeofenderentity.signalTo(blockpos);
                    world.addFreshEntity(eyeofenderentity);
                    if (entity instanceof ServerPlayer) {
                        CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayer)entity, blockpos);
                    }

                    world.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 0.5F, 0.4F / (Mth.nextFloat(new Random(),0.0F,1.0F) * 0.4F + 0.8F));
                    world.levelEvent((Player)null, 1003, entity.blockPosition(), 0);
                    if (!entity.getAbilities().instabuild) {
                        itemstack.shrink(0);
                    }

                    entity.awardStat(Stats.ITEM_USED.get(this));
                    entity.swing(handIn, true);
                    itemstack=ogSword;
                    return InteractionResultHolder.success(itemstack);
                }
            }
            itemstack=ogSword;


            Random r = new Random();
            int game = r.nextInt(100);

            if(game < 85){
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
            }else{
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,160,1));
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
            }
        }


    @Override
    public void unlockSEACH(Player entity, Level world) {
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayer);
        ModTrigger.Themoretrigger.trigger(serverPlayer);
    }


    @Override
    public void setCD() {
        this.swordCD=SwordConfig.ENDER_PORTAL_SWORD_COOLDOWN.get();
    }


    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
ogSword.hurtAndBreak(SwordConfig.ENDER_PORTAL_SWORD_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

            return callerRC(world,entity,handIn, RegistryHandler.ender_PORTAL.getId(),SwordConfig.ENDER_PORTAL_SWORD_COOLDOWN.get());
    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        Level worldIn = attacker.getCommandSenderWorld();
        Random pushRNG = new Random();
        int gameRNG = pushRNG.nextInt(100);
        if(gameRNG < 25){
            worldIn.playSound((Player) null, target.getX(), target.getY(), target.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1.0F, 0.4F);
            target.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            attacker.level.broadcastEntityEvent(target, (byte)46);
            target.setPos(target.getX(),target.getY()+3, target.getZ());
            target.knockback(1,attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

        }else{
            ItemStack itemstack = new ItemStack(Items.CHORUS_FRUIT);
            ChorusFruitItem X = new ChorusFruitItem(new Properties());
            X.finishUsingItem(itemstack,worldIn,target);
            itemstack=stack;
        }

        Random r = new Random();
        int game = r.nextInt(100);
        if(game < 85){
            return true;
        }else{
            target.addEffect(new MobEffectInstance(MobEffects.CONFUSION,SwordConfig.ENDER_PORTAL_SWORD_HIT_TK.get(),1));
            return true;
        }

    }


}
