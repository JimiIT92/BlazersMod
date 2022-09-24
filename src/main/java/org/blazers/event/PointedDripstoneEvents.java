package org.blazers.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.BLPointedDripstoneBlock;
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
            event.getLevel().setBlock(event.getPos(), BLPointedDripstoneBlock.getDripstoneFor(hitBlockState.getBlock()).withPropertiesOf(blockState), 11);
        }
    }

    /**
     * Play the break sound when the {@link FallingBlockEntity Falling Dripstone} lands
     *
     * @param event {@link EntityLeaveLevelEvent Entity Leave World Event}
     */
    @SubscribeEvent
    public static void onFallingIceDripstoneBroken(final EntityLeaveLevelEvent event) {
        if(event.getEntity() instanceof FallingBlockEntity entity && entity.getBlockState().is(BLBlocks.POINTED_ICE_DRIPSTONE.get())) {
            event.getLevel().playSound(null, entity.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}