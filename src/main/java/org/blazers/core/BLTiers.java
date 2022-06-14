package org.blazers.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link ForgeTier Tiers}
 */
public final class BLTiers {

    //#region Tiers

    public static final ForgeTier RUBY = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(BLItems.RUBY.get()));
    public static final ForgeTier SAPPHIRE = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(BLItems.SAPPHIRE.get()));
    public static final ForgeTier EMERALD = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.EMERALD));

    public static final ForgeTier MALACHITE = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(BLItems.MALACHITE.get()));
    public static final ForgeTier ONICE = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(BLItems.ONICE.get()));
    public static final ForgeTier TOPAZ = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(BLItems.TOPAZ.get()));

    public static final ForgeTier PEARL = new ForgeTier(1, 191, 5.0F, 1.5F, 12, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(BLItems.PEARL.get()));
    public static final ForgeTier AMETHYST = new ForgeTier(1, 191, 5.0F, 1.5F, 12, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.AMETHYST_SHARD));

    public static final ForgeTier BLAZERITE = new ForgeTier(4, 2266, 10.0F, 5.0F, 20, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(BLItems.BLAZERITE.get()));
    public static final ForgeTier GYULIANITE = new ForgeTier(4, 2266, 10.0F, 5.0F, 20, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(BLItems.GYULIANITE.get()));

    public static final ForgeTier FLINT = new ForgeTier(1, 131, 4.0F, 1.0F, 5, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.FLINT));

    //#endregion

}
