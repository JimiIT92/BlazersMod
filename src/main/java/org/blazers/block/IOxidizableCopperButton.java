package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import net.minecraft.state.property.Properties;
import org.blazers.core.BLBlocks;

import java.util.Optional;
import java.util.function.Supplier;

public interface IOxidizableCopperButton
        extends Degradable<Oxidizable.OxidationLevel> {
    Supplier<BiMap<Block, Block>> OXIDATION_LEVEL_INCREASES = Suppliers.memoize(() -> ((ImmutableBiMap.Builder)ImmutableBiMap.builder()
            .put(BLBlocks.COPPER_BUTTON, BLBlocks.EXPOSED_COPPER_BUTTON))
            .put(BLBlocks.EXPOSED_COPPER_BUTTON, BLBlocks.WEATHERED_COPPER_BUTTON)
            .put(BLBlocks.WEATHERED_COPPER_BUTTON, BLBlocks.OXIDIZED_COPPER_BUTTON)
        .build());
    Supplier<BiMap<Block, Block>> OXIDATION_LEVEL_DECREASES = Suppliers.memoize(() -> OXIDATION_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedOxidationBlock(Block block) {
        return Optional.ofNullable(OXIDATION_LEVEL_DECREASES.get().get(block));
    }

    static Block getUnaffectedOxidationBlock(Block block) {
        Block block2 = block;
        Block block3 = OXIDATION_LEVEL_DECREASES.get().get(block2);
        while (block3 != null) {
            block2 = block3;
            block3 = OXIDATION_LEVEL_DECREASES.get().get(block2);
        }
        return block2;
    }

    static Optional<BlockState> getDecreasedOxidationState(BlockState state) {
        return IOxidizableCopperButton.getDecreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state).with(Properties.POWERED, Boolean.FALSE));
    }

    static Optional<Block> getIncreasedOxidationBlock(Block block) {
        return Optional.ofNullable(OXIDATION_LEVEL_INCREASES.get().get(block));
    }

    static BlockState getUnaffectedOxidationState(BlockState state) {
        return IOxidizableCopperButton.getUnaffectedOxidationBlock(state.getBlock()).getStateWithProperties(state);
    }

    @Override
    default Optional<BlockState> getDegradationResult(BlockState state) {
        return IOxidizableCopperButton.getIncreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state).with(Properties.POWERED, Boolean.FALSE));
    }

    @Override
    default float getDegradationChanceMultiplier() {
        if (this.getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED) {
            return 0.75f;
        }
        return 1.0f;
    }
}

