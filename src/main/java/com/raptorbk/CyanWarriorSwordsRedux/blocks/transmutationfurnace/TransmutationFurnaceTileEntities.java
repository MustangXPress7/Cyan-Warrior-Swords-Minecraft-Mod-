package com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace;
import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TransmutationFurnaceTileEntities {
        public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CyanWarriorSwordsReduxMod.MOD_ID);

        public static final RegistryObject<BlockEntityType<TransmutationFurnaceBlockEntity>> TRANSMUTATION_FURNACE=TILE_ENTITIES.register("transmutation_entity", () -> BlockEntityType.Builder.of(TransmutationFurnaceBlockEntity::new, TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.get()).build(null));

}

