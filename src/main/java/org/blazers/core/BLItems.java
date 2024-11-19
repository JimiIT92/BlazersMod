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

    public static final RegistryObject<Item> EMERALD_SWORD = ItemHelper.registerSword("emerald_sword", BLToolMaterials.EMERALD);

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