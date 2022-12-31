package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class TransmutationRecipeSerializer<T extends TransmutationRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final int cookingtime;
    private final TransmutationRecipeSerializer.IFactory<T> iBlastRecipeFactory;

    public TransmutationRecipeSerializer(TransmutationRecipeSerializer.IFactory<T> factoryIn, int cookingtimeIn) {
        this.cookingtime = cookingtimeIn;
        this.iBlastRecipeFactory = factoryIn;
    }

    /**
     * TODO
     * Change "Ingredient" to json object containing IngredientName and Count
     */


    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String group = JSONUtils.getAsString(json, "group", "");
        JsonElement jsonelement = (JSONUtils.isArrayNode(json, "ingredient") ? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient"));
        Ingredient ingredient = Ingredient.fromJson(jsonelement);


        JsonObject resultJson= json.getAsJsonObject("result");
        String result = JSONUtils.getAsString(resultJson, "item");

        ResourceLocation resourcelocation = new ResourceLocation(result);
        ItemStack itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));

        JsonObject experienceJson= json.getAsJsonObject("experience");
        float experience = JSONUtils.getAsFloat(experienceJson, "value", 0.0F);

        JsonObject cookingtimeJson= json.getAsJsonObject("cookingtime");
        int cookingtime = JSONUtils.getAsInt(cookingtimeJson, "value", this.cookingtime);

        JsonObject countJson= json.getAsJsonObject("count");
        int count = JSONUtils.getAsInt(countJson, "value", 1);

        //Se establece la cantidad de Items que se producen en el resultado
        itemstack.setCount(count);

        return this.iBlastRecipeFactory.create(recipeId, group, ingredient, itemstack, experience, cookingtime);
    }

    @Override
    public T fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.iBlastRecipeFactory.create(recipeId, s, ingredient, itemstack, f, i);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, T recipe) {
        buffer.writeUtf(recipe.tab);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItemStack(recipe.result,false);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public interface IFactory<T extends TransmutationRecipe> {
        T create(ResourceLocation resourceLocation, String s, Ingredient ingredient, ItemStack itemStack, float experience, int cookingtime);
    }
}