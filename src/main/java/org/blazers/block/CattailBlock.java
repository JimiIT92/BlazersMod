package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.blazers.core.BLTags;
import org.jetbrains.annotations.Nullable;

public class CattailBlock extends TallPlantBlock implements Waterloggable {

    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public CattailBlock() {
        super(FabricBlockSettings.of(Material.PLANT).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.SMALL_DRIPLEAF).offsetType(OffsetType.XZ));
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d offset = state.getModelOffset(world, pos);
        return Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D).offset(offset.x, offset.y, offset.z);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BLTags.Blocks.CATTAIL_PLACEABLE) || world.getFluidState(pos.up()).isOf(Fluids.WATER) && super.canPlantOnTop(floor, world, pos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockstate = super.getPlacementState(context);
        return blockstate != null ? withWaterloggedState(context.getWorld(), context.getBlockPos(), blockstate) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient) {
            BlockPos blockPos = pos.up();
            BlockState blockState = withWaterloggedState(world, blockPos, this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER));
            world.setBlockState(blockPos, blockState, 3);
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(state.get(HALF) == DoubleBlockHalf.UPPER) {
            return super.canPlaceAt(state, world, pos);
        }
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        return this.canPlantOnTop(blockState, world, blockPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }

    @Override
    public float getVerticalModelOffsetMultiplier() {
        return 0.1F;
    }
}
