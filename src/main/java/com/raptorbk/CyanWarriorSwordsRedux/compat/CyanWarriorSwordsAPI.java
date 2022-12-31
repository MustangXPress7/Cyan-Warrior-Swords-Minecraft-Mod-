package com.raptorbk.CyanWarriorSwordsRedux.compat;

import javax.annotation.Nonnull;

public class CyanWarriorSwordsAPI {
    private final CyanWarriorSwordRecipeRegistrar recipeRegistrar;
    public static final CyanWarriorSwordsAPI INSTANCE = new CyanWarriorSwordsAPI();
    public CyanWarriorSwordsAPI() {
        this.recipeRegistrar = new CyanWarriorSwordRecipeRegistrar();
    }

    @Nonnull
    public CyanWarriorSwordRecipeRegistrar getRecipeRegistrar()
    {
        return recipeRegistrar;
    }
}
