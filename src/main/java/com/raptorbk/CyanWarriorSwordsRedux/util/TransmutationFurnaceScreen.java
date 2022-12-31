package com.raptorbk.CyanWarriorSwordsRedux.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.raptorbk.CyanWarriorSwordsRedux.Menus.TransmutationFurnaceMenu;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class TransmutationFurnaceScreen extends ContainerScreen<TransmutationFurnaceMenu> {

    public static final ResourceLocation texture = new ResourceLocation("textures/gui/container/furnace.png");
    public TransmutationFurnaceScreen(TransmutationFurnaceMenu container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
        int i = this.leftPos;
        int j = this.topPos;
        this.minecraft.textureManager.bind(texture);
        this.blit(p_97853_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            int k = this.menu.getLitProgress();
            this.blit(p_97853_, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        this.blit(p_97853_, i + 79, j + 34, 176, 14, l + 1, 16);
    }

}
