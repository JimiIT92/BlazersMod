package org.blazers.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.TntRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.core.BLBlocks;
import org.blazers.entity.PrimedAtomicTnt;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link PrimedAtomicTnt Primed Atomic TNT}
 */
@OnlyIn(Dist.CLIENT)
public class AtomicTntRenderer extends EntityRenderer<PrimedAtomicTnt> {

    /**
     * {@link BlockRenderDispatcher Block Renderer Dispatcher}
     */
    private final BlockRenderDispatcher blockRenderer;

    /**
     * Constructor. Sets the {@link TntRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public AtomicTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
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
        int i = entity.getFuse();
        if ((float)i - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - partialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            pose.scale(f1, f1, f1);
        }

        pose.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pose.translate(-0.5D, -0.5D, 0.5D);
        pose.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, BLBlocks.ATOMIC_TNT.get().defaultBlockState(), pose, buffer, packedLight, i / 5 % 2 == 0);
        pose.popPose();
        super.render(entity, yaw, partialTicks, pose, buffer, packedLight);
    }

    /**
     * Get the {@link PrimedAtomicTnt Primed Atomic TNT} texture
     *
     * @param entity {@link PrimedAtomicTnt Primed Atomic TNT}
     * @return {@link PrimedAtomicTnt Primed Atomic TNT} texture
     */
    public @NotNull ResourceLocation getTextureLocation(@NotNull PrimedAtomicTnt entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}