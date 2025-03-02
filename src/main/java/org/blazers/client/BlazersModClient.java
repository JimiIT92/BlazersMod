package org.blazers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.entity.EntityType;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.model.MalachiteSpearEntityModel;
import org.blazers.entity.model.SpearEntityModel;
import org.blazers.entity.renderer.MalachiteSpearEntityRenderer;
import org.blazers.entity.renderer.MalachiteSpearModelRenderer;
import org.blazers.entity.renderer.SpearEntityRenderer;
import org.blazers.entity.renderer.SpearModelRenderer;
import org.hendrix.client.registry.HCItemModels;
import org.hendrix.registry.HCEntities;

/**
 * {@link BlazersMod Blazers Mod} {@link ClientModInitializer Client initializer}
 */
@Environment(EnvType.CLIENT)
public final class BlazersModClient implements ClientModInitializer {

    /**
     * Initialize the mod's client stuff
     */
    @Override
    public void onInitializeClient() {
        registerModelLayers();
        registerEntityRenderers();

        registerItemModels();
    }

    /**
     * Register all {@link SpecialModelRenderer Item Models}
     */
    public static void registerItemModels() {
        HCItemModels.itemModel(BLItems.SPEAR, new SpearModelRenderer.Unbaked());
        HCItemModels.itemModel(BLItems.MALACHITE_SPEAR, new MalachiteSpearModelRenderer.Unbaked());
    }

    /**
     * Register all {@link EntityModelLayer Entity Model Layers}
     */
    public static void registerModelLayers() {
        HCEntities.registerModelLayer(BLModelLayers.SPEAR, SpearEntityModel::getTextureModelData);
        HCEntities.registerModelLayer(BLModelLayers.MALACHITE_SPEAR, MalachiteSpearEntityModel::getTextureModelData);
    }

    /**
     * Register all {@link EntityType Entities} rendering
     */
    public static void registerEntityRenderers() {
        HCEntities.registerRenderer(BLEntityTypes.SPEAR, SpearEntityRenderer::new);
        HCEntities.registerRenderer(BLEntityTypes.MALACHITE_SPEAR, MalachiteSpearEntityRenderer::new);
    }

}
