package com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block block){
        super(block, new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }
}
