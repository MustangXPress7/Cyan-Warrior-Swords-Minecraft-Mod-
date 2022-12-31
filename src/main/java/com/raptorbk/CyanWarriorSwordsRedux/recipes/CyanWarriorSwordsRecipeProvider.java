package com.raptorbk.CyanWarriorSwordsRedux.recipes;
import java.util.Arrays;
import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.common.data.BaseRecipeProvider;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.CyanWarriorSwordsRecipeBuilder;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.ISubRecipeProvider;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.TransmutationRecipeProvider;
import net.minecraft.data.DataGenerator;

import java.util.List;

public class CyanWarriorSwordsRecipeProvider extends BaseRecipeProvider {
    public CyanWarriorSwordsRecipeProvider(DataGenerator gen) { super(gen, CyanWarriorSwordsReduxMod.MOD_ID); }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders(){
        return Arrays.asList(new TransmutationRecipeProvider());
    }
}
