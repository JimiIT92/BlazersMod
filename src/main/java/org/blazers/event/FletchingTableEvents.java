package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.inventory.FletchingMenu;

/**
 * Event Listener for the {@link Blocks#FLETCHING_TABLE Fletching Table} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class FletchingTableEvents {

    /**
     * Open the {@link Blocks#FLETCHING_TABLE Fletching Table} GUI
     * if the {@link Player Player} right clicks it
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Right Click Button Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        Level world = event.getWorld();
        BlockState state = world.getBlockState(pos);
        if(state.is(Blocks.FLETCHING_TABLE)) {
            event.setCanceled(true);
            if(world.isClientSide) {
                event.setCancellationResult(InteractionResult.SUCCESS);
            } else {
                Player player = event.getPlayer();
                player.openMenu(getMenuProvider(world, pos));
                event.setCancellationResult(InteractionResult.CONSUME);
            }
        }
    }

    /**
     * Get the {@link Blocks#FLETCHING_TABLE Fletching Table} {@link MenuProvider Menu Provider}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Blocks#FLETCHING_TABLE Fletching Table} {@link MenuProvider Menu Provider}
     */
    private static MenuProvider getMenuProvider(Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new FletchingMenu(id, inventory, ContainerLevelAccess.create(level, pos)),
                new TranslatableComponent("container.fletching"));
    }
}