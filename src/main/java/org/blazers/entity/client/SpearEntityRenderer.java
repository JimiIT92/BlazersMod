package org.blazers.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.blazers.BlazersMod;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.SpearEntity;
import org.blazers.entity.client.model.SpearModel;

@Environment(value= EnvType.CLIENT)
public class SpearEntityRenderer extends EntityRenderer<SpearEntity> {

    public static final Identifier TEXTURE = new Identifier(BlazersMod.MOD_ID, "textures/entity/spear/regular.png");
    private final SpearModel model;

    public SpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new SpearModel(context.getPart(BLModelLayers.SPEAR));
    }

    @Override
    public void render(SpearEntity spearEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, spearEntity.prevYaw, spearEntity.getYaw()) - 90.0f));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, spearEntity.prevPitch, spearEntity.getPitch()) + 90.0f));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(spearEntity)), false, false);
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(spearEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(SpearEntity tridentEntity) {
        return TEXTURE;
    }
}
