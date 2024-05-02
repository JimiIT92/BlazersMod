package org.blazers.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

/**
 * Helper methods for {@link Level Level}
 */
public final class LevelHelper {

    /**
     * Check if a {@link BlockPos Block Pos} is underwater
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the location is underwater}
     */
    public static boolean isUnderwater(final Level level, final BlockPos blockPos) {
        return level.getFluidState(blockPos).is(Fluids.WATER) || level.getFluidState(blockPos.above()).is(Fluids.WATER)
                || level.getFluidState(blockPos.below()).is(Fluids.WATER) || level.getFluidState(blockPos.north()).is(Fluids.WATER)
                || level.getFluidState(blockPos.south()).is(Fluids.WATER) || level.getFluidState(blockPos.east()).is(Fluids.WATER)
                || level.getFluidState(blockPos.west()).is(Fluids.WATER);
    }

}