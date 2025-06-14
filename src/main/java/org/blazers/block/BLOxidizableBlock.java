package org.blazers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OxidizableBlock;

/**
 * Implementation class for an {@link OxidizableBlock Oxidizable Block}
 */
public class BLOxidizableBlock extends OxidizableBlock implements BLOxidizable {

    /**
     * The {@link OxidationLevel Block Oxidation Level}
     */
    private final OxidationLevel oxidationLevel;

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param oxidationLevel The {@link OxidationLevel Block Oxidation Level}
     * @param settings The {@link Settings Block Settings}
     */
    public BLOxidizableBlock(final OxidationLevel oxidationLevel, final Settings settings) {
        super(oxidationLevel, settings);
        this.oxidationLevel = oxidationLevel;
    }

    /**
     * Check if the {@link Block Block} should tick
     *
     * @param state The {@link BlockState current Block State}
     * @return {@link Boolean True if the Block should tick}
     */
    @Override
    protected boolean hasRandomTicks(final BlockState state) {
        return !OxidationLevel.OXIDIZED.equals(this.oxidationLevel);
    }

    /**
     * Get the {@link OxidationLevel Block Oxidation Level}
     *
     * @return The {@link OxidationLevel Block Oxidation Level}
     */
    @Override
    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

}
