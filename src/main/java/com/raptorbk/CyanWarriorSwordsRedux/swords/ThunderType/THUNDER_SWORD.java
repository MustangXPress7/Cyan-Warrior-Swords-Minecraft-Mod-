package com.raptorbk.CyanWarriorSwordsRedux.swords.ThunderType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class THUNDER_SWORD extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
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

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.THUNDER_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.thunder_sword"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        entity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,180,0));

        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));

        ItemStack currentSword = entity.getItemInHand(handIn);



        ServerWorld worldSV = (ServerWorld) world;
        /*LightningBoltEntity entityBolt = new LightningBoltEntity(worldSV, entity.getX(), entity.getY(), entity.getZ()-2, false);
        LightningBoltEntity entityBolt2 = new LightningBoltEntity(worldSV, entity.getX(), entity.getY(), entity.getZ()+2, false);
        LightningBoltEntity entityBolt3 = new LightningBoltEntity(worldSV, entity.getX()+2, entity.getY(), entity.getZ(), false);
        LightningBoltEntity entityBolt4 = new LightningBoltEntity(worldSV, entity.getX()-2, entity.getY(), entity.getZ(), false);


        entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,20,4));
        worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt2);
        worldSVaddEntity(entityBolt3);
        worldSVaddEntity(entityBolt4);*/
        LightningBoltEntity entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY(), entity.getZ()-2);



        LightningBoltEntity entityBolt2 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt2.moveTo(entity.getX(), entity.getY(), entity.getZ()+2);


        LightningBoltEntity entityBolt3 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt3.moveTo(entity.getX()+2, entity.getY(), entity.getZ());


        LightningBoltEntity entityBolt4 = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt4.moveTo(entity.getX()-2, entity.getY(), entity.getZ());

        worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt2);
        worldSV.addFreshEntity(entityBolt3);
        worldSV.addFreshEntity(entityBolt4);

        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }



    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.THUNDER_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.thunder_SWORD.getId(),SwordConfig.THUNDER_SWORD_COOLDOWN.get());
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

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,2));
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.THUNDER_SWORD_COOLDOWN.get();
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.thunder_SWORD.getId())){
                    addEffectsTick(playerIn);
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
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);        });
        return true;
    }
}
