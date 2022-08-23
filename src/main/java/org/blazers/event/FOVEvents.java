package org.blazers.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLItems;

/**
 * Event Listener for the {@link Float FOV} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, value = Dist.CLIENT)
public final class FOVEvents {

    /**
     * Change the FOV if the {@link Player Player}
     * is using a {@link BLItems#CARBON_BOW Carbon Bow}
     *
     * @param event {@link ComputeFovModifierEvent FOV Modifier Event}
     */
    @SubscribeEvent
    public static void onFOVModifier(final ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        if(player.getUseItem().getItem().equals(BLItems.CARBON_BOW.get())) {
            event.setNewFovModifier(BLItems.getCarbonBowFieldOfView(player));
        }
    }
}