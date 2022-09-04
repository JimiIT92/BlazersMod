package org.blazers.core;

import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.blazers.tab.SortedItemGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class BLTabs {

    public static final List<Map.Entry<Item, BLTabSortGroups>> TAB_GROUP_ORDERINGS = new ArrayList<>();

    public static final ItemGroup TAB_BUILDING_BLOCKS = createTab("building_blocks", () -> new ItemStack(BLBlocks.SAPPHIRE_ORE),
            BLTabSortGroups.COBBLESTONES, BLTabSortGroups.OVERWORLD_ORES, BLTabSortGroups.NETHER_ORES, BLTabSortGroups.RAW_STORAGE_BLOCKS, BLTabSortGroups.STORAGE_BLOCKS,
            BLTabSortGroups.COPPER_BLOCKS, BLTabSortGroups.HOLLOW_LOGS, BLTabSortGroups.HOLLOW_STRIPPED_LOGS, BLTabSortGroups.CUT_BRICKS);

    public static final ItemGroup TAB_DECORATIONS = createTab("decorations", () -> new ItemStack(BLBlocks.CATTAIL), BLTabSortGroups.PLANTS, BLTabSortGroups.DRIPSTONES);

    //public static final ItemGroup TAB_REDSTONE = createTab("redstone", () -> new ItemStack(BLBlocks.COPPER_BUTTON), BLTabSortGroups.TNT, BLTabSortGroups.BUTTONS);

    public static final ItemGroup TAB_TOOLS = createTab("tools", () -> new ItemStack(BLItems.EMERALD_PICKAXE), BLTabSortGroups.TOOLS);

    public static final ItemGroup TAB_COMBAT = createTab("combat", () -> new ItemStack(BLItems.KATANA),
            BLTabSortGroups.BOWS, BLTabSortGroups.SWORDS, BLTabSortGroups.WEAPONS, BLTabSortGroups.KATANAS, BLTabSortGroups.ARMORS, BLTabSortGroups.SPEARS);

    public static final ItemGroup TAB_FOOD = createTab("food", () -> new ItemStack(BLItems.SASHIMI), BLTabSortGroups.FOOD);

    public static final ItemGroup TAB_MISC = createTab("misc", () -> new ItemStack(BLItems.SAPPHIRE),
            BLTabSortGroups.GEMS, BLTabSortGroups.RAW_GEMS_AND_INGOTS, BLTabSortGroups.MATERIALS, BLTabSortGroups.NUGGETS, BLTabSortGroups.SPAWN_EGGS, BLTabSortGroups.HORSE_ARMORS);

    private static ItemGroup createTab(final String name, final Supplier<ItemStack> item, final BLTabSortGroups... sortGroups) {
        ((ItemGroupExtensions) ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return new SortedItemGroup(name, item, sortGroups);
    }

    public enum BLTabSortGroups {
        COBBLESTONES,
        OVERWORLD_ORES,
        NETHER_ORES,
        RAW_STORAGE_BLOCKS,
        STORAGE_BLOCKS,
        COPPER_BLOCKS,
        HOLLOW_LOGS,
        HOLLOW_STRIPPED_LOGS,
        CUT_BRICKS,
        PLANTS,
        DRIPSTONES,
        TNT,
        BUTTONS,
        GEMS,
        RAW_GEMS_AND_INGOTS,
        MATERIALS,
        NUGGETS,
        SPAWN_EGGS,
        HORSE_ARMORS,
        MUSIC_DISCS,
        COPPER_HORNS,
        FOOD,
        TOOLS,
        BOWS,
        SWORDS,
        WEAPONS,
        KATANAS,
        ARMORS,
        SPEARS,
        NONE
    }

}