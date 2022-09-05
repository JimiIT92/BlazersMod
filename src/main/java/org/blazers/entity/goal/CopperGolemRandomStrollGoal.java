package org.blazers.entity.goal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.blazers.block.CopperButtonBlock;
import org.blazers.entity.CopperGolemEntity;

import java.util.ArrayList;
import java.util.Optional;

public class CopperGolemRandomStrollGoal extends WanderAroundGoal {

    public CopperGolemRandomStrollGoal(CopperGolemEntity mob) {
        super(mob, 1.0D, 120);
    }

    @Override
    public void stop() {
        super.stop();
        getRandomNearbyCopperButton().ifPresent(copperButton -> {
            CopperGolemEntity copperGolem = ((CopperGolemEntity)this.mob);
            if(copperGolem.canPressCopperButton() && !copperGolem.isPressingCopperButton()) {
                Path path = this.mob.getNavigation().findPathTo(copperButton, 2);
                if(path != null) {
                    this.mob.getNavigation().startMovingAlong(path, this.mob.speed);
                    World world = this.mob.world;
                    BlockState state = world.getBlockState(copperButton);
                    copperGolem.setPressingCopperButton(true);
                    ((CopperButtonBlock)state.getBlock()).powerOn(state, world, copperButton);
                }
            }
        });
    }

    private Optional<BlockPos> getRandomNearbyCopperButton() {
        ArrayList<BlockPos> copperButtons = new ArrayList<>();
        World world = this.mob.getWorld();
        BlockPos pos = this.mob.getBlockPos();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos pos1 = pos.add(x, y, z);
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
