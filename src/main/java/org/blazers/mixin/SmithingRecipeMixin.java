package org.blazers.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.LegacySmithingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.blazers.item.IPreEnchantedItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LegacySmithingRecipe.class)
public final class SmithingRecipeMixin {

    @Inject(method = "craft(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;", at=@At("RETURN"), cancellable = true)
    public void craft(Inventory inventory, DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> infoReturnable) {
        ItemStack recipeResult = infoReturnable.getReturnValue();
        Item item = recipeResult.getItem();
        if(item instanceof IPreEnchantedItem) {
            Pair<Enchantment, Integer> itemEnchantment = ((IPreEnchantedItem)item).getEnchantment();
            EnchantmentHelper.set(Map.of(itemEnchantment.getFirst(), itemEnchantment.getSecond()), recipeResult);
        }
        infoReturnable.setReturnValue(recipeResult);
    }
}