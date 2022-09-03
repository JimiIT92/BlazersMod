package org.blazers.tab;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.blazers.BlazersMod;
import org.blazers.core.BLTabs;

import java.util.*;
import java.util.function.Supplier;

import static org.blazers.core.BLTabs.TAB_GROUP_ORDERINGS;

public class SortedItemGroup extends ItemGroup {

    private final Supplier<ItemStack> item;
    private final BLTabs.BLTabSortGroups[] sortGroups;

    public SortedItemGroup(final String name, final Supplier<ItemStack> item, final BLTabs.BLTabSortGroups... sortGroups) {
        super(ItemGroup.GROUPS.length - 1, String.format("%s.%s", BlazersMod.MOD_ID, name));
        this.item = item;
        this.sortGroups = sortGroups;
    }

    @Override
    public ItemStack createIcon() {
        return item.get();
    }

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        super.appendStacks(stacks);
        if (sortGroups == null || sortGroups.length == 0) {
            return;
        }
        List<BLTabs.BLTabSortGroups> sortGroupList = new ArrayList<>(Arrays.stream(sortGroups).sorted().toList());
        Collections.reverse(sortGroupList);
        sortGroupList.forEach(sortGroup -> {
            List<Map.Entry<Item, BLTabs.BLTabSortGroups>> tabGroupItems = new ArrayList<>(TAB_GROUP_ORDERINGS.stream()
                    .filter(x -> x.getValue().equals(sortGroup)).toList());
            Collections.reverse(tabGroupItems);
            tabGroupItems.forEach(item -> {
                ItemStack itemStack = item.getKey().getDefaultStack();
                stacks.remove(stacks.stream().filter(itemStack1 -> itemStack1.getItem().equals(itemStack.getItem())).findFirst().get());
                stacks.add(0, itemStack);
            });
        });
    }
}
