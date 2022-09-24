package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod} {@link Block Pointed Ice Dripstone Block}
 */
public class PointedIceDripstoneBlock extends BLPointedDripstoneBlock {

    /**
     * Constructor. Sets the {@link Block Pointed Dripstone} properties
     */
    public PointedIceDripstoneBlock() {
        super(Blocks.ICE);
    }

    /**
     * Don't drop the {@link Block falling dripstones} after they fall
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param fallingBlockEntity {@link FallingBlockEntity Falling Block Entity}
     */
    @Override
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {

    }

    /**
     * Get the {@link FallingBlockEntity Falling Dripstone Entity}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @return {@link FallingBlockEntity Falling Dripstone Entity}
     */
    @Override
    public FallingBlockEntity getFallingBlockEntity(Level level, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingBlockEntity = super.getFallingBlockEntity(level, pos, state);
        fallingBlockEntity.dropItem = false;
        return fallingBlockEntity;
    }
}