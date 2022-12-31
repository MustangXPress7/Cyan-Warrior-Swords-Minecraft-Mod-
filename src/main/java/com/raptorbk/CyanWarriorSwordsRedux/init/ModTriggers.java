package com.raptorbk.CyanWarriorSwordsRedux.init;


import com.raptorbk.CyanWarriorSwordsRedux.customadv.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModTriggers {

    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Ablasttrigger Ablasttrigger;
    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Bestbothtrigger Bestbothtrigger;
    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Somethingelsetrigger Somethingelsetrigger;
    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Dualwieldachtrigger Dualwieldachtrigger;
    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Reallyradtrigger Reallyradtrigger;
    public static com.raptorbk.CyanWarriorSwordsRedux.customadv.Themoretrigger Themoretrigger;

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Enchantment> event) {
        CriteriaTriggers.register(Ablasttrigger = new Ablasttrigger());
        CriteriaTriggers.register(Bestbothtrigger = new Bestbothtrigger());
        CriteriaTriggers.register(Dualwieldachtrigger = new Dualwieldachtrigger());
        CriteriaTriggers.register(Somethingelsetrigger = new Somethingelsetrigger());
        CriteriaTriggers.register(Reallyradtrigger = new Reallyradtrigger());
        CriteriaTriggers.register(Themoretrigger = new Themoretrigger());
    }

}
