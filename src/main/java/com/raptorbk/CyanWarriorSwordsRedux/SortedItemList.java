package com.raptorbk.CyanWarriorSwordsRedux;

import com.google.common.collect.Ordering;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn.TransmutationFurnaceBlocks;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortedItemList
{

    private static List<Item> sortedItemList = Arrays.asList(
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
            RegistryHandler.ability_TOTEM.get(),
            RegistryHandler.synergy_TOTEM.get(),
            RegistryHandler.active_synergy_TOTEM.get(),
            RegistryHandler.sword_HANDLE.get(),
            TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.asItem()
            );

    public static Comparator<ItemStack> tabSorter = Ordering.explicit(sortedItemList).onResultOf(ItemStack::getItem);
}