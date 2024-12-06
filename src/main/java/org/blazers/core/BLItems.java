package org.blazers.core;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import org.blazers.BlazersMod;
import org.hendrix.helper.FoodHelper;
import org.hendrix.registry.HCItems;

import java.util.Locale;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Items

    public static final Item RAW_URANIUM = HCItems.registerItem("raw_uranium");
    public static final Item SAPPHIRE = HCItems.registerItem("sapphire");
    public static final Item TOPAZ = HCItems.registerItem("topaz");
    public static final Item PEARL = HCItems.registerItem("pearl");
    public static final Item RUBY = HCItems.registerItem("ruby");
    public static final Item MALACHITE = HCItems.registerItem("malachite");
    public static final Item ONICE = HCItems.registerItem("onice");
    public static final Item BLAZERITE = HCItems.registerItem("blazerite", Rarity.RARE);
    public static final Item GYULIANITE = HCItems.registerItem("gyulianite", Rarity.RARE);
    public static final Item URANIUM_NUGGET = HCItems.registerItem("uranium_nugget");
    public static final Item URANIUM_INGOT = HCItems.registerItem("uranium_ingot");
    public static final Item CARBON = HCItems.registerItem("carbon");

    public static final Item HOSOMAKI = HCItems.registerFood("hosomaki", FoodHelper.food(2, 0.1F));
    public static final Item NIGIRI = HCItems.registerFood("nigiri", FoodHelper.food(5, 0.6F));
    public static final Item SASHIMI = HCItems.registerFood("sashimi", FoodHelper.food(6, 0.8F));

    public static final Item EMERALD_SWORD = HCItems.registerSword("emerald_sword", BLToolMaterials.EMERALD);
    public static final Item EMERALD_SHOVEL = HCItems.registerShovel("emerald_shovel", BLToolMaterials.EMERALD);
    public static final Item EMERALD_PICKAXE = HCItems.registerPickaxe("emerald_pickaxe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_AXE = HCItems.registerAxe("emerald_axe", BLToolMaterials.EMERALD, 5.0F, -3.0F);
    public static final Item EMERALD_HOE = HCItems.registerHoe("emerald_hoe", BLToolMaterials.EMERALD, -3.0F, 0F);
    public static final Item AMETHYST_SWORD = HCItems.registerSword("amethyst_sword", BLToolMaterials.AMETHYST);
    public static final Item SAPPHIRE_SWORD = HCItems.registerSword("sapphire_sword", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_SHOVEL = HCItems.registerShovel("sapphire_shovel", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_PICKAXE = HCItems.registerPickaxe("sapphire_pickaxe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_AXE = HCItems.registerAxe("sapphire_axe", BLToolMaterials.SAPPHIRE, 5.0F, -3.0F);
    public static final Item SAPPHIRE_HOE = HCItems.registerHoe("sapphire_hoe", BLToolMaterials.SAPPHIRE, -3.0F, 0F);
    public static final Item TOPAZ_HAMMER = HCItems.registerSword("topaz_hammer", BLToolMaterials.TOPAZ, 5, -3.0F);
    public static final Item PEARL_SWORD = HCItems.registerSword("pearl_sword", BLToolMaterials.PEARL);
    public static final Item RUBY_SWORD = HCItems.registerSword("ruby_sword", BLToolMaterials.RUBY);
    public static final Item RUBY_SHOVEL = HCItems.registerShovel("ruby_shovel", BLToolMaterials.RUBY);
    public static final Item RUBY_PICKAXE = HCItems.registerPickaxe("ruby_pickaxe", BLToolMaterials.RUBY);
    public static final Item RUBY_AXE = HCItems.registerAxe("ruby_axe", BLToolMaterials.RUBY, 5.0F, -3.0F);
    public static final Item RUBY_HOE = HCItems.registerHoe("ruby_hoe", BLToolMaterials.RUBY, -3.0F, 0F);
    public static final Item ONICE_SICKLE = HCItems.registerSword("onice_sickle", BLToolMaterials.ONICE);
    public static final Item ONICE_SHOVEL = HCItems.registerShovel("onice_shovel", BLToolMaterials.ONICE);
    public static final Item ONICE_PICKAXE = HCItems.registerPickaxe("onice_pickaxe", BLToolMaterials.ONICE);
    public static final Item ONICE_AXE = HCItems.registerAxe("onice_axe", BLToolMaterials.ONICE, 5.5F, -3.0F);
    public static final Item ONICE_HOE = HCItems.registerHoe("onice_hoe", BLToolMaterials.ONICE, -2, -0.5F);

    public static final Item KATANA = registerKatana(null);
    public static final Item WHITE_KATANA = registerKatana(DyeColor.WHITE);
    public static final Item ORANGE_KATANA = registerKatana(DyeColor.ORANGE);
    public static final Item MAGENTA_KATANA = registerKatana(DyeColor.MAGENTA);
    public static final Item LIGHT_BLUE_KATANA = registerKatana(DyeColor.LIGHT_BLUE);
    public static final Item YELLOW_KATANA = registerKatana(DyeColor.YELLOW);
    public static final Item LIME_KATANA = registerKatana(DyeColor.LIME);
    public static final Item PINK_KATANA = registerKatana(DyeColor.PINK);
    public static final Item GRAY_KATANA = registerKatana(DyeColor.GRAY);
    public static final Item LIGHT_GRAY_KATANA = registerKatana(DyeColor.LIGHT_GRAY);
    public static final Item CYAN_KATANA = registerKatana(DyeColor.CYAN);
    public static final Item PURPLE_KATANA = registerKatana(DyeColor.PURPLE);
    public static final Item BLUE_KATANA = registerKatana(DyeColor.BLUE);
    public static final Item BROWN_KATANA = registerKatana(DyeColor.BROWN);
    public static final Item GREEN_KATANA = registerKatana(DyeColor.GREEN);
    public static final Item RED_KATANA = registerKatana(DyeColor.RED);
    public static final Item BLACK_KATANA = registerKatana(DyeColor.BLACK);

    //#endregion

    /**
     * Register a {@link SwordItem Katana}
     *
     * @param color The {@link DyeColor Katana Color}
     * @return The {@link Item registered Item}
     */
    private static Item registerKatana(final DyeColor color) {
        final String name = (color != null ? (color.name().toLowerCase(Locale.ROOT) + "_") : "") + "katana";
        return HCItems.registerSword(name, BLToolMaterials.CARBON, 3.0F, 0F);
    }

    /**
     * Register all {@link Item Items}
     */
    public static void register() {

    }

}