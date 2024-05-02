package org.blazers.helper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

/**
 * Helper methods for {@link Block Blocks}
 */
public final class BlockHelper {

    public static MapColor woodColor(final WoodType woodType, final boolean isStrippedLog) {
        if(woodType.equals(WoodType.OAK)) {
            return isStrippedLog ? MapColor.WOOD : MapColor.PODZOL;
        }
        if(woodType.equals(WoodType.SPRUCE)) {
            return isStrippedLog ? MapColor.PODZOL : MapColor.COLOR_BROWN;
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return isStrippedLog ? MapColor.SAND : MapColor.QUARTZ;
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return isStrippedLog ? MapColor.DIRT : MapColor.PODZOL;
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return isStrippedLog ? MapColor.COLOR_ORANGE : MapColor.STONE;
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return MapColor.COLOR_BROWN;
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return isStrippedLog ? MapColor.TERRACOTTA_WHITE : MapColor.TERRACOTTA_GRAY;
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return isStrippedLog ? MapColor.COLOR_RED : MapColor.PODZOL;
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return isStrippedLog ? MapColor.PODZOL : MapColor.COLOR_YELLOW;
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return MapColor.CRIMSON_STEM;
        }
        if(woodType.equals(WoodType.WARPED)) {
            return MapColor.WARPED_STEM;
        }
        return MapColor.WOOD;
    }

    /**
     * Check if a {@link Block Block} can catch fire based on its {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @return {@link Boolean True if the Wood Type is flammable}
     */
    public static boolean isFlammable(final WoodType woodType) {
        return !woodType.equals(WoodType.CRIMSON) && !woodType.equals(WoodType.WARPED);
    }

    /**
     * Get an {@link ItemStack Item Stack} for a {@link Block Block}
     *
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @return {@link ItemStack The Block Item Stack}
     */
    public static ItemStack itemStack(final Supplier<Block> blockSupplier) {
        return blockSupplier.get().asItem().getDefaultInstance();
    }

}