package org.blazers.core;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import org.blazers.BlazersMod;
import org.blazers.item.*;
import org.hendrix.helper.FoodHelper;
import org.hendrix.helper.ItemHelper;
import org.hendrix.helper.ResourceHelper;
import org.hendrix.registry.HCItems;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Items

    //#region Materials

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

    //#endregion

    //#region Food

    public static final Item HOSOMAKI = HCItems.registerFood("hosomaki", FoodHelper.food(2, 0.1F));
    public static final Item NIGIRI = HCItems.registerFood("nigiri", FoodHelper.food(5, 0.6F));
    public static final Item SASHIMI = HCItems.registerFood("sashimi", FoodHelper.food(6, 0.8F));

    //#endregion

    //#region Weapons

    public static final Item EMERALD_SWORD = HCItems.registerSword("emerald_sword", BLToolMaterials.EMERALD);
    public static final Item AMETHYST_SWORD = HCItems.registerSword("amethyst_sword", BLToolMaterials.AMETHYST);
    public static final Item SAPPHIRE_SWORD = HCItems.registerSword("sapphire_sword", BLToolMaterials.SAPPHIRE);
    public static final Item TOPAZ_HAMMER = HCItems.registerSword("topaz_hammer", BLToolMaterials.TOPAZ, 5, -3.0F);
    public static final Item PEARL_SWORD = HCItems.registerSword("pearl_sword", BLToolMaterials.PEARL);
    public static final Item RUBY_SWORD = HCItems.registerSword("ruby_sword", BLToolMaterials.RUBY);
    public static final Item ONICE_SICKLE = HCItems.registerSword("onice_sickle", BLToolMaterials.ONICE);
    public static final Item BLAZERITE_SWORD = registerPreEnchantedSword("blazerite_sword", BLToolMaterials.BLAZERITE, Enchantments.FIRE_ASPECT);
    public static final Item GYULIANITE_SWORD = registerPreEnchantedSword("gyulianite_sword", BLToolMaterials.GYULIANITE, Enchantments.KNOCKBACK);

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

    public static final Item SPEAR = registerSpear("spear", BLToolMaterials.FLINT, 1);
    public static final Item MALACHITE_SPEAR = registerSpear("malachite_spear", BLToolMaterials.MALACHITE, 3);

    public static final Item CARBOWN_BOW = HCItems.registerItem("carbon_bow", Suppliers.memoize(() -> new CarbonBowItem(ItemHelper.settings("carbon_bow").maxDamage(384))));

    //#endregion

    //#region Tools

    public static final Item EMERALD_SHOVEL = HCItems.registerShovel("emerald_shovel", BLToolMaterials.EMERALD);
    public static final Item EMERALD_PICKAXE = HCItems.registerPickaxe("emerald_pickaxe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_AXE = HCItems.registerAxe("emerald_axe", BLToolMaterials.EMERALD, 5.0F, -3.0F);
    public static final Item EMERALD_HOE = HCItems.registerHoe("emerald_hoe", BLToolMaterials.EMERALD, -3.0F, 0F);
    public static final Item SAPPHIRE_SHOVEL = HCItems.registerShovel("sapphire_shovel", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_PICKAXE = HCItems.registerPickaxe("sapphire_pickaxe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_AXE = HCItems.registerAxe("sapphire_axe", BLToolMaterials.SAPPHIRE, 5.0F, -3.0F);
    public static final Item SAPPHIRE_HOE = HCItems.registerHoe("sapphire_hoe", BLToolMaterials.SAPPHIRE, -3.0F, 0F);
    public static final Item RUBY_SHOVEL = HCItems.registerShovel("ruby_shovel", BLToolMaterials.RUBY);
    public static final Item RUBY_PICKAXE = HCItems.registerPickaxe("ruby_pickaxe", BLToolMaterials.RUBY);
    public static final Item RUBY_AXE = HCItems.registerAxe("ruby_axe", BLToolMaterials.RUBY, 5.0F, -3.0F);
    public static final Item RUBY_HOE = HCItems.registerHoe("ruby_hoe", BLToolMaterials.RUBY, -3.0F, 0F);
    public static final Item ONICE_SHOVEL = HCItems.registerShovel("onice_shovel", BLToolMaterials.ONICE);
    public static final Item ONICE_PICKAXE = HCItems.registerPickaxe("onice_pickaxe", BLToolMaterials.ONICE);
    public static final Item ONICE_AXE = HCItems.registerAxe("onice_axe", BLToolMaterials.ONICE, 5.5F, -3.0F);
    public static final Item ONICE_HOE = HCItems.registerHoe("onice_hoe", BLToolMaterials.ONICE, -2, -0.5F);

    //#endregion

    //#region Armor

    public static final Item EMERALD_HELMET = HCItems.registerHelmet("emerald_helmet", BLArmorMaterials.EMERALD);
    public static final Item EMERALD_CHESTPLATE = HCItems.registerChestplate("emerald_chestplate", BLArmorMaterials.EMERALD);
    public static final Item EMERALD_LEGGINGS = HCItems.registerLeggings("emerald_leggings", BLArmorMaterials.EMERALD);
    public static final Item EMERALD_BOOTS = HCItems.registerBoots("emerald_boots", BLArmorMaterials.EMERALD);
    public static final Item AMETHYST_HELMET = HCItems.registerHelmet("amethyst_helmet", BLArmorMaterials.AMETHYST);
    public static final Item AMETHYST_CHESTPLATE = HCItems.registerChestplate("amethyst_chestplate", BLArmorMaterials.AMETHYST);
    public static final Item AMETHYST_LEGGINGS = HCItems.registerLeggings("amethyst_leggings", BLArmorMaterials.AMETHYST);
    public static final Item AMETHYST_BOOTS = HCItems.registerBoots("amethyst_boots", BLArmorMaterials.AMETHYST);
    public static final Item SAPPHIRE_HELMET = HCItems.registerHelmet("sapphire_helmet", BLArmorMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_CHESTPLATE = HCItems.registerChestplate("sapphire_chestplate", BLArmorMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_LEGGINGS = HCItems.registerLeggings("sapphire_leggings", BLArmorMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_BOOTS = HCItems.registerBoots("sapphire_boots", BLArmorMaterials.SAPPHIRE);
    public static final Item TOPAZ_HELMET = HCItems.registerHelmet("topaz_helmet", BLArmorMaterials.TOPAZ);
    public static final Item TOPAZ_CHESTPLATE = HCItems.registerChestplate("topaz_chestplate", BLArmorMaterials.TOPAZ);
    public static final Item TOPAZ_LEGGINGS = HCItems.registerLeggings("topaz_leggings", BLArmorMaterials.TOPAZ);
    public static final Item TOPAZ_BOOTS = HCItems.registerBoots("topaz_boots", BLArmorMaterials.TOPAZ);
    public static final Item PEARL_HELMET = HCItems.registerHelmet("pearl_helmet", BLArmorMaterials.PEARL);
    public static final Item PEARL_CHESTPLATE = HCItems.registerChestplate("pearl_chestplate", BLArmorMaterials.PEARL);
    public static final Item PEARL_LEGGINGS = HCItems.registerLeggings("pearl_leggings", BLArmorMaterials.PEARL);
    public static final Item PEARL_BOOTS = HCItems.registerBoots("pearl_boots", BLArmorMaterials.PEARL);
    public static final Item RUBY_HELMET = HCItems.registerHelmet("ruby_helmet", BLArmorMaterials.RUBY);
    public static final Item RUBY_CHESTPLATE = HCItems.registerChestplate("ruby_chestplate", BLArmorMaterials.RUBY);
    public static final Item RUBY_LEGGINGS = HCItems.registerLeggings("ruby_leggings", BLArmorMaterials.RUBY);
    public static final Item RUBY_BOOTS = HCItems.registerBoots("ruby_boots", BLArmorMaterials.RUBY);
    public static final Item MALACHITE_HELMET = HCItems.registerHelmet("malachite_helmet", BLArmorMaterials.MALACHITE);
    public static final Item MALACHITE_CHESTPLATE = HCItems.registerChestplate("malachite_chestplate", BLArmorMaterials.MALACHITE);
    public static final Item MALACHITE_LEGGINGS = HCItems.registerLeggings("malachite_leggings", BLArmorMaterials.MALACHITE);
    public static final Item MALACHITE_BOOTS = HCItems.registerBoots("malachite_boots", BLArmorMaterials.MALACHITE);
    public static final Item ONICE_HELMET = HCItems.registerHelmet("onice_helmet", BLArmorMaterials.ONICE);
    public static final Item ONICE_CHESTPLATE = HCItems.registerChestplate("onice_chestplate", BLArmorMaterials.ONICE);
    public static final Item ONICE_LEGGINGS = HCItems.registerLeggings("onice_leggings", BLArmorMaterials.ONICE);
    public static final Item ONICE_BOOTS = HCItems.registerBoots("onice_boots", BLArmorMaterials.ONICE);
    public static final Item BLAZERITE_HELMET = registerPreEnchantedArmor("blazerite_helmet", BLArmorMaterials.BLAZERITE, EquipmentType.HELMET, Enchantments.FIRE_PROTECTION);
    public static final Item BLAZERITE_CHESTPLATE = registerPreEnchantedArmor("blazerite_chestplate", BLArmorMaterials.BLAZERITE, EquipmentType.CHESTPLATE, Enchantments.FIRE_PROTECTION);
    public static final Item BLAZERITE_LEGGINGS = registerPreEnchantedArmor("blazerite_leggings", BLArmorMaterials.BLAZERITE, EquipmentType.LEGGINGS, Enchantments.FIRE_PROTECTION);
    public static final Item BLAZERITE_BOOTS = registerPreEnchantedArmor("blazerite_boots", BLArmorMaterials.BLAZERITE, EquipmentType.BOOTS, Enchantments.FIRE_PROTECTION);
    public static final Item GYULIANITE_HELMET = registerPreEnchantedArmor("gyulianite_helmet", BLArmorMaterials.GYULIANITE, EquipmentType.HELMET, Enchantments.PROJECTILE_PROTECTION);
    public static final Item GYULIANITE_CHESTPLATE = registerPreEnchantedArmor("gyulianite_chestplate", BLArmorMaterials.GYULIANITE, EquipmentType.CHESTPLATE, Enchantments.PROJECTILE_PROTECTION);
    public static final Item GYULIANITE_LEGGINGS = registerPreEnchantedArmor("gyulianite_leggings", BLArmorMaterials.GYULIANITE, EquipmentType.LEGGINGS, Enchantments.PROJECTILE_PROTECTION);
    public static final Item GYULIANITE_BOOTS = registerPreEnchantedArmor("gyulianite_boots", BLArmorMaterials.GYULIANITE, EquipmentType.BOOTS, Enchantments.PROJECTILE_PROTECTION);

    public static final Item EMERALD_HORSE_ARMOR = HCItems.registerHorseArmor("emerald_horse_armor", BLArmorMaterials.EMERALD);
    public static final Item SAPPHIRE_HORSE_ARMOR = HCItems.registerHorseArmor("sapphire_horse_armor", BLArmorMaterials.SAPPHIRE);
    public static final Item RUBY_HORSE_ARMOR = HCItems.registerHorseArmor("ruby_horse_armor", BLArmorMaterials.RUBY);
    public static final Item TOPAZ_HORSE_ARMOR = HCItems.registerHorseArmor("topaz_horse_armor", BLArmorMaterials.TOPAZ);
    public static final Item MALACHITE_HORSE_ARMOR = HCItems.registerHorseArmor("malachite_horse_armor", BLArmorMaterials.MALACHITE);

    //#endregion

    //#endregion

    /**
     * Register a {@link SwordItem Katana}
     *
     * @param color The {@link DyeColor Katana Color}
     * @return The {@link Item registered Item}
     */
    private static Item registerKatana(final DyeColor color) {
        return HCItems.registerSword(ResourceHelper.suffixedColorName(color, "_") + "katana", BLToolMaterials.CARBON, 3.0F, 0F);
    }

    /**
     * Register a {@link SpearItem Spear Item}
     *
     * @param name The {@link String Item name}
     * @param material The {@link ToolMaterial Item material}
     * @param attackDamage The {@link Integer Spear attack damage}
     * @return The {@link Item registered Item}
     */
    private static Item registerSpear(final String name, final ToolMaterial material, final int attackDamage) {
        return HCItems.registerItem(name, Suppliers.memoize(() -> new SpearItem(material, attackDamage, ItemHelper.settings(name).maxDamage(material.durability()))));
    }

    /**
     * Register a {@link IPreEnchantedItem pre-enchanted Sword}
     *
     * @param name The {@link String Item name}
     * @param material The {@link ToolMaterial Sword Material}
     * @param enchantment The {@link RegistryKey<Enchantment> Sword enchantment}
     * @return The {@link Item registered Item}
     */
    private static Item registerPreEnchantedSword(final String name, final ToolMaterial material, final RegistryKey<Enchantment> enchantment) {
        return HCItems.registerItem(name, Suppliers.memoize(() -> new PreEnchantedSwordItem(name, material, Pair.of(enchantment, 10))));
    }

    /**
     * Register a {@link IPreEnchantedItem pre-enchanted Armor}
     *
     * @param name The {@link String Item name}
     * @param material The {@link ArmorMaterial Armor Material}
     * @param equipmentType The {@link EquipmentType Armor equipment type}
     * @param enchantment The {@link RegistryKey<Enchantment> Armor enchantment}
     * @return The {@link Item registered Item}
     */
    private static Item registerPreEnchantedArmor(final String name, final ArmorMaterial material, final EquipmentType equipmentType, final RegistryKey<Enchantment> enchantment) {
        return HCItems.registerItem(name, Suppliers.memoize(() -> new PreEnchantedArmorItem(name, material, equipmentType, Pair.of(enchantment, 4))));
    }

    /**
     * Register all {@link Item Items}
     */
    public static void register() {

    }

}