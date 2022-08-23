package org.blazers.core;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.world.feature.BLOrePlacement;

import java.util.List;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
 */
public final class BLPlacedFeatures {

    /**
     * {@link PlacedFeature Placed Features} {@link DeferredRegister<PlacedFeature> Registry}
     */
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BlazersMod.MOD_ID);

    //#region Placed Features

    public static final RegistryObject<PlacedFeature> ORE_SAPPHIRE = registerCommonOre("ore_sapphire_placed", BLConfiguredFeatures.ORE_SAPPHIRE, 7, -80, 80);
    public static final RegistryObject<PlacedFeature> ORE_TOPAZ = registerCommonOre("ore_topaz_placed", BLConfiguredFeatures.ORE_TOPAZ, 10, -80, 80);

    public static final RegistryObject<PlacedFeature> ORE_PEARL = registerCommonOre("ore_pearl_placed", BLConfiguredFeatures.ORE_PEARL, 40, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480)));

    public static final RegistryObject<PlacedFeature> ORE_RUBY = registerCommonOre("ore_ruby_placed", BLConfiguredFeatures.ORE_RUBY, 7, -80, 80);
    public static final RegistryObject<PlacedFeature> ORE_MALACHITE = registerCommonOre("ore_malachite_placed", BLConfiguredFeatures.ORE_MALACHITE, 7, PlacementUtils.RANGE_10_10);
    public static final RegistryObject<PlacedFeature> ORE_ONICE = registerCommonOre("ore_onice_placed", BLConfiguredFeatures.ORE_ONICE, 7, PlacementUtils.RANGE_10_10);

    public static final RegistryObject<PlacedFeature> ORE_URANIUM = PLACED_FEATURES.register("ore_uranium_placed",
            () -> new PlacedFeature(BLConfiguredFeatures.ORE_URANIUM.getHolder().get(), List.of(InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)), BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> CATTAIL = PLACED_FEATURES.register("cattail_placed", () -> new PlacedFeature(BLConfiguredFeatures.CATTAIL.getHolder().get(), AquaticPlacements.seagrassPlacement(80)));

    public static final RegistryObject<PlacedFeature> FALLEN_BIRCH_TREE = PLACED_FEATURES.register("fallen_birch_tree_placed", () -> new PlacedFeature(
            BLConfiguredFeatures.FALLEN_BIRCH_TREE.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2))));
    public static final RegistryObject<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = PLACED_FEATURES.register("fallen_hollow_birch_tree_placed", () -> new PlacedFeature(
            BLConfiguredFeatures.FALLEN_HOLLOW_BIRCH_TREE.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2))));

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
    private static RegistryObject<PlacedFeature> registerCommonOre(String name, RegistryObject<ConfiguredFeature<?, ?>> oreConfiguration, int count, int minHeight, int maxHeight) {
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
    private static RegistryObject<PlacedFeature> registerCommonOre(String name, RegistryObject<ConfiguredFeature<?, ?>> oreConfiguration, int count, PlacementModifier placementModifier) {
        return PLACED_FEATURES.register(name, () -> new PlacedFeature(oreConfiguration.getHolder().get(), BLOrePlacement.commonOrePlacement(count, placementModifier)));
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link PlacedFeature Placed Features}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}