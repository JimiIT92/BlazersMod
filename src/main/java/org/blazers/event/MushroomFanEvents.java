package org.blazers.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;

import static net.minecraft.world.level.block.BaseCoralPlantTypeBlock.WATERLOGGED;
import static net.minecraft.world.level.block.BaseCoralWallFanBlock.FACING;

/**
 * Event Listener for the {@link BaseCoralWallFanBlock Mushroom Fan} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class MushroomFanEvents {

    /**
     * Place a {@link BaseCoralWallFanBlock Mushroom Fan} if
     * the {@link net.minecraft.world.entity.player.Player Player} right click a block with a {@link net.minecraft.world.item.Item Mushroom Item}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Direction face = event.getFace();
        boolean isValidFace = face != null && face != Direction.DOWN && face != Direction.UP;
        Level world = event.getWorld();
        BlockPos pos = event.getPos();
        if(stack.is(Tags.Items.MUSHROOMS) && isValidFace && world.getBlockState(pos).isFaceSturdy(world, pos, face)) {
            Player player = event.getPlayer();
            Block block = (stack.is(Items.RED_MUSHROOM) ? BLBlocks.RED_MUSHROOM_WALL_FAN : BLBlocks.BROWN_MUSHROOM_WALL_FAN).get();
            player.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.0F);
            world.setBlockAndUpdate(pos.offset(face.getNormal()), block.defaultBlockState()
                            .setValue(WATERLOGGED, false)
                            .setValue(FACING, face)
            );
            if(!player.isCreative()) {
                stack.shrink(1);
            }
        }
    }
}