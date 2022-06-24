package org.blazers.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.recipe.FletchingRecipe;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link RecipeType Recipe Type}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BLRecipeTypes {

    //#region Recipe Types

    public static RecipeType<FletchingRecipe> FLETCHING;

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link RecipeType Recipe Types}
     *
     * @param event {@link RegistryEvent.Register<RecipeSerializer> Recipe Serializer Register Event}
     */
    @SubscribeEvent
    public static void registerRecipeType(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        FLETCHING = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(BlazersMod.MOD_ID, FletchingRecipe.Type.ID), FletchingRecipe.Type.INSTANCE);
    }
}