package org.blazers.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.ThrownSpear;
import org.blazers.entity.client.model.ThrownSpearModel;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link ThrownSpear Thrown Spear}
 */
@OnlyIn(Dist.CLIENT)
public class ThrownSpearRenderer extends EntityRenderer<ThrownSpear> {

    /**
     * {@link ResourceLocation Spear Texture Location}
     */
    public static final ResourceLocation SPEAR_LOCATION = new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/spear/regular.png");

    /**
     * {@link ThrownSpearModel Thrown Spear Model}
     */
    protected EntityModel<ThrownSpear> model;

    /**
     * Constructor. Sets the {@link EntityRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public ThrownSpearRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownSpearModel(context.bakeLayer(ThrownSpearModel.LAYER_LOCATION));
    }

    /**
     * Render the {@link ThrownSpear Thrown Spear}
     *
     * @param entity {@link ThrownSpear Thrown Spear}
     * @param yaw {@link Float Entity Yaw}
     * @param partialTicks {@link Float Partial Ticks}
     * @param pose {@link PoseStack Entity Pose}
     * @param buffer {@link MultiBufferSource Buffer}
     * @param packedLight {@link Integer Packed Light}
     */
    public void render(ThrownSpear entity, float yaw, float partialTicks, PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight) {
        pose.pushPose();
        pose.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        pose.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, false);
        this.model.renderToBuffer(pose, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
        super.render(entity, yaw, partialTicks, pose, buffer, packedLight);
    }

    /**
     * Get the {@link ResourceLocation Thrown Spear texture}
     *
     * @param entity {@link ThrownSpear Thrown Spear}
     * @return {@link ResourceLocation Thrown Spear texture}
     */
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownSpear entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/spear/regular.png");
    }
}