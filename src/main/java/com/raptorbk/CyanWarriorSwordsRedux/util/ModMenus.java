package com.raptorbk.CyanWarriorSwordsRedux.util;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.Menus.TransmutationFurnaceMenu;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMenus   {
    public static final DeferredRegister<ContainerType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, CyanWarriorSwordsReduxMod.MOD_ID);

    public static final RegistryObject<ContainerType<TransmutationFurnaceMenu>> TRANSMUTATION = MENUS.register("transmutation_furnace", () -> new ContainerType<>(TransmutationFurnaceMenu::new));

}
