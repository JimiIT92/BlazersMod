package org.blazers.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.WeatheringCutCopperBricks;
import org.blazers.core.BLEntityTypes;
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
            Block below = world.getBlockState(pos.below()).getBlock();
            if(below instanceof WeatheringCopperFullBlock || below instanceof WeatheringCutCopperBricks) {
                Player player = event.getEntity();
                world.destroyBlock(pos, false);
                world.destroyBlock(pos.below(), false);
                CopperGolem copperGolem = new CopperGolem(BLEntityTypes.COPPER_GOLEM.get(), world);
                copperGolem.setPos(pos.getX() + 0.5F, pos.getY() - 0.5F, pos.getZ() + 0.5F);
                copperGolem.setWeatherState(((ChangeOverTimeBlock<WeatheringCopper.WeatherState>)below).getAge(), false);
                copperGolem.setPersistenceRequired();

                EventUtils.fireWorldEvent(world, player, EventUtils.WorldEvents.AXE_SCRAPE, pos);
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE);
                player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemStack);
                }
                if(!player.isCreative()) {
                    itemStack.shrink(1);
                }
                if(world.isClientSide()) {
                    player.swing(event.getHand());
                }
                world.addFreshEntity(copperGolem);
            }
        }
    }
}