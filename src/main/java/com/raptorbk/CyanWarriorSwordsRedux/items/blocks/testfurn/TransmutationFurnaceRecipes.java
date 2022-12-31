package com.raptorbk.CyanWarriorSwordsRedux.items.blocks.testfurn;

import com.google.common.collect.Maps;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

public class TransmutationFurnaceRecipes
{
	private static final TransmutationFurnaceRecipes COOKING_BASE = new TransmutationFurnaceRecipes();
	private final Map<ItemStack, ItemStack> cookingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static TransmutationFurnaceRecipes instance()
    {
        return COOKING_BASE;
    }
	
	private TransmutationFurnaceRecipes()
	{
		//this.addCookingRecipeForBlock(Blocks.IRON_ORE, new ItemStack(Items.APPLE), 0.6f);

		this.addCookingRecipe(new ItemStack(ModItems.fire_SWORD),new ItemStack(ModItems.fire_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.water_SWORD),new ItemStack(ModItems.water_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.earth_SWORD),new ItemStack(ModItems.earth_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.wind_SWORD),new ItemStack(ModItems.wind_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.thunder_SWORD),new ItemStack(ModItems.thunder_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.dark_SWORD),new ItemStack(ModItems.dark_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.light_SWORD),new ItemStack(ModItems.light_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.ender_SWORD),new ItemStack(ModItems.ender_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.beast_SWORD),new ItemStack(ModItems.beast_ESSENCE),1.0F);
        this.addCookingRecipe(new ItemStack(ModItems.combustion_SWORD),new ItemStack(ModItems.fire_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.ice_SWORD),new ItemStack(ModItems.water_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.wild_NATURE),new ItemStack(ModItems.earth_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.wind_IMPULSE),new ItemStack(ModItems.wind_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.thunder_SHOCK),new ItemStack(ModItems.thunder_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.dark_NETHER),new ItemStack(ModItems.dark_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.light_NETHER),new ItemStack(ModItems.light_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.ender_PORTAL),new ItemStack(ModItems.ender_ESSENCE),2.0F);
        this.addCookingRecipe(new ItemStack(ModItems.golem_SWORD),new ItemStack(ModItems.beast_ESSENCE),2.0F);







    }
	
	public void addCookingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addCooking(Item.getItemFromBlock(input), stack, experience);
    }
	
	public void addCooking(Item input, ItemStack stack, float experience)
    {
        this.addCookingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }
	
	public void addCookingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getCookingResult(input) != ItemStack.EMPTY) 
        { 
        	net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} = {}", input, stack); return; 
        }
        this.cookingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }
	
	public ItemStack getCookingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.cookingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getCookingList()
    {
        return this.cookingList;
    }

    public float getCookingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
