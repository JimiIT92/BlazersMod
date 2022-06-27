package org.blazers.world.gen;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.blazers.core.BLPlacedFeatures;

import java.util.List;
import java.util.Objects;

/**
 * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.level.block.Block Flowers} into the {@link net.minecraft.world.level.Level World}
 */
public final class BLFlowerGeneration {

    /**
     * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.level.block.Block Flowers} into the {@link net.minecraft.world.level.Level World}
     *
     * @param event {@link BiomeLoadingEvent Biome Loading Event}
     */
    public static void generateFlowers(final BiomeLoadingEvent event) {
        ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName()));
        List<Holder<PlacedFeature>> flowers = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

        if(Biomes.SWAMP.equals(biome)) {
            flowers.add(BLPlacedFeatures.CATTAIL);
        } else if(Biomes.BIRCH_FOREST.equals(biome)) {
            flowers.add(BLPlacedFeatures.FALLEN_BIRCH_TREE);
            flowers.add(BLPlacedFeatures.FALLEN_HOLLOW_BIRCH_TREE);
            flowers.add(VegetationPlacements.FLOWER_MEADOW);
        }
    }

}