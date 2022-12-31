package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class CyanWarriorSwordsRecipeType {

    public static final DeferredRegister<RecipeType<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CyanWarriorSwordsReduxMod.MOD_ID);
    public static final RegistryObject<RecipeType<TransmutationRecipe>> TRANSMUTATION = RECIPE_SERIALIZERS.register("transmutation", () -> new RecipeType<>() {
    });

    //public static final RecipeType<TransmutationRecipe> TRANSMUTATION = RecipeType.register(CyanWarriorSwordsReduxMod.MOD_ID+":transmutation");

}
