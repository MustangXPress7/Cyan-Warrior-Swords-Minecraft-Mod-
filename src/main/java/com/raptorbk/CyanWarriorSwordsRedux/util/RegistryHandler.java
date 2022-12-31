package com.raptorbk.CyanWarriorSwordsRedux.util;

import com.raptorbk.CyanWarriorSwordsRedux.*;
import com.raptorbk.CyanWarriorSwordsRedux.essences.*;
import com.raptorbk.CyanWarriorSwordsRedux.items.SwordHandle;
import com.raptorbk.CyanWarriorSwordsRedux.swords.BeastType.BEAST_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.BeastType.GOLEM_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.CyanType.CYAN_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.DarkType.DARK_NETHER;
import com.raptorbk.CyanWarriorSwordsRedux.swords.DarkType.DARK_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.EarthType.EARTH_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.EarthType.WILD_NATURE;
import com.raptorbk.CyanWarriorSwordsRedux.swords.EnderType.ENDER_PORTAL;
import com.raptorbk.CyanWarriorSwordsRedux.swords.EnderType.ENDER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.FireType.COMBUSTION_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.FireType.FIRE_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.FireType.METEOR_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.LightType.LIGHT_NETHER;
import com.raptorbk.CyanWarriorSwordsRedux.swords.LightType.LIGHT_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing.*;
import com.raptorbk.CyanWarriorSwordsRedux.swords.ThunderType.THUNDER_SHOCK;
import com.raptorbk.CyanWarriorSwordsRedux.swords.ThunderType.THUNDER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.WaterType.ICE_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.WaterType.WATER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.swords.WindType.WIND_IMPULSE;
import com.raptorbk.CyanWarriorSwordsRedux.swords.WindType.WIND_SWORD;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CyanWarriorSwordsReduxMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyanWarriorSwordsReduxMod.MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CyanWarriorSwordsReduxMod.MOD_ID);

    public static void init() {

        IEventBus MOD_EVENT_BUS;
        MOD_EVENT_BUS=FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }



    // Items
    public static final RegistryObject<Item> beast_ESSENCE = ITEMS.register("beast_essence", BeastEssence::new);

    public static final RegistryObject<Item> dark_ESSENCE = ITEMS.register("dark_essence", DarkEssence::new);

    public static final RegistryObject<Item> earth_ESSENCE = ITEMS.register("earth_essence", EarthEssence::new);

    public static final RegistryObject<Item> ender_ESSENCE = ITEMS.register("ender_essence", EnderEssence::new);

    public static final RegistryObject<Item> fire_ESSENCE = ITEMS.register("fire_essence", FireEssence::new);

    public static final RegistryObject<Item> light_ESSENCE = ITEMS.register("light_essence", LightEssence::new);

    public static final RegistryObject<Item> mixed_ESSENCE = ITEMS.register("mixed_essence", MixedEssence::new);

    public static final RegistryObject<Item> thunder_ESSENCE = ITEMS.register("thunder_essence", ThunderEssence::new);

    public static final RegistryObject<Item> water_ESSENCE = ITEMS.register("water_essence", WaterEssence::new);

    public static final RegistryObject<Item> wind_ESSENCE = ITEMS.register("wind_essence", WindEssence::new);

    public static final RegistryObject<Item> sword_HANDLE = ITEMS.register("sword_handle", SwordHandle::new);

    public static final RegistryObject<Item> synergy_TOTEM = ITEMS.register("synergy_totem", SYNERGY_TOTEM::new);

    public static final RegistryObject<Item> active_synergy_TOTEM = ITEMS.register("active_synergy_totem", ACTIVE_SYNERGY_TOTEM::new);

    public static final RegistryObject<Item> ability_TOTEM = ITEMS.register("ability_totem", ABILITY_TOTEM::new);

    //ENCHANTMENTS

    public static final RegistryObject<Enchantment> dw_ENCHANTMENT = ENCHANTMENTS.register("dw_enchantment", DW_ENCHANTMENT::new);

    public static final RegistryObject<Enchantment> inh_ENCHANTMENT = ENCHANTMENTS.register("inh_enchantment", INH_ENCHANTMENT::new);


    // Swords
    public static RegistryObject<SwordItem> earth_SWORD = ITEMS.register("earth_sword", () -> {
        return new EARTH_SWORD();
    });

    public static RegistryObject<SwordItem> peaceful_NATURE = ITEMS.register("peaceful_nature", () -> {
        return new PEACEFUL_NATURE();
    });

    public static RegistryObject<SwordItem> wild_NATURE = ITEMS.register("wild_nature", () -> {
        return new WILD_NATURE();
    });

    public static RegistryObject<SwordItem> water_SWORD = ITEMS.register("water_sword", () -> {
        return new WATER_SWORD();
    });

    public static RegistryObject<SwordItem> fire_SWORD = ITEMS.register("fire_sword", () -> {
        return new FIRE_SWORD();
    });

    public static RegistryObject<SwordItem> thunder_SWORD = ITEMS.register("thunder_sword", () -> {
        return new THUNDER_SWORD();
    });

    public static RegistryObject<SwordItem> wind_SWORD = ITEMS.register("wind_sword", () -> {
        return new WIND_SWORD();
    });

    public static RegistryObject<SwordItem> light_SWORD = ITEMS.register("light_sword", () -> {
        return new LIGHT_SWORD();
    });

    public static RegistryObject<SwordItem> dark_SWORD = ITEMS.register("dark_sword", () -> {
        return new DARK_SWORD();
    });

    public static RegistryObject<SwordItem> ender_SWORD = ITEMS.register("ender_sword", () -> {
        return new ENDER_SWORD();
    });

    public static RegistryObject<SwordItem> beast_SWORD = ITEMS.register("beast_sword", () -> {
        return new BEAST_SWORD();
    });

    public static RegistryObject<SwordItem> meteor_SWORD = ITEMS.register("meteor_sword", () -> {
        return new METEOR_SWORD();
    });

    public static RegistryObject<SwordItem> combustion_SWORD = ITEMS.register("combustion_sword", () -> {
        return new COMBUSTION_SWORD();
    });

    public static RegistryObject<SwordItem> ice_SWORD = ITEMS.register("ice_sword", () -> {
        return new ICE_SWORD();
    });

    public static RegistryObject<SwordItem> thunder_SHOCK = ITEMS.register("thunder_shock", () -> {
        return new THUNDER_SHOCK();
    });

    public static RegistryObject<SwordItem> wind_BOOM = ITEMS.register("wind_boom", () -> {
        return new WIND_BOOM();
    });

    public static RegistryObject<SwordItem> dark_NETHER = ITEMS.register("dark_nether", () -> {
        return new DARK_NETHER();
    });

    public static RegistryObject<SwordItem> light_NETHER = ITEMS.register("light_nether", () -> {
        return new LIGHT_NETHER();
    });

    public static RegistryObject<SwordItem> ender_PORTAL = ITEMS.register("ender_portal", () -> {
        return new ENDER_PORTAL();
    });

    public static RegistryObject<SwordItem> golem_SWORD = ITEMS.register("golem_sword", () -> {
        return new GOLEM_SWORD();
    });

    public static RegistryObject<SwordItem> wind_IMPULSE = ITEMS.register("wind_impulse", () -> {
        return new WIND_IMPULSE();
    });

    public static RegistryObject<SwordItem> thunderstorm_SWORD = ITEMS.register("thunderstorm_sword", () -> {
        return new THUNDERSTORM_SWORD();
    });

    public static RegistryObject<SwordItem> meteoric_THUNDERSTORM = ITEMS.register("meteoric_thunderstorm", () -> {
        return new METEORIC_THUNDERSTORM();
    });

    public static RegistryObject<SwordItem> ender_FIRE = ITEMS.register("ender_fire", () -> {
        return new ENDER_FIRE();
    });

    public static RegistryObject<SwordItem> ender_WIND = ITEMS.register("ender_wind", () -> {
        return new ENDER_WIND();
    });

    public static RegistryObject<SwordItem> ender_THUNDER = ITEMS.register("ender_thunder", () -> {
        return new ENDER_THUNDER();
    });

    public static RegistryObject<SwordItem> time_SWORD = ITEMS.register("time_sword", () -> {
        return new TIME_SWORD();
    });

    public static RegistryObject<SwordItem> steam_SWORD = ITEMS.register("steam_sword", () -> {
        return new STEAM_SWORD();
    });

    public static RegistryObject<SwordItem> tri_ENDER = ITEMS.register("tri_ender", () -> {
        return new TRI_ENDER();
    });

    public static RegistryObject<SwordItem> cyan_SWORD = ITEMS.register("cyan_sword", () -> {
        return new CYAN_SWORD();
    });

    public static RegistryObject<SwordItem> atlantis_SWORD = ITEMS.register("atlantis_sword", () -> {
        return new ATLANTIS_SWORD();
    });

    public static RegistryObject<SwordItem> wind_BLAST = ITEMS.register("wind_blast", () -> {
        return new WIND_BLAST();
    });
}
