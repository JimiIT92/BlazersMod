package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.collection.DefaultedList;
import org.blazers.core.BLTabs;

public class PreEnchantedSwordItem extends SwordItem implements IPreEnchantedItem {

    private final Enchantment enchantment;
    private final int level;

    public PreEnchantedSwordItem(ToolMaterial toolMaterial, Enchantment enchantment, int level) {
        super(toolMaterial, 3, -2.4F, new FabricItemSettings().group(BLTabs.TAB_COMBAT).rarity(Rarity.EPIC));
        this.enchantment = enchantment;
        this.level = level;
    }

    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if(isIn(group)) {
            ItemStack itemStack = new ItemStack(this);
            itemStack.addEnchantment(enchantment, level);
            stacks.add(itemStack);
        }
    }

    @Override
    public Pair<Enchantment, Integer> getEnchantment() {
        return new Pair<>(enchantment, level);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
