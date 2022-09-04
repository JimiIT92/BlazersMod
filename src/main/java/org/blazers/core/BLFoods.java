package org.blazers.core;

import net.minecraft.item.FoodComponent;

public final class BLFoods {

    public static final FoodComponent HOSOMAKI = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
    public static final FoodComponent NIGIRI = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).build();
    public static final FoodComponent SASHIMI = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).build();

}