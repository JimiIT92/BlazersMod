package org.blazers.entity.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.DyeableHorseArmorItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.entity.WitherSkeletonHorse;
import org.jetbrains.annotations.NotNull;

/**
 * {@link WitherSkeletonHorse Wither Skeleton Horse} {@link net.minecraft.client.renderer.entity.layers.HorseArmorLayer Armor Layer}
 */
@OnlyIn(Dist.CLIENT)
public class WitherSkeletonHorseArmorLayer extends RenderLayer<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> {

    /**
     * {@link WitherSkeletonHorse Wither Skeleton Horse} {@link HorseModel Model}
     */
    private final HorseModel<WitherSkeletonHorse> model;

    /**
     * Constructor. Sets the {@link WitherSkeletonHorse Wither Skeleton Horse} {@link HorseModel Model}
     *
     * @param renderer {@link RenderLayerParent Render Layer}
     * @param modelSet {@link EntityModelSet Model Set}
     */
    public WitherSkeletonHorseArmorLayer(RenderLayerParent<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new HorseModel<>(modelSet.bakeLayer(ModelLayers.HORSE_ARMOR));
    }

    /**
     * Render the {@link net.minecraft.client.renderer.entity.layers.HorseArmorLayer Horse Armor}
     *
     * @param poseStack {@link PoseStack Pose Stack}
     * @param buffer {@link MultiBufferSource Buffer Source}
     * @param packedLight {@link Integer Packed Light}
     * @param entity {@link WitherSkeletonHorse Wither Skeleton Horse}
     * @param limbSwing {@link Float Limb Swing}
     * @param limbSwingAmount {@link Float Limb Swing Amount}
     * @param partialTick {@link Float Partial Tick}
     * @param age {@link Float Age}
     * @param headYaw {@link Float Head Yaw}
     * @param headPitch {@link Float Head Pitch}
     */
    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, WitherSkeletonHorse entity,
                       float limbSwing, float limbSwingAmount, float partialTick, float age, float headYaw, float headPitch) {
        ItemStack itemstack = entity.getArmor();
        if (itemstack.getItem() instanceof HorseArmorItem horsearmoritem) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch);
            float red = 1.0F;
            float green = 1.0F;
            float blue = 1.0F;
            if (horsearmoritem instanceof DyeableHorseArmorItem) {
                int color = ((DyeableHorseArmorItem)horsearmoritem).getColor(itemstack);
                red = (float)(color >> 16 & 255) / 255.0F;
                green = (float)(color >> 8 & 255) / 255.0F;
                blue = (float)(color & 255) / 255.0F;
            }

            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(horsearmoritem.getTexture()));
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
        }
    }
}