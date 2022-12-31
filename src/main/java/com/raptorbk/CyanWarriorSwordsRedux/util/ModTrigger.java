package com.raptorbk.CyanWarriorSwordsRedux.util;

import com.raptorbk.CyanWarriorSwordsRedux.customadv.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTrigger {
    public static Reallyradtrigger Reallyradtrigger;
    public static Ablasttrigger Ablasttrigger;
    public static Sworddestroyedtrigger Sworddestroyedtrigger;
    public static Dualwieldachtrigger Dualwieldachtrigger;
    public static Somethingelsetrigger Somethingelsetrigger;
    public static Removingdentstrigger Removingdentstrigger;
    public static Themoretrigger Themoretrigger;
    public static Bestbothtrigger Bestbothtrigger;
    @SubscribeEvent
    public static void onRegistry(FMLCommonSetupEvent event) {
        CriteriaTriggers.register(Reallyradtrigger = new Reallyradtrigger());
        CriteriaTriggers.register(Ablasttrigger = new Ablasttrigger());
        CriteriaTriggers.register(Sworddestroyedtrigger = new Sworddestroyedtrigger());
        CriteriaTriggers.register(Dualwieldachtrigger  = new Dualwieldachtrigger());
        CriteriaTriggers.register(Somethingelsetrigger  = new Somethingelsetrigger());
        CriteriaTriggers.register(Removingdentstrigger  = new Removingdentstrigger());
        CriteriaTriggers.register(Themoretrigger = new Themoretrigger());
        CriteriaTriggers.register(Bestbothtrigger = new Bestbothtrigger());
    }
}