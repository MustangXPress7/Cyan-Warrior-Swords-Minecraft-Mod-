package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class WIND_BLAST extends SWORD_CWSR {
    public WIND_BLAST(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        //if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.WIND_BLAST_USE_COST,entity);

        }


        return callerRC(world,entity,handIn,ModItems.wind_BLAST.getRegistryName(),CwsrConfig.WIND_BLAST_COOLDOWN);
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);



        entity.fallDistance = 0.0F;

        int radius=8;
        AxisAlignedBB bb = new AxisAlignedBB(entity.posX-radius, entity.posY-radius, entity.posZ-radius, entity.posX+radius, entity.posY+radius, entity.posZ+radius);
        List<Entity> e = world.getEntitiesWithinAABBExcludingEntity(entity, bb);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof EntityLivingBase){
                world.createExplosion(entity,em.posX,em.posY,em.posZ,0.5F, false);
            }
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    public void unlockACH(EntityPlayer entity, World world){
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Ablasttrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.WIND_BLAST_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.WIND_BLAST_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.knockBack(target,2,attacker.posX - target.posX, attacker.posZ - target.posZ);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,10,2));
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Bestbothtrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;
                addMobEffectsTick(playerIn);
                unlockACH(playerIn,worldIn);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(OffHandItem.getItem() instanceof WIND_BLAST){
                    addMobEffectsTick(playerIn);
                    unlockACH(playerIn,worldIn);
                }
            }
        }
    }
}
