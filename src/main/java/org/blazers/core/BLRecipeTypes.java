package org.blazers.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.blazers.BlazersMod;
import org.blazers.recipe.FletchingRecipe;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link RecipeType Recipe Type}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BLRecipeTypes {

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link RecipeType Recipe Types}
     *
     * @param event {@link RegisterEvent Recipe Serializer Register Event}
     */
    @SubscribeEvent
    public static void registerRecipeTypes(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper ->
                helper.register(new ResourceLocation(BlazersMod.MOD_ID, FletchingRecipe.Type.ID), FletchingRecipe.Type.INSTANCE));
    }
}