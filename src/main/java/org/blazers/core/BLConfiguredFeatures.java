package org.blazers.core;

import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.blazers.BlazersMod;

import java.util.List;

public final class BLConfiguredFeatures {

    private static final RuleTest STONE_ORE_REPLEACEABLES = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest DEEPSLATE_ORE_REPLEACEABLES = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private static final RuleTest UNDERWATER_ORE_REPLEACEABLES = new TagMatchRuleTest(BlockTags.SAND);
    private static final RuleTest NETHER_ORE_REPLEACEABLES = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_TOPAZ = registerKey("ore_topaz");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PEARL = registerKey("ore_pearl");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerKey("ore_ruby");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MALACHITE = registerKey("ore_malachite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_ONICE = registerKey("ore_onice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_URANIUM = registerKey("ore_uranium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CATTAIL = registerKey("cattail");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = registerKey("fallen_birch_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        List<OreFeatureConfig.Target> oreSapphire = createOverworldOre(BLBlocks.SAPPHIRE_ORE, BLBlocks.DEEPSLATE_SAPPHIRE_ORE);
        List<OreFeatureConfig.Target> oreTopaz = createOverworldOre(BLBlocks.TOPAZ_ORE, BLBlocks.DEEPSLATE_TOPAZ_ORE);

        List<OreFeatureConfig.Target> orePearl = createUnderwaterOre(BLBlocks.PEARL_ORE);

        List<OreFeatureConfig.Target> oreRuby = createNetherOre(BLBlocks.RUBY_ORE);
        List<OreFeatureConfig.Target> oreMalachite = createNetherOre(BLBlocks.MALACHITE_ORE);
        List<OreFeatureConfig.Target> oreOnice = createNetherOre(BLBlocks.ONICE_ORE);
        List<OreFeatureConfig.Target> oreUranium = createNetherOre(BLBlocks.URANIUM_ORE);

        register(context, ORE_SAPPHIRE, Feature.ORE, new OreFeatureConfig(oreSapphire, 4));
        register(context, ORE_TOPAZ, Feature.ORE, new OreFeatureConfig(oreTopaz, 4));
        register(context, ORE_PEARL, Feature.ORE, new OreFeatureConfig(orePearl, 4));
        register(context, ORE_RUBY, Feature.ORE, new OreFeatureConfig(oreRuby, 4));
        register(context, ORE_MALACHITE, Feature.ORE, new OreFeatureConfig(oreMalachite, 7));
        register(context, ORE_ONICE, Feature.ORE, new OreFeatureConfig(oreOnice, 7));
        register(context, ORE_URANIUM, Feature.ORE, new OreFeatureConfig(oreUranium, 4));

        register(context, CATTAIL, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(BLBlocks.CATTAIL)));
        register(context, FALLEN_BIRCH_TREE, BLFeatures.FALLEN_BIRCH_TREE, new ProbabilityConfig(0.1F));
        register(context, FALLEN_HOLLOW_BIRCH_TREE, BLFeatures.FALLEN_HOLLOW_BIRCH_TREE, new ProbabilityConfig(0.1F));
    }

    public static List<OreFeatureConfig.Target> createOverworldOre(Block stoneOre, Block deepslateOre) {
        return List.of(OreFeatureConfig.createTarget(STONE_ORE_REPLEACEABLES, stoneOre.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLEACEABLES, deepslateOre.getDefaultState()));
    }

    public static List<OreFeatureConfig.Target> createUnderwaterOre(Block ore) {
        return List.of(OreFeatureConfig.createTarget(UNDERWATER_ORE_REPLEACEABLES, ore.getDefaultState()));
    }

    public static List<OreFeatureConfig.Target> createNetherOre(Block ore) {
        return List.of(OreFeatureConfig.createTarget(NETHER_ORE_REPLEACEABLES, ore.getDefaultState()));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(final String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(BlazersMod.MOD_ID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(final Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}