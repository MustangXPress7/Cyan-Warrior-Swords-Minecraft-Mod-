package com.raptorbk.CyanWarriorSwordsRedux.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.METEOR_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class METEOR_SWORD extends METEOR_CLASS_SWORD {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.METEOR_SWORD_DUR.get();
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

    public METEOR_SWORD() {
        super(iItemTier, SwordConfig.METEOR_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public int fireballStrength = 2;


    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.METEOR_SWORD_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.meteor_sword"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getItemInHand(handIn);

        Vector3d vec3 = entity.getViewVector(1.0F);
        FireballEntity fireballentity = new FireballEntity(world, entity,vec3.x,vec3.y,vec3.z);
        fireballentity.explosionPower = fireballStrength;
        fireballentity.xRot = entity.xRot;
        fireballentity.yRot = entity.yRot;
        fireballentity.setPos(entity.getX(),entity.getY()+2,entity.getZ());
        fireballentity.xPower=vec3.x;
        fireballentity.yPower=vec3.y;
        fireballentity.zPower=vec3.z;

        ItemStack x = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);
        if(entity.inventory.contains(x)){
            if(entity.getOffhandItem().getItem() instanceof METEOR_CLASS_SWORD && entity.getMainHandItem().getItem() instanceof METEOR_CLASS_SWORD){
                if(entity.getOffhandItem().getItem() instanceof METEOR_SWORD){
                    this.setDelayMeteor(true);
                    this.setDelayedMeteor(fireballentity);
                }else{
                    world.addFreshEntity(fireballentity);
                }

            }else{
                world.addFreshEntity(fireballentity);
            }
        }else{
            world.addFreshEntity(fireballentity);
        }


        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.METEOR_SWORD_COOLDOWN.get();
    }


    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.METEOR_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.meteor_SWORD.getId(),SwordConfig.METEOR_SWORD_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.setSecondsOnFire(SwordConfig.METEOR_SWORD_HIT_SEC.get());
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
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        stack.enchant(Enchantments.FIRE_ASPECT,2);
        world.explode(entity,entity.getX(),entity.getY(),entity.getZ(),1.0F, Explosion.Mode.NONE);
    }

    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,10,1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(this.getDelayMeteor() && entityIn instanceof PlayerEntity && (((PlayerEntity) entityIn).getMainHandItem()==stack || ((PlayerEntity) entityIn).getOffhandItem()==stack)){
            if(this.getCount() >= 5){
                this.setCount(0);
                this.setDelayMeteor(false);
                worldIn.addFreshEntity(this.getDelayedMeteor());
                this.setDelayedMeteor(null);
            }else{
                this.setCount(this.getCount()+1);
            }
        }


        if(isSelected && !worldIn.isClientSide){
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;
                addEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) entityIn;

                ItemStack OffHandItem = playerIn.getOffhandItem();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.meteor_SWORD.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
}
