package org.blazers.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.core.BLBlocks;
import org.blazers.entity.block.PrimedAtomicTnt;
import org.jetbrains.annotations.NotNull;

/**
 * {@link EntityRenderer Renderer class} for the {@link PrimedAtomicTnt Primed Atomic TNT}
 */
@OnlyIn(Dist.CLIENT)
public final class AtomicTntRenderer extends EntityRenderer<PrimedAtomicTnt> {

    /**
     * {@link BlockRenderDispatcher The Block Renderer Dispatcher instance}
     */
    private final BlockRenderDispatcher blockRendererDispatcher;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Entity Renderer Context}
     */
    public AtomicTntRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRendererDispatcher = context.getBlockRenderDispatcher();
    }

    /**
     * Render the {@link PrimedAtomicTnt Primed Atomic TNT}
     *
     * @param entity {@link PrimedAtomicTnt Primed Atomic TNT}
     * @param yaw {@link Float Entity Yaw}
     * @param partialTicks {@link Float Partial Ticks}
     * @param pose {@link PoseStack Entity Pose}
     * @param buffer {@link MultiBufferSource Buffer}
     * @param packedLight {@link Integer Packed Light}
     */
    public void render(PrimedAtomicTnt entity, float yaw, float partialTicks, PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight) {
        pose.pushPose();
        pose.translate(0.0D, 0.5D, 0.0D);
        final int fuse = entity.getFuse();
        if ((float)fuse - partialTicks + 1.0F < 10.0F) {
            float scaleFactor = 1.0F - ((float)fuse - partialTicks + 1.0F) / 10.0F;
            scaleFactor = Mth.clamp(scaleFactor, 0.0F, 1.0F);
            scaleFactor *= scaleFactor;
            scaleFactor *= scaleFactor;
            final float scale = 1.0F + scaleFactor * 0.3F;
            pose.scale(scale, scale, scale);
        }

        pose.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pose.translate(-0.5D, -0.5D, 0.5D);
        pose.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRendererDispatcher, BLBlocks.ATOMIC_TNT.get().defaultBlockState(), pose, buffer, packedLight, fuse / 5 % 2 == 0);
        pose.popPose();
        super.render(entity, yaw, partialTicks, pose, buffer, packedLight);
    }

    /**
     * Get the {@link ResourceLocation Entity texture location}
     *
     * @param entity {@link PrimedAtomicTnt The entity}
     * @return {@link InventoryMenu#BLOCK_ATLAS The Block Textures Resource Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull PrimedAtomicTnt entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }

}