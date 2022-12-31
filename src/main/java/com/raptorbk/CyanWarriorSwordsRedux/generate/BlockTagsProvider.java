package com.raptorbk.CyanWarriorSwordsRedux.generate;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace.TransmutationFurnaceBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTagsProvider extends net.minecraft.data.tags.BlockTagsProvider {
    public BlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, CyanWarriorSwordsReduxMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        //tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.get());
    }
}
