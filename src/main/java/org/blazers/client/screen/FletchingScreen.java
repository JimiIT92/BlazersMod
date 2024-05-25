package org.blazers.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.blazers.inventory.FletchingMenu;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link ItemCombinerScreen Fletching Recipe Screen}
 */
@OnlyIn(Dist.CLIENT)
public final class FletchingScreen extends ItemCombinerScreen<FletchingMenu> {

    /**
     * {@link ResourceLocation The Fletching Screen Texture location}
     */
    private static final ResourceLocation FLETCHING_LOCATION = RegistryHelper.location("textures/gui/container/fletching.png");

    /**
     * Constructor. Set the Screen properties
     *
     * @param menu {@link FletchingMenu The Menu reference}
     * @param inventory {@link Inventory The Screen inventory}
     * @param title {@link Component The Screen title}
     */
    public FletchingScreen(final FletchingMenu menu, final Inventory inventory, final Component title) {
        super(menu, inventory, title, FLETCHING_LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    /**
     * Render the error icon
     *
     * @param guiGraphics {@link GuiGraphics The GUI Graphics instance}
     * @param x {@link Integer The Screen X coordinate}
     * @param y {@link Integer The Screen Y coordinate}
     */
    @Override
    protected void renderErrorIcon(final @NotNull GuiGraphics guiGraphics, final int x, final int y) {
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            guiGraphics.blit(FLETCHING_LOCATION, x + 99, y + 45, this.imageWidth, 0, 28, 21);
        }
    }

}