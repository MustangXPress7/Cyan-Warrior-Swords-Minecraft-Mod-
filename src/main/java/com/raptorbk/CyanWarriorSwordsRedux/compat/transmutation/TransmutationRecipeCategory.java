package com.raptorbk.CyanWarriorSwordsRedux.compat.transmutation;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn.TransmutationFurnaceBlocks;
import com.raptorbk.CyanWarriorSwordsRedux.common.tags.CyanWarriorSwordTags;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.TransmutationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TransmutationRecipeCategory implements IRecipeCategory<TransmutationRecipe> {
    private static final int OUTPUT_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int TRANSMUTATION_SLOT = 2;
    public static final ResourceLocation UID = CyanWarriorSwordsReduxMod.rl("transmutation");
    public static final ResourceLocation BACKGROUNDRL = CyanWarriorSwordsReduxMod.rl("gui/jei/transmutation_furnace.png");

    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public TransmutationRecipeCategory(IGuiHelper guiHelper)
    {
        icon = guiHelper.createDrawableIngredient(new ItemStack(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE));
        background = guiHelper.createDrawable(BACKGROUNDRL, 0, 0, 170, 167);
//		craftingGridHelper = guiHelper.createCraftingGridHelper(INPUT_SLOT);
    }

    @Nonnull
    @Override
    public ResourceLocation getUid()
    {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        ITextComponent string_title_jei_transmutation_category=new TranslationTextComponent("jei.cwsr.titles.transmutation");
        return string_title_jei_transmutation_category.getString();
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon()
    {
        return icon;
    }



    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull TransmutationRecipe recipe, @Nonnull IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 115, 34);
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 55, 16);

        guiItemStacks.set(ingredients);
    }

    @Override
    public Class<? extends TransmutationRecipe> getRecipeClass()
    {
        return TransmutationRecipe.class;
    }

    @Override
    public void setIngredients(TransmutationRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }
}
