package com.raptorbk.CyanWarriorSwordsRedux;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class INH_ENCHANTMENT extends Enchantment {
    public INH_ENCHANTMENT(){
        super(Rarity.RARE, EnchantmentType.WEAPON,new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 40;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 41;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
