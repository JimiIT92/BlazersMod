package org.blazers.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.blazers.core.BLTabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Implementation class for a pre-enchanted {@link SwordItem Sword}
 */
public class PreEnchantedSwordItem extends SwordItem {

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
        super(tier, 3, -2.4F, new Properties().tab(BLTabs.TAB_COMBAT).rarity(Rarity.EPIC));
        this.enchantment = enchantment;
        this.level = level;
    }

    /**
     * Enchant the {@link SwordItem Sword Item} when is crafted
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @param level {@link Level World reference}
     * @param player {@link Player The Player} who crafted the {@link SwordItem Sword}
     */
    @Override
    public void onCraftedBy(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Player player) {
        enchant(itemStack);
        super.onCraftedBy(itemStack, level, player);
    }

    /**
     * Enchant the {@link SwordItem} in inventory
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @param level {@link Level World reference}
     * @param entity {@link Entity Entity reference}
     * @param slotId {@link Integer Slot ID}
     * @param isSelected {@link Boolean If the slot is being highlighted}
     */
    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        enchant(itemStack);
        super.inventoryTick(itemStack, level, entity, slotId, isSelected);
    }

    /**
     * Enchant the {@link SwordItem Sword}
     *
     * @param itemStack {@link ItemStack Item Stack}
     */
    private void enchant(ItemStack itemStack) {
        if(!itemStack.isEnchanted()) {
            itemStack.enchant(enchantment, level);
        }
    }

    /**
     * Get the {@link ListTag Enchantment Tag}
     * for the {@link SwordItem Item} tooltip
     *
     * @return {@link ListTag Enchantment Tag}
     */
    private ListTag getEnchantmentTag() {
        ListTag compoundtag = new ListTag();
        CompoundTag enchantmentTag = new CompoundTag();
        enchantmentTag.putString("id", String.valueOf(this.enchantment.getRegistryName()));
        enchantmentTag.putInt("lvl", this.level);
        compoundtag.add(enchantmentTag);
        return compoundtag;
    }

    /**
     * Shows the {@link ListTag Enchantment Tag} on tooltip
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @param level {@link Level World reference}
     * @param tooltip {@link List<Component> Tooltip}
     * @param isAdvanced {@link Boolean If is showing advanced tooltips}
     */
    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
        if(!itemStack.isEnchanted()) {
            ItemStack.appendEnchantmentNames(tooltip, getEnchantmentTag());
        }
    }

    /**
     * Apply {@link SwordItem Item} glint
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link Boolean True} if the {@link SwordItem Sword} should glint
     */
    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
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
}