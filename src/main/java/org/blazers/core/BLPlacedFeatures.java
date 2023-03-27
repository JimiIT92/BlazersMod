package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.blazers.BlazersMod;
import org.blazers.world.feature.BLOrePlacement;

import java.util.List;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
 */
public final class BLPlacedFeatures {

    //#region Placed Features

    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = registerKey("ore_sapphire_placed");
    public static final ResourceKey<PlacedFeature> ORE_TOPAZ = registerKey("ore_topaz_placed");

    public static final ResourceKey<PlacedFeature> ORE_PEARL = registerKey("ore_pearl_placed");

    public static final ResourceKey<PlacedFeature> ORE_RUBY = registerKey("ore_ruby_placed");
    public static final ResourceKey<PlacedFeature> ORE_MALACHITE = registerKey("ore_malachite_placed");
    public static final ResourceKey<PlacedFeature> ORE_ONICE = registerKey("ore_onice_placed");

    public static final ResourceKey<PlacedFeature> ORE_URANIUM = registerKey("ore_uranium_placed");

    public static final ResourceKey<PlacedFeature> CATTAIL = registerKey("cattail_placed");

    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_TREE = registerKey("fallen_birch_tree_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree_placed");

    //#endregion

    /**
     * Register the {@link ConfiguredFeature Configured Features}
     *
     * @param context {@link BootstapContext <ConfiguredFeature> Bootstrap Context}
     */
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        registerCommonOre(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_SAPPHIRE), 7, -80, 80);
        registerCommonOre(context, ORE_TOPAZ, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_TOPAZ), 10, -80, 80);

        registerCommonOre(context, ORE_PEARL, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_PEARL), 40, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480)));

        registerCommonOre(context, ORE_RUBY, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_RUBY), 7, -80, 80);
        registerCommonOre(context, ORE_MALACHITE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_MALACHITE), 7, PlacementUtils.RANGE_10_10);
        registerCommonOre(context, ORE_ONICE, configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_ONICE), 7, PlacementUtils.RANGE_10_10);

        context.register(ORE_URANIUM, new PlacedFeature(configuredFeatures.getOrThrow(BLConfiguredFeatures.ORE_URANIUM), List.of(InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome())));

        context.register(CATTAIL, new PlacedFeature(configuredFeatures.getOrThrow(BLConfiguredFeatures.CATTAIL), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(80), BiomeFilter.biome())));

        context.register(FALLEN_BIRCH_TREE, new PlacedFeature(configuredFeatures.getOrThrow(BLConfiguredFeatures.FALLEN_BIRCH_TREE), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2))));
        context.register(FALLEN_HOLLOW_BIRCH_TREE, new PlacedFeature(configuredFeatures.getOrThrow(BLConfiguredFeatures.FALLEN_HOLLOW_BIRCH_TREE), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2))));
    }

    /**
     * Register a common {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param context {@link BootstapContext Bootstrap Context}
     * @param key {@link String Placed Feature Resource Key}
     * @param oreConfiguration {@link ConfiguredFeature Ore Configured Feature}
     * @param count {@link Integer Max Ores count}
     * @param minHeight {@link Integer Min Ore generation height}
     * @param maxHeight {@link Integer Max Ore generation height}
     * @return Common {@link PlacedFeature Ore Placed Feature}
     */
    private static void registerCommonOre(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder.Reference<ConfiguredFeature<?, ?>> oreConfiguration, int count, int minHeight, int maxHeight) {
        registerCommonOre(context, key, oreConfiguration, count, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.aboveBottom(maxHeight)));
    }

    /**
     * Register a common {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param context {@link BootstapContext Bootstrap Context}
     * @param key {@link String Placed Feature Resource Key}
     * @param oreConfiguration {@link ConfiguredFeature Ore Configured Feature}
     * @param count {@link Integer Max Ores count}
     * @param placementModifier {@link PlacementModifier Placement Modifier}
     */
    private static void registerCommonOre(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder.Reference<ConfiguredFeature<?, ?>> oreConfiguration, int count, PlacementModifier placementModifier) {
        context.register(key, new PlacedFeature(oreConfiguration, BLOrePlacement.commonOrePlacement(count, placementModifier)));
    }

    /**
     * Register a {@link ResourceKey Resource Key}
     *
     * @param name {@link String Resource Key Name}
     * @return {@link ResourceKey Registered Resource Key}
     */
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BlazersMod.MOD_ID, name));
    }
}