package com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TransmutationFurnaceBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyanWarriorSwordsReduxMod.MOD_ID);

    public static final RegistryObject<Block> TRANSMUTATION_FURNACE = BLOCKS.register("transmutation_furnace", () -> new TransmutationFurnaceBlock(BlockBehaviour
            .Properties.of(Material.STONE)
            .sound(SoundType.STONE)
            .strength(1.5f,6.0f)
            .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 13 : 0)));

}
