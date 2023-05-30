package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class TransmutationRecipe extends AbstractCookingRecipe {


    public final ResourceLocation id;
    public final String tab;
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int cookTime;

    public TransmutationRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
        super(CyanWarriorSwordsRecipeType.TRANSMUTATION.get(), idIn, groupIn, CookingBookCategory.MISC,ingredientIn, resultIn, experienceIn, cookTimeIn);

        this.id = idIn;
        this.tab = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }



    @Override
    public ItemStack assemble(Container inv) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public String getGroup() {
        return this.tab;
    }




    @Override
    public ItemStack getToastSymbol(){return new ItemStack(ModItems.TRANSMUTATION_FURNACE.get());}

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MiscObjects.transmutation;
    }

    @Override
    public RecipeType<TransmutationRecipe> getType()
    {
        return CyanWarriorSwordsRecipeType.TRANSMUTATION.get();
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public float getExperience() {
        return this.experience;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }
}
