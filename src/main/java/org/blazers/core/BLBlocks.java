package org.blazers.core;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.block.AtomicTntBlock;
import org.blazers.block.HollowBlock;
import org.blazers.block.WaxedCopperButtonBlock;
import org.blazers.block.WeatheringCopperButtonBlock;

import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLBlocks {

    /**
     * {@link Block Blocks} {@link DeferredRegister <Block> Registry}
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlazersMod.MOD_ID);

    //#region Blocks

    public static final RegistryObject<Block> RUBY_ORE = registerNetherOreBlock("ruby_ore");
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> TOPAZ_ORE = registerOreBlock("topaz_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerOreBlock("deepslate_topaz_ore", true);
    public static final RegistryObject<Block> PEARL_ORE = registerBlock("pearl_ore", () ->
            new SandBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND)
                    .requiresCorrectToolForDrops()
    ), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MALACHITE_ORE = registerNetherOreBlock("malachite_ore");
    public static final RegistryObject<Block> ONICE_ORE = registerNetherOreBlock("onice_ore");
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new OreBlock(
            BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)
    ), BLTabs.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> RUBY_BLOCK = registerSimpleBlock("ruby_block", createGemBlockProperties(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerSimpleBlock("sapphire_block", createGemBlockProperties(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerSimpleBlock("topaz_block", createGemBlockProperties(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> PEARL_BLOCK = registerSimpleBlock("pearl_block", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK)
            .sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(0.75F));
    public static final RegistryObject<Block> MALACHITE_BLOCK = registerSimpleBlock("malachite_block", createGemBlockProperties(MaterialColor.TERRACOTTA_GREEN));
    public static final RegistryObject<Block> ONICE_BLOCK = registerSimpleBlock("onice_block", createGemBlockProperties(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerSimpleBlock("uranium_block", BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER));
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = registerSimpleBlock("raw_uranium_block", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(5.0F, 6.0F));

    public static final RegistryObject<Block> HOLLOW_OAK_LOG = registerBlock("hollow_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_OAK_LOG = registerBlock("hollow_stripped_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> HOLLOW_SPRUCE_LOG = registerBlock("hollow_spruce_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_SPRUCE_LOG = registerBlock("hollow_stripped_spruce_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.QUARTZ).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_BIRCH_LOG = registerBlock("hollow_stripped_birch_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_JUNGLE_LOG = registerBlock("hollow_jungle_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_JUNGLE_LOG = registerBlock("hollow_stripped_jungle_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_ACACIA_LOG = registerBlock("hollow_acacia_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_ACACIA_LOG = registerBlock("hollow_stripped_acacia_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_DARK_OAK_LOG = registerBlock("hollow_dark_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_DARK_OAK_LOG = registerBlock("hollow_stripped_dark_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_WARPED_STEM = registerBlock("hollow_warped_stem", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_WARPED_STEM = registerBlock("hollow_stripped_warped_stem", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_CRIMSON_STEM = registerBlock("hollow_crimson_stem", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_CRIMSON_STEM = registerBlock("hollow_stripped_crimson_stem", () -> new HollowBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> COPPER_BUTTON = registerBlock("copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.UNAFFECTED), BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = registerBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.WEATHERED), BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = registerBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.EXPOSED), BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = registerBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.OXIDIZED), BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = registerBlock("waxed_copper_button", WaxedCopperButtonBlock::new, BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = registerBlock("waxed_weathered_copper_button", WaxedCopperButtonBlock::new, BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = registerBlock("waxed_exposed_copper_button", WaxedCopperButtonBlock::new, BLTabs.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = registerBlock("waxed_oxidized_copper_button", WaxedCopperButtonBlock::new, BLTabs.TAB_REDSTONE);

    public static final RegistryObject<Block> ATOMIC_TNT = registerBlock("atomic_tnt", AtomicTntBlock::new, BLTabs.TAB_REDSTONE);

    //#endregion

    /**
     * Create the {@link BlockBehaviour.Properties Block Properties}
     * for a {@link Block Gem Block}
     *
     * @param mapColor {@link MaterialColor Block Map Color}
     * @return {@link BlockBehaviour.Properties Block Properties}
     */
    private static BlockBehaviour.Properties createGemBlockProperties(MaterialColor mapColor) {
        return BlockBehaviour.Properties.of(Material.METAL, mapColor)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL);
    }

    /**
     * Create the {@link BlockBehaviour.Properties Block Properties}
     * for an {@link Block Ore Block}
     *
     * @return {@link BlockBehaviour.Properties Block Properties}
     */
    private static BlockBehaviour.Properties createOreBlockProperties() {
        return BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F);
    }

    /**
     * Create the {@link BlockBehaviour.Properties Block Properties}
     * for a {@link Block Deepslate Ore Block}
     *
     * @return {@link BlockBehaviour.Properties Block Properties}
     */
    private static BlockBehaviour.Properties createDeepslateOreBlockProperties() {
        return createOreBlockProperties()
                .color(MaterialColor.DEEPSLATE)
                .strength(4.5F, 3.0F)
                .sound(SoundType.DEEPSLATE);
    }

    /**
     * Register an {@link Block Ore Block}
     *
     * @param name {@link String Block Name}
     * @param isDeepslateOre {@link Boolean Whether the Ore is a Deepslate Ore}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerOreBlock(String name, boolean isDeepslateOre) {
        return registerBlock(name, () -> new OreBlock(isDeepslateOre ? createDeepslateOreBlockProperties() : createOreBlockProperties(),
                UniformInt.of(3, 7)), BLTabs.TAB_BUILDING_BLOCKS);
    }

    /**
     * Register a {@link Block Nether Ore Block}
     *
     * @param name {@link String Block Name}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerNetherOreBlock(String name) {
        return registerBlock(name, () ->
                        new OreBlock(createOreBlockProperties().color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE),
                                UniformInt.of(2, 5))
                , BLTabs.TAB_BUILDING_BLOCKS);
    }

    /**
     * Register a {@link Block Simple Block}
     *
     * @param name {@link String Block Name}
     * @param properties {@link BlockBehaviour.Properties Block Properties}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new Block(properties), BLTabs.TAB_BUILDING_BLOCKS);
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String Block Name}
     * @param blockSupplier {@link Supplier<Block> Block Supplier}
     * @param tab {@link CreativeModeTab Creative Mode Tab}
     * @param <T> Block Type
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier, CreativeModeTab tab) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, block, tab);
        return block;
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name  {@link String Block Name}
     * @param block {@link RegistryObject<T> Block}
     * @param tab   {@link CreativeModeTab Creative Mode Tab}
     * @param <T>   Block Type
     */
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        BLItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    /**
     * Register the {@link Block Transparent Blocks}
     */
    public static void registerTransparentBlocks() {
        setRenderType(RenderType.cutout(),
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
                HOLLOW_WARPED_STEM,
                HOLLOW_STRIPPED_WARPED_STEM,
                HOLLOW_CRIMSON_STEM,
                HOLLOW_STRIPPED_CRIMSON_STEM
        );
    }

    /**
     * Sets a {@link RenderType Render Type} for some {@link Block Blocks}
     *
     * @param renderType {@link RenderType Render Type}
     * @param blocks {@link Block Blocks}
     */
    @SafeVarargs
    private static void setRenderType(RenderType renderType, RegistryObject<Block>... blocks) {
        for (RegistryObject<Block> block: blocks) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), renderType);
        }
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link Block Blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}