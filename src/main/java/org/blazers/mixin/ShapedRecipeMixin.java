package org.blazers.mixin;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.tag.InstrumentTags;
import net.minecraft.util.collection.DefaultedList;
import org.blazers.item.CopperHornItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public final class ShapedRecipeMixin {

    ShapedRecipe recipe = (ShapedRecipe) (Object)this;

    @Inject(method = "craft(Lnet/minecraft/inventory/CraftingInventory;)Lnet/minecraft/item/ItemStack;", at=@At("RETURN"), cancellable = true)
    public void craft(CraftingInventory inventory, CallbackInfoReturnable<ItemStack> infoReturnable) {
        ItemStack recipeResult = infoReturnable.getReturnValue();
        Item item = recipeResult.getItem();
        if(item instanceof CopperHornItem) {
            ItemStack goatHorn = getGoatHorn(inventory);
            if(!goatHorn.isEmpty()) {
                CopperHornItem.getInstrument(goatHorn, InstrumentTags.GOAT_HORNS).ifPresent(instrument -> CopperHornItem.upgradeInstrument(recipeResult, instrument));
            }
        } else if(recipeResult.isOf(Items.ARMOR_STAND)) {
            DefaultedList<Ingredient> ingredients = recipe.getIngredients();
            boolean showArms = ingredients.stream().filter(ingredient -> ingredient.test(Items.STICK.getDefaultStack())).count() == 2;
            boolean small = ingredients.stream().anyMatch(ingredient -> ingredient.test(Items.IRON_INGOT.getDefaultStack()));
            NbtCompound nbt = recipeResult.getOrCreateNbt();
            NbtCompound entityTags = nbt.getCompound("EntityTag");
            entityTags.putBoolean("Small", small);
            entityTags.putBoolean("ShowArms", showArms);
            nbt.put("EntityTag", entityTags);
            recipeResult.setNbt(nbt);
        }
        infoReturnable.setReturnValue(recipeResult);
    }

    private ItemStack getGoatHorn(CraftingInventory inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack itemStack = inventory.getStack(i);
            if(itemStack.isOf(Items.GOAT_HORN)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }
}