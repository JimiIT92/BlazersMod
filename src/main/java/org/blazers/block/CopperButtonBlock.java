package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class CopperButtonBlock extends ButtonBlock {

    public CopperButtonBlock() {
        super(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.COPPER), 40, false, SoundEvents.BLOCK_COPPER_BREAK, SoundEvents.BLOCK_COPPER_PLACE);
    }
//
//    @Override
//    public void powerOn(BlockState state, World world, BlockPos pos) {
//        world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_ALL);
//        this.updateNeighbors(state, world, pos);
//        world.createAndScheduleBlockTick(pos, this, 40);
//    }
//
//    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
//        world.updateNeighborsAlways(pos, this);
//        world.updateNeighborsAlways(pos.offset(AbstractButtonBlock.getDirection(state).getOpposite()), this);
//    }
}
