package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.blazers.BlazersMod;
import org.blazers.core.BLTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link DoublePlantBlock Cattail Block}
 */
public class CattailBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

    /**
     * {@link BooleanProperty Waterlogged Property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Sets the {@link DoublePlantBlock Cattail} {@link BlockBehaviour.Properties Block Properties}
     */
    public CattailBlock() {
        super(BlockBehaviour.Properties.of(Material.PLANT).noCollission().noOcclusion().instabreak().sound(SoundType.SMALL_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XZ));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    /**
     * Get the {@link DoublePlantBlock Cattail}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param context {@link CollisionContext Collision Context}
     * @return {@link DoublePlantBlock Cattail} {@link VoxelShape Shape}
     */
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D).move(offset.x, offset.y, offset.z);
    }

    /**
     * Check if the {@link DoublePlantBlock Cattail} can be placed on a {@link Block Block}
     *
     * @param state {@link BlockState Block State}
     * @param world {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link DoublePlantBlock Cattail} can be placed on a {@link Block Block}
     */
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos) {
        return state.is(BLTags.Blocks.CATTAIL_PLACEABLE) || world.getFluidState(pos.above()).isSourceOfType(Fluids.WATER) && super.mayPlaceOn(state, world, pos);
    }

    /**
     * Get the {@link BlockState Block State} when the
     * {@link DoublePlantBlock Cattail} is placed
     *
     * @param context {@link BlockPlaceContext Place Context}
     * @return Placed {@link BlockState Block State}
     */
    @Nullable
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        return blockstate != null ? copyWaterloggedFrom(context.getLevel(), context.getClickedPos(), blockstate) : null;
    }

    /**
     * Place the {@link DoubleBlockHalf#UPPER Upper variant} on {@link DoublePlantBlock Cattail} placed
     *
     * @param world {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @param entity {@link LivingEntity Entity} who placed the {@link DoublePlantBlock Cattail}
     * @param stack {@link ItemStack Item Stack}
     */
    public void setPlacedBy(Level world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull LivingEntity entity, @NotNull ItemStack stack) {
        if (!world.isClientSide()) {
            BlockPos blockpos = pos.above();
            BlockState blockstate = DoublePlantBlock.copyWaterloggedFrom(world, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER));
            world.setBlock(blockpos, blockstate, 3);
        }
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
     * Checks if the {@link DoublePlantBlock Cattail} should break
     * if one {@link #HALF Half} is broken
     *
     * @param state {@link BlockState Block State}
     * @param level {@link LevelReader World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link CattailBlock} should not break if one {@link #HALF Half} is broken
     */
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, level, pos);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, level, blockpos);
        }
    }

    /**
     * Updated the {@link BlockState Block State} if the
     * {@link DoublePlantBlock Cattail} is waterlogged
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
        stateBuilder.add(HALF, WATERLOGGED);
    }

    /**
     * Get the {@link Float Block Vertical Offset}
     *
     * @return {@link Float Block Vertical Offset}
     */
    public float getMaxVerticalOffset() {
        return 0.1F;
    }
}