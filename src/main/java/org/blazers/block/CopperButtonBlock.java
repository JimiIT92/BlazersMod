package org.blazers.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import org.blazers.BlazersMod;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link ButtonBlock Copper Button Block}
 */
public class CopperButtonBlock extends ButtonBlock {

    /**
     * Constructor. Sets the {@link Properties Copper Button Properties}
     */
    public CopperButtonBlock() {
        super(BlockSetType.COPPER, 40,
                Properties.of().mapColor(MapColor.COLOR_ORANGE)
                .noCollission()
                .strength(0.5F)
                .sound(SoundType.COPPER)
        );
    }
}