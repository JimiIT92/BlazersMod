package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import org.blazers.BlazersMod;

/**
 * {@link BlazersMod Blazers Mod} {@link ForgeTier Tiers}
 */
public final class BLTiers {

    //#region Tiers

    public static final ForgeTier FLINT = new ForgeTier(131, 4.0F, 1.0F, 5, BlockTags.NEEDS_STONE_TOOL, Suppliers.memoize(() -> Ingredient.of(Items.FLINT)), BlockTags.INCORRECT_FOR_STONE_TOOL);
    public static final ForgeTier EMERALD = new ForgeTier(1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, Suppliers.memoize(() -> Ingredient.of(Items.EMERALD)), BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    public static final ForgeTier AMETHYST = new ForgeTier(191, 5.0F, 1.5F, 12, BlockTags.NEEDS_STONE_TOOL, Suppliers.memoize(() -> Ingredient.of(Items.AMETHYST_SHARD)), BlockTags.INCORRECT_FOR_STONE_TOOL);
    public static final ForgeTier SAPPHIRE = new ForgeTier(1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.SAPPHIRE.get())), BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    public static final ForgeTier TOPAZ = new ForgeTier(905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.TOPAZ.get())), BlockTags.INCORRECT_FOR_IRON_TOOL);
    public static final ForgeTier PEARL = new ForgeTier(191, 5.0F, 1.5F, 12, BlockTags.NEEDS_STONE_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.PEARL.get())), BlockTags.INCORRECT_FOR_STONE_TOOL);
    public static final ForgeTier RUBY = new ForgeTier(1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.RUBY.get())), BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    public static final ForgeTier MALACHITE = new ForgeTier(905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.MALACHITE.get())), BlockTags.INCORRECT_FOR_IRON_TOOL);
    public static final ForgeTier ONICE = new ForgeTier(905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.ONICE.get())), BlockTags.INCORRECT_FOR_IRON_TOOL);
    public static final ForgeTier CARBON = new ForgeTier(250, 8.0F, 2.0F, 14, BlockTags.NEEDS_STONE_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.CARBON.get())), BlockTags.INCORRECT_FOR_STONE_TOOL);
    public static final ForgeTier BLAZERITE = new ForgeTier(2266, 10.0F, 5.0F, 20, BlockTags.NEEDS_DIAMOND_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.BLAZERITE.get())), BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    public static final ForgeTier GYULIANITE = new ForgeTier(2266, 10.0F, 5.0F, 20, BlockTags.NEEDS_DIAMOND_TOOL, Suppliers.memoize(() -> Ingredient.of(BLItems.GYULIANITE.get())), BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

    //#endregion

}