package org.blazers.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.Firefly;
import org.jetbrains.annotations.NotNull;

/**
 * Model class for a {@link Firefly Firefly}
 */
@OnlyIn(Dist.CLIENT)
public class FireflyModel extends EntityModel<Firefly> {

    /**
     * {@link Firefly Firefly} {@link ModelLayerLocation Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlazersMod.MOD_ID, "firefly"), "main");
    /**
     * Main {@link Firefly Firefly} {@link ModelPart Model Part}
     */
    private final ModelPart main;

    /**
     * Constructor. Sets the Main {@link Firefly Firefly} {@link ModelPart Model Part}
     *
     * @param root Main {@link Firefly Firefly} {@link ModelPart Model Part}
     */
    public FireflyModel(ModelPart root) {
        this.main = root.getChild("main");
    }

    /**
     * Create the {@link Firefly Firefly} {@link LayerDefinition Body Layer}
     *
     * @return {@link Firefly Firefly} {@link LayerDefinition Body Layer}
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    /**
     * Setup {@link Firefly Firefly} animations
     *
     * @param entity {@link Firefly Firefly}
     * @param limbSwing {@link Float Limb Swing}
     * @param limbSwingAmount {@link Float Limb Swing Amount}
     * @param ageInTicks {@link Float Age in ticks}
     * @param netHeadYaw {@link Float Net Head Yaw}
     * @param headPitch {@link Float Head Pitch}
     */
    @Override
    public void setupAnim(@NotNull Firefly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    /**
     * Render the {@link Firefly}
     *
     * @param poseStack {@link PoseStack Pose Stack}
     * @param vertexConsumer {@link VertexConsumer Vertex Consumer}
     * @param packedLight {@link Integer Packed Light}
     * @param packedOverlay {@link Integer Packed Overlay}
     * @param red {@link Float Red value}
     * @param green {@link Float Green value}
     * @param blue {@link Float Blue value}
     * @param alpha {@link Float Alpha value}
     */
    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}