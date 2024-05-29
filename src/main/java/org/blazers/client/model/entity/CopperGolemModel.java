package org.blazers.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.entity.CopperGolem;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Model class for a {@link CopperGolem Copper Golem}
 */
@OnlyIn(Dist.CLIENT)
public final class CopperGolemModel extends HierarchicalModel<CopperGolem> {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(RegistryHelper.location("copper_golem"), "main");
    /**
     * The {@link AnimationDefinition Copper Golem Idle animation}
     */
    public static final AnimationDefinition COPPER_GOLEM_IDLE = AnimationDefinition.Builder.withLength(3F).looping()
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.5F, KeyframeAnimations.degreeVec(0F, 0F, 7.5F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.5F, KeyframeAnimations.degreeVec(0F, 0F, -7.5F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .build();
    /**
     * The {@link AnimationDefinition Copper Golem walk animation}
     */
    public static final AnimationDefinition COPPER_GOLEM_WALK = AnimationDefinition.Builder.withLength(1.375F).looping()
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(-17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(-17.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .build();
    /**
     * The {@link AnimationDefinition Copper Golem interact animation}
     */
    public static final AnimationDefinition COPPER_GOLEM_INTERACT = AnimationDefinition.Builder.withLength(1.25F)
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-27.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-120F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5F, KeyframeAnimations.degreeVec(-27.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-102.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-60F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(-137.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-137.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-60F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5F, KeyframeAnimations.degreeVec(-102.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-27.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-120F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(-27.5F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0F, 45F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0F, 90F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0F, 135F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0F, 225F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0F, 270F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1F, KeyframeAnimations.degreeVec(0F, 315F, 0F),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0F, 360F, 0F),
                                    AnimationChannel.Interpolations.LINEAR)))
            .build();
    /**
     * {@link ModelPart The Head Model Part}
     */
    private final ModelPart head;
    /**
     * {@link ModelPart The root Model Part}
     */
    private final ModelPart root;

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public CopperGolemModel(final ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer(){
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition root = meshDefinition.getRoot();

        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(26, 13).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 20.0F, -1.5F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 20.0F, -1.5F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 13.0F, -1.5F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 13.0F, -1.5F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.5F, -2.0F))
                .addOrReplaceChild("nose", CubeListBuilder.create().texOffs(51, 13).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -5.0F));
        root.addOrReplaceChild("rod", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -18.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 20).addBox(-2.0F, -21.0F, -4.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    /**
     * Setup the entity animations
     *
     * @param copperGolem {@link CopperGolem The entity}
     * @param limbSwing {@link Float The entity limb swing}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param ageInTicks {@link Float The entity age in ticks}
     * @param netHead {@link Float The entity net head}
     * @param headPitch {@link Float The entity head pitch}
     */
    @Override
    public void setupAnim(final @NotNull CopperGolem copperGolem, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHead, final float headPitch) {
        this.root.getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHead, headPitch, ageInTicks);

        this.animateWalk(COPPER_GOLEM_WALK, limbSwing, limbSwingAmount, 1.375F, 2F);
        this.animate(copperGolem.idleAnimationState, COPPER_GOLEM_IDLE, ageInTicks, 1F);
        this.animate(copperGolem.interactAnimationState, COPPER_GOLEM_INTERACT, ageInTicks, 1F);
    }

    /**
     * Apply the head rotation to the entity
     *
     * @param netHead {@link Float The entity net head}
     * @param headPitch {@link Float The entity head pitch}
     * @param ageInTicks {@link Float The entity age in ticks}
     */
    private void applyHeadRotation(final float netHead, final float headPitch, final float ageInTicks) {
        this.head.xRot = Mth.clamp(headPitch, -25.0F, 45.0F) * ((float)Math.PI / 180F);
        this.head.yRot = Mth.clamp(netHead, -30.0F, 30.0F) * ((float)Math.PI / 180F);
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

    /**
     * Get the {@link ModelPart root Model Part}
     *
     * @return {@link ModelPart The root Model Part}
     */
    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }

}