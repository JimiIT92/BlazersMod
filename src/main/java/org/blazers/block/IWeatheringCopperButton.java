package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.ButtonBlock.POWERED;

/**
 * Implementation interface for a {@link BlazersMod Blazers Mod}  {@link ButtonBlock Copper Button Block}
 */
public interface IWeatheringCopperButton extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    /**
     * {@link BiMap Block progressive weathering states}
     */
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(BLBlocks.COPPER_BUTTON.get(), BLBlocks.EXPOSED_COPPER_BUTTON.get())
            .put(BLBlocks.EXPOSED_COPPER_BUTTON.get(), BLBlocks.WEATHERED_COPPER_BUTTON.get())
            .put(BLBlocks.WEATHERED_COPPER_BUTTON.get(), BLBlocks.OXIDIZED_COPPER_BUTTON.get())
            .build()
    );
    /**
     * {@link BiMap Block previous weathering states}
     */
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    /**
     * Get the previous {@link Block Weathering State Block}
     *
     * @param block Current {@link Block Weathering State Block}
     * @return Previous {@link Block Weathering State Block}
     */
    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    /**
     * Get the previous {@link BlockState Weathering State Block State}
     *
     * @param state Current {@link Block Weathering State Block State}
     * @return Previous {@link Block Weathering State Block State}
     */
    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map(block -> block.withPropertiesOf(state).setValue(POWERED, Boolean.FALSE));
    }

    /**
     * Get the first {@link Block Weathering State Block}
     *
     * @param currentBlock Current {@link Block Weathering State Block}
     * @return First {@link Block Weathering State Block}
     */
    static Block getFirst(Block currentBlock) {
        Block block = currentBlock;

        for(Block block1 = PREVIOUS_BY_BLOCK.get().get(currentBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    /**
     * Get the first {@link Block Weathering State Block State}
     *
     * @param state Current {@link Block Weathering State Block State}
     * @return First {@link Block Weathering State Block State}
     */
    static BlockState getFirst(BlockState state) {
        return getFirst(state.getBlock()).withPropertiesOf(state);
    }

    /**
     * Get the next {@link Block Weathering State Block}
     *
     * @param block Current {@link Block Weathering State Block}
     * @return Next {@link Block Weathering State Block}
     */
    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    /**
     * Get the next {@link Block Weathering State Block State}
     *
     * @param state Current {@link Block Weathering State Block State}
     * @return Next {@link Block Weathering State Block State}
     */
    default @NotNull Optional<BlockState> getNext(BlockState state) {
        return getNext(state.getBlock()).map(block -> block.withPropertiesOf(state).setValue(POWERED, Boolean.FALSE));
    }

    /**
     * Get the {@link Float Oxidization chance}
     *
     * @return {@link Float Oxidization chance}
     */
    default float getChanceModifier() {
        return this.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }
}