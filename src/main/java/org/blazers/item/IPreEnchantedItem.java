package org.blazers.item;

import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;

/**
 * Interface for a pre-enchanted {@link Item Item}
 */
public interface IPreEnchantedItem {

    /**
     * Get the {@link Item Item} {@link EnchantmentLevelEntry Enchantment and its level
     *
     * @return The {@link Item Item} {@link EnchantmentLevelEntry Enchantment and its level
     */
    EnchantmentLevelEntry getEnchantment();

}