package org.blazers.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.blazers.block.CopperButtonBlock;
import org.blazers.block.HollowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public final class AxeItemMixin {

    @Inject(method = "useOnBlock", at=@At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> infoReturnable) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();
        BlockState blockState = world.getBlockState(blockPos);
        if(playerEntity.isSneaking() || blockState.getBlock() instanceof HollowBlock) {
            HollowBlock.getHollow(blockState)
                    .ifPresent(state -> replaceBlock(blockState, state.with(Properties.WATERLOGGED, isUnderwater(world, blockPos)), context, world, playerEntity, blockPos, itemStack, SoundEvents.ITEM_AXE_STRIP, infoReturnable));
            Oxidizable.getDecreasedOxidationState(blockState).ifPresent(state -> {
                if(state.getBlock() instanceof CopperButtonBlock) {
                    world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
                    replaceBlock(blockState, state.with(Properties.POWERED, false), context, world, playerEntity, blockPos, itemStack, SoundEvents.ITEM_AXE_SCRAPE, infoReturnable);
                }
            });
            Optional.ofNullable(HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock()))
                    .map(block -> block.getStateWithProperties(blockState))
                    .ifPresent(state -> {
                        if(state.getBlock() instanceof CopperButtonBlock) {
                            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
                            replaceBlock(blockState, state.with(Properties.POWERED, false), context, world, playerEntity, blockPos, itemStack, SoundEvents.ITEM_AXE_WAX_OFF, infoReturnable);
                        }
                    });
        }
    }

    private void replaceBlock(BlockState blockState, BlockState newState, ItemUsageContext context, World world, PlayerEntity playerEntity, BlockPos blockPos, ItemStack itemStack, SoundEvent sound, CallbackInfoReturnable<ActionResult> infoReturnable) {
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
        }
        world.setBlockState(blockPos, newState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, newState));
        if (playerEntity != null) {
            itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
        }
        boolean isClient = world.isClient;
        if(isClient) {
            playerEntity.swingHand(context.getHand());
            playerEntity.playSound(sound, 1.0F, 1.0F);
        }
        infoReturnable.setReturnValue(ActionResult.success(isClient));
    }

    private static boolean isUnderwater(World world, BlockPos pos) {
        return world.getFluidState(pos).isOf(Fluids.WATER) || world.getFluidState(pos.up()).isOf(Fluids.WATER)
                || world.getFluidState(pos.down()).isOf(Fluids.WATER) || world.getFluidState(pos.north()).isOf(Fluids.WATER)
                || world.getFluidState(pos.south()).isOf(Fluids.WATER) || world.getFluidState(pos.east()).isOf(Fluids.WATER)
                || world.getFluidState(pos.west()).isOf(Fluids.WATER);
    }
}