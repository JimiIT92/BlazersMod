package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation class for the {@link BlazersMod Blazers Mod} {@link Block Cut Copper Bricks}
 */
public class WeatheringCutCopperBricks extends Block implements IWeatheringCutCopperBricks {

    /**
     * {@link WeatheringCopper.WeatherState Copper Weather State}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Sets the {@link WeatheringCopper.WeatherState Copper Weather State}
     * and the {@link BlockBehaviour Parent Block Properties}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Copper Weather State}
     * @param parentBlock {@link BlockBehaviour Parent Block Properties}
     */
    public WeatheringCutCopperBricks(WeatheringCopper.WeatherState weatherState, BlockBehaviour parentBlock) {
        super(BlockBehaviour.Properties.copy(parentBlock));
        this.weatherState = weatherState;
    }

    /**
     * Oxidize the {@link Block Cut Copper Bricks}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link Random Random variable}
     */
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.onRandomTick(state, level, pos, random);
    }

    /**
     * Randomly tick the {@link Block Cut Copper Bricks}
     * if is not fully oxidized
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link Block Cut Copper Bricks} is not fully oxidized
     */
    public boolean isRandomlyTicking(BlockState state) {
        return IWeatheringCutCopperBricks.getNext(state.getBlock()).isPresent();
    }

    /**
     * Get the {@link WeatheringCopper.WeatherState Copper Weather State}
     *
     * @return {@link WeatheringCopper.WeatherState Copper Weather State}
     */
    @Override
    public WeatheringCopper.@NotNull WeatherState getAge() {
        return this.weatherState;
    }

    /**
     * Sets the {@link Block Cut Copper Bricks} variant on
     * right click with an {@link AxeItem Axe}
     *
     * @param state {@link BlockState Block State}
     * @param context {@link UseOnContext Use Context}
     * @param toolAction {@link ToolAction Tool action}
     * @param simulate {@link Boolean If this is a simulation}
     * @return Modified {@link BlockState Block State}
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction == ToolActions.AXE_WAX_OFF) {
            Optional<BlockState> previousBlockState = IWeatheringCutCopperBricks.getPrevious(state);
            if(previousBlockState.isPresent()) {
                return previousBlockState.get();
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
