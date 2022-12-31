package com.raptorbk.CyanWarriorSwordsRedux.recipes;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class serializerInit{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, CyanWarriorSwordsReduxMod.MOD_ID);
    public static final RegistryObject<RecipeSerializer<?>> TRANSMUTATION_RECIPE = RECIPE_SERIALIZERS.register("transmutation", () -> new TransmutationRecipeSerializer<>(TransmutationRecipe::new,200));


    /*
    @SubscribeEvent
    public static void onRecipeSerializerRegistry(final RegistryEvent.Register<RecipeSerializer<?>> registryEvent) {
        registryEvent.getRegistry().registerAll(
                new TransmutationRecipeSerializer<>(TransmutationRecipe::new, 200).setRegistryName(CyanWarriorSwordsReduxMod.MOD_ID, "transmutation")
        );
    }*/

}