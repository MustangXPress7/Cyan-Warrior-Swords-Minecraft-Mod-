package com.raptorbk.CyanWarriorSwordsRedux.events.loot.lootcondition;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootConditions {

    protected static LootItemConditionType FROM_LOOT_TABLE;


    public static void register() {
        FROM_LOOT_TABLE = Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE,
                new ResourceLocation(CyanWarriorSwordsReduxMod.MOD_ID, "from_loot_table"),

                new LootItemConditionType(new FromLootTable.Serializer()));

    }
}
