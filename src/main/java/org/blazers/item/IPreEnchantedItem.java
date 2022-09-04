package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.Enchantment;

public interface IPreEnchantedItem {

    Pair<Enchantment, Integer> getEnchantment();
}
