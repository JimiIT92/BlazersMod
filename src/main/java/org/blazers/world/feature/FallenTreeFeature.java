package org.blazers.world.feature;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import java.util.Random;

import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

/**
 * Place Fallen Trees inside the {@link net.minecraft.world.level.Level World}
 */
public class FallenTreeFeature extends Feature<ProbabilityFeatureConfiguration> {

    /**
     * Trunk {@link Block Block}
     */
    private final Block TRUNK;

    /**
     * Constructor. Sets the {@link ProbabilityFeatureConfiguration#CODEC Tree Configuration Codec}
     *
     * @param block Log Block
     */
    public FallenTreeFeature(Block block) {
        super(ProbabilityFeatureConfiguration.CODEC);
        this.TRUNK = block;
    }

    /**
     * Place the Fallen Tree inside the {@link net.minecraft.world.level.Level World}
     *
     * @param context {@link FeaturePlaceContext<ProbabilityFeatureConfiguration> Place Context}
     * @return {@link Boolean True} if the Fallen Tree has been placed
     */
    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        Random random = context.random();
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        ProbabilityFeatureConfiguration probabilityfeatureconfiguration = context.config();
        if(random.nextDouble() < (double)probabilityfeatureconfiguration.probability) {
            int size = 3 + random.nextInt(2);
            Direction direction = Util.getRandom(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}, random);
            BlockPos.MutableBlockPos mutableBlockPos = blockpos.mutable();
            for (int i = 0; i < size; i++) {
                mutableBlockPos = mutableBlockPos.move(direction, i);
                if(!worldgenlevel.getBlockState(mutableBlockPos.below()).is(Blocks.GRASS_BLOCK) || !worldgenlevel.isEmptyBlock(mutableBlockPos.above())) {
                    return false;
                }
            }
            mutableBlockPos = blockpos.mutable();
            for (int i = 0; i < size; i++) {
                worldgenlevel.setBlock(mutableBlockPos, this.TRUNK.defaultBlockState().setValue(AXIS, direction.getAxis()), 11);
                tryPlaceMoss(worldgenlevel, mutableBlockPos, random);
                mutableBlockPos = mutableBlockPos.move(direction);
            }
        }

        return true;
    }

    /**
     * Try place {@link Blocks#MOSS_CARPET Moss carpets} on top of trunk logs
     *
     * @param level {@link WorldGenLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link Random Random variable}
     */
    private void tryPlaceMoss(WorldGenLevel level, BlockPos pos, Random random) {
        if(random.nextBoolean() && level.isEmptyBlock(pos.above(2))) {
            level.setBlock(pos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 11);
        }
    }

}