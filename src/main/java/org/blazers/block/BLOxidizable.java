package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Interface for a {@link BlazersMod Blazers Mod} {@link Oxidizable Oxidizable Block}
 */
public interface BLOxidizable extends Oxidizable {

    /**
     * The {@link Supplier Cut Copper Bricks Oxidation Level Increases Map}
     */
    Supplier<BiMap<Block, Block>> CUT_COPPER_BRICKS_OXIDATION_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(BLBlocks.CUT_COPPER_BRICKS, BLBlocks.EXPOSED_CUT_COPPER_BRICKS)
            .put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS, BLBlocks.WEATHERED_CUT_COPPER_BRICKS)
            .put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS, BLBlocks.OXIDIZED_CUT_COPPER_BRICKS)
            .build()
    );
    /**
     * The {@link Supplier Cut Copper Bricks Oxidation Level Decreases Map}
     */
    Supplier<BiMap<Block, Block>> CUT_COPPER_BRICKS_OXIDATION_LEVEL_DECREASES = Suppliers.memoize(() -> CUT_COPPER_BRICKS_OXIDATION_LEVEL_INCREASES.get().inverse());

    /**
     * Get the {@link Optional<Block> Block} decreased {@link Oxidizable.OxidationLevel Oxidation Level}
     *
     * @param block The {@link Block Block to check}
     * @return The {@link Optional<Block> Block} decreased {@link Oxidizable.OxidationLevel Oxidation Level}
     */
    static Optional<Block> getDecreasedOxidationBlock(final Block block) {
        return Optional.ofNullable(CUT_COPPER_BRICKS_OXIDATION_LEVEL_DECREASES.get().get(block));
    }

    /**
     * Get the {@link Block Unaffected Block} for the provided {@link Block Block}
     *
     * @param block The {@link Block Block to check}
     * @return The {@link Block Unaffected Block}
     */
    static Block getUnaffectedOxidationBlock(final Block block) {
        Block unaffectedBlock = block;

        for(Block decreasedOxidationBlock = CUT_COPPER_BRICKS_OXIDATION_LEVEL_DECREASES.get().get(block); decreasedOxidationBlock != null; decreasedOxidationBlock = CUT_COPPER_BRICKS_OXIDATION_LEVEL_DECREASES.get().get(decreasedOxidationBlock)) {
            unaffectedBlock = decreasedOxidationBlock;
        }

        return unaffectedBlock;
    }

    /**
     * Get the {@link Optional<BlockState> decreased Oxidation Block State}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link Optional<BlockState> decreased Oxidation Block State}
     */
    static Optional<BlockState> getDecreasedOxidationState(final BlockState state) {
        return BLOxidizable.getDecreasedOxidationBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
    }

    /**
     * Get the {@link Block Increased Block} for the provided {@link Block Block}
     *
     * @param block The {@link Block Block to check}
     * @return The {@link Block Increased Block}
     */
    static Optional<Block> getIncreasedOxidationBlock(final Block block) {
        return Optional.ofNullable(CUT_COPPER_BRICKS_OXIDATION_LEVEL_INCREASES.get().get(block));
    }

    /**
     * Get the {@link BlockState unaffected Oxidation Block State}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link BlockState unaffected Oxidation Block State}
     */
    static BlockState getUnaffectedOxidationState(final BlockState state) {
        return BLOxidizable.getUnaffectedOxidationBlock(state.getBlock()).getStateWithProperties(state);
    }

    /**
     * Get the {@link Optional<BlockState> degradation result Block State}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link Optional<BlockState> degradation result Block State}
     */
    default Optional<BlockState> getDegradationResult(final BlockState state) {
        return BLOxidizable.getIncreasedOxidationBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
    }

}