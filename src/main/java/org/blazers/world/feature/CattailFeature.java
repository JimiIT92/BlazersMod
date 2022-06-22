package org.blazers.world.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.blazers.core.BLBlocks;

import java.util.Random;

/**
 * Place {@link org.blazers.block.CattailBlock Cattails} inside the {@link net.minecraft.world.level.Level World}
 */
public class CattailFeature extends Feature<ProbabilityFeatureConfiguration> {

    /**
     * Constructor. Sets the {@link ProbabilityFeatureConfiguration#CODEC Probability Feature Configuration Codec}
     */
    public CattailFeature() {
        super(ProbabilityFeatureConfiguration.CODEC);
    }

    /**
     * Place the {@link org.blazers.block.CattailBlock Cattails} inside the {@link net.minecraft.world.level.Level World}
     *
     * @param context {@link FeaturePlaceContext<ProbabilityFeatureConfiguration> Place Context}
     * @return {@link Boolean True} if the {@link org.blazers.block.CattailBlock Cattail} has been placed
     */
    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        boolean flag = false;
        Random random = context.random();
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        ProbabilityFeatureConfiguration probabilityfeatureconfiguration = context.config();
        int i = random.nextInt(8) - random.nextInt(8);
        int j = random.nextInt(8) - random.nextInt(8);
        int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
        BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
            boolean flag1 = random.nextDouble() < (double)probabilityfeatureconfiguration.probability;
            BlockState blockstate = BLBlocks.CATTAIL.get().defaultBlockState();
            if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
                if (flag1) {
                    BlockState blockstate1 = blockstate.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
                    BlockPos blockpos2 = blockpos1.above();
                    if (worldgenlevel.getBlockState(blockpos2).is(Blocks.WATER)) {
                        worldgenlevel.setBlock(blockpos1, blockstate, 2);
                        worldgenlevel.setBlock(blockpos2, blockstate1, 2);
                    }
                } else {
                    worldgenlevel.setBlock(blockpos1, blockstate, 2);
                }

                flag = true;
            }
        }

        return flag;
    }
}