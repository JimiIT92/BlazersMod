package org.blazers.entity.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import org.blazers.entity.FireflyEntity;

@Environment(EnvType.CLIENT)
public class FireflyModel extends EntityModel<FireflyEntity> {

    private final ModelPart root;

    public FireflyModel(ModelPart root) {
        this.root = root;
    }

    public static TexturedModelData getTextureModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create()
                .uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F), ModelTransform.pivot(0f, 24f, 0f));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void setAngles(FireflyEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
