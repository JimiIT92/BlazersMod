package org.blazers.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLPaintings;
import org.blazers.core.BLSounds;

/**
 * Handle all events for {@link Painting Paintings}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class PaintingEvents {

    /**
     * Play a sound if the {@link BLPaintings#BRUH Bruh Painting} is placed
     *
     * @param event {@link EntityJoinLevelEvent The entity join level event}
     */
    @SubscribeEvent
    public static void onPaintingPlaced(final EntityJoinLevelEvent event) {
        if(!event.isCanceled() && !event.loadedFromDisk()) {
            checkBruhPainting(event.getEntity(), true);
        }
    }

    /**
     * Play a sound if the {@link BLPaintings#BRUH Bruh Painting} is destroyed
     *
     * @param event {@link EntityLeaveLevelEvent The entity leave level event}
     */
    @SubscribeEvent
    public static void onPaintingPlaced(final EntityLeaveLevelEvent event) {
        if(!event.isCanceled()) {
            checkBruhPainting(event.getEntity(), false);
        }
    }

    /**
     * Check if the {@link Entity entity} is the {@link BLPaintings#BRUH Bruh Painting}.
     * If so, play a sound when is placed or destroyed
     *
     * @param entity {@link Entity The entity to check}
     * @param isJoining {@link Boolean If the entity is joining the level}
     */
    private static void checkBruhPainting(final Entity entity, final boolean isJoining) {
        if(entity instanceof Painting painting && painting.getVariant().value().equals(BLPaintings.BRUH.get())) {
            entity.playSound((isJoining ? BLSounds.BRUH : BLSounds.REVERSED_BRUH).get());
        }
    }
}