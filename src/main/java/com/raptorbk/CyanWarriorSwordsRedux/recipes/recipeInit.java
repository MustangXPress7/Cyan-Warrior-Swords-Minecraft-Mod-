package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class recipeInit{

    /*
    @SubscribeEvent
    public static void onRecipeSerializerRegistry(final RegistryEvent.Register<RecipeSerializer<?>> registryEvent) {
        registryEvent.getRegistry().registerAll(
                new TransmutationRecipeSerializer<>(TransmutationRecipe::new, 200).setRegistryName(CyanWarriorSwordsReduxMod.MOD_ID, "transmutation")
        );
    }*/



    public  static final RecipeType<TransmutationRecipe> TRANSMUTATION = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(CyanWarriorSwordsReduxMod.MOD_ID,"transmutation"),new RecipeType<TransmutationRecipe>(){
        @Override
        public String toString(){
            return "transmutation";
        }
    });

}