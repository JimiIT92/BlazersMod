package org.blazers.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.HollowBlock;

import static org.blazers.block.HollowBlock.WATERLOGGED;

/**
 * Event Listener for the {@link org.blazers.block.HollowBlock Hollow Wood Block} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class HollowWoodEvents {

    /**
     * Change a {@link net.minecraft.world.level.block.Block Wood Block} with
     * the corresponding {@link org.blazers.block.HollowBlock Hollow Wood Block}
     * if the {@link Player Player} shifts-right click with an {@link net.minecraft.world.item.AxeItem Axe}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        Level world = event.getLevel();
        if(player.isShiftKeyDown() && stack.getItem() instanceof AxeItem) {
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);
            HollowBlock.getHollow(state).ifPresent(hollowState -> {
                world.setBlockAndUpdate(pos, hollowState.setValue(WATERLOGGED, isUnderwater(world, pos)));
                if(!player.isCreative()) {
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(event.getHand()));
                }
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
                }
                event.setUseItem(Event.Result.DENY);
                if(world.isClientSide()) {
                    player.swing(event.getHand());
                    player.playSound(SoundEvents.AXE_STRIP, 1.0F, 1.0F);
                }
            });
        }
    }

    /**
     * Check if the {@link Block Block} is underwater
     *
     * @param world {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link Block Block} is underwater
     */
    private static boolean isUnderwater(Level world, BlockPos pos) {
        return world.getFluidState(pos).is(Fluids.WATER) || world.getFluidState(pos.above()).is(Fluids.WATER)
                || world.getFluidState(pos.below()).is(Fluids.WATER) || world.getFluidState(pos.north()).is(Fluids.WATER)
                || world.getFluidState(pos.south()).is(Fluids.WATER) || world.getFluidState(pos.east()).is(Fluids.WATER)
                || world.getFluidState(pos.west()).is(Fluids.WATER);
    }
}