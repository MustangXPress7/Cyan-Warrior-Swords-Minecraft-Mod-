package com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.ENDER_CLASS_SWORD;
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
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ENDER_THUNDER extends ENDER_CLASS_SWORD {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.ENDER_THUNDER_DUR.get();
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

    public ENDER_THUNDER() {
        super(iItemTier, SwordConfig.ENDER_THUNDER_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.ENDER_THUNDER_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.ender_thunder"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));





        ServerWorld worldSV = (ServerWorld) world;


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


        entity.addEffect(new EffectInstance(Effects.REGENERATION,120,3));

        ItemStack ogSword = entity.getItemInHand(handIn);




        ItemStack itemstack = new ItemStack(Items.ENDER_PEARL);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            EnderPearlEntity enderpearlentity = new EnderPearlEntity(world, entity);
            enderpearlentity.setItem(itemstack);
            enderpearlentity.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, 1.5F, 1.0F);
            ItemStack x = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);
            if(entity.inventory.contains(x)){
                if(entity.getOffhandItem().getItem() instanceof ENDER_CLASS_SWORD && entity.getMainHandItem().getItem() instanceof ENDER_CLASS_SWORD){
                    if(entity.getOffhandItem().getItem() instanceof ENDER_THUNDER){
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

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 95){
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        }else{
            entity.addEffect(new EffectInstance(Effects.CONFUSION,160,1));
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        }
    }


    @Override
    public void setCD() {
        this.swordCD=SwordConfig.ENDER_THUNDER_COOLDOWN.get();
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack ogSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
ogSword.hurtAndBreak(SwordConfig.ENDER_THUNDER_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.ender_THUNDER.getId(), SwordConfig.ENDER_THUNDER_COOLDOWN.get());
    }

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,2));
    }


    @Override
    public void unlockSEACH(PlayerEntity entity, World world) {
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
        ModTrigger.Bestbothtrigger.trigger(serverPlayerEntity);
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.ender_THUNDER.getId())){
                    addEffectsTick(playerIn);
                    this.throwEnderPearlEvent(entityIn,worldIn, stack);
                }
            }
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
}
