package org.blazers.client.renderer.layer;

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
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.entity.animal.WitherSkeletonHorse;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for the {@link WitherSkeletonHorse Wither Skeleton Horse} armor layer
 */
@OnlyIn(Dist.CLIENT)
public final class WitherSkeletonHorseArmorLayer extends RenderLayer<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> {

    /**
     * The {@link WitherSkeletonHorse Wither Skeleton Horse} {@link HorseModel Model}
     */
    private final HorseModel<WitherSkeletonHorse> model;

    /**
     * Constructor. Set the layer properties
     *
     * @param parent {@link RenderLayerParent The parent render layer}
     * @param modelSet {@link EntityModelSet The entity models}
     */
    public WitherSkeletonHorseArmorLayer(final RenderLayerParent<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> parent, final EntityModelSet modelSet) {
        super(parent);
        this.model = new HorseModel<>(modelSet.bakeLayer(ModelLayers.HORSE_ARMOR));
    }

    /**
     * Render the layer
     *
     * @param poseStack {@link PoseStack The entity pose stack}
     * @param buffer {@link MultiBufferSource The entity buffer}
     * @param packedLight {@link Integer The client packed light}
     * @param entity {@link WitherSkeletonHorse The entity}
     * @param limbSwing {@link Float The entity limb swing}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param partialTick {@link Float The entity partial ticks}
     * @param ageInTicks {@link Float The entity age in ticks}
     * @param netHeadYaw {@link Float The entity net head yaw}
     * @param headPitch {@link Float The entity head pitch}
     */
    @Override
    public void render(final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight, final @NotNull WitherSkeletonHorse entity, final float limbSwing, final float limbSwingAmount, final float partialTick, final float ageInTicks, final float netHeadYaw, final float headPitch) {
        ItemStack itemstack = entity.getBodyArmorItem();
        if (itemstack.getItem() instanceof AnimalArmorItem horsearmoritem && horsearmoritem.getBodyType().equals(AnimalArmorItem.BodyType.EQUESTRIAN)) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            float red = 1.0F;
            float green = 1.0F;
            float blue = 1.0F;
            if (itemstack.is(ItemTags.DYEABLE)) {
                final int color = DyedItemColor.getOrDefault(itemstack, -6265536);
                red = (float) FastColor.ARGB32.red(color) / 255.0F;
                green = (float)FastColor.ARGB32.green(color) / 255.0F;
                blue = (float)FastColor.ARGB32.blue(color) / 255.0F;
            }

            final VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(horsearmoritem.getTexture()));
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
        }
    }

}