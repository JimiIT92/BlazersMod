package org.blazers.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.HollowLogBlock;
import org.blazers.helper.ItemHelper;
import org.blazers.helper.LevelHelper;

/**
 * Handle all events for {@link HollowLogBlock Hollow Logs}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class HollowLogEvents {

    /**
     * Hollow a {@link RotatedPillarBlock Log} when the {@link Player Player} right click with an {@link AxeItem Axe} while sneaking
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final BlockPos clickedPos = event.getPos();
            final ItemStack itemStack = event.getItemStack();
            final Level level = event.getLevel();
            if(player.isShiftKeyDown() && itemStack.is(ItemTags.AXES)) {
                HollowLogBlock.getHollow(level.getBlockState(clickedPos)).ifPresent(hollowState -> {
                    level.setBlockAndUpdate(clickedPos, hollowState.setValue(BlockStateProperties.WATERLOGGED, LevelHelper.isUnderwater(level, clickedPos)));
                    ItemHelper.hurt(itemStack, player, event.getHand(), SoundEvents.AXE_STRIP);
                    if(player instanceof ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, clickedPos, itemStack);
                    }
                    event.setUseItem(Event.Result.DENY);
                });
            }
        }
    }

}