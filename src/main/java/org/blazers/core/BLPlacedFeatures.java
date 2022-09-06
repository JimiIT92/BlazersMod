package org.blazers.core;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.blazers.world.feature.BLOrePlacement;

import java.util.List;

public final class BLPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> ORE_SAPPHIRE = registerCommonOre("ore_sapphire_placed", BLConfiguredFeatures.ORE_SAPPHIRE, 7, -80, 80);
    public static final RegistryEntry<PlacedFeature> ORE_TOPAZ = registerCommonOre("ore_topaz_placed", BLConfiguredFeatures.ORE_TOPAZ, 10, -80, 80);

    public static final RegistryEntry<PlacedFeature> ORE_PEARL = registerCommonOre("ore_pearl_placed", BLConfiguredFeatures.ORE_PEARL, 40, -16, 480);

    public static final RegistryEntry<PlacedFeature> ORE_RUBY = registerCommonOre("ore_ruby_placed", BLConfiguredFeatures.ORE_RUBY, 7, -80, 80);
    public static final RegistryEntry<PlacedFeature> ORE_MALACHITE = registerCommonOre("ore_malachite_placed", BLConfiguredFeatures.ORE_MALACHITE, 7, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE);
    public static final RegistryEntry<PlacedFeature> ORE_ONICE = registerCommonOre("ore_onice_placed", BLConfiguredFeatures.ORE_ONICE, 7, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE);

    public static final RegistryEntry<PlacedFeature> ORE_URANIUM = PlacedFeatures.register("ore_uranium_placed", BLConfiguredFeatures.ORE_URANIUM,
            List.of(SquarePlacementModifier.of(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()));

    public static final RegistryEntry<PlacedFeature> CATTAIL = PlacedFeatures.register("cattail_placed", BLConfiguredFeatures.CATTAIL, OceanPlacedFeatures.seagrassModifiers(80));

    public static final RegistryEntry<PlacedFeature> FALLEN_BIRCH_TREE = PlacedFeatures.register("fallen_birch_tree_placed",
            BLConfiguredFeatures.FALLEN_BIRCH_TREE, VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(3, 0.1F, 2)));
    public static final RegistryEntry<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = PlacedFeatures.register("fallen_hollow_birch_tree_placed",
            BLConfiguredFeatures.FALLEN_HOLLOW_BIRCH_TREE, VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(3, 0.1F, 2)));

    private static RegistryEntry<PlacedFeature> registerCommonOre(String name, RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> oreConfiguration, int count, int minHeight, int maxHeight) {
        return registerCommonOre(name, oreConfiguration, count, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(minHeight), YOffset.aboveBottom(maxHeight)));
    }

    private static RegistryEntry<PlacedFeature> registerCommonOre(String name, RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> oreConfiguration, int count, PlacementModifier placementModifier) {
        return PlacedFeatures.register(name, oreConfiguration, BLOrePlacement.commonOrePlacement(count, placementModifier));
    }
}