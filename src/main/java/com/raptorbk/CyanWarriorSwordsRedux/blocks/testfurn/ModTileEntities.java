package com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid= CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModTileEntities {

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        TransmutationFurnaceTileEntities.TRANSMUTATION_FURNACE = register(TransmutationFurnaceTileEntity::new, "transmutation_furnace", TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE, event);
    }

    private static <T extends TileEntity> TileEntityType<T> register(Supplier<T> supplier, String registryName, Block block, RegistryEvent.Register<TileEntityType<?>> registryEvent) {
        TileEntityType<T> tileEntityType = TileEntityType.Builder.of(supplier, block).build(null);
        tileEntityType.setRegistryName(registryName);
        registryEvent.getRegistry().register(tileEntityType);
        return tileEntityType;
    }

}