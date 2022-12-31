package com.raptorbk.CyanWarriorSwordsRedux.common.data;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn.TransmutationFurnaceBlocks;
import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class GeneratorBaseRecipes extends BaseRecipeProvider{
    public GeneratorBaseRecipes(DataGenerator gen)  { super(gen, CyanWarriorSwordsReduxMod.MOD_ID);}

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
    {
        IRecipeSerializer d;
        addVanillaRecipes(consumer);
    }

    private void addVanillaRecipes(Consumer<IFinishedRecipe> consumer)
    {

        ShapedRecipeBuilder.shaped(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.asItem()).define('a', Blocks.BRICKS).define('b', RegistryHandler.ability_TOTEM.get()).pattern("aaa").pattern("aba").pattern("aaa").unlockedBy("has_sword_handle", has(RegistryHandler.sword_HANDLE.get())).save(consumer, CyanWarriorSwordsReduxMod.rl("transmutation_furnace"));

    }
}
