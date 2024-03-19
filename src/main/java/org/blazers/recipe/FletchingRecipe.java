package org.blazers.recipe;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import org.blazers.item.IPreEnchantedItem;

import java.util.Map;

public class FletchingRecipe implements Recipe<Inventory> {

    public static final String ID = "fletching";
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;

    public FletchingRecipe(Ingredient base, Ingredient addition, ItemStack result) {

        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack recipeResult = this.result.copy();
        Item item = recipeResult.getItem();
        if(item instanceof IPreEnchantedItem) {
            Pair<Enchantment, Integer> itemEnchantment = ((IPreEnchantedItem)item).getEnchantment();
            EnchantmentHelper.set(Map.of(itemEnchantment.getFirst(), itemEnchantment.getSecond()), recipeResult);
        }
        return recipeResult;
    }

    public boolean matches(Inventory inventory, World world) {
        return this.base.test(inventory.getStack(0)) && this.addition.test(inventory.getStack(1));
    }

    public boolean fits(int width, int height) {
        return width >= 2 && height >= 1;
    }

    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    public static class Type implements RecipeType<FletchingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = FletchingRecipe.ID;
    }

    public static class Serializer  implements RecipeSerializer<FletchingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = FletchingRecipe.ID;

        private static final Codec<FletchingRecipe> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter((recipe) -> {
                return recipe.base;
            }), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter((recipe) -> {
                return recipe.addition;
            }), ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter((recipe) -> {
                return recipe.result;
            })).apply(instance, FletchingRecipe::new);
        });

        public Codec<FletchingRecipe> codec() {
            return CODEC;
        }

        @Override
        public FletchingRecipe read(PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new FletchingRecipe(ingredient, ingredient2, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, FletchingRecipe fletchingRecipe) {
            fletchingRecipe.base.write(packetByteBuf);
            fletchingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(fletchingRecipe.result);
        }

    }
}
