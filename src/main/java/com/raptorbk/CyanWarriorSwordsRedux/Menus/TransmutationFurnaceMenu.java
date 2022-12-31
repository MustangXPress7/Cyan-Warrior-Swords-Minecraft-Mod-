package com.raptorbk.CyanWarriorSwordsRedux.Menus;

import com.raptorbk.CyanWarriorSwordsRedux.recipes.CustomIRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipesType;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModMenus;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class TransmutationFurnaceMenu extends AbstractFurnaceContainer{
    public TransmutationFurnaceMenu(int windowId, PlayerInventory playerInv) {
        super(ModMenus.TRANSMUTATION.get(), CyanWarriorSwordsRecipesType.TRANSMUTATION, RecipeBookCategory.BLAST_FURNACE, windowId, playerInv);
    }


    public TransmutationFurnaceMenu(int windowId, PlayerInventory playerInv, IInventory tile, IIntArray dataSlots) {
        super(ModMenus.TRANSMUTATION.get(), CyanWarriorSwordsRecipesType.TRANSMUTATION, RecipeBookCategory.BLAST_FURNACE, windowId, playerInv, tile, dataSlots);
    }
}