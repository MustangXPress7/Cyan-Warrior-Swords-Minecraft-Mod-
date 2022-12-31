package com.raptorbk.CyanWarriorSwordsRedux.generate;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.events.loot.EssencesStructureAdditionModifier;
import com.raptorbk.CyanWarriorSwordsRedux.events.loot.lootcondition.FromLootTable;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(DataGenerator gen) {
        super(gen, CyanWarriorSwordsReduxMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("beast_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_dungeon", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.SIMPLE_DUNGEON) }, RegistryHandler.wind_ESSENCE.get()));

        add("beast_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_jungle_temple", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.JUNGLE_TEMPLE) }, RegistryHandler.wind_ESSENCE.get()));

        add("beast_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_desert_pyramid", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.DESERT_PYRAMID) }, RegistryHandler.wind_ESSENCE.get()));



        add("beast_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_abandoned_mineshaft", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.ABANDONED_MINESHAFT) }, RegistryHandler.wind_ESSENCE.get()));



        add("beast_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_igloo_chest", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.IGLOO_CHEST) }, RegistryHandler.wind_ESSENCE.get()));

        add("beast_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.beast_ESSENCE.get()));
        add("dark_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.dark_ESSENCE.get()));
        add("earth_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.earth_ESSENCE.get()));
        add("ender_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.ender_ESSENCE.get()));
        add("fire_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.fire_ESSENCE.get()));
        add("light_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.light_ESSENCE.get()));
        add("thunder_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.thunder_ESSENCE.get()));
        add("water_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.water_ESSENCE.get()));
        add("wind_essence_nether_bridge", ModLootModifiers.ESSENCES_DUNGEON,
                new EssencesStructureAdditionModifier(new ILootCondition[] { RandomChance.randomChance(0.1f).build(),
                        new FromLootTable(LootTables.NETHER_BRIDGE) }, RegistryHandler.wind_ESSENCE.get()));

    }

}
