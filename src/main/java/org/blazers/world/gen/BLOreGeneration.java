package org.blazers.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.blazers.core.BLPlacedFeatures;

public class BLOreGeneration {

    public static void generateOres() {
        addOverworldOre(BLPlacedFeatures.ORE_SAPPHIRE);
        addOverworldOre(BLPlacedFeatures.ORE_TOPAZ);
        addUnderwaterOre(BLPlacedFeatures.ORE_PEARL);
        addNetherOre(BLPlacedFeatures.ORE_RUBY);
        addNetherOre(BLPlacedFeatures.ORE_MALACHITE);
        addNetherOre(BLPlacedFeatures.ORE_ONICE);
        addNetherOre(BLPlacedFeatures.ORE_URANIUM);
    }

    private static void addOverworldOre(RegistryEntry<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ore.getKey().get());
    }

    private static void addNetherOre(RegistryEntry<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ore.getKey().get());
    }

    private static void addUnderwaterOre(RegistryEntry<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.WARM_OCEAN,
                BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN
        ), GenerationStep.Feature.UNDERGROUND_ORES, ore.getKey().get());
    }
}