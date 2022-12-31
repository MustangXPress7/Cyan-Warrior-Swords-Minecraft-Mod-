package com.raptorbk.CyanWarriorSwordsRedux.compat;

import com.google.common.collect.ImmutableSet;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.TransmutationRecipe;
import net.minecraft.world.level.Level;

import java.util.Set;

public class CyanWarriorSwordRecipeRegistrar {


    public Set<TransmutationRecipe> getTransmutationRecipes(Level world)
    {
        return ImmutableSet.copyOf(world.getRecipeManager().getAllRecipesFor(CyanWarriorSwordsRecipeType.TRANSMUTATION.get()));
    }
}
