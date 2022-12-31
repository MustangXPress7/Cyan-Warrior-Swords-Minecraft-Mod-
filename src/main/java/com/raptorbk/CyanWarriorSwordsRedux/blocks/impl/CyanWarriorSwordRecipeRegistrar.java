package com.raptorbk.CyanWarriorSwordsRedux.blocks.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CustomIRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipesType;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.TransmutationRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public class CyanWarriorSwordRecipeRegistrar {

    public TransmutationRecipe getTRANSMUTATION(World world, @Nonnull ItemStack input)
    {
        Preconditions.checkNotNull(input, "input cannot be null.");
        if (input.isEmpty())
            return null;

        List<TransmutationRecipe> transmutationRecipes = world.getRecipeManager().getAllRecipesFor(CyanWarriorSwordsRecipesType.TRANSMUTATION);

        for (TransmutationRecipe recipe : transmutationRecipes)
        {

                    return recipe;


        }

//		if (input.isEmpty())
//			return null;
//
//		List<RecipeBloodAltar> altarRecipes = world.getRecipeManager().getRecipesForType(BloodMagicRecipeType.ALTAR);
//
//		for (RecipeBloodAltar recipe : altarRecipes) if (recipe.getInput().test(input))
//			return recipe;

        return null;
    }

    public Set<TransmutationRecipe> getTransmutationRecipes(World world)
    {
        return ImmutableSet.copyOf(world.getRecipeManager().getAllRecipesFor(CyanWarriorSwordsRecipesType.TRANSMUTATION));
    }
}
