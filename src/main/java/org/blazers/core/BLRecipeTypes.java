package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.blazers.recipe.FletchingRecipe;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link RecipeType Recipe Types}
 */
public final class BLRecipeTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<RecipeType> Recipe Type Registry}
     */
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = RegistryHelper.registry(ForgeRegistries.RECIPE_TYPES);

    //#endregion

    //#region Recipe Types

    public static final RegistryObject<RecipeType<FletchingRecipe>> FLETCHING = registerRecipeType(FletchingRecipe.ID, Suppliers.memoize(() -> FletchingRecipe.Type.INSTANCE));

    //#endregion

    //#region Methods

    /**
     * Register a {@link RecipeType Recipe Type}
     *
     * @param name {@link String The Recipe Type name}
     * @param recipeTypeSupplier {@link Supplier<RecipeType> The Supplier for the Recipe Type}
     * @return {@link RegistryObject<RecipeType> The registered Recipe Type}
     * @param <T> The Recipe Type
     */
    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> registerRecipeType(final String name, final Supplier<RecipeType<T>> recipeTypeSupplier) {
        return RECIPE_TYPES.register(name, recipeTypeSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link RecipeBookCategories Recipe Book Categories}
     */
    public static void registerRecipeBookCategories() {
        RecipeBookCategories.create(RegistryHelper.location(FletchingRecipe.ID).toString(), FletchingRecipe.getRecipeIcon());
    }

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link RecipeType Recipe Types}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

    //#endregion

}