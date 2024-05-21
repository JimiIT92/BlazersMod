package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.blazers.BlazersMod;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.RegistryHelper;

import java.util.List;

/**
 * {@link BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
 */
public final class BLPlacedFeatures {

    //#region Placed Features

    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.SAPPHIRE));
    public static final ResourceKey<PlacedFeature> ORE_TOPAZ = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.TOPAZ));
    public static final ResourceKey<PlacedFeature> ORE_PEARL = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.PEARL));
    public static final ResourceKey<PlacedFeature> ORE_RUBY = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.RUBY));
    public static final ResourceKey<PlacedFeature> ORE_MALACHITE = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.MALACHITE));
    public static final ResourceKey<PlacedFeature> ORE_ONICE = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.ONICE));
    public static final ResourceKey<PlacedFeature> ORE_URANIUM = registerOrePlacedFeatureKey(BlockHelper.materialName(BLMaterials.URANIUM));
    public static final ResourceKey<PlacedFeature> CATTAIL = registerPlacedFeatureKey("cattail");
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_TREE = registerPlacedFeatureKey("fallen_birch_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = registerPlacedFeatureKey("fallen_hollow_birch_tree");

    //#endregion

    //#region Methods

    /**
     * Register an {@link PlacedFeature Ore} {@link ResourceKey Resource Key}
     *
     * @param oreName {@link String The Ore name}
     * @return {@link ResourceKey<DamageType> The Ore Resource Key}
     */
    private static ResourceKey<PlacedFeature> registerOrePlacedFeatureKey(final String oreName) {
        return registerPlacedFeatureKey("ore_" + oreName);
    }

    /**
     * Register an {@link PlacedFeature Configured Feature} {@link ResourceKey Resource Key}
     *
     * @param name {@link String The Configured Feature name}
     * @return {@link ResourceKey<DamageType> The Configured Feature Resource Key}
     */
    private static ResourceKey<PlacedFeature> registerPlacedFeatureKey(final String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, RegistryHelper.location(name + "_placed"));
    }

    /**
     * Register an {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link String The Placed Feature Resource Key}
     * @param oreConfiguration {@link ConfiguredFeature The Ore Configured Feature}
     * @param count {@link Integer The Max Ores count}
     * @param minHeight {@link Integer The min Ore generation height}
     * @param maxHeight {@link Integer The max Ore generation height}
     */
    private static void registerOre(final BootstrapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> oreKey, final Holder.Reference<ConfiguredFeature<?, ?>> oreConfiguration, final int count, final int minHeight, final int maxHeight) {
        registerOre(context, oreKey, oreConfiguration, count, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.aboveBottom(maxHeight)));
    }

    /**
     * Register ann {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link String The Placed Feature Resource Key}
     * @param oreConfiguration {@link ConfiguredFeature The Ore Configured Feature}
     * @param count {@link Integer The Max Ores count}
     * @param placementModifier {@link PlacementModifier Placement Modifier}
     */
    private static void registerOre(final BootstrapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> oreKey, final Holder.Reference<ConfiguredFeature<?, ?>> oreConfiguration, final int count, final PlacementModifier placementModifier) {
        PlacementUtils.register(context, oreKey, oreConfiguration, List.of(CountPlacement.of(count), InSquarePlacement.spread(), placementModifier, BiomeFilter.biome()));
    }

    /**
     * Register a {@link PlacedFeature Fallen Tree Placed Feature}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param fallenTreeKey {@link String The Fallen Tree Resource Key}
     * @param fallenTreeConfiguration {@link ConfiguredFeature The Fallen Tree Configured Feature}
     */
    private static void registerFallenTree(final BootstrapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> fallenTreeKey, final Holder.Reference<ConfiguredFeature<?, ?>> fallenTreeConfiguration) {
        PlacementUtils.register(context, fallenTreeKey, fallenTreeConfiguration, VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2)));
    }

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
     *
     * @param context {@link BootstrapContext<PlacedFeature> The Bootstrap Context}
     */
    public static void bootstrap(final BootstrapContext<PlacedFeature> context) {
        final HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        registerOre(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_SAPPHIRE), 7, -80, 80);
        registerOre(context, ORE_TOPAZ, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_TOPAZ), 10, -80, 80);
        registerOre(context, ORE_PEARL, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_PEARL), 40, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480)));
        registerOre(context, ORE_RUBY, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_RUBY), 7, -80, 80);
        registerOre(context, ORE_MALACHITE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_MALACHITE), 7, PlacementUtils.RANGE_10_10);
        registerOre(context, ORE_ONICE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_ONICE), 7, PlacementUtils.RANGE_10_10);
        context.register(ORE_URANIUM, new PlacedFeature(configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_URANIUM), List.of(InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome())));

        PlacementUtils.register(context, CATTAIL, configuredFeatures.getOrThrow(BLConfiguredFeatures.CATTAIL), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(80), BiomeFilter.biome()));

        registerFallenTree(context, FALLEN_BIRCH_TREE, configuredFeatures.getOrThrow(BLConfiguredFeatures.FALLEN_BIRCH_TREE));
        registerFallenTree(context, FALLEN_HOLLOW_BIRCH_TREE, configuredFeatures.getOrThrow(BLConfiguredFeatures.FALLEN_HOLLOW_BIRCH_TREE));
    }

    //#endregion

}