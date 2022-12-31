package com.raptorbk.CyanWarriorSwordsRedux.init;


import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.TransmutationFurnace;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();



    public static final Block TRANSMUTATION_FURNACE_OFF = new TransmutationFurnace("transmutation_furnace_off", false).setCreativeTab(ModItems.cyanWarriorTab);
    public static final Block TRANSMUTATION_FURNACE_ON = new TransmutationFurnace("transmutation_furnace_on", true);

}
