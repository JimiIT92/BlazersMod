package org.blazers.item;

import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import org.blazers.core.BLItems;
import org.blazers.core.BLTabs;
import org.jetbrains.annotations.NotNull;

public class CopperHornItem extends InstrumentItem {

    private final TagKey<Instrument> instruments;

    public CopperHornItem(TagKey<Instrument> instrument) {
        super(new Item.Properties().tab(BLTabs.TAB_MISC).stacksTo(1), instrument);
        this.instruments = instrument;
    }

    public void fillItemCategory(@NotNull CreativeModeTab creativeTab, @NotNull NonNullList<ItemStack> items) {
        if (this.allowedIn(creativeTab)) {
            for(Holder<Instrument> holder : Registry.INSTRUMENT.getTagOrEmpty(this.instruments)) {
                items.add(create(BLItems.COPPER_HORN.get(), holder));
            }
        }

    }
}
