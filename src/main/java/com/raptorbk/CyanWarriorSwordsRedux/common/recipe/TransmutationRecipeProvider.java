package com.raptorbk.CyanWarriorSwordsRedux.common.recipe;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.common.recipe.builder.TransmutationRecipeBuilder;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class TransmutationRecipeProvider implements ISubRecipeProvider{

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer){
        String basePath = "transmutation/";

        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.beast_SWORD.get()), new ItemStack(RegistryHandler.beast_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"beast_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.golem_SWORD.get()), new ItemStack(RegistryHandler.beast_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"beast_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.dark_SWORD.get()), new ItemStack(RegistryHandler.dark_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"dark_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.dark_NETHER.get()), new ItemStack(RegistryHandler.dark_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"dark_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.earth_SWORD.get()), new ItemStack(RegistryHandler.earth_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"earth_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.wild_NATURE.get()), new ItemStack(RegistryHandler.earth_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"earth_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.ender_SWORD.get()), new ItemStack(RegistryHandler.ender_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"ender_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.ender_PORTAL.get()), new ItemStack(RegistryHandler.ender_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"ender_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.fire_SWORD.get()), new ItemStack(RegistryHandler.fire_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"fire_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.combustion_SWORD.get()), new ItemStack(RegistryHandler.fire_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"fire_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.light_SWORD.get()), new ItemStack(RegistryHandler.light_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"light_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.light_NETHER.get()), new ItemStack(RegistryHandler.light_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"light_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.thunder_SWORD.get()), new ItemStack(RegistryHandler.thunder_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"thunder_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.thunder_SHOCK.get()), new ItemStack(RegistryHandler.thunder_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"thunder_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.water_SWORD.get()), new ItemStack(RegistryHandler.water_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"water_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.ice_SWORD.get()), new ItemStack(RegistryHandler.water_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"water_essence_smelt_o2"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.wind_SWORD.get()), new ItemStack(RegistryHandler.wind_ESSENCE.get()), 1,1,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"wind_essence_smelt_o1"));
        TransmutationRecipeBuilder.transmutation(Ingredient.of(RegistryHandler.wind_IMPULSE.get()), new ItemStack(RegistryHandler.wind_ESSENCE.get()), 1,2,1000).build(consumer, CyanWarriorSwordsReduxMod.rl(basePath+"wind_essence_smelt_o2"));


    }
}