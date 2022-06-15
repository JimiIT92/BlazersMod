package org.blazers.core;

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

    public static final RegistryObject<Item> RUBY_SWORD = registerItem("ruby_sword", () ->
            new SwordItem(BLTiers.RUBY, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> RUBY_SHOVEL = registerItem("ruby_shovel", () ->
            new ShovelItem(BLTiers.RUBY, 1.5F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> RUBY_PICKAXE = registerItem("ruby_pickaxe", () ->
            new PickaxeItem(BLTiers.RUBY, 1, -2.8F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> RUBY_AXE = registerItem("ruby_axe", () ->
            new AxeItem(BLTiers.RUBY, 5.0F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> RUBY_HOE = registerItem("ruby_hoe", () ->
            new HoeItem(BLTiers.RUBY, -3, 0,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerItem("sapphire_sword", () ->
            new SwordItem(BLTiers.SAPPHIRE, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerItem("sapphire_shovel", () ->
            new ShovelItem(BLTiers.SAPPHIRE, 1.5F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe", () ->
            new PickaxeItem(BLTiers.SAPPHIRE, 1, -2.8F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerItem("sapphire_axe", () ->
            new AxeItem(BLTiers.SAPPHIRE, 5.0F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerItem("sapphire_hoe", () ->
            new HoeItem(BLTiers.SAPPHIRE, -3, 0,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> EMERALD_SWORD = registerItem("emerald_sword", () ->
            new SwordItem(BLTiers.EMERALD, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerItem("emerald_shovel", () ->
            new ShovelItem(BLTiers.EMERALD, 1.5F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerItem("emerald_pickaxe", () ->
            new PickaxeItem(BLTiers.EMERALD, 1, -2.8F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> EMERALD_AXE = registerItem("emerald_axe", () ->
            new AxeItem(BLTiers.EMERALD, 5.0F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> EMERALD_HOE = registerItem("emerald_hoe", () ->
            new HoeItem(BLTiers.EMERALD, -3, 0,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> ONICE_SHOVEL = registerItem("onice_shovel", () ->
            new ShovelItem(BLTiers.ONICE, 1.5F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> ONICE_PICKAXE = registerItem("onice_pickaxe", () ->
            new PickaxeItem(BLTiers.ONICE, 1, -2.8F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> ONICE_AXE = registerItem("onice_axe", () ->
            new AxeItem(BLTiers.ONICE, 5.5F, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> ONICE_HOE = registerItem("onice_hoe", () ->
            new HoeItem(BLTiers.ONICE, -2, -0.5F,
                    new Item.Properties().tab(BLTabs.TAB_TOOLS)));
    public static final RegistryObject<Item> MALACHITE_SPEAR = registerItem("malachite_spear", () ->
            new SpearItem(BLTiers.MALACHITE, 3, -2.4F));
    public static final RegistryObject<Item> SPEAR = registerItem("spear", () ->
            new SpearItem(BLTiers.FLINT, 1, -2.4F));
    public static final RegistryObject<Item> ONICE_SICKLE = registerItem("onice_sickle", () ->
            new SwordItem(BLTiers.ONICE, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> TOPAZ_HAMMER = registerItem("topaz_hammer", () ->
            new SwordItem(BLTiers.TOPAZ, 5, -3.0F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> PEARL_SWORD = registerItem("pearl_sword", () ->
            new SwordItem(BLTiers.PEARL, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));
    public static final RegistryObject<Item> AMETHYST_SWORD = registerItem("amethyst_sword", () ->
            new SwordItem(BLTiers.AMETHYST, 3, -2.4F,
                    new Item.Properties().tab(BLTabs.TAB_COMBAT)));

    public static final RegistryObject<Item> BLAZERITE_SWORD = registerItem("blazerite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.BLAZERITE, 3, -2.4F, Enchantments.FIRE_ASPECT, 10));
    public static final RegistryObject<Item> GYULIANITE_SWORD = registerItem("gyulianite_sword", () ->
            new PreEnchantedSwordItem(BLTiers.GYULIANITE, 3, -2.4F, Enchantments.KNOCKBACK, 10));

    //#endregion

    /**
     * Create a {@link Item Simple Item}
     *
     * @return {@link Item Item}
     */
    private static Item.Properties createSimpleItemProperties() {
        return new Item.Properties().tab(BLTabs.TAB_MISC);
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