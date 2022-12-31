package com.raptorbk.CyanWarriorSwordsRedux.init;

import com.raptorbk.CyanWarriorSwordsRedux.client.CwsrConfig;
import com.raptorbk.CyanWarriorSwordsRedux.items.essences.EssenceBase;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.BeastType.BEAST_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.BeastType.GOLEM_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.CyanType.CYAN_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.DarkType.DARK_NETHER;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.DarkType.DARK_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.EarthType.EARTH_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.EarthType.WILD_NATURE;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.EnderType.ENDER_PORTAL;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.EnderType.ENDER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.FireType.COMBUSTION_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.FireType.FIRE_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.FireType.METEOR_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.LightType.LIGHT_NETHER;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.LightType.LIGHT_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.Mixing.*;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.ThunderType.THUNDER_SHOCK;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.ThunderType.THUNDER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.WaterType.ICE_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.WaterType.WATER_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.WindType.WIND_IMPULSE;
import com.raptorbk.CyanWarriorSwordsRedux.items.swords.WindType.WIND_SWORD;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.ABILITY_TOTEM;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.ACTIVE_SYNERGY_TOTEM;
import com.raptorbk.CyanWarriorSwordsRedux.items.totems.SYNERGY_TOTEM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<>();


    public static final CreativeTabs cyanWarriorTab = (new CreativeTabs("tabTutorialMod") {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(cyan_SWORD);
        }

    });


    //Materials
    public static final Item.ToolMaterial MATERIAL_BEAST_SWORD = EnumHelper.addToolMaterial("material_beast_sword", 3, CwsrConfig.BEAST_SWORD_DUR, 10.0F, CwsrConfig.BEAST_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_GOLEM_SWORD = EnumHelper.addToolMaterial("material_golem_sword", 3, CwsrConfig.GOLEM_SWORD_DUR, 10.0F, CwsrConfig.GOLEM_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_CYAN_SWORD = EnumHelper.addToolMaterial("material_cyan_sword", 3, CwsrConfig.CYAN_SWORD_DUR, 10.0F, CwsrConfig.CYAN_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_DARK_NETHER = EnumHelper.addToolMaterial("material_dark_nether", 3, CwsrConfig.DARK_NETHER_SWORD_DUR, 10.0F, CwsrConfig.DARK_NETHER_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_DARK_SWORD = EnumHelper.addToolMaterial("material_dark_sword", 3, CwsrConfig.DARK_SWORD_DUR, 10.0F, CwsrConfig.DARK_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_EARTH_SWORD = EnumHelper.addToolMaterial("material_earth_sword", 3, CwsrConfig.EARTH_SWORD_DUR, 10.0F, CwsrConfig.EARTH_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WILD_NATURE = EnumHelper.addToolMaterial("material_wild_nature", 3, CwsrConfig.WILD_NATURE_SWORD_DUR, 10.0F, CwsrConfig.WILD_NATURE_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ENDER_PORTAL = EnumHelper.addToolMaterial("material_ender_portal", 3, CwsrConfig.ENDER_PORTAL_SWORD_DUR, 10.0F, CwsrConfig.ENDER_PORTAL_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ENDER_SWORD = EnumHelper.addToolMaterial("material_ender_sword", 3, CwsrConfig.ENDER_SWORD_DUR, 10.0F, CwsrConfig.ENDER_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_COMBUSTION_SWORD = EnumHelper.addToolMaterial("material_combustion_sword", 3, CwsrConfig.COMBUSTION_SWORD_DUR, 10.0F, CwsrConfig.COMBUSTION_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_FIRE_SWORD = EnumHelper.addToolMaterial("material_fire_sword", 3, CwsrConfig.FIRE_SWORD_DUR, 10.0F, CwsrConfig.FIRE_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_METEOR_SWORD = EnumHelper.addToolMaterial("material_meteor_sword", 3, CwsrConfig.METEOR_SWORD_DUR, 10.0F, CwsrConfig.METEOR_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_LIGHT_NETHER = EnumHelper.addToolMaterial("material_light_nether", 3, CwsrConfig.LIGHT_SWORD_DUR, 10.0F, CwsrConfig.LIGHT_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_LIGHT_SWORD = EnumHelper.addToolMaterial("material_light_sword", 3, CwsrConfig.LIGHT_SWORD_DUR, 10.0F, CwsrConfig.LIGHT_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ENDER_FIRE = EnumHelper.addToolMaterial("material_ender_fire", 3, CwsrConfig.ENDER_FIRE_DUR, 10.0F, CwsrConfig.ENDER_FIRE_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ENDER_THUNDER = EnumHelper.addToolMaterial("material_ender_thunder", 3, CwsrConfig.ENDER_THUNDER_DUR, 10.0F, CwsrConfig.ENDER_THUNDER_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ENDER_WIND = EnumHelper.addToolMaterial("material_ender_wind", 3, CwsrConfig.ENDER_WIND_DUR, 10.0F, CwsrConfig.ENDER_WIND_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_METEORIC_THUNDERSTORM = EnumHelper.addToolMaterial("material_meteoric_thunderstorm", 3, CwsrConfig.METEORIC_THUNDERSTORM_DUR, 10.0F, CwsrConfig.METEORIC_THUNDERSTORM_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_PEACEFUL_NATURE = EnumHelper.addToolMaterial("material_peaceful_nature", 3, CwsrConfig.PEACEFUL_NATURE_DUR, 10.0F, CwsrConfig.PEACEFUL_NATURE_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_STEAM_SWORD = EnumHelper.addToolMaterial("material_steam_sword", 3, CwsrConfig.STEAM_SWORD_DUR, 10.0F, CwsrConfig.STEAM_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_THUNDERSTORM_SWORD = EnumHelper.addToolMaterial("material_thunderstorm_sword", 3, CwsrConfig.THUNDERSTORM_SWORD_DUR, 10.0F, CwsrConfig.THUNDERSTORM_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_TIME_SWORD = EnumHelper.addToolMaterial("material_time_sword", 3, CwsrConfig.TIME_SWORD_DUR, 10.0F, CwsrConfig.TIME_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_TRI_ENDER = EnumHelper.addToolMaterial("material_tri_ender", 3, CwsrConfig.TRI_ENDER_DUR, 10.0F, CwsrConfig.TRI_ENDER_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WIND_BLAST = EnumHelper.addToolMaterial("material_wind_blast", 3, CwsrConfig.WIND_BLAST_DUR, 10.0F, CwsrConfig.WIND_BLAST_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WIND_BOOM = EnumHelper.addToolMaterial("material_wind_boom", 3, CwsrConfig.WIND_BOOM_DUR, 10.0F, CwsrConfig.WIND_BOOM_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_THUNDER_SHOCK = EnumHelper.addToolMaterial("material_thunder_shock", 3, CwsrConfig.THUNDER_SHOCK_SWORD_DUR, 10.0F, CwsrConfig.THUNDER_SHOCK_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_THUNDER_SWORD = EnumHelper.addToolMaterial("material_thunder_sword", 3, CwsrConfig.THUNDER_SWORD_DUR, 10.0F, CwsrConfig.THUNDER_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_ICE_SWORD = EnumHelper.addToolMaterial("material_ice_sword", 3, CwsrConfig.ICE_SWORD_DUR, 10.0F, CwsrConfig.ICE_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WATER_SWORD = EnumHelper.addToolMaterial("material_water_sword", 3, CwsrConfig.WATER_SWORD_DUR, 10.0F, CwsrConfig.WATER_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WIND_IMPULSE = EnumHelper.addToolMaterial("material_wind_impulse", 3, CwsrConfig.WIND_IMPULSE_SWORD_DUR, 10.0F, CwsrConfig.WIND_IMPULSE_SWORD_DMG+1, 10);

    public static final Item.ToolMaterial MATERIAL_WIND_SWORD = EnumHelper.addToolMaterial("material_wind_sword", 3, CwsrConfig.WIND_SWORD_DUR, 10.0F, CwsrConfig.WIND_SWORD_DMG+1, 10);




    //Totems

    public static final Item SynergyTotem = new SYNERGY_TOTEM("synergy_totem");

    public static final Item ActiveSynergyTotem = new ACTIVE_SYNERGY_TOTEM("active_synergy_totem");

    public static final Item AbilityTotem = new ABILITY_TOTEM("ability_totem");

    //Essences

    public static final Item beast_ESSENCE = new EssenceBase("beast_essence");

    public static final Item dark_ESSENCE = new EssenceBase("dark_essence");

    public static final Item earth_ESSENCE = new EssenceBase("earth_essence");

    public static final Item ender_ESSENCE = new EssenceBase("ender_essence");

    public static final Item fire_ESSENCE = new EssenceBase("fire_essence");

    public static final Item light_ESSENCE = new EssenceBase("light_essence");

    public static final Item mixed_ESSENCE = new EssenceBase("mixed_essence");

    public static final Item thunder_ESSENCE = new EssenceBase("thunder_essence");

    public static final Item water_ESSENCE = new EssenceBase("water_essence");

    public static final Item wind_ESSENCE = new EssenceBase("sword_handle");

    //Misc

    public static final Item sword_HANDLE = new EssenceBase("wind_essence");

    //Swords
    public static final ItemSword beast_SWORD = new BEAST_SWORD("beast_sword",MATERIAL_BEAST_SWORD);

    public static final ItemSword golem_SWORD = new GOLEM_SWORD("golem_sword",MATERIAL_GOLEM_SWORD);

    public static final ItemSword cyan_SWORD = new CYAN_SWORD("cyan_sword",MATERIAL_CYAN_SWORD);

    public static final ItemSword dark_SWORD = new DARK_SWORD("dark_sword",MATERIAL_DARK_SWORD);

    public static final ItemSword dark_NETHER = new DARK_NETHER("dark_nether",MATERIAL_DARK_NETHER);

    public static final ItemSword earth_SWORD = new EARTH_SWORD("earth_sword",MATERIAL_EARTH_SWORD);

    public static final ItemSword wild_NATURE = new WILD_NATURE("wild_nature",MATERIAL_WILD_NATURE);

    public static final ItemSword ender_PORTAL = new ENDER_PORTAL("ender_portal",MATERIAL_ENDER_PORTAL);

    public static final ItemSword ender_SWORD = new ENDER_SWORD("ender_sword",MATERIAL_ENDER_SWORD);

    public static final ItemSword combustion_SWORD = new COMBUSTION_SWORD("combustion_sword",MATERIAL_COMBUSTION_SWORD);

    public static final ItemSword fire_SWORD = new FIRE_SWORD("fire_sword",MATERIAL_FIRE_SWORD);

    public static final ItemSword meteor_SWORD = new METEOR_SWORD("meteor_sword",MATERIAL_METEOR_SWORD);

    public static final ItemSword light_NETHER = new LIGHT_NETHER("light_nether",MATERIAL_LIGHT_NETHER);

    public static final ItemSword light_SWORD = new LIGHT_SWORD("light_sword",MATERIAL_LIGHT_SWORD);

    public static final ItemSword ender_FIRE = new ENDER_FIRE("ender_fire",MATERIAL_ENDER_FIRE);

    public static final ItemSword ender_THUNDER = new ENDER_THUNDER("ender_thunder",MATERIAL_ENDER_THUNDER);

    public static final ItemSword ender_WIND = new ENDER_WIND("ender_wind",MATERIAL_ENDER_WIND);

    public static final ItemSword meteoric_THUNDERSTORM = new METEORIC_THUNDERSTORM("meteoric_thunderstorm",MATERIAL_METEORIC_THUNDERSTORM);

    public static final ItemSword peaceful_NATURE = new PEACEFUL_NATURE("peaceful_nature",MATERIAL_PEACEFUL_NATURE);

    public static final ItemSword steam_SWORD = new STEAM_SWORD("steam_sword",MATERIAL_STEAM_SWORD);

    public static final ItemSword thunderstorm_SWORD = new THUNDERSTORM_SWORD("thunderstorm_sword",MATERIAL_THUNDERSTORM_SWORD);

    public static final ItemSword time_SWORD = new TIME_SWORD("time_sword",MATERIAL_TIME_SWORD);

    public static final ItemSword tri_ENDER = new TRI_ENDER("tri_ender",MATERIAL_TRI_ENDER);

    public static final ItemSword wind_BLAST = new WIND_BLAST("wind_blast",MATERIAL_WIND_BLAST);

    public static final ItemSword wind_BOOM = new WIND_BOOM("wind_boom",MATERIAL_WIND_BOOM);

    public static final ItemSword thunder_SHOCK = new THUNDER_SHOCK("thunder_shock",MATERIAL_THUNDER_SHOCK);

    public static final ItemSword thunder_SWORD = new THUNDER_SWORD("thunder_sword",MATERIAL_THUNDER_SWORD);

    public static final ItemSword ice_SWORD = new ICE_SWORD("ice_sword",MATERIAL_ICE_SWORD);

    public static final ItemSword water_SWORD = new WATER_SWORD("water_sword",MATERIAL_WATER_SWORD);

    public static final ItemSword wind_IMPULSE = new WIND_IMPULSE("wind_impulse",MATERIAL_WIND_IMPULSE);

    public static final ItemSword wind_SWORD = new WIND_SWORD("wind_sword",MATERIAL_WIND_SWORD);












}
