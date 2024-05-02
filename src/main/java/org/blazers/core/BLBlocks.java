package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.block.HollowLog;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.RegistryHelper;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLBlocks {

    //#region Registry

    /**
     * The {@link DeferredRegister<Block> Block Registry}
     */
    private static final DeferredRegister<Block> BLOCKS = RegistryHelper.registry(ForgeRegistries.BLOCKS);

    //#endregion

    //#region Blocks

    //#region Hollow Logs

    public static final RegistryObject<Block> HOLLOW_OAK_LOG = registerHollowLog(WoodType.OAK, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_OAK_LOG = registerHollowLog(WoodType.OAK, true);
    public static final RegistryObject<Block> HOLLOW_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, true);
    public static final RegistryObject<Block> HOLLOW_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, true);
    public static final RegistryObject<Block> HOLLOW_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, true);
    public static final RegistryObject<Block> HOLLOW_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, true);
    public static final RegistryObject<Block> HOLLOW_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, true);
    public static final RegistryObject<Block> HOLLOW_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, true);
    public static final RegistryObject<Block> HOLLOW_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, true);
    public static final RegistryObject<Block> HOLLOW_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, true);
    public static final RegistryObject<Block> HOLLOW_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, true);
    public static final RegistryObject<Block> HOLLOW_WARPED_STEM = registerHollowLog(WoodType.WARPED, false);
    public static final RegistryObject<Block> STRIPPED_HOLLOW_WARPED_STEM = registerHollowLog(WoodType.WARPED, true);

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register an {@link HollowLog hollow log}
     *
     * @param woodType The {@link WoodType log Wood Type}
     * @param isStrippedLog {@link Boolean If the log is a stripped log}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Block> The registered hollow log}
     */
    private static RegistryObject<Block> registerHollowLog(final WoodType woodType, final boolean isStrippedLog, final FeatureFlag... featureFlags) {
        return registerBlock(getHollowLogName(woodType, isStrippedLog), Suppliers.memoize(() -> new HollowLog(woodType, BlockHelper.woodColor(woodType, isStrippedLog), featureFlags)));
    }

    /**
     * Get the {@link String name} for an {@link HollowLog hollow log}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param isStrippedLog {@link Boolean If the log is a stripped log}
     * @return {@link String The hollow log name}
     */
    private static String getHollowLogName(final WoodType woodType, final boolean isStrippedLog) {
        return "hollow_" + (isStrippedLog ? "stripped_" : "") + woodType.name().toLowerCase(Locale.ROOT) + "_" + (woodType.equals(WoodType.BAMBOO) ? "block" : woodType.equals(WoodType.CRIMSON) || woodType.equals(WoodType.WARPED) ? "stem" : "log");
    }

    /**
     * Register a {@link Block Block} without registering its {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBlockWithoutBlockItem(final String name, final Supplier<? extends Block> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, blockSupplier);
        BLItems.registerBlockItem(name, blockSupplier, featureFlags);
        return block;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link Block Blocks}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    //#endregion

}