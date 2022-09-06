package org.blazers.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.blazers.core.BLBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public final class EntityMixin {

    Entity entity = (Entity) (Object)this;

    @Inject(method = "discard", at=@At("RETURN"))
    public void discard(CallbackInfo info) {
        if(entity instanceof FallingBlockEntity fallingBlockEntity && fallingBlockEntity.getBlockState().isOf(BLBlocks.POINTED_ICE_DRIPSTONE)) {
            fallingBlockEntity.getWorld().playSound(null, fallingBlockEntity.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }
}