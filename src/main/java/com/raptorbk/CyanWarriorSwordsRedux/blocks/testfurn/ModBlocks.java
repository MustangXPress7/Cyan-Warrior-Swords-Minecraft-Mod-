package com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

@Mod.EventBusSubscriber(modid= CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE = registerBlock("transmutation_furnace", CyanWarriorSwordsReduxMod.TAB, new TransmutationFurnaceBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(2.0F, 6.0F)));
    }



    private static ToIntFunction<BlockState> getLightLevelWhenLit(final int lightLevel) {
        return (blockState) -> blockState.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

    public static Block registerBlock(String name, ItemGroup itemGroup, Block block) {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(itemGroup));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
}