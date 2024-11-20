package org.blazers.core;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.hendrix.forge.helper.ItemHelper;
import org.hendrix.forge.helper.PropertyHelper;
import org.hendrix.forge.registries.HCRegistries;

import java.util.Locale;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Items

    //#region Materials

    public static final RegistryObject<Item> RAW_URANIUM = ItemHelper.registerSimpleItem("raw_uranium");
    public static final RegistryObject<Item> SAPPHIRE = ItemHelper.registerSimpleItem("sapphire");
    public static final RegistryObject<Item> RUBY = ItemHelper.registerSimpleItem("ruby");
    public static final RegistryObject<Item> TOPAZ = ItemHelper.registerSimpleItem("topaz");
    public static final RegistryObject<Item> PEARL = ItemHelper.registerSimpleItem("pearl");
    public static final RegistryObject<Item> MALACHITE = ItemHelper.registerSimpleItem("malachite");
    public static final RegistryObject<Item> ONICE = ItemHelper.registerSimpleItem("onice");
    public static final RegistryObject<Item> BLAZERITE = ItemHelper.registerRareItem("blazerite", Rarity.RARE);
    public static final RegistryObject<Item> GYULIANITE = ItemHelper.registerRareItem("gyulianite", Rarity.RARE);
    public static final RegistryObject<Item> URANIUM_NUGGET = ItemHelper.registerSimpleItem("uranium_nugget");
    public static final RegistryObject<Item> URANIUM_INGOT = ItemHelper.registerSimpleItem("uranium_ingot");
    public static final RegistryObject<Item> CARBON = ItemHelper.registerSimpleItem("carbon");

    //#endregion

    //#region Food

    public static final RegistryObject<Item> HOSOMAKI = ItemHelper.registerFood("hosomaki", PropertyHelper.food(2, 0.1F));
    public static final RegistryObject<Item> NIGIRI = ItemHelper.registerFood("nigiri", PropertyHelper.food(5, 0.6F));
    public static final RegistryObject<Item> SASHIMI = ItemHelper.registerFood("sashimi", PropertyHelper.food(6, 0.8F));

    //#endregion

    //#region Tools and Swords

    public static final RegistryObject<Item> SAPPHIRE_SWORD = ItemHelper.registerSword("sapphire_sword", BLToolMaterials.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ItemHelper.registerShovel("sapphire_shovel", BLToolMaterials.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ItemHelper.registerPickaxe("sapphire_pickaxe", BLToolMaterials.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_AXE = ItemHelper.registerAxe("sapphire_axe", BLToolMaterials.SAPPHIRE, 5.0F, -3.0F);
    public static final RegistryObject<Item> SAPPHIRE_HOE = ItemHelper.registerHoe("sapphire_hoe", BLToolMaterials.SAPPHIRE, -3.0F, 0.0F);

    public static final RegistryObject<Item> RUBY_SWORD = ItemHelper.registerSword("ruby_sword", BLToolMaterials.RUBY);
    public static final RegistryObject<Item> RUBY_SHOVEL = ItemHelper.registerShovel("ruby_shovel", BLToolMaterials.RUBY);
    public static final RegistryObject<Item> RUBY_PICKAXE = ItemHelper.registerPickaxe("ruby_pickaxe", BLToolMaterials.RUBY);
    public static final RegistryObject<Item> RUBY_AXE = ItemHelper.registerAxe("ruby_axe", BLToolMaterials.RUBY, 5.0F, -3.0F);
    public static final RegistryObject<Item> RUBY_HOE = ItemHelper.registerHoe("ruby_hoe", BLToolMaterials.RUBY, -3.0F, 0.0F);

    public static final RegistryObject<Item> EMERALD_SWORD = ItemHelper.registerSword("emerald_sword", BLToolMaterials.EMERALD);
    public static final RegistryObject<Item> EMERALD_SHOVEL = ItemHelper.registerShovel("emerald_shovel", BLToolMaterials.EMERALD);
    public static final RegistryObject<Item> EMERALD_PICKAXE = ItemHelper.registerPickaxe("emerald_pickaxe", BLToolMaterials.EMERALD);
    public static final RegistryObject<Item> EMERALD_AXE = ItemHelper.registerAxe("emerald_axe", BLToolMaterials.EMERALD, 5.0F, -3.0F);
    public static final RegistryObject<Item> EMERALD_HOE = ItemHelper.registerHoe("emerald_hoe", BLToolMaterials.EMERALD, -3.0F, 0.0F);

    public static final RegistryObject<Item> ONICE_SICKLE = ItemHelper.registerSword("onice_sickle", BLToolMaterials.ONICE);
    public static final RegistryObject<Item> ONICE_SHOVEL = ItemHelper.registerShovel("onice_shovel", BLToolMaterials.ONICE);
    public static final RegistryObject<Item> ONICE_PICKAXE = ItemHelper.registerPickaxe("onice_pickaxe", BLToolMaterials.ONICE);
    public static final RegistryObject<Item> ONICE_AXE = ItemHelper.registerAxe("onice_axe", BLToolMaterials.ONICE, 5.5F, -3.0F);
    public static final RegistryObject<Item> ONICE_HOE = ItemHelper.registerHoe("onice_hoe", BLToolMaterials.ONICE, -2.0F, -0.5F);

    public static final RegistryObject<Item> AMETHYST_SWORD = ItemHelper.registerSword("amethyst_sword", BLToolMaterials.AMETHYST);

    public static final RegistryObject<Item> PEARL_SWORD = ItemHelper.registerSword("pearl_sword", BLToolMaterials.PEARL);

    public static final RegistryObject<Item> TOPAZ_HAMMER = ItemHelper.registerSword("topaz_hammer", BLToolMaterials.TOPAZ, 5, -3.0F);

    public static final RegistryObject<Item> KATANA = registerKatana(null);
    public static final RegistryObject<Item> WHITE_KATANA = registerKatana(DyeColor.WHITE);
    public static final RegistryObject<Item> ORANGE_KATANA = registerKatana(DyeColor.ORANGE);
    public static final RegistryObject<Item> MAGENTA_KATANA = registerKatana(DyeColor.MAGENTA);
    public static final RegistryObject<Item> LIGHT_BLUE_KATANA = registerKatana(DyeColor.LIGHT_BLUE);
    public static final RegistryObject<Item> YELLOW_KATANA = registerKatana(DyeColor.YELLOW);
    public static final RegistryObject<Item> LIME_KATANA = registerKatana(DyeColor.LIME);
    public static final RegistryObject<Item> PINK_KATANA = registerKatana(DyeColor.PINK);
    public static final RegistryObject<Item> GRAY_KATANA = registerKatana(DyeColor.GRAY);
    public static final RegistryObject<Item> LIGHT_GRAY_KATANA = registerKatana(DyeColor.LIGHT_GRAY);
    public static final RegistryObject<Item> CYAN_KATANA = registerKatana(DyeColor.CYAN);
    public static final RegistryObject<Item> PURPLE_KATANA = registerKatana(DyeColor.PURPLE);
    public static final RegistryObject<Item> BLUE_KATANA = registerKatana(DyeColor.BLUE);
    public static final RegistryObject<Item> BROWN_KATANA = registerKatana(DyeColor.BROWN);
    public static final RegistryObject<Item> GREEN_KATANA = registerKatana(DyeColor.GREEN);
    public static final RegistryObject<Item> RED_KATANA = registerKatana(DyeColor.RED);
    public static final RegistryObject<Item> BLACK_KATANA = registerKatana(DyeColor.BLACK);

    //#endregion

    //#endregion

    /**
     * Register a {@link SwordItem Katana}
     *
     * @param color THe {@link DyeColor Katana Color}
     * @return The {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerKatana(final DyeColor color) {
        return ItemHelper.registerSword((color != null ? (color.getName().toLowerCase(Locale.ROOT) + "_") : "") + "katana", BLToolMaterials.CARBON, 3.0F, 0.0F);
    }

    /**
     * Register all {@link Item Items}
     *
     * @param eventBus The {@link IEventBus Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        HCRegistries.ITEMS.register(eventBus);
    }

}