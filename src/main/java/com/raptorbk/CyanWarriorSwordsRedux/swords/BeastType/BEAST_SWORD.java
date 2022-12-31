package com.raptorbk.CyanWarriorSwordsRedux.swords.BeastType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class BEAST_SWORD extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        
        
        @Override
        public int getUses() {
            return SwordConfig.BEAST_SWORD_DUR.get();
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

    public BEAST_SWORD() {
        super(iItemTier, SwordConfig.BEAST_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getItemInHand(handIn);

        Vector3d look = entity.getLookAngle();
        WolfEntity wolfProjectile = new WolfEntity(EntityType.WOLF,world);
        wolfProjectile.setPos(entity.getX(),entity.getY()+1,entity.getZ());
        wolfProjectile.setDeltaMovement(look.x,look.y,look.z);
        wolfProjectile.setTame(true);
        wolfProjectile.tame(entity);
        world.addFreshEntity(wolfProjectile);
        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.beast_sword"));
    }


    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.BEAST_SWORD_USE_COST.get();
    }

    @Override
    public void setCD() {
        this.swordCD=SwordConfig.BEAST_SWORD_COOLDOWN.get();
    }



    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        if(!(world instanceof ServerWorld)) return new ActionResult<>(ActionResultType.PASS, entity.getItemInHand(handIn));

        ItemStack currentSword = entity.getItemInHand(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);


        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
currentSword.hurtAndBreak(SwordConfig.BEAST_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return callerRC(world,entity,handIn,RegistryHandler.beast_SWORD.getId(),SwordConfig.BEAST_SWORD_COOLDOWN.get());
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



    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        unlockSEACH(entity,world);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WOLF_HOWL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
    }

}
