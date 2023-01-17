package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
 */
public final class BLConfiguredFeatures {

    /**
     * {@link ConfiguredFeature Configured Features} {@link DeferredRegister <ConfiguredFeature> Registry}
     */
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, BlazersMod.MOD_ID);

    //#region Configured Features

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerOverworldOre("ore_sapphire", BLBlocks.SAPPHIRE_ORE, BLBlocks.DEEPSLATE_SAPPHIRE_ORE, 4);
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TOPAZ = registerOverworldOre("ore_topaz", BLBlocks.TOPAZ_ORE, BLBlocks.DEEPSLATE_TOPAZ_ORE, 4);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_PEARL = registerUnderwaterOre("ore_pearl", BLBlocks.PEARL_ORE, 4);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_RUBY = registerNetherOre("ore_ruby", BLBlocks.RUBY_ORE, 4);
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_MALACHITE = registerNetherOre("ore_malachite", BLBlocks.MALACHITE_ORE, 7);
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ONICE = registerNetherOre("ore_onice", BLBlocks.ONICE_ORE, 7);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_URANIUM = registerNetherOre("ore_uranium", BLBlocks.URANIUM_ORE, 4);

    public static final RegistryObject<ConfiguredFeature<?, ?>> CATTAIL = CONFIGURED_FEATURES.register("cattail",
            () -> new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR,
                    new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BLBlocks.CATTAIL.get().defaultBlockState(), 1))))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = CONFIGURED_FEATURES.register("fallen_birch_tree", () -> new ConfiguredFeature<>(
            BLFeatures.FALLEN_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_HOLLOW_BIRCH_TREE = CONFIGURED_FEATURES.register("fallen_hollow_birch_tree", () -> new ConfiguredFeature<>(
            BLFeatures.FALLEN_HOLLOW_BIRCH_TREE.get(), new ProbabilityFeatureConfiguration(0.1F)));

    //#endregion

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
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ore.get().defaultBlockState())));
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
    private static RegistryObject<ConfiguredFeature<?, ?>> registerOverworldOre(String name, Supplier<Block> stoneOre, Supplier<Block> deepslateOre, int size) {
        return CONFIGURED_FEATURES.register(name, () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createOverworldTargetState(stoneOre, deepslateOre).get(), size)));
    }

    /**
     * Register an Underwater {@link ConfiguredFeature Ore Configuration}
     *
     * @param name {@link String Ore Feature Name}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     * @return Underwater {@link ConfiguredFeature Ore Configuration}
     */
    private static RegistryObject<ConfiguredFeature<?, ?>> registerUnderwaterOre(String name, Supplier<Block> ore, int size) {
        return CONFIGURED_FEATURES.register(name, () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createUnderwaterTargetState(ore).get(), size)));
    }

    /**
     * Register a Nether {@link ConfiguredFeature Ore Configuration}
     *
     * @param name {@link String Ore Feature Name}
     * @param ore {@link Block Ore Block}
     * @param size {@link Integer Max Vein Size}
     * @return Nether {@link ConfiguredFeature Ore Configuration}
     */
    private static RegistryObject<ConfiguredFeature<?, ?>> registerNetherOre(String name, Supplier<Block> ore, int size) {
        return CONFIGURED_FEATURES.register(name, () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(createNetherTargetState(ore).get(), size)));
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link ConfiguredFeature Configured Features}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}