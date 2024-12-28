package org.blazers.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.model.SpearEntityModel;
import org.blazers.entity.projectile.SpearEntity;

/**
 * Renderer class for a {@link SpearEntity Spear}
 */
@Environment(EnvType.CLIENT)
public class SpearEntityRenderer extends EntityRenderer<SpearEntity, SpearEntityRenderState> {

    /**
     * The {@link Model Spear Entity Model}
     */
    protected Model model;

    /**
     * Constructor. Set the render propertis
     *
     * @param context The {@link EntityRendererFactory.Context Render Context}
     */
    public SpearEntityRenderer(final EntityRendererFactory.Context context) {
        super(context);
        this.model = new SpearEntityModel(context.getPart(BLModelLayers.SPEAR));
    }

    /**
     * Get the {@link EntityRenderState Entity Render State}
     *
     * @return The {@link SpearEntityRenderState Spear Entity Render State}
     */
    @Override
    public SpearEntityRenderState createRenderState() {
        return new SpearEntityRenderState();
    }

    /**
     * Render the {@link SpearEntity Spear}
     *
     * @param spearEntityRenderState The {@link SpearEntityRenderState Spear Entity Render State}
     * @param matrixStack The {@link MatrixStack Render Matrix Stack}
     * @param vertexConsumerProvider The {@link VertexConsumerProvider Vertex Consumer Provider}
     * @param light The {@link Integer client light}
     */
    public void render(final SpearEntityRenderState spearEntityRenderState, final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int light) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(spearEntityRenderState.yaw - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(spearEntityRenderState.pitch + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(texture()), false, false);
        this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(spearEntityRenderState, matrixStack, vertexConsumerProvider, light);
    }

    /**
     * Get the {@link Identifier Entity Texture}
     *
     * @return The {@link Identifier Entity Texture}
     */
    public Identifier texture() {
        return SpearEntityModel.TEXTURE;
    }

    /**
     * Update the {@link SpearEntity Spear} {@link Float pitch} and {@link Float yaw}
     *
     * @param spear The {@link SpearEntity Spear Entity}
     * @param spearEntityRenderState The {@link SpearEntityRenderState Spear Entity Render State}
     * @param angle The {@link Float entity angle}
     */
    public void updateRenderState(final SpearEntity spear, final SpearEntityRenderState spearEntityRenderState, final float angle) {
        super.updateRenderState(spear, spearEntityRenderState, angle);
        spearEntityRenderState.yaw = spear.getLerpedYaw(angle);
        spearEntityRenderState.pitch = spear.getLerpedPitch(angle);
    }
}
