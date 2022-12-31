package com.raptorbk.CyanWarriorSwordsRedux.common.recipe.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.CyanWarriorSwordsRecipeBuilder;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.helper.SerializerHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

public class TransmutationRecipeBuilder extends CyanWarriorSwordsRecipeBuilder<TransmutationRecipeBuilder> {
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int cookTime;
    public final int count;

    public TransmutationRecipeBuilder(Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int count, int cookTimeIn){
        super(bmSerializer("transmutation"));

        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
        this.count = count;
    }

    public static TransmutationRecipeBuilder transmutation(Ingredient input, ItemStack resultIn, float experienceIn, int count, int cookTimeIn)
    {
        return new TransmutationRecipeBuilder(input, resultIn,experienceIn, count, cookTimeIn);
    }

    @Override
    protected TransmutationRecipeResult getResult(ResourceLocation id)
    {
        return new TransmutationRecipeResult(id);
    }

    public class TransmutationRecipeResult extends RecipeResult
    {
        protected TransmutationRecipeResult(ResourceLocation id)
        {
            super(id);
        }


        @Override
        public void serializeRecipeData(@Nonnull JsonObject json)
        {
            json.add("ingredient", ingredient.toJson());

            json.add("result", SerializerHelper.serializeItemStack(result));
            json.add("experience", SerializerHelper.serializefloats("value",experience));
            json.add("cookingtime", SerializerHelper.serializeints("value", cookTime));
            json.add("count", SerializerHelper.serializeints("value", count));
        }
    }
}
