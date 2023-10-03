package org.blazers.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

@Environment(value= EnvType.CLIENT)
public class FletchingScreen extends ForgingScreen<FletchingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(BlazersMod.MOD_ID, "textures/gui/container/fletching.png");

    public FletchingScreen(FletchingScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleX = 60;
        this.titleY = 18;
    }

    @Override
    protected void drawForeground(DrawContext matrices, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawInvalidRecipeArrow(DrawContext matrices, int x, int y) {
        if (this.hasInvalidRecipe()) {
            matrices.drawTexture(TEXTURE, x + 99, y + 45, this.backgroundWidth, 0, 28, 21);
        }
    }

    private boolean hasInvalidRecipe() {
        return (this.handler).getSlot(0).hasStack() &&
                (this.handler).getSlot(1).hasStack() &&
                !(this.handler).getSlot((this.handler).getResultSlotIndex()).hasStack();
    }
}
