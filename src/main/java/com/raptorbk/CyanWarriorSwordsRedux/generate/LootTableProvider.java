package com.raptorbk.CyanWarriorSwordsRedux.generate;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace.TransmutationFurnaceBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.fmllegacy.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private final DataGenerator generator;
    private final Map<Block, LootTable.Builder> blockTables = Maps.newHashMap();

    public LootTableProvider(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(HashCache hashCache) {
        addBlockLootTables();

        HashMap<ResourceLocation, LootTable> namespacedTables = new HashMap<>(blockTables.size());

        for (Map.Entry<Block, LootTable.Builder> entry : blockTables.entrySet()) {
            namespacedTables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }

        writeLootTables(namespacedTables, hashCache);
    }


    private void addBlockLootTables() {

        dropSelf(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE);
    }

    private void dropSelf(RegistryObject<? extends Block> block) {
        addLoot(block, new LootTable.Builder().withPool(new LootPool.Builder().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block.get())).when(ExplosionCondition.survivesExplosion())));
    }

    private void addLoot(RegistryObject<? extends Block> block, LootTable.Builder builder) {
        blockTables.put(block.get(), builder);
    }

    private void writeLootTables(Map<ResourceLocation, LootTable> tables, HashCache cache) {
        Path outputFolder = generator.getOutputFolder();

        for (Map.Entry<ResourceLocation, LootTable> entry : tables.entrySet()) {
            Path path = outputFolder.resolve("data/" + entry.getKey().getNamespace() + "/loot_tables/" + entry.getKey().getPath() + ".json");

            try {
                DataProvider.save(GSON, cache, LootTables.serialize(entry.getValue()), path);
            } catch (Exception e) {
                LOGGER.error("Error writing loot table", path, e);
            }
        }
    }
}
