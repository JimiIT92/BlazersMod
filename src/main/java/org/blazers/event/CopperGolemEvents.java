package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.entity.CopperGolem;

/**
 * Handle all events for {@link CopperGolem Copper Golem}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class CopperGolemEvents {

    /**
     * Try to summon a {@link CopperGolem Copper Golem}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Level level = event.getLevel();
            final BlockPos blockPos = event.getHitVec().getBlockPos();
            final BlockState blockState = level.getBlockState(blockPos);
            final ItemStack itemStack = event.getItemStack();
            if(itemStack.is(Items.ECHO_SHARD) && blockState.getBlock() instanceof LightningRodBlock && (blockState.hasProperty(LightningRodBlock.FACING) && blockState.getValue(LightningRodBlock.FACING).equals(Direction.UP))) {
                CopperGolem.trySpawnGolem(level, blockPos, event.getEntity(), itemStack, event.getHand());
            }
        }
    }

}