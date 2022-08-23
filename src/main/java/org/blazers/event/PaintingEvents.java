package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLPaintings;
import org.blazers.core.BLSounds;

/**
 * Event Listener for the {@link net.minecraft.world.entity.decoration.Painting Painting} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class PaintingEvents {

    /**
     * Play a {@link SoundEvents Sound} when the painting is placed
     *
     * @param event {@link EntityJoinLevelEvent Entity Join World Event}
     */
    @SubscribeEvent
    public static void onPaintingPlaced(final EntityJoinLevelEvent event) {
        if(event.getEntity() instanceof Painting painting && painting.getVariant().get().equals((BLPaintings.BRUH.get())) && !event.loadedFromDisk()) {
            playSound(event.getLevel(), painting.getPos(), BLSounds.BRUH.get());
        }
    }

    /**
     * Play a {@link SoundEvents Sound} when the painting is destroyed
     *
     * @param event {@link EntityLeaveLevelEvent Entity Leave World Event}
     */
    @SubscribeEvent
    public static void onPaintingDestroyed(final EntityLeaveLevelEvent event) {
        if(event.getEntity() instanceof Painting painting && painting.getVariant().get().equals((BLPaintings.BRUH.get()))) {
            playSound(event.getLevel(), painting.getPos(), BLSounds.REVERSED_BRUH.get());
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