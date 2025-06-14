package org.blazers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.blazers.block.BLOxidizable;
import org.hendrix.helper.SoundHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Mixin class for the {@link AxeItem Axe Item}
 */
@Mixin(AxeItem.class)
public final class AxeItemMixin {

    /**
     * Try to decrease the {@link BLOxidizable.OxidationLevel Oxidation Level} of an {@link BLOxidizable Oxidizable Block}
     *
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param player The {@link PlayerEntity Player that is tryinf to scrape the Block}
     * @param state The {@link BlockState current Block State}
     * @param callbackInfoReturnable The {@link Optional<BlockState> Optional Blockstate Callback Info Returnable}
     */
    @Inject(method = "tryStrip", at = @At(value = "HEAD"), cancellable = true)
    private void tryStrip(World world, BlockPos pos, PlayerEntity player, BlockState state, CallbackInfoReturnable<Optional<BlockState>> callbackInfoReturnable) {
        final Optional<BlockState> decreasedOxidationState = BLOxidizable.getDecreasedOxidationState(state);
        if (decreasedOxidationState.isPresent()) {
            SoundHelper.play(player, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS);
            world.syncWorldEvent(player, 3005, pos, 0);
            callbackInfoReturnable.setReturnValue(decreasedOxidationState);
        }
    }
}
