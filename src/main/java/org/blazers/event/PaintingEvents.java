package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLPaintings;
import org.blazers.core.BLSounds;

/**
 * Event Listener for the {@link net.minecraft.world.entity.decoration.Motive Painting} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class PaintingEvents {

    /**
     * Play a {@link SoundEvents Sound} when the painting is placed
     *
     * @param event {@link EntityJoinWorldEvent Entity Join World Event}
     */
    @SubscribeEvent
    public static void onPaintingPlaced(final EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof Painting painting && painting.motive.equals((BLPaintings.BRUH.get()))) {
            playSound(event.getWorld(), painting.getPos(), BLSounds.BRUH.get());
        }
    }

    /**
     * Play a {@link SoundEvents Sound} when the painting is destroyed
     *
     * @param event {@link EntityLeaveWorldEvent Entity Leave World Event}
     */
    @SubscribeEvent
    public static void onPaintingDestroyed(final EntityLeaveWorldEvent event) {
        if(event.getEntity() instanceof Painting painting && painting.motive.equals((BLPaintings.BRUH.get()))) {
            playSound(event.getWorld(), painting.getPos(), BLSounds.REVERSED_BRUH.get());
        }
    }

    /**
     * Play a {@link SoundEvents Sound} when the painting is placed or break
     *
     * @param world {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param sound {@link SoundEvent Sound}
     */
    private static void playSound(Level world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}