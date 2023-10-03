package org.blazers.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.blazers.core.BLBlocks;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class HollowBlock extends PillarBlock implements Waterloggable {

    public static final Supplier<Map<Block, Block>> HOLLOWABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(Blocks.OAK_LOG, BLBlocks.HOLLOW_OAK_LOG)
            .put(Blocks.STRIPPED_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .put(BLBlocks.HOLLOW_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .put(Blocks.SPRUCE_LOG, BLBlocks.HOLLOW_SPRUCE_LOG)
            .put(Blocks.STRIPPED_SPRUCE_LOG, BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .put(BLBlocks.HOLLOW_SPRUCE_LOG, BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .put(Blocks.BIRCH_LOG, BLBlocks.HOLLOW_BIRCH_LOG)
            .put(Blocks.STRIPPED_BIRCH_LOG, BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .put(BLBlocks.HOLLOW_BIRCH_LOG, BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .put(Blocks.JUNGLE_LOG, BLBlocks.HOLLOW_JUNGLE_LOG)
            .put(Blocks.STRIPPED_JUNGLE_LOG, BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .put(BLBlocks.HOLLOW_JUNGLE_LOG, BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .put(Blocks.ACACIA_LOG, BLBlocks.HOLLOW_ACACIA_LOG)
            .put(Blocks.STRIPPED_ACACIA_LOG, BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .put(BLBlocks.HOLLOW_ACACIA_LOG, BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .put(Blocks.DARK_OAK_LOG, BLBlocks.HOLLOW_DARK_OAK_LOG)
            .put(Blocks.STRIPPED_DARK_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .put(BLBlocks.HOLLOW_DARK_OAK_LOG, BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .put(Blocks.MANGROVE_LOG, BLBlocks.HOLLOW_MANGROVE_LOG)
            .put(Blocks.STRIPPED_MANGROVE_LOG, BLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .put(BLBlocks.HOLLOW_MANGROVE_LOG, BLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .put(Blocks.CRIMSON_STEM, BLBlocks.HOLLOW_CRIMSON_STEM)
            .put(Blocks.STRIPPED_CRIMSON_STEM, BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .put(BLBlocks.HOLLOW_CRIMSON_STEM, BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .put(Blocks.WARPED_STEM, BLBlocks.HOLLOW_WARPED_STEM)
            .put(Blocks.STRIPPED_WARPED_STEM, BLBlocks.HOLLOW_STRIPPED_WARPED_STEM)
            .put(BLBlocks.HOLLOW_WARPED_STEM, BLBlocks.HOLLOW_STRIPPED_WARPED_STEM)
            .build());

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public HollowBlock(MapColor color) {
        this(color, BlockSoundGroup.WOOD);
    }

    public HollowBlock(MapColor color, BlockSoundGroup sound) {
        super(FabricBlockSettings.create().mapColor(color).strength(2.0f).sounds(sound).nonOpaque().blockVision((state, world, pos) -> false));
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(AXIS, context.getSide().getAxis()).with(WATERLOGGED, Fluids.WATER.equals(context.getWorld().getFluidState(context.getBlockPos()).getFluid()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(AXIS)) {
            case X -> VoxelShapes.union(
                    Block.createCuboidShape(0, 12, 4, 16, 15, 12),
                    Block.createCuboidShape(0, 1, 4, 16, 4, 12),
                    Block.createCuboidShape(0, 0, 15, 16, 16, 16),
                    Block.createCuboidShape(0, 0, 0, 16, 16, 1),
                    Block.createCuboidShape(0, 0, 1, 16, 1, 15),
                    Block.createCuboidShape(0, 15, 1, 16, 16, 15),
                    Block.createCuboidShape(0, 1, 12, 16, 15, 15),
                    Block.createCuboidShape(0, 1, 1, 16, 15, 4)
            );
            case Y -> VoxelShapes.union(
                    Block.createCuboidShape(4, 0, 12, 12, 16, 15),
                    Block.createCuboidShape(4, 0, 1, 12, 16, 4),
                    Block.createCuboidShape(0, 0, 0, 1, 16, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 16, 16),
                    Block.createCuboidShape(1, 0, 0, 15, 16, 1),
                    Block.createCuboidShape(1, 0, 15, 15, 16, 16),
                    Block.createCuboidShape(1, 0, 1, 4, 16, 15),
                    Block.createCuboidShape(12, 0, 1, 15, 16, 15)
            );
            case Z -> VoxelShapes.union(
                    Block.createCuboidShape(4, 12, 0, 12, 15, 16),
                    Block.createCuboidShape(4, 1, 0, 12, 4, 16),
                    Block.createCuboidShape(0, 0, 0, 1, 16, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 16, 16),
                    Block.createCuboidShape(1, 0, 0, 15, 1, 16),
                    Block.createCuboidShape(1, 15, 0, 15, 16, 16),
                    Block.createCuboidShape(1, 1, 0, 4, 15, 16),
                    Block.createCuboidShape(12, 1, 0, 15, 15, 16)
            );
        };
    }

    public static Optional<BlockState> getHollow(BlockState state) {
        return Optional.ofNullable(HollowBlock.HOLLOWABLES.get().get(state.getBlock())).map(block -> block.getStateWithProperties(state));
    }
}
