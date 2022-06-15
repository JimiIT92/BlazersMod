package org.blazers.core;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.item.PreEnchantedSwordItem;
import org.blazers.item.SpearItem;

import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    /**
     * {@link Item Items} {@link DeferredRegister<Item> Registry}
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlazersMod.MOD_ID);

    //#region Items

    public static final RegistryObject<Item> RUBY = registerSimpleItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = registerSimpleItem("sapphire");
    public static final RegistryObject<Item> TOPAZ = registerSimpleItem("topaz");
    public static final RegistryObject<Item> PEARL = registerSimpleItem("pearl");
    public static final RegistryObject<Item> MALACHITE = registerSimpleItem("malachite");
    public static final RegistryObject<Item> ONICE = registerSimpleItem("onice");
    public static final RegistryObject<Item> CARBON = registerSimpleItem("carbon");
    public static final RegistryObject<Item> RAW_URANIUM = registerSimpleItem("raw_uranium");
    public static final RegistryObject<Item> URANIUM_NUGGET = registerSimpleItem("uranium_nugget");
    public static final RegistryObject<Item> URANIUM_INGOT = registerSimpleItem("uranium_ingot");
    public static final RegistryObject<Item> BLAZERITE = registerRareItem("blazerite", Rarity.RARE);
    public static final RegistryObject<Item> GYULIANITE = registerRareItem("gyulianite", Rarity.RARE);

    public static final RegistryObject<Item> HOSOMAKI = registerFood("hosomaki", BLFoods.HOSOMAKI);
    public static final RegistryObject<Item> NIGIRI = registerFood("nigiri", BLFoods.NIGIRI);
    public static final RegistryObject<Item> SASHIMI = registerFood("sashimi", BLFoods.SASHIMI);

    public static final RegistryObject<Item> RUBY_SWORD = registerSword("ruby_sword", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_SHOVEL = registerShovel("ruby_shovel", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_AXE = registerAxe("ruby_axe", BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_HOE = registerHoe("ruby_hoe", BLTiers.RUBY);
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerSword("sapphire_sword", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerShovel("sapphire_shovel", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerPickaxe("sapphire_pickaxe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerAxe("sapphire_axe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerHoe("sapphire_hoe", BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> EMERALD_SWORD = registerSword("emerald_sword", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerShovel("emerald_shovel", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerPickaxe("emerald_pickaxe", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_AXE = registerAxe("emerald_axe", BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_HOE = registerHoe("emerald_hoe", BLTiers.EMERALD);
    public static final RegistryObject<Item> ONICE_SHOVEL = registerShovel("onice_shovel", BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_PICKAXE = registerPickaxe("onice_pickaxe", BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_AXE = registerAxe("onice_axe", BLTiers.ONICE, 5.5F, -3.0F);
    public static final RegistryObject<Item> ONICE_HOE = registerHoe("onice_hoe", BLTiers.ONICE, -2, -0.5F);
    public static final RegistryObject<Item> MALACHITE_SPEAR = registerItem("malachite_spear", () ->
            new SpearItem(BLTiers.MALACHITE, 3, -2.4F));
    public static final RegistryObject<Item> SPEAR = registerItem("spear", () ->
            new SpearItem(BLTiers.FLINT, 1, -2.4F));
    public static final RegistryObject<Item> ONICE_SICKLE = registerSword("onice_sickle", BLTiers.ONICE);
    public static final RegistryObject<Item> TOPAZ_HAMMER = registerSword("topaz_hammer", BLTiers.TOPAZ, 5, -3.0F);
    public static final RegistryObject<Item> PEARL_SWORD = registerSword("pearl_sword", BLTiers.PEARL);
    public static final RegistryObject<Item> AMETHYST_SWORD = registerSword("amethyst_sword", BLTiers.AMETHYST);
    public static final RegistryObject<Item> BLAZERITE_SWORD = registerItem("blazerite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.BLAZERITE, Enchantments.FIRE_ASPECT, 10));
    public static final RegistryObject<Item> GYULIANITE_SWORD = registerItem("gyulianite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.GYULIANITE, Enchantments.KNOCKBACK, 10));
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

    public static final RegistryObject<Item> RUBY_HELMET = registerArmorItem("ruby_helmet", BLArmorMaterials.RUBY, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = registerArmorItem("ruby_chestplate", BLArmorMaterials.RUBY, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> RUBY_LEGGINGS = registerArmorItem("ruby_leggings", BLArmorMaterials.RUBY, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> RUBY_BOOTS = registerArmorItem("ruby_boots", BLArmorMaterials.RUBY, EquipmentSlot.FEET);
    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerArmorItem("sapphire_helmet", BLArmorMaterials.SAPPHIRE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = registerArmorItem("sapphire_chestplate", BLArmorMaterials.SAPPHIRE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = registerArmorItem("sapphire_leggings", BLArmorMaterials.SAPPHIRE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerArmorItem("sapphire_boots", BLArmorMaterials.SAPPHIRE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem("emerald_helmet", BLArmorMaterials.EMERALD, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", BLArmorMaterials.EMERALD, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", BLArmorMaterials.EMERALD, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem("emerald_boots", BLArmorMaterials.EMERALD, EquipmentSlot.FEET);
    public static final RegistryObject<Item> ONICE_HELMET = registerArmorItem("onice_helmet", BLArmorMaterials.ONICE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> ONICE_CHESTPLATE = registerArmorItem("onice_chestplate", BLArmorMaterials.ONICE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> ONICE_LEGGINGS = registerArmorItem("onice_leggings", BLArmorMaterials.ONICE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> ONICE_BOOTS = registerArmorItem("onice_boots", BLArmorMaterials.ONICE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> TOPAZ_HELMET = registerArmorItem("topaz_helmet", BLArmorMaterials.TOPAZ, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> TOPAZ_CHESTPLATE = registerArmorItem("topaz_chestplate", BLArmorMaterials.TOPAZ, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> TOPAZ_LEGGINGS = registerArmorItem("topaz_leggings", BLArmorMaterials.TOPAZ, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> TOPAZ_BOOTS = registerArmorItem("topaz_boots", BLArmorMaterials.TOPAZ, EquipmentSlot.FEET);
    public static final RegistryObject<Item> MALACHITE_HELMET = registerArmorItem("malachite_helmet", BLArmorMaterials.MALACHITE, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> MALACHITE_CHESTPLATE = registerArmorItem("malachite_chestplate", BLArmorMaterials.MALACHITE, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> MALACHITE_LEGGINGS = registerArmorItem("malachite_leggings", BLArmorMaterials.MALACHITE, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> MALACHITE_BOOTS = registerArmorItem("malachite_boots", BLArmorMaterials.MALACHITE, EquipmentSlot.FEET);
    public static final RegistryObject<Item> PEARL_HELMET = registerArmorItem("pearl_helmet", BLArmorMaterials.PEARL, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> PEARL_CHESTPLATE = registerArmorItem("pearl_chestplate", BLArmorMaterials.PEARL, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> PEARL_LEGGINGS = registerArmorItem("pearl_leggings", BLArmorMaterials.PEARL, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> PEARL_BOOTS = registerArmorItem("pearl_boots", BLArmorMaterials.PEARL, EquipmentSlot.FEET);
    public static final RegistryObject<Item> AMETHYST_HELMET = registerArmorItem("amethyst_helmet", BLArmorMaterials.AMETHYST, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = registerArmorItem("amethyst_chestplate", BLArmorMaterials.AMETHYST, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = registerArmorItem("amethyst_leggings", BLArmorMaterials.AMETHYST, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> AMETHYST_BOOTS = registerArmorItem("amethyst_boots", BLArmorMaterials.AMETHYST, EquipmentSlot.FEET);

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
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSimpleItem(String name) {
        return registerItem(name, () -> new Item(createSimpleItemProperties()));
    }

    /**
     * Register a {@link Item Rare Item}
     *
     * @param name {@link String Item Name}
     * @param rarity {@link Rarity Item Rarity}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerRareItem(String name, Rarity rarity) {
        return registerItem(name, () -> new Item(createSimpleItemProperties().rarity(rarity)));
    }

    /**
     * Register a {@link Item Food Item}
     *
     * @param name {@link String Item Name}
     * @param foodProperties {@link FoodProperties Food Properties}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerFood(String name, FoodProperties foodProperties) {
        return registerItem(name, () -> new Item(createSimpleItemProperties().food(foodProperties).tab(BLTabs.TAB_FOOD)));
    }

    /**
     * Register a {@link ShovelItem Shovel}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerShovel(String name, Tier tier) {
        return registerItem(name, () -> new ShovelItem(tier, 1.5F, -3.0F, createToolItemProperties()));
    }

    /**
     * Register a {@link PickaxeItem Pickaxe}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerPickaxe(String name, Tier tier) {
        return registerItem(name, () -> new PickaxeItem(tier, 1, -2.8F, createToolItemProperties()));
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
        return registerItem(name, () -> new AxeItem(tier, attackDamageModifier, attackSpeedModifier, createToolItemProperties()));
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
        return registerItem(name, () -> new HoeItem(tier, attackDamageModifier, attackSpeedModifier, createToolItemProperties()));
    }

    /**
     * Register a {@link SwordItem Katana}
     *
     * @param name {@link String Item Name}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerKatana(String name) {
        return registerSword(name, BLTiers.CARBON, 3, 0);
    }

    /**
     * Register a {@link SwordItem Sword}
     *
     * @param name {@link String Item Name}
     * @param tier {@link Tier Item Tier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSword(String name, Tier tier) {
        return registerSword(name, tier, 3, -2.4F);
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
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerSword(String name, Tier tier, int attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, () -> new SwordItem(tier, attackDamageModifier, attackSpeedModifier, createCombatItemProperties()));
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
        return registerItem(name, () -> new ArmorItem(armorMaterial, slot, createCombatItemProperties()));
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name {@link String Item Name}
     * @param itemSupplier {@link Supplier<Item> Item Supplier}
     * @return {@link RegistryObject<Item> Registered Item}
     */
    private static RegistryObject<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
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