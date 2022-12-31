package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class serializerInit{

    @SubscribeEvent
    public static void onRecipeSerializerRegistry(final RegistryEvent.Register<RecipeSerializer<?>> registryEvent) {
        registryEvent.getRegistry().registerAll(
                new TransmutationRecipeSerializer<>(TransmutationRecipe::new, 200).setRegistryName(CyanWarriorSwordsReduxMod.MOD_ID, "transmutation")
        );
    }

}