package org.blazers.core;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import org.blazers.BlazersMod;

/**
 * {@link BlazersMod Blazers Mod} {@link ToolMaterial Tool Materials}
 */
public final class BLToolMaterials {

    //#region Tool Materials

    public static final ToolMaterial EMERALD = gemToolMaterial(BLTags.ItemTags.EMERALD_TOOL_MATERIALS);
    public static final ToolMaterial SAPPHIRE = gemToolMaterial(BLTags.ItemTags.SAPPHIRE_TOOL_MATERIALS);
    public static final ToolMaterial RUBY = gemToolMaterial(BLTags.ItemTags.RUBY_TOOL_MATERIALS);
    public static final ToolMaterial TOPAZ = rockToolMaterial(BLTags.ItemTags.TOPAZ_TOOL_MATERIALS);
    public static final ToolMaterial MALACHITE = rockToolMaterial(BLTags.ItemTags.MALACHITE_TOOL_MATERIALS);
    public static final ToolMaterial ONICE = rockToolMaterial(BLTags.ItemTags.ONICE_TOOL_MATERIALS);
    public static final ToolMaterial AMETHYST = crystalToolMaterial(BLTags.ItemTags.AMETHYST_TOOL_MATERIALS);
    public static final ToolMaterial PEARL = crystalToolMaterial(BLTags.ItemTags.PEARL_TOOL_MATERIALS);
    public static final ToolMaterial BLAZERITE = powerfulToolMaterial(BLTags.ItemTags.BLAZERITE_TOOL_MATERIALS);
    public static final ToolMaterial GYULIANITE = powerfulToolMaterial(BLTags.ItemTags.GYULIANITE_TOOL_MATERIALS);
    public static final ToolMaterial FLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 131, 4.0F, 1.0F, 5, BLTags.ItemTags.FLINT_TOOL_MATERIALS);
    public static final ToolMaterial CARBON = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 250, 8.0F, 2.0F, 14, BLTags.ItemTags.CARBON_TOOL_MATERIALS);

    //#endregion

    /**
     * Get a {@link ToolMaterial Tool Material} for a {@link Item gem-like Item}
     *
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial gemToolMaterial(final TagKey<Item> repairItemsTag) {
        return new ToolMaterial(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                1796,
                8.5F,
                3.5F,
                13,
                repairItemsTag
        );
    }

    /**
     * Get a {@link ToolMaterial Tool Material} for a {@link Item rock-like Item}
     *
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial rockToolMaterial(final TagKey<Item> repairItemsTag) {
        return new ToolMaterial(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                905,
                7.0F,
                2.5F,
                12,
                repairItemsTag
        );
    }

    /**
     * Get a {@link ToolMaterial Tool Material} for a {@link Item crystal-like Item}
     *
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial crystalToolMaterial(final TagKey<Item> repairItemsTag) {
        return new ToolMaterial(
                BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                191,
                5.0F,
                1.5F,
                12,
                repairItemsTag
        );
    }

    /**
     * Get a {@link ToolMaterial Tool Material} for a {@link Item powerful Item}
     *
     * @param repairItemTags The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ToolMaterial Tool Material}
     */
    private static ToolMaterial powerfulToolMaterial(final TagKey<Item> repairItemTags) {
        return new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                2266,
                10.0F,
                5.0F,
                20,
                repairItemTags
        );
    }

}