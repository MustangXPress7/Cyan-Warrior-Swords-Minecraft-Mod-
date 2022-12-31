package com.raptorbk.CyanWarriorSwordsRedux.items.swords.WindType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;

public class WIND_IMPULSE extends SWORD_CWSR {
    public WIND_IMPULSE(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        //if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.WIND_IMPULSE_SWORD_USE_COST, entity);
        }

        return callerRC(world,entity,handIn,ModItems.wind_IMPULSE.getRegistryName(),CwsrConfig.WIND_IMPULSE_SWORD_COOLDOWN);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.WIND_IMPULSE_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.WIND_IMPULSE_SWORD_COOLDOWN;
    }


    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);


        entity.addVelocity(0,0.5,0);
        entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,100,3));
        entity.fallDistance = 0.0F;


        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,900,4));
        target.knockBack(target,2,attacker.posX - target.posX, attacker.posZ - target.posZ);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,10,4));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;
                addMobEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.earth_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Themoretrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        unlockSEACH(entity,world);
    }
}
