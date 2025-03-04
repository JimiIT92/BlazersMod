package org.blazers.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import org.blazers.core.BLItems;
import org.blazers.item.CopperHornItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin class for the {@link ShapedRecipe Shaped Recipe}
 */
@Mixin(ShapedRecipe.class)
public final class ShapedRecipeMixin {

    /**
     * Craft a {@link BLItems#COPPER_HORN Copper Horn} based on the {@link Items#GOAT_HORN Goat Horn} {@link Instrument Instrument}
     *
     * @param recipeInput The {@link CraftingRecipeInput Recipt Input}
     * @param registryWrapperLookup The {@link RegistryWrapper.WrapperLookup Registry Wrapper Lookup}
     * @param infoReturnable The {@link CallbackInfoReturnable<ItemStack> Item Stack Callback Info Returnable}
     */
    @Inject(at = @At("RETURN"), method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", cancellable = true)
    public void craft(final CraftingRecipeInput recipeInput, final RegistryWrapper.WrapperLookup registryWrapperLookup, final CallbackInfoReturnable<ItemStack> infoReturnable) {
        final ItemStack recipeResult = infoReturnable.getReturnValue();
        if(recipeResult.isOf(BLItems.COPPER_HORN)) {
            recipeInput.getStacks().stream().filter(itemStack -> itemStack.isOf(Items.GOAT_HORN)).findFirst().ifPresent(goatHorn -> {
                RegistryEntry<Instrument> instrument = goatHorn.get(DataComponentTypes.INSTRUMENT);
                if(instrument != null) {
                    CopperHornItem.upgradeInstrument(recipeResult, registryWrapperLookup, instrument);
                }
                infoReturnable.setReturnValue(recipeResult);
            });
        }
    }

}