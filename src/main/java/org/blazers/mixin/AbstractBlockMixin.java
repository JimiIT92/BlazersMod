package org.blazers.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.blazers.core.BLBlocks;
import org.blazers.entity.CopperGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public final class AbstractBlockMixin {

    @Inject(method = "onUse", at=@At("HEAD"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> infoReturnable) {
        ItemStack stack = player == null ? ItemStack.EMPTY : player.getStackInHand(hand);
        if(!stack.isEmpty()) {
            Direction face = hit.getSide();
            if(stack.isOf(Items.ECHO_SHARD) && state.isOf(Blocks.LIGHTNING_ROD) && state.get(Properties.FACING).equals(Direction.UP)) {
                CopperGolemEntity.trySpawnGolem(world, pos, player, stack, hand);
            } else if(canPlaceMushroom(stack, Items.BROWN_MUSHROOM, state, world, pos, face)) {
                placeMushroomFan(BLBlocks.BROWN_MUSHROOM_WALL_FAN, stack, player, world, pos, face, hand);
            } else if(canPlaceMushroom(stack, Items.RED_MUSHROOM, state, world, pos, face)) {
                placeMushroomFan(BLBlocks.RED_MUSHROOM_WALL_FAN, stack, player, world, pos, face, hand);
            }
        }
    }

    private void placeMushroomFan(Block mushroom, ItemStack stack, PlayerEntity player, World world, BlockPos pos, Direction direction, Hand hand) {
        if(!world.isClient) {
            BlockPos placePos = pos.offset(direction);
            world.setBlockState(placePos, mushroom.getDefaultState()
                    .with(Properties.WATERLOGGED, world.getFluidState(placePos).isOf(Fluids.WATER))
                    .with(HorizontalFacingBlock.FACING, direction)
            );
            if(!player.isCreative()) {
                stack.decrement(1);
            }
        } else {
            player.swingHand(hand);
            player.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1.0F, 1.0F);
        }
    }

    private boolean canPlaceMushroom(ItemStack stack, Item mushroom, BlockState state, World world, BlockPos pos, Direction direction) {
        return stack.isOf(mushroom) && isValidFace(direction) && state.isSideSolidFullSquare(world, pos, direction);
    }

    private boolean isValidFace(Direction face) {
        return face != Direction.UP && face != Direction.DOWN;
    }
}