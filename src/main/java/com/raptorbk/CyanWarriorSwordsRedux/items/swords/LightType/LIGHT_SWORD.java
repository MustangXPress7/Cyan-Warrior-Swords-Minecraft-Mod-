package com.raptorbk.CyanWarriorSwordsRedux.items.swords.LightType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;
import java.util.Objects;

public class LIGHT_SWORD extends SWORD_CWSR {
    public LIGHT_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.LIGHT_SWORD_USE_COST,entity);

        }

        return callerRC(world,entity,handIn,ModItems.light_SWORD.getRegistryName(),CwsrConfig.LIGHT_SWORD_COOLDOWN);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);



        int radius=28;
        AxisAlignedBB bb = new AxisAlignedBB(entity.posX-radius, entity.posY-radius, entity.posZ-radius, entity.posX+radius, entity.posY+radius, entity.posZ+radius);
        List<Entity> e = world.getEntitiesWithinAABBExcludingEntity(entity, bb);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof EntityLivingBase){
                ((EntityLivingBase) em).addPotionEffect(new PotionEffect(MobEffects.GLOWING,1200,0));

            }

        }

        if(entity.isPotionActive(MobEffects.WITHER)){
            entity.removePotionEffect(MobEffects.WITHER);
        }
        if(entity.isPotionActive(MobEffects.BLINDNESS)){
            entity.removePotionEffect(MobEffects.BLINDNESS);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.LIGHT_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.LIGHT_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        if(target.isPotionActive(MobEffects.INVISIBILITY)){
            target.removeActivePotionEffect(MobEffects.INVISIBILITY);
        }
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,CwsrConfig.LIGHT_SWORD_WEAKNESS_HIT_TK,4));
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,CwsrConfig.LIGHT_SWORD_BLIND_HIT_TK,4));
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,240,4));
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.light_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }
}
