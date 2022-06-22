package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import org.blazers.world.feature.BLOrePlacement;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
 */
public final class BLPlacedFeatures {

    //#region Placed Features

    public static final Holder<PlacedFeature> ORE_SAPPHIRE = registerCommonOre("ore_sapphire", BLConfiguredFeatures.ORE_SAPPHIRE, 7, -80, 80);
    public static final Holder<PlacedFeature> ORE_TOPAZ = registerCommonOre("ore_topaz", BLConfiguredFeatures.ORE_TOPAZ, 10, -80, 80);

    public static final Holder<PlacedFeature> ORE_PEARL = registerCommonOre("ore_pearl", BLConfiguredFeatures.ORE_PEARL, 40, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480)));

    public static final Holder<PlacedFeature> ORE_RUBY = registerCommonOre("ore_ruby", BLConfiguredFeatures.ORE_RUBY, 7, -80, 80);
    public static final Holder<PlacedFeature> ORE_MALACHITE = registerCommonOre("ore_malachite", BLConfiguredFeatures.ORE_MALACHITE, 7, PlacementUtils.RANGE_10_10);
    public static final Holder<PlacedFeature> ORE_ONICE = registerCommonOre("ore_onice", BLConfiguredFeatures.ORE_ONICE, 7, PlacementUtils.RANGE_10_10);

    public static final Holder<PlacedFeature> ORE_URANIUM = PlacementUtils.register("ore_uranium", BLConfiguredFeatures.ORE_URANIUM,
            InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CATTAIL = PlacementUtils.register("cattail", BLConfiguredFeatures.CATTAIL, AquaticPlacements.seagrassPlacement(80));

    //#endregion

    /**
     * Register a common {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param name {@link String Placed Feature Name}
     * @param oreConfiguration {@link ConfiguredFeature Ore Configured Feature}
     * @param count {@link Integer Max Ores count}
     * @param minHeight {@link Integer Min Ore generation height}
     * @param maxHeight {@link Integer Max Ore generation height}
     * @return Common {@link PlacedFeature Ore Placed Feature}
     */
    private static Holder<PlacedFeature> registerCommonOre(String name, Holder<ConfiguredFeature<OreConfiguration, ?>> oreConfiguration, int count, int minHeight, int maxHeight) {
        return registerCommonOre(name, oreConfiguration, count, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.aboveBottom(maxHeight)));
    }

    /**
     * Register a common {@link PlacedFeature Ore Placed Feature}
     * using the {@link HeightRangePlacement Triangle Height distribution}
     *
     * @param name {@link String Placed Feature Name}
     * @param oreConfiguration {@link ConfiguredFeature Ore Configured Feature}
     * @param count {@link Integer Max Ores count}
     * @param placementModifier {@link PlacementModifier Placement Modifier}
     * @return Common {@link PlacedFeature Ore Placed Feature}
     */
    private static Holder<PlacedFeature> registerCommonOre(String name, Holder<ConfiguredFeature<OreConfiguration, ?>> oreConfiguration, int count, PlacementModifier placementModifier) {
        return PlacementUtils.register(name, oreConfiguration, BLOrePlacement.commonOrePlacement(count, placementModifier));
    }
}