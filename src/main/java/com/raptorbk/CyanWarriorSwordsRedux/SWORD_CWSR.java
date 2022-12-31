package com.raptorbk.CyanWarriorSwordsRedux;


import com.raptorbk.CyanWarriorSwordsRedux.init.ModEnchantments;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModTriggers;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.ABILITY_TOTEM;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.ACTIVE_SYNERGY_TOTEM;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.SYNERGY_TOTEM;
import com.raptorbk.CyanWarriorSwordsRedux.util.IHasModel;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SWORD_CWSR extends ItemSword implements IHasModel {
    public boolean firstExec=true;
    public boolean blocker=false;
    public int damagePU=0;
    public boolean damageBool=false;
    public int webPos;
    public int count=0;
    public boolean delayThrow=false;
    public int swordCD=0;
    public EntityEnderPearl throwEnder=null;
    public SWORD_CWSR(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModItems.cyanWarriorTab);
        ModItems.ITEMS.add(this);
    }




    public int getSwordCD() {return this.swordCD;}

    public void setThrowEnder(EntityEnderPearl throwEnder) {
        this.throwEnder = throwEnder;
    }

    public void setDelayThrow(boolean x){
        this.delayThrow=x;
    }

    public boolean getDelayThrow(){
        return this.delayThrow;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int x){
        this.count=x;
    }


    public void throwEnderPearlEvent(Entity entityIn,World worldIn, ItemStack stack){
        if(this.getDelayThrow() && entityIn instanceof EntityPlayer && (((EntityPlayer) entityIn).getHeldItemMainhand()==stack || ((EntityPlayer) entityIn).getHeldItemOffhand()==stack)){
            if(this.getCount() >= 5){
                this.setCount(0);
                this.setDelayThrow(false);
                worldIn.spawnEntity(this.getThrowEnder());
                this.setThrowEnder(null);
            }else{
                this.setCount(this.getCount()+1);
            }
        }
    }

    public void unlockDWACH(EntityPlayer entity, World world){
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Dualwieldachtrigger.trigger(serverEntityPlayer);
    }

    protected boolean applyToAdvancement(EntityPlayerMP player, Advancement advancementIn) {
        AdvancementProgress advancementprogress = player.getAdvancements().getProgress(advancementIn);
        if (advancementprogress.isDone()) {
            return false;
        } else {
            for(String s : advancementprogress.getRemaningCriteria()) {
                player.getAdvancements().grantCriterion(advancementIn, s);
            }
            return true;
        }
    }



    public EntityEnderPearl getThrowEnder() {
        return this.throwEnder;
    }

    public int getWebPos(){
        return this.webPos;
    }

    public void setWebPos(int x){
        this.webPos=x;
    }

    public void setDamageBool(boolean x){
        this.damageBool=x;
    }

    public void setDamagePU(){
        this.damagePU=0;
    }

    public void setEnderPearlPos(int x){
        this.damagePU=30;
    }


    public int getDamagePU(){
        return this.damagePU;
    }

    public void setBlocker(boolean x){
        this.blocker=x;
    }

    public boolean getDamageBool(){
        return this.damageBool;
    }

    public void useRightClick(World worldIn, EntityPlayer playerIn, EnumHand EnumHand) {
        this.firstExec=false;
       this.getContainerItem().onItemRightClick(worldIn, playerIn, EnumHand);
    }

    public ActionResult<ItemStack> eventRC(World world, EntityPlayer entity, EnumHand EnumHandIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getHeldItem(EnumHandIn);

        return new ActionResult<>(EnumActionResult.SUCCESS, currentSword);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        unlockSEACH(playerIn,worldIn);
        super.onCreated(stack, worldIn, playerIn);
    }

    public boolean lfAbilityTotem(EntityPlayer entity){
        List<ItemStack> listItems = entity.inventory.mainInventory;
        boolean isINH=false;
        for (ItemStack temp : listItems) {
            if(temp.getItem() instanceof ABILITY_TOTEM){
                if(checkINH(temp)){
                    isINH=true;
                }
                break;
            }
        }
        return isINH;
    }

    public boolean checkINH(ItemStack totemItem){
       Map<Enchantment, Integer> xEnc= EnchantmentHelper.getEnchantments(totemItem);
       if(xEnc.containsKey(ModEnchantments.InhEnchantment)){
            return true;
        }else{
            return false;
        }
    }

    public void unlockSEACH(EntityPlayer entity, World world){
        if(!(world instanceof WorldServer)) return;
        EntityPlayerMP serverEntityPlayer= (EntityPlayerMP) entity;
        ModTriggers.Somethingelsetrigger.trigger(serverEntityPlayer);
    }





    public void setCD(){ }

    public ActionResult<ItemStack> callerRC(World world, EntityPlayer entity, EnumHand EnumHandIn, ResourceLocation swordCH, int CooldownRC) {


        ItemStack OffHandItem = entity.getHeldItemOffhand();
        ItemStack MainHandItem= entity.getHeldItemMainhand();
        ItemStack ReturnableItem=null;




        if(Objects.equals(MainHandItem.getItem().getRegistryName(), swordCH)){
            ReturnableItem=MainHandItem;
        }else{
            ReturnableItem=OffHandItem;
        }

        ItemStack ActiveSynergyTotemStack = new ItemStack(ModItems.ActiveSynergyTotem,1);

        List<ItemStack> listItems = entity.inventory.mainInventory;
        boolean isINH=false;
        for (ItemStack temp : listItems) {
            if(temp.getItem() instanceof SYNERGY_TOTEM || temp.getItem() instanceof ACTIVE_SYNERGY_TOTEM || temp.getItem() instanceof ABILITY_TOTEM){
                if(checkINH(temp)){
                    isINH=true;
                }
                break;
            }
        }

        if(isINH){
            return new ActionResult<>(EnumActionResult.SUCCESS, ReturnableItem);
        }

        if(Objects.equals(OffHandItem.getItem().getRegistryName(), swordCH)   && MainHandItem.getItem() instanceof SWORD_CWSR & !(Objects.equals(OffHandItem.getItem().getRegistryName(),MainHandItem.getItem().getRegistryName()))){
            return new ActionResult<>(EnumActionResult.SUCCESS, ReturnableItem);
        }

        if(firstExec){
            if(!(MainHandItem.getItem() instanceof SWORD_CWSR)){  //Mano izquierda y en la derecha nada u otra cosa
                entity.getCooldownTracker().setCooldown(ReturnableItem.getItem(), CooldownRC);

                return eventRC(world,entity,EnumHandIn,ReturnableItem);
            }else if (MainHandItem.getItem() instanceof SWORD_CWSR && OffHandItem.getItem() instanceof SWORD_CWSR){{  //Las dos son espadas cwsr
                if(!(entity.getCooldownTracker().hasCooldown(OffHandItem.getItem()))){
                    if(entity.inventory.hasItemStack(ActiveSynergyTotemStack)){
                        if(!(Objects.equals(OffHandItem.getItem().getRegistryName(),MainHandItem.getItem().getRegistryName()))){
                            if(!blocker){
                                ((SWORD_CWSR) OffHandItem.getItem()).setDamagePU();
                                OffHandItem.damageItem(((SWORD_CWSR) OffHandItem.getItem()).getDamagePU(),entity);
                                ((SWORD_CWSR) OffHandItem.getItem()).setCD();
                                entity.getCooldownTracker().setCooldown(OffHandItem.getItem(), ((SWORD_CWSR) OffHandItem.getItem()).getSwordCD());
                                ((SWORD_CWSR) OffHandItem.getItem()).eventRC(world, entity, EnumHandIn,OffHandItem);
                                unlockDWACH(entity,world);
                            }else{
                                this.setBlocker(false);
                                this.setDamageBool(false);
                            }
                        }
                        this.firstExec=true;
                    }
                }
                if(MainHandItem != OffHandItem){
                    entity.getCooldownTracker().setCooldown(ReturnableItem.getItem(), CooldownRC);
                }

                return eventRC(world,entity,EnumHandIn,ReturnableItem);
            }}else{
                entity.getCooldownTracker().setCooldown(ReturnableItem.getItem(), CooldownRC);
                return eventRC(world,entity,EnumHandIn,ReturnableItem);  //Mano derecha
            }
        }else{
            if(MainHandItem != OffHandItem){
                entity.getCooldownTracker().setCooldown(ReturnableItem.getItem(), CooldownRC);
            }
            this.firstExec=true;
            return eventRC(world,entity,EnumHandIn,ReturnableItem);
        }
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this,0,"inventory");
    }
}
