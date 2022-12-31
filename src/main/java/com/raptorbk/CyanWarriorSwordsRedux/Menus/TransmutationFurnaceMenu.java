package com.raptorbk.CyanWarriorSwordsRedux.Menus;

import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.recipeInit;
import net.minecraft.world.inventory.RecipeBookType;

public class TransmutationFurnaceMenu extends AbstractFurnaceMenu {

    public TransmutationFurnaceMenu(int windowId, Inventory playerInv){
        super(ModMenus.TRANSMUTATION.get(), CyanWarriorSwordsRecipeType.TRANSMUTATION.get(), RecipeBookType.BLAST_FURNACE,windowId,playerInv);
    }

    public TransmutationFurnaceMenu(int windowId, Inventory playerInv, Container tile, ContainerData dataSlots) {
        super(ModMenus.TRANSMUTATION.get(), CyanWarriorSwordsRecipeType.TRANSMUTATION.get(), RecipeBookType.BLAST_FURNACE, windowId, playerInv, tile, dataSlots);
    }
}
