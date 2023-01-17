package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class PreEnchantedArmorItem extends ArmorItem implements IPreEnchantedItem {

    private final Enchantment enchantment;
    private final int level;

    public PreEnchantedArmorItem(ArmorMaterial material, EquipmentSlot slot, Enchantment enchantment, int level) {
        super(material, slot, new FabricItemSettings());
        this.enchantment = enchantment;
        this.level = level;
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

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.addEnchantment(enchantment, level);
        return itemStack;
    }
}
