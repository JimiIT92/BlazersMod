package org.blazers.block.weathering;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import org.blazers.BlazersMod;
import org.blazers.helper.PropertyHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link ButtonBlock copper Button Block}
 */
public class CopperButton extends ButtonBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block properties}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public CopperButton(final FeatureFlag... featureFlags) {
        super(BlockSetType.COPPER, 40, PropertyHelper.block(MapColor.COLOR_ORANGE, 0.5F, SoundType.COPPER, featureFlags).noCollission());
    }

}