package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link RotatedPillarBlock Hollow Block}
 */
public class HollowBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    /**
     * Hollowable {@link Block Logs}
     */
    public static final Supplier<Map<Block, Block>> HOLLOWABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(Blocks.OAK_LOG, BLBlocks.HOLLOW_OAK_LOG.get())
            .put(Blocks.STRIPPED_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_OAK_LOG.get())
            .put(BLBlocks.HOLLOW_OAK_LOG.get(), BLBlocks.HOLLOW_STRIPPED_OAK_LOG.get())
            .put(Blocks.SPRUCE_LOG, BLBlocks.HOLLOW_SPRUCE_LOG.get())
            .put(Blocks.STRIPPED_SPRUCE_LOG, BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG.get())
            .put(BLBlocks.HOLLOW_SPRUCE_LOG.get(), BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG.get())
            .put(Blocks.BIRCH_LOG, BLBlocks.HOLLOW_BIRCH_LOG.get())
            .put(Blocks.STRIPPED_BIRCH_LOG, BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG.get())
            .put(BLBlocks.HOLLOW_BIRCH_LOG.get(), BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG.get())
            .put(Blocks.JUNGLE_LOG, BLBlocks.HOLLOW_JUNGLE_LOG.get())
            .put(Blocks.STRIPPED_JUNGLE_LOG, BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG.get())
            .put(BLBlocks.HOLLOW_JUNGLE_LOG.get(), BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG.get())
            .put(Blocks.ACACIA_LOG, BLBlocks.HOLLOW_ACACIA_LOG.get())
            .put(Blocks.STRIPPED_ACACIA_LOG, BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG.get())
            .put(BLBlocks.HOLLOW_ACACIA_LOG.get(), BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG.get())
            .put(Blocks.DARK_OAK_LOG, BLBlocks.HOLLOW_DARK_OAK_LOG.get())
            .put(Blocks.STRIPPED_DARK_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG.get())
            .put(BLBlocks.HOLLOW_DARK_OAK_LOG.get(), BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG.get())
            .put(Blocks.MANGROVE_LOG, BLBlocks.HOLLOW_MANGROVE_LOG.get())
            .put(Blocks.STRIPPED_MANGROVE_LOG, BLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG.get())
            .put(BLBlocks.HOLLOW_MANGROVE_LOG.get(), BLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG.get())
            .put(Blocks.CRIMSON_STEM, BLBlocks.HOLLOW_CRIMSON_STEM.get())
            .put(Blocks.STRIPPED_CRIMSON_STEM, BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.get())
            .put(BLBlocks.HOLLOW_CRIMSON_STEM.get(), BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.get())
            .put(Blocks.WARPED_STEM, BLBlocks.HOLLOW_WARPED_STEM.get())
            .put(Blocks.STRIPPED_WARPED_STEM, BLBlocks.HOLLOW_STRIPPED_WARPED_STEM.get())
            .put(BLBlocks.HOLLOW_WARPED_STEM.get(), BLBlocks.HOLLOW_STRIPPED_WARPED_STEM.get())
            .build());

    /**
     * {@link BooleanProperty Waterlogged Property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties {@link BlockBehaviour.Properties Block Properties}
     */
    public HollowBlock(BlockBehaviour.Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Determine if the {@link RotatedPillarBlock Block} is flammable
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Fire direction}
     * @return {@link Boolean True} if the {@link RotatedPillarBlock Block} is flammable
     */
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.material.isFlammable();
    }

    /**
     * Get the {@link RotatedPillarBlock Block} {@link Integer flammability value}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Fire direction}
     * @return {@link RotatedPillarBlock Block} {@link Integer flammability value}
     */
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    /**
     * Get the {@link RotatedPillarBlock Block} {@link Integer fire spread value}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Fire direction}
     * @return {@link RotatedPillarBlock Block} {@link Integer fire spread value}
     */
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    /**
     * Sets the {@link RotatedPillarBlock Stripped Block} variant on
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
        if(stack.getItem() instanceof AxeItem && toolAction == ToolActions.AXE_STRIP) {
            Optional<BlockState> optionalHollowState = getHollow(state);
            if(optionalHollowState.isPresent()) {
                return optionalHollowState.get().setValue(AXIS, state.getValue(AXIS)).setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    /**
     * Get the {@link BlockState Block State} when the
     * {@link RotatedPillarBlock Block} is placed
     *
     * @param context {@link BlockPlaceContext Place Context}
     * @return Placed {@link BlockState Block State}
     */
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(WATERLOGGED, Fluids.WATER.equals(context.getLevel().getFluidState(context.getClickedPos()).getType()));
    }

    /**
     * Updated the {@link BlockState Block State} if the
     * {@link RotatedPillarBlock Block} is waterlogged
     *
     * @param state {@link BlockState Block State}
     * @param facing {@link Direction Direction}
     * @param facingState {@link BlockState Facing Block State}
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param facingPos {@link BlockPos Facing Block Pos}
     * @return Placed {@link BlockState Block State}
     */
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    /**
     * Create the {@link StateDefinition Block State Definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder Block State Definition Builder}
     */
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AXIS).add(WATERLOGGED);
    }

    /**
     * Get the {@link FluidState Fluid State}
     *
     * @param state {@link BlockState Block State}
     * @return {@link FluidState Fluid State}
     */
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Get the {@link Float shade brightness}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Float Shade brightness}
     */
    @Override
    public float getShadeBrightness(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return 1.0F;
    }

    /**
     * Determine if the {@link RotatedPillarBlock Block}
     * should propagate the Skylight
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link RotatedPillarBlock Block} should propagate the Skylight
     */
    @Override
    public boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return true;
    }

    /**
     * Get the {@link RotatedPillarBlock Block} {@link VoxelShape Shape}
     * based on its {@link Direction Axis}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param context {@link CollisionContext Collision Context}
     * @return {@link RotatedPillarBlock Block} {@link VoxelShape Shape}
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> Shapes.or(
                    Block.box(0, 12, 4, 16, 15, 12),
                    Block.box(0, 1, 4, 16, 4, 12),
                    Block.box(0, 0, 15, 16, 16, 16),
                    Block.box(0, 0, 0, 16, 16, 1),
                    Block.box(0, 0, 1, 16, 1, 15),
                    Block.box(0, 15, 1, 16, 16, 15),
                    Block.box(0, 1, 12, 16, 15, 15),
                    Block.box(0, 1, 1, 16, 15, 4)
            );
            case Y -> Shapes.or(
                    Block.box(4, 0, 12, 12, 16, 15),
                    Block.box(4, 0, 1, 12, 16, 4),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 16, 1),
                    Block.box(1, 0, 15, 15, 16, 16),
                    Block.box(1, 0, 1, 4, 16, 15),
                    Block.box(12, 0, 1, 15, 16, 15)
            );
            case Z -> Shapes.or(
                    Block.box(4, 12, 0, 12, 15, 16),
                    Block.box(4, 1, 0, 12, 4, 16),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 1, 16),
                    Block.box(1, 15, 0, 15, 16, 16),
                    Block.box(1, 1, 0, 4, 15, 16),
                    Block.box(12, 1, 0, 15, 15, 16)
            );
        };
    }

    /**
     * Get the Hollow {@link Block Log}
     * based on the current {@link BlockState Block State}
     *
     * @param state Current {@link BlockState Block State}
     * @return Hollow {@link Block Log}
     */
    public static Optional<BlockState> getHollow(BlockState state) {
        return Optional.ofNullable(HollowBlock.HOLLOWABLES.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
    }
}