package org.blazers.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.blazers.BlazersMod;
import org.blazers.block.*;

public final class BLBlocks {

    public static final Block HOLLOW_OAK_LOG = registerBlock("hollow_oak_log", new HollowBlock(MapColor.OAK_TAN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_OAK_LOG = registerBlock("hollow_stripped_oak_log", new HollowBlock(MapColor.OAK_TAN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_SPRUCE_LOG = registerBlock("hollow_spruce_log", new HollowBlock(MapColor.SPRUCE_BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_SPRUCE_LOG = registerBlock("hollow_stripped_spruce_log", new HollowBlock(MapColor.SPRUCE_BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log", new HollowBlock(MapColor.PALE_YELLOW), BLTabs.BUILDING_BLOCKS, BLTabs.NATURAL);
    public static final Block HOLLOW_STRIPPED_BIRCH_LOG = registerBlock("hollow_stripped_birch_log", new HollowBlock(MapColor.PALE_YELLOW), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_JUNGLE_LOG = registerBlock("hollow_jungle_log", new HollowBlock(MapColor.DIRT_BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_JUNGLE_LOG = registerBlock("hollow_stripped_jungle_log", new HollowBlock(MapColor.DIRT_BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_ACACIA_LOG = registerBlock("hollow_acacia_log", new HollowBlock(MapColor.ORANGE), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_ACACIA_LOG = registerBlock("hollow_stripped_acacia_log", new HollowBlock(MapColor.ORANGE), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_DARK_OAK_LOG = registerBlock("hollow_dark_oak_log", new HollowBlock(MapColor.BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_DARK_OAK_LOG = registerBlock("hollow_stripped_dark_oak_log", new HollowBlock(MapColor.BROWN), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_MANGROVE_LOG = registerBlock("hollow_mangrove_log", new HollowBlock(MapColor.RED), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_MANGROVE_LOG = registerBlock("hollow_stripped_mangrove_log", new HollowBlock(MapColor.RED), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_CRIMSON_STEM = registerBlock("hollow_crimson_stem", new HollowBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_STEM), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_CRIMSON_STEM = registerBlock("hollow_stripped_crimson_stem", new HollowBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_STEM), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_WARPED_STEM = registerBlock("hollow_warped_stem", new HollowBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_STEM), BLTabs.BUILDING_BLOCKS);
    public static final Block HOLLOW_STRIPPED_WARPED_STEM = registerBlock("hollow_stripped_warped_stem", new HollowBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_STEM), BLTabs.BUILDING_BLOCKS);

    public static final Block COBBLED_GRANITE = registerSimpleBlock("cobbled_granite", FabricBlockSettings.copyOf(Blocks.GRANITE));
    public static final Block COBBLED_DIORITE = registerSimpleBlock("cobbled_diorite", FabricBlockSettings.copyOf(Blocks.DIORITE));
    public static final Block COBBLED_ANDESITE = registerSimpleBlock("cobbled_andesite", FabricBlockSettings.copyOf(Blocks.ANDESITE));
    public static final Block SANDSTONE_BRICKS = registerSimpleBlock("sandstone_bricks", FabricBlockSettings.copyOf(Blocks.SANDSTONE));
    public static final Block CUT_BRICKS = registerSimpleBlock("cut_bricks", FabricBlockSettings.copyOf(Blocks.BRICKS));
    public static final Block PURPUR_TILES = registerSimpleBlock("purpur_tiles", FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block STONE_TILES = registerSimpleBlock("stone_tiles", FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
    public static final Block MOSSY_STONE_TILES = registerSimpleBlock("mossy_stone_tiles", FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS));
    public static final Block CUT_DEEPSLATE_BRICKS = registerSimpleBlock("cut_deepslate_bricks", FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS));
    public static final Block POLISHED_DEEPSLATE_BRICKS = registerSimpleBlock("polished_deepslate_bricks", FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE));
    public static final Block END_STONE_TILES = registerSimpleBlock("end_stone_tiles", FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS));
    public static final Block QUARTZ_TILES = registerSimpleBlock("quartz_tiles", FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS));
    public static final Block CUT_PRISMARINE_BRICKS = registerSimpleBlock("cut_prismarine_bricks", FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS));
    public static final Block DARK_PRISMARINE_BRICKS = registerSimpleBlock("dark_prismarine_bricks", FabricBlockSettings.copyOf(Blocks.DARK_PRISMARINE));
    public static final Block RED_SANDSTONE_BRICKS = registerSimpleBlock("red_sandstone_bricks", FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE));
    public static final Block POLISHED_BLACKSTONE_TILES = registerSimpleBlock("polished_blackstone_tiles", FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS));

    public static final Block SAPPHIRE_BLOCK = registerSimpleBlock("sapphire_block", createGemBlockProperties(MapColor.BLACK));
    public static final Block TOPAZ_BLOCK = registerSimpleBlock("topaz_block", createGemBlockProperties(MapColor.ORANGE));
    public static final Block PEARL_BLOCK = registerSimpleBlock("pearl_block", FabricBlockSettings.create().mapColor(MapColor.PINK)
            .sounds(BlockSoundGroup.CALCITE).requiresTool().strength(0.75F));
    public static final Block RUBY_BLOCK = registerSimpleBlock("ruby_block", createGemBlockProperties(MapColor.RED));
    public static final Block MALACHITE_BLOCK = registerSimpleBlock("malachite_block", createGemBlockProperties(MapColor.TERRACOTTA_GREEN));
    public static final Block ONICE_BLOCK = registerSimpleBlock("onice_block", createGemBlockProperties(MapColor.TERRACOTTA_BLACK));
    public static final Block URANIUM_BLOCK = registerSimpleBlock("uranium_block", FabricBlockSettings.create().mapColor(MapColor.GREEN).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.COPPER));
    public static final Block RAW_URANIUM_BLOCK = registerSimpleBlock("raw_uranium_block", FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(5.0F, 6.0F));

    public static final Block CUT_COPPER_BRICKS = registerBlock("cut_copper_bricks", new OxidizableCutCopperBricksBlock(Oxidizable.OxidationLevel.UNAFFECTED, Blocks.CUT_COPPER), BLTabs.BUILDING_BLOCKS);
    public static final Block EXPOSED_CUT_COPPER_BRICKS = registerBlock("exposed_cut_copper_bricks", new OxidizableCutCopperBricksBlock(Oxidizable.OxidationLevel.EXPOSED, Blocks.EXPOSED_CUT_COPPER), BLTabs.BUILDING_BLOCKS);
    public static final Block WEATHERED_CUT_COPPER_BRICKS = registerBlock("weathered_cut_copper_bricks", new OxidizableCutCopperBricksBlock(Oxidizable.OxidationLevel.WEATHERED, Blocks.WEATHERED_CUT_COPPER), BLTabs.BUILDING_BLOCKS);
    public static final Block OXIDIZED_CUT_COPPER_BRICKS = registerBlock("oxidized_cut_copper_bricks", new OxidizableCutCopperBricksBlock(Oxidizable.OxidationLevel.OXIDIZED, Blocks.OXIDIZED_CUT_COPPER), BLTabs.BUILDING_BLOCKS);
    public static final Block WAXED_CUT_COPPER_BRICKS = registerBlock("waxed_cut_copper_bricks", new Block(FabricBlockSettings.copyOf(Blocks.WAXED_CUT_COPPER)), BLTabs.BUILDING_BLOCKS);
    public static final Block WAXED_EXPOSED_CUT_COPPER_BRICKS = registerBlock("waxed_exposed_cut_copper_bricks", new Block(FabricBlockSettings.copyOf(Blocks.WAXED_EXPOSED_CUT_COPPER)), BLTabs.BUILDING_BLOCKS);
    public static final Block WAXED_WEATHERED_CUT_COPPER_BRICKS = registerBlock("waxed_weathered_cut_copper_bricks", new Block(FabricBlockSettings.copyOf(Blocks.WAXED_WEATHERED_CUT_COPPER)), BLTabs.BUILDING_BLOCKS);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_BRICKS = registerBlock("waxed_oxidized_cut_copper_bricks", new Block(FabricBlockSettings.copyOf(Blocks.WAXED_OXIDIZED_CUT_COPPER)), BLTabs.BUILDING_BLOCKS);

    public static final Block COPPER_BUTTON = registerBlock("copper_button", new OxidizableCopperButtonBlock(Oxidizable.OxidationLevel.UNAFFECTED), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block EXPOSED_COPPER_BUTTON = registerBlock("exposed_copper_button", new OxidizableCopperButtonBlock(Oxidizable.OxidationLevel.EXPOSED), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block WEATHERED_COPPER_BUTTON = registerBlock("weathered_copper_button", new OxidizableCopperButtonBlock(Oxidizable.OxidationLevel.WEATHERED), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block OXIDIZED_COPPER_BUTTON = registerBlock("oxidized_copper_button", new OxidizableCopperButtonBlock(Oxidizable.OxidationLevel.OXIDIZED), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block WAXED_COPPER_BUTTON = registerBlock("waxed_copper_button", new CopperButtonBlock(), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block WAXED_EXPOSED_COPPER_BUTTON = registerBlock("waxed_exposed_copper_button", new CopperButtonBlock(), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block WAXED_WEATHERED_COPPER_BUTTON = registerBlock("waxed_weathered_copper_button", new CopperButtonBlock(), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);
    public static final Block WAXED_OXIDIZED_COPPER_BUTTON = registerBlock("waxed_oxidized_copper_button", new CopperButtonBlock(), BLTabs.BUILDING_BLOCKS, BLTabs.REDSTONE);

    public static final Block POINTED_STONE_DRIPSTONE = registerDripstone("pointed_stone_dripstone", Blocks.STONE);
    public static final Block POINTED_GRANITE_DRIPSTONE = registerDripstone("pointed_granite_dripstone", Blocks.GRANITE);
    public static final Block POINTED_DIORITE_DRIPSTONE = registerDripstone("pointed_diorite_dripstone", Blocks.DIORITE);
    public static final Block POINTED_ANDESITE_DRIPSTONE = registerDripstone("pointed_andesite_dripstone", Blocks.ANDESITE);
    public static final Block POINTED_ICE_DRIPSTONE = registerBlock("pointed_ice_dripstone", new PointedIceDripstoneBlock(), BLTabs.NATURAL);

    public static final Block SAPPHIRE_ORE = registerOreBlock("sapphire_ore", false);
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerOreBlock("deepslate_sapphire_ore", true);
    public static final Block TOPAZ_ORE = registerOreBlock("topaz_ore", false);
    public static final Block DEEPSLATE_TOPAZ_ORE = registerOreBlock("deepslate_topaz_ore", true);
    public static final Block PEARL_ORE = registerBlock("pearl_ore", new ColoredFallingBlock(new ColorCode(14406560), FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW)
            .strength(0.5F)
            .sounds(BlockSoundGroup.SAND)
            .requiresTool()
            ), BLTabs.NATURAL);
    public static final Block RUBY_ORE = registerNetherOreBlock("ruby_ore");
    public static final Block MALACHITE_ORE = registerNetherOreBlock("malachite_ore");
    public static final Block ONICE_ORE = registerNetherOreBlock("onice_ore");
    public static final Block URANIUM_ORE = registerBlock("uranium_ore", new ExperienceDroppingBlock(
            UniformIntProvider.create(2, 5),
            FabricBlockSettings.create().mapColor(MapColor.GREEN).requiresTool().strength(30.0F, 1200.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS)
    ), BLTabs.NATURAL);

    public static final Block ATOMIC_TNT = registerBlock("atomic_tnt", new AtomicTntBlock(), BLTabs.REDSTONE, BLTabs.COMBAT);

    public static final Block BROWN_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("brown_mushroom_wall_fan",
            new DeadCoralWallFanBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).sounds(BlockSoundGroup.GRASS).requiresTool().noCollision().breakInstantly().dropsLike(Blocks.BROWN_MUSHROOM)));
    public static final Block RED_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("red_mushroom_wall_fan",
            new DeadCoralWallFanBlock(FabricBlockSettings.create().mapColor(MapColor.RED).sounds(BlockSoundGroup.GRASS).requiresTool().noCollision().breakInstantly().dropsLike(Blocks.RED_MUSHROOM)));

    public static final Block CATTAIL = registerBlock("cattail", new CattailBlock(), BLTabs.NATURAL);
    public static final Block POTTED_CATTAIL = registerBlockWithoutBlockItem("potted_cattail", new FlowerPotBlock(CATTAIL, FabricBlockSettings.create().breakInstantly().nonOpaque()));

    public static final Block WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.RED_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs", new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs", new StairsBlock(Blocks.ORANGE_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs", new StairsBlock(Blocks.MAGENTA_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs", new StairsBlock(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs", new StairsBlock(Blocks.YELLOW_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs", new StairsBlock(Blocks.LIME_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs", new StairsBlock(Blocks.PINK_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs", new StairsBlock(Blocks.GRAY_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs", new StairsBlock(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs", new StairsBlock(Blocks.CYAN_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs", new StairsBlock(Blocks.PURPLE_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs", new StairsBlock(Blocks.BLUE_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs", new StairsBlock(Blocks.BROWN_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs", new StairsBlock(Blocks.GREEN_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs", new StairsBlock(Blocks.RED_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.RED_CONCRETE)), BLTabs.COLORED_BLOCKS);
    public static final Block BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs", new StairsBlock(Blocks.BLACK_CONCRETE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE)), BLTabs.COLORED_BLOCKS);

    private static FabricBlockSettings createGemBlockProperties(MapColor mapColor) {
        return FabricBlockSettings.create().mapColor(mapColor)
                .requiresTool()
                .strength(5.0F, 6.0F)
                .sounds(BlockSoundGroup.METAL);
    }

    private static FabricBlockSettings createOreBlockSettings() {
        return FabricBlockSettings.create().requiresTool().strength(3.0F, 3.0F);
    }

    private static FabricBlockSettings createDeepslateOreBlockSettings() {
        return createOreBlockSettings().mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE);
    }

    private static Block registerOreBlock(final String name, final boolean isDeepslateOre) {
        return registerBlock(name, new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), isDeepslateOre ? createDeepslateOreBlockSettings() : createOreBlockSettings()), BLTabs.NATURAL);
    }

    private static Block registerNetherOreBlock(final String name) {
        return registerBlock(name, new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), createOreBlockSettings().mapColor(MapColor.DARK_RED).sounds(BlockSoundGroup.NETHER_ORE)), BLTabs.NATURAL);
    }

    private static Block registerDripstone(final String name, final Block materialBlock) {
        return registerBlock(name, new BLPointedDripstoneBlock(materialBlock), BLTabs.NATURAL);
    }
    
    private static Block registerSimpleBlock(String name, FabricBlockSettings properties) {
        return registerBlock(name, new Block(properties), BLTabs.BUILDING_BLOCKS);
    }

    private static Block registerBlockWithoutBlockItem(final String name, final Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(BlazersMod.MOD_ID, name), block);
    }

    private static Block registerBlock(final String name, final Block block, final ItemGroup... tabs) {
        BLItems.registerItem(name, new BlockItem(block, new FabricItemSettings()), tabs);
        return registerBlockWithoutBlockItem(name, block);
    }

    public static void registerWaxedBlocks() {
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(COPPER_BUTTON, WAXED_COPPER_BUTTON);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(EXPOSED_COPPER_BUTTON, WAXED_EXPOSED_COPPER_BUTTON);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(WEATHERED_COPPER_BUTTON, WAXED_WEATHERED_COPPER_BUTTON);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(OXIDIZED_COPPER_BUTTON, WAXED_OXIDIZED_COPPER_BUTTON);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(CUT_COPPER_BRICKS, WAXED_CUT_COPPER_BRICKS);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(EXPOSED_CUT_COPPER_BRICKS, WAXED_EXPOSED_CUT_COPPER_BRICKS);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(WEATHERED_CUT_COPPER_BRICKS, WAXED_WEATHERED_CUT_COPPER_BRICKS);
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(OXIDIZED_CUT_COPPER_BRICKS, WAXED_OXIDIZED_CUT_COPPER_BRICKS);
    }

    public static void registerOxidizableBlocks() {
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(COPPER_BUTTON, EXPOSED_COPPER_BUTTON);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(EXPOSED_COPPER_BUTTON, WEATHERED_COPPER_BUTTON);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(WEATHERED_COPPER_BUTTON, OXIDIZED_COPPER_BUTTON);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(CUT_COPPER_BRICKS, EXPOSED_CUT_COPPER_BRICKS);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(EXPOSED_CUT_COPPER_BRICKS, WEATHERED_CUT_COPPER_BRICKS);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(WEATHERED_CUT_COPPER_BRICKS, OXIDIZED_CUT_COPPER_BRICKS);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(HOLLOW_OAK_LOG, HOLLOW_STRIPPED_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_SPRUCE_LOG, HOLLOW_STRIPPED_SPRUCE_LOG);
        StrippableBlockRegistry.register(HOLLOW_BIRCH_LOG, HOLLOW_STRIPPED_BIRCH_LOG);
        StrippableBlockRegistry.register(HOLLOW_JUNGLE_LOG, HOLLOW_STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(HOLLOW_ACACIA_LOG, HOLLOW_STRIPPED_ACACIA_LOG);
        StrippableBlockRegistry.register(HOLLOW_DARK_OAK_LOG, HOLLOW_STRIPPED_DARK_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_MANGROVE_LOG, HOLLOW_STRIPPED_MANGROVE_LOG);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(HOLLOW_OAK_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_OAK_LOG, 5, 5);
        registry.add(HOLLOW_SPRUCE_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_SPRUCE_LOG, 5, 5);
        registry.add(HOLLOW_BIRCH_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_BIRCH_LOG, 5, 5);
        registry.add(HOLLOW_JUNGLE_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_JUNGLE_LOG, 5, 5);
        registry.add(HOLLOW_ACACIA_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_ACACIA_LOG, 5, 5);
        registry.add(HOLLOW_DARK_OAK_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_DARK_OAK_LOG, 5, 5);
        registry.add(HOLLOW_MANGROVE_LOG, 5, 5);
        registry.add(HOLLOW_STRIPPED_MANGROVE_LOG, 5, 5);
    }

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ATOMIC_TNT, AtomicTntBlock.DISPENSER_BEHAVIOR);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " blocks");
    }

}