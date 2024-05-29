package org.blazers.client.renderer.entity;

import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.client.renderer.layer.WitherSkeletonHorseArmorLayer;
import org.blazers.entity.animal.WitherSkeletonHorse;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link WitherSkeletonHorse Wither Skeleton Horse}
 */
@OnlyIn(Dist.CLIENT)
public final class WitherSkeletonHorseRenderer extends AbstractHorseRenderer<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Entity Renderer Context}
     */
    public WitherSkeletonHorseRenderer(final EntityRendererProvider.Context context) {
        super(context, new HorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 1.1F);
        this.addLayer(new WitherSkeletonHorseArmorLayer(this, context.getModelSet()));
    }

    /**
     * Get the {@link ResourceLocation entity texture location}
     *
     * @param entity {@link WitherSkeletonHorse The entity}
     * @return {@link ResourceLocation The entity texture location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull WitherSkeletonHorse entity) {
        return RegistryHelper.location("textures/entity/horse/horse_wither_skeleton.png");
    }

}