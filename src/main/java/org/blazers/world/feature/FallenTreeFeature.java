package org.blazers.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import static net.minecraft.block.PillarBlock.AXIS;


public class FallenTreeFeature extends Feature<ProbabilityConfig> implements FeatureConfig {

    private final Block TRUNK;

    public FallenTreeFeature(Block block) {
        super(ProbabilityConfig.CODEC);
        this.TRUNK = block;
    }

    @Override
    public boolean generate(FeatureContext<ProbabilityConfig> context) {
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos blockpos = context.getOrigin();
        ProbabilityConfig probabilityConfig = context.getConfig();
        if(random.nextDouble() < (double)probabilityConfig.probability) {
            int size = 3 + random.nextInt(2);
            Direction direction = Util.getRandom(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}, random);
            BlockPos.Mutable mutableBlockPos = blockpos.mutableCopy();
            for (int i = 0; i < size; i++) {
                mutableBlockPos = mutableBlockPos.move(direction, i);
                if(!world.getBlockState(mutableBlockPos.down()).isOf(Blocks.GRASS_BLOCK) || !world.isAir(mutableBlockPos.up())) {
                    return false;
                }
            }
            mutableBlockPos = blockpos.mutableCopy();
            for (int i = 0; i < size; i++) {
                world.setBlockState(mutableBlockPos, this.TRUNK.getDefaultState().with(AXIS, direction.getAxis()), 11);
                tryPlaceMoss(world, mutableBlockPos, random);
                mutableBlockPos = mutableBlockPos.move(direction);
            }
        }

        return true;
    }

    private void tryPlaceMoss(StructureWorldAccess world, BlockPos pos, Random random) {
        if(random.nextBoolean() && world.isAir(pos.up(2))) {
            world.setBlockState(pos.up(), Blocks.MOSS_CARPET.getDefaultState(), 11);
        }
    }
}
