package org.blazers.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod Items}
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
    public static final RegistryObject<Item> BLAZERITE = registerRareItem("blazerite", Rarity.EPIC);
    public static final RegistryObject<Item> GYULIANITE = registerRareItem("gyulianite", Rarity.EPIC);

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