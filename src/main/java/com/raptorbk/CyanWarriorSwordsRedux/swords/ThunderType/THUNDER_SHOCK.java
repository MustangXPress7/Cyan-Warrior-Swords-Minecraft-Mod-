package com.raptorbk.CyanWarriorSwordsRedux.swords.ThunderType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
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
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class THUNDER_SHOCK extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.THUNDER_SHOCK_SWORD_DUR.get();
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

    public THUNDER_SHOCK() {
        super(iItemTier, SwordConfig.THUNDER_SHOCK_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.THUNDER_SHOCK_SWORD_USE_COST.get();
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    public static BlockRayTraceResult raytraceFromEntity(Entity e, double distance, boolean fluids) {
        Vector3d Vector3d = e.getEyePosition(1);
        Vector3d Vector3d1 = e.getViewVector(1);
        Vector3d Vector3d2 = Vector3d.add(Vector3d1.x * distance, Vector3d1.y * distance, Vector3d1.z * distance);
        return e.level.clip(new RayTraceContext(Vector3d, Vector3d2, RayTraceContext.BlockMode.OUTLINE, fluids ? RayTraceContext.FluidMode.ANY : RayTraceContext.FluidMode.NONE, e));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.thunder_shock"));
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


        ServerWorld worldSV = (ServerWorld) world;


        entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,50,4));
        LightningBoltEntity entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY(), entity.getZ()-1);



        LightningBoltEntity entityBolt2 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt2.moveTo(entity.getX(), entity.getY(), entity.getZ()+1);


        LightningBoltEntity entityBolt3 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt3.moveTo(entity.getX()+1, entity.getY(), entity.getZ());


        LightningBoltEntity entityBolt4 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt4.moveTo(entity.getX()-1, entity.getY(), entity.getZ());

        worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt2);
        worldSV.addFreshEntity(entityBolt3);
        worldSV.addFreshEntity(entityBolt4);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof LivingEntity && !(em instanceof ArmorStandEntity)){
                LightningBoltEntity entityBolt5 = EntityType.LIGHTNING_BOLT.create(worldSV);
                entityBolt5.moveTo(em.getX(), em.getY(), em.getZ());
                worldSV.addFreshEntity(entityBolt5);
            }

        }


        entity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,150,0));
        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.THUNDER_SHOCK_SWORD_COOLDOWN.get();
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.THUNDER_SHOCK_SWORD_USE_COST.get(), entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn, RegistryHandler.thunder_SHOCK.getId(),SwordConfig.THUNDER_SHOCK_SWORD_COOLDOWN.get());
    }

    @Override
    public void unlockSEACH(PlayerEntity entity, World world) {
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
        ModTrigger.Themoretrigger.trigger(serverPlayerEntity);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        if(!(world instanceof ServerWorld)) return;
        unlockSEACH(entity,world);
        ServerWorld worldSV = (ServerWorld) world;
        LightningBoltEntity entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY()+5, entity.getZ());
        worldSV.addFreshEntity(entityBolt);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker, playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,4));
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.thunder_SHOCK.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }

}
