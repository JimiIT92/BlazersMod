package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.damagesource.DamageType;
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
import org.blazers.helper.BlockHelper;
import org.blazers.helper.RegistryHelper;
import org.blazers.world.feature.FallenTreeFeature;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
 */
public final class BLConfiguredFeatures {

    //#region Configured Features

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.SAPPHIRE));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOPAZ = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.TOPAZ));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PEARL = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.PEARL));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.RUBY));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MALACHITE = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.MALACHITE));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ONICE = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.ONICE));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM = registerOreConfiguredFeatureKey(BlockHelper.materialName(BLMaterials.URANIUM));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL = registerConfiguredFeatureKey("cattail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = registerConfiguredFeatureKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_HOLLOW_BIRCH_TREE = registerConfiguredFeatureKey("fallen_hollow_birch_tree");

    //#endregion

    //#region Methods

    /**
     * Register an {@link ConfiguredFeature Ore} {@link ResourceKey Resource Key}
     *
     * @param oreName {@link String The Ore name}
     * @return {@link ResourceKey<DamageType> The Ore Resource Key}
     */
    private static ResourceKey<ConfiguredFeature<?, ?>> registerOreConfiguredFeatureKey(final String oreName) {
        return registerConfiguredFeatureKey("ore_" + oreName);
    }

    /**
     * Register an {@link ConfiguredFeature Configured Feature} {@link ResourceKey Resource Key}
     *
     * @param name {@link String The Configured Feature name}
     * @return {@link ResourceKey<DamageType> The Configured Feature Resource Key}
     */
    private static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeatureKey(final String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, RegistryHelper.location(name));
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param stoneOreSupplier {@link Block The Supplier for the Stone Ore Block}
     * @param deepslateOreSupplier  {@link Block The Supplier for the Deepslate Ore Block}
     * @return Overworld {@link OreConfiguration.TargetBlockState The Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createOverworldTargetState(final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier) {
        return Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), stoneOreSupplier.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), deepslateOreSupplier.get().defaultBlockState()))
        );
    }

    /**
     * Get the Underwater {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param oreSupplier {@link Block The Supplier for the Ore Block}
     * @return Underwater {@link OreConfiguration.TargetBlockState The Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createUnderwaterTargetState(final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Get the Nether {@link OreConfiguration.TargetBlockState Target Block State}
     *
     * @param oreSupplier {@link Block The Supplier for the Ore Block}
     * @return Nether {@link OreConfiguration.TargetBlockState The Target Block State}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createNetherTargetState(final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Register an Overworld {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link ResourceKey The Ore Resource Key}
     * @param stoneOreSupplier {@link Block The Supplier for the Stone Ore Block}
     * @param deepslateOreSupplier {@link Block The Supplier for the Deepslate Stone Ore Block}
     * @param size {@link Integer The Max Vein Size}
     */
    private static void registerOverworldOre(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> oreKey, final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier, final int size) {
        registerOre(context, oreKey, createOverworldTargetState(stoneOreSupplier, deepslateOreSupplier), size);
    }

    /**
     * Register an Underwater {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link ResourceKey The Ore Resource Key}
     * @param oreSupplier {@link Block The Supplier for the Stone Ore Block}
     * @param size {@link Integer The Max Vein Size}
     */
    private static void registerUnderwaterOre(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> oreKey, final Supplier<Block> oreSupplier, final int size) {
        registerOre(context, oreKey, createUnderwaterTargetState(oreSupplier), size);
    }

    /**
     * Register a Nether {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link ResourceKey The Ore Resource Key}
     * @param oreSupplier {@link Block The Supplier for the Stone Ore Block}
     * @param size {@link Integer The Max Vein Size}
     */
    private static void registerNetherOre(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> oreKey, final Supplier<Block> oreSupplier, final int size) {
        registerOre(context, oreKey, createNetherTargetState(oreSupplier), size);
    }

    /**
     * Register an {@link ConfiguredFeature Ore Configuration}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param oreKey {@link ResourceKey The Ore Resource Key}
     * @param targetBlockStateSupplier {@link Block The Supplier for the Ore Target Block State}
     * @param size {@link Integer The Max Vein Size}
     */
    private static void registerOre(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> oreKey, final Supplier<List<OreConfiguration.TargetBlockState>> targetBlockStateSupplier, final int size) {
        context.register(oreKey, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetBlockStateSupplier.get(), size)));
    }

    /**
     * Register a {@link FallenTreeFeature Fallen Tree Feature}
     *
     * @param context {@link BootstrapContext The Bootstrap Context}
     * @param fallenTreeKey {@link ResourceKey The Fallen Tree Resource Key}
     * @param fallenTreeFeatureSupplier {@link Supplier<Feature> The Supplier for the Fallen Tree Feature}
     */
    private static void registerFallenTree(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> fallenTreeKey, final Supplier<Feature<ProbabilityFeatureConfiguration>> fallenTreeFeatureSupplier) {
        context.register(fallenTreeKey, new ConfiguredFeature<>(fallenTreeFeatureSupplier.get(), new ProbabilityFeatureConfiguration(0.1F)));
    }

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
     *
     * @param context {@link BootstrapContext<ConfiguredFeature> The Bootstrap Context}
     */
    public static void bootstrap(final BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerOverworldOre(context, ORE_SAPPHIRE,BLBlocks.SAPPHIRE_ORE, BLBlocks.DEEPSLATE_SAPPHIRE_ORE, 4);
        registerOverworldOre(context, ORE_TOPAZ, BLBlocks.TOPAZ_ORE, BLBlocks.DEEPSLATE_TOPAZ_ORE, 4);
        registerUnderwaterOre(context,ORE_PEARL, BLBlocks.PEARL_ORE, 4);
        registerNetherOre(context, ORE_RUBY, BLBlocks.RUBY_ORE, 4);
        registerNetherOre(context, ORE_MALACHITE, BLBlocks.MALACHITE_ORE, 7);
        registerNetherOre(context, ORE_ONICE, BLBlocks.ONICE_ORE, 7);
        registerNetherOre(context, ORE_URANIUM, BLBlocks.URANIUM_ORE, 4);

        context.register(CATTAIL, new ConfiguredFeature<>(
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BLBlocks.CATTAIL.get().defaultBlockState(), 1)))
                )))
        ));

        registerFallenTree(context, FALLEN_BIRCH_TREE, Suppliers.memoize(() -> BLFeatures.FALLEN_BIRCH_TREE.get()));
        registerFallenTree(context, FALLEN_HOLLOW_BIRCH_TREE, Suppliers.memoize(() -> BLFeatures.FALLEN_HOLLOW_BIRCH_TREE.get()));
    }

    //#endregion

}