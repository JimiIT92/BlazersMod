package org.blazers.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.blazers.item.IPreEnchantedItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * Change the behavior of the {@link SmithingTransformRecipe Upgrade Recipe} result
 */
@Mixin(SmithingTransformRecipe.class)
public final class UpgradeRecipeMixin {

    /**
     * Allows the {@link SmithingTransformRecipe UpgradeRecipe} to copy JSON NBT Tags
     * into the {@link ItemStack Recipe Result}
     *
     * @param inv {@link Container Inventory}
     * @param infoReturnable {@link CallbackInfoReturnable<ItemStack> Callback Info Returnable}
     */
    @Inject(method = "assemble(Lnet/minecraft/world/Container;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    public void assemble(Container inv, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> infoReturnable) {
        ItemStack recipeResult = infoReturnable.getReturnValue();
        Item item = recipeResult.getItem();
        if(item instanceof IPreEnchantedItem) {
            Pair<Enchantment, Integer> itemEnchantment = ((IPreEnchantedItem)item).getEnchantment();
            EnchantmentHelper.setEnchantments(Map.of(itemEnchantment.getFirst(), itemEnchantment.getSecond()), recipeResult);
        }
        infoReturnable.setReturnValue(recipeResult);
    }
}