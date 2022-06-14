package org.blazers.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod Blocks}
 */
public final class BLBlocks {

    /**
     * {@link Block Blocks} {@link DeferredRegister <Block> Registry}
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlazersMod.MOD_ID);

    //#region Blocks

    public static final RegistryObject<Block> RUBY_ORE = registerNetherOreBlock("ruby_ore");
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerGemOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerGemOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> TOPAZ_ORE = registerGemOreBlock("topaz_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerGemOreBlock("deepslate_topaz_ore", true);
    public static final RegistryObject<Block> PEARL_ORE = registerBlock("pearl_ore", () ->
            new SandBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND)
                    .requiresCorrectToolForDrops()
    ), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MALACHITE_ORE = registerNetherOreBlock("malachite_ore");
    public static final RegistryObject<Block> ONICE_ORE = registerNetherOreBlock("onice_ore");
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new OreBlock(
            BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)
    ), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> RUBY_BLOCK = registerSimpleBlock("ruby_block", createGemBlockProperties(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerSimpleBlock("sapphire_block", createGemBlockProperties(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerSimpleBlock("topaz_block", createGemBlockProperties(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> PEARL_BLOCK = registerSimpleBlock("pearl_block", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK)
            .sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(0.75F));
    public static final RegistryObject<Block> MALACHITE_BLOCK = registerSimpleBlock("malachite_block", createGemBlockProperties(MaterialColor.TERRACOTTA_GREEN));
    public static final RegistryObject<Block> ONICE_BLOCK = registerSimpleBlock("onice_block", createGemBlockProperties(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerSimpleBlock("uranium_block", BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER));
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = registerSimpleBlock("raw_uranium_block", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(5.0F, 6.0F));

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

    private static BlockBehaviour.Properties createOreBlockProperties() {
        return BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F);
    }

    private static BlockBehaviour.Properties createDeepslateOreBlockProperties() {
        return createOreBlockProperties()
                .color(MaterialColor.DEEPSLATE)
                .strength(4.5F, 3.0F)
                .sound(SoundType.DEEPSLATE);
    }

    private static RegistryObject<Block> registerGemOreBlock(String name, boolean isDeepslateOre) {
        return registerBlock(name, () -> new OreBlock(isDeepslateOre ? createDeepslateOreBlockProperties() : createOreBlockProperties(),
                UniformInt.of(3, 7)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    }

    private static RegistryObject<Block> registerNetherOreBlock(String name) {
        return registerBlock(name, () ->
                        new OreBlock(createOreBlockProperties().color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE),
                                UniformInt.of(2, 5))
                , CreativeModeTab.TAB_BUILDING_BLOCKS);
    }

    private static RegistryObject<Block> registerOreBlock(String name) {
        return registerBlock(name, () ->
                new OreBlock(createOreBlockProperties())
        , CreativeModeTab.TAB_BUILDING_BLOCKS);
    }

    /**
     * Register a {@link Block Simple Block}
     *
     * @param name {@link String Block Name}
     * @param properties {@link BlockBehaviour.Properties Block Properties}
     * @return {@link RegistryObject<Item> Registered Block}
     */
    private static RegistryObject<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new Block(properties), CreativeModeTab.TAB_BUILDING_BLOCKS);
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
     * Register the {@link BlazersMod Blazers Mod} {@link Block Blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}