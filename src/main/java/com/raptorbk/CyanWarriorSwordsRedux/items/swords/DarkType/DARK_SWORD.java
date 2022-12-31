package com.raptorbk.CyanWarriorSwordsRedux.items.swords.DarkType;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class DARK_SWORD extends SWORD_CWSR {
    public DARK_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand handIn) {
        if(!(world instanceof WorldServer)) return new ActionResult<>(EnumActionResult.PASS, entity.getHeldItem(handIn));
        ItemStack currentSword = entity.getHeldItem(handIn);
        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        if(!lfAbilityTotem(entity) && ((entity.getHeldItemMainhand() != entity.getHeldItem(handIn) && entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR && entity.inventory.hasItemStack(ActiveSynergyTotemStack)) || entity.getHeldItemMainhand() == entity.getHeldItem(handIn) || (entity.getHeldItemOffhand()==entity.getHeldItem(handIn) && !(entity.getHeldItemMainhand().getItem() instanceof SWORD_CWSR)))) {
            currentSword.damageItem(CwsrConfig.DARK_SWORD_USE_COST, entity);

        }

        return callerRC(world,entity,handIn, ModItems.dark_SWORD.getRegistryName(),CwsrConfig.DARK_SWORD_COOLDOWN);
    }

    @Override
    public void setDamagePU() {
        this.damagePU=CwsrConfig.DARK_SWORD_USE_COST;
    }

    @Override
    public void setCD() {
        this.swordCD=CwsrConfig.DARK_SWORD_COOLDOWN;
    }



    @Override
    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand handIn, ItemStack OffHandItem) {
        ItemStack currentSword = entity.getHeldItem(handIn);


        entity.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY,200,4));

        Random r = new Random();
        int game = r.nextInt(100);

        if(game < 94){
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else if (game < 96){
            entity.addPotionEffect(new PotionEffect(MobEffects.WITHER,80,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else if (game < 98){
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,200,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
        else{
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,80,1));
            return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
        }
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, CwsrConfig.DARK_SWORD_WITHER_HIT_TK,4));
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,CwsrConfig.DARK_SWORD_BLIND_HIT_TK,4));
        stack.damageItem(CwsrConfig.ALL_SWORDS_HIT_COST,attacker);
        return true;
    }
}
