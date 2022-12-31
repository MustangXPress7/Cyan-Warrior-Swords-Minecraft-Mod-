package com.raptorbk.CyanWarriorSwordsRedux.generate;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.Init;
import com.raptorbk.CyanWarriorSwordsRedux.events.loot.EssencesStructureAdditionModifier;
import com.raptorbk.CyanWarriorSwordsRedux.events.loot.lootcondition.LootConditions;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = CyanWarriorSwordsReduxMod.MOD_ID)
@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLootModifiers {

    public static final GlobalLootModifierSerializer<EssencesStructureAdditionModifier> ESSENCES_DUNGEON = null;

    @SubscribeEvent
    public static void onRegisterModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        LootConditions.register();
        IForgeRegistry<GlobalLootModifierSerializer<?>> reg = event.getRegistry();

        reg.register(Init.setup(new EssencesStructureAdditionModifier.Serializer(), "essences_dungeon"));
    }
}
