package org.blazers.mixin;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.blazers.item.CopperHornItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Change the behavior of the {@link ShapedRecipe Shaped Recipe} result
 */
@Mixin(ShapedRecipe.class)
public final class ShapedRecipeMixin {

    /**
     * Allows the {@link ShapedRecipe ShapedRecipe} to read the recipe ingredient components
     *
     * @param craftingContainer {@link Container The Crafting Inventory}
     * @param provider The {@link HolderLookup.Provider Holder Lookup Provider}
     * @param infoReturnable {@link CallbackInfoReturnable<ItemStack> Callback Info Returnable}
     */
    @Inject(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    public void assemble(final CraftingContainer craftingContainer, final HolderLookup.Provider provider, final CallbackInfoReturnable<ItemStack> infoReturnable) {
        final ItemStack recipeResult = infoReturnable.getReturnValue();
        if(recipeResult.getItem() instanceof CopperHornItem) {
            final ItemStack goatHorn = craftingContainer.getItems().stream().filter(itemStack -> itemStack.is(Items.GOAT_HORN)).findFirst().orElse(ItemStack.EMPTY);
            if(!goatHorn.isEmpty()) {
                CopperHornItem.getInstrument(goatHorn, InstrumentTags.GOAT_HORNS).ifPresent(instrument -> CopperHornItem.upgradeInstrument(recipeResult, instrument));
            }
        }
        infoReturnable.setReturnValue(recipeResult);
    }

}