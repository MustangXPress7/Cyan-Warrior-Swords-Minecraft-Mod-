package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.common.data.BaseRecipeProvider;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.ISubRecipeProvider;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.TransmutationRecipeProvider;
import net.minecraft.data.DataGenerator;
import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;

import java.util.Arrays;
import java.util.List;

public class CyanWarriorSwordsRecipesProvider extends BaseRecipeProvider {
    public CyanWarriorSwordsRecipesProvider(DataGenerator gen) { super(gen, CyanWarriorSwordsReduxMod.MOD_ID); }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders(){
        return Arrays.asList(new TransmutationRecipeProvider());
    }
}
