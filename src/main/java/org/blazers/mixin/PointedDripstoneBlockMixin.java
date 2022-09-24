package org.blazers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import org.blazers.block.BLPointedDripstoneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointedDripstoneBlock.class)
public final class PointedDripstoneBlockMixin {

    @Inject(method = "getPlacementState", at=@At("RETURN"), cancellable = true)
    public void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> callbackInfoReturnable) {
        BlockState dripstone = callbackInfoReturnable.getReturnValue();
        if(dripstone != null) {
            PlayerEntity player = context.getPlayer();
            if(player != null && !player.isSneaking()) {
                BlockState ground = context.getWorld().getBlockState(context.getBlockPos().offset(context.getSide().getOpposite()));
                callbackInfoReturnable.setReturnValue(BLPointedDripstoneBlock.getDripstoneFor(ground.getBlock()).getStateWithProperties(dripstone));
            }
        }
    }
}