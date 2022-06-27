package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
 */
public final class BLConfiguredFeatures {

    //#region Configured Features

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SAPPHIRE = registerOverworldOre("ore_sapphire", BLBlocks.SAPPHIRE_ORE.get(), BLBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), 4);
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_TOPAZ = registerOverworldOre("ore_topaz", BLBlocks.TOPAZ_ORE.get(), BLBlocks.DEEPSLATE_TOPAZ_ORE.get(), 4);

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_PEARL = registerUnderwaterOre("ore_pearl", BLBlocks.PEARL_ORE.get(), 4);

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_RUBY = registerNetherOre("ore_ruby", BLBlocks.RUBY_ORE.get(), 4);
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MALACHITE = registerNetherOre("ore_malachite", BLBlocks.MALACHITE_ORE.get(), 7);
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_ONICE = registerNetherOre("ore_onice", BLBlocks.ONICE_ORE.get(), 7);

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_URANIUM = registerNetherOre("ore_uranium", BLBlocks.URANIUM_ORE.get(), 4);

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> CATTAIL = FeatureUtils.register("cattail", Feature.SIMPLE_RANDOM_SELECTOR,
            new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BLBlocks.CATTAIL.get().defaultBlockState(), 1)))))));

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> FALLEN_BIRCH_TREE = FeatureUtils.register("fallen_birch_tree", BLFeatures.FALLEN_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> FALLEN_HOLLOW_BIRCH_TREE = FeatureUtils.register("fallen_hollow_birch_tree", BLFeatures.FALLEN_HOLLOW_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F));

    //#endregion

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param stoneOre {@link Block Stone Ore}
     * @param deepslateOre  {@link Block Deepslate Ore}
     * @return Overworld {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static List<OreConfiguration.TargetBlockState> createOverworldTargetState(Block stoneOre, Block deepslateOre) {
        return List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, stoneOre.defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslateOre.defaultBlockState()));
    }

    /**
     * Get the Underwater {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param ore {@link Block Ore Block}
     * @return Underwater {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static List<OreConfiguration.TargetBlockState> createUnderwaterTargetState(Block ore) {
        return List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND), ore.defaultBlockState()));
    }

    /**
     * Get the Nether {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param ore {@link Block Ore Block}
     * @return Nether {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static List<OreConfiguration.TargetBlockState> createNetherTargetState(Block ore) {
        return List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ore.defaultBlockState()));
    }

    /**
     * Register an Overworld {@link ConfiguredFeature Ore Configuration}
     *
     * @param name {@link String Ore Feature Name}
     * @param stoneOre {@link Block Stone Ore}
     * @param deepslateOre {@link Block Deepslate Ore}
     * @param size {@link Integer Max Vein Size}
     * @return Overworld {@link ConfiguredFeature Ore Configuration}
     */
    private static Holder<ConfiguredFeature<OreConfiguration, ?>> registerOverworldOre(String name, Block stoneOre, Block deepslateOre, int size) {
        return FeatureUtils.register(name, Feature.ORE, new OreConfiguration(createOverworldTargetState(stoneOre, deepslateOre), size));
    }

    /**
     * Register an Underwater {@link ConfiguredFeature Ore Configuration}
     *
     * @param name {@link String Ore Feature Name}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     * @return Underwater {@link ConfiguredFeature Ore Configuration}
     */
    private static Holder<ConfiguredFeature<OreConfiguration, ?>> registerUnderwaterOre(String name, Block ore, int size) {
        return FeatureUtils.register(name, Feature.ORE, new OreConfiguration(createUnderwaterTargetState(ore), size));
    }

    /**
     * Register a Nether {@link ConfiguredFeature Ore Configuration}
     *
     * @param name {@link String Ore Feature Name}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     * @return Nether {@link ConfiguredFeature Ore Configuration}
     */
    private static Holder<ConfiguredFeature<OreConfiguration, ?>> registerNetherOre(String name, Block ore, int size) {
        return FeatureUtils.register(name, Feature.ORE, new OreConfiguration(createNetherTargetState(ore), size));
    }

}