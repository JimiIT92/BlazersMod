package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

/**
 * Interface for a pre-enchanted {@link Item Item}
 */
public interface IPreEnchantedItem {

    /**
     * Get the {@link Item Item} {@link Pair Enchantment and its level}
     *
     * @return The {@link Item Item} {@link Pair Enchantment and its level}
     */
    Pair<RegistryKey<Enchantment>, Integer> getEnchantment();

    /**
     * Get the {@link ItemStack Item Stack} of a {@link IPreEnchantedItem pre-enchanted Item}
     *
     * @param item The {@link Item Item}
     * @param registryWrapper the {@link RegistryWrapper.WrapperLookup Registry Wrapper Lookup}
     * @return The {@link ItemStack enchanted Item Stack}
     */
    default ItemStack getItemStack(final Item item, final RegistryWrapper.WrapperLookup registryWrapper) {
        final ItemStack stack = item.getDefaultStack();
        if(item instanceof IPreEnchantedItem) {
            registryWrapper.getOptional(RegistryKeys.ENCHANTMENT)
                    .flatMap(registry -> registry.getOptional(getEnchantment().getFirst()))
                    .ifPresent(enchantment -> stack.addEnchantment(enchantment, getEnchantment().getSecond()));
        }
        return stack;
    }

}