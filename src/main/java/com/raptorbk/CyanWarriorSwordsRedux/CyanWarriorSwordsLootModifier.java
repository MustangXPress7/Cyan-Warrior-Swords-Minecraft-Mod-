package com.raptorbk.CyanWarriorSwordsRedux;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.raptorbk.CyanWarriorSwordsRedux.config.ConfigValues;
import com.raptorbk.CyanWarriorSwordsRedux.config.ItemConfig;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CyanWarriorSwordsLootModifier extends LootModifier {

    private static final ResourceLocation MINESHAFT_CHEST_LOOT_TABLE_ID = BuiltInLootTables.ABANDONED_MINESHAFT;
    private static final ResourceLocation DUNGEON_CHEST_LOOT_TABLE_ID = BuiltInLootTables.SIMPLE_DUNGEON;
    private static final ResourceLocation STRONGHOLD_CHEST_LOOT_TABLE_ID = BuiltInLootTables.STRONGHOLD_CORRIDOR;
    private static final ResourceLocation MANSION_CHEST_LOOT_TABLE_ID = BuiltInLootTables.WOODLAND_MANSION;

    private static final ResourceLocation DESERT_PYRAMID_CHEST_LOOT_TABLE_ID = BuiltInLootTables.DESERT_PYRAMID;

    private static final ResourceLocation JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID = BuiltInLootTables.JUNGLE_TEMPLE;
    private static final ResourceLocation SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID = BuiltInLootTables.SHIPWRECK_SUPPLY;

    protected final LootItemCondition[] conditions;

    public static final Supplier<Codec<CyanWarriorSwordsLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, CyanWarriorSwordsLootModifier::new)));

    public CyanWarriorSwordsLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
        this.conditions = conditionsIn;
    }

    protected void ApplyLootModifier(ResourceLocation structure_chest_holder, ResourceLocation id,ObjectArrayList<ItemStack> generatedLoot, LootContext context, ConfigValues essence_cfg, Item essence_item ){
        if (structure_chest_holder.equals(id)) {
            if (ItemConfig.getBool(essence_cfg)) {
                LootPool pool = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0, 1))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .add(LootItem.lootTableItem(essence_item))
                        .build();
                pool.addRandomItems(generatedLoot::add, context);
            }
        }
    }
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation id = context.getQueriedLootTableId();

        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(SHIPWRECK_SUPPLY_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());

        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(DESERT_PYRAMID_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());

        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());


        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(MANSION_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());


        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(STRONGHOLD_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());


        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(DUNGEON_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());

        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.BEAST_ESSENCE,RegistryHandler.beast_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.DARK_ESSENCE,RegistryHandler.dark_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.EARTH_ESSENCE,RegistryHandler.earth_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.ENDER_ESSENCE,RegistryHandler.ender_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.FIRE_ESSENCE,RegistryHandler.fire_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.LIGHT_ESSENCE,RegistryHandler.light_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.MIXED_ESSENCE,RegistryHandler.mixed_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.THUNDER_ESSENCE,RegistryHandler.thunder_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WATER_ESSENCE,RegistryHandler.water_ESSENCE.get());
        ApplyLootModifier(MINESHAFT_CHEST_LOOT_TABLE_ID,id,generatedLoot,context,ConfigValues.WIND_ESSENCE,RegistryHandler.wind_ESSENCE.get());



        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

