package org.blazers.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import org.blazers.block.CopperButtonBlock;
import org.blazers.entity.CopperGolem;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Make the {@link CopperGolem Copper Golem} randomly stroll around and push {@link CopperButtonBlock Copper Buttons}
 */
public class CopperGolemRandomStrollGoal extends RandomStrollGoal {

    /**
     * Constructors. Sets the {@link Float Speed Modifier} and {@link Integer Update Interval}
     *
     * @param copperGolem {@link CopperGolem Copper Golem}
     */
    public CopperGolemRandomStrollGoal(final CopperGolem copperGolem) {
        super(copperGolem, 1.0D, 120);
    }

    /**
     * Push a {@link CopperButtonBlock Copper Button} if is in range
     * when the {@link CopperGolem Copper Golem} stops
     */
    @Override
    public void stop() {
        super.stop();
        getRandomNearbyCopperButton().ifPresent(copperButton -> {
            CopperGolem copperGolem = ((CopperGolem)this.mob);
            if(!copperGolem.isPressingCopperButton()) {
                Path path = this.mob.getNavigation().createPath(copperButton, 2);
                if(path != null) {
                    path.advance();
                    Level world = this.mob.getLevel();
                    BlockState state = world.getBlockState(copperButton);
                    copperGolem.setPressingCopperButton(true);
                    ((CopperButtonBlock)state.getBlock()).press(state, world, copperButton);
                }
            }
        });
    }

    /**
     * Get the nearby {@link CopperButtonBlock Copper Buttons}
     *
     * @return {@link Optional<Block> Random Nearby Copper Button}
     */
    private Optional<BlockPos> getRandomNearbyCopperButton() {
        ArrayList<BlockPos> copperButtons = new ArrayList<>();
        Level world = this.mob.getLevel();
        BlockPos pos = this.mob.blockPosition();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos pos1 = pos.offset(x, y, z);
                    BlockState state = world.getBlockState(pos1);
                    if(state.getBlock() instanceof CopperButtonBlock) {
                        copperButtons.add(pos1);
                    }
                }
            }
        }

        return copperButtons.isEmpty() ? Optional.empty() : Optional.of(copperButtons.get(this.mob.getRandom().nextInt(copperButtons.size())));
    }
}
