package org.blazers.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.CopperGolem;
import org.blazers.entity.client.model.CopperGolemModel;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

/**
 * Renderer class for a {@link CopperGolem Copper Golem}
 */
@OnlyIn(Dist.CLIENT)
public class CopperGolemRenderer extends DynamicGeoEntityRenderer<CopperGolem> {

    /**
     * Constructor. Set up the {@link CopperGolem Copper Golem} Renderer and {@link Float Shadow Radius}
     *
     * @param renderManager {@link EntityRendererProvider.Context Render Manager}
     */
    public CopperGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CopperGolemModel());
        this.shadowRadius = 0.4F;
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link ResourceLocation Texture Resource Location}
     *
     * @param entity {@link CopperGolem Copper Golem}
     * @return {@link CopperGolem Copper Golem} {@link ResourceLocation Texture Resource Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(CopperGolem entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/copper_golem/" + entity.getCurrentWeatherStateName() + ".png");
    }
}