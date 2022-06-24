package org.blazers.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.blazers.item.IPreEnchantedItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * Change the behavior of the {@link UpgradeRecipe Upgrade Recipe} result
 */
@Mixin(UpgradeRecipe.class)
public final class UpgradeRecipeMixin {

    @Shadow @Final private ResourceLocation id;
    /**
     * {@link UpgradeRecipe UpgradeRecipe Class Instance}
     */
    private final UpgradeRecipe UPGRADE_RECIPE = (UpgradeRecipe)(Object)this;

    /**
     * Allows the {@link UpgradeRecipe UpgradeRecipe} to copy JSON NBT Tags
     * into the {@link ItemStack Recipe Result}
     *
     * @param inv {@link Container Inventory}
     * @param infoReturnable {@link CallbackInfoReturnable<ItemStack> Callback Info Returnable}
     */
    @Inject(method = "assemble(Lnet/minecraft/world/Container;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    public void assemble(Container inv, CallbackInfoReturnable<ItemStack> infoReturnable) {
        ItemStack recipeResult = infoReturnable.getReturnValue();
        Item item = recipeResult.getItem();
        if(item instanceof IPreEnchantedItem) {
            Pair<Enchantment, Integer> itemEnchantment = ((IPreEnchantedItem)item).getEnchantment();
            EnchantmentHelper.setEnchantments(Map.of(itemEnchantment.getFirst(), itemEnchantment.getSecond()), recipeResult);
        }
        infoReturnable.setReturnValue(recipeResult);
    }
}