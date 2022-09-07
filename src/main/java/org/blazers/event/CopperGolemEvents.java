package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.WeatheringCopperFullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.WeatheringCutCopperBricks;
import org.blazers.entity.CopperGolem;

/**
 * Event Listener for the {@link CopperGolem Copper Golem} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class CopperGolemEvents {

    /**
     * Summon a {@link CopperGolem Copper Golem} if the {@link Player Player} right click
     * a {@link LightningRodBlock Lightning Rod} on top of a {@link WeatheringCopperFullBlock Copper Block}
     * or a {@link WeatheringCutCopperBricks Cut Copper Bricks Block} with an {@link Items#ECHO_SHARD Echo Shard}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        Level world = event.getLevel();
        BlockPos pos = event.getHitVec().getBlockPos();
        BlockState state = world.getBlockState(pos);
        ItemStack itemStack = event.getItemStack();
        if(itemStack.is(Items.ECHO_SHARD) && state.getBlock() instanceof LightningRodBlock && (state.hasProperty(LightningRodBlock.FACING) && state.getValue(LightningRodBlock.FACING).equals(Direction.UP))) {
            CopperGolem.trySpawnGolem(world, pos, event.getEntity(), itemStack, event.getHand());
        }
    }
}