package org.blazers.client.event;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLItems;
import org.blazers.item.CarbonBowItem;

/**
 * Handle all events for {@link Float FOV}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, value = Dist.CLIENT)
public final class FovEvents {

    /**
     * Change the {@link Float FOV} when the {@link Player Player} pulls a {@link BowItem Bow}
     *
     * @param event {@link ComputeFovModifierEvent The compute FOV modifier event}
     */
    @SubscribeEvent
    public static void onFovModifier(final ComputeFovModifierEvent event) {
        final Player player = event.getPlayer();
        if(player.getUseItem().is(BLItems.CARBON_BOW.get())) {
            event.setNewFovModifier(getCarbonBowFieldOfView(player));
        }
    }

    /**
     * Get a custom {@link Float FOV} when using a {@link BLItems#CARBON_BOW Carbon Bow}
     *
     * @param player {@link Player The Player instance}
     * @return The {@link BLItems#CARBON_BOW Carbon Bow} {@link Float FOV}
     */
    private static float getCarbonBowFieldOfView(final Player player) {
        float fov = 1F;
        if (player.getAbilities().flying) {
            fov *= 1.1F;
        }
        fov *= ((float)player.getAttributeValue(Attributes.MOVEMENT_SPEED) / player.getAbilities().getWalkingSpeed() + 1F) / 2F;
        if (player.getAbilities().getWalkingSpeed() == 0F || Float.isNaN(fov) || Float.isInfinite(fov)) {
            fov = 1F;
        }
        float useItemTicksLeft = (float)player.getTicksUsingItem() / CarbonBowItem.DRAW_SPEED;
        if (useItemTicksLeft > 1F) {
            useItemTicksLeft = 1F;
        } else {
            useItemTicksLeft *= useItemTicksLeft;
        }

        fov *= 1F - useItemTicksLeft * 0.15F;
        return fov;
    }

}