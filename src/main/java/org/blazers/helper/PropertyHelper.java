package org.blazers.helper;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.blazers.core.BLMaterials;
import org.blazers.core.BLTiers;

/**
 * Helper methods for {@link BlockBehaviour.Properties Block Properties} and {@link Item.Properties Item Properties}
 */
public final class PropertyHelper {

    /**
     * Get the {@link Item.Properties properties} for a {@link Item Food Item}
     *
     * @param nutrition {@link Integer The food nutrition value}
     * @param saturationModifier {@link Float The food saturation modifier value}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link Item.Properties The Food Item properties}
     */
    public static Item.Properties food(final int nutrition, final float saturationModifier, final FeatureFlag... featureFlags) {
        return item(featureFlags).food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationModifier).build());
    }

    /**
     * Get the {@link Item.Properties properties} for a {@link SwordItem Sword Item}
     *
     * @param tier {@link BLTiers The Sword Tier}
     * @param attackDamageModifier {@link Integer The attack damage modifier}
     * @param attackSpeedModifier {@link Float The attack speed modifier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link Item.Properties The Sword Item properties}
     */
    public static Item.Properties sword(final Tier tier, final int attackDamageModifier, final float attackSpeedModifier, final FeatureFlag... featureFlags) {
        return item(featureFlags).attributes(SwordItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier));
    }

    /**
     * Get the {@link Item.Properties properties} for a {@link ArmorItem Armor Item}
     *
     * @param durabilityModifier {@link Integer The durability modifier}
     * @param armorType {@link ArmorItem.Type The Armor Item type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link Item.Properties The Armor Item properties}
     */
    public static Item.Properties armorItem(final ArmorItem.Type armorType, final int durabilityModifier,  final FeatureFlag... featureFlags) {
        return item(featureFlags).durability(armorType.getDurability(durabilityModifier));
    }

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
     * Get the {@link BlockBehaviour.Properties properties} for an {@link Block Ore Storage Block}
     *
     * @param material {@link BLMaterials The Ore Storage material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Ore Block properties}
     */
    public static BlockBehaviour.Properties oreStorage(final BLMaterials material, final FeatureFlag... featureFlags) {
        return block(material.color(), material.strength(), material.blastResistance(), material.sound(), featureFlags).requiresCorrectToolForDrops();
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

    /**
     * Get a copy of some {@link BlockBehaviour.Properties Block properties}
     *
     * @param block {@link Block The Block to copy from}
     * @return {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public static BlockBehaviour.Properties from(final Block block, final FeatureFlag... featureFlags) {
        return BlockBehaviour.Properties.ofFullCopy(block).requiredFeatures(featureFlags);
    }

}