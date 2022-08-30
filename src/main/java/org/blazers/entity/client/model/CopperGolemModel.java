package org.blazers.entity.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.CopperGolem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * Model class for a {@link CopperGolem Copper Golem}
 */
@OnlyIn(Dist.CLIENT)
public class CopperGolemModel extends AnimatedGeoModel<CopperGolem> {

    /**
     * Get the {@link CopperGolem Copper Golem} {@link ResourceLocation Model Resource Location}
     *
     * @param entity {@link CopperGolem Copper Golem}
     * @return {@link CopperGolem Copper Golem} {@link ResourceLocation Model Resource Location}
     */
    @Override
    public ResourceLocation getModelResource(CopperGolem entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "geo/copper_golem.geo.json");
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link ResourceLocation Texture Resource Location}
     *
     * @param entity {@link CopperGolem Copper Golem}
     * @return {@link CopperGolem Copper Golem} {@link ResourceLocation Texture Resource Location}
     */
    @Override
    public ResourceLocation getTextureResource(CopperGolem entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/copper_golem/" + entity.getCurrentWeatherStateName() + ".png");
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link ResourceLocation Animation Resource Location}
     *
     * @param entity {@link CopperGolem Copper Golem}
     * @return {@link CopperGolem Copper Golem} {@link ResourceLocation Animation Resource Location}
     */
    @Override
    public ResourceLocation getAnimationResource(CopperGolem entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "animations/copper_golem.animation.json");
    }
}