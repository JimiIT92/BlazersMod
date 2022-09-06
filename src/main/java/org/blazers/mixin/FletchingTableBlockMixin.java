package org.blazers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FletchingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.blazers.inventory.FletchingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FletchingTableBlock.class)
public final class FletchingTableBlockMixin {

    @Inject(method = "onUse", at=@At("HEAD"), cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> infoReturnable) {
        if (world.isClient) {
            infoReturnable.setReturnValue(ActionResult.SUCCESS);
        } else {
            player.openHandledScreen(createFletchingTableScreenHandlerFactory(state, world, pos));
            infoReturnable.setReturnValue(ActionResult.CONSUME);
        }
    }

    public NamedScreenHandlerFactory createFletchingTableScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new FletchingScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), Text.translatable("container.fletching"));
    }
}