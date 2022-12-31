package com.raptorbk.CyanWarriorSwordsRedux.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ExecuteSeffect;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class COMBUSTION_SWORD extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.COMBUSTION_SWORD_DUR.get();
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

    public COMBUSTION_SWORD() {
        super(iItemTier, SwordConfig.COMBUSTION_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.COMBUSTION_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.combustion_sword"));
    }


    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {


        int radius=8;
        AxisAlignedBB bb = new AxisAlignedBB(entity.getX()-radius, entity.getY()-radius, entity.getZ()-radius, entity.getX()+radius, entity.getY()+radius, entity.getZ()+radius);
        List<Entity> e = world.getEntities(entity, bb);


        if(world.isClientSide){
            int entCountValid=0;
            for (int i = 0; i <= e.size() - 1; i++) {
                Entity em = e.get(i);
                if (em instanceof LivingEntity && !(em instanceof ArmorStandEntity)){
                    entCountValid=entCountValid+1;
                }

            }

            if(entCountValid==0){
                for(int j = 0; j < 16; ++j) {

                    double d0 = (double)j / 127.0D;
                    float f = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float f1 = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float f2 = (entity.getRandom().nextFloat() - 0.5F) * 0.2F;
                    double d1 = MathHelper.lerp(d0, entity.xo, entity.getX()) + (entity.getRandom().nextDouble() - 0.5D) * (double)entity.getBbWidth() * 6.0D;
                    double d2 = MathHelper.lerp(d0, entity.yo, entity.getY()) + entity.getRandom().nextDouble() * (double)entity.getBbHeight();
                    double d3 = MathHelper.lerp(d0, entity.zo, entity.getZ()) + (entity.getRandom().nextDouble() - 0.5D) * (double)entity.getBbWidth() * 6.0D;
                    world.addParticle(ParticleTypes.ANGRY_VILLAGER, d1, d2, d3, (double)f, (double)f1, (double)f2);
                }
            }
        }

        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));

        ItemStack currentSword = entity.getItemInHand(handIn);


        callEffect(new ExecuteSeffect(), world,entity,handIn, Blocks.FIRE);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof LivingEntity && !(em instanceof ArmorStandEntity)){
                BlockPos posTarget = new BlockPos(em.getX(), em.getY(), em.getZ());
                world.setBlock(posTarget, Blocks.FIRE.defaultBlockState(),1);
            }

        }



        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
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
        this.swordCD=SwordConfig.COMBUSTION_SWORD_COOLDOWN.get();
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.COMBUSTION_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.combustion_SWORD.getId(),SwordConfig.COMBUSTION_SWORD_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.setSecondsOnFire(SwordConfig.COMBUSTION_SWORD_HIT_SEC.get());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        unlockSEACH(entity,world);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        stack.enchant(Enchantments.FIRE_ASPECT,2);
        world.explode(entity,entity.getX(),entity.getY(),entity.getZ(),1.0F, Explosion.Mode.NONE);
    }

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,10,1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;
                addEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.combustion_SWORD.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }


}
