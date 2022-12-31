package com.raptorbk.CyanWarriorSwordsRedux.init;

import com.raptorbk.CyanWarriorSwordsRedux.DW_ENCHANTMENT;
import com.raptorbk.CyanWarriorSwordsRedux.INH_ENCHANTMENT;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModEnchantments {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

    public static final Enchantment InhEnchantment = addEnchantment(new INH_ENCHANTMENT(), "inh_enchantment");

    public static final Enchantment DwEnchantment = addEnchantment(new DW_ENCHANTMENT(), "dw_enchantment");

    private static Enchantment addEnchantment(Enchantment enchantment, String name) {
        ENCHANTMENTS.add(enchantment);
        return enchantment.setRegistryName(name).setName(name);
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        for(Enchantment enchantment: ENCHANTMENTS)
            event.getRegistry().register(enchantment);
    }

}
