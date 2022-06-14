package org.blazers.core;

import net.minecraft.world.food.FoodProperties;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link FoodProperties Foods}
 */
public final class BLFoods {

    //#region Foods

    public static final FoodProperties HOSOMAKI = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties NIGIRI = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties SASHIMI = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).build();

    //#endregion

}