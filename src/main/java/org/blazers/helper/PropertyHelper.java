package org.blazers.helper;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * Helper methods for {@link BlockBehaviour.Properties Block Properties} and {@link Item.Properties Item Properties}
 */
public final class PropertyHelper {

    /**
     * Get the {@link Item.Properties properties} for a simple {@link Item Item}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link Item.Properties The Item properties}
     */
    public static Item.Properties item(final FeatureFlag... featureFlags) {
        return new Item.Properties().requiredFeatures(featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties properties} for an {@link Block Ore Block}
     *
     * @param isDeepslateOre {@link Boolean If the Ore is a Deepslate Ore Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Ore Block properties}
     */
    public static BlockBehaviour.Properties ore(final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return (isDeepslateOre ? block(MapColor.DEEPSLATE, 4.5F, 3F, SoundType.DEEPSLATE) : block(MapColor.STONE, 3F, SoundType.STONE)).requiresCorrectToolForDrops();
    }

    /**
     * Get the {@link BlockBehaviour.Properties properties} for a {@link Block Nether Ore Block}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Nether Ore Block properties}
     */
    public static BlockBehaviour.Properties netherOre(final FeatureFlag... featureFlags) {
        return ore(false).mapColor(MapColor.NETHER).sound(SoundType.NETHER_ORE);
    }

    /**
     * Get the {@link BlockBehaviour.Properties properties} for a simple {@link Block Block}
     *
     * @param color {@link MapColor The Block color on maps}
     * @param strength {@link Float The Block strength against tools and explosions}
     * @param sound {@link SoundType The Block sound}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block properties}
     */
    public static BlockBehaviour.Properties block(final MapColor color, final float strength, final SoundType sound, final FeatureFlag... featureFlags) {
        return block(color, strength, strength, sound, featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties properties} for a simple {@link Block Block}
     *
     * @param color {@link MapColor The Block color on maps}
     * @param strength {@link Float The Block strength against tools}
     * @param blastResistance {@link Float The Block strength against explosions}
     * @param sound {@link SoundType The Block sound}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block properties}
     */
    public static BlockBehaviour.Properties block(final MapColor color, final float strength, final float blastResistance, final SoundType sound, final FeatureFlag... featureFlags) {
        return BlockBehaviour.Properties.of().mapColor(color).strength(strength, blastResistance).sound(sound).requiredFeatures(featureFlags);
    }

}