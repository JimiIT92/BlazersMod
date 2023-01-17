package org.blazers.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.blazers.core.BLBlocks;
import org.blazers.entity.AtomicTntEntity;

@Environment(value= EnvType.CLIENT)
public class AtomicTntEntityRenderer extends EntityRenderer<AtomicTntEntity> {

    private final BlockRenderManager blockRenderManager;

    public AtomicTntEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        this.blockRenderManager = context.getBlockRenderManager();
    }

    @Override
    public void render(AtomicTntEntity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.5, 0.0);
        int j = entity.getFuse();
        if ((float)j - tickDelta + 1.0f < 10.0f) {
            float h = 1.0f - ((float)j - tickDelta + 1.0f) / 10.0f;
            h = MathHelper.clamp(h, 0.0f, 1.0f);
            h *= h;
            h *= h;
            float k = 1.0f + h * 0.3f;
            matrixStack.scale(k, k, k);
        }
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0f));
        matrixStack.translate(-0.5, -0.5, 0.5);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f));
        TntMinecartEntityRenderer.renderFlashingBlock(this.blockRenderManager, BLBlocks.ATOMIC_TNT.getDefaultState(), matrixStack, vertexConsumers, light, j / 5 % 2 == 0);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(AtomicTntEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}