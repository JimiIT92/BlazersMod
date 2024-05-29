package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.client.screen.FletchingScreen;
import org.blazers.inventory.FletchingMenu;

/**
 * Handle all events for {@link Blocks#FLETCHING_TABLE Fletching Table}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class FletchingTableEvents {

    /**
     * Show the {@link FletchingScreen Fletching Screen} when right clicking on a {@link Blocks#FLETCHING_TABLE Fletching Table}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final BlockPos blockPos = event.getPos();
            final Level level = event.getLevel();
            final BlockState blockState = level.getBlockState(blockPos);
            if(blockState.is(Blocks.FLETCHING_TABLE)) {
                event.setCanceled(true);
                if(level.isClientSide) {
                    event.setCancellationResult(InteractionResult.SUCCESS);
                    return;
                }
                event.getEntity().openMenu(new SimpleMenuProvider((id, inventory, player) -> new FletchingMenu(id, inventory, ContainerLevelAccess.create(level, blockPos)), Component.translatable("container.fletching")));
                event.setCancellationResult(InteractionResult.CONSUME);
            }
        }
    }

}