package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableCutCopperBricksBlock extends Block implements IOxidizableCutCopperBricks {

    private final Oxidizable.OxidationLevel weatherState;

    public OxidizableCutCopperBricksBlock(Oxidizable.OxidationLevel weatherState, Block parentBlock) {
        super(FabricBlockSettings.copyOf(parentBlock));
        this.weatherState = weatherState;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return IOxidizableCutCopperBricks.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.weatherState;
    }
}
