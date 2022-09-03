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

    private static Block registerBlock(final String name, final Block block, final ItemGroup tab, final BLTabs.BLTabSortGroups sortGroup) {
        BLItems.registerItem(name, new BlockItem(block, new FabricItemSettings().group(tab)), sortGroup);
        return Registry.register(Registry.BLOCK, new Identifier(BlazersMod.MOD_ID, name), block);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " blocks");
    }

}