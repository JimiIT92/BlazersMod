package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * Interface for Pre-Enchanted {@link Item Items}
 */
public interface IPreEnchantedItem {

    /**
     * Get the {@link Item Enchantment}
     *
     * @return {@link Item Enchantment}
     */
    Pair<Enchantment, Integer> getEnchantment();
}