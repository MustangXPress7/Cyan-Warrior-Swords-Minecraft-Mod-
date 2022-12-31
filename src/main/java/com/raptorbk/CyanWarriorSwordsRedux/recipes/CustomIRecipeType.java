package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Optional;

public interface CustomIRecipeType<T extends IRecipe<?>> {
    IRecipeType<TransmutationRecipe> TRANSMUTATION = register("transmutation");

    static <T extends IRecipe<?>> net.minecraft.item.crafting.IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new net.minecraft.item.crafting.IRecipeType<T>() {
            public String toString() {
                return key;
            }
        });
    }

    default <C extends IInventory> Optional<T> matches(IRecipe<C> recipe, World worldIn, C inv) {
        return recipe.matches(inv, worldIn) ? Optional.of((T)recipe) : Optional.empty();
    }
}