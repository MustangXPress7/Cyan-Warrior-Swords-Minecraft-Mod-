package com.raptorbk.CyanWarriorSwordsRedux.swords.CyanType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.client.renderer.EffectInstance;
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


public class CYAN_SWORD extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.CYAN_SWORD_DUR.get();
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

    public CYAN_SWORD() {
        super(iItemTier, SwordConfig.CYAN_SWORD_DMG.get(), 0.0F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    public void addEffectsTick(Player playerIn){
        playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION,10,4));
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        return callerRC(world,entity,handIn, RegistryHandler.cyan_SWORD.getId(),0);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    public void unlockACH(Player entity, Level world){
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Reallyradtrigger.trigger(serverPlayer);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;
                    unlockACH(playerIn,worldIn);
            }
        }else{
            if(entityIn instanceof Player) {
                Player playerIn = (Player) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(OffHandItem.getItem() instanceof  CYAN_SWORD){
                        unlockACH(playerIn,worldIn);
                }
            }
        }
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cwsr.cyan_sword"));
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
    }
}
