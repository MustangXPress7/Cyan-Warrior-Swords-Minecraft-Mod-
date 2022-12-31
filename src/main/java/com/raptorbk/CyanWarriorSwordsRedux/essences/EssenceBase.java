package com.raptorbk.CyanWarriorSwordsRedux.essences;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class EssenceBase extends Item {

    public void unlockCACH(Player entity, Level world){
        if(!(world instanceof ServerLevel)) return;
        ServerPlayer serverPlayer= (ServerPlayer) entity;
        ModTrigger.Removingdentstrigger.trigger(serverPlayer);
    }


    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
        unlockCACH(playerIn,worldIn);
        super.onCraftedBy(stack, worldIn, playerIn);
    }

    public EssenceBase() {
        super(new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }
}
