package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class TransmutationRecipeSerializer<T extends TransmutationRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final int cookingTime;
    private final TransmutationRecipeSerializer.IFactory<T> iBlastRecipeFactory;

    public TransmutationRecipeSerializer(TransmutationRecipeSerializer.IFactory<T> factoryIn, int cookingTimeIn) {
        this.cookingTime = cookingTimeIn;
        this.iBlastRecipeFactory = factoryIn;
    }

    /**
     * TODO
     * Change "Ingredient" to json object containing IngredientName and Count
     */


    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        JsonElement jsonelement = (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
        Ingredient ingredient = Ingredient.fromJson(jsonelement);


        JsonObject resultJson= json.getAsJsonObject("result");
        String result = GsonHelper.getAsString(resultJson, "item");

        ResourceLocation resourcelocation = new ResourceLocation(result);
        ItemStack itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));

        JsonObject experienceJson= json.getAsJsonObject("experience");
        float experience = GsonHelper.getAsFloat(experienceJson, "value", 0.0F);

        JsonObject cookingtimeJson= json.getAsJsonObject("cookingtime");
        int cookingtime = GsonHelper.getAsInt(cookingtimeJson, "value", this.cookingTime);

        JsonObject countJson= json.getAsJsonObject("count");
        int count = GsonHelper.getAsInt(countJson, "value", 1);

        //Se establece la cantidad de Items que se producen en el resultado
        itemstack.setCount(count);

        return this.iBlastRecipeFactory.create(recipeId, group, ingredient, itemstack, experience, cookingtime);
    }

    /*
    @Nullable
    @Override
    public T fromNetwork(ResourceLocation p_199426_1_, FriendlyByteBuf p_199426_2_) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf p_199427_1_, T p_199427_2_) {

    }*/


    @Override
    public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.iBlastRecipeFactory.create(recipeId, s, ingredient, itemstack, f, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.tab);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public interface IFactory<T extends TransmutationRecipe> {
        T create(ResourceLocation resourceLocation, String s, Ingredient ingredient, ItemStack itemStack, float experience, int cookingTime);
    }
}