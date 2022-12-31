package com.raptorbk.CyanWarriorSwordsRedux.items.swords.FireType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.SwordClasses.METEOR_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;
import java.util.Random;

public class METEOR_SWORD extends METEOR_CLASS_SWORD {
    public METEOR_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.METEOR_SWORD_USE_COST,entity);
        }

        return callerRC(world,entity,handIn,ModItems.meteor_SWORD.getRegistryName(),CwsrConfig.METEOR_SWORD_COOLDOWN);
    }
    public int fireballStrength = 2;

    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);
        Vec3d vec3 = entity.getLook(1.0F);
        EntityLargeFireball fireballentity = new EntityLargeFireball(world, entity,vec3.x,vec3.y,vec3.z);
        fireballentity.explosionPower = fireballStrength;
        fireballentity.rotationPitch = entity.rotationPitch;
        fireballentity.rotationYaw = entity.rotationYaw;
        fireballentity.setPosition(entity.posX,entity.posY+2,entity.posZ);
        fireballentity.accelerationX=vec3.x;
        fireballentity.accelerationY=vec3.y;
        fireballentity.accelerationZ=vec3.z;

        ItemStack x = new ItemStack(ModItems.ActiveSynergyTotem,1);
        if(entity.inventory.hasItemStack(x)){
            if(entity.getHeldItemOffhand().getItem() instanceof METEOR_CLASS_SWORD && entity.getHeldItemMainhand().getItem() instanceof METEOR_CLASS_SWORD){
                if(entity.getHeldItemOffhand().getItem() instanceof METEOR_SWORD){
                    this.setDelayMeteor(true);
                    this.setDelayedMeteor(fireballentity);
                }else{
                    world.spawnEntity(fireballentity);
                }

            }else{
                world.spawnEntity(fireballentity);
            }
        }else{
            world.spawnEntity(fireballentity);
        }


        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.METEOR_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.METEOR_SWORD_COOLDOWN;
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.setFire(CwsrConfig.METEOR_SWORD_HIT_SEC);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,10,1));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(this.getDelayMeteor() && entityIn instanceof EntityPlayer && (((EntityPlayer) entityIn).getHeldItemMainhand()==stack || ((EntityPlayer) entityIn).getHeldItemOffhand()==stack)){
            if(this.getCount() >= 5){
                this.setCount(0);
                this.setDelayMeteor(false);
                worldIn.spawnEntity(this.getDelayedMeteor());
                this.setDelayedMeteor(null);
            }else{
                this.setCount(this.getCount()+1);
            }
        }


        if(isSelected && !worldIn.isRemote){
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;
                addMobEffectsTick(playerIn);
            }
        }else{
            if(entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer) entityIn;

                ItemStack OffHandItem = playerIn.getHeldItemOffhand();
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.meteor_SWORD.getRegistryName())){
                    addMobEffectsTick(playerIn);
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer entity) {
        unlockSEACH(entity,world);
        Random x=new Random();
        world.playSound((EntityPlayer) null, entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (x.nextFloat() * 0.4F + 0.8F));
        stack.addEnchantment(Enchantments.FIRE_ASPECT,2);
        world.createExplosion(entity,entity.posX,entity.posY,entity.posZ,0.1F, false);
    }
}
