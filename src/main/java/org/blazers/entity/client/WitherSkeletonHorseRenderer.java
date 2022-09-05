package org.blazers.entity.client;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.entity.WitherSkeletonHorse;
import org.blazers.entity.client.layer.WitherSkeletonHorseArmorFeatureRenderer;

public class WitherSkeletonHorseRenderer extends AbstractHorseEntityRenderer<WitherSkeletonHorse, HorseEntityModel<WitherSkeletonHorse>> {

    public WitherSkeletonHorseRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseEntityModel<>(context.getPart(EntityModelLayers.HORSE)), 1.1F);
        this.addFeature(new WitherSkeletonHorseArmorFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(WitherSkeletonHorse entity) {
        return new Identifier(BlazersMod.MOD_ID, "textures/entity/horse/horse_wither_skeleton.png");
    }
}
