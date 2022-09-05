package org.blazers.core;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.entity.client.model.FireflyModel;
import org.blazers.entity.client.model.MalachiteSpearModel;
import org.blazers.entity.client.model.SpearModel;

public final class BLModelLayers {

    public static final EntityModelLayer FIREFLY = register("firefly");
    public static final EntityModelLayer SPEAR = register("spear");
    public static final EntityModelLayer MALACHITE_SPEAR = register("malachite_spear");

    private static EntityModelLayer register(String name) {
        return new EntityModelLayer(new Identifier(BlazersMod.MOD_ID, name), EntityModelPartNames.CUBE);
    }

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(FIREFLY, FireflyModel::getTextureModelData);
        EntityModelLayerRegistry.registerModelLayer(SPEAR, SpearModel::getTextureModelData);
        EntityModelLayerRegistry.registerModelLayer(MALACHITE_SPEAR, MalachiteSpearModel::getTextureModelData);
    }
}