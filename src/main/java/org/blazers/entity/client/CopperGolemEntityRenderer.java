package org.blazers.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.entity.CopperGolemEntity;
import org.blazers.entity.client.model.CopperGolemModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CopperGolemEntityRenderer extends GeoEntityRenderer<CopperGolemEntity> {

    public CopperGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CopperGolemModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public Identifier getTextureLocation(CopperGolemEntity entity) {
        return new Identifier(BlazersMod.MOD_ID, "textures/entity/copper_golem/" + entity.getCurrentWeatherStateName() + ".png");
    }
}