package org.blazers.core;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.blazers.BlazersMod;
import org.blazers.block.HollowBlock;
import org.hendrix.helper.WorldHelper;

/**
 * {@link BlazersMod Blazers Mod} Events
 */
public final class BLEvents {

    /**
     * Hollow a {@link PillarBlock Log Block} when right-clicked with an {@link AxeItem Axe}
     *
     * @param player The {@link PlayerEntity Player}
     * @param world The {@link World World reference}
     * @param hand The {@link Hand Hand the Player is using}
     * @param blockHitResult The {@link BlockHitResult Block Hit Result}
     * @return The {@link ActionResult interaction Action Result}
     */
    private static ActionResult hollowLog(final PlayerEntity player, final World world, final Hand hand, final BlockHitResult blockHitResult) {
        final ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.getItem() instanceof AxeItem) {
            final BlockPos blockPos = blockHitResult.getBlockPos();
            final BlockState blockState = world.getBlockState(blockPos);
            if(player.isSneaking() || blockState.getBlock() instanceof HollowBlock) {
                return HollowBlock.getHollow(blockState).map(hollowBlockState -> WorldHelper.setBlock(hollowBlockState, player, hand, world, blockPos, itemStack, SoundEvents.ITEM_AXE_STRIP)).orElse(ActionResult.PASS);
            }
        }
        return ActionResult.PASS;
    }

    /**
     * Register all Events
     */
    public static void register() {
        UseBlockCallback.EVENT.register(BLEvents::hollowLog);
    }
}