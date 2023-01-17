package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Rarity;

public class PreEnchantedSwordItem extends SwordItem implements IPreEnchantedItem {

    private final Enchantment enchantment;
    private final int level;

    public PreEnchantedSwordItem(ToolMaterial toolMaterial, Enchantment enchantment, int level) {
        super(toolMaterial, 3, -2.4F, new FabricItemSettings().rarity(Rarity.EPIC));
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
