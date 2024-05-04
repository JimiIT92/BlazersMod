package org.blazers.block.weathering;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Interface for a {@link BlazersMod Blazers Mod} {@link ChangeOverTimeBlock weathering Block}
 */
public interface IBLWeatheringBlock extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    /**
     * The {@link Supplier<BiMap> weathering states map}
     */
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(BLBlocks.CUT_COPPER_BRICKS.get(), BLBlocks.EXPOSED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS.get(), BLBlocks.WEATHERED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS.get(), BLBlocks.OXIDIZED_CUT_COPPER_BRICKS.get())
    .build());
    /**
     * The {@link Supplier<BiMap> inverted weathering states map}
     */
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    /**
     * Get the previous {@link Block weathering state Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Optional<Block> The previous weathering state Block, if any}
     */
    static Optional<Block> getPrevious(final Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    /**
     * Get the previous {@link BlockState weathering Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The previous weathering Block State, if any}
     */
    static Optional<BlockState> getPrevious(final BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(block -> block.withPropertiesOf(blockState));
    }

    /**
     * Get the {@link Block first weathering state Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Block The first weathering state Block}
     */
    static Block getFirst(Block block) {
        for (Block previousBlock = PREVIOUS_BY_BLOCK.get().get(block); previousBlock != null; previousBlock = PREVIOUS_BY_BLOCK.get().get(previousBlock)) {
            block = previousBlock;
        }

        return block;
    }

    /**
     * Get the {@link BlockState first weathering Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockState The first weathering Block State}
     */
    static BlockState getFirst(BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
    }

    /**
     * Get the next {@link Block weathering state Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Optional<Block> The next weathering state Block, if any}
     */
    static Optional<Block> getNext(final Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    /**
     * Get the next {@link BlockState weathering Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The next weathering Block State, if any}
     */
    @Override
    default @NotNull Optional<BlockState> getNext(final BlockState blockState) {
        return getNext(blockState.getBlock()).map(block -> block.withPropertiesOf(blockState));
    }

    /**
     * Get the {@link Float chance modifier} for a {@link Block Block} to oxidize
     *
     * @return {@link Float The oxidization chance modifier}
     */
    @Override
    default float getChanceModifier() {
        return this.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }

}