package org.blazers.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLItems;

/**
 * Event Listener for the {@link Float FOV} related events
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = BlazersMod.MOD_ID, value = Dist.CLIENT)
public final class FOVModifier {

    /**
     * Change the FOV if the {@link Player Player}
     * is using a {@link BLItems#CARBON_BOW Carbon Bow}
     *
     * @param event {@link FOVModifierEvent FOV Modifier Event}
     */
    @SubscribeEvent
    public static void changeFov(final FOVModifierEvent event) {
        Player player = event.getEntity();
        if(player.getUseItem().getItem().equals(BLItems.CARBON_BOW.get())) {
            event.setNewfov(BLItems.getCarbonBowFieldOfView(player));
        }
    }
}