package com.raptorbk.CyanWarriorSwordsRedux.generate;


import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipesProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = CyanWarriorSwordsReduxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenerationInit {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        BlockTagsProvider blockTags = new BlockTagsProvider(gen, helper);

        gen.addProvider(event.includeServer(),new CyanWarriorSwordsRecipesProvider(gen));
        //gen.addProvider(event.includeServer(),new ModLootModifierProvider(gen));
        gen.addProvider(event.includeServer(), blockTags);
        //gen.addProvider(event.includeServer(), new LootTableProvider(gen));

    }
}
