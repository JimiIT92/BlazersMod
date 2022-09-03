package org.blazers.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;

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

    private static FabricItemSettings createSimpleItemSettings(final ItemGroup tab) {
        return new FabricItemSettings().group(tab);
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