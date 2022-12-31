package com.raptorbk.CyanWarriorSwordsRedux.blocks.testfurn;

import com.raptorbk.CyanWarriorSwordsRedux.Menus.TransmutationFurnaceMenu;
import com.raptorbk.CyanWarriorSwordsRedux.essences.EssenceBase;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CustomIRecipeType;
import com.raptorbk.CyanWarriorSwordsRedux.recipes.CyanWarriorSwordsRecipesType;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;


public class TransmutationFurnaceTileEntity extends AbstractFurnaceTileEntity {
    public TransmutationFurnaceTileEntity() {
        super(TransmutationFurnaceTileEntities.TRANSMUTATION_FURNACE, CyanWarriorSwordsRecipesType.TRANSMUTATION);
    }

    protected ITextComponent getDefaultName() {
        ITextComponent titleUI=new TranslationTextComponent("ui.cwsr.transfurnace.title");
        return new TranslationTextComponent(titleUI.getString());
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new TransmutationFurnaceMenu(id, player, this, this.dataAccess);
    }

    /* FOLLOWING Code helps the copied code below. */

    public static final int BURN_TIME = 0;
    public static final int RECIPES_USED = 1;
    public static final int COOK_TIME = 2;
    public static final int COOK_TIME_TOTAL = 3;

    /* FOLLOWING Code is copied from "Shadows-of-Fire/FastFurnace" mod to enhance performance */

    public static final int INPUT = 0;
    public static final int FUEL = 1;
    public static final int OUTPUT = 2;

    protected AbstractCookingRecipe curRecipe;
    protected ItemStack failedMatch = ItemStack.EMPTY;

    private boolean isBurning() {
        return this.dataAccess.get(BURN_TIME) > 0; //changed because of private variable
    }

    @Override
    public void tick() {
        boolean wasBurning = this.isBurning();
        boolean dirty = false;
        if (this.isBurning()) {
            this.dataAccess.set(BURN_TIME, this.dataAccess.get(BURN_TIME) - 1); //changed because of private variable
        }

        if (!this.level.isClientSide) {
            ItemStack fuel = this.items.get(FUEL);
            if (this.isBurning() || !fuel.isEmpty() && !this.items.get(INPUT).isEmpty()) {
                AbstractCookingRecipe irecipe = getRecipe();
                boolean valid = this.canBurn(irecipe);
                if (!this.isBurning() && valid) {
                    this.dataAccess.set(BURN_TIME, this.getBurnDuration(fuel)); //changed because of private variable
                    this.dataAccess.set(RECIPES_USED, this.dataAccess.get(BURN_TIME)); //changed because of private variable
                    if (this.isBurning()) {
                        dirty = true;
                        if (fuel.hasContainerItem()) this.items.set(1, fuel.getContainerItem());
                        else if (!fuel.isEmpty()) {
                            fuel.shrink(1);
                            if (fuel.isEmpty()) {
                                this.items.set(1, fuel.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && valid) {
                    this.dataAccess.set(COOK_TIME, this.dataAccess.get(COOK_TIME) + 1); //changed because of private variable
                    if (this.dataAccess.get(COOK_TIME) == this.dataAccess.get(COOK_TIME_TOTAL)) { //changed because of private variable
                        this.dataAccess.set(COOK_TIME, 0); //changed because of private variable
                        this.dataAccess.set(COOK_TIME_TOTAL, this.getTotalCookTime()); //changed because of private variable
                        this.smeltItem(irecipe);
                        dirty = true;
                    }
                } else {
                    this.dataAccess.set(COOK_TIME, 0); //changed because of private variable
                }
            } else if (!this.isBurning() && this.dataAccess.get(COOK_TIME) > 0) { //changed because of private variable
                this.dataAccess.set(COOK_TIME, MathHelper.clamp(this.dataAccess.get(COOK_TIME) - 2, 0, this.dataAccess.get(COOK_TIME_TOTAL))); //changed because of private variable
            }

            if (wasBurning != this.isBurning()) {
                dirty = true;
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
            }
        }

        if (dirty) {
            this.setChanged();
        }

    }


    @Override
    protected boolean canBurn(@Nullable IRecipe<?> recipe) {
            if (!this.items.get(0).isEmpty() && recipe != null) {
                ItemStack recipeOutput = recipe.getResultItem();
                if (!recipeOutput.isEmpty() && recipeOutput.getItem() instanceof EssenceBase) {
                    ItemStack output = this.items.get(OUTPUT);
                    if (output.isEmpty()) return true;
                    else if (!output.sameItem(recipeOutput)) return false;
                    else return output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize();
                }
            }
            return false;
        }



    private void smeltItem(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canBurn(recipe)) {
            ItemStack itemstack = this.items.get(0);
            ItemStack itemstack1 = recipe.getResultItem();
            ItemStack itemstack2 = this.items.get(2);

            if (itemstack2.isEmpty()) {
                this.items.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (!this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    @Override
    protected int getTotalCookTime() {
        double timeFactor = Config.COOK_TIME_FACTOR.getValue();
        AbstractCookingRecipe rec = getRecipe();
        if (rec == null) return (int) (200 * timeFactor);
        return (int) (rec.getCookingTime() * timeFactor);
    }

    @SuppressWarnings("unchecked")
    protected AbstractCookingRecipe getRecipe() {
        ItemStack input = this.getItem(INPUT);
        if (input.isEmpty() || input == failedMatch) return null;
        if (curRecipe != null && curRecipe.matches(this, level)) return curRecipe;
        else {
            AbstractCookingRecipe rec = level.getRecipeManager().getRecipeFor((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.level).orElse(null);
            if (rec == null) failedMatch = input;
            else failedMatch = ItemStack.EMPTY;
            return curRecipe = rec;
        }
    }
}
