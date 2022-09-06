package org.blazers.core;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.blazers.BlazersMod;
import org.blazers.world.feature.FallenTreeFeature;

public final class BLFeatures {

    public static final Feature<ProbabilityConfig> FALLEN_BIRCH_TREE = register("fallen_birch_tree", new FallenTreeFeature(Blocks.BIRCH_LOG));
    public static final Feature<ProbabilityConfig> FALLEN_HOLLOW_BIRCH_TREE = register("fallen_hollow_birch_tree", new FallenTreeFeature(BLBlocks.HOLLOW_BIRCH_LOG));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " features");
    }
}