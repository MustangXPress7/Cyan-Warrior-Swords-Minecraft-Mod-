package com.raptorbk.CyanWarriorSwordsRedux.essences;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModTrigger;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class ThunderEssence extends EssenceBase {
    public void unlockCACH(PlayerEntity entity, World world){
        if(!(world instanceof ServerWorld)) return;
        ServerPlayerEntity serverPlayerEntity= (ServerPlayerEntity) entity;
        ModTrigger.Removingdentstrigger.trigger(serverPlayerEntity);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        unlockCACH(playerIn,worldIn);
        super.onCraftedBy(stack, worldIn, playerIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.thunder_essence"));
    }

    public ThunderEssence() {
        super(new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }
}
