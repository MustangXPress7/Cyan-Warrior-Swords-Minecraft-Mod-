package com.raptorbk.CyanWarriorSwordsRedux.events.loot.lootcondition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class FromLootTable implements LootItemCondition {

    private ResourceLocation table;

    public FromLootTable(ResourceLocation table) {
        this.table = table;
    }

    @Override
    public boolean test(LootContext t) {
        return t.getQueriedLootTableId().equals(table);
    }

    @Override
    public LootItemConditionType getType() {
        return LootConditions.FROM_LOOT_TABLE;
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<FromLootTable> {
        public void serialize(JsonObject json, FromLootTable instance, JsonSerializationContext context) {
            json.addProperty("loot_table", instance.table.toString());
        }

        public FromLootTable deserialize(JsonObject json, JsonDeserializationContext context) {
            ResourceLocation table = new ResourceLocation(GsonHelper.getAsString(json, "loot_table"));
            return new FromLootTable(table);
        }
    }

}

