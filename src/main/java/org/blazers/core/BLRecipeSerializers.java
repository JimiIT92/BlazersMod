package org.blazers.core;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.recipe.FletchingRecipe;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link RecipeSerializer Recipes}
 */
public final class BLRecipeSerializers {

    /**
     * {@link Block Blocks} {@link DeferredRegister<RecipeSerializer> Recipe Serializers}
     */
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BlazersMod.MOD_ID);

    //#region Recipes

    public static final RegistryObject<RecipeSerializer<FletchingRecipe>> FLETCHING = RECIPE_SERIALIZERS.register(FletchingRecipe.ID, FletchingRecipe.Serializer::new);

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link RecipeSerializer Recipe Serializers}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}