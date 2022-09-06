package org.blazers.core;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;
import org.blazers.recipe.FletchingRecipe;

public final class BLRecipes {

    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(BlazersMod.MOD_ID, FletchingRecipe.Serializer.ID), FletchingRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(BlazersMod.MOD_ID, FletchingRecipe.Type.ID), FletchingRecipe.Type.INSTANCE);
    }
}