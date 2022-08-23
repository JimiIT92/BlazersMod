package org.blazers.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;

/**
 * Event Listener for the {@link PlayerInteractEvent.EntityInteract Entity Interact} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class EntityInteractEvents {

    /**
     * Toggle the {@link ItemFrame Item Frame Invisibility}
     * or remove the {@link ArmorStand Armor Stand arms}
     * if the {@link Player Player} shift right-click with some {@link ShearsItem Shears}
     *
     * @param event {@link PlayerInteractEvent.EntityInteract Entity Interact Event}
     */
    @SubscribeEvent
    public static void onInteract(final PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        Level world = event.getLevel();
        if(player.isShiftKeyDown() && item instanceof ShearsItem && entity instanceof ItemFrame itemFrame) {
            boolean invisible = !itemFrame.isInvisible();
            itemFrame.setInvisible(invisible);
            world.playSound(player, itemFrame.getPos(), invisible ? SoundEvents.ITEM_FRAME_REMOVE_ITEM : SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.75F, 1.0F);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.CONSUME);
        }
    }
}