package org.blazers.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.blazers.BlazersMod;

/**
 * {@link BlazersMod Blazers Mod} {@link ToolMaterial Tool Materials}
 */
public final class BLToolMaterials {

    //#region Tool Materials

    public static final ToolMaterial SAPPHIRE = gemMaterial(BLTags.SAPPHIRE_TOOL_MATERIALS);
    public static final ToolMaterial RUBY = gemMaterial(BLTags.RUBY_TOOL_MATERIALS);
    public static final ToolMaterial EMERALD = gemMaterial(BLTags.EMERALD_TOOL_MATERIALS);
    public static final ToolMaterial MALACHITE = rockMaterial(BLTags.MALACHITE_TOOL_MATERIALS);
    public static final ToolMaterial ONICE = rockMaterial(BLTags.ONICE_TOOL_MATERIALS);
    public static final ToolMaterial TOPAZ = rockMaterial(BLTags.TOPAZ_TOOL_MATERIALS);
    public static final ToolMaterial PEARL = crystalMaterial(BLTags.PEARL_TOOL_MATERIALS);
    public static final ToolMaterial AMETHYST = crystalMaterial(BLTags.AMETHYST_TOOL_MATERIALS);
    public static final ToolMaterial BLAZERITE = blazerMaterial(BLTags.BLAZERITE_TOOL_MATERIALS);
    public static final ToolMaterial GYULIANITE = blazerMaterial(BLTags.GYULIANITE_TOOL_MATERIALS);
    public static final ToolMaterial FLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, BLTags.FLINT_TOOL_MATERIALS);
    public static final ToolMaterial CARBON = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 250, 8.0F, 2.0F, 14, BLTags.CARBON_TOOL_MATERIALS);

    //#endregion

    /**
     * Create the {@link ToolMaterial Tool Material} for a gem
     *
     * @param repairItems The {@link TagKey<Item> Repair Items Tag}
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial gemMaterial(final TagKey<Item> repairItems) {
        return new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1796, 8.5F, 3.5F, 13, repairItems);
    }

    /**
     * Create the {@link ToolMaterial Tool Material} for a rock
     *
     * @param repairItems The {@link TagKey<Item> Repair Items Tag}
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial rockMaterial(final TagKey<Item> repairItems) {
        return new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 905, 7.0F, 2.5F, 12, repairItems);
    }

    /**
     * Create the {@link ToolMaterial Tool Material} for a crystal
     *
     * @param repairItems The {@link TagKey<Item> Repair Items Tag}
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial crystalMaterial(final TagKey<Item> repairItems) {
        return new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 191, 5.0F, 1.5F, 12, repairItems);
    }

    /**
     * Create the {@link ToolMaterial Tool Material} for {@link BLItems#BLAZERITE Blazerite} or {@link BLItems#GYULIANITE Gyulianite} tools
     *
     * @param repairItems The {@link TagKey<Item> Repair Items Tag}
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial blazerMaterial(final TagKey<Item> repairItems) {
        return new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2266, 10.0F, 5.0F, 20, repairItems);
    }

}