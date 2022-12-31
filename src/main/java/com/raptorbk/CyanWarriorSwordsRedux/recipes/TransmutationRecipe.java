package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TransmutationRecipe extends AbstractCookingRecipe {

    public final ResourceLocation id;
    public final String tab;
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int cookTime;

    public TransmutationRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
        super(CyanWarriorSwordsRecipesType.TRANSMUTATION, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);

        this.id = idIn;
        this.tab = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }



    @Override
    public ItemStack assemble(IInventory inv) {
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
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return MiscObjects.transmutation;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public float getExperience() {
        return this.experience;
    }
}
