package com.raptorbk.CyanWarriorSwordsRedux.blocks.transmutationfurnace;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static ForgeConfigSpec COMMON;

    public static List<IResettableConfigType> allValues = new ArrayList<>();

    public static final ConfigType.Double COOK_TIME_FACTOR = new ConfigType.Double(1.0);

    static {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        COMMON = common.build();
    }

    public static void resetConfig() {
        for (IResettableConfigType par : allValues) {
            par.reset();
        }
    }
}