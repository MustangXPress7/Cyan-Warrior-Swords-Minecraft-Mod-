package com.raptorbk.CyanWarriorSwordsRedux.swords.EnderType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ENDER_PORTAL extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
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

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk) {
        seffect.execute(world, entity, handIn, blk);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.ender_portal"));
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.ENDER_PORTAL_SWORD_USE_COST.get();
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        

        ItemStack itemstack = new ItemStack(Items.ENDER_EYE);
        /*if (raytraceresult.getType() == RayTraceResult.Type.BLOCK && world.getBlockState(((BlockRayTraceResult) raytraceresult).getPos()).getBlock() == Blocks.END_PORTAL_FRAME) {
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else {
            entity.startUsingItem(handIn);
            if (world instanceof ServerWorld) {
                BlockPos blockpos = ((ServerWorld) world).getChunkProvider().getChunkGenerator().findNearestStructure(world, "Stronghold", new BlockPos(entity), 100, false);
                if (blockpos != null) {
                    EyeOfEnderEntity eyeofenderentity = new EyeOfEnderEntity(world, entity.getX(), entity.getYHeight(0.5D), entity.getZ());
                    eyeofenderentity.setItem(itemstack);
                    eyeofenderentity.signalTo(blockpos);
                    world.addFreshEntity(eyeofenderentity);
                    if (entity instanceof ServerPlayerEntity) {
                        CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayerEntity) entity, blockpos);
                    }

                    world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                    world.playEvent((PlayerEntity) null, 1003, new BlockPos(entity), 0);
                    if (!entity.abilities.instabuild) {
                        itemstack.shrink(0);
                    }

                    entity.awardStat(Stats.ITEM_USED.get(this));
                    entity.swing(handIn, true);
                    itemstack=ogSword;
                    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
                }
            }*/

        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, entity, RayTraceContext.FluidMode.NONE);

            entity.startUsingItem(handIn);
            if (world instanceof ServerWorld) {
                BlockPos blockpos = ((ServerWorld)world).getChunkSource().generator.findNearestMapFeature((ServerWorld)world, Structure.STRONGHOLD, entity.blockPosition(), 100, false);
                if (blockpos != null) {
                    EyeOfEnderEntity eyeofenderentity = new EyeOfEnderEntity(world, entity.getX(), entity.getY(0.5D), entity.getZ());
                    eyeofenderentity.setItem(itemstack);
                    eyeofenderentity.signalTo(blockpos);
                    world.addFreshEntity(eyeofenderentity);
                    if (entity instanceof ServerPlayerEntity) {
                        CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayerEntity)entity, blockpos);
                    }

                    world.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                    world.levelEvent((PlayerEntity)null, 1003, entity.blockPosition(), 0);
                    if (!entity.abilities.instabuild) {
                        itemstack.shrink(0);
                    }

                    entity.awardStat(Stats.ITEM_USED.get(this));
                    entity.swing(handIn, true);
                    itemstack=ogSword;
                    return ActionResult.success(itemstack);
                }
            }
            itemstack=ogSword;


            Random r = new Random();
            int game = r.nextInt(100);

            if(game < 85){
                return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
            }else{
                entity.addEffect(new EffectInstance(Effects.CONFUSION,160,1));
                return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
            }
        }


    @Override
    public void unlockSEACH(PlayerEntity entity, World world) {
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
        ModTrigger.Themoretrigger.trigger(serverPlayerEntity);
    }


    @Override
    public void setCD() {
        this.swordCD=SwordConfig.ENDER_PORTAL_SWORD_COOLDOWN.get();
    }


    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
ogSword.hurtAndBreak(SwordConfig.ENDER_PORTAL_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

            return callerRC(world,entity,handIn, RegistryHandler.ender_PORTAL.getId(),SwordConfig.ENDER_PORTAL_SWORD_COOLDOWN.get());
    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        World worldIn = attacker.getCommandSenderWorld();
        Random pushRNG = new Random();
        int gameRNG = pushRNG.nextInt(100);
        if(gameRNG < 25){
            worldIn.playSound((PlayerEntity) null, target.getX(), target.getY(), target.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 0.4F);
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
            target.addEffect(new EffectInstance(Effects.CONFUSION,SwordConfig.ENDER_PORTAL_SWORD_HIT_TK.get(),1));
            return true;
        }

    }


}
