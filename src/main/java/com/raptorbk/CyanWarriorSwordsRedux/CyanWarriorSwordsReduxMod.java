package com.raptorbk.CyanWarriorSwordsRedux;


import com.google.common.collect.Ordering;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace.*;
import com.raptorbk.CyanWarriorSwordsRedux.config.Config;
import com.raptorbk.CyanWarriorSwordsRedux.config.ItemConfig;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.screens.TransmutationFurnaceScreen;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModMenus;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.NonNullList;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.recipeInit;
import net.minecraft.data.DataGenerator;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Mod("cwsr")
public class CyanWarriorSwordsReduxMod
{

    public  static  final String MOD_ID= "cwsr";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public CyanWarriorSwordsReduxMod() {
        if(!ItemConfig.initialized){
            ItemConfig.load();
        }
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;



       TransmutationFurnaceBlocks.BLOCKS.register(modEventBus);
        //ModLootModifiers.LOOT_SERIALIZERS.register(modEventBus);
        CyanWarriorSwordsRecipeType.RECIPE_SERIALIZERS.register(modEventBus);
        recipeInit.RECIPE_SERIALIZERS.register(modEventBus);

       TransmutationFurnaceTileEntities.TILE_ENTITIES.register(modEventBus);

       ModItems.ITEMS.register(modEventBus);
        //serializerInit.SERIALIZERS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON, "cwsr-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT, "cwsr-client.toml");

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);



        Config.loadConfig(Config.CLIENT, FMLPaths.CONFIGDIR.get().resolve("cwsr-client.toml").toString());
        Config.loadConfig(Config.COMMON, FMLPaths.CONFIGDIR.get().resolve("cwsr-common.toml").toString());




        RegistryHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.LOOT_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    static Comparator<ItemStack> tabSorter;

    private void setup(final FMLCommonSetupEvent event) {
        List<Item> sortedItemList = Arrays.asList(
                RegistryHandler.fire_SWORD.get(),
                RegistryHandler.water_SWORD.get(),
                RegistryHandler.earth_SWORD.get(),
                RegistryHandler.wind_SWORD.get(),
                RegistryHandler.thunder_SWORD.get(),
                RegistryHandler.dark_SWORD.get(),
                RegistryHandler.light_SWORD.get(),
                RegistryHandler.ender_SWORD.get(),
                RegistryHandler.beast_SWORD.get(),
                RegistryHandler.combustion_SWORD.get(),
                RegistryHandler.ice_SWORD.get(),
                RegistryHandler.wild_NATURE.get(),
                RegistryHandler.wind_IMPULSE.get(),
                RegistryHandler.thunder_SHOCK.get(),
                RegistryHandler.dark_NETHER.get(),
                RegistryHandler.light_NETHER.get(),
                RegistryHandler.ender_PORTAL.get(),
                RegistryHandler.golem_SWORD.get(),
                RegistryHandler.ender_FIRE.get(),
                RegistryHandler.ender_WIND.get(),
                RegistryHandler.ender_THUNDER.get(),
                RegistryHandler.peaceful_NATURE.get(),
                RegistryHandler.time_SWORD.get(),
                RegistryHandler.steam_SWORD.get(),
                RegistryHandler.meteor_SWORD.get(),
                RegistryHandler.wind_BLAST.get(),
                RegistryHandler.wind_BOOM.get(),
                RegistryHandler.thunderstorm_SWORD.get(),
                RegistryHandler.meteoric_THUNDERSTORM.get(),
                RegistryHandler.tri_ENDER.get(),
                RegistryHandler.atlantis_SWORD.get(),
                RegistryHandler.cyan_SWORD.get(),
                TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.get().asItem(),
                RegistryHandler.earth_ESSENCE.get(),
                RegistryHandler.beast_ESSENCE.get(),
                RegistryHandler.dark_ESSENCE.get(),
                RegistryHandler.ender_ESSENCE.get(),
                RegistryHandler.fire_ESSENCE.get(),
                RegistryHandler.thunder_ESSENCE.get(),
                RegistryHandler.water_ESSENCE.get(),
                RegistryHandler.wind_ESSENCE.get(),
                RegistryHandler.light_ESSENCE.get(),
                RegistryHandler.mixed_ESSENCE.get(),
                RegistryHandler.sword_HANDLE.get(),
                RegistryHandler.ability_TOTEM.get(),
                RegistryHandler.synergy_TOTEM.get(),
                RegistryHandler.active_synergy_TOTEM.get()
        );
        tabSorter = Ordering.explicit(sortedItemList).onResultOf(ItemStack::getItem);


    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenus.TRANSMUTATION.get(), TransmutationFurnaceScreen::new);
    }

    public static ResourceLocation rl(String name)
    {
        return new ResourceLocation(CyanWarriorSwordsReduxMod.MOD_ID, name);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        /*
        gen.addProvider(event.includeServer(),new CyanWarriorSwordsRecipesProvider(gen));
        gen.addProvider(event.includeServer(),new ModLootModifierProvider(gen));*/
    }




    public static final CreativeModeTab TAB = new CreativeModeTab("cwsr") {




        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistryHandler.cyan_SWORD.get());
        }



        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);
            items.sort(tabSorter);
        }


    };
}
