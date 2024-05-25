package org.blazers.item;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link ArmorItem pre-enchanted Armor Item}
 */
public final class PreEnchantedArmorItem extends ArmorItem implements IPreEnchantedItem {

    /**
     * {@link EnchantmentInstance The Item enchantment}
     */
    private final EnchantmentInstance enchantment;

    /**
     * Constructor. Set the {@link Properties Item properties}
     *
     * @param armorMaterialSupplier {@link Supplier<ArmorMaterial> The armor material supplier}
     * @param armorType {@link ArmorItem.Type The Armor Item type}
     * @param enchantment {@link EnchantmentInstance The Item enchantment}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public PreEnchantedArmorItem(final RegistryObject<ArmorMaterial> armorMaterialSupplier, final ArmorItem.Type armorType, final EnchantmentInstance enchantment, final FeatureFlag... featureFlags) {
        super(armorMaterialSupplier.getHolder().orElseThrow(), armorType, PropertyHelper.armorItem(armorType, 39, featureFlags));
        this.enchantment = enchantment;
    }

    /**
     * Get the {@link EnchantmentInstance Item enchantment}
     *
     * @return {@link EnchantmentInstance The Item enchantment}
     */
    @Override
    public EnchantmentInstance getEnchantment() {
        return this.enchantment;
    }

    /**
     * Make the {@link ItemStack ItemStack} glint
     *
     * @param itemStack {@link ItemStack The current ItemStack}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFoil(final @NotNull ItemStack itemStack) {
        return true;
    }

    /**
     * Check if the {@link Item Item} can be enchanted with {@link EnchantedBookItem enchanted books}
     *
     * @param itemStack {@link ItemStack The current ItemStack}
     * @param book {@link ItemStack The enchanted book ItemStack}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isBookEnchantable(final ItemStack itemStack, final ItemStack book) {
        return false;
    }

    /**
     * Check if the {@link Item Item} can be enchanted at the {@link EnchantingTableBlock Enchanting Table}
     *
     * @param itemStack {@link ItemStack The current ItemStack}
     * @param enchantment {@link Enchantment The enchantment to apply}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean canApplyAtEnchantingTable(final ItemStack itemStack, final Enchantment enchantment) {
        return false;
    }

    /**
     * Get the default {@link ItemStack Item Stack}
     *
     * @return {@link ItemStack The default Item Stack}
     */
    @Override
    public @NotNull ItemStack getDefaultInstance() {
        final ItemStack itemStack = super.getDefaultInstance();
        itemStack.enchant(this.enchantment.enchantment, this.enchantment.level);
        return itemStack;
    }

}