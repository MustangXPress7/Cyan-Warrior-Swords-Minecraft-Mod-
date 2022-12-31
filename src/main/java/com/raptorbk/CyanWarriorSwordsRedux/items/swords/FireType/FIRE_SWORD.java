package com.raptorbk.CyanWarriorSwordsRedux.items.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.util.ExecuteSeffect;
import com.raptorbk.CyanWarriorSwordsRedux.util.SurroundEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;

public class FIRE_SWORD extends SWORD_CWSR {
    public FIRE_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.FIRE_SWORD_USE_COST,entity);
        }

        if(Objects.equals(entity.getHeldItemOffhand().getItem().getRegistryName(), ModItems.wild_NATURE.getRegistryName())){
            SWORD_CWSR x = (SWORD_CWSR) entity.getHeldItemOffhand().getItem();
            x.setDamageBool(true);
            x.setBlocker(true);
            EnumHand f = EnumHand.OFF_HAND;
            if(!(entity.getCooldownTracker().hasCooldown(x))){
                ((SWORD_CWSR) x).eventRC(world,entity,f,entity.getHeldItemOffhand());
                entity.getCooldownTracker().setCooldown(x, CwsrConfig.WILD_NATURE_SWORD_COOLDOWN);
            }
            return super.onItemRightClick(world,entity,handIn);
        }else{
            if(Objects.equals(entity.getHeldItemMainhand().getItem().getRegistryName(),ModItems.fire_SWORD)){
                return callerRC(world,entity,handIn,ModItems.fire_SWORD.getRegistryName(),CwsrConfig.FIRE_SWORD_COOLDOWN);
            }else{
                if(Objects.equals(entity.getHeldItemMainhand().getItem().getRegistryName(),ModItems.water_SWORD)){
                    return super.onItemRightClick(world,entity,handIn);
                }else{
                    return callerRC(world,entity,handIn,ModItems.fire_SWORD.getRegistryName(),CwsrConfig.EARTH_SWORD_COOLDOWN);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);


        Block blk = Blocks.FIRE;
        callEffect(new ExecuteSeffect(), world,entity,handIn,blk);
        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    public static void callEffect(SurroundEffect seffect, World world, EntityPlayer entity, EnumHand handIn, Block blk){
        seffect.execute(world,entity,handIn,blk);
    }


    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.FIRE_SWORD_COOLDOWN;
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.FIRE_SWORD_USE_COST;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.setFire(CwsrConfig.FIRE_SWORD_HIT_SEC);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,10,1));
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.fire_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }
}
