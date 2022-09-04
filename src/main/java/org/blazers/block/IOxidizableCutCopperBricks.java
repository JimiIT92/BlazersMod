package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import org.blazers.core.BLBlocks;

import java.util.Optional;
import java.util.function.Supplier;

public interface IOxidizableCutCopperBricks
        extends Degradable<Oxidizable.OxidationLevel> {
    Supplier<BiMap<Block, Block>> OXIDATION_LEVEL_INCREASES = Suppliers.memoize(() -> ((ImmutableBiMap.Builder)ImmutableBiMap.builder()
            .put(BLBlocks.CUT_COPPER_BRICKS, BLBlocks.EXPOSED_CUT_COPPER_BRICKS))
            .put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS, BLBlocks.WEATHERED_CUT_COPPER_BRICKS)
            .put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS, BLBlocks.OXIDIZED_CUT_COPPER_BRICKS)
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
        return IOxidizableCutCopperBricks.getDecreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    static Optional<Block> getIncreasedOxidationBlock(Block block) {
        return Optional.ofNullable(OXIDATION_LEVEL_INCREASES.get().get(block));
    }

    static BlockState getUnaffectedOxidationState(BlockState state) {
        return IOxidizableCutCopperBricks.getUnaffectedOxidationBlock(state.getBlock()).getStateWithProperties(state);
    }

    @Override
    default Optional<BlockState> getDegradationResult(BlockState state) {
        return IOxidizableCutCopperBricks.getIncreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    @Override
    default float getDegradationChanceMultiplier() {
        if (this.getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED) {
            return 0.75f;
        }
        return 1.0f;
    }
}

