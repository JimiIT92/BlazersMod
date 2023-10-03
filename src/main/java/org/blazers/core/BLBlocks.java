package org.blazers.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.block.*;

import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLBlocks {

    /**
     * {@link Block Blocks} {@link DeferredRegister<Block> Registry}
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlazersMod.MOD_ID);

    //#region Blocks

    public static final RegistryObject<Block> HOLLOW_OAK_LOG = registerBlock("hollow_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_OAK_LOG = registerBlock("hollow_stripped_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_SPRUCE_LOG = registerBlock("hollow_spruce_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_SPRUCE_LOG = registerBlock("hollow_stripped_spruce_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.NATURAL);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_BIRCH_LOG = registerBlock("hollow_stripped_birch_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_JUNGLE_LOG = registerBlock("hollow_jungle_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_JUNGLE_LOG = registerBlock("hollow_stripped_jungle_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_ACACIA_LOG = registerBlock("hollow_acacia_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_ACACIA_LOG = registerBlock("hollow_stripped_acacia_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_DARK_OAK_LOG = registerBlock("hollow_dark_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_DARK_OAK_LOG = registerBlock("hollow_stripped_dark_oak_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_MANGROVE_LOG = registerBlock("hollow_mangrove_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_MANGROVE_LOG = registerBlock("hollow_stripped_mangrove_log", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_CRIMSON_STEM = registerBlock("hollow_crimson_stem", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_CRIMSON_STEM = registerBlock("hollow_stripped_crimson_stem", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_WARPED_STEM = registerBlock("hollow_warped_stem", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_WARPED_STEM = registerBlock("hollow_stripped_warped_stem", () -> new HollowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).strength(2.0F).sound(SoundType.STEM)), BLTabs.BLTabKeys.BUILDING_BLOCKS);

    public static final RegistryObject<Block> COBBLED_GRANITE = registerSimpleBlock("cobbled_granite", BlockBehaviour.Properties.copy(Blocks.GRANITE));
    public static final RegistryObject<Block> COBBLED_DIORITE = registerSimpleBlock("cobbled_diorite", BlockBehaviour.Properties.copy(Blocks.DIORITE));
    public static final RegistryObject<Block> COBBLED_ANDESITE = registerSimpleBlock("cobbled_andesite", BlockBehaviour.Properties.copy(Blocks.ANDESITE));
    public static final RegistryObject<Block> SANDSTONE_BRICKS = registerSimpleBlock("sandstone_bricks", BlockBehaviour.Properties.copy(Blocks.SANDSTONE));
    public static final RegistryObject<Block> CUT_BRICKS = registerSimpleBlock("cut_bricks", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final RegistryObject<Block> PURPUR_TILES = registerSimpleBlock("purpur_tiles", BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK));
    public static final RegistryObject<Block> STONE_TILES = registerSimpleBlock("stone_tiles", BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS));
    public static final RegistryObject<Block> MOSSY_STONE_TILES = registerSimpleBlock("mossy_stone_tiles", BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS));
    public static final RegistryObject<Block> CUT_DEEPSLATE_BRICKS = registerSimpleBlock("cut_deepslate_bricks", BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BRICKS = registerSimpleBlock("polished_deepslate_bricks", BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE));
    public static final RegistryObject<Block> END_STONE_TILES = registerSimpleBlock("end_stone_tiles", BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS));
    public static final RegistryObject<Block> QUARTZ_TILES = registerSimpleBlock("quartz_tiles", BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS));
    public static final RegistryObject<Block> CUT_PRISMARINE_BRICKS = registerSimpleBlock("cut_prismarine_bricks", BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS));
    public static final RegistryObject<Block> DARK_PRISMARINE_BRICKS = registerSimpleBlock("dark_prismarine_bricks", BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE));
    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS = registerSimpleBlock("red_sandstone_bricks", BlockBehaviour.Properties.copy(Blocks.RED_SANDSTONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_TILES = registerSimpleBlock("polished_blackstone_tiles", BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS));

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerSimpleBlock("sapphire_block", createGemBlockProperties(MapColor.COLOR_BLUE));
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerSimpleBlock("topaz_block", createGemBlockProperties(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> PEARL_BLOCK = registerSimpleBlock("pearl_block", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK)
            .sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(0.75F));
    public static final RegistryObject<Block> RUBY_BLOCK = registerSimpleBlock("ruby_block", createGemBlockProperties(MapColor.COLOR_RED));
    public static final RegistryObject<Block> MALACHITE_BLOCK = registerSimpleBlock("malachite_block", createGemBlockProperties(MapColor.TERRACOTTA_GREEN));
    public static final RegistryObject<Block> ONICE_BLOCK = registerSimpleBlock("onice_block", createGemBlockProperties(MapColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerSimpleBlock("uranium_block", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER));
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = registerSimpleBlock("raw_uranium_block", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(5.0F, 6.0F));

    public static final RegistryObject<Block> CUT_COPPER_BRICKS = registerBlock("cut_copper_bricks", () -> new WeatheringCutCopperBricks(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_BRICKS = registerBlock("exposed_cut_copper_bricks", () -> new WeatheringCutCopperBricks(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_BRICKS = registerBlock("weathered_cut_copper_bricks", () -> new WeatheringCutCopperBricks(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_BRICKS = registerBlock("oxidized_cut_copper_bricks", () -> new WeatheringCutCopperBricks(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WAXED_CUT_COPPER_BRICKS = registerBlock("waxed_cut_copper_bricks", () -> new WaxedCutCopperBricksBlock(Blocks.CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_BRICKS = registerBlock("waxed_exposed_cut_copper_bricks", () -> new WaxedCutCopperBricksBlock(Blocks.EXPOSED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_BRICKS = registerBlock("waxed_weathered_cut_copper_bricks", () -> new WaxedCutCopperBricksBlock(Blocks.WEATHERED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_BRICKS = registerBlock("waxed_oxidized_cut_copper_bricks", () -> new WaxedCutCopperBricksBlock(Blocks.OXIDIZED_CUT_COPPER), BLTabs.BLTabKeys.BUILDING_BLOCKS);

    public static final RegistryObject<Block> COPPER_BUTTON = registerBlock("copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.UNAFFECTED), BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = registerBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.WEATHERED), BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = registerBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.EXPOSED), BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = registerBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.OXIDIZED), BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = registerBlock("waxed_copper_button", WaxedCopperButtonBlock::new, BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = registerBlock("waxed_weathered_copper_button", WaxedCopperButtonBlock::new, BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = registerBlock("waxed_exposed_copper_button", WaxedCopperButtonBlock::new, BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = registerBlock("waxed_oxidized_copper_button", WaxedCopperButtonBlock::new, BLTabs.BLTabKeys.BUILDING_BLOCKS, BLTabs.BLTabKeys.REDSTONE);

    public static final RegistryObject<Block> POINTED_STONE_DRIPSTONE = registerDripstone("pointed_stone_dripstone", Blocks.STONE);
    public static final RegistryObject<Block> POINTED_GRANITE_DRIPSTONE = registerDripstone("pointed_granite_dripstone", Blocks.GRANITE);
    public static final RegistryObject<Block> POINTED_DIORITE_DRIPSTONE = registerDripstone("pointed_diorite_dripstone", Blocks.DIORITE);
    public static final RegistryObject<Block> POINTED_ANDESITE_DRIPSTONE = registerDripstone("pointed_andesite_dripstone", Blocks.ANDESITE);
    public static final RegistryObject<Block> POINTED_ICE_DRIPSTONE = registerBlock("pointed_ice_dripstone", PointedIceDripstoneBlock::new, BLTabs.BLTabKeys.NATURAL);

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> TOPAZ_ORE = registerOreBlock("topaz_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerOreBlock("deepslate_topaz_ore", true);
    public static final RegistryObject<Block> PEARL_ORE = registerBlock("pearl_ore", () ->
            new SandBlock(14406560, BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND)
                    .requiresCorrectToolForDrops()
            ), BLTabs.BLTabKeys.NATURAL);
    public static final RegistryObject<Block> RUBY_ORE = registerNetherOreBlock("ruby_ore");
    public static final RegistryObject<Block> MALACHITE_ORE = registerNetherOreBlock("malachite_ore");
    public static final RegistryObject<Block> ONICE_ORE = registerNetherOreBlock("onice_ore");
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new DropExperienceBlock(
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)
    ), BLTabs.BLTabKeys.NATURAL);

    public static final RegistryObject<Block> ATOMIC_TNT = registerBlock("atomic_tnt", AtomicTntBlock::new, BLTabs.BLTabKeys.REDSTONE, BLTabs.BLTabKeys.COMBAT);

    public static final RegistryObject<Block> BROWN_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("brown_mushroom_wall_fan",
            () -> new BaseCoralWallFanBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.GRASS).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(Blocks.BROWN_MUSHROOM)));
    public static final RegistryObject<Block> RED_MUSHROOM_WALL_FAN = registerBlockWithoutBlockItem("red_mushroom_wall_fan",
            () -> new BaseCoralWallFanBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(Blocks.RED_MUSHROOM)));

    public static final RegistryObject<Block> CATTAIL = registerBlock("cattail", CattailBlock::new, BLTabs.BLTabKeys.NATURAL);
    public static final RegistryObject<Block> POTTED_CATTAIL = registerBlockWithoutBlockItem("potted_cattail", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CATTAIL, BlockBehaviour.Properties.of().instabreak().noOcclusion()));

    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs", () -> new StairBlock(Blocks.WHITE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs", () -> new StairBlock(Blocks.ORANGE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs", () -> new StairBlock(Blocks.MAGENTA_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs", () -> new StairBlock(Blocks.YELLOW_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs", () -> new StairBlock(Blocks.LIME_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs", () -> new StairBlock(Blocks.PINK_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs", () -> new StairBlock(Blocks.GRAY_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs", () -> new StairBlock(Blocks.CYAN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs", () -> new StairBlock(Blocks.PURPLE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs", () -> new StairBlock(Blocks.BLUE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs", () -> new StairBlock(Blocks.BROWN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs", () -> new StairBlock(Blocks.GREEN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs", () -> new StairBlock(Blocks.RED_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);
    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs", () -> new StairBlock(Blocks.BLACK_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)), BLTabs.BLTabKeys.COLORED_BLOCKS);

    //#endregion

    /**
     * Create the {@link BlockBehaviour.Properties Block Properties}
     * for a {@link Block Gem Block}
     *
     * @param mapColor {@link MapColor Block Map Color}
     * @return {@link BlockBehaviour.Properties Block Properties}
     */
    private static BlockBehaviour.Properties createGemBlockProperties(MapColor mapColor) {
        return BlockBehaviour.Properties.of().mapColor(mapColor)
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
        return BlockBehaviour.Properties.of()
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
                .mapColor(MapColor.DEEPSLATE)
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
        return registerBlock(name, () -> new DropExperienceBlock(isDeepslateOre ? createDeepslateOreBlockProperties() : createOreBlockProperties(),
                UniformInt.of(3, 7)), BLTabs.BLTabKeys.NATURAL);
    }

    /**
     * Register a {@link Block Nether Ore Block}
     *
     * @param name {@link String Block Name}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerNetherOreBlock(String name) {
        return registerBlock(name, () ->
                        new DropExperienceBlock(createOreBlockProperties().mapColor(MapColor.NETHER).sound(SoundType.NETHER_ORE),
                                UniformInt.of(2, 5))
                , BLTabs.BLTabKeys.NATURAL);
    }

    /**
     * Register a {@link BLPointedDripstoneBlock Dripstone Block}
     *
     * @param name {@link String Block Name}
     * @param materialBlock {@link Block Material Block}
     * @return {@link RegistryObject<Block> Registered Dripstone Block}
     */
    private static RegistryObject<Block> registerDripstone(final String name, final Block materialBlock) {
        return registerBlock(name, () -> new BLPointedDripstoneBlock(materialBlock), BLTabs.BLTabKeys.NATURAL);
    }

    /**
     * Register a {@link Block Simple Block}
     *
     * @param name {@link String Block Name}
     * @param properties {@link BlockBehaviour.Properties Block Properties}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new Block(properties), BLTabs.BLTabKeys.BUILDING_BLOCKS);
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String Block Name}
     * @param blockSupplier {@link Supplier<Block> Block Supplier}
     * @param tabs {@link CreativeModeTab Creative Mode Tabs}
     * @param <T> Block Type
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier, BLTabs.BLTabKeys... tabs) {
        RegistryObject<T> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block, tabs);
        return block;
    }

    /**
     * Register a {@link Block Block}
     * without register a {@link BlockItem Block Item}
     *
     * @param name {@link String Block Name}
     * @param blockSupplier {@link Supplier<Block> Block Supplier}
     * @param <T> Block Type
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name  {@link String Block Name}
     * @param block {@link RegistryObject<T> Block}
     * @param tabs   {@link CreativeModeTab Creative Mode Tabs}
     * @param <T>   Block Type
     */
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, BLTabs.BLTabKeys... tabs) {
        BLItems.registerItem(name, () -> new BlockItem(block.get(), BLItems.createSimpleItemProperties()), tabs);
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