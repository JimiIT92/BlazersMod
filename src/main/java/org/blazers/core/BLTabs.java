package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.InstrumentComponent;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.registry.tag.PaintingVariantTags;
import org.blazers.BlazersMod;
import org.blazers.item.CopperHornItem;
import org.blazers.item.IPreEnchantedItem;
import org.hendrix.helper.RegistryHelper;
import org.hendrix.registry.HCTabs;

import java.util.Arrays;
import java.util.Comparator;

/**
 * {@link BlazersMod Blazers Mod} {@link ItemGroup Item Groups}
 */
public final class BLTabs {

    //#region Item Groups

    //public static ItemGroup BUILDING_BLOCKS = HCTabs.registerTab("building_blocks", Suppliers.memoize(() -> BLBlocks.RUBY_BLOCK));
    //public static ItemGroup COLORED_BLOCKS = HCTabs.registerTab("colored_blocks", Suppliers.memoize(() -> BLBlocks.YELLOW_CONCRETE_STAIRS));
    public static ItemGroup NATURAL = HCTabs.registerTab("natural", Suppliers.memoize(() -> BLBlocks.HOLLOW_BIRCH_LOG));
    public static ItemGroup FUNCTIONAL = HCTabs.registerTab("functional", Suppliers.memoize(() -> Items.PAINTING));
    //public static ItemGroup REDSTONE = HCTabs.registerTab("redstone", Suppliers.memoize(() -> BLBlocks.COPPER_BUTTON));
    public static ItemGroup TOOLS = HCTabs.registerTab("tools", Suppliers.memoize(() -> BLItems.EMERALD_PICKAXE));
    public static ItemGroup COMBAT = HCTabs.registerTab("combat", Suppliers.memoize(() -> BLItems.KATANA));
    public static ItemGroup FOOD_AND_DRINK = HCTabs.registerTab("food_and_drink", Suppliers.memoize(() -> BLItems.SASHIMI));
    public static ItemGroup INGREDIENTS = HCTabs.registerTab("ingredients", Suppliers.memoize(() -> BLItems.RUBY));
    //public static ItemGroup SPAWN_EGGS = HCTabs.registerTab("spawn_eggs", Suppliers.memoize(() -> BLItems.COPPER_GOLEM_SPAWN_EGG));

    //#endregion

    /**
     * Register all {@link ItemGroup Item Groups}
     */
    public static void register() {
        HCTabs.addItems(NATURAL,
                BLBlocks.HOLLOW_OAK_LOG,
                BLBlocks.HOLLOW_STRIPPED_OAK_LOG,
                BLBlocks.HOLLOW_SPRUCE_LOG,
                BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
                BLBlocks.HOLLOW_BIRCH_LOG,
                BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
                BLBlocks.HOLLOW_JUNGLE_LOG,
                BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
                BLBlocks.HOLLOW_ACACIA_LOG,
                BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
                BLBlocks.HOLLOW_DARK_OAK_LOG,
                BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
                BLBlocks.HOLLOW_MANGROVE_LOG,
                BLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
                BLBlocks.HOLLOW_BAMBOO_BLOCK,
                BLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK,
                BLBlocks.HOLLOW_CHERRY_LOG,
                BLBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
                BLBlocks.HOLLOW_PALE_OAK_LOG,
                BLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG,
                BLBlocks.HOLLOW_CRIMSON_STEM,
                BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
                BLBlocks.HOLLOW_WARPED_STEM,
                BLBlocks.HOLLOW_STRIPPED_WARPED_STEM
        );

        addPaintings();

        HCTabs.addItems(TOOLS,
                BLItems.EMERALD_SHOVEL,
                BLItems.EMERALD_PICKAXE,
                BLItems.EMERALD_AXE,
                BLItems.EMERALD_HOE,
                BLItems.SAPPHIRE_SHOVEL,
                BLItems.SAPPHIRE_PICKAXE,
                BLItems.SAPPHIRE_AXE,
                BLItems.SAPPHIRE_HOE,
                BLItems.RUBY_SHOVEL,
                BLItems.RUBY_PICKAXE,
                BLItems.RUBY_AXE,
                BLItems.RUBY_HOE,
                BLItems.ONICE_SHOVEL,
                BLItems.ONICE_PICKAXE,
                BLItems.ONICE_AXE,
                BLItems.ONICE_HOE,
                BLItems.MUSIC_DISC_SURVIVAL,
                BLItems.MUSIC_DISC_ENDERMAN_VS_BLAZE
        );

        addGoatHorns();
        addCopperHorns();

        HCTabs.addItems(COMBAT,
                BLItems.EMERALD_SWORD,
                BLItems.AMETHYST_SWORD,
                BLItems.SAPPHIRE_SWORD,
                BLItems.TOPAZ_HAMMER,
                BLItems.PEARL_SWORD,
                BLItems.RUBY_SWORD,
                BLItems.ONICE_SICKLE
        );

        addPreEnchantedItems(
                BLItems.BLAZERITE_SWORD,
                BLItems.GYULIANITE_SWORD
        );

        HCTabs.addItems(COMBAT,
                BLItems.KATANA,
                BLItems.WHITE_KATANA,
                BLItems.ORANGE_KATANA,
                BLItems.MAGENTA_KATANA,
                BLItems.LIGHT_BLUE_KATANA,
                BLItems.YELLOW_KATANA,
                BLItems.LIME_KATANA,
                BLItems.PINK_KATANA,
                BLItems.GRAY_KATANA,
                BLItems.LIGHT_GRAY_KATANA,
                BLItems.CYAN_KATANA,
                BLItems.PURPLE_KATANA,
                BLItems.BLUE_KATANA,
                BLItems.BROWN_KATANA,
                BLItems.GREEN_KATANA,
                BLItems.RED_KATANA,
                BLItems.BLACK_KATANA,
                BLItems.SPEAR,
                BLItems.MALACHITE_SPEAR,
                BLItems.CARBOWN_BOW,
                BLItems.EMERALD_HELMET,
                BLItems.EMERALD_CHESTPLATE,
                BLItems.EMERALD_LEGGINGS,
                BLItems.EMERALD_BOOTS,
                BLItems.AMETHYST_HELMET,
                BLItems.AMETHYST_CHESTPLATE,
                BLItems.AMETHYST_LEGGINGS,
                BLItems.AMETHYST_BOOTS,
                BLItems.SAPPHIRE_HELMET,
                BLItems.SAPPHIRE_CHESTPLATE,
                BLItems.SAPPHIRE_LEGGINGS,
                BLItems.SAPPHIRE_BOOTS,
                BLItems.TOPAZ_HELMET,
                BLItems.TOPAZ_CHESTPLATE,
                BLItems.TOPAZ_LEGGINGS,
                BLItems.TOPAZ_BOOTS,
                BLItems.PEARL_HELMET,
                BLItems.PEARL_CHESTPLATE,
                BLItems.PEARL_LEGGINGS,
                BLItems.PEARL_BOOTS,
                BLItems.RUBY_HELMET,
                BLItems.RUBY_CHESTPLATE,
                BLItems.RUBY_LEGGINGS,
                BLItems.RUBY_BOOTS,
                BLItems.MALACHITE_HELMET,
                BLItems.MALACHITE_CHESTPLATE,
                BLItems.MALACHITE_LEGGINGS,
                BLItems.MALACHITE_BOOTS,
                BLItems.ONICE_HELMET,
                BLItems.ONICE_CHESTPLATE,
                BLItems.ONICE_LEGGINGS,
                BLItems.ONICE_BOOTS
        );

        addPreEnchantedItems(
                BLItems.BLAZERITE_HELMET,
                BLItems.BLAZERITE_CHESTPLATE,
                BLItems.BLAZERITE_LEGGINGS,
                BLItems.BLAZERITE_BOOTS,
                BLItems.GYULIANITE_HELMET,
                BLItems.GYULIANITE_CHESTPLATE,
                BLItems.GYULIANITE_LEGGINGS,
                BLItems.GYULIANITE_BOOTS
        );

        HCTabs.addItems(COMBAT,
                BLItems.EMERALD_HORSE_ARMOR,
                BLItems.SAPPHIRE_HORSE_ARMOR,
                BLItems.RUBY_HORSE_ARMOR,
                BLItems.TOPAZ_HORSE_ARMOR,
                BLItems.MALACHITE_HORSE_ARMOR
        );

        HCTabs.addItems(FOOD_AND_DRINK,
                BLItems.HOSOMAKI,
                BLItems.NIGIRI,
                BLItems.SASHIMI
        );

        HCTabs.addItems(INGREDIENTS,
                BLItems.RAW_URANIUM,
                BLItems.SAPPHIRE,
                BLItems.TOPAZ,
                BLItems.PEARL,
                BLItems.RUBY,
                BLItems.MALACHITE,
                BLItems.ONICE,
                BLItems.BLAZERITE,
                BLItems.GYULIANITE,
                BLItems.URANIUM_NUGGET,
                BLItems.URANIUM_INGOT,
                BLItems.CARBON
        );
    }

    /**
     * Add the {@link Item modded Paintings} to the Creative Inventory
     */
    private static void addPaintings() {
        HCTabs.removeItemsByCondition(ItemGroups.FUNCTIONAL, BLTabs::isModdedPainting);
        HCTabs.modifyItems(FUNCTIONAL, entries ->
                RegistryHelper.getRegistry(entries.getContext().lookup(), RegistryKeys.PAINTING_VARIANT)
                        .streamEntries()
                        .filter((registryEntry) -> registryEntry.value().assetId().getNamespace().equalsIgnoreCase(BlazersMod.MOD_ID))
                        .filter((registryEntry) -> registryEntry.isIn(PaintingVariantTags.PLACEABLE))
                        .sorted(Comparator.comparing(RegistryEntry::value, Comparator.comparingInt(PaintingVariant::getArea).thenComparing(PaintingVariant::width)))
                        .forEach(paintingVariantReference -> {
                            final ItemStack itemStack = new ItemStack(Items.PAINTING);
                            itemStack.set(DataComponentTypes.PAINTING_VARIANT, paintingVariantReference);
                            entries.add(itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
                        }));
    }

    /**
     * Add some {@link IPreEnchantedItem Pre Enchanted Items} to the Creative Inventory
     *
     * @param items The {@link ItemConvertible Pre Enchanted Items to add}
     */
    private static void addPreEnchantedItems(final ItemConvertible... items) {
        if(items != null && items.length > 0) {
            HCTabs.modifyItems(COMBAT, entries -> {
                final ItemGroup.DisplayContext context = entries.getContext();
                Arrays.asList(items).forEach(item -> entries.add(((IPreEnchantedItem)item).getItemStack(item.asItem(), context.lookup())));
            });
        }
    }

    /**
     * Add the {@link GoatHornItem modded Goat Horns} to the Creative Inventory
     */
    private static void addGoatHorns() {
        HCTabs.removeItemsByCondition(ItemGroups.TOOLS, BLTabs::isModdedGoatHorn);
        HCTabs.modifyItems(TOOLS, entries ->
                RegistryHelper.getValuesFromTag(entries.getContext().lookup(), RegistryKeys.INSTRUMENT, InstrumentTags.GOAT_HORNS).ifPresent(instruments ->
                        instruments.stream().map(instrument -> GoatHornItem.getStackForInstrument(Items.GOAT_HORN, instrument))
                                .filter(BLTabs::isModdedGoatHorn)
                                .forEach(itemStack -> entries.add(itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS))
        ));
    }

    /**
     * Add the {@link CopperHornItem Copper Horns} to the Creative Inventory
     */
    private static void addCopperHorns() {
        HCTabs.modifyItems(TOOLS, entries ->
                RegistryHelper.getValuesFromTag(entries.getContext().lookup(), RegistryKeys.INSTRUMENT, BLTags.Instruments.MELODY_COPPER_HORNS).ifPresent(instruments ->
                        instruments.stream().map(instrument -> CopperHornItem.getStackForInstrument(BLItems.COPPER_HORN, instrument))
                                .forEach(itemStack -> entries.add(itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS))
        ));
    }

    /**
     * Check if a {@link GoatHornItem Goat Horn} is a modded one
     *
     * @param itemStack The {@link ItemStack Item Stack to check}
     * @return {@link Boolean True} if is a modded {@link GoatHornItem Goat Horn}
     */
    private static boolean isModdedGoatHorn(final ItemStack itemStack) {
        final InstrumentComponent instrument = itemStack.get(DataComponentTypes.INSTRUMENT);
        if(instrument != null) {
            return instrument.instrument().getKey().map(key -> key.getValue().getNamespace().equalsIgnoreCase(BlazersMod.MOD_ID)).orElse(false);
        }
        return false;
    }

    /**
     * Check if a {@link ItemStack Painting} is a modded one
     *
     * @param itemStack The {@link ItemStack Item Stack to check}
     * @return {@link Boolean True} if is a modded {@link ItemStack Painting}
     */
    private static boolean isModdedPainting(final ItemStack itemStack) {
        final RegistryEntry<PaintingVariant> paintingVariant = itemStack.get(DataComponentTypes.PAINTING_VARIANT);
        if(paintingVariant != null) {
            return paintingVariant.getKey().map(paintingVariantKey -> paintingVariantKey.getValue().getNamespace().equalsIgnoreCase(BlazersMod.MOD_ID)).orElse(false);
        }
        return false;
    }

}