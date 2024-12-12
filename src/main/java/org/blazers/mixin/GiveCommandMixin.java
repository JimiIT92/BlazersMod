package org.blazers.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.GiveCommand;
import org.blazers.core.BLItems;
import org.blazers.item.IPreEnchantedItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Mixin class for the {@link GiveCommand Give Command}
 */
@Mixin(GiveCommand.class)
public final class GiveCommandMixin {

    @ModifyExpressionValue(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/command/argument/ItemStackArgument;createStack(IZ)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack createStack(ItemStack original){
        if(original.getItem() instanceof IPreEnchantedItem) {
            return BLItems.BLAZERITE_SWORD.getDefaultStack();
        }
        return original;
    }

}