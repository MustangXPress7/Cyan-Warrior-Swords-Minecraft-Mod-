package com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class PEACEFUL_NATURE extends SWORD_CWSR {
    private static Tier iItemTier = new Tier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.PEACEFUL_NATURE_DUR.get();
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

    public PEACEFUL_NATURE() {
        super(iItemTier, SwordConfig.PEACEFUL_NATURE_DMG.get(), -2.4F, new Item.Properties());
    }

    public static void callEffect(SurroundEffect seffect, Level world, Player entity, InteractionHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.PEACEFUL_NATURE_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.cwsr.peaceful_nature"));
    }

    @Override
    public InteractionResultHolder<ItemStack> eventRC(Level world, Player entity, InteractionHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);


        int radius=8;
        int entCountValid=0;
        AABB bb = new AABB(entity.getX()-radius, entity.getY()-radius, entity.getZ()-radius, entity.getX()+radius, entity.getY()+radius, entity.getZ()+radius);
        List<Entity> e = world.getEntities(entity, bb);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof LivingEntity && !(em instanceof ArmorStand)){
                entCountValid=entCountValid+1;
                BlockPos posTarget = new BlockPos(em.getX(), em.getY()+this.getWebPos(), em.getZ());

                world.setBlock(posTarget, Blocks.COBWEB.defaultBlockState(),2);
                world.scheduleTick(posTarget,Blocks.COBWEB.defaultBlockState().getBlock(),0);
            }

        }

        if (entCountValid == 0) {
            for (int j = 0; j < 16; ++j) {

                double d0 = (double) j / 127.0D;
                float f = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                float f1 = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                float f2 = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                double d1 = Mth.lerp(d0, entity.xo, entity.getX()) + (entity.getRandom().nextDouble() - 0.5D) * (double) entity.getBbWidth() * 6.0D;
                double d2 = Mth.lerp(d0, entity.yo, entity.getY()) + entity.getRandom().nextDouble() * (double) entity.getBbHeight();
                double d3 = Mth.lerp(d0, entity.zo, entity.getZ()) + (entity.getRandom().nextDouble() - 0.5D) * (double) entity.getBbWidth() * 6.0D;
                world.addParticle(ParticleTypes.ANGRY_VILLAGER, d1, d2, d3, (double) f, (double) f1, (double) f2);
            }
        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, currentSword);
    }

    @Override
    public void unlockSEACH(Player entity, Level world) {
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayer);
        ModTrigger.Bestbothtrigger.trigger(serverPlayer);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.PEACEFUL_NATURE_COOLDOWN.get();
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.getInventory().contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.PEACEFUL_NATURE_USE_COST.get(),entity,Player -> {
                unlockDestroyACH(entity,world);
                Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        if(Objects.equals(ForgeRegistries.ITEMS.getKey(entity.getOffhandItem().getItem()), RegistryHandler.combustion_SWORD.getId()) || Objects.equals(ForgeRegistries.ITEMS.getKey(entity.getOffhandItem().getItem()), RegistryHandler.combustion_SWORD.getId())){
            this.setWebPos(1);
        }else{
            this.setWebPos(0);
        }
        return callerRC(world,entity,handIn, RegistryHandler.peaceful_NATURE.getId(),SwordConfig.PEACEFUL_NATURE_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,Player -> {
            if(attacker instanceof Player){
                unlockDestroyACH((Player) attacker,attacker.getCommandSenderWorld());
            }
            Player.broadcastBreakEvent(EquipmentSlot.MAINHAND);        });
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,SwordConfig.PEACEFUL_NATURE_HIT_TK.get(),3));
        return true;
    }
}

