package com.raptorbk.CyanWarriorSwordsRedux.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ExecuteSeffect;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class FIRE_SWORD extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.FIRE_SWORD_DUR.get();
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

    public FIRE_SWORD() {
        super(iItemTier, SwordConfig.FIRE_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.FIRE_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.fire_sword"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);


        Block blk = Blocks.FIRE;
        callEffect(new ExecuteSeffect(), world,entity,handIn,blk);
        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }


    @Override
    public void setCD() {
        this.swordCD=SwordConfig.FIRE_SWORD_COOLDOWN.get();
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.FIRE_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        if(Objects.equals(entity.getOffhandItem().getItem().getRegistryName(),RegistryHandler.wild_NATURE.getId())){
            SWORD_CWSR x = (SWORD_CWSR) entity.getOffhandItem().getItem();
            x.setDamageBool(true);
            x.setBlocker(true);
            Hand f = Hand.OFF_HAND;
            if(!(entity.getCooldowns().isOnCooldown(x))){
                ((SWORD_CWSR) x.getItem()).eventRC(world,entity,f,entity.getOffhandItem());
                entity.getCooldowns().addCooldown(x.getItem(), SwordConfig.WILD_NATURE_SWORD_COOLDOWN.get());
            }
            return super.use(world,entity,handIn);
        }else{
            if(Objects.equals(entity.getMainHandItem().getItem().getRegistryName(),RegistryHandler.fire_SWORD)){
                return callerRC(world,entity,handIn,RegistryHandler.fire_SWORD.getId(),SwordConfig.FIRE_SWORD_COOLDOWN.get());
            }else{
                if(Objects.equals(entity.getMainHandItem().getItem().getRegistryName(),RegistryHandler.water_SWORD)){
                    return super.use(world,entity,handIn);
                }else{
                    return callerRC(world,entity,handIn,RegistryHandler.fire_SWORD.getId(),SwordConfig.EARTH_SWORD_COOLDOWN.get());
                }
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.setSecondsOnFire(SwordConfig.FIRE_SWORD_HIT_SEC.get());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.fire_SWORD.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
}
