package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;

/**
 * Event Listener for the {@link Blocks#POINTED_DRIPSTONE Pointed Dripstone} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class PointedDripstoneEvents {

    /**
     * Place a {@link net.minecraft.world.level.block.PointedDripstoneBlock Variant Pointed Dripstone}
     * based on where it's placed
     *
     * @param event {@link BlockEvent.EntityPlaceEvent Entity Place Block Event}
     */
    @SubscribeEvent
    public static void onBlockPlaced(final BlockEvent.EntityPlaceEvent event) {
        BlockState blockState = event.getPlacedBlock();
        BlockState hitBlockState = event.getPlacedAgainst();
        if(blockState.is(Blocks.POINTED_DRIPSTONE) && event.getEntity() instanceof Player player && !player.isShiftKeyDown()) {
            LevelAccessor world = event.getWorld();
            BlockPos pos = event.getPos();
            if(hitBlockState.is(Blocks.ICE)) {
                world.setBlock(pos, BLBlocks.POINTED_ICE_DRIPSTONE.get().withPropertiesOf(blockState), 11);
            } else if(hitBlockState.is(Blocks.STONE)) {
                world.setBlock(pos, BLBlocks.POINTED_STONE_DRIPSTONE.get().withPropertiesOf(blockState), 11);
            }
        }
    }
}