package com.raptorbk.CyanWarriorSwordsRedux.util.handlers;


import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.TileEntityTransmutationFurnace;
import com.raptorbk.CyanWarriorSwordsRedux.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityTransmutationFurnace.class, new ResourceLocation(Reference.MOD_ID + ":transmutation_furnace"));
    }

}

