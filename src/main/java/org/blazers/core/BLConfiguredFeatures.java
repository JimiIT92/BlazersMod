package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import org.blazers.BlazersMod;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
 */
public final class BLConfiguredFeatures {

    //#region Configured Features

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOPAZ = registerKey("ore_topaz");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PEARL = registerKey("ore_pearl");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MALACHITE = registerKey("ore_malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ONICE = registerKey("ore_onice");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM = registerKey("ore_uranium");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL = registerKey("cattail");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = registerKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree");

    //#endregion

    /**
     * Register the {@link ConfiguredFeature Configured Features}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> Bootstrap Context}
     */
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        registerOverworldOre(context, ORE_SAPPHIRE,BLBlocks.SAPPHIRE_ORE, BLBlocks.DEEPSLATE_SAPPHIRE_ORE, 4);
        registerOverworldOre(context, ORE_TOPAZ, BLBlocks.TOPAZ_ORE, BLBlocks.DEEPSLATE_TOPAZ_ORE, 4);
        registerUnderwaterOre(context,ORE_PEARL, BLBlocks.PEARL_ORE, 4);
        registerNetherOre(context, ORE_RUBY, BLBlocks.RUBY_ORE, 4);
        registerNetherOre(context, ORE_MALACHITE, BLBlocks.MALACHITE_ORE, 7);
        registerNetherOre(context, ORE_ONICE, BLBlocks.ONICE_ORE, 7);
        registerNetherOre(context, ORE_URANIUM, BLBlocks.URANIUM_ORE, 4);
        context.register(CATTAIL,
                new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR,
                        new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BLBlocks.CATTAIL.get().defaultBlockState(), 1))))))));
        context.register(FALLEN_BIRCH_TREE, new ConfiguredFeature<>(BLFeatures.FALLEN_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F)));
        context.register(FALLEN_HOLLOW_BIRCH_TREE, new ConfiguredFeature<>(BLFeatures.FALLEN_HOLLOW_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F)));
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param stoneOre {@link Block Stone Ore}
     * @param deepslateOre  {@link Block Deepslate Ore}
     * @return Overworld {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createOverworldTargetState(Supplier<Block> stoneOre, Supplier<Block> deepslateOre) {
        return Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), stoneOre.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), deepslateOre.get().defaultBlockState())));
    }

    /**
     * Get the Underwater {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param ore {@link Block Ore Block}
     * @return Underwater {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createUnderwaterTargetState(Supplier<Block> ore) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND), ore.get().defaultBlockState())));
    }

    /**
     * Get the Nether {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param ore {@link Block Ore Block}
     * @return Nether {@link OreConfiguration.TargetBlockState Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createNetherTargetState(Supplier<Block> ore) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), ore.get().defaultBlockState())));
    }

    /**
     * Register an Overworld {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstapContext Bootstrap Context}
     * @param key {@link ResourceKey Resource Key}
     * @param stoneOre {@link Block Stone Ore Block}
     * @param deepslateOre {@link Block Deepslate Stone Ore Block}
     * @param size {@link Integer Max Vein Size}
     */
    private static void registerOverworldOre(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<Block> stoneOre, Supplier<Block> deepslateOre, int size) {
        context.register(key, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createOverworldTargetState(stoneOre, deepslateOre).get(), size)));
    }

    /**
     * Register an Underwater {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstapContext Bootstrap Context}
     * @param key {@link ResourceKey Resource Key}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     */
    private static void registerUnderwaterOre(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<Block> ore, int size) {
        context.register(key, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createUnderwaterTargetState(ore).get(), size)));
    }

    /**
     * Register a Nether {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstapContext Bootstrap Context}
     * @param key {@link ResourceKey Resource Key}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     */
    private static void registerNetherOre(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<Block> ore, int size) {
        context.register(key, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createNetherTargetState(ore).get(), size)));
    }

    /**
     * Register a {@link ResourceKey Resource Key}
     *
     * @param name {@link String Resource Key Name}
     * @return {@link ResourceKey Registered Resource Key}
     */
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BlazersMod.MOD_ID, name));
    }

}