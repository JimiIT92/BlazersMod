package org.blazers.client.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.client.model.ThrownSpearModel;
import org.blazers.entity.projectile.ThrownSpear;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link ThrownSpear Thrown Spear}
 */
@OnlyIn(Dist.CLIENT)
public class ThrownSpearRenderer extends EntityRenderer<ThrownSpear> {

    /**
     * {@link ResourceLocation The Spear texture location}
     */
    public static ResourceLocation SPEAR_LOCATION = RegistryHelper.location("textures/entity/spear/regular.png");

    /**
     * {@link ThrownSpearModel Thrown Spear Model}
     */
    protected Model model;

    /**
     * Constructor. Sets the {@link EntityRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public ThrownSpearRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownSpearModel(context.bakeLayer(ThrownSpearModel.LAYER_LOCATION));
    }

    /**
     * Render the {@link ThrownSpear Thrown Spear}
     *
     * @param entity {@link ThrownSpear The Thrown Spear}
     * @param yaw {@link Float The Entity yaw}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    public void render(final ThrownSpear entity, final float yaw, final float partialTicks, final PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
        final VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, false);
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    /**
     * Get the {@link ResourceLocation Thrown Spear texture}
     *
     * @param entity {@link ThrownSpear Thrown Spear}
     * @return {@link ResourceLocation Thrown Spear texture}
     */
    public @NotNull ResourceLocation getTextureLocation(final @NotNull ThrownSpear entity) {
        return SPEAR_LOCATION;
    }

}