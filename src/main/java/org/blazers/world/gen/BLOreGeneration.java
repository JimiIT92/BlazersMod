package org.blazers.world.gen;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.blazers.core.BLPlacedFeatures;

import java.util.List;

/**
 * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.level.block.Block Ores} into the {@link net.minecraft.world.level.Level World}
 */
public final class BLOreGeneration {

    /**
     * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.level.block.Block Ores} into the {@link net.minecraft.world.level.Level World}
     *
     * @param event {@link BiomeLoadingEvent Biome Loading Event}
     */
    public static void generateOres(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder biomeGeneration = event.getGeneration();
        List<Holder<PlacedFeature>> ores = biomeGeneration.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
        ResourceLocation biomeName = event.getName();
        Biome.BiomeCategory biomeCategory = event.getCategory();

        if(Biomes.WARM_OCEAN.location().equals(biomeName) ||
                Biomes.LUKEWARM_OCEAN.location().equals(biomeName) ||
                Biomes.DEEP_LUKEWARM_OCEAN.location().equals(biomeName)) {
            List<Holder<PlacedFeature>> topLayer = biomeGeneration.getFeatures(GenerationStep.Decoration.TOP_LAYER_MODIFICATION);
            topLayer.add(BLPlacedFeatures.ORE_PEARL);
        } else {
            switch (biomeCategory) {
                case NETHER -> addNetherOres(ores);
                case NONE, THEEND -> {}
                default -> addOverworldOres(ores);
            }
        }
    }

    /**
     * Add the Nether Ores to the {@link net.minecraft.world.level.Level World}
     *
     * @param ores {@link Holder<PlacedFeature> Biome Ores}
     */
    private static void addNetherOres(final List<Holder<PlacedFeature>> ores) {
        ores.add(BLPlacedFeatures.ORE_RUBY);
        ores.add(BLPlacedFeatures.ORE_MALACHITE);
        ores.add(BLPlacedFeatures.ORE_ONICE);
        ores.add(BLPlacedFeatures.ORE_URANIUM);
    }

    /**
     * Add the Nether Ores to the {@link net.minecraft.world.level.Level World}
     *
     * @param ores {@link Holder<PlacedFeature> Biome Ores}
     */
    private static void addOverworldOres(final List<Holder<PlacedFeature>> ores) {
        ores.add(BLPlacedFeatures.ORE_SAPPHIRE);
        ores.add(BLPlacedFeatures.ORE_TOPAZ);
    }
}