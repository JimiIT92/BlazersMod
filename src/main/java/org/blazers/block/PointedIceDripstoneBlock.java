package org.blazers.block;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link BLPointedDripstoneBlock Ice Pointed Dripstone Block}
 */
public class PointedIceDripstoneBlock extends BLPointedDripstoneBlock {
    /**
     * Constructor. Sets the {@link BlockBehaviour.Properties Block properties}
     */
    public PointedIceDripstoneBlock() {
        super(Suppliers.memoize(() -> Blocks.ICE));
    }

    /**
     * Don't drop the {@link Block falling dripstones} after they fall
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param fallingBlockEntity {@link FallingBlockEntity The Falling Block Entity}
     */
    @Override
    public void onBrokenAfterFall(final @NotNull Level level, final @NotNull BlockPos blockPos, final FallingBlockEntity fallingBlockEntity) { }

    /**
     * Get the {@link FallingBlockEntity Falling Dripstone Entity}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link FallingBlockEntity The Falling Dripstone Entity}
     */
    @Override
    public FallingBlockEntity getFallingBlockEntity(final Level level, final BlockPos blockPos, final BlockState blockState) {
        final FallingBlockEntity fallingBlockEntity = super.getFallingBlockEntity(level, blockPos, blockState);
        fallingBlockEntity.dropItem = false;
        return fallingBlockEntity;
    }

}