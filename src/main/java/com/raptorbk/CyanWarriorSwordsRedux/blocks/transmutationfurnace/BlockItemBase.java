package com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.List;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block block){
        super(block, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }
}
