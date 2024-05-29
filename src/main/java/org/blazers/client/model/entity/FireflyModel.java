package org.blazers.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.entity.animal.Firefly;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Model class for a {@link Firefly Firefly}
 */
@OnlyIn(Dist.CLIENT)
public final class FireflyModel extends EntityModel<Firefly> {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(RegistryHelper.location("firefly"), "main");
    /**
     * {@link ModelPart The Root Model Part}
     */
    private final ModelPart root;

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public FireflyModel(final ModelPart root) {
        this.root = root.getChild("main");
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer(){
        final MeshDefinition meshDefinition = new MeshDefinition();
        meshDefinition.getRoot().addOrReplaceChild("main", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 16, 16);
    }

    /**
     * Setup the entity animations
     *
     * @param firefly {@link Firefly The entity}
     * @param limbSwing {@link Float The entity limb swing}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param ageInTicks {@link Float The entity age in ticks}
     * @param netHead {@link Float The entity net head}
     * @param headPitch {@link Float The entity head pitch}
     */
    @Override
    public void setupAnim(final @NotNull Firefly firefly, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHead, final float headPitch) {

    }

    /**
     * Render the {@link EntityModel Model}
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
