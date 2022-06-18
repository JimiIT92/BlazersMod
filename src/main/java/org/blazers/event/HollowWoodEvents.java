package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;

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
    public static void onFOVModifier(final PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        ItemStack stack = event.getItemStack();
        if(player.isShiftKeyDown() && stack.getItem() instanceof AxeItem) {
            Level world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);
            Block block = Blocks.AIR;
            if(state.is(Blocks.OAK_LOG)) {
                block = BLBlocks.HOLLOW_OAK_LOG.get();
            } else if(state.is(Blocks.SPRUCE_LOG)) {
                block = BLBlocks.HOLLOW_SPRUCE_LOG.get();
            } else if(state.is(Blocks.BIRCH_LOG)) {
                block = BLBlocks.HOLLOW_BIRCH_LOG.get();
            } else if(state.is(Blocks.JUNGLE_LOG)) {
                block = BLBlocks.HOLLOW_JUNGLE_LOG.get();
            } else if(state.is(Blocks.ACACIA_LOG)) {
                block = BLBlocks.HOLLOW_ACACIA_LOG.get();
            } else if(state.is(Blocks.DARK_OAK_LOG)) {
                block = BLBlocks.HOLLOW_DARK_OAK_LOG.get();
            } else if(state.is(Blocks.WARPED_STEM)) {
                block = BLBlocks.HOLLOW_WARPED_STEM.get();
            } else if(state.is(Blocks.CRIMSON_STEM)) {
                block = BLBlocks.HOLLOW_CRIMSON_STEM.get();
            } else if(state.is(Blocks.STRIPPED_OAK_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_OAK_LOG.get();
            } else if(state.is(Blocks.STRIPPED_SPRUCE_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG.get();
            } else if(state.is(Blocks.STRIPPED_BIRCH_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_BIRCH_LOG.get();
            } else if(state.is(Blocks.STRIPPED_JUNGLE_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG.get();
            } else if(state.is(Blocks.STRIPPED_ACACIA_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_ACACIA_LOG.get();
            } else if(state.is(Blocks.STRIPPED_DARK_OAK_LOG)) {
                block = BLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG.get();
            } else if(state.is(Blocks.STRIPPED_WARPED_STEM)) {
                block = BLBlocks.HOLLOW_STRIPPED_WARPED_STEM.get();
            } else if(state.is(Blocks.STRIPPED_CRIMSON_STEM)) {
                block = BLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.get();
            }
            if(!Blocks.AIR.equals(block)) {
                world.setBlockAndUpdate(pos, block.withPropertiesOf(state));
                if(!player.isCreative()) {
                    stack.shrink(1);
                }
                event.setUseItem(Event.Result.DENY);
            }
        }
    }
}