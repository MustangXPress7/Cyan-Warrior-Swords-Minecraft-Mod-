package com.raptorbk.CyanWarriorSwordsRedux;



import com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace.*;
import com.raptorbk.CyanWarriorSwordsRedux.config.Config;
import com.raptorbk.CyanWarriorSwordsRedux.config.ItemConfig;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.screens.TransmutationFurnaceScreen;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.util.ModMenus;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.gui.screens.MenuScreens;

import com.raptorbk.CyanWarriorSwordsRedux.recipes.recipeInit;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




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



       TransmutationFurnaceBlocks.BLOCKS.register(modEventBus);

        CyanWarriorSwordsRecipeType.RECIPE_SERIALIZERS.register(modEventBus);
        recipeInit.RECIPE_SERIALIZERS.register(modEventBus);

       TransmutationFurnaceTileEntities.TILE_ENTITIES.register(modEventBus);

       ModItems.ITEMS.register(modEventBus);


        ModMenus.MENUS.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON, "cwsr-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT, "cwsr-client.toml");


        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistryHandler::registerCreativeModeTab);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistryHandler::addToCreativeModeTabs);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);



        Config.loadConfig(Config.CLIENT, FMLPaths.CONFIGDIR.get().resolve("cwsr-client.toml").toString());
        Config.loadConfig(Config.COMMON, FMLPaths.CONFIGDIR.get().resolve("cwsr-common.toml").toString());




        RegistryHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.LOOT_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }










    private void doClientStuff(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenus.TRANSMUTATION.get(), TransmutationFurnaceScreen::new);
    }

    public static ResourceLocation rl(String name)
    {
        return new ResourceLocation(CyanWarriorSwordsReduxMod.MOD_ID, name);
    }



}
