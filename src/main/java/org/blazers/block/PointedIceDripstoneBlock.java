package org.blazers.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PointedIceDripstoneBlock extends BLPointedDripstoneBlock {

    public PointedIceDripstoneBlock() {
        super(Blocks.ICE);
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {

    }

    @Override
    public FallingBlockEntity getFallingBlockEntity(World world, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingBlockEntity = super.getFallingBlockEntity(world, pos, state);
        fallingBlockEntity.dropItem = false;
        return fallingBlockEntity;
    }
}

