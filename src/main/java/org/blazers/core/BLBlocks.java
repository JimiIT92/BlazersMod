package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.block.HollowLog;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.PropertyHelper;
import org.blazers.helper.RegistryHelper;

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

    //#region Ores and Ore Blocks

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOre(BLMaterials.SAPPHIRE, false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOre(BLMaterials.SAPPHIRE, true);
    public static final RegistryObject<Block> TOPAZ_ORE = registerOre(BLMaterials.TOPAZ, false);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerOre(BLMaterials.TOPAZ, true);
    public static final RegistryObject<Block> PEARL_ORE = registerBlock(BlockHelper.oreName(BLMaterials.PEARL, false), Suppliers.memoize(() ->
        new ColoredFallingBlock(new ColorRGBA(14406560), PropertyHelper.block(MapColor.SAND, 0.5F, SoundType.SAND).requiresCorrectToolForDrops())
    ));
    public static final RegistryObject<Block> RUBY_ORE = registerNetherOre(BLMaterials.RUBY);
    public static final RegistryObject<Block> MALACHITE_ORE = registerNetherOre(BLMaterials.MALACHITE);
    public static final RegistryObject<Block> ONICE_ORE = registerNetherOre(BLMaterials.ONICE);
    public static final RegistryObject<Block> URANIUM_ORE = registerOre(BLMaterials.URANIUM, false, 2, 5, PropertyHelper.netherOre().mapColor(MapColor.COLOR_GREEN).strength(30F, 1200F).sound(SoundType.ANCIENT_DEBRIS));

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register an {@link HollowLog hollow log}
     *
     * @param woodType The {@link WoodType log Wood Type}
     * @param isStrippedLog {@link Boolean If the log is a stripped log}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered hollow log}
     */
    private static RegistryObject<Block> registerHollowLog(final WoodType woodType, final boolean isStrippedLog, final FeatureFlag... featureFlags) {
        return registerBlock(getHollowLogName(woodType, isStrippedLog), Suppliers.memoize(() -> new HollowLog(woodType, BlockHelper.woodColor(woodType, isStrippedLog), featureFlags)));
    }

    /**
     * Register a {@link DropExperienceBlock Nether Ore Block}
     *
     * @param material {@link BLMaterials The Ore material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Nether Ore Block}
     */
    private static RegistryObject<Block> registerNetherOre(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerOre(material, false, 2, 5, PropertyHelper.netherOre(featureFlags));
    }

    /**
     * Register an {@link DropExperienceBlock Ore Block}
     *
     * @param material {@link BLMaterials The Ore material}
     * @param isDeepslateOre {@link Boolean If the Ore Block is a Deepslate Ore Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Ore Block}
     */
    private static RegistryObject<Block> registerOre(final BLMaterials material, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOre(material, isDeepslateOre, 3, 7, PropertyHelper.ore(isDeepslateOre, featureFlags));
    }

    /**
     * Register an {@link DropExperienceBlock Ore Block}
     *
     * @param material {@link BLMaterials The Ore material}
     * @param isDeepslateOre {@link Boolean If the Ore Block is a Deepslate Ore Block}
     * @param experience {@link Integer The minimum Ore Block experience dropped when mined}
     * @param additionalExperience {@link Integer The additional maximum Ore Block experience dropped when mined}
     * @param properties {@link BlockBehaviour.Properties The Block properties}
     * @return {@link RegistryObject<Block> The registered Ore Block}
     */
    private static RegistryObject<Block> registerOre(final BLMaterials material, final boolean isDeepslateOre, final int experience, final int additionalExperience, final BlockBehaviour.Properties properties) {
        return registerBlock(BlockHelper.oreName(material, isDeepslateOre), Suppliers.memoize(() -> new DropExperienceBlock(UniformInt.of(experience, additionalExperience), properties)));
    }

    /**
     * Get the {@link String name} for an {@link HollowLog hollow log}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param isStrippedLog {@link Boolean If the log is a stripped log}
     * @return {@link String The hollow log name}
     */
    private static String getHollowLogName(final WoodType woodType, final boolean isStrippedLog) {
        return "hollow_" + BlockHelper.woodName(woodType, isStrippedLog);
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
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
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