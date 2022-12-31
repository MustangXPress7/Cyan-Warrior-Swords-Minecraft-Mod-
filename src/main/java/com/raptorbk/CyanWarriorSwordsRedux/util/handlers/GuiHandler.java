package com.raptorbk.CyanWarriorSwordsRedux.util.handlers;


import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.ContainerTransmutationFurnace;
import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.GuiTransmutationFurnace;
import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.TileEntityTransmutationFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

        if(ID == RegistryHandler.GUI_TRANSMUTATION_FURNACE)
        {
            return new ContainerTransmutationFurnace(player.inventory, (TileEntityTransmutationFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        }


        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

        if(ID == RegistryHandler.GUI_TRANSMUTATION_FURNACE)
        {
            return new GuiTransmutationFurnace(player.inventory, (TileEntityTransmutationFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        }


        return null;
    }
}
