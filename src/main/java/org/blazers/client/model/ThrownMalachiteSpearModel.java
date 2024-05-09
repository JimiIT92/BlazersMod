package org.blazers.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link Model Thrown Malachite Spear Model}
 */
@OnlyIn(Dist.CLIENT)
public final class ThrownMalachiteSpearModel extends Model {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(RegistryHelper.location("thrown_malachite_spear"), "main");
    /**
     * {@link ModelPart The Root Model Part}
     */
    private final ModelPart root;

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public ThrownMalachiteSpearModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.root = root.getChild("main");
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        meshDefinition.getRoot().addOrReplaceChild("main", CubeListBuilder.create()
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

        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    /**
     * Render the {@link HumanoidModel Model}
     *
     * @param poseStack {@link PoseStack The Model Pose Stack}
     * @param vertexConsumer {@link VertexConsumer The Vertex Consumer reference}
     * @param packedLight {@link Integer The Model Packed light}
     * @param packedOverlay {@link Integer The Model Packed overlay}
     * @param red {@link Float The Model red channel value}
     * @param green {@link Float The Model green channel value}
     * @param blue {@link Float The Model blue channel value}
     * @param alpha {@link Float The Model alpha channel value}
     */
    @Override
    public void renderToBuffer(final @NotNull PoseStack poseStack, final @NotNull VertexConsumer vertexConsumer, final int packedLight, final int packedOverlay, final float red, final float green, final float blue, final float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}