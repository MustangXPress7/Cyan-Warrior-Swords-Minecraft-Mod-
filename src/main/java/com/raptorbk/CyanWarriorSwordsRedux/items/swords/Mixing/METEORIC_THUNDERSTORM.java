package com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.SwordClasses.METEOR_CLASS_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Objects;
import java.util.Random;

public class METEORIC_THUNDERSTORM extends METEOR_CLASS_SWORD {
    public METEORIC_THUNDERSTORM(String name, ToolMaterial material) {
        super(name, material);
    }



    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));



        ItemStack currentSword = entity.getHeldItem(handIn);



        WorldServer worldSV = (WorldServer) world;


        entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,200,0));
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
                if(entity.getHeldItemOffhand().getItem() instanceof METEORIC_THUNDERSTORM){
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

        float var4 = 1.0F;
        int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - entity.getYOffset());

        EntityLightningBolt entityBolt = new EntityLightningBolt(worldSV, entity.posX, entity.posY, entity.posZ, false);

        entity.fallDistance=0.0F;
        double motionX = (double)(-MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 9F);
        double motionZ = (double)(MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 9F);
        double motionY = (double)(-MathHelper.sin((entity.rotationPitch + 0F) / 180.0F * (float)Math.PI) * 9F);
        //entity.addVelocity(((double)(-MathHelper.sin(entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F, 0.1D, ((double)(MathHelper.cos(entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F))/8F);
        entity.addVelocity(motionX,motionY,motionZ);
        entity.velocityChanged=true;


        entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,5));
        worldSV.addWeatherEffect(entityBolt);

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,80,3));
        entity.fallDistance=0.0F;



        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));

        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))){
            currentSword.damageItem(CwsrConfig.METEORIC_THUNDERSTORM_USE_COST,entity);
        }


        return callerRC(world,entity,handIn,ModItems.meteoric_THUNDERSTORM.getRegistryName(),CwsrConfig.METEORIC_THUNDERSTORM_COOLDOWN);
    }

    public int fireballStrength = 3;

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.METEORIC_THUNDERSTORM_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.METEORIC_THUNDERSTORM_COOLDOWN;
    }

    @Override
    public void unlockSEACH(EntityPlayer entity, World world) {
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
        ModTriggers.Bestbothtrigger.trigger(serverEntityPlayer);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.knockBack(target,2,attacker.posX - target.posX, attacker.posZ - target.posZ);
        target.setFire(CwsrConfig.METEORIC_THUNDERSTORM_HIT_SEC);
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }

    public void addMobEffectsTick(EntityPlayer playerIn){
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED,20,4));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,30,4));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,40,0));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(this.getDelayMeteor() && entityIn instanceof EntityPlayer && (((EntityPlayer) entityIn).getHeldItemMainhand()==stack || ((EntityPlayer) entityIn).getHeldItemOffhand()==stack)){
            if(this.getCount() >= 10){
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
                if(Objects.equals(OffHandItem.getItem().getRegistryName(), ModItems.meteoric_THUNDERSTORM.getRegistryName())){
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
