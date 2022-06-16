package org.blazers.event;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.world.feature.gen.BLOreGeneration;

/**
 * Event Listener for the {@link net.minecraft.world.level.Level World} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class WorldEvents {

    /**
     * Generate the {@link BlazersMod Blazers Mod} {@link org.blazers.core.BLPlacedFeatures Placed Features}
     * into the {@link net.minecraft.world.level.Level World}
     *
     * @param event {@link BiomeLoadingEvent Biome Loading Event}
     */
    @SubscribeEvent
    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        BLOreGeneration.generateOres(event);
    }
}