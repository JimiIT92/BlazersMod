package org.blazers.core;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.item.*;

import java.util.AbstractMap;
import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    /**
     * {@link Item Items} {@link DeferredRegister<Item> Registry}
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlazersMod.MOD_ID);

    //#region Items

    public static final RegistryObject<Item> SAPPHIRE = registerSimpleItem("sapphire", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> TOPAZ = registerSimpleItem("topaz", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> PEARL = registerSimpleItem("pearl", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> RUBY = registerSimpleItem("ruby", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> MALACHITE = registerSimpleItem("malachite", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> ONICE = registerSimpleItem("onice", BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> RAW_URANIUM = registerSimpleItem("raw_uranium", BLTabSortGroups.RAW_GEMS_AND_INGOTS);
    public static final RegistryObject<Item> URANIUM_INGOT = registerSimpleItem("uranium_ingot", BLTabSortGroups.RAW_GEMS_AND_INGOTS);
    public static final RegistryObject<Item> CARBON = registerSimpleItem("carbon", BLTabSortGroups.MATERIALS);
    public static final RegistryObject<Item> URANIUM_NUGGET = registerSimpleItem("uranium_nugget", BLTabSortGroups.NUGGETS);
    public static final RegistryObject<Item> BLAZERITE = registerRareItem("blazerite", Rarity.RARE, BLTabSortGroups.GEMS);
    public static final RegistryObject<Item> GYULIANITE = registerRareItem("gyulianite", Rarity.RARE, BLTabSortGroups.GEMS);

    public static final RegistryObject<Item> HOSOMAKI = registerFood("hosomaki", BLFoods.HOSOMAKI);
    public static final RegistryObject<Item> NIGIRI = registerFood("nigiri", BLFoods.NIGIRI);
    public static final RegistryObject<Item> SASHIMI = registerFood("sashimi", BLFoods.SASHIMI);

    public static final RegistryObject<Item> EMERALD_SWORD = registerSword("emerald_sword", BLTiers.EMERALD, BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerShovel("emerald_shovel", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerPickaxe("emerald_pickaxe", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_AXE = registerAxe("emerald_axe", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_HOE = registerHoe("emerald_hoe", BLTiers.EMERALD);
    public static final RegistryObject<Item> AMETHYST_SWORD = registerSword("amethyst_sword", BLTiers.AMETHYST, BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerSword("sapphire_sword", BLTiers.SAPPHIRE, BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerShovel("sapphire_shovel", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerPickaxe("sapphire_pickaxe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerAxe("sapphire_axe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerHoe("sapphire_hoe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> TOPAZ_HAMMER = registerSword("topaz_hammer", BLTiers.TOPAZ, 5, -3.0F, BLTabSortGroups.WEAPONS);
    public static final RegistryObject<Item> PEARL_SWORD = registerSword("pearl_sword", BLTiers.PEARL, BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> RUBY_SWORD = registerSword("ruby_sword", BLTiers.RUBY, BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> RUBY_SHOVEL = registerShovel("ruby_shovel", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_AXE = registerAxe("ruby_axe", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_HOE = registerHoe("ruby_hoe", BLTiers.RUBY);
    public static final RegistryObject<Item> ONICE_SHOVEL = registerShovel("onice_shovel", BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_PICKAXE = registerPickaxe("onice_pickaxe", BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_AXE = registerAxe("onice_axe", BLTiers.ONICE, 5.5F, -3.0F);
    public static final RegistryObject<Item> ONICE_HOE = registerHoe("onice_hoe", BLTiers.ONICE, -2, -0.5F);
    public static final RegistryObject<Item> ONICE_SICKLE = registerSword("onice_sickle", BLTiers.ONICE, BLTabSortGroups.WEAPONS);

    public static final RegistryObject<Item> SPEAR = registerItem("spear", () ->
            new SpearItem(BLTiers.FLINT, 1, -2.4F), BLTabSortGroups.SPEARS);
    public static final RegistryObject<Item> MALACHITE_SPEAR = registerItem("malachite_spear", () ->
            new SpearItem(BLTiers.MALACHITE, 3, -2.4F), BLTabSortGroups.SPEARS);

    public static final RegistryObject<Item> BLAZERITE_SWORD = registerItem("blazerite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.BLAZERITE, Enchantments.FIRE_ASPECT, 10), BLTabSortGroups.SWORDS);
    public static final RegistryObject<Item> GYULIANITE_SWORD = registerItem("gyulianite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.GYULIANITE, Enchantments.KNOCKBACK, 10), BLTabSortGroups.SWORDS);

    public static final RegistryObject<Item> KATANA = registerKatana("katana");
    public static final RegistryObject<Item> WHITE_KATANA = registerKatana("white_katana");
    public static final RegistryObject<Item> ORANGE_KATANA = registerKatana("orange_katana");
    public static final RegistryObject<Item> MAGENTA_KATANA = registerKatana("magenta_katana");
    public static final RegistryObject<Item> LIGHT_BLUE_KATANA = registerKatana("light_blue_katana");
    public static final RegistryObject<Item> YELLOW_KATANA = registerKatana("yellow_katana");
    public static final RegistryObject<Item> LIME_KATANA = registerKatana("lime_katana");
    public static final RegistryObject<Item> PINK_KATANA = registerKatana("pink_katana");
    public static final RegistryObject<Item> GRAY_KATANA = registerKatana("gray_katana");
    public static final RegistryObject<Item> LIGHT_GRAY_KATANA = registerKatana("light_gray_katana");
    public static final RegistryObject<Item> CYAN_KATANA = registerKatana("cyan_katana");
    public static final RegistryObject<Item> PURPLE_KATANA = registerKatana("purple_katana");
    public static final RegistryObject<Item> BLUE_KATANA = registerKatana("blue_katana");
    public static final RegistryObject<Item> BROWN_KATANA = registerKatana("brown_katana");
    public static final RegistryObject<Item> GREEN_KATANA = registerKatana("green_katana");
    public static final RegistryObject<Item> RED_KATANA = registerKatana("red_katana");
    public static final RegistryObject<Item> BLACK_KATANA = registerKatana("black_katana");

    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem("emerald_helmet", BLArmorMaterials.EMERALD, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", BLArmorMaterials.EMERALD, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", BLArmorMaterials.EMERALD, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem("emerald_boots", BLArmorMaterials.EMERALD, EquipmentSlot.FEET);
    public static final RegistryObject<Item> AMETHYST_HELMET = registerArmorItem("amethyst_helmet", BLArmorMaterials.AMETHYST, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = registerArmorItem("amethyst_chestplate", BLArmorMaterials.AMETHYST, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = registerArmorItem("amethyst_leggings", BLArmorMaterials.AMETHYST, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> AMETHYST_BOOTS = registerArmorItem("amethyst_boots", BLArmorMaterials.AMETHYST, EquipmentSlot.FEET);

    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerArmorItem("sapphire_helmet", BLArmorMaterials.SAPPHIRE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = registerArmorItem("sapphire_chestplate", BLArmorMaterials.SAPPHIRE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = registerArmorItem("sapphire_leggings", BLArmorMaterials.SAPPHIRE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerArmorItem("sapphire_boots", BLArmorMaterials.SAPPHIRE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> TOPAZ_HELMET = registerArmorItem("topaz_helmet", BLArmorMaterials.TOPAZ, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> TOPAZ_CHESTPLATE = registerArmorItem("topaz_chestplate", BLArmorMaterials.TOPAZ, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> TOPAZ_LEGGINGS = registerArmorItem("topaz_leggings", BLArmorMaterials.TOPAZ, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> TOPAZ_BOOTS = registerArmorItem("topaz_boots", BLArmorMaterials.TOPAZ, EquipmentSlot.FEET);
    public static final RegistryObject<Item> PEARL_HELMET = registerArmorItem("pearl_helmet", BLArmorMaterials.PEARL, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> PEARL_CHESTPLATE = registerArmorItem("pearl_chestplate", BLArmorMaterials.PEARL, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> PEARL_LEGGINGS = registerArmorItem("pearl_leggings", BLArmorMaterials.PEARL, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> PEARL_BOOTS = registerArmorItem("pearl_boots", BLArmorMaterials.PEARL, EquipmentSlot.FEET);
    public static final RegistryObject<Item> RUBY_HELMET = registerArmorItem("ruby_helmet", BLArmorMaterials.RUBY, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = registerArmorItem("ruby_chestplate", BLArmorMaterials.RUBY, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> RUBY_LEGGINGS = registerArmorItem("ruby_leggings", BLArmorMaterials.RUBY, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> RUBY_BOOTS = registerArmorItem("ruby_boots", BLArmorMaterials.RUBY, EquipmentSlot.FEET);
    public static final RegistryObject<Item> MALACHITE_HELMET = registerArmorItem("malachite_helmet", BLArmorMaterials.MALACHITE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> MALACHITE_CHESTPLATE = registerArmorItem("malachite_chestplate", BLArmorMaterials.MALACHITE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> MALACHITE_LEGGINGS = registerArmorItem("malachite_leggings", BLArmorMaterials.MALACHITE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> MALACHITE_BOOTS = registerArmorItem("malachite_boots", BLArmorMaterials.MALACHITE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> ONICE_HELMET = registerArmorItem("onice_helmet", BLArmorMaterials.ONICE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> ONICE_CHESTPLATE = registerArmorItem("onice_chestplate", BLArmorMaterials.ONICE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> ONICE_LEGGINGS = registerArmorItem("onice_leggings", BLArmorMaterials.ONICE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> ONICE_BOOTS = registerArmorItem("onice_boots", BLArmorMaterials.ONICE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> BLAZERITE_HELMET = registerItem("blazerite_helmet", () -> new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.HEAD, Enchantments.FIRE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> BLAZERITE_CHESTPLATE = registerItem("blazerite_chestplate", () -> new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.CHEST, Enchantments.FIRE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> BLAZERITE_LEGGINGS = registerItem("blazerite_leggings", () -> new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.LEGS, Enchantments.FIRE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> BLAZERITE_BOOTS = registerItem("blazerite_boots", () -> new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  EquipmentSlot.FEET, Enchantments.FIRE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> GYULIANITE_HELMET = registerItem("gyulianite_helmet", () -> new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.HEAD, Enchantments.PROJECTILE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> GYULIANITE_CHESTPLATE = registerItem("gyulianite_chestplate", () -> new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.CHEST, Enchantments.PROJECTILE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> GYULIANITE_LEGGINGS = registerItem("gyulianite_leggings", () -> new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.LEGS, Enchantments.PROJECTILE_PROTECTION, 4), BLTabSortGroups.ARMORS);
    public static final RegistryObject<Item> GYULIANITE_BOOTS = registerItem("gyulianite_boots", () -> new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  EquipmentSlot.FEET, Enchantments.PROJECTILE_PROTECTION, 4), BLTabSortGroups.ARMORS);

    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = registerItem("emerald_horse_armor", () -> new BLHorseArmorItem(13, "emerald"), BLTabSortGroups.HORSE_ARMORS);
    public static final RegistryObject<Item> SAPPHIRE_HORSE_ARMOR = registerItem("sapphire_horse_armor", () -> new BLHorseArmorItem(13, "sapphire"), BLTabSortGroups.HORSE_ARMORS);
    public static final RegistryObject<Item> TOPAZ_HORSE_ARMOR = registerItem("topaz_horse_armor", () -> new BLHorseArmorItem(8, "topaz"), BLTabSortGroups.HORSE_ARMORS);
    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = registerItem("ruby_horse_armor", () -> new BLHorseArmorItem(13, "ruby"), BLTabSortGroups.HORSE_ARMORS);
    public static final RegistryObject<Item> MALACHITE_HORSE_ARMOR = registerItem("malachite_horse_armor", () -> new BLHorseArmorItem(8, "malachite"), BLTabSortGroups.HORSE_ARMORS);

    public static final RegistryObject<Item> CARBON_BOW = registerItem("carbon_bow", CarbonBowItem::new, BLTabSortGroups.BOWS);

    public static final RegistryObject<Item> WITHER_SKELETON_HORSE_SPAWN_EGG = registerSpawnEgg("wither_skeleton_horse_spawn_egg", BLEntityTypes.WITHER_SKELETON_HORSE, 4672845, 1315860);
    public static final RegistryObject<Item> FIREFLY_SPAWN_EGG = registerSpawnEgg("firefly_spawn_egg", BLEntityTypes.FIREFLY, 0x0A0A0A, 0xF0C43E);

    public static final RegistryObject<Item> COPPER_HORN = registerItem("copper_horn", CopperHornItem::new, BLTabSortGroups.COPPER_HORNS);

    public static final RegistryObject<Item> MUSIC_DISC_SURVIVAL = registerMusicDisc("music_disc_survival", BLSounds.MUSIC_DISC_SURVIVAL, 28);
    public static final RegistryObject<Item> MUSIC_DISC_ENDERMAN_VS_BLAZE = registerMusicDisc("music_disc_enderman_vs_blaze", BLSounds.MUSIC_DISC_ENDERMAN_VS_BLAZE, 173);

    //#endregion

    /**
     * Create a {@link Item.Properties Simple Item Properties}
     *
     * @return {@link Item.Properties Simple Item Properties}
     */
    private static Item.Properties createSimpleItemProperties() {
        return new Item.Properties().tab(BLTabs.TAB_MISC);
    }

    /**
     * Create a {@link Item.Properties Tool Item Properties}
     *
     * @return {@link Item.Properties Tool Item Properties}
     */
    private static Item.Properties createToolItemProperties() {
        return new Item.Properties().tab(BLTabs.TAB_TOOLS);
    }

    /**
     * Create a {@link Item.Properties Combat Item Properties}
     *
     * @return {@link Item.Properties Combat Item Properties}
     */
    private static Item.Properties createCombatItemProperties() {
        return new Item.Properties().tab(BLTabs.TAB_COMBAT);
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String Item Name}
     * @param sortGroup {@link BLTabSortGroups Creative Tab Sort Group}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSimpleItem(String name, BLTabSortGroups sortGroup) {
        return registerItem(name, () -> new Item(createSimpleItemProperties()), sortGroup);
    }

    /**
     * Register a {@link Item Rare Item}
     *
     * @param name {@link String Item Name}
     * @param rarity {@link Rarity Item Rarity}
     * @param sortGroup {@link BLTabSortGroups Creative Tab Sort Group}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerRareItem(String name, Rarity rarity, BLTabSortGroups sortGroup) {
        return registerItem(name, () -> new Item(createSimpleItemProperties().rarity(rarity)), sortGroup);
    }

    /**
     * Register a {@link Item Food Item}
     *
     * @param name {@link String Item Name}
     * @param foodProperties {@link FoodProperties Food Properties}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerFood(String name, FoodProperties foodProperties) {
        return registerItem(name, () -> new Item(createSimpleItemProperties().food(foodProperties).tab(BLTabs.TAB_FOOD)), BLTabSortGroups.FOOD);
    }

    /**
     * Register a {@link ShovelItem Shovel}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerShovel(String name, Tier tier) {
        return registerItem(name, () -> new ShovelItem(tier, 1.5F, -3.0F, createToolItemProperties()), BLTabSortGroups.TOOLS);
    }

    /**
     * Register a {@link PickaxeItem Pickaxe}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerPickaxe(String name, Tier tier) {
        return registerItem(name, () -> new PickaxeItem(tier, 1, -2.8F, createToolItemProperties()), BLTabSortGroups.TOOLS);
    }

    /**
     * Register a {@link AxeItem Axe}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerAxe(String name, Tier tier) {
        return registerAxe(name, tier, 5.0F, -3.0F);
    }

    /**
     * Register a {@link AxeItem Axe} and apply
     * some {@link Integer damage modifier} and
     * {@link Float speed modifier}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @param attackDamageModifier {@link Float Attack damage modifier}
     * @param attackSpeedModifier {@link Float Attack speed modifier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerAxe(String name, Tier tier, float attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, () -> new AxeItem(tier, attackDamageModifier, attackSpeedModifier, createToolItemProperties()), BLTabSortGroups.TOOLS);
    }

    /**
     * Register a {@link HoeItem Hoe}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerHoe(String name, Tier tier) {
        return registerHoe(name, tier, -3, 0F);
    }

    /**
     * Register a {@link HoeItem Hoe} and apply
     * some {@link Integer damage modifier} and
     * {@link Float speed modifier}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @param attackDamageModifier {@link Integer Attack damage modifier}
     * @param attackSpeedModifier {@link Float Attack speed modifier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerHoe(String name, Tier tier, int attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, () -> new HoeItem(tier, attackDamageModifier, attackSpeedModifier, createToolItemProperties()), BLTabSortGroups.TOOLS);
    }

    /**
     * Register a {@link SwordItem Katana}
     *
     * @param name {@link String Item Name}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerKatana(String name) {
        return registerSword(name, BLTiers.CARBON, 3, 0, BLTabSortGroups.KATANAS);
    }

    /**
     * Register a {@link SwordItem Sword}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @param sortGroup {@link BLTabSortGroups Creative Tab Sort Group}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSword(String name, Tier tier, BLTabSortGroups sortGroup) {
        return registerSword(name, tier, 3, -2.4F, sortGroup);
    }

    /**
     * Register a {@link SwordItem Sword} and apply
     * some {@link Integer damage modifier} and
     * {@link Float speed modifier}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @param attackDamageModifier {@link Integer Attack damage modifier}
     * @param attackSpeedModifier {@link Float Attack speed modifier}
     * @param sortGroup {@link BLTabSortGroups Creative Tab Sort Group}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSword(String name, Tier tier, int attackDamageModifier, float attackSpeedModifier, BLTabSortGroups sortGroup) {
        return registerItem(name, () -> new SwordItem(tier, attackDamageModifier, attackSpeedModifier, createCombatItemProperties()), sortGroup);
    }

    /**
     * Register an {@link ArmorItem Armor Item}
     *
     * @param name {@link String Item Name}
     * @param armorMaterial {@link ArmorMaterial Armor Material}
     * @param slot {@link EquipmentSlot Armor equipment slot}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerArmorItem(String name, ArmorMaterial armorMaterial, EquipmentSlot slot) {
        return registerItem(name, () -> new ArmorItem(armorMaterial, slot, createCombatItemProperties()), BLTabSortGroups.ARMORS);
    }

    /**
     * Register a {@link ForgeSpawnEggItem Spawn Egg}
     *
     * @param name {@link String Spawn Egg name}
     * @param entityType {@link EntityType Entity Type}
     * @param primaryColor {@link Integer Span Egg primary color}
     * @param secondaryColor {@link Integer Span Egg secondary color}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> entityType, int primaryColor, int secondaryColor) {
        return registerItem(name, () -> new ForgeSpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().tab(BLTabs.TAB_MISC)), BLTabSortGroups.SPAWN_EGGS);
    }

    /**
     * Register a {@link RecordItem Music Disc}
     *
     * @param name {@link String Music Disc Name}
     * @param sound {@link RegistryObject<SoundEvent> Music Disc Sound}
     * @param length {@link Integer Music Disc Length}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerMusicDisc(String name, RegistryObject<SoundEvent> sound, int length) {
        return registerItem(name, () -> new RecordItem(15, sound, new Item.Properties().tab(BLTabs.TAB_MISC).rarity(Rarity.RARE).stacksTo(1), length * 20), BLTabSortGroups.MUSIC_DISCS);
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name {@link String Item Name}
     * @param itemSupplier {@link Supplier<Item> Item Supplier}
     * @param sortGroup {@link BLTabSortGroups Creative Tab Sort Group}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerItem(String name, Supplier<Item> itemSupplier, BLTabSortGroups sortGroup) {
        RegistryObject<Item> item = ITEMS.register(name, itemSupplier);
        BLTabs.TAB_GROUP_ORDERINGS.add(new AbstractMap.SimpleEntry<>(item, sortGroup));
        return item;
    }

    /**
     * Register some custom {@link Item Item} properties
     */
    public static void registerItemProperties() {
        registerCarbonBowProperties();
        registerUseItemProperty(SPEAR, "throwing");
        registerUseItemProperty(MALACHITE_SPEAR, "throwing");
        registerUseItemProperty(COPPER_HORN, "tooting");
    }

    /**
     * Register a property for when
     * an {@link net.minecraft.world.entity.Entity Entity} is
     * using an {@link RegistryObject<Item> Item}
     *
     * @param item {@link RegistryObject<Item> Item}
     * @param name {@link String Property name}
     */
    private static void registerUseItemProperty(RegistryObject<Item> item, String name) {
        ItemProperties.register(item.get(), new ResourceLocation(name), (stack, level, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }

    /**
     * Register the {@link #CARBON_BOW Carbon Bow} properties
     */
    private static void registerCarbonBowProperties() {
        ItemProperties.register(CARBON_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, seed) ->
                entity == null ? 0.0F : entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 2.0F);
        registerUseItemProperty(CARBON_BOW, "pulling");
    }

    /**
     * Get a custom {@link Float FOV} when using a {@link #CARBON_BOW Carbon Bow}
     *
     * @param player {@link Player Player instance}
     * @return {@link #CARBON_BOW Carbon Bow} {@link Float FOV}
     */
    public static float getCarbonBowFieldOfView(Player player) {
        float fov = 1.0F;
        if (player.getAbilities().flying) {
            fov *= 1.1F;
        }

        fov *= ((float)player.getAttributeValue(Attributes.MOVEMENT_SPEED) / player.getAbilities().getWalkingSpeed() + 1.0F) / 2.0F;
        if (player.getAbilities().getWalkingSpeed() == 0.0F || Float.isNaN(fov) || Float.isInfinite(fov)) {
            fov = 1.0F;
        }

        int i = player.getTicksUsingItem();
        float f1 = (float)i / 5.0F;
        if (f1 > 1.0F) {
            f1 = 1.0F;
        } else {
            f1 *= f1;
        }

        fov *= 1.0F - f1 * 0.15F;
        return fov;
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link Item Items}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}