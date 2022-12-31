package com.raptorbk.CyanWarriorSwordsRedux.essences;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FireEssence extends Item {
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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.cwsr.fire_essence"));
    }

    public FireEssence() {
        super(new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }
}
