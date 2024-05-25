package org.blazers.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.blazers.BlazersMod;
import org.blazers.core.BLRecipeSerializers;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

/**
 * {@link BlazersMod Blazers Mod} {@link Recipe Recipe class} for a {@link Blocks#FLETCHING_TABLE Fletching Table}
 */
public class FletchingRecipe implements Recipe<Container> {

    /**
     * The {@link String Fletching Recipe Id}
     */
    public static final String ID = "fletching";
    /**
     * {@link Ingredient The base recipe ingredient}
     */
    private final Ingredient base;
    /**
     * {@link Ingredient The addition recipe ingredient}
     */
    private final Ingredient addition;
    /**
     * {@link ItemStack The recipe result}
     */
    private final ItemStack result;

    /**
     * Constructor. Set the recipe properties
     *
     * @param base {@link Ingredient The base recipe ingredient}
     * @param addition {@link Ingredient The addition recipe ingredient}
     * @param result {@link ItemStack The recipe result}
     */
    public FletchingRecipe(final Ingredient base, final Ingredient addition, final ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    /**
     * Check if the ingredients matches a recipe
     *
     * @param container {@link Container The crafting container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the ingredients matches a recipe}
     */
    @Override
    public boolean matches(final Container container, final @NotNull Level level) {
        return this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1));
    }

    /**
     * Craft an item
     *
     * @param container {@link Container The crafting container}
     * @param holderLookupProvider {@link HolderLookup.Provider The holder lookup provider reference}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack assemble(final Container container, final HolderLookup.@NotNull Provider holderLookupProvider) {
        final ItemStack itemStack = container.getItem(0).transmuteCopy(this.result.getItem(), this.result.getCount());
        itemStack.applyComponents(this.result.getComponentsPatch());
        return itemStack;
    }

    /**
     * Check if a recipe can be crafted
     *
     * @param ingredientsCount {@link Integer The number of ingredients}
     * @param resultsCount {@link Integer The number of result items}
     * @return {@link Boolean True if a recipe can be crafted}
     */
    @Override
    public boolean canCraftInDimensions(final int ingredientsCount, final int resultsCount) {
        return ingredientsCount >= 2 && resultsCount >= 1;
    }

    /**
     * Check if the {@link ItemStack Item Stack} is a {@link Ingredient base ingredient}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean True if is a base ingredient}
     */
    public boolean isBaseIngredient(final ItemStack itemStack) {
        return this.base.test(itemStack);
    }

    /**
     * Check if the {@link ItemStack Item Stack} is an {@link Ingredient addition ingredient}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean True if is an addition ingredient}
     */
    public boolean isAdditionIngredient(final ItemStack itemStack) {
        return this.addition.test(itemStack);
    }

    /**
     * Get the {@link ItemStack Item Stack} to show as a Recipe Toast Icon
     *
     * @return {@link Blocks#FLETCHING_TABLE The Fletching Table icon}
     */
    @Override
    public @NotNull ItemStack getToastSymbol() {
        return getRecipeIcon();
    }

    /**
     * Get the {@link ItemStack Recipe Icon}
     *
     * @return {@link Blocks#FLETCHING_TABLE The Fletching Table icon}
     */
    public static ItemStack getRecipeIcon() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    /**
     * Get the {@link ItemStack recipe result}
     *
     * @param holderLookupProvider {@link HolderLookup.Provider The holder lookup provider reference}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack getResultItem(final HolderLookup.@NotNull Provider holderLookupProvider) {
        return this.result;
    }

    /**
     * Get the {@link RecipeSerializer Recipe Serializer}
     *
     * @return {@link BLRecipeSerializers#FLETCHING The Fletching Recipe Serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BLRecipeSerializers.FLETCHING.get();
    }

    /**
     * Get the {@link RecipeType Recipe Type}
     *
     * @return {@link Type#INSTANCE The Fletching Recipe Type}
     */
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    /**
     * Check if the recipe is incomplete
     *
     * @return {@link Boolean True if the recipe is incomplete}
     */
    @Override
    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch(net.minecraftforge.common.ForgeHooks::hasNoElements);
    }

    /**
     * Implementation class for the {@link FletchingRecipe Fletching Recipe} Type
     */
    public static class Type implements RecipeType<FletchingRecipe> {

        /**
         * {@link Type The Recipe Type intance}
         */
        public static final Type INSTANCE = new Type();
        /**
         * {@link String The Recipe Type Id}
         */
        public static final String ID = FletchingRecipe.ID;

        /**
         * Constructor.
         */
        public Type() { }
    }

    /**
     * Implementation class for the {@link FletchingRecipe Fletching Recipe} Serializer
     */
    public static class Serializer implements RecipeSerializer<FletchingRecipe> {

        /**
         * {@link MapCodec The Recipe Serializer codec}
         */
        private static final MapCodec<FletchingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, FletchingRecipe::new)
        );
        /**
         * {@link StreamCodec The Recipe Serializer Stream codec}
         */
        public static final StreamCodec<RegistryFriendlyByteBuf, FletchingRecipe> STREAM_CODEC = StreamCodec.of(FletchingRecipe.Serializer::toNetwork, FletchingRecipe.Serializer::fromNetwork);

        /**
         * Get the {@link MapCodec Recipe Serializer codec}
         *
         * @return {@link MapCodec The Recipe Serializer codec}
         */
        @Override
        public @NotNull MapCodec<FletchingRecipe> codec() {
            return CODEC;
        }

        /**
         * Get the {@link StreamCodec Recipe Serializer Stream codec}
         *
         * @return {@link StreamCodec The Recipe Serializer Stream codec}
         */
        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FletchingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        /**
         * Deserialize a {@link FletchingRecipe Fletching Recipe} from the network
         *
         * @param buffer {@link RegistryFriendlyByteBuf The network buffer}
         * @return {@link FletchingRecipe The recipe}
         */
        private static FletchingRecipe fromNetwork(final RegistryFriendlyByteBuf buffer) {
            final Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            final Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            final ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            return new FletchingRecipe(base, addition, result);
        }

        /**
         * Serialize a {@link FletchingRecipe Fletching Recipe} to the network
         *
         * @param buffer {@link RegistryFriendlyByteBuf The network buffer}
         * @param recipe {@link FletchingRecipe The Fletching recipe}
         */
        private static void toNetwork(final RegistryFriendlyByteBuf buffer, final FletchingRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

    }

}