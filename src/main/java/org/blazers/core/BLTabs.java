package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

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

    public static final CreativeModeTab TAB_BUILDING_BLOCKS = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable(BlazersMod.MOD_ID + ".building_blocks"))
            .icon(() -> new ItemStack(BLBlocks.SAPPHIRE_ORE.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.COBBLESTONES,
                        BLTabSortGroups.OVERWORLD_ORES,
                        BLTabSortGroups.NETHER_ORES,
                        BLTabSortGroups.RAW_STORAGE_BLOCKS,
                        BLTabSortGroups.STORAGE_BLOCKS,
                        BLTabSortGroups.COPPER_BLOCKS,
                        BLTabSortGroups.HOLLOW_LOGS,
                        BLTabSortGroups.HOLLOW_STRIPPED_LOGS,
                        BLTabSortGroups.CUT_BRICKS);
            })
            .build();

    public static final CreativeModeTab TAB_DECORATIONS = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .title(Component.translatable(BlazersMod.MOD_ID + ".decorations"))
            .icon(() -> new ItemStack(BLBlocks.CATTAIL.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.PLANTS, BLTabSortGroups.DRIPSTONES);
            })
            .build();

    public static final CreativeModeTab TAB_REDSTONE = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 2)
            .title(Component.translatable(BlazersMod.MOD_ID + ".redstone"))
            .icon(() -> new ItemStack(BLBlocks.COPPER_BUTTON.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.TNT, BLTabSortGroups.BUTTONS);
            })
            .build();

    public static final CreativeModeTab TAB_TOOLS = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
                    .title(Component.translatable(BlazersMod.MOD_ID + ".tools"))
                    .icon(() -> new ItemStack(BLItems.EMERALD_PICKAXE.get()))
                    .displayItems((featureFlagSet, output, op) -> {
                        sortByTabGroups(output,
                                BLTabSortGroups.TOOLS);
                    })
                    .build();

    public static final CreativeModeTab TAB_COMBAT = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
            .title(Component.translatable(BlazersMod.MOD_ID + ".combat"))
            .icon(() -> new ItemStack(BLItems.KATANA.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.BOWS, BLTabSortGroups.SWORDS, BLTabSortGroups.WEAPONS, BLTabSortGroups.KATANAS, BLTabSortGroups.ARMORS, BLTabSortGroups.SPEARS);
            })
            .build();

    public static final CreativeModeTab TAB_FOOD = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 1)
            .title(Component.translatable(BlazersMod.MOD_ID + ".food"))
            .icon(() -> new ItemStack(BLItems.SASHIMI.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.FOOD);
            })
            .build();

    public static final CreativeModeTab TAB_MISC = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 2)
            .title(Component.translatable(BlazersMod.MOD_ID + ".misc"))
            .icon(() -> new ItemStack(BLItems.RUBY.get()))
            .displayItems((featureFlagSet, output, op) -> {
                sortByTabGroups(output,
                        BLTabSortGroups.GEMS, BLTabSortGroups.RAW_GEMS_AND_INGOTS, BLTabSortGroups.MATERIALS, BLTabSortGroups.NUGGETS, BLTabSortGroups.SPAWN_EGGS, BLTabSortGroups.HORSE_ARMORS);
            })
            .build();

    /**
     * Sort the {@link NonNullList<ItemStack> Creative Tab Items List} by some {@link BLTabSortGroups Tab Sort Groups}
     *
     * @param output {@link NonNullList<ItemStack> Creative Tab Items List}
     * @param sortGroups {@link BLTabSortGroups Tab Sort Groups}
     */
    private static void sortByTabGroups(CreativeModeTab.Output output, BLTabSortGroups... sortGroups) {
        if (sortGroups == null || sortGroups.length == 0) {
            return;
        }
        //output.acceptAll(TAB_GROUP_ORDERINGS.stream().filter(a -> a.getValue().equals(BLTabSortGroups.COBBLESTONES)).map(Map.Entry::getKey).collect(Collectors.toList()));
        List<BLTabSortGroups> sortGroupList = new ArrayList<>(Arrays.stream(sortGroups).sorted().toList());
        Collections.reverse(sortGroupList);
        sortGroupList.forEach(sortGroup -> {
            if(sortGroup.equals(BLTabSortGroups.COPPER_HORNS)) {
                for(Holder<Instrument> holder : BuiltInRegistries.INSTRUMENT.getTagOrEmpty(BLTags.Instruments.MELODY_COPPER_HORNS)) {
                    output.accept(InstrumentItem.create(BLItems.COPPER_HORN.get(), holder), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            } else {
                List<Map.Entry<RegistryObject<?>, BLTabSortGroups>> tabGroupItems = new ArrayList<>(TAB_GROUP_ORDERINGS.stream()
                        .filter(x -> x.getValue().equals(sortGroup)).toList());
                Collections.reverse(tabGroupItems);
                tabGroupItems.forEach(item -> {
                    if(item.getKey().get() instanceof Block) {
                        output.accept(new BlockItem(((RegistryObject<Block>) item.getKey()).get(), new Item.Properties()));
                    } else {
                        output.accept(((RegistryObject<Item>) item.getKey()).get());
                    }
                });
            }
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
     * Register the {@link BlazersMod Blazers Mod} {@link CreativeModeTab Creative Tabs}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {

    }

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
        HALF_SLABS,
        CUT_BRICKS,
        STAIRS,
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