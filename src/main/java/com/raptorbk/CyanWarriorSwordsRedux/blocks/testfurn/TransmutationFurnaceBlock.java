package com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TransmutationFurnaceBlock extends FurnaceBlock {
    public TransmutationFurnaceBlock(AbstractBlock.Properties builder) {
        super(builder);
    }


    @Override
    public TileEntity newBlockEntity(@Nonnull IBlockReader worldIn) {
        return new TransmutationFurnaceTileEntity();
    }

    /**
     * Interface for handling interaction with blocks that implement AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */



    @Override
    protected void openContainer(World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof TransmutationFurnaceTileEntity) {
            player.openMenu((INamedContainerProvider)tileentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.transmutation_furnace"));
    }
}
