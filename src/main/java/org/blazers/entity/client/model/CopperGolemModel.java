package org.blazers.entity.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.entity.CopperGolemEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@Environment(EnvType.CLIENT)
public class CopperGolemModel extends AnimatedGeoModel<CopperGolemEntity> {

    @Override
    public Identifier getModelResource(CopperGolemEntity entity) {
        return new Identifier(BlazersMod.MOD_ID, "geo/copper_golem.geo.json");
    }

    @Override
    public Identifier getTextureResource(CopperGolemEntity entity) {
        return new Identifier(BlazersMod.MOD_ID, "textures/entity/copper_golem/" + entity.getCurrentWeatherStateName() + ".png");
    }

    @Override
    public Identifier getAnimationResource(CopperGolemEntity entity) {
        return new Identifier(BlazersMod.MOD_ID, "animations/copper_golem.animation.json");
    }
}

