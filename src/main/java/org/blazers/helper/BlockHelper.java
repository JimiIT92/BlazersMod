package org.blazers.helper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.blazers.core.BLMaterials;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * Helper methods for {@link Block Blocks}
 */
public final class BlockHelper {

    /**
     * Get the {@link MapColor color} of a given {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type to get the color from}
     * @param isStrippedLog {@link Boolean If the Wood Type is referring to a stripped log}
     * @return {@link MapColor The wood Map Color}
     */
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
     * Get the {@link String name} of an {@link Block Ore Block}
     *
     * @param material {@link BLMaterials The Ore Block material}
     * @param isDeepslateOre {@link Boolean If the Ore Block is a Deepslate Ore Block}
     * @return {@link String The Ore Block name}
     */
    public static String oreName(final BLMaterials material, final boolean isDeepslateOre) {
        return (isDeepslateOre ? "deepslate_" : "") + lower(material.name()) + "_ore";
    }

    /**
     * Get the {@link String name} of a {@link BLMaterials material}
     *
     * @param material {@link BLMaterials The material to get the name from}
     * @return {@link String The material name}
     */
    public static String materialName(final BLMaterials material) {
        return lower(material.name());
    }

    /**
     * Get the {@link String name} of a {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type to get the name from}
     * @param isStrippedLog {@link Boolean If the Wood Type is referring to a stripped log}
     * @return {@link String The material name}
     */
    public static String woodName(final WoodType woodType, final boolean isStrippedLog) {
        return (isStrippedLog ? "stripped_" : "") + lower(woodType.name()) + "_" + (woodType.equals(WoodType.BAMBOO) ? "block" : woodType.equals(WoodType.CRIMSON) || woodType.equals(WoodType.WARPED) ? "stem" : "log");
    }

    /**
     * Get the {@link String name} of a {@link WeatheringCopper.WeatherState weather state}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @return {@link String The weather state name}
     */
    public static String weatherStateName(final WeatheringCopper.WeatherState weatherState) {
        if(!weatherState.equals(WeatheringCopper.WeatherState.UNAFFECTED)) {
            return lower(weatherState.getSerializedName());
        }
        return "";
    }

    /**
     * Get the {@link String name} of a {@link Block weathering Block}
     *
     * @param materialName {@link String The Block material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @return {@link String The weathering Block name}
     */
    public static String weatheringBlockName(final String materialName, final WeatheringCopper.WeatherState weatherState,final boolean isWaxed) {
        final String weatherStateName = weatherStateName(weatherState);
        return (isWaxed ? "waxed_" : "") + weatherStateName + (weatherStateName.isEmpty() ? "" : "_") + materialName;
    }

    /**
     * Get the {@link String name} of a {@link Block Block}
     *
     * @param block {@link Block The Block}
     * @return {@link String The Block name}
     */
    public static String blockName(final Block block) {
        return lower(block.getName().getString().replace(" ", "_"));
    }

    /**
     * Make a {@link String text} lowercase
     *
     * @param text {@link String The text}
     * @return {@link String The lowercase text}
     */
    static String lower(final String text) {
        return text.toLowerCase(Locale.ROOT);
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