package org.blazers.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} Ore Placement handler
 */
public final class BLOrePlacement {

    /**
     * Get the {@link PlacementModifier Placement Modifier} for an {@link net.minecraft.world.level.block.Block Ore}
     *
     * @param countModifier {@link PlacementModifier Count modifier}
     * @param heightModifier {@link PlacementModifier Height modifier}
     * @return {@link net.minecraft.world.level.block.Block Ore Block} {@link PlacementModifier Placement Modifier}
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    /**
     * Get the {@link PlacementModifier Placement Modifier} for a common {@link net.minecraft.world.level.block.Block Ore}
     *
     * @param count {@link Integer Ore max count}
     * @param modifier {@link PlacementModifier Height modifier}
     * @return Common {@link net.minecraft.world.level.block.Block Ore Block} {@link PlacementModifier Placement Modifier}
     */
    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    /**
     * Get the {@link PlacementModifier Placement Modifier} for a rare {@link net.minecraft.world.level.block.Block Ore}
     *
     * @param count {@link Integer Ore max count}
     * @param modifier {@link PlacementModifier Height modifier}
     * @return Rare {@link net.minecraft.world.level.block.Block Ore Block} {@link PlacementModifier Placement Modifier}
     */
    public static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), modifier);
    }
}