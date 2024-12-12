package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Rarity;
import org.hendrix.helper.ItemHelper;

/**
 * Implementation class for a {@link IPreEnchantedItem pre-enchanted} {@link SwordItem Sword Item}
 */
public final class PreEnchantedSwordItem extends SwordItem implements IPreEnchantedItem {

    /**
     * The {@link Item Item} {@link Pair Enchantment and its level}
     */
    private final Pair<RegistryKey<Enchantment>, Integer> enchantment;

    /**
     * Constructor. Set the {@link Item.Settings Item properties}
     *
     * @param name The {@link String Item name}
     * @param material The {@link ToolMaterial Sword material}
     * @param enchantment The {@link Item Item} {@link Pair Enchantment and its level}
     */
    public PreEnchantedSwordItem(final String name, final ToolMaterial material, final Pair<RegistryKey<Enchantment>, Integer> enchantment) {
        super(material, 3.0F, -2.4F, ItemHelper.settings(name, Rarity.EPIC)
                .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
        );
        this.enchantment = enchantment;
    }

    /**
     * Get the {@link Item Item} {@link Pair Enchantment and its level}
     *
     * @return The {@link Item Item} {@link Pair Enchantment and its level}
     */
    @Override
    public Pair<RegistryKey<Enchantment>, Integer> getEnchantment() {
        return enchantment;
    }

    /**
     * Make the {@link Item Item} not enchantable
     *
     * @param stack The {@link ItemStack Item Stack} containing the {@link Enchantment Enchantment}
     * @param enchantment The {@link RegistryKey<Enchantment> }
     * @param context The {@link EnchantingContext Enchanting Context}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean canBeEnchantedWith(final ItemStack stack, final RegistryEntry<Enchantment> enchantment, final EnchantingContext context) {
        return false;
    }

}