package org.blazers.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.hendrix.forge.helper.ItemHelper;
import org.hendrix.forge.helper.PropertyHelper;
import org.hendrix.forge.registries.HCRegistries;

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

    //#endregion

    //#endregion

    /**
     * Register all {@link Item Items}
     *
     * @param eventBus The {@link IEventBus Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        HCRegistries.ITEMS.register(eventBus);
    }

}