package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.Holder;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.ItemHelper;
import org.blazers.helper.PropertyHelper;
import org.blazers.helper.RegistryHelper;
import org.blazers.item.CarbonBowItem;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Registry

    /**
     * The {@link DeferredRegister<Item> Item Registry}
     */
    private static final DeferredRegister<Item> ITEMS = RegistryHelper.registry(ForgeRegistries.ITEMS);

    //#endregion

    //#region Items

    //#region Gems

    public static final RegistryObject<Item> CARBON = registerGem(BLMaterials.CARBON);
    public static final RegistryObject<Item> SAPPHIRE = registerGem(BLMaterials.SAPPHIRE);
    public static final RegistryObject<Item> TOPAZ = registerGem(BLMaterials.TOPAZ);
    public static final RegistryObject<Item> PEARL = registerGem(BLMaterials.PEARL);
    public static final RegistryObject<Item> RUBY = registerGem(BLMaterials.RUBY);
    public static final RegistryObject<Item> MALACHITE = registerGem(BLMaterials.MALACHITE);
    public static final RegistryObject<Item> ONICE = registerGem(BLMaterials.ONICE);
    public static final RegistryObject<Item> RAW_URANIUM = registerGem(BLMaterials.RAW_URANIUM);
    public static final RegistryObject<Item> URANIUM_NUGGET = registerNugget(BLMaterials.URANIUM);
    public static final RegistryObject<Item> URANIUM_INGOT = registerIngot(BLMaterials.URANIUM);
    public static final RegistryObject<Item> BLAZERITE = registerGem(BLMaterials.BLAZERITE, Rarity.RARE);
    public static final RegistryObject<Item> GYULIANITE = registerGem(BLMaterials.GYULIANITE, Rarity.RARE);

    //#endregion

    //#region Food

    public static final RegistryObject<Item> HOSOMAKI = registerFood("hosomaki", 2, 0.1F);
    public static final RegistryObject<Item> NIGIRI = registerFood("nigiri", 5, 0.6F);
    public static final RegistryObject<Item> SASHIMI = registerFood("sashimi", 6, 0.8F);

    //#endregion

    //#region Tools, Weapons and Armors

    //#region Emerald

    public static final RegistryObject<Item> EMERALD_SWORD = registerSword(BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerShovel(BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerPickaxe(BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_AXE = registerAxe(BLTiers.EMERALD);
    public static final RegistryObject<Item> EMERALD_HOE = registerHoe(BLTiers.EMERALD);

    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem(BLTiers.EMERALD, BLArmorMaterials.EMERALD, 35, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem(BLTiers.EMERALD,BLArmorMaterials.EMERALD, 35, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem(BLTiers.EMERALD,BLArmorMaterials.EMERALD, 35, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem(BLTiers.EMERALD,BLArmorMaterials.EMERALD, 35, ArmorItem.Type.BOOTS);

    //#endregion

    //#region Amethyst

    public static final RegistryObject<Item> AMETHYST_SWORD = registerSword(BLTiers.AMETHYST);

    public static final RegistryObject<Item> AMETHYST_HELMET = registerArmorItem(BLTiers.AMETHYST, BLArmorMaterials.AMETHYST, 10, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = registerArmorItem(BLTiers.AMETHYST, BLArmorMaterials.AMETHYST, 10, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = registerArmorItem(BLTiers.AMETHYST, BLArmorMaterials.AMETHYST, 10, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> AMETHYST_BOOTS = registerArmorItem(BLTiers.AMETHYST, BLArmorMaterials.AMETHYST, 10, ArmorItem.Type.BOOTS);

    //#endregion

    //#region Sapphire

    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerSword(BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerShovel(BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerPickaxe(BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerAxe(BLTiers.SAPPHIRE);
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerHoe(BLTiers.SAPPHIRE);

    /*public static final RegistryObject<Item> SAPPHIRE_HELMET = registerArmorItem(BLTiers.SAPPHIRE, BLArmorMaterials.SAPPHIRE, 35, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = registerArmorItem(BLTiers.SAPPHIRE, BLArmorMaterials.SAPPHIRE, 35, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = registerArmorItem(BLTiers.SAPPHIRE, BLArmorMaterials.SAPPHIRE, 35, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerArmorItem(BLTiers.SAPPHIRE, BLArmorMaterials.SAPPHIRE, 35, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Topaz

    public static final RegistryObject<Item> TOPAZ_HAMMER = registerSword(BLTiers.TOPAZ, "hammer", 5, -3F);

    /*public static final RegistryObject<Item> TOPAZ_HELMET = registerArmorItem(BLTiers.TOPAZ, BLArmorMaterials.TOPAZ, 24, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> TOPAZ_CHESTPLATE = registerArmorItem(BLTiers.TOPAZ, BLArmorMaterials.TOPAZ, 24, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> TOPAZ_LEGGINGS = registerArmorItem(BLTiers.TOPAZ, BLArmorMaterials.TOPAZ, 24, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> TOPAZ_BOOTS = registerArmorItem(BLTiers.TOPAZ, BLArmorMaterials.TOPAZ, 24, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Pearl

    public static final RegistryObject<Item> PEARL_SWORD = registerSword(BLTiers.PEARL);

    /*public static final RegistryObject<Item> PEARL_HELMET = registerArmorItem(BLTiers.PEARL, BLArmorMaterials.PEARL, 10, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> PEARL_CHESTPLATE = registerArmorItem(BLTiers.PEARL, BLArmorMaterials.PEARL, 10, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> PEARL_LEGGINGS = registerArmorItem(BLTiers.PEARL, BLArmorMaterials.PEARL, 10, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> PEARL_BOOTS = registerArmorItem(BLTiers.PEARL, BLArmorMaterials.PEARL, 10, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Ruby

    public static final RegistryObject<Item> RUBY_SWORD = registerSword(BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_SHOVEL = registerShovel(BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_PICKAXE = registerPickaxe(BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_AXE = registerAxe(BLTiers.RUBY);
    public static final RegistryObject<Item> RUBY_HOE = registerHoe(BLTiers.RUBY);

    /*public static final RegistryObject<Item> RUBY_HELMET = registerArmorItem(BLTiers.RUBY, BLArmorMaterials.RUBY, 35, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = registerArmorItem(BLTiers.RUBY, BLArmorMaterials.RUBY, 35, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> RUBY_LEGGINGS = registerArmorItem(BLTiers.RUBY, BLArmorMaterials.RUBY, 35, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> RUBY_BOOTS = registerArmorItem(BLTiers.RUBY, BLArmorMaterials.RUBY, 35, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Onice

    public static final RegistryObject<Item> ONICE_SICKLE = registerSword(BLTiers.ONICE, "sickle", 3, -2.4F);
    public static final RegistryObject<Item> ONICE_SHOVEL = registerShovel(BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_PICKAXE = registerPickaxe(BLTiers.ONICE);
    public static final RegistryObject<Item> ONICE_AXE = registerAxe(BLTiers.ONICE, 5.5F, -3F);
    public static final RegistryObject<Item> ONICE_HOE = registerHoe(BLTiers.ONICE, -2F, -0.5F);

    /*public static final RegistryObject<Item> ONICE_HELMET = registerArmorItem(BLTiers.ONICE, BLArmorMaterials.ONICE, 24, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> ONICE_CHESTPLATE = registerArmorItem(BLTiers.ONICE, BLArmorMaterials.ONICE, 24, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> ONICE_LEGGINGS = registerArmorItem(BLTiers.ONICE, BLArmorMaterials.ONICE, 24, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> ONICE_BOOTS = registerArmorItem(BLTiers.ONICE, BLArmorMaterials.ONICE, 24, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Malachite

    /*public static final RegistryObject<Item> MALACHITE_HELMET = registerArmorItem(BLTiers.MALACHITE, BLArmorMaterials.MALACHITE, 24, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> MALACHITE_CHESTPLATE = registerArmorItem(BLTiers.MALACHITE, BLArmorMaterials.MALACHITE, 24, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> MALACHITE_LEGGINGS = registerArmorItem(BLTiers.MALACHITE, BLArmorMaterials.MALACHITE, 24, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> MALACHITE_BOOTS = registerArmorItem(BLTiers.MALACHITE, BLArmorMaterials.MALACHITE, 24, ArmorItem.Type.BOOTS);*/

    //#endregion

    //#region Carbon

    public static final RegistryObject<Item> CARBON_BOW = registerItem("carbon_bow", Suppliers.memoize(CarbonBowItem::new));

    //#endregion

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register a {@link Item gem}
     *
     * @param material {@link BLMaterials The gem material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered gem}
     */
    private static RegistryObject<Item> registerGem(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerGem(material, Rarity.COMMON, featureFlags);
    }

    /**
     * Register a {@link Item gem}
     *
     * @param material {@link BLMaterials The gem material}
     * @param rarity {@link Rarity The Item rarity}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered gem}
     */
    private static RegistryObject<Item> registerGem(final BLMaterials material, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(BlockHelper.materialName(material), rarity, featureFlags);
    }

    /**
     * Register a {@link Item nugget}
     *
     * @param material {@link BLMaterials The nugget material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered nugget}
     */
    private static RegistryObject<Item> registerNugget(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerItem(BlockHelper.materialName(material) + "_nugget", featureFlags);
    }

    /**
     * Register an {@link Item ingot}
     *
     * @param material {@link BLMaterials The ingot material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered ingot}
     */
    private static RegistryObject<Item> registerIngot(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerItem(BlockHelper.materialName(material) + "_ingot", featureFlags);
    }

    /**
     * Register a {@link Item Food Item}
     *
     * @param name {@link String The item name}
     * @param nutrition {@link Integer The food nutrition value}
     * @param saturationModifier {@link Float The food saturation modifier value}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Food Item}
     */
    private static RegistryObject<Item> registerFood(final String name, final int nutrition, final float saturationModifier, final FeatureFlag... featureFlags) {
        return registerItem(name, Suppliers.memoize(() -> new Item(PropertyHelper.food(nutrition, saturationModifier, featureFlags))));
    }

    /**
     * Register a {@link SwordItem Sword Item}
     *
     * @param tier {@link BLTiers The Sword Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Sword Item}
     */
    private static RegistryObject<Item> registerSword(final Tier tier, final FeatureFlag... featureFlags) {
        return registerSword(tier, 3, -2.4F, featureFlags);
    }

    /**
     * Register a {@link SwordItem Sword Item}
     *
     * @param tier {@link BLTiers The Sword Tier}
     * @param attackDamageModifier {@link Integer The attack damage modifier}
     * @param attackSpeedModifier {@link Float The attack speed modifier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Sword Item}
     */
    private static RegistryObject<Item> registerSword(final Tier tier, final int attackDamageModifier, final float attackSpeedModifier, final FeatureFlag... featureFlags) {
        return registerSword(tier, "sword", 3, -2.4F, featureFlags);
    }

    /**
     * Register a {@link SwordItem Sword Item}
     *
     * @param tier {@link BLTiers The Sword Tier}
     * @param itemSuffix {@link String The Sword Item name suffix}
     * @param attackDamageModifier {@link Integer The attack damage modifier}
     * @param attackSpeedModifier {@link Float The attack speed modifier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Sword Item}
     */
    private static RegistryObject<Item> registerSword(final Tier tier, final String itemSuffix, final int attackDamageModifier, final float attackSpeedModifier, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_" + itemSuffix, Suppliers.memoize(() -> new SwordItem(tier, PropertyHelper.item(featureFlags).attributes(SwordItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)))));
    }

    /**
     * Register a {@link ShovelItem Shovel Item}
     *
     * @param tier {@link BLTiers The Shovel Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Shovel Item}
     */
    private static RegistryObject<Item> registerShovel(final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_shovel", Suppliers.memoize(() -> new ShovelItem(tier, PropertyHelper.item(featureFlags).attributes(ShovelItem.createAttributes(tier, 1.5F, -3F)))));
    }

    /**
     * Register a {@link PickaxeItem Pickaxe Item}
     *
     * @param tier {@link BLTiers The Pickaxe Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Pickaxe Item}
     */
    private static RegistryObject<Item> registerPickaxe(final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_pickaxe", Suppliers.memoize(() -> new PickaxeItem(tier, PropertyHelper.item(featureFlags).attributes(PickaxeItem.createAttributes(tier, 1F, -2.8F)))));
    }

    /**
     * Register an {@link AxeItem Axe Item}
     *
     * @param tier {@link BLTiers The Axe Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Axe Item}
     */
    private static RegistryObject<Item> registerAxe(final Tier tier, final FeatureFlag... featureFlags) {
        return registerAxe(tier, 5, -3F, featureFlags);
    }

    /**
     * Register an {@link AxeItem Axe Item}
     *
     * @param tier {@link BLTiers The Axe Tier}
     * @param attackDamageModifier {@link Float The attack damage modifier}
     * @param attackSpeedModifier {@link Float The attack speed modifier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Axe Item}
     */
    private static RegistryObject<Item> registerAxe(final Tier tier, final float attackDamageModifier, final float attackSpeedModifier, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_axe", Suppliers.memoize(() -> new AxeItem(tier, PropertyHelper.item(featureFlags).attributes(AxeItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)))));
    }

    /**
     * Register an {@link HoeItem Hoe Item}
     *
     * @param tier {@link BLTiers The Hoe Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Hoe Item}
     */
    private static RegistryObject<Item> registerHoe(final Tier tier, final FeatureFlag... featureFlags) {
        return registerHoe(tier, -3, 0F, featureFlags);
    }

    /**
     * Register an {@link HoeItem Hoe Item}
     *
     * @param tier {@link BLTiers The Hoe Tier}
     * @param attackDamageModifier {@link Float The attack damage modifier}
     * @param attackSpeedModifier {@link Float The attack speed modifier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Hoe Item}
     */
    private static RegistryObject<Item> registerHoe(final Tier tier, final float attackDamageModifier, final float attackSpeedModifier, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_hoe", Suppliers.memoize(() -> new HoeItem(tier, PropertyHelper.item(featureFlags).attributes(HoeItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)))));
    }

    /**
     * Register an {@link ArmorItem Armor Item}
     *
     * @param tier {@link Tier The Item tier}
     * @param armorMaterialSupplier {@link Holder<ArmorMaterial> The Armor material}
     * @param durabilityModifier {@link Integer The durability modifier}
     * @param armorType {@link ArmorItem.Type The Armor Item type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Armor Item}
     */
    private static RegistryObject<Item> registerArmorItem(final Tier tier, final Supplier<ArmorMaterial> armorMaterialSupplier, final int durabilityModifier, final ArmorItem.Type armorType, final FeatureFlag... featureFlags) {
        return registerItem(ItemHelper.tierName(tier) + "_" + armorType.getSerializedName(), Suppliers.memoize(() -> new ArmorItem(Holder.direct(armorMaterialSupplier.get()), armorType, PropertyHelper.item(featureFlags).durability(armorType.getDurability(durabilityModifier)))));
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerItem(final String name, final FeatureFlag... featureFlags) {
        return registerItem(name, Rarity.COMMON, featureFlags);
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param rarity {@link Rarity The Item rarity}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerItem(final String name, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(name, Suppliers.memoize(() -> new Item(PropertyHelper.item(featureFlags).rarity(rarity))));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The Item name}
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Block Item}
     */
    static RegistryObject<Item> registerBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerItem(name, Suppliers.memoize(() -> new BlockItem(blockSupplier.get(), PropertyHelper.item(featureFlags))));
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param itemSupplier {@link Supplier<Item> The Item supplier}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    static RegistryObject<Item> registerItem(final String name, final Supplier<? extends Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    //#endregion

    //#region Item properties

    /**
     * Register an {@link ItemProperties Item property}
     *
     * @param item {@link RegistryObject<Item> The Item to register the property to}
     * @param name {@link String The property name}
     */
    private static void registerUseItemProperty(final RegistryObject<Item> item, final String name) {
        registerUseItemProperty(item, name, (itemStack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem().is(itemStack.getItem()) ? 1F : 0F);
    }

    /**
     * Register an {@link ItemProperties Item property}
     *
     * @param item {@link RegistryObject<Item> The Item to register the property to}
     * @param name {@link String The property name}
     * @param itemPropertyFunction {@link ItemPropertyFunction The Item property function}
     */
    private static void registerUseItemProperty(final RegistryObject<Item> item, final String name, final ItemPropertyFunction itemPropertyFunction) {
        ItemProperties.register(item.get(), RegistryHelper.location(name), itemPropertyFunction);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link Item Items}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link ItemProperties Item Properties}
     */
    public static void registerItemProperties() {
        registerUseItemProperty(CARBON_BOW, "pull", (itemStack, level, entity, seed) -> entity == null ? 0F : !entity.getUseItem().is(itemStack.getItem()) ? 0F : (float)(itemStack.getUseDuration() - entity.getUseItemRemainingTicks()) / 2F);
        registerUseItemProperty(CARBON_BOW, "pulling");
    }

    //#endregion

}