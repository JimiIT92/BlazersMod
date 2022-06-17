package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link ButtonBlock Copper Button Block}
 */
public class WeatheringCopperButtonBlock extends CopperButtonBlock implements IWeatheringCopperButton {

    /**
     * {@link WeatheringCopper.WeatherState Copper Weather State}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Sets the {@link WeatheringCopper.WeatherState Copper Weather State}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Copper Weather State}
     */
    public WeatheringCopperButtonBlock(WeatheringCopper.WeatherState weatherState) {
        super();
        this.weatherState = weatherState;
    }

    /**
     * Oxidize the {@link ButtonBlock Copper Button}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link Random Random variable}
     */
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
        this.onRandomTick(state, level, pos, random);
    }

    /**
     * Randomly tick the {@link ButtonBlock Copper Button}
     * if is not fully oxidized
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link ButtonBlock Copper Button} is not fully oxidized
     */
    public boolean isRandomlyTicking(BlockState state) {
        return IWeatheringCopperButton.getNext(state.getBlock()).isPresent();
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
}