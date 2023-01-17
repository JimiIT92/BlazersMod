package org.blazers.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.recipe.FletchingRecipe;

public final class BLRecipes {

    public static void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(BlazersMod.MOD_ID, FletchingRecipe.Serializer.ID), FletchingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(BlazersMod.MOD_ID, FletchingRecipe.Type.ID), FletchingRecipe.Type.INSTANCE);
    }
}