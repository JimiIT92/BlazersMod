package org.blazers.entity.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import org.blazers.entity.SpearEntity;

@Environment(EnvType.CLIENT)
public class SpearModel extends EntityModel<SpearEntity> {

    private final ModelPart root;

    public SpearModel(ModelPart root) {
        this.root = root;
    }

    public static TexturedModelData getTextureModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0)
                .cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F)
                .uv(9, 0)
                .cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .uv(28, 0)
                .cuboid(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(SpearEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}