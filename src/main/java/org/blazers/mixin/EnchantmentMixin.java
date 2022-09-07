package org.blazers.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.blazers.item.IPreEnchantedItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public final class EnchantmentMixin {

    @Inject(method = "isAcceptableItem", at=@At("HEAD"), cancellable = true)
    public void isAccettableItem(ItemStack stack, CallbackInfoReturnable<Boolean> infoReturnable) {
        if(stack.getItem() instanceof IPreEnchantedItem) {
            infoReturnable.setReturnValue(false);
        }
    }
}