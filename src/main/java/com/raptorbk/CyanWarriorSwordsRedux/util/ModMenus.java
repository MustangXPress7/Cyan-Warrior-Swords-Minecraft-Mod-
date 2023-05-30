package com.raptorbk.CyanWarriorSwordsRedux.util;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.Menus.TransmutationFurnaceMenu;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CyanWarriorSwordsReduxMod.MOD_ID);

    public static final RegistryObject<MenuType<TransmutationFurnaceMenu>> TRANSMUTATION = MENUS.register("transmutation_furnace", () -> new MenuType<>(TransmutationFurnaceMenu::new, FeatureFlags.VANILLA_SET));


}
