package com.raptorbk.CyanWarriorSwordsRedux.items.swords.BeastType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class BEAST_SWORD extends SWORD_CWSR {

    public BEAST_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }


    @Override
    public void setDamagePU() {
        this.damagePU = CwsrConfig.BEAST_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD = CwsrConfig.BEAST_SWORD_COOLDOWN;
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer playerIn, EnumHand EnumHandIn, ItemStack OffHandItem) {
        ItemStack currentSword = playerIn.getHeldItem(EnumHandIn);

        Vec3d look = playerIn.getLookVec();
        EntityWolf wolfProjectile = new EntityWolf(world);
        wolfProjectile.setPosition(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
        wolfProjectile.setVelocity(look.x, look.y, look.z);
        wolfProjectile.setTamed(true);
        wolfProjectile.setTamedBy(playerIn);
        world.spawnEntity(wolfProjectile);
        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!(worldIn instanceof WorldServer))
            return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));

        ItemStack currentSword = playerIn.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem, 1);


        if (!lfAbilityTotem(playerIn) && ((playerIn.getHeldItemMainhand() != playerIn.getHeldItem(handIn) && playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && playerIn.inventory.hasItemStack(ActiveSynergyTotemStack)) || playerIn.getHeldItemMainhand() == playerIn.getHeldItem(handIn) || (playerIn.getHeldItemOffhand() == playerIn.getHeldItem(handIn) && !(playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))) {
            currentSword.damageItem(CwsrConfig.BEAST_SWORD_USE_COST, playerIn);
            //unlockDestroyACH(playerIn,world);
        }

        return callerRC(worldIn, playerIn, handIn, ModItems.beast_SWORD.getRegistryName(), CwsrConfig.BEAST_SWORD_COOLDOWN);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        unlockSEACH(entity, world);
        Random x = new Random();
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_WOLF_HOWL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (x.nextFloat() * 0.4F + 0.8F));
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST, attacker);
        return true;
    }
}
