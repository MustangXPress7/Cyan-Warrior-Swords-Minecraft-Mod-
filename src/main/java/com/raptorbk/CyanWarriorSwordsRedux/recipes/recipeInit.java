package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class recipeInit{

    @SubscribeEvent
    public static void onRecipeSerializerRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> registryEvent) {
        registryEvent.getRegistry().registerAll(
                new TransmutationRecipeSerializer<>(TransmutationRecipe::new, 200).setRegistryName(CyanWarriorSwordsReduxMod.MOD_ID, "transmutation")
        );
    }

}