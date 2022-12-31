package com.raptorbk.CyanWarriorSwordsRedux.swords.DarkType;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.config.SwordConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class DARK_NETHER extends SWORD_CWSR {
    private static IItemTier iItemTier = new IItemTier() {
        private Item repairItem;
        @Override
        public int getUses() {
            return SwordConfig.DARK_NETHER_SWORD_DUR.get();
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

    public DARK_NETHER() {
        super(iItemTier, SwordConfig.DARK_NETHER_SWORD_DMG.get(), -2.4F, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    @Override
    public void setDamagePU() {
        this.damagePU=SwordConfig.DARK_NETHER_SWORD_USE_COST.get();
    }

    @Override
    public void setCD(){
        this.swordCD=SwordConfig.DARK_NETHER_SWORD_COOLDOWN.get();
    }

    public static void callEffect(SurroundEffect seffect, World world, PlayerEntity entity, Hand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.dark_nether"));
    }


    @Override
    public void unlockSEACH(PlayerEntity entity, World world) {
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
        ModTrigger.Themoretrigger.trigger(serverPlayerEntity);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getItemInHand(handIn);





        //Creo que esto no genera efecto de wither en el enemigo
        entity.addEffect(new EffectInstance(Effects.BLINDNESS,20,1));
        Vector3d vec3 = entity.getViewVector(1.0F);
        WitherSkullEntity witherEntity = new WitherSkullEntity(world, entity,vec3.x,vec3.y,vec3.z);
        witherEntity.xRot = entity.xRot;
        witherEntity.yRot = entity.yRot;
        witherEntity.setPos(entity.getX(),entity.getY()+2,entity.getZ());
        witherEntity.xPower=vec3.x;
        witherEntity.yPower=vec3.y;
        witherEntity.zPower=vec3.z;
        world.addFreshEntity(witherEntity);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WITHER_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 70){
            return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addEffect(new EffectInstance(Effects.WITHER,80,1));
            return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
        }
        else if (game < 10){
            entity.addEffect(new EffectInstance(Effects.BLINDNESS,80,1));
            return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
        }
        else{
            entity.addEffect(new EffectInstance(Effects.CONFUSION,80,1));
            return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){
        target.addEffect(new EffectInstance(Effects.WITHER,SwordConfig.DARK_NETHER_SWORD_HIT_TK.get(),0));
        stack.hurtAndBreak(SwordConfig.ALL_SWORDS_HIT_COST.get(),attacker,playerEntity -> {
            if(attacker instanceof PlayerEntity){
                unlockDestroyACH((PlayerEntity) attacker,attacker.getCommandSenderWorld());
            }
            playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }


    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack currentSword = entity.getItemInHand(handIn);

        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);




        if(!lfAbilityTotem(entity) && ((entity.getMainHandItem() != entity.getItemInHand(handIn) && entity.getMainHandItem().getItem() instanceof SWORD_CWSR && entity.inventory.contains(ActiveSynergyTotemStack)) || entity.getMainHandItem() == entity.getItemInHand(handIn) || (entity.getOffhandItem()==entity.getItemInHand(handIn) && !(entity.getMainHandItem().getItem() instanceof SWORD_CWSR)))){
                currentSword.hurtAndBreak(SwordConfig.DARK_NETHER_SWORD_USE_COST.get(),entity,playerEntity -> {
                unlockDestroyACH(entity,world);
                playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }
        return callerRC(world,entity,handIn, RegistryHandler.dark_NETHER.getId(),SwordConfig.DARK_NETHER_SWORD_COOLDOWN.get());
    }
}
