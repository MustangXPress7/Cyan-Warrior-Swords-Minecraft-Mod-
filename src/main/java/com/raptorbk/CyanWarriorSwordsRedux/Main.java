package com.raptorbk.CyanWarriorSwordsRedux;

import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.proxy.CommonProxy;
import com.raptorbk.CyanWarriorSwordsRedux.util.Reference;
import com.raptorbk.CyanWarriorSwordsRedux.util.handlers.RegistryHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version=Reference.VERSION)
public class Main {


    public static Configuration config;

    @Mod.Instance
    public  static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event)
    {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "cwsr_config.cfg"));
        CwsrConfig.readConfig();
    }


    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries(event);
    }


    @Mod.EventHandler
    public static void Postinit(FMLPostInitializationEvent event)
    {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
