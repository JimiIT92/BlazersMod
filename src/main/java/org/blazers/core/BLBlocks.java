package org.blazers.core;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import org.blazers.BlazersMod;
import org.blazers.block.HollowBlock;
import org.hendrix.helper.BlockHelper;
import org.hendrix.helper.ResourceHelper;
import org.hendrix.registry.HCBlocks;

/**
 * {@link BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLBlocks {

    //#region Blocks

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

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {
        registerStrippableBlocks();
        registerFlammableBlocks();
    }

}