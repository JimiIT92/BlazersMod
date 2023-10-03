package org.blazers.recipe;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.blazers.core.BLRecipeSerializers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link Blocks#FLETCHING_TABLE Fletching Table} Recipe Manager
 */
public class FletchingRecipe implements Recipe<Container> {

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
     * @param base {@link Ingredient Base Recipe Ingredient}
     * @param addition {@link Ingredient Addition Recipe Ingredient}
     * @param result{@link Crafting Result}
     */
    public FletchingRecipe(Ingredient base, Ingredient addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    /**
     * Get the {@link ItemStack Item Stack} Toast Symbol
     *
     * @return {@link ItemStack Item Stack} Toast Symbol
     */
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
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
     * Check if the ingredients matches a recipe
     *
     * @param container {@link Container The crafting container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the ingredients matches a recipe}
     */
    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1));
    }

    /**
     * Craft an item
     *
     * @param container {@link Container The crafting result}
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The crafting result}
     */
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = container.getItem(0).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }

        return itemstack;
    }

    /**
     * Get the crafting result
     *
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The crafting result}
     */
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public boolean canCraftInDimensions(int ingredients, int results) {
        return ingredients >= 3 && results >= 1;
    }

    public boolean isAdditionIngredient(ItemStack itemStack) {
        return this.addition.test(itemStack);
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
    public static class Serializer implements RecipeSerializer<FletchingRecipe> {

        private static final Codec<FletchingRecipe> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
                Ingredient.CODEC.fieldOf("base").forGetter((recipe) -> recipe.base),
                Ingredient.CODEC.fieldOf("addition").forGetter((recipe) -> recipe.addition),
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter((recipe) -> recipe.result))
                .apply(builder, FletchingRecipe::new));

        public Codec<FletchingRecipe> codec() {
            return CODEC;
        }

        /**
         * Parse a {@link FriendlyByteBuf Recipe Buffer}
         *
         * @param buffer {@link FriendlyByteBuf Recipe Buffer}
         * @return {@link FletchingRecipe Recipe}
         */
        @Nullable
        @Override
        public FletchingRecipe fromNetwork(@NotNull FriendlyByteBuf buffer) {
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient additioon = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new FletchingRecipe(base, additioon, result);
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