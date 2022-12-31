package com.raptorbk.CyanWarriorSwordsRedux.compat;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace.TransmutationFurnaceBlocks;
import com.raptorbk.CyanWarriorSwordsRedux.compat.transmutation.TransmutationRecipeCategory;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

@JeiPlugin
public class CyanWarriorSwordsJEIPlugin implements IModPlugin {
    public static IJeiHelpers jeiHelpers;

    private static final ResourceLocation ID = CyanWarriorSwordsReduxMod.rl("jei_plugin");

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.get().asItem()), TransmutationRecipeCategory.RECIPE_TYPE);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration){
        jeiHelpers = registration.getJeiHelpers();

        registration.addRecipeCategories(new TransmutationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }


    @Override
    public void registerRecipes(IRecipeRegistration registration){
        var recipeManager = Minecraft.getInstance().level.getRecipeManager();
        var transmutation_Recipes = recipeManager.getAllRecipesFor(CyanWarriorSwordsRecipeType.TRANSMUTATION.get());
        registration.addRecipes(TransmutationRecipeCategory.RECIPE_TYPE, transmutation_Recipes);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }
}