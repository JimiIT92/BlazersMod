package org.blazers.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.blazers.entity.animal.WitherSkeletonHorse;

/**
 * Handle all events for entity attributes
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityAttributesEvents {

    /**
     * Register all entity attributes
     *
     * @param event {@link EntityAttributeCreationEvent The entity attribute creation event}
     */
    @SubscribeEvent
    public static void onEntityAttributeSetup(final EntityAttributeCreationEvent event) {
        event.put(BLEntityTypes.WITHER_SKELETON_HORSE.get(), WitherSkeletonHorse.createAttributes().build());
    }

}