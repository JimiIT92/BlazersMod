package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.blazers.recipe.FletchingRecipe;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link RecipeSerializer Recipe Serializers}
 */
public final class BLRecipeSerializers {

    //#region Registry

    /**
     * The {@link DeferredRegister<RecipeSerializer> Recipe Serializer Registry}
     */
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistryHelper.registry(ForgeRegistries.RECIPE_SERIALIZERS);

    //#endregion

    //#region Recipe Serializers

    public static final RegistryObject<RecipeSerializer<FletchingRecipe>> FLETCHING = registerRecipeSerializer(FletchingRecipe.ID, Suppliers.memoize(() -> new FletchingRecipe.Serializer()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link RecipeSerializer Recipe Serializer}
     *
     * @param name {@link String The Recipe Serializer name}
     * @param recipeSerializerSupplier {@link Supplier<RecipeSerializer> The Supplier for the Recipe Serializer}
     * @return {@link RegistryObject<RecipeSerializer> The registered Recipe Serializer}
     * @param <T> The Recipe Type
     */
    private static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> registerRecipeSerializer(final String name, final Supplier<RecipeSerializer<T>> recipeSerializerSupplier) {
        return RECIPE_SERIALIZERS.register(name, recipeSerializerSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link RecipeSerializer Recipe Serializers}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

    //#endregion

}