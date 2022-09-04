package org.blazers.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;
import org.blazers.block.CattailBlock;
import org.blazers.block.HollowBlock;

public final class BLBlocks {

    public static final Block SAPPHIRE_ORE = registerOreBlock("sapphire_ore", false);
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerOreBlock("deepslate_sapphire_ore", true);
    public static final Block TOPAZ_ORE = registerOreBlock("topaz_ore", false);
    public static final Block DEEPSLATE_TOPAZ_ORE = registerOreBlock("deepslate_topaz_ore", true);
    public static final Block PEARL_ORE = registerBlock("pearl_ore", new SandBlock(14406560, FabricBlockSettings.of(Material.AGGREGATE, MapColor.PALE_YELLOW)
            .strength(0.5F)
            .sounds(BlockSoundGroup.SAND)
            .requiresTool()
            ), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.OVERWORLD_ORES);
    public static final Block RUBY_ORE = registerNetherOreBlock("ruby_ore");
    public static final Block MALACHITE_ORE = registerNetherOreBlock("malachite_ore");
    public static final Block ONICE_ORE = registerNetherOreBlock("onice_ore");
    public static final Block URANIUM_ORE = registerBlock("uranium_ore", new OreBlock(
            FabricBlockSettings.of(Material.METAL, MapColor.GREEN).requiresTool().strength(30.0F, 1200.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS)
    ), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.NETHER_ORES);

    public static final Block SAPPHIRE_BLOCK = registerSimpleBlock("sapphire_block", createGemBlockProperties(MapColor.BLACK), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block TOPAZ_BLOCK = registerSimpleBlock("topaz_block", createGemBlockProperties(MapColor.ORANGE), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block PEARL_BLOCK = registerSimpleBlock("pearl_block", FabricBlockSettings.of(Material.STONE, MapColor.PINK)
            .sounds(BlockSoundGroup.CALCITE).requiresTool().strength(0.75F), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block RUBY_BLOCK = registerSimpleBlock("ruby_block", createGemBlockProperties(MapColor.RED), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block MALACHITE_BLOCK = registerSimpleBlock("malachite_block", createGemBlockProperties(MapColor.TERRACOTTA_GREEN), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block ONICE_BLOCK = registerSimpleBlock("onice_block", createGemBlockProperties(MapColor.TERRACOTTA_BLACK), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block URANIUM_BLOCK = registerSimpleBlock("uranium_block", FabricBlockSettings.of(Material.METAL, MapColor.GREEN).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.COPPER), BLTabs.BLTabSortGroups.STORAGE_BLOCKS);
    public static final Block RAW_URANIUM_BLOCK = registerSimpleBlock("raw_uranium_block", FabricBlockSettings.of(Material.STONE, MapColor.ORANGE).requiresTool().strength(5.0F, 6.0F), BLTabs.BLTabSortGroups.RAW_STORAGE_BLOCKS);

    public static final Block HOLLOW_OAK_LOG = registerBlock("hollow_oak_log", new HollowBlock(MapColor.OAK_TAN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_OAK_LOG = registerBlock("hollow_stripped_oak_log", new HollowBlock(MapColor.OAK_TAN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_SPRUCE_LOG = registerBlock("hollow_spruce_log", new HollowBlock(MapColor.SPRUCE_BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_SPRUCE_LOG = registerBlock("hollow_stripped_spruce_log", new HollowBlock(MapColor.SPRUCE_BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log", new HollowBlock(MapColor.PALE_YELLOW), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_BIRCH_LOG = registerBlock("hollow_stripped_birch_log", new HollowBlock(MapColor.PALE_YELLOW), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_JUNGLE_LOG = registerBlock("hollow_jungle_log", new HollowBlock(MapColor.DIRT_BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_JUNGLE_LOG = registerBlock("hollow_stripped_jungle_log", new HollowBlock(MapColor.DIRT_BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_ACACIA_LOG = registerBlock("hollow_acacia_log", new HollowBlock(MapColor.ORANGE), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_ACACIA_LOG = registerBlock("hollow_stripped_acacia_log", new HollowBlock(MapColor.ORANGE), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_DARK_OAK_LOG = registerBlock("hollow_dark_oak_log", new HollowBlock(MapColor.BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_DARK_OAK_LOG = registerBlock("hollow_stripped_dark_oak_log", new HollowBlock(MapColor.BROWN), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_MANGROVE_LOG = registerBlock("hollow_mangrove_log", new HollowBlock(MapColor.RED), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_MANGROVE_LOG = registerBlock("hollow_stripped_mangrove_log", new HollowBlock(MapColor.RED), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_CRIMSON_STEM = registerBlock("hollow_crimson_stem", new HollowBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_STEM), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_CRIMSON_STEM = registerBlock("hollow_stripped_crimson_stem", new HollowBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_STEM), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);
    public static final Block HOLLOW_WARPED_STEM = registerBlock("hollow_warped_stem", new HollowBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_STEM), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_LOGS);
    public static final Block HOLLOW_STRIPPED_WARPED_STEM = registerBlock("hollow_stripped_warped_stem", new HollowBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_STEM), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.HOLLOW_STRIPPED_LOGS);

    //copper buttons

    //tnt

    public static final Block BROWN_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("brown_mushroom_wall_fan",
            new DeadCoralWallFanBlock(FabricBlockSettings.of(Material.PLANT, MapColor.BROWN).sounds(BlockSoundGroup.GRASS).requiresTool().noCollision().breakInstantly().dropsLike(Blocks.BROWN_MUSHROOM)));
    public static final Block RED_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("red_mushroom_wall_fan",
            new DeadCoralWallFanBlock(FabricBlockSettings.of(Material.PLANT, MapColor.RED).sounds(BlockSoundGroup.GRASS).requiresTool().noCollision().breakInstantly().dropsLike(Blocks.RED_MUSHROOM)));

    public static final Block CATTAIL = registerBlock("cattail", new CattailBlock(), BLTabs.TAB_DECORATIONS, BLTabs.BLTabSortGroups.PLANTS);
    public static final Block POTTED_CATTAIL = registerBlockWithoutBlockItem("potted_cattail", new FlowerPotBlock(CATTAIL, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));

    public static final Block COBBLED_GRANITE = registerSimpleBlock("cobbled_granite", FabricBlockSettings.copyOf(Blocks.GRANITE), BLTabs.BLTabSortGroups.COBBLESTONES);
    public static final Block COBBLED_DIORITE = registerSimpleBlock("cobbled_diorite", FabricBlockSettings.copyOf(Blocks.DIORITE), BLTabs.BLTabSortGroups.COBBLESTONES);
    public static final Block COBBLED_ANDESITE = registerSimpleBlock("cobbled_andesite", FabricBlockSettings.copyOf(Blocks.ANDESITE), BLTabs.BLTabSortGroups.COBBLESTONES);
    public static final Block SANDSTONE_BRICKS = registerSimpleBlock("sandstone_bricks", FabricBlockSettings.copyOf(Blocks.SANDSTONE), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block CUT_BRICKS = registerSimpleBlock("cut_bricks", FabricBlockSettings.copyOf(Blocks.BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block PURPUR_TILES = registerSimpleBlock("purpur_tiles", FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block STONE_TILES = registerSimpleBlock("stone_tiles", FabricBlockSettings.copyOf(Blocks.STONE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block MOSSY_STONE_TILES = registerSimpleBlock("mossy_stone_tiles", FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block CUT_DEEPSLATE_BRICKS = registerSimpleBlock("cut_deepslate_bricks", FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block POLISHED_DEEPSLATE_BRICKS = registerSimpleBlock("polished_deepslate_bricks", FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block END_STONE_TILES = registerSimpleBlock("end_stone_tiles", FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block QUARTZ_TILES = registerSimpleBlock("quartz_tiles", FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block CUT_PRISMARINE_BRICKS = registerSimpleBlock("cut_prismarine_bricks", FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block DARK_PRISMARINE_BRICKS = registerSimpleBlock("dark_prismarine_bricks", FabricBlockSettings.copyOf(Blocks.DARK_PRISMARINE), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block RED_SANDSTONE_BRICKS = registerSimpleBlock("red_sandstone_bricks", FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE), BLTabs.BLTabSortGroups.CUT_BRICKS);
    public static final Block POLISHED_BLACKSTONE_TILES = registerSimpleBlock("polished_blackstone_tiles", FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS), BLTabs.BLTabSortGroups.CUT_BRICKS);

    //cut copper bricks

    //dripstones

    private static FabricBlockSettings createGemBlockProperties(MapColor mapColor) {
        return FabricBlockSettings.of(Material.METAL, mapColor)
                .requiresTool()
                .strength(5.0F, 6.0F)
                .sounds(BlockSoundGroup.METAL);
    }

    private static FabricBlockSettings createOreBlockSettings() {
        return FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F);
    }

    private static FabricBlockSettings createDeepslateOreBlockSettings() {
        return createOreBlockSettings().mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE);
    }

    private static Block registerOreBlock(final String name, final boolean isDeepslateOre) {
        return registerBlock(name, new OreBlock(isDeepslateOre ? createDeepslateOreBlockSettings() : createOreBlockSettings(), UniformIntProvider.create(3, 7)), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.OVERWORLD_ORES);
    }

    private static Block registerNetherOreBlock(final String name) {
        return registerBlock(name, new OreBlock(createOreBlockSettings().mapColor(MapColor.DARK_RED).sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(2, 5)), BLTabs.TAB_BUILDING_BLOCKS, BLTabs.BLTabSortGroups.NETHER_ORES);
    }

    private static Block registerSimpleBlock(String name, FabricBlockSettings properties, BLTabs.BLTabSortGroups sortGroup) {
        return registerBlock(name, new Block(properties), BLTabs.TAB_BUILDING_BLOCKS, sortGroup);
    }

    private static Block registerBlockWithoutBlockItem(final String name, final Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(BlazersMod.MOD_ID, name), block);
    }

    private static Block registerBlock(final String name, final Block block, final ItemGroup tab, final BLTabs.BLTabSortGroups sortGroup) {
        BLItems.registerItem(name, new BlockItem(block, new FabricItemSettings().group(tab)), sortGroup);
        return registerBlockWithoutBlockItem(name, block);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " blocks");
    }

}