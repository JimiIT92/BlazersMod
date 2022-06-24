package org.blazers.recipe;


import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;
import org.blazers.core.BLRecipeSerializers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link Blocks#FLETCHING_TABLE Fletching Table} Recipe Manager
 */
public class FletchingRecipe extends UpgradeRecipe {

    /**
     * {@link FletchingRecipe Fletching Recipe} {@link String ID}
     */
    public static final String ID = "fletching";
    /**
     * {@link Ingredient Base Recipe Ingredient}
     */
    private final Ingredient base;
    /**
     * {@link Ingredient Addition Recipe Ingredient}
     */
    private final Ingredient addition;
    /**
     * {@link Ingredient Crafting Result}
     */
    private final ItemStack result;

    /**
     * Constructor. Sets the {@link FletchingRecipe Fletching Recipe} properties
     *
     * @param id {@link ResourceLocation Recipe ID}
     * @param base {@link Ingredient Base Recipe Ingredient}
     * @param addition {@link Ingredient Addition Recipe Ingredient}
     * @param result{@link Crafting Result}
     */
    public FletchingRecipe(ResourceLocation id, Ingredient base, Ingredient addition, ItemStack result) {
        super(id, base, addition, result);
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    /**
     * Get the {@link RecipeSerializer Recipe Serializer}
     *
     * @return {@link RecipeSerializer Recipe Serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BLRecipeSerializers.FLETCHING.get();
    }

    /**
     * Get the {@link RecipeType Recipe Type}
     *
     * @return {@link RecipeType Recipe Type}
     */
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    /**
     * Check if the {@link FletchingRecipe Recipe} should be hidden from the Recipe Book
     *
     * @return {@link Boolean True} if the {@link FletchingRecipe Recipe} should be hidden from the Recipe Book
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * {@link FletchingRecipe Fletching Recipe} {@link RecipeType Type}
     */
    public static class Type implements RecipeType<FletchingRecipe> {

        /**
         * {@link RecipeType Recipe Type Instance}
         */
        public static final Type INSTANCE = new Type();
        /**
         * {@link String Recipe ID}
         */
        public static final String ID = FletchingRecipe.ID;

        /**
         * Constructor
         */
        private Type() { }
    }

    /**
     * {@link FletchingRecipe Fletching Recipe} {@link Serializer Serializer}
     */
    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FletchingRecipe> {

        /**
         * Parse a {@link JsonObject Recipe JSON}
         *
         * @param recipeId {@link ResourceLocation Recipe ID}
         * @param serializedRecipe {@link JsonObject Recipe JSON}
         * @return {@link FletchingRecipe Recipe}
         */
        @Override
        public @NotNull FletchingRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject serializedRecipe) {
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(serializedRecipe, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(serializedRecipe, "addition"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result"));
            return new FletchingRecipe(recipeId, base, addition, result);
        }

        /**
         * Parse a {@link FriendlyByteBuf Recipe Buffer}
         *
         * @param recipeId {@link ResourceLocation Recipe ID}
         * @param buffer {@link FriendlyByteBuf Recipe Buffer}
         * @return {@link FletchingRecipe Recipe}
         */
        @Nullable
        @Override
        public FletchingRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient additioon = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new FletchingRecipe(recipeId, base, additioon, result);
        }

        /**
         * Write a {@link FletchingRecipe Recipe} to the {@link FriendlyByteBuf Recipe Buffer}
         *
         * @param buffer {@link FriendlyByteBuf Recipe Buffer}
         * @param recipe {@link FletchingRecipe Recipe
         */
        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull FletchingRecipe recipe) {
            recipe.base.toNetwork(buffer);
            recipe.addition.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }
}