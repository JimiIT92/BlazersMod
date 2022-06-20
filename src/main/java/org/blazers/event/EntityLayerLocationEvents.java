package org.blazers.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.entity.client.model.FireflyModel;

/**
 * Event Listener for the {@link net.minecraft.client.model.geom.ModelLayerLocation Entity Layer Locations} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class EntityLayerLocationEvents {

    /**
     * Register {@link net.minecraft.client.model.geom.ModelLayerLocation Entity Layer Locations}
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions Entity Renderers Event}
     */
    @SubscribeEvent
    public static void onLayerDefinitionsRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FireflyModel.LAYER_LOCATION, FireflyModel::createBodyLayer);
    }

}