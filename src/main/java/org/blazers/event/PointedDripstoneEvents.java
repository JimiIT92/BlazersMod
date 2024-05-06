package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.BLPointedDripstoneBlock;
import org.blazers.block.PointedIceDripstoneBlock;

import java.util.Objects;

/**
 * Handle all events for {@link PointedDripstoneBlock Pointed Dripstone Blocks}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class PointedDripstoneEvents {

    /**
     * Place the correct {@link BLPointedDripstoneBlock Pointed Dripstone Block}
     *
     * @param event {@link BlockEvent.EntityPlaceEvent The Entity Place Block Event}
     */
    @SubscribeEvent
    public static void onEntityPlace(final BlockEvent.EntityPlaceEvent event) {
        if(!event.isCanceled()) {
            final Entity placer = event.getEntity();
            final Level level = Objects.requireNonNull(placer).level();
            final BlockPos blockPos = event.getPos();
            final BlockState placedBlock = event.getPlacedBlock();
            if(placer instanceof Player player && !event.getPlacedAgainst().isAir()) {
                final BlockState hitBlockState = event.getPlacedAgainst();
                if(placedBlock.is(Blocks.POINTED_DRIPSTONE) && !player.isShiftKeyDown()) {
                    level.setBlockAndUpdate(blockPos, BLPointedDripstoneBlock.getDripstoneFor(hitBlockState.getBlock()).withPropertiesOf(placedBlock));
                }
            }
        }
    }

    /**
     * Play the {@link SoundEvents#GLASS_BREAK Glass Break sound} when an {@link PointedIceDripstoneBlock Ice Pointed Dripstone} lands
     *
     * @param event {@link EntityLeaveLevelEvent The Entity Leave Level Event}
     */
    @SubscribeEvent
    public static void onEntityLeaveLevel(final EntityLeaveLevelEvent event) {
        if(!event.isCanceled() && event.getEntity() instanceof FallingBlockEntity entity && entity.getBlockState().getBlock() instanceof PointedIceDripstoneBlock) {
            event.getLevel().playSound(null, entity.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

}