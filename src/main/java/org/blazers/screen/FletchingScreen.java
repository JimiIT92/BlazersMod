package org.blazers.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.inventory.FletchingMenu;

/**
 * {@link net.minecraft.world.level.block.Blocks#FLETCHING_TABLE Fletching Table} GUI Screen
 */
@OnlyIn(Dist.CLIENT)
public class FletchingScreen  extends ItemCombinerScreen<FletchingMenu> {

    /**
     * GUI {@link ResourceLocation Texture Location}
     */
    private static final ResourceLocation FLETCHING_LOCATION = new ResourceLocation(BlazersMod.MOD_ID, "textures/gui/container/fletching.png");

    /**
     * Constructor. Sets the {@link net.minecraft.world.level.block.Blocks#FLETCHING_TABLE Fletching Table} GUI properties
     *
     * @param menu {@link FletchingMenu Fletching Table Menu}
     * @param inventory {@link Inventory Inventory}
     * @param title {@link Component GUI Title}
     */
    public FletchingScreen(FletchingMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, FLETCHING_LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    /**
     * Render the error arrow inside the GUI
     *
     * @param guiGraphics {@link PoseStack Pos}
     * @param x {@link Integer GUI X coordinate}
     * @param y {@link Integer GUI Y coordinate}
     */
    protected void renderErrorIcon(GuiGraphics guiGraphics, int x, int y) {
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            guiGraphics.blit(FLETCHING_LOCATION, x + 99, y + 45, this.imageWidth, 0, 28, 21);
        }
    }
}