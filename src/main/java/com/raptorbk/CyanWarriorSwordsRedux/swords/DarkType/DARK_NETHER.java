package com.raptorbk.CyanWarriorSwordsRedux.swords.DarkType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import net.minecraft.util.*;
import net.minecraft.world.phys.Vec3;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class DARK_NETHER extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.DARK_NETHER_SWORD_DUR.get();
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

    public DARK_NETHER() {
        super(iItemTier, SwordConfig.DARK_NETHER_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.DARK_NETHER_SWORD_USE_COST.get();
    }

    @Override
    public void setCD(){
        this.swordCD=SwordConfig.DARK_NETHER_SWORD_COOLDOWN.get();
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }


    @Override
    public void unlockSEACH(Player entity, Level world) {
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayer);
        ModTrigger.Themoretrigger.trigger(serverPlayer);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.cwsr.dark_nether"));
    }


    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getItemInHand(handIn);





        //Creo que esto no genera efecto de wither en el enemigo
        entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20,1));
        Vec3 vec3 = entity.getViewVector(1.0F);
        WitherSkull witherEntity = new WitherSkull(world, entity,vec3.x,vec3.y,vec3.z);
        witherEntity.setXRot(entity.getXRot());
        witherEntity.setYRot(entity.getYRot());
        witherEntity.setPos(entity.getX(),entity.getY()+2,entity.getZ());
        witherEntity.xPower=vec3.x;
        witherEntity.yPower=vec3.y;
        witherEntity.zPower=vec3.z;
        world.addFreshEntity(witherEntity);
        world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WITHER_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (Mth.nextFloat(world.random,0.0F,1.0F) * 0.4F + 0.8F));

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 70){
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER,80,1));
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,80,1));
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
        }
        else{
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,80,1));
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.addEffect(new MobEffectInstance(MobEffects.WITHER,SwordConfig.DARK_NETHER_SWORD_HIT_TK.get(),0));
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }


    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);

        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);




        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
                currentSword.hurtAndBreak(SwordConfig.DARK_NETHER_SWORD_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return callerRC(world,entity,handIn, RegistryHandler.dark_NETHER.getId(),SwordConfig.DARK_NETHER_SWORD_COOLDOWN.get());
    }
}
