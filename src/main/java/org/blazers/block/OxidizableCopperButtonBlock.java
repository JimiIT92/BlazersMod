package org.blazers.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableCopperButtonBlock extends CopperButtonBlock implements IOxidizableCopperButton {

    private final Oxidizable.OxidationLevel weatherState;

    public OxidizableCopperButtonBlock(Oxidizable.OxidationLevel weatherState) {
        super();
        this.weatherState = weatherState;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return IOxidizableCopperButton.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.weatherState;
    }
}
