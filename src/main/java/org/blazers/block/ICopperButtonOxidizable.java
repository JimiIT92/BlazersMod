package org.blazers.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import net.minecraft.state.property.Properties;

import java.util.Optional;

public interface ICopperButtonOxidizable extends Degradable<Oxidizable.OxidationLevel> {


    static Optional<BlockState> getDecreasedOxidationState(BlockState state) {
        return Oxidizable.getDecreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state).with(Properties.POWERED, false));
    }

    @Override
    default Optional<BlockState> getDegradationResult(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state).with(Properties.POWERED, false));
    }

    @Override
    default float getDegradationChanceMultiplier() {
        if (this.getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED) {
            return 0.75f;
        }
        return 1.0f;
    }
}