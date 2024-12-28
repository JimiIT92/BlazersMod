package org.blazers.core;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import org.blazers.BlazersMod;
import org.blazers.item.IPreEnchantedItem;
import org.hendrix.registry.HCTabs;

import java.util.Arrays;

/**
 * {@link BlazersMod Blazers Mod} {@link ItemGroup Item Groups}
 */
public final class BLTabs {

    //#region Item Groups

    //public static ItemGroup BUILDING_BLOCKS = HCTabs.tab("building_blocks", Suppliers.memoize(() -> BLBlocks.RUBY_BLOCK));
    //public static ItemGroup COLORED_BLOCKS = HCTabs.tab("colored_blocks", Suppliers.memoize(() -> BLBlocks.YELLOW_CONCRETE_STAIRS));
    //public static ItemGroup NATURAL = HCTabs.tab("natural", Suppliers.memoize(() -> BLBlocks.HOLLOW_BIRCH_LOG));
    //public static ItemGroup FUNCTIONAL = HCTabs.tab("functional", Suppliers.memoize(() -> Items.PAINTING));
    //public static ItemGroup REDSTONE = HCTabs.tab("redstone", Suppliers.memoize(() -> BLBlocks.COPPER_BUTTON));
    public static ItemGroup TOOLS = HCTabs.tab("tools", Suppliers.memoize(() -> BLItems.EMERALD_PICKAXE));
    public static ItemGroup COMBAT = HCTabs.tab("combat", Suppliers.memoize(() -> BLItems.KATANA));
    public static ItemGroup FOOD_AND_DRINK = HCTabs.tab("food_and_drink", Suppliers.memoize(() -> BLItems.SASHIMI));
    public static ItemGroup INGREDIENTS = HCTabs.tab("ingredients", Suppliers.memoize(() -> BLItems.RUBY));
    //public static ItemGroup SPAWN_EGGS = HCTabs.tab("spawn_eggs", Suppliers.memoize(() -> BLItems.COPPER_GOLEM_SPAWN_EGG));

    //#endregion

    /**
     * Register all {@link ItemGroup Item Groups}
     */
    public static void register() {
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
                BLItems.ONICE_HOE
        );

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

    private static void addPreEnchantedItems(final ItemConvertible... items) {
        if(items != null && items.length > 0) {
            HCTabs.tabKey(COMBAT).ifPresent(tabKey -> ItemGroupEvents.modifyEntriesEvent(tabKey).register(entries -> {
                ItemGroup.DisplayContext context = entries.getContext();
                Arrays.asList(items).forEach(item -> entries.add(((IPreEnchantedItem)item).getItemStack(item.asItem(), context.lookup())));
            }));
        }
    }

}