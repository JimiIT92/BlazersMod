package org.blazers.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
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
     * Allows the {@link ShapedRecipe ShapedRecipe} to copy JSON NBT Tags
     * into the {@link ItemStack Recipe Result}
     *
     * @param inv {@link Container Inventory}
     * @param infoReturnable {@link CallbackInfoReturnable<ItemStack> Callback Info Returnable}
     */
    @Inject(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    public void assemble(CraftingContainer inv, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> infoReturnable) {
        ItemStack recipeResult = infoReturnable.getReturnValue();
        Item item = recipeResult.getItem();
        if(item instanceof CopperHornItem) {
            ItemStack goatHorn = getGoatHorn(inv);
            if(!goatHorn.isEmpty()) {
                CopperHornItem.getInstrument(goatHorn, InstrumentTags.GOAT_HORNS).ifPresent(instrument -> CopperHornItem.upgradeInstrument(recipeResult, instrument));
            }
        }
        infoReturnable.setReturnValue(recipeResult);
    }

    /**
     * Get the {@link Items#GOAT_HORN Goat Horn} from the {@link Container Crafting Inventory}
     *
     * @param inventory {@link Container Crafting Inventory}
     * @return {@link ItemStack Goat Horn Item Stack}
     */
    private ItemStack getGoatHorn(CraftingContainer inventory) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if(itemStack.is(Items.GOAT_HORN)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }
}