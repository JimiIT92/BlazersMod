package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.blazers.core.BLTabs;

public class PreEnchantedArmorItem extends ArmorItem implements IPreEnchantedItem {

    private final Enchantment enchantment;
    private final int level;

    public PreEnchantedArmorItem(ArmorMaterial material, EquipmentSlot slot, Enchantment enchantment, int level) {
        super(material, slot, new FabricItemSettings().group(BLTabs.TAB_COMBAT));
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
