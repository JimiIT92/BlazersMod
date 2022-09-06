package org.blazers.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class FletchingRecipe extends SmithingRecipe {

    public static final String ID = "fletching";
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;

    public FletchingRecipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
        super(id, base, addition, result);
        this.base = base;
        this.addition = addition;
        this.result = result;
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

    public static class Type implements RecipeType<FletchingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = FletchingRecipe.ID;
    }

    public static class Serializer  implements RecipeSerializer<FletchingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = FletchingRecipe.ID;

        @Override
        public FletchingRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new FletchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public FletchingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new FletchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, FletchingRecipe fletchingRecipe) {
            fletchingRecipe.base.write(packetByteBuf);
            fletchingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(fletchingRecipe.result);
        }

    }
}
