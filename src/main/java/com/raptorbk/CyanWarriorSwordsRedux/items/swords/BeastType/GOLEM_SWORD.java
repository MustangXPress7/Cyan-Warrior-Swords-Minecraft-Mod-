package com.raptorbk.CyanWarriorSwordsRedux.items.swords.BeastType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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

public class GOLEM_SWORD extends SWORD_CWSR {
    public GOLEM_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!(worldIn instanceof WorldServer))
            return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        ItemStack currentSword = playerIn.getHeldItem(handIn);

        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem, 1);

        if (!lfAbilityTotem(playerIn) && ((playerIn.getHeldItemMainhand() != playerIn.getHeldItem(handIn) && playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && playerIn.inventory.hasItemStack(ActiveSynergyTotemStack)) || playerIn.getHeldItemMainhand() == playerIn.getHeldItem(handIn) || (playerIn.getHeldItemOffhand() == playerIn.getHeldItem(handIn) && !(playerIn.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))) {
            currentSword.damageItem(CwsrConfig.GOLEM_SWORD_USE_COST, playerIn);
        }

        return callerRC(worldIn, playerIn, handIn, ModItems.golem_SWORD.getRegistryName(), CwsrConfig.GOLEM_SWORD_COOLDOWN);

    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer playerIn, EnumHand EnumHandIn, ItemStack OffHandItem) {
        ItemStack currentSword = playerIn.getHeldItem(EnumHandIn);
        if (!world.isRemote) {


            Vec3d look = playerIn.getLookVec();
            EntityIronGolem wolfProjectile = new EntityIronGolem(world);
            wolfProjectile.setPosition(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
            wolfProjectile.setVelocity(look.x, look.y, look.z);

            world.spawnEntity(wolfProjectile);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void setDamagePU() {
        this.damagePU = CwsrConfig.GOLEM_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD = CwsrConfig.GOLEM_SWORD_COOLDOWN;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST, attacker);
        return true;
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if (!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer = (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Themoretrigger.trigger(serverEntityPlayer);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        unlockSEACH(entity, world);
        Random x = new Random();
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_IRONGOLEM_STEP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (x.nextFloat() * 0.4F + 0.8F));
    }
}
