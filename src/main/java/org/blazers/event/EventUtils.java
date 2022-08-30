package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;

/**
 * Utility methods for firing Events
 */
public final class EventUtils {

    /**
     * Fire a {@link Level World} Event
     *
     * @param level {@link Level World Reference}
     * @param player {@link Player Player Reference}
     * @param event {@link WorldEvents World Event}
     * @param pos {@link Vec3 Block Pos}
     */
    public static void fireWorldEvent(@Nonnull final Level level, final Player player, final WorldEvents event, final Vec3 pos) {
        fireWorldEvent(level, player, event, new BlockPos(pos));
    }

    /**
     * Fire a {@link Level World} Event
     *
     * @param level {@link Level World Reference}
     * @param player {@link Player Player Reference}
     * @param event {@link WorldEvents World Event}
     * @param pos {@link BlockPos Block Pos}
     */
    public static void fireWorldEvent(@Nonnull final Level level, final Player player, final WorldEvents event, final BlockPos pos) {
        level.levelEvent(player, event.getID(), pos, 0);
    }

    /**
     * World Events
     */
    public enum WorldEvents {
        POINTED_DRIPSTONE_FALLING(1045),
        HONEYCOMB_WAX_ON(3003),
        AXE_WAX_OFF(3004),
        AXE_SCRAPE(3005);

        /**
         * {@link Integer Event ID}
         */
        private final int ID;

        /**
         * Constructor. Sets the {@link Integer Event ID}
         *
         * @param id {@link Integer Event ID}
         */
        WorldEvents(final int id) {
            ID = id;
        }

        /**
         * Get the {@link Integer Event ID}
         *
         * @return {@link Integer Event ID}
         */
        public int getID() {
            return ID;
        }
    }
}