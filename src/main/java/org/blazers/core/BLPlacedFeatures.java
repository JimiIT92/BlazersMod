package org.blazers.core;


import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.blazers.BlazersMod;

import java.util.List;

public final class BLPlacedFeatures {

    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE = registerKey("ore_sapphire_placed");
    public static final RegistryKey<PlacedFeature> ORE_TOPAZ = registerKey("ore_topaz_placed");
    public static final RegistryKey<PlacedFeature> ORE_PEARL = registerKey("ore_pearl_placed");
    public static final RegistryKey<PlacedFeature> ORE_RUBY = registerKey("ore_ruby_placed");
    public static final RegistryKey<PlacedFeature> ORE_MALACHITE = registerKey("ore_malachite_placed");
    public static final RegistryKey<PlacedFeature> ORE_ONICE = registerKey("ore_onice_placed");
    public static final RegistryKey<PlacedFeature> ORE_URANIUM = registerKey("ore_uranium_placed");
    public static final RegistryKey<PlacedFeature> CATTAIL = registerKey("cattail_placed");
    public static final RegistryKey<PlacedFeature> FALLEN_BIRCH_TREE = registerKey("fallen_birch_tree_placed");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(context, ORE_SAPPHIRE, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_SAPPHIRE), getCommonOrePlacements(7, -80, 80));
        register(context, ORE_TOPAZ, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_TOPAZ), getCommonOrePlacements(10, -80, 80));
        register(context, ORE_PEARL, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_PEARL), getCommonOrePlacements(40, -16, 480));

        register(context, ORE_RUBY, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_RUBY), getCommonOrePlacements(7, -80, 80));
        register(context, ORE_MALACHITE, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_MALACHITE), modifiersWithCount(7, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        register(context, ORE_ONICE, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_ONICE), modifiersWithCount(7, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));

        register(context, ORE_URANIUM, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.ORE_URANIUM), List.of(SquarePlacementModifier.of(), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()));

        register(context, CATTAIL, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.CATTAIL), List.of(SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, CountPlacementModifier.of(80), BiomePlacementModifier.of()));

        register(context, FALLEN_BIRCH_TREE, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.FALLEN_BIRCH_TREE), VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(3, 0.1F, 2)));
        register(context, FALLEN_HOLLOW_BIRCH_TREE, configuredFeatureRegistryEntryLookup.getOrThrow(BLConfiguredFeatures.FALLEN_HOLLOW_BIRCH_TREE), VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(3, 0.1F, 2)));
    }

    private static List<PlacementModifier> getCommonOrePlacements(int count, int minHeight, int maxHeight) {
        return modifiersWithCount(count, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(minHeight), YOffset.aboveBottom(maxHeight)));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static RegistryKey<PlacedFeature> registerKey(final String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(BlazersMod.MOD_ID, name));
    }
}