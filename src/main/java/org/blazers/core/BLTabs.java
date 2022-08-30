package org.blazers.core;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class BLTabs {

    /**
     * Items ordering in {@link CreativeModeTab Creative Tabs}
     */
    public static final List<Map.Entry<RegistryObject<?>, BLTabSortGroups>> TAB_GROUP_ORDERINGS = new ArrayList<>();

    //#region Creative Mode Tabs

    public static final CreativeModeTab TAB_BUILDING_BLOCKS = new CreativeModeTab(BlazersMod.MOD_ID + ".building_blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLBlocks.SAPPHIRE_ORE.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items,
                    BLTabSortGroups.COBBLESTONES,
                    BLTabSortGroups.OVERWORLD_ORES,
                    BLTabSortGroups.NETHER_ORES,
                    BLTabSortGroups.RAW_STORAGE_BLOCKS,
                    BLTabSortGroups.STORAGE_BLOCKS,
                    BLTabSortGroups.COPPER_BLOCKS,
                    BLTabSortGroups.HOLLOW_LOGS,
                    BLTabSortGroups.HOLLOW_STRIPPED_LOGS,
                    BLTabSortGroups.CUT_BRICKS);
        }
    };

    public static final CreativeModeTab TAB_DECORATIONS = new CreativeModeTab(BlazersMod.MOD_ID + ".decorations") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLBlocks.CATTAIL.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.PLANTS, BLTabSortGroups.DRIPSTONES);
        }
    };

    public static final CreativeModeTab TAB_REDSTONE = new CreativeModeTab(BlazersMod.MOD_ID + ".redstone") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLBlocks.COPPER_BUTTON.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.TNT, BLTabSortGroups.BUTTONS);
        }
    };

    public static final CreativeModeTab TAB_TOOLS = new CreativeModeTab(BlazersMod.MOD_ID + ".tools") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.EMERALD_PICKAXE.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.TOOLS);
        }
    };

    public static final CreativeModeTab TAB_COMBAT = new CreativeModeTab(BlazersMod.MOD_ID + ".combat") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.KATANA.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.BOWS, BLTabSortGroups.SWORDS, BLTabSortGroups.WEAPONS, BLTabSortGroups.KATANAS, BLTabSortGroups.ARMORS, BLTabSortGroups.SPEARS);
        }
    };

    public static final CreativeModeTab TAB_FOOD = new CreativeModeTab(BlazersMod.MOD_ID + ".food") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.SASHIMI.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.FOOD);
        }
    };

    public static final CreativeModeTab TAB_MISC = new CreativeModeTab(BlazersMod.MOD_ID + ".misc") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.RUBY.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            sortByTabGroups(items, BLTabSortGroups.GEMS, BLTabSortGroups.RAW_GEMS_AND_INGOTS, BLTabSortGroups.MATERIALS, BLTabSortGroups.NUGGETS, BLTabSortGroups.SPAWN_EGGS, BLTabSortGroups.HORSE_ARMORS);
        }
    };

    /**
     * Sort a {@link RegistryObject<Block> Block} inside the {@link NonNullList<ItemStack> Creative Tab Items List}
     *
     * @param items {@link NonNullList<ItemStack> Creative Tab Items List}
     * @param block {@link RegistryObject<Block> Block}
     */
    private static void sortBlock(NonNullList<ItemStack> items, RegistryObject<Block> block) {
        ItemStack itemStack = getItemStack(block);
        items.remove(items.stream().filter(item -> item.getItem().equals(itemStack.getItem())).findFirst().get());
        items.add(0, itemStack);
    }

    /**
     * Sort a {@link RegistryObject<Item> Item} inside the {@link NonNullList<ItemStack> Creative Tab Items List}
     *
     * @param items {@link NonNullList<ItemStack> Creative Tab Items List}
     * @param item {@link RegistryObject<Item> Item}
     */
    private static void sortItem(NonNullList<ItemStack> items, RegistryObject<Item> item) {
        ItemStack itemStack = item.get().getDefaultInstance();
        items.remove(items.stream().filter(itemStack1 -> itemStack1.getItem().equals(itemStack.getItem())).findFirst().get());
        items.add(0, itemStack);
    }

    /**
     * Sort the {@link NonNullList<ItemStack> Creative Tab Items List} by some {@link BLTabSortGroups Tab Sort Groups}
     *
     * @param items {@link NonNullList<ItemStack> Creative Tab Items List}
     * @param sortGroups {@link BLTabSortGroups Tab Sort Groups}
     */
    private static void sortByTabGroups(NonNullList<ItemStack> items, BLTabSortGroups... sortGroups) {
        if (sortGroups == null || sortGroups.length == 0) {
            return;
        }
        List<BLTabSortGroups> sortGroupList = new ArrayList<>(Arrays.stream(sortGroups).sorted().toList());
        Collections.reverse(sortGroupList);
        sortGroupList.forEach(sortGroup -> {
            List<Map.Entry<RegistryObject<?>, BLTabSortGroups>> tabGroupItems = new ArrayList<>(TAB_GROUP_ORDERINGS.stream()
                    .filter(x -> x.getValue().equals(sortGroup)).toList());
            Collections.reverse(tabGroupItems);
            tabGroupItems.forEach(item -> {
                if(item.getKey().get() instanceof Block) {
                    sortBlock(items, (RegistryObject<Block>) item.getKey());
                } else {
                    sortItem(items, (RegistryObject<Item>) item.getKey());
                }
            });
        });
    }

    /**
     * Get an {@link ItemStack Item Stack} for a {@link RegistryObject<Block> Block}
     *
     * @param block {@link RegistryObject<Block> Block}
     * @return {@link ItemStack Item Stack}
     */
    private static ItemStack getItemStack(RegistryObject<Block> block) {
        return block.get().asItem().getDefaultInstance();
    }

    //#endregion

    /**
     * {@link CreativeModeTab Creative Tab} Sort Groups
     */
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