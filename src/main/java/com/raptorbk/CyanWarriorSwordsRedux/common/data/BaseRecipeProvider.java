package com.raptorbk.CyanWarriorSwordsRedux.common.data;

import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.ISubRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseRecipeProvider extends RecipeProvider {

    private final String modid;

    public BaseRecipeProvider(DataGenerator gen, String modid){
        super(gen.getPackOutput());
        this.modid = modid;
    }

    


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        getSubRecipeProviders().forEach(subRecipeProvider -> subRecipeProvider.addRecipes(consumer));
    }


    /**
     * Gets all the sub/offloaded recipe providers that this recipe provider has.
     *
     * @implNote This is only called once per provider so there is no need to bother
     *           caching the list that this returns
     */
    protected List<ISubRecipeProvider> getSubRecipeProviders()
    {
        return Collections.emptyList();
    }
}