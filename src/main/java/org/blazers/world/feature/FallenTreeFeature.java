package org.blazers.world.feature;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.blazers.BlazersMod;

import java.util.Random;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

/**
 * {@link BlazersMod Blazers Mod} {@link Feature Fallen Tree Feature} implementation class
 */
public final class FallenTreeFeature extends Feature<ProbabilityFeatureConfiguration> {

    /**
     * {@link Supplier<BlockState> The Supplier for the Log Block State}
     */
    private final Supplier<BlockState> logBlockStateSupplier;

    /**
     * Constructor. Set the {@link Feature Feature} properties
     *
     * @param logBlockStateSupplier {@link Supplier<BlockState> The Supplier for the Log Block State}
     */
    public FallenTreeFeature(final Supplier<BlockState> logBlockStateSupplier) {
        super(ProbabilityFeatureConfiguration.CODEC);
        this.logBlockStateSupplier = logBlockStateSupplier;
    }

    /**
     * Place the Fallen Tree inside the {@link Level Level}
     *
     * @param context {@link FeaturePlaceContext<ProbabilityFeatureConfiguration> The Feature Place Context}
     * @return {@link Boolean True} if the Fallen Tree has been placed
     */
    @Override
    public boolean place(final FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        final RandomSource random = context.random();
        final WorldGenLevel level = context.level();
        final BlockPos blockPos = context.origin();
        final ProbabilityFeatureConfiguration config = context.config();
        if(random.nextDouble() < (double)config.probability) {
            final int size = 3 + random.nextInt(2);
            final Direction direction = Util.getRandom(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}, random);
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            for (int i = 0; i < size; i++) {
                mutableBlockPos = mutableBlockPos.move(direction, i);
                if(!level.getBlockState(mutableBlockPos.below()).is(Blocks.GRASS_BLOCK) || !level.isEmptyBlock(mutableBlockPos.above())) {
                    return false;
                }
            }
            mutableBlockPos = blockPos.mutable();
            for (int i = 0; i < size; i++) {
                BlockState logBlockState = this.logBlockStateSupplier.get();
                if(logBlockState.hasProperty(AXIS)) {
                    logBlockState = logBlockState.setValue(AXIS, direction.getAxis());
                }
                level.setBlock(mutableBlockPos, logBlockState, 11);
                tryPlaceMoss(level, mutableBlockPos, random);
                mutableBlockPos = mutableBlockPos.move(direction);
            }
        }

        return true;
    }

    /**
     * Try place {@link Blocks#MOSS_CARPET Moss carpets} on top of Logs
     *
     * @param level {@link WorldGenLevel The Level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param random {@link Random The Random variable}
     */
    private void tryPlaceMoss(final WorldGenLevel level, final BlockPos blockPos, final RandomSource random) {
        if(random.nextBoolean() && level.isEmptyBlock(blockPos.above(2))) {
            level.setBlock(blockPos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 11);
        }
    }

}