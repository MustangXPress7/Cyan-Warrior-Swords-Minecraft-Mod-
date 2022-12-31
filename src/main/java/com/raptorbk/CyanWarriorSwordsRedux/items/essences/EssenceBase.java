package com.raptorbk.CyanWarriorSwordsRedux.items.essences;

import com.raptorbk.CyanWarriorSwordsRedux.Main;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.util.IHasModel;
import net.minecraft.item.Item;

public class EssenceBase extends Item implements IHasModel {

    public EssenceBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModItems.cyanWarriorTab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this,0,"inventory");
    }
}
