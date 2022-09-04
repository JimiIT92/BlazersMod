package org.blazers.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;
import org.blazers.item.*;

import java.util.AbstractMap;

public final class BLItems {

    public static final Item SAPPHIRE = registerSimpleItem("sapphire", BLTabs.BLTabSortGroups.GEMS);
    public static final Item TOPAZ = registerSimpleItem("topaz", BLTabs.BLTabSortGroups.GEMS);
    public static final Item PEARL = registerSimpleItem("pearl", BLTabs.BLTabSortGroups.GEMS);
    public static final Item RUBY = registerSimpleItem("ruby", BLTabs.BLTabSortGroups.GEMS);
    public static final Item MALACHITE = registerSimpleItem("malachite", BLTabs.BLTabSortGroups.GEMS);
    public static final Item ONICE = registerSimpleItem("onice", BLTabs.BLTabSortGroups.GEMS);
    public static final Item RAW_URANIUM = registerSimpleItem("raw_uranium", BLTabs.BLTabSortGroups.RAW_GEMS_AND_INGOTS);
    public static final Item URANIUM_INGOT = registerSimpleItem("uranium_ingot", BLTabs.BLTabSortGroups.RAW_GEMS_AND_INGOTS);
    public static final Item CARBON = registerSimpleItem("carbon", BLTabs.BLTabSortGroups.MATERIALS);
    public static final Item URANIUM_NUGGET = registerSimpleItem("uranium_nugget", BLTabs.BLTabSortGroups.NUGGETS);
    public static final Item BLAZERITE = registerRareItem("blazerite", BLTabs.BLTabSortGroups.GEMS);
    public static final Item GYULIANITE = registerRareItem("gyulianite", BLTabs.BLTabSortGroups.GEMS);

    public static final Item HOSOMAKI = registerFood("hosomaki", BLFoods.HOSOMAKI);
    public static final Item NIGIRI = registerFood("nigiri", BLFoods.NIGIRI);
    public static final Item SASHIMI = registerFood("sashimi", BLFoods.SASHIMI);

    public static final Item EMERALD_SWORD = registerSword("emerald_sword", BLToolMaterials.EMERALD, BLTabs.BLTabSortGroups.SWORDS);
    public static final Item EMERALD_SHOVEL = registerShovel("emerald_shovel", BLToolMaterials.EMERALD);
    public static final Item EMERALD_PICKAXE = registerPickaxe("emerald_pickaxe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_AXE = registerAxe("emerald_axe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_HOE = registerHoe("emerald_hoe", BLToolMaterials.EMERALD);
    public static final Item AMETHYST_SWORD = registerSword("amethyst_sword", BLToolMaterials.AMETHYST, BLTabs.BLTabSortGroups.SWORDS);
    public static final Item SAPPHIRE_SWORD = registerSword("sapphire_sword", BLToolMaterials.SAPPHIRE, BLTabs.BLTabSortGroups.SWORDS);
    public static final Item SAPPHIRE_SHOVEL = registerShovel("sapphire_shovel", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_PICKAXE = registerPickaxe("sapphire_pickaxe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_AXE = registerAxe("sapphire_axe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_HOE = registerHoe("sapphire_hoe", BLToolMaterials.SAPPHIRE);
    public static final Item TOPAZ_HAMMER = registerSword("topaz_hammer", BLToolMaterials.TOPAZ, 5, -3.0F, BLTabs.BLTabSortGroups.WEAPONS);
    public static final Item PEARL_SWORD = registerSword("pearl_sword", BLToolMaterials.PEARL, BLTabs.BLTabSortGroups.SWORDS);
    public static final Item RUBY_SWORD = registerSword("ruby_sword", BLToolMaterials.RUBY, BLTabs.BLTabSortGroups.SWORDS);
    public static final Item RUBY_SHOVEL = registerShovel("ruby_shovel", BLToolMaterials.RUBY);
    public static final Item RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", BLToolMaterials.RUBY);
    public static final Item RUBY_AXE = registerAxe("ruby_axe", BLToolMaterials.RUBY);
    public static final Item RUBY_HOE = registerHoe("ruby_hoe", BLToolMaterials.RUBY);
    public static final Item ONICE_SHOVEL = registerShovel("onice_shovel", BLToolMaterials.ONICE);
    public static final Item ONICE_PICKAXE = registerPickaxe("onice_pickaxe", BLToolMaterials.ONICE);
    public static final Item ONICE_AXE = registerAxe("onice_axe", BLToolMaterials.ONICE, 5.5F, -3.0F);
    public static final Item ONICE_HOE = registerHoe("onice_hoe", BLToolMaterials.ONICE, -2, -0.5F);
    public static final Item ONICE_SICKLE = registerSword("onice_sickle", BLToolMaterials.ONICE, BLTabs.BLTabSortGroups.WEAPONS);

    public static final Item SPEAR = registerSpearItem("spear", BLToolMaterials.FLINT, 1);
    public static final Item MALACHITE_SPEAR = registerSpearItem("malachite_spear", BLToolMaterials.MALACHITE, 3);

    public static final Item BLAZERITE_SWORD = registerItem("blazerite_sword",
            new PreEnchantedSwordItem(BLToolMaterials.BLAZERITE, Enchantments.FIRE_ASPECT, 10), BLTabs.BLTabSortGroups.SWORDS);
    public static final Item GYULIANITE_SWORD = registerItem("gyulianite_sword",
            new PreEnchantedSwordItem(BLToolMaterials.GYULIANITE, Enchantments.KNOCKBACK, 10), BLTabs.BLTabSortGroups.SWORDS);

    public static final Item KATANA = registerKatana("katana");
    public static final Item WHITE_KATANA = registerKatana("white_katana");
    public static final Item ORANGE_KATANA = registerKatana("orange_katana");
    public static final Item MAGENTA_KATANA = registerKatana("magenta_katana");
    public static final Item LIGHT_BLUE_KATANA = registerKatana("light_blue_katana");
    public static final Item YELLOW_KATANA = registerKatana("yellow_katana");
    public static final Item LIME_KATANA = registerKatana("lime_katana");
    public static final Item PINK_KATANA = registerKatana("pink_katana");
    public static final Item GRAY_KATANA = registerKatana("gray_katana");
    public static final Item LIGHT_GRAY_KATANA = registerKatana("light_gray_katana");
    public static final Item CYAN_KATANA = registerKatana("cyan_katana");
    public static final Item PURPLE_KATANA = registerKatana("purple_katana");
    public static final Item BLUE_KATANA = registerKatana("blue_katana");
    public static final Item BROWN_KATANA = registerKatana("brown_katana");
    public static final Item GREEN_KATANA = registerKatana("green_katana");
    public static final Item RED_KATANA = registerKatana("red_katana");
    public static final Item BLACK_KATANA = registerKatana("black_katana");

    public static final Item EMERALD_HELMET = registerArmorItem("emerald_helmet", BLArmorMaterials.EMERALD, EquipmentSlot.HEAD);
    public static final Item EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", BLArmorMaterials.EMERALD, EquipmentSlot.CHEST);
    public static final Item EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", BLArmorMaterials.EMERALD, EquipmentSlot.LEGS);
    public static final Item EMERALD_BOOTS = registerArmorItem("emerald_boots", BLArmorMaterials.EMERALD, EquipmentSlot.FEET);
    public static final Item AMETHYST_HELMET = registerArmorItem("amethyst_helmet", BLArmorMaterials.AMETHYST, EquipmentSlot.HEAD);
    public static final Item AMETHYST_CHESTPLATE = registerArmorItem("amethyst_chestplate", BLArmorMaterials.AMETHYST, EquipmentSlot.CHEST);
    public static final Item AMETHYST_LEGGINGS = registerArmorItem("amethyst_leggings", BLArmorMaterials.AMETHYST, EquipmentSlot.LEGS);
    public static final Item AMETHYST_BOOTS = registerArmorItem("amethyst_boots", BLArmorMaterials.AMETHYST, EquipmentSlot.FEET);
    public static final Item SAPPHIRE_HELMET = registerArmorItem("sapphire_helmet", BLArmorMaterials.SAPPHIRE, EquipmentSlot.HEAD);
    public static final Item SAPPHIRE_CHESTPLATE = registerArmorItem("sapphire_chestplate", BLArmorMaterials.SAPPHIRE, EquipmentSlot.CHEST);
    public static final Item SAPPHIRE_LEGGINGS = registerArmorItem("sapphire_leggings", BLArmorMaterials.SAPPHIRE, EquipmentSlot.LEGS);
    public static final Item SAPPHIRE_BOOTS = registerArmorItem("sapphire_boots", BLArmorMaterials.SAPPHIRE, EquipmentSlot.FEET);
    public static final Item TOPAZ_HELMET = registerArmorItem("topaz_helmet", BLArmorMaterials.TOPAZ, EquipmentSlot.HEAD);
    public static final Item TOPAZ_CHESTPLATE = registerArmorItem("topaz_chestplate", BLArmorMaterials.TOPAZ, EquipmentSlot.CHEST);
    public static final Item TOPAZ_LEGGINGS = registerArmorItem("topaz_leggings", BLArmorMaterials.TOPAZ, EquipmentSlot.LEGS);
    public static final Item TOPAZ_BOOTS = registerArmorItem("topaz_boots", BLArmorMaterials.TOPAZ, EquipmentSlot.FEET);
    public static final Item PEARL_HELMET = registerArmorItem("pearl_helmet", BLArmorMaterials.PEARL, EquipmentSlot.HEAD);
    public static final Item PEARL_CHESTPLATE = registerArmorItem("pearl_chestplate", BLArmorMaterials.PEARL, EquipmentSlot.CHEST);
    public static final Item PEARL_LEGGINGS = registerArmorItem("pearl_leggings", BLArmorMaterials.PEARL, EquipmentSlot.LEGS);
    public static final Item PEARL_BOOTS = registerArmorItem("pearl_boots", BLArmorMaterials.PEARL, EquipmentSlot.FEET);
    public static final Item RUBY_HELMET = registerArmorItem("ruby_helmet", BLArmorMaterials.RUBY, EquipmentSlot.HEAD);
    public static final Item RUBY_CHESTPLATE = registerArmorItem("ruby_chestplate", BLArmorMaterials.RUBY, EquipmentSlot.CHEST);
    public static final Item RUBY_LEGGINGS = registerArmorItem("ruby_leggings", BLArmorMaterials.RUBY, EquipmentSlot.LEGS);
    public static final Item RUBY_BOOTS = registerArmorItem("ruby_boots", BLArmorMaterials.RUBY, EquipmentSlot.FEET);
    public static final Item MALACHITE_HELMET = registerArmorItem("malachite_helmet", BLArmorMaterials.MALACHITE, EquipmentSlot.HEAD);
    public static final Item MALACHITE_CHESTPLATE = registerArmorItem("malachite_chestplate", BLArmorMaterials.MALACHITE, EquipmentSlot.CHEST);
    public static final Item MALACHITE_LEGGINGS = registerArmorItem("malachite_leggings", BLArmorMaterials.MALACHITE, EquipmentSlot.LEGS);
    public static final Item MALACHITE_BOOTS = registerArmorItem("malachite_boots", BLArmorMaterials.MALACHITE, EquipmentSlot.FEET);
    public static final Item ONICE_HELMET = registerArmorItem("onice_helmet", BLArmorMaterials.ONICE, EquipmentSlot.HEAD);
    public static final Item ONICE_CHESTPLATE = registerArmorItem("onice_chestplate", BLArmorMaterials.ONICE, EquipmentSlot.CHEST);
    public static final Item ONICE_LEGGINGS = registerArmorItem("onice_leggings", BLArmorMaterials.ONICE, EquipmentSlot.LEGS);
    public static final Item ONICE_BOOTS = registerArmorItem("onice_boots", BLArmorMaterials.ONICE, EquipmentSlot.FEET);

    public static final Item BLAZERITE_HELMET = registerItem("blazerite_helmet", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.HEAD, Enchantments.FIRE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item BLAZERITE_CHESTPLATE = registerItem("blazerite_chestplate", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.CHEST, Enchantments.FIRE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item BLAZERITE_LEGGINGS = registerItem("blazerite_leggings", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.LEGS, Enchantments.FIRE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item BLAZERITE_BOOTS = registerItem("blazerite_boots", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.FEET, Enchantments.FIRE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item GYULIANITE_HELMET = registerItem("gyulianite_helmet", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.HEAD, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item GYULIANITE_CHESTPLATE = registerItem("gyulianite_chestplate", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.CHEST, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item GYULIANITE_LEGGINGS = registerItem("gyulianite_leggings", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.LEGS, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);
    public static final Item GYULIANITE_BOOTS = registerItem("gyulianite_boots", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.FEET, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.BLTabSortGroups.ARMORS);

    public static final Item EMERALD_HORSE_ARMOR = registerHorseArmorItem("emerald_horse_armor",13, "emerald");
    public static final Item SAPPHIRE_HORSE_ARMOR = registerHorseArmorItem("sapphire_horse_armor",13, "sapphire");
    public static final Item TOPAZ_HORSE_ARMOR = registerHorseArmorItem("topaz_horse_armor",8, "topaz");
    public static final Item RUBY_HORSE_ARMOR = registerHorseArmorItem("ruby_horse_armor",13, "ruby");
    public static final Item MALACHITE_HORSE_ARMOR = registerHorseArmorItem("malachite_horse_armor",8, "malachite");

    public static final Item CARBON_BOW = registerItem("carbon_bow", new CarbonBowItem(), BLTabs.BLTabSortGroups.BOWS);

    //sapwn eggs

    //copper horn

    public static final Item MUSIC_DISC_SURVIVAL = registerMusicDisc("music_disc_survival", BLSounds.MUSIC_DISC_SURVIVAL, 28);
    public static final Item MUSIC_DISC_ENDERMAN_VS_BLAZE = registerMusicDisc("music_disc_enderman_vs_blaze", BLSounds.MUSIC_DISC_ENDERMAN_VS_BLAZE, 173);

    private static FabricItemSettings createSimpleItemSettings(final ItemGroup tab) {
        return new FabricItemSettings().group(tab);
    }

    private static FabricItemSettings createToolItemSettings() {
        return createSimpleItemSettings(BLTabs.TAB_TOOLS);
    }

    private static FabricItemSettings createCombatItemSettings() {
        return createSimpleItemSettings(BLTabs.TAB_COMBAT);
    }

    private static Item registerFood(String name, FoodComponent foodProperties) {
        return registerItem(name, new Item(createSimpleItemSettings(BLTabs.TAB_FOOD).food(foodProperties)), BLTabs.BLTabSortGroups.FOOD);
    }

    private static Item registerShovel(String name, ToolMaterial toolMaterial) {
        return registerItem(name, new ShovelItem(toolMaterial, 1.5F, -3.0F, createToolItemSettings()), BLTabs.BLTabSortGroups.TOOLS);
    }

    private static Item registerPickaxe(String name, ToolMaterial toolMaterial) {
        return registerItem(name, new BLPickaxeItem(toolMaterial), BLTabs.BLTabSortGroups.TOOLS);
    }

    private static Item registerAxe(String name, ToolMaterial toolMaterial) {
        return registerAxe(name, toolMaterial, 5.0F, -3.0F);
    }

    private static Item registerAxe(String name, ToolMaterial toolMaterial, float attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, new BLAxeItem(toolMaterial, attackDamageModifier, attackSpeedModifier), BLTabs.BLTabSortGroups.TOOLS);
    }

    private static Item registerHoe(String name, ToolMaterial toolMaterial) {
        return registerHoe(name, toolMaterial, -3, 0F);
    }

    private static Item registerHoe(String name, ToolMaterial toolMaterial, int attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, new BLHoeItem(toolMaterial, attackDamageModifier, attackSpeedModifier), BLTabs.BLTabSortGroups.TOOLS);
    }

    private static Item registerKatana(String name) {
        return registerSword(name, BLToolMaterials.CARBON, 3, 0, BLTabs.BLTabSortGroups.KATANAS);
    }

    private static Item registerSword(String name, ToolMaterial toolMaterial, BLTabs.BLTabSortGroups sortGroup) {
        return registerSword(name, toolMaterial, 3, -2.4F, sortGroup);
    }

    private static Item registerSword(String name, ToolMaterial toolMaterial, int attackDamageModifier, float attackSpeedModifier, BLTabs.BLTabSortGroups sortGroup) {
        return registerItem(name, new SwordItem(toolMaterial, attackDamageModifier, attackSpeedModifier, createCombatItemSettings()), sortGroup);
    }

    private static Item registerArmorItem(String name, ArmorMaterial armorMaterial, EquipmentSlot slot) {
        return registerItem(name, new ArmorItem(armorMaterial, slot, createCombatItemSettings()), BLTabs.BLTabSortGroups.ARMORS);
    }

    private static Item registerHorseArmorItem(String name, int protection, String armorName) {
        return registerItem(name, new BLHorseArmorItem(protection, armorName), BLTabs.BLTabSortGroups.HORSE_ARMORS);
    }

    private static Item registerSpearItem(String name, ToolMaterial toolMaterial, int attackDamage) {
        return registerItem(name, new SpearItem(toolMaterial, attackDamage, -2.4F), BLTabs.BLTabSortGroups.SPEARS);
    }

    private static Item registerMusicDisc(String name, SoundEvent sound, int length) {
        return registerItem(name, new BLMusicDiscItem(sound, length), BLTabs.BLTabSortGroups.MUSIC_DISCS);
    }

    private static Item registerRareItem(final String name, final BLTabs.BLTabSortGroups sortGroup) {
        return registerItem(name, new Item(createSimpleItemSettings(BLTabs.TAB_MISC).rarity(Rarity.RARE)), sortGroup);
    }

    private static Item registerSimpleItem(final String name, final BLTabs.BLTabSortGroups sortGroup) {
        return registerItem(name, new Item(createSimpleItemSettings(BLTabs.TAB_MISC)), sortGroup);
    }

    public static Item registerItem(final String name, final Item item, final BLTabs.BLTabSortGroups sortGroup) {
        BLTabs.TAB_GROUP_ORDERINGS.add(new AbstractMap.SimpleEntry<>(item, sortGroup));
        return Registry.register(Registry.ITEM, new Identifier(BlazersMod.MOD_ID, name), item);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " items");
    }
}