package org.blazers.event;

import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.weathering.BLWeatheringBlock;
import org.blazers.block.weathering.IBLWeatheringBlock;

/**
 * Handle all events for {@link LightningBolt Lightning Bolts}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class LightningBoltEvents {

    /**
     * Clear some oxidization from {@link BLWeatheringBlock weathering Blocks} when struck by a {@link LightningBolt Lightning Bolt}
     *
     * @param event {@link VanillaGameEvent The Vanilla Game Event}
     */
    @SubscribeEvent
    public static void onVanillaGameEvent(final VanillaGameEvent event) {
        if(!event.isCanceled() && event.getVanillaEvent().equals(GameEvent.LIGHTNING_STRIKE.value()) && event.getCause() instanceof LightningBolt lightningBolt) {
            final BlockState blockState = lightningBolt.getBlockStateOn();
            if(blockState.getBlock() instanceof IBLWeatheringBlock) {
                IBLWeatheringBlock.lightningStrike(blockState, event.getLevel(), lightningBolt.getOnPos());
            }
        }
    }

}