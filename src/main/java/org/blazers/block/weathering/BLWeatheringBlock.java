package org.blazers.block.weathering;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolAction;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * {@link BlazersMod Blazers Mod} {@link IBLWeatheringBlock weathering Block}
 */
public class BLWeatheringBlock extends Block implements IBLWeatheringBlock {

    /**
     * The {@link MapCodec<BLWeatheringBlock> Block codec}
     */
    private static final MapCodec<BLWeatheringBlock> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(IBLWeatheringBlock::getAge),
            propertiesCodec()
    ).apply(builder, BLWeatheringBlock::new));

    /**
     * {@link WeatheringCopper.WeatherState The Block weather state}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block properties}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Block weather state}
     * @param properties {@link BlockBehaviour.Properties The Block properties}
     */
    public BLWeatheringBlock(final WeatheringCopper.WeatherState weatherState, final BlockBehaviour.Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    /**
     * Makes the {@link Block Block} randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The Level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    protected void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        this.changeOverTime(blockState, level, blockPos, randomSource);
    }

    /**
     * Check if the {@link Block Block} should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block is not fully oxidized}
     */
    @Override
    protected boolean isRandomlyTicking(final BlockState blockState) {
        return IBLWeatheringBlock.getNext(blockState.getBlock()).isPresent();
    }

    /**
     * Get the {@link WeatheringCopper.WeatherState The Block weather state}
     *
     * @return {@link #weatherState The Block weather state}
     */
    @Override
    public WeatheringCopper.@NotNull WeatherState getAge() {
        return this.weatherState;
    }

    /**
     * Get the {@link MapCodec<BLWeatheringBlock> Block codec}
     *
     * @return {@link MapCodec<BLWeatheringBlock> The Block codec}
     */
    @Override
    public @NotNull MapCodec<BLWeatheringBlock> codec() {
        return CODEC;
    }


    /**
     * Interact with the {@link Block Block} on right click
     *
     * @param itemStack {@link ItemStack The used Item Stack}
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param player {@link Player The Player interacting with the Block}
     * @param hand {@link InteractionHand The hand used to interact with the Block}
     * @param blockHitResult {@link BlockHitResult The Block Hit Result}
     * @return {@link ItemInteractionResult The interaction result}
     */
    @Override
    protected @NotNull ItemInteractionResult useItemOn(final @NotNull ItemStack itemStack, final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult blockHitResult) {
        if(itemStack.getItem() instanceof HoneycombItem) {
            return ItemInteractionResult.sidedSuccess(IBLWaxedBlock.wax(blockState, player, level, itemStack, blockPos, hand));
        }
        return super.useItemOn(itemStack, blockState, level, blockPos, player, hand, blockHitResult);
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param simulate {@link Boolean If the action only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        final Optional<BlockState> previousBlockState = IBLWeatheringBlock.getPrevious(blockState);
        if(previousBlockState.isPresent()) {
            final Player player = context.getPlayer();
            context.getLevel().levelEvent(player, 3005, context.getClickedPos(), 0);
            if(player != null) {
                player.playSound(SoundEvents.AXE_SCRAPE);
            }
            return previousBlockState.get();
        }
        return super.getToolModifiedState(blockState, context, toolAction, simulate);
    }
}