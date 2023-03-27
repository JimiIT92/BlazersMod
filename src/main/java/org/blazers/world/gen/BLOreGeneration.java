package org.blazers.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class BLOreGeneration {

    public static void generateOres() {
       /* addOverworldOre(BLPlacedFeatures.ORE_SAPPHIRE);
        addOverworldOre(BLPlacedFeatures.ORE_TOPAZ);
        addUnderwaterOre(BLPlacedFeatures.ORE_PEARL);
        addNetherOre(BLPlacedFeatures.ORE_RUBY);
        addNetherOre(BLPlacedFeatures.ORE_MALACHITE);
        addNetherOre(BLPlacedFeatures.ORE_ONICE);
        addNetherOre(BLPlacedFeatures.ORE_URANIUM);*/
    }

    private static void addOverworldOre(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }

    private static void addNetherOre(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }

    private static void addUnderwaterOre(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.WARM_OCEAN,
                BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN
        ), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }
}