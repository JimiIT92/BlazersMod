package org.blazers.client.event;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.client.model.ThrownMalachiteSpearModel;
import org.blazers.client.model.ThrownSpearModel;

/**
 * Handle all events for {@link LayerDefinition Layer definitions}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class LayerDefinitionEvents {

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link LayerDefinition Layer definitions}
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions The Layer Definition Register Event}
     */
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrownSpearModel.LAYER_LOCATION, ThrownSpearModel::createBodyLayer);
        event.registerLayerDefinition(ThrownMalachiteSpearModel.LAYER_LOCATION, ThrownMalachiteSpearModel::createBodyLayer);
    }
}