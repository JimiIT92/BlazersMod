package org.blazers.core;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.HoneycombItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import org.blazers.BlazersMod;
import org.blazers.block.BLOxidizableBlock;
import org.blazers.block.HollowBlock;
import org.hendrix.helper.BlockHelper;
import org.hendrix.helper.ResourceHelper;
import org.hendrix.registry.HCBlocks;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLBlocks {

    //#region Blocks

    //#region Ores and Storage Blocks

    public static final Block SAPPHIRE_ORE = HCBlocks.registerOre("sapphire_ore");
    public static final Block DEEPSLATE_SAPPHIRE_ORE = HCBlocks.registerDeepslateOre("deepslate_sapphire_ore");
    public static final Block TOPAZ_ORE = HCBlocks.registerOre("topaz_ore");
    public static final Block DEEPSLATE_TOPAZ_ORE = HCBlocks.registerDeepslateOre("deepslate_topaz_ore");
    public static final Block PEARL_ORE = registerPearlOre();
    public static final Block RUBY_ORE = HCBlocks.registerNetherOre("ruby_ore");
    public static final Block MALACHITE_ORE = HCBlocks.registerNetherOre("malachite_ore");
    public static final Block ONICE_ORE = HCBlocks.registerNetherOre("onice_ore");
    public static final Block URANIUM_ORE = registerUraniumOre();

    public static final Block SAPPHIRE_BLOCK = HCBlocks.registerOreStorageBlock("sapphire_block", MapColor.BLUE);
    public static final Block TOPAZ_BLOCK = HCBlocks.registerOreStorageBlock("topaz_block", MapColor.ORANGE);
    public static final Block PEARL_BLOCK = registerPearlBlock();
    public static final Block RUBY_BLOCK = HCBlocks.registerOreStorageBlock("ruby_block", MapColor.RED);
    public static final Block MALACHITE_BLOCK = HCBlocks.registerOreStorageBlock("malachite_block", MapColor.TERRACOTTA_GREEN);
    public static final Block ONICE_BLOCK = HCBlocks.registerOreStorageBlock("onice_block", MapColor.TERRACOTTA_BLACK);
    public static final Block RAW_URANIUM_BLOCK = registerRawUraniumBlock();
    public static final Block URANIUM_BLOCK = registerUraniumBlock();

    //#endregion

    //#region Hollow Logs

    public static final Block HOLLOW_OAK_LOG = registerHollowLog(WoodType.OAK, false);
    public static final Block HOLLOW_STRIPPED_OAK_LOG = registerHollowLog(WoodType.OAK, true);
    public static final Block HOLLOW_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, false);
    public static final Block HOLLOW_STRIPPED_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, true);
    public static final Block HOLLOW_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, false);
    public static final Block HOLLOW_STRIPPED_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, true);
    public static final Block HOLLOW_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, false);
    public static final Block HOLLOW_STRIPPED_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, true);
    public static final Block HOLLOW_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, false);
    public static final Block HOLLOW_STRIPPED_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, true);
    public static final Block HOLLOW_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, false);
    public static final Block HOLLOW_STRIPPED_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, true);
    public static final Block HOLLOW_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, false);
    public static final Block HOLLOW_STRIPPED_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, true);
    public static final Block HOLLOW_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, false);
    public static final Block HOLLOW_STRIPPED_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, true);
    public static final Block HOLLOW_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, false);
    public static final Block HOLLOW_STRIPPED_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, true);
    public static final Block HOLLOW_PALE_OAK_LOG = registerHollowLog(WoodType.PALE_OAK, false);
    public static final Block HOLLOW_STRIPPED_PALE_OAK_LOG = registerHollowLog(WoodType.PALE_OAK, true);
    public static final Block HOLLOW_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, false);
    public static final Block HOLLOW_STRIPPED_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, true);
    public static final Block HOLLOW_WARPED_STEM = registerHollowLog(WoodType.WARPED, false);
    public static final Block HOLLOW_STRIPPED_WARPED_STEM = registerHollowLog(WoodType.WARPED, true);

    //#endregion

    //#region Stone Variants

    public static final Block COBBLED_GRANITE = registerStoneVariant("cobbled_granite", Suppliers.memoize(() -> Blocks.GRANITE));
    public static final Block COBBLED_DIORITE = registerStoneVariant("cobbled_diorite", Suppliers.memoize(() -> Blocks.DIORITE));
    public static final Block COBBLED_ANDESITE = registerStoneVariant("cobbled_andesite", Suppliers.memoize(() -> Blocks.ANDESITE));
    public static final Block SANDSTONE_BRICKS = registerStoneVariant("sandstone_bricks", Suppliers.memoize(() -> Blocks.SANDSTONE));
    public static final Block CUT_BRICKS = registerStoneVariant("cut_bricks", Suppliers.memoize(() -> Blocks.BRICKS));
    public static final Block PURPUR_TILES = registerStoneVariant("purpur_tiles", Suppliers.memoize(() -> Blocks.PURPUR_BLOCK));
    public static final Block STONE_TILES = registerStoneVariant("stone_tiles", Suppliers.memoize(() -> Blocks.STONE_BRICKS));
    public static final Block MOSSY_STONE_TILES = registerStoneVariant("mossy_stone_tiles", Suppliers.memoize(() -> Blocks.MOSSY_STONE_BRICKS));
    public static final Block CUT_DEEPSLATE_BRICKS = registerStoneVariant("cut_deepslate_bricks", Suppliers.memoize(() -> Blocks.DEEPSLATE_BRICKS));
    public static final Block POLISHED_DEEPSLATE_BRICKS = registerStoneVariant("polished_deepslate_bricks", Suppliers.memoize(() -> Blocks.POLISHED_DEEPSLATE));
    public static final Block END_STONE_TILES = registerStoneVariant("end_stone_tiles", Suppliers.memoize(() -> Blocks.END_STONE_BRICKS));
    public static final Block QUARTZ_TILES = registerStoneVariant("quartz_tiles", Suppliers.memoize(() -> Blocks.QUARTZ_BRICKS));
    public static final Block CUT_PRISMARINE_BRICKS = registerStoneVariant("cut_prismarine_bricks", Suppliers.memoize(() -> Blocks.PRISMARINE_BRICKS));
    public static final Block DARK_PRISMARINE_BRICKS = registerStoneVariant("dark_prismarine_bricks", Suppliers.memoize(() -> Blocks.DARK_PRISMARINE));
    public static final Block RED_SANDSTONE_BRICKS = registerStoneVariant("red_sandstone_bricks", Suppliers.memoize(() -> Blocks.RED_SANDSTONE));
    public static final Block POLISHED_BLACKSTONE_TILES = registerStoneVariant("polished_blackstone_tiles", Suppliers.memoize(() -> Blocks.POLISHED_BLACKSTONE_BRICKS));

    //#endregion

    //#region Copper Blocks

    public static final Block CUT_COPPER_BRICKS = registerCutCopperBricksBlock(Oxidizable.OxidationLevel.UNAFFECTED, Suppliers.memoize(() -> Blocks.CUT_COPPER));
    public static final Block EXPOSED_CUT_COPPER_BRICKS = registerCutCopperBricksBlock(Oxidizable.OxidationLevel.EXPOSED, Suppliers.memoize(() -> Blocks.EXPOSED_CUT_COPPER));
    public static final Block WEATHERED_CUT_COPPER_BRICKS = registerCutCopperBricksBlock(Oxidizable.OxidationLevel.WEATHERED, Suppliers.memoize(() -> Blocks.WEATHERED_CUT_COPPER));
    public static final Block OXIDIZED_CUT_COPPER_BRICKS = registerCutCopperBricksBlock(Oxidizable.OxidationLevel.OXIDIZED, Suppliers.memoize(() -> Blocks.OXIDIZED_CUT_COPPER));

    public static final Block WAXED_CUT_COPPER_BRICKS = registerWaxedCutCopperBricksBlock(Oxidizable.OxidationLevel.UNAFFECTED, Suppliers.memoize(() -> Blocks.CUT_COPPER));
    public static final Block WAXED_EXPOSED_CUT_COPPER_BRICKS = registerWaxedCutCopperBricksBlock(Oxidizable.OxidationLevel.EXPOSED, Suppliers.memoize(() -> Blocks.EXPOSED_CUT_COPPER));
    public static final Block WAXED_WEATHERED_CUT_COPPER_BRICKS = registerWaxedCutCopperBricksBlock(Oxidizable.OxidationLevel.WEATHERED, Suppliers.memoize(() -> Blocks.WEATHERED_CUT_COPPER));
    public static final Block WAXED_OXIDIZED_CUT_COPPER_BRICKS = registerWaxedCutCopperBricksBlock(Oxidizable.OxidationLevel.OXIDIZED, Suppliers.memoize(() -> Blocks.OXIDIZED_CUT_COPPER));

    //#endregion

    //#endregion

    /**
     * Register an {@link HollowBlock Hollow Log Block}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @param isStripped {@link Boolean Whether the Log is stripped}
     * @return The {@link Block registered Block}
     */
    private static Block registerHollowLog(final WoodType woodType, final boolean isStripped) {
        final String name = "hollow_" + ResourceHelper.logName(woodType, isStripped);
        return HCBlocks.registerBlock(name, Suppliers.memoize(() -> new HollowBlock(BlockHelper.wood(name, woodType))));
    }

    /**
     * Register a {@link Block Stone Variant Block}
     *
     * @param name The {@link String Block name}
     * @param blockSupplier The {@link Supplier<Block> base Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerStoneVariant(final String name, final Supplier<Block> blockSupplier) {
        return HCBlocks.registerBlock(name, BlockHelper.settings(name, blockSupplier));
    }

    /**
     * Register the {@link #PEARL_ORE Pearl Ore}
     *
     * @return The {@link Block registered Pearl Ore}
     */
    private static Block registerPearlOre() {
        final String name = "pearl_ore";
        return HCBlocks.registerBlock(name, Suppliers.memoize(() -> new SandBlock(new ColorCode(14406560), BlockHelper.ore(name).mapColor(MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND))));
    }

    /**
     * Register the {@link #PEARL_BLOCK Pearl Block}
     *
     * @return The {@link Block registered Pearl Block}
     */
    private static Block registerPearlBlock() {
        final String name = "pearl_block";
        return HCBlocks.registerBlock(name, BlockHelper.oreStorage(name, MapColor.PINK).sounds(BlockSoundGroup.CALCITE).strength(0.75F));
    }

    /**
     * Register the {@link #URANIUM_ORE Uranium Ore}
     *
     * @return The {@link Block registered Uranium Ore}
     */
    private static Block registerUraniumOre() {
        final String name = "uranium_ore";
        return HCBlocks.registerOre(name, BlockHelper.defaultOreXpProvider(), BlockHelper.ore(name).mapColor(MapColor.GREEN).strength(30.0F, 1200.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS));
    }

    /**
     * Register the {@link #RAW_URANIUM_BLOCK Raw Uranium Block}
     *
     * @return The {@link Block registered Raw Uranium Block}
     */
    private static Block registerRawUraniumBlock() {
        final String name = "raw_uranium_block";
        return HCBlocks.registerBlock(name, BlockHelper.oreStorage(name, MapColor.EMERALD_GREEN).sounds(BlockSoundGroup.STONE).strength(5.0F, 6.0F));
    }

    /**
     * Register the {@link #URANIUM_ORE Uranium Ore}
     *
     * @return The {@link Block registered Uranium Ore}
     */
    private static Block registerUraniumBlock() {
        final String name = "uranium_block";
        return HCBlocks.registerBlock(name, BlockHelper.oreStorage(name, MapColor.GREEN).sounds(BlockSoundGroup.COPPER).strength(3.0F, 6.0F));
    }

    /**
     * Register a {@link OxidizableBlock Cut Copper Bricks Block}
     *
     * @param oxidationLevel The {@link Oxidizable.OxidationLevel Oxidation Level}
     * @param copperBlockSupplier The {@link Supplier<Block> Copper Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerCutCopperBricksBlock(final Oxidizable.OxidationLevel oxidationLevel, final Supplier<Block> copperBlockSupplier) {
        final String name = (Oxidizable.OxidationLevel.UNAFFECTED.equals(oxidationLevel) ? "" : (oxidationLevel.asString() + "_")).toLowerCase(Locale.ROOT) + "cut_copper_bricks";
        return HCBlocks.registerBlock(name, Suppliers.memoize(() -> new BLOxidizableBlock(oxidationLevel, BlockHelper.settings(name, copperBlockSupplier))));
    }

    /**
     * Register a {@link Block Waxed Cut Copper Bricks Block}
     *
     * @param oxidationLevel The {@link Oxidizable.OxidationLevel Oxidation Level}
     * @param copperBlockSupplier The {@link Supplier<Block> Copper Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerWaxedCutCopperBricksBlock(final Oxidizable.OxidationLevel oxidationLevel, final Supplier<Block> copperBlockSupplier) {
        final String name = "waxed_" + (Oxidizable.OxidationLevel.UNAFFECTED.equals(oxidationLevel) ? "" : (oxidationLevel.asString() + "_")).toLowerCase(Locale.ROOT) + "cut_copper_bricks";
        return HCBlocks.registerBlock(name, BlockHelper.settings(name, copperBlockSupplier));
    }

    /**
     * Register all strippable {@link Block Blocks}
     */
    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(HOLLOW_OAK_LOG, HOLLOW_STRIPPED_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_SPRUCE_LOG, HOLLOW_STRIPPED_SPRUCE_LOG);
        StrippableBlockRegistry.register(HOLLOW_BIRCH_LOG, HOLLOW_STRIPPED_BIRCH_LOG);
        StrippableBlockRegistry.register(HOLLOW_JUNGLE_LOG, HOLLOW_STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(HOLLOW_ACACIA_LOG, HOLLOW_STRIPPED_ACACIA_LOG);
        StrippableBlockRegistry.register(HOLLOW_DARK_OAK_LOG, HOLLOW_STRIPPED_DARK_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_MANGROVE_LOG, HOLLOW_STRIPPED_MANGROVE_LOG);
        StrippableBlockRegistry.register(HOLLOW_BAMBOO_BLOCK, HOLLOW_STRIPPED_BAMBOO_BLOCK);
        StrippableBlockRegistry.register(HOLLOW_CHERRY_LOG, HOLLOW_STRIPPED_CHERRY_LOG);
        StrippableBlockRegistry.register(HOLLOW_PALE_OAK_LOG, HOLLOW_STRIPPED_PALE_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_CRIMSON_STEM, HOLLOW_STRIPPED_CRIMSON_STEM);
        StrippableBlockRegistry.register(HOLLOW_WARPED_STEM, HOLLOW_STRIPPED_WARPED_STEM);
    }

    /**
     * Register all flammable {@link Block Blocks}
     */
    public static void registerFlammableBlocks() {
        HCBlocks.registerFlammableBlocks(
                5,
                5,
                HOLLOW_OAK_LOG,
                HOLLOW_STRIPPED_OAK_LOG,
                HOLLOW_SPRUCE_LOG,
                HOLLOW_STRIPPED_SPRUCE_LOG,
                HOLLOW_BIRCH_LOG,
                HOLLOW_STRIPPED_BIRCH_LOG,
                HOLLOW_JUNGLE_LOG,
                HOLLOW_STRIPPED_JUNGLE_LOG,
                HOLLOW_ACACIA_LOG,
                HOLLOW_STRIPPED_ACACIA_LOG,
                HOLLOW_DARK_OAK_LOG,
                HOLLOW_STRIPPED_DARK_OAK_LOG,
                HOLLOW_MANGROVE_LOG,
                HOLLOW_STRIPPED_MANGROVE_LOG,
                HOLLOW_BAMBOO_BLOCK,
                HOLLOW_STRIPPED_BAMBOO_BLOCK,
                HOLLOW_CHERRY_LOG,
                HOLLOW_STRIPPED_CHERRY_LOG,
                HOLLOW_PALE_OAK_LOG,
                HOLLOW_STRIPPED_PALE_OAK_LOG
        );
    }

    public static void registerWaxableBlocks() {
        final BiMap<Block, Block> unwaxedToWaxedBlocks = HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get();
        unwaxedToWaxedBlocks.put(BLBlocks.CUT_COPPER_BRICKS, BLBlocks.WAXED_CUT_COPPER_BRICKS);
        unwaxedToWaxedBlocks.put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS, BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS);
        unwaxedToWaxedBlocks.put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS, BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS);
        unwaxedToWaxedBlocks.put(BLBlocks.OXIDIZED_CUT_COPPER_BRICKS, BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS);
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {
        registerStrippableBlocks();
        registerFlammableBlocks();
        registerWaxableBlocks();
    }

}