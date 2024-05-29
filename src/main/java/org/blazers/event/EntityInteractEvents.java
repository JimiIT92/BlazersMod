package org.blazers.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.helper.ItemHelper;

/**
 * Handle all events for interacting with an entity
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class EntityInteractEvents {

    /**
     * Toggle the {@link ItemFrame Item Frame} invisibility status
     * when right clicked with some {@link ShearsItem Shears} while sneaking
     *
     * @param event {@link PlayerInteractEvent.EntityInteract The entity interact event}
     */
    @SubscribeEvent
    public static void onEntityInteract(final PlayerInteractEvent.EntityInteract event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final ItemStack itemStack = event.getItemStack();
            if(player.isShiftKeyDown() && itemStack.getItem() instanceof ShearsItem && event.getTarget() instanceof ItemFrame itemFrame) {
                final boolean invisible = !itemFrame.isInvisible();
                itemFrame.setInvisible(invisible);
                event.getLevel().playSound(player, itemFrame.getPos(), invisible ? SoundEvents.ITEM_FRAME_REMOVE_ITEM : SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.75F, 1.0F);
                ItemHelper.hurt(itemStack, player, event.getHand());
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.CONSUME);
            }
        }
    }

}