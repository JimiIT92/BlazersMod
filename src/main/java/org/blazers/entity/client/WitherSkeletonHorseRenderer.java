package org.blazers.entity.client;

import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.WitherSkeletonHorse;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link WitherSkeletonHorse Wither Skeleton Horse}
 */
@OnlyIn(Dist.CLIENT)
public class WitherSkeletonHorseRenderer extends AbstractHorseRenderer<WitherSkeletonHorse, HorseModel<WitherSkeletonHorse>> {

    /**
     * Constructor. Sets the {@link AbstractHorseRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public WitherSkeletonHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new HorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 1.1F);
    }

    /**
     * Get the {@link ResourceLocation Wither Skeleton Horse texture}
     *
     * @param entity {@link WitherSkeletonHorse Wither Skeleton Horse}
     * @return {@link ResourceLocation Wither Skeleton Horse texture}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WitherSkeletonHorse entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/horse/horse_wither_skeleton.png");
    }
}