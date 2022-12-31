package com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.ENDER_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ExecuteSeffect;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class TRI_ENDER extends ENDER_CLASS_SWORD {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.TRI_ENDER_DUR.get();
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

    public TRI_ENDER() {
        super(iItemTier, SwordConfig.TRI_ENDER_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }


    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.TRI_ENDER_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.tri_ender"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));

        ServerWorld worldSV = (ServerWorld) world;

        callEffect(new ExecuteSeffect(), world,entity,handIn, Blocks.FIRE);

        ItemStack ogSword = entity.getItemInHand(handIn);



        ItemStack itemstack = new ItemStack(Items.ENDER_PEARL);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        ItemStack currentSword = entity.getItemInHand(handIn);
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

        if (!world.isClientSide) {
            for (int i = 0; i <= e.size() - 1; i++) {
                Entity em = e.get(i);
                if (em instanceof ServerPlayerEntity && !(em instanceof ArmorStandEntity) && !em.isSpectator()){
                    if(!(((ServerPlayerEntity) em).isCreative()) && !(((ServerPlayerEntity) em).abilities.flying)){
                        ((ServerPlayerEntity)em).connection.send(new SEntityVelocityPacket(em));
                        ((ServerPlayerEntity) em).knockback(2,entity.getX() - em.getX(), entity.getZ() - em.getZ());
                        em.hurtMarked=true;
                    }
                }else{
                    if (em instanceof LivingEntity && !(em instanceof ArmorStandEntity)){
                        ((LivingEntity) em).knockback(2,entity.getX() - em.getX(), entity.getZ() - em.getZ());
                    }
                }
            }


            EnderPearlEntity enderpearlentity = new EnderPearlEntity(world, entity);
            enderpearlentity.setItem(itemstack);
            enderpearlentity.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, 1.5F, 1.0F);
            ItemStack x = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);
            if(entity.inventory.contains(x)){
                if(entity.getOffhandItem().getItem() instanceof ENDER_CLASS_SWORD && entity.getMainHandItem().getItem() instanceof ENDER_CLASS_SWORD){
                    if(entity.getOffhandItem().getItem() instanceof TRI_ENDER){
                        world.addFreshEntity(enderpearlentity);
                    }
                }else{
                    if((Objects.equals(entity.getMainHandItem().getItem().getRegistryName(),RegistryHandler.meteor_SWORD.getId()) || Objects.equals(entity.getOffhandItem().getItem().getRegistryName(),RegistryHandler.meteor_SWORD.getId())) || (Objects.equals(entity.getMainHandItem().getItem().getRegistryName(),RegistryHandler.meteoric_THUNDERSTORM.getId()) || Objects.equals(entity.getOffhandItem().getItem().getRegistryName(),RegistryHandler.meteoric_THUNDERSTORM.getId()))  ){
                        this.setDelayThrow(true);
                        this.setThrowEnder(enderpearlentity);
                    }else{
                        world.addFreshEntity(enderpearlentity);
                    }
                }
            }else{
                world.addFreshEntity(enderpearlentity);
            }
        }

        entity.awardStat(Stats.ITEM_USED.get(this));
        if (!entity.abilities.instabuild) {
            itemstack.shrink(0);
        }

        itemstack=ogSword;




        LightningBoltEntity entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX()+5, entity.getY(), entity.getZ()-1);



        LightningBoltEntity entityBolt2 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt2.moveTo(entity.getX()+5, entity.getY(), entity.getZ()-1);


        LightningBoltEntity entityBolt3 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt3.moveTo(entity.getX()-5, entity.getY(), entity.getZ()+1);


        LightningBoltEntity entityBolt4 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt4.moveTo(entity.getX()-5, entity.getY(), entity.getZ()-3);

        worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt2);
        worldSV.addFreshEntity(entityBolt3);
        worldSV.addFreshEntity(entityBolt4);
        entity.addEffect(new EffectInstance(Effects.REGENERATION,80,3));



        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.TRI_ENDER_COOLDOWN.get();
    }


    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
ogSword.hurtAndBreak(SwordConfig.TRI_ENDER_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.tri_ENDER.getId(),SwordConfig.TRI_ENDER_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.setSecondsOnFire(SwordConfig.TRI_ENDER_HIT_SEC.get());
        target.knockback(2,attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);        });
        return true;
    }

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.JUMP,10,3));
        playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,3));
        playerIn.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,10,1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        this.throwEnderPearlEvent(entityIn,worldIn, stack);
        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;
                addEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.tri_ENDER.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
}
