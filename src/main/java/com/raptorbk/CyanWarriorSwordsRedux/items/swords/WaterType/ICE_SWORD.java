package com.raptorbk.CyanWarriorSwordsRedux.items.swords.WaterType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Objects;

public class ICE_SWORD extends SWORD_CWSR {
    public ICE_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }



    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.ICE_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.ICE_SWORD_COOLDOWN;
    }


    @SideOnly(Side.CLIENT)
    private void spawnParticles(World world,EntityLivingBase entity, EnumParticleTypes particleType)
    {
        for (int i = 0; i < 20; ++i)
        {
            double d0 = world.rand.nextGaussian() * 0.02D;
            double d1 = world.rand.nextGaussian() * 0.02D;
            double d2 = world.rand.nextGaussian() * 0.02D;
            world.spawnParticle(particleType, entity.posX + (double)(world.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 1.0D + (double)(world.rand.nextFloat() * entity.height), entity.posZ + (double)(world.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2);
        }
    }

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);

        int radius=6;
        AxisAlignedBB bb = new AxisAlignedBB(entity.posX-radius, entity.posY-radius, entity.posZ-radius, entity.posX+radius, entity.posY+radius, entity.posZ+radius);
        List<Entity> e = world.getEntitiesWithinAABBExcludingEntity(entity, bb);
        for (int i = 0; i <= e.size() - 1; i++) {
            Entity em = e.get(i);
            if (em instanceof EntityLivingBase){
                EntityLivingBase xC=(EntityLivingBase) em;
                xC.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,100,4));

                xC.getRNG();

                spawnParticles(world,xC,EnumParticleTypes.SNOWBALL);
            }

        }
        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        //if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.ICE_SWORD_USE_COST, entity);
        }

        return callerRC(world,entity,handIn,ModItems.ice_SWORD.getRegistryName(),CwsrConfig.ICE_SWORD_COOLDOWN);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,CwsrConfig.ICE_SWORD_HIT_TK,3));
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityLivingBase tempEntity=(EntityLivingBase) entityIn;
                BlockPos playerBlockStandingPos=entityIn.getPosition();
                EnchantmentFrostWalker.freezeNearby(tempEntity,worldIn,playerBlockStandingPos,2);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.ice_SWORD.getRegistryName())){
                    EntityLivingBase tempEntity=(EntityLivingBase) entityIn;
                    BlockPos playerBlockStandingPos=entityIn.getPosition();
                    EnchantmentFrostWalker.freezeNearby(tempEntity,worldIn,playerBlockStandingPos,2);
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

        //Ojo con esto, esquiva cualquier tipo de bloqueo
        unlockSEACH(entity,world);
        BlockPos pos = new BlockPos(entity.posX, entity.posY+3, entity.posZ);
        world.setBlockState(pos, Blocks.ICE.getDefaultState());
    }
}
