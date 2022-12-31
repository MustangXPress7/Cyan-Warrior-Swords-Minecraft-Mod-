package com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace;

import com.raptorbk.CyanWarriorSwordsRedux.Menus.TransmutationFurnaceMenu;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.recipeInit;


public class TransmutationFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    private AbstractCookingRecipe recipeType;

    protected TransmutationFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(TransmutationFurnaceTileEntities.TRANSMUTATION_FURNACE.get(), pos, state, CyanWarriorSwordsRecipeType.TRANSMUTATION.get() );
    }



    @Override
    protected Component getDefaultName() {
        Component titleUI=Component.translatable("ui.cwsr.transfurnace.title");
        return Component.translatable(titleUI.getString());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new TransmutationFurnaceMenu(id, player, this, this.dataAccess);
    }

    /* FOLLOWING Code helps the copied code below. */

    public static final int BURN_TIME = 0;
    public static final int RECIPES_USED = 1;
    public static final int COOK_TIME = 2;
    public static final int COOK_TIME_TOTAL = 3;

    /* FOLLOWING Code is copied from "Shadows-of-Fire/FastFurnace" mod to enhance performance */

    public static final int INPUT = 0;
    public static final int FUEL = 1;
    public static final int OUTPUT = 2;

    protected AbstractCookingRecipe curRecipe;
    protected ItemStack failedMatch = ItemStack.EMPTY;

    private boolean isBurning() {
        return this.dataAccess.get(BURN_TIME) > 0; //changed because of private variable
    }



    public static void tick(Level level, BlockPos pos, BlockState state, TransmutationFurnaceBlockEntity transmutationfurnace){
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, transmutationfurnace);

        int progress = transmutationfurnace.dataAccess.get(2);

        if (!level.isClientSide && ((progress - 1) % 32 == 0 && (progress + 16 < transmutationfurnace.dataAccess.get(3)))) {
            level.scheduleTick(pos, TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.get(), 0);
        }
    }
}
