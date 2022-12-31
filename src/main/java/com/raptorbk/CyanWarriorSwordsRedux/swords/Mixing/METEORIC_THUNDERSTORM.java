package com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.METEOR_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class METEORIC_THUNDERSTORM extends METEOR_CLASS_SWORD {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.METEORIC_THUNDERSTORM_DUR.get();
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

    public METEORIC_THUNDERSTORM() {
        super(iItemTier, SwordConfig.METEORIC_THUNDERSTORM_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }


    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }
    public int fireballStrength = 3;

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.METEORIC_THUNDERSTORM_USE_COST.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.meteoric_thunderstorm"));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {
        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));



        ItemStack currentSword = entity.getItemInHand(handIn);



        ServerWorld worldSV = (ServerWorld) world;


        entity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,200,0));
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
                if(entity.getOffhandItem().getItem() instanceof METEORIC_THUNDERSTORM){
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

        float var4 = 1.0F;
        int j = (int)(entity.yo + (entity.getY() - entity.yo) * (double)var4 + 1.62D - entity.getMyRidingOffset());

        //LightningBoltEntity entityBolt = new LightningBoltEntity(worldSV, entity.getX(), entity.getY(), entity.getZ(), false);
        LightningBoltEntity entityBolt = EntityType.LIGHTNING_BOLT.create(worldSV);
        entityBolt.moveTo(entity.getX(), entity.getY(), entity.getZ());

        entity.fallDistance=0.0F;
        double motionX = (double)(-MathHelper.sin(entity.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(entity.xRot / 180.0F * (float)Math.PI) * 9F);
        double motionZ = (double)(MathHelper.cos(entity.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(entity.xRot / 180.0F * (float)Math.PI) * 9F);
        double motionY = (double)(-MathHelper.sin((entity.xRot + 0F) / 180.0F * (float)Math.PI) * 9F);
        //entity.push(((double)(-MathHelper.sin(entity.yRot * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F, 0.1D, ((double)(MathHelper.cos(entity.yRot * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F);
        entity.push(motionX,motionY,motionZ);
        entity.hurtMarked=true;


        entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,10,5));
        //worldSV.addFreshEntity(entityBolt);
        worldSV.addFreshEntity(entityBolt);
        entity.addEffect(new EffectInstance(Effects.REGENERATION,80,3));
        entity.fallDistance=0.0F;



        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.METEORIC_THUNDERSTORM_COOLDOWN.get();
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.METEORIC_THUNDERSTORM_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }


        return callerRC(world,entity,handIn,RegistryHandler.meteoric_THUNDERSTORM.getId(),SwordConfig.METEORIC_THUNDERSTORM_COOLDOWN.get());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.knockback(2,attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        target.setSecondsOnFire(SwordConfig.METEORIC_THUNDERSTORM_HIT_SEC.get());
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public void unlockSEACH(PlayerEntity entity, World world) {
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
        ModTrigger.Bestbothtrigger.trigger(serverPlayerEntity);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        unlockSEACH(entity,world);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        stack.enchant(Enchantments.FIRE_ASPECT,2);
        world.explode(entity,entity.getX(),entity.getY(),entity.getZ(),1.0F, Explosion.Mode.NONE);
    }


    public void addEffectsTick(PlayerEntity playerIn){
        playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,20,4));
        playerIn.addEffect(new EffectInstance(Effects.JUMP,30,4));
        playerIn.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,40,0));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(this.getDelayMeteor() && entityIn instanceof PlayerEntity && (((PlayerEntity) entityIn).getMainHandItem()==stack || ((PlayerEntity) entityIn).getOffhandItem()==stack)){
            if(this.getCount() >= 10){
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), RegistryHandler.meteoric_THUNDERSTORM.getId())){
                    addEffectsTick(playerIn);
                }
            }
        }
    }
}
