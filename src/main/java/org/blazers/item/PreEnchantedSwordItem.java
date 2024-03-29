package org.blazers.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation class for a pre-enchanted {@link SwordItem Sword}
 */
public class PreEnchantedSwordItem extends SwordItem implements IPreEnchantedItem {

    /**
     * {@link SwordItem Sword} {@link Enchantment Enchantment}
     */
    private final Enchantment enchantment;
    /**
     * {@link Integer Enchantment Level}
     */
    private final int level;

    /**
     * Constructor. Set the {@link SwordItem Sword} {@link Tier Tier}
     * and {@link Enchantment enchantment}
     *
     * @param tier {@link Tier Item Tier}
     * @param enchantment {@link Enchantment Enchantment to apply}
     * @param level {@link Integer Enchantment level to apply}
     */
    public PreEnchantedSwordItem(Tier tier, Enchantment enchantment, int level) {
        super(tier, 3, -2.4F, new Properties().rarity(Rarity.EPIC));
        this.enchantment = enchantment;
        this.level = level;
    }

    /**
     * Get the {@link ArmorItem Armor Enchantment}ù
     *
     * @return {@link ArmorItem Armor Enchantment}ù
     */
    @Override
    public Pair<Enchantment, Integer> getEnchantment() {
        return new Pair<>(enchantment, level);
    }

    /**
     * Check if the {@link ItemStack Item Stack} should glint
     *
     * @param stack {@link ItemStack Item Stack}
     * @return {@link Boolean True} if the {@link ItemStack Item Stack} should glint
     */
    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    /**
     * Sets if the {@link SwordItem Sword} can be enchanted in an Anvil
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @param book {@link ItemStack Enchantment Book}
     * @return {@link Boolean True} if the {@link SwordItem Sword} can be enchanted in an Anvil
     */
    @Override
    public boolean isBookEnchantable(ItemStack itemStack, ItemStack book) {
        return false;
    }

    /**
     * Sets if the {@link SwordItem Sword} can be enchanted
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link Boolean True} if the {@link SwordItem Sword} can be enchanted
     */
    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * Sets if the {@link SwordItem Sword} can be enchanted in an Enchanting Table
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @param enchantment {@link Enchantment The Enchantment to apply}
     * @return {@link Boolean True} if the {@link SwordItem Sword} can be enchanted in an Enchanting Table
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
        return false;
    }

    /**
     * Get the Default {@link ItemStack Item Stack}
     *
     * @return Default {@link ItemStack Item Stack}
     */
    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        stack.enchant(enchantment, level);
        return stack;
    }
}