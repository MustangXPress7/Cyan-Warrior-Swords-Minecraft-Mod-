package com.raptorbk.CyanWarriorSwordsRedux.compat.jei;

import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.impl.CyanWarriorSwordsAPI;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn.TransmutationFurnaceBlocks;
import com.raptorbk.CyanWarriorSwordsRedux.compat.transmutation.TransmutationRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

@JeiPlugin
public class CyanWarriorSwordsJEIPlugin implements IModPlugin
{
    public static IJeiHelpers jeiHelpers;

    private  static final ResourceLocation ID = CyanWarriorSwordsReduxMod.rl("jei_plugin");

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
        registration.addRecipeCatalyst(new ItemStack(TransmutationFurnaceBlocks.TRANSMUTATION_FURNACE.asItem()), TransmutationRecipeCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration){
        jeiHelpers = registration.getJeiHelpers();

        registration.addRecipeCategories(new TransmutationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        ClientWorld world = Objects.requireNonNull(Minecraft.getInstance().level);
        registration.addRecipes(CyanWarriorSwordsAPI.INSTANCE.getRecipeRegistrar().getTransmutationRecipes(world), TransmutationRecipeCategory.UID);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }
}
