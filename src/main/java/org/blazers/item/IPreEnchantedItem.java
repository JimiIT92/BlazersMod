package org.blazers.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.blazers.BlazersMod;

/**
 * Interface for a {@link BlazersMod Blazers Mod} {@link Item pre-enchanted Item}
 */
public interface IPreEnchantedItem {

    /**
     * Get the {@link EnchantmentInstance Enchantment Instance}
     *
     * @return {@link EnchantmentInstance The Item enchantment}
     */
    EnchantmentInstance getEnchantment();
    
}