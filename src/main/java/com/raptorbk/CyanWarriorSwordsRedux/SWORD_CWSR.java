package com.raptorbk.CyanWarriorSwordsRedux;

import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SWORD_CWSR extends SwordItem {
    public boolean firstExec=true;
    public boolean blocker=false;
    public int damagePU=0;
    public boolean damageBool=false;
    public int webPos;
    public int count=0;
    public boolean delayThrow=false;
    public int swordCD=0;
    public EnderPearlEntity throwEnder=null;
    public SWORD_CWSR(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier,attackDamageIn,attackSpeedIn,builder);
    }


    public int getSwordCD() {return this.swordCD;}

    public void setThrowEnder(EnderPearlEntity throwEnder) {
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
        if(this.getDelayThrow() && entityIn instanceof PlayerEntity && (((PlayerEntity) entityIn).getMainHandItem()==stack || ((PlayerEntity) entityIn).getOffhandItem()==stack)){
            if(this.getCount() >= 5){
                this.setCount(0);
                this.setDelayThrow(false);
                worldIn.addFreshEntity(this.getThrowEnder());
                this.setThrowEnder(null);
            }else{
                this.setCount(this.getCount()+1);
            }
        }
    }

    protected boolean applyToAdvancement(ServerPlayerEntity player, Advancement advancementIn) {
        AdvancementProgress advancementprogress = player.getAdvancements().getOrStartProgress(advancementIn);
        if (advancementprogress.isDone()) {
            return false;
        } else {
            for(String s : advancementprogress.getRemainingCriteria()) {
                player.getAdvancements().award(advancementIn, s);
            }
            return true;
        }
    }

    public void unlockDestroyACH(PlayerEntity entity, World world){
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Sworddestroyedtrigger.trigger(serverPlayerEntity);
    }



    public EnderPearlEntity getThrowEnder() {
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

    public void useRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
        this.firstExec=false;
       this.getItem().use(worldIn, playerIn, hand);
    }

    public ActionResult<ItemStack> eventRC(World world, PlayerEntity entity, Hand handIn, ItemStack OffHandItem) {

        ItemStack currentSword = entity.getItemInHand(handIn);

        return new ActionResult<>(ActionResultType.SUCCESS, currentSword);
    }

    public void unlockSEACH(PlayerEntity entity, World world){
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Somethingelsetrigger.trigger(serverPlayerEntity);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        unlockSEACH(playerIn,worldIn);
        super.onCraftedBy(stack, worldIn, playerIn);
    }

    public boolean lfAbilityTotem(PlayerEntity entity){
        List<ItemStack> listItems = entity.inventory.items;
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
        if(xEnc.containsKey(RegistryHandler.inh_ENCHANTMENT.get())){
            return true;
        }else{
            return false;
        }
    }

    public void setCD(){ }

    public void unlockDWACH(PlayerEntity entity, World world){
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Dualwieldachtrigger.trigger(serverPlayerEntity);
    }

    public ActionResult<ItemStack> callerRC(World world, PlayerEntity entity, Hand handIn, ResourceLocation swordCH, int CooldownRC) {


        ItemStack OffHandItem = entity.getOffhandItem();
        ItemStack MainHandItem= entity.getMainHandItem();
        ItemStack ReturnableItem=null;




        if(Objects.equals(MainHandItem.getItem().getRegistryName(), swordCH)){
            ReturnableItem=MainHandItem;
        }else{
            ReturnableItem=OffHandItem;
        }

        ItemStack ActiveSynergyTotemStack = new ItemStack(RegistryHandler.active_synergy_TOTEM.get(),1);

        List<ItemStack> listItems = entity.inventory.items;
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
            return new ActionResult<>(ActionResultType.SUCCESS, ReturnableItem);
        }




        //Si la espada se encuentra en la mano izquierda pero hay otra en la derecha, la derecha se encarga de todo

        if(Objects.equals(OffHandItem.getItem().getRegistryName(), swordCH)   && MainHandItem.getItem() instanceof SWORD_CWSR & !(Objects.equals(OffHandItem.getItem().getRegistryName(),MainHandItem.getItem().getRegistryName()))){
            return new ActionResult<>(ActionResultType.SUCCESS, ReturnableItem);
        }

        if(firstExec){
            if(!(MainHandItem.getItem() instanceof SWORD_CWSR)){  //Mano izquierda y en la derecha nada u otra cosa
                entity.getCooldowns().addCooldown(ReturnableItem.getItem(), CooldownRC);

                return eventRC(world,entity,handIn,ReturnableItem);
            }else if (MainHandItem.getItem() instanceof SWORD_CWSR && OffHandItem.getItem() instanceof SWORD_CWSR){{  //Las dos son espadas cwsr
                if(!(entity.getCooldowns().isOnCooldown(OffHandItem.getItem()))){
                    if(entity.inventory.contains(ActiveSynergyTotemStack)){
                        if(!(Objects.equals(OffHandItem.getItem().getRegistryName(),MainHandItem.getItem().getRegistryName()))){
                            if(!blocker){
                                ((SWORD_CWSR) OffHandItem.getItem()).setDamagePU();
                                OffHandItem.hurtAndBreak(((SWORD_CWSR) OffHandItem.getItem()).getDamagePU(),entity,playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.OFFHAND));
                                ((SWORD_CWSR) OffHandItem.getItem()).setCD();
                                entity.getCooldowns().addCooldown(OffHandItem.getItem(), ((SWORD_CWSR) OffHandItem.getItem()).getSwordCD());
                                ((SWORD_CWSR) OffHandItem.getItem()).eventRC(world, entity, handIn,OffHandItem);
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
                    entity.getCooldowns().addCooldown(ReturnableItem.getItem(), CooldownRC);
                }

                return eventRC(world,entity,handIn,ReturnableItem);
            }}else{
                entity.getCooldowns().addCooldown(ReturnableItem.getItem(), CooldownRC);
                return eventRC(world,entity,handIn,ReturnableItem);  //Mano derecha
            }
        }else{
            if(MainHandItem != OffHandItem){
                entity.getCooldowns().addCooldown(ReturnableItem.getItem(), CooldownRC);
            }
            this.firstExec=true;
            return eventRC(world,entity,handIn,ReturnableItem);
        }
    }
}
