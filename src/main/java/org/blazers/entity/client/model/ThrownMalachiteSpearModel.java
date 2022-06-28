package org.blazers.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.ThrownSpear;
import org.jetbrains.annotations.NotNull;

/**
 * Model class for a {@link ThrownSpear Thrown Malachite Spear}
 */
@OnlyIn(Dist.CLIENT)
public class ThrownMalachiteSpearModel extends Model {

    /**
     * {@link ThrownSpear Thrown Malachite Spear} {@link ModelLayerLocation Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlazersMod.MOD_ID, "thrown_malachite_spear"), "main");
    /**
     * Main {@link ThrownSpear Thrown Malachite Spear} {@link ModelPart Model Part}
     */
    private final ModelPart main;

    /**
     * Constructor. Sets the Main {@link ThrownSpear Thrown Malachite Spear} {@link ModelPart Model Part}
     *
     * @param root Main {@link ThrownSpear Thrown Malachite Spear} {@link ModelPart Model Part}
     */
    public ThrownMalachiteSpearModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.main = root.getChild("main");
    }

    /**
     * Create the {@link ThrownSpear Thrown Malachite Spear} {@link LayerDefinition Body Layer}
     *
     * @return {@link ThrownSpear Thrown Malachite Spear} {@link LayerDefinition Body Layer}
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 12).addBox(-1.5F, 6.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 17).addBox(-1.0F, 7.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 17).addBox(-1.0F, 7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 21).addBox(-1.0F, 2.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 21).addBox(-1.0F, 2.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(1, 26).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(14, 28).addBox(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    /**
     * Render the {@link ThrownSpear Thrown Malachite Spear}
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