package com.raptorbk.CyanWarriorSwordsRedux;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class INH_ENCHANTMENT extends Enchantment{
    public INH_ENCHANTMENT(){
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON,new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 40;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 41;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
}
