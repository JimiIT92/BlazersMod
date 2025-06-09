package org.blazers.mixin;

import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.sound.SoundCategory;
import org.blazers.core.BLSounds;
import org.hendrix.helper.IdentifierHelper;
import org.hendrix.helper.SoundHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin class for the {@link PaintingEntity Painting Entity}
 */
@Mixin(PaintingEntity.class)
public final class PaintingEntityMixin {

    /**
     * The {@link PaintingEntity Painting Entity}
     */
    @Unique
    PaintingEntity entity = (PaintingEntity) (Object)this;

    /**
     * Play a sound when the "bruh" painting is placed
     *
     * @param callbackInfo The {@link CallbackInfo Callback Info}
     */
    @Inject(method = "onPlace", at = @At("RETURN"))
    private void onPlace(final CallbackInfo callbackInfo) {
        if(isBruhPainting()) {
            SoundHelper.play(entity, BLSounds.BRUH, SoundCategory.BLOCKS);
        }
    }

    /**
     * Play a sound when the "bruh" painting is broken
     *
     * @param callbackInfo The {@link CallbackInfo Callback Info}
     */
    @Inject(method = "onBreak", at = @At("RETURN"))
    private void onBreak(final CallbackInfo callbackInfo) {
        if(isBruhPainting()) {
            SoundHelper.play(entity, BLSounds.REVERSED_BRUH, SoundCategory.BLOCKS);
        }
    }

    /**
     * Check if a painting is the "bruh" painting
     *
     * @return {@link Boolean True if is the "bruh" painting}
     */
    @Unique
    private boolean isBruhPainting() {
        return entity.getVariant().getKey().map(variantKey -> variantKey.getValue().equals(IdentifierHelper.modded("bruh"))).orElse(false);
    }

}