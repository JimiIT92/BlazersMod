package org.blazers.core;

import net.minecraft.block.Block;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.blazers.BlazersMod;

import java.util.List;

public final class BLConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SAPPHIRE = registerOverworldOre("ore_sapphire", BLBlocks.SAPPHIRE_ORE, BLBlocks.DEEPSLATE_SAPPHIRE_ORE, 4);
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TOPAZ = registerOverworldOre("ore_topaz", BLBlocks.TOPAZ_ORE, BLBlocks.DEEPSLATE_TOPAZ_ORE, 4);

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PEARL = registerUnderwaterOre("ore_pearl", BLBlocks.PEARL_ORE, 4);

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_RUBY = registerNetherOre("ore_ruby", BLBlocks.RUBY_ORE, 4);
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_MALACHITE = registerNetherOre("ore_malachite", BLBlocks.MALACHITE_ORE, 7);
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_ONICE = registerNetherOre("ore_onice", BLBlocks.ONICE_ORE, 7);

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_URANIUM = registerNetherOre("ore_uranium", BLBlocks.URANIUM_ORE, 4);

    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> CATTAIL = ConfiguredFeatures.register("cattail", Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(BLBlocks.CATTAIL)));

    private static List<OreFeatureConfig.Target> createOverworldTargetState(Block stoneOre, Block deepslateOre) {
        return List.of(
                OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, stoneOre.getDefaultState()),
                OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslateOre.getDefaultState()));
    }

    private static List<OreFeatureConfig.Target> createUnderwaterTargetState(Block ore) {
        return List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.SAND), ore.getDefaultState()));
    }

    private static List<OreFeatureConfig.Target> createNetherTargetState(Block ore) {
        return List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, ore.getDefaultState()));
    }

    private static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> registerOverworldOre(String name, Block stoneOre, Block deepslateOre, int size) {
        return ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(createOverworldTargetState(stoneOre, deepslateOre), size));
    }

    private static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> registerUnderwaterOre(String name, Block ore, int size) {
        return ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(createUnderwaterTargetState(ore), size));
    }

    private static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> registerNetherOre(String name, Block ore, int size) {
        return ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(createNetherTargetState(ore), size));
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " configured features");
    }

}