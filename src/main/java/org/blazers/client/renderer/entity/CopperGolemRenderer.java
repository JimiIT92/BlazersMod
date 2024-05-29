package org.blazers.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.client.model.entity.CopperGolemModel;
import org.blazers.entity.CopperGolem;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link CopperGolem Copper Golem}
 */
@OnlyIn(Dist.CLIENT)
public final class CopperGolemRenderer extends MobRenderer<CopperGolem, CopperGolemModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Entity Renderer Context}
     */
    public CopperGolemRenderer(final EntityRendererProvider.Context context) {
        super(context, new CopperGolemModel(context.bakeLayer(CopperGolemModel.LAYER_LOCATION)), 0.4F);
    }


    /**
     * Get the {@link ResourceLocation entity texture location}
     *
     * @param entity {@link CopperGolem The entity}
     * @return {@link ResourceLocation The entity texture location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull CopperGolem entity) {
        return RegistryHelper.location("textures/entity/copper_golem/" + entity.getWeatherStateName(entity.getCurrentWeatherState()) + ".png");
    }

}