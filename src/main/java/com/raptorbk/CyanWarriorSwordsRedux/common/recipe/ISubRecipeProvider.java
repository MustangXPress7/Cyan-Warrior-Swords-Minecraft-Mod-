package com.raptorbk.CyanWarriorSwordsRedux.common.recipe;

import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;

public interface ISubRecipeProvider {
    void addRecipes(Consumer<IFinishedRecipe> consumer);
}
