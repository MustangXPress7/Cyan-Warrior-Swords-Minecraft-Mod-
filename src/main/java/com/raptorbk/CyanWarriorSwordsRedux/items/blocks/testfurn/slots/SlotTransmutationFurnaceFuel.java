package com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.slots;


import com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn.TileEntityTransmutationFurnace;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTransmutationFurnaceFuel extends Slot
{

	public SlotTransmutationFurnaceFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	{
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return TileEntityTransmutationFurnace.isItemFuel(stack) || isBucket(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) 
	{
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}
	
	public static boolean isBucket(ItemStack stack)
	{
		return stack.getItem() == Items.BUCKET;
	}
}
