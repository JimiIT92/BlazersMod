package org.blazers.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import org.blazers.core.BLPaintings;
import org.blazers.core.BLSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaintingEntity.class)
public final class PaintingEntityMixin {

    PaintingEntity entity = (PaintingEntity) (Object)this;

    @Inject(method = "onPlace", at=@At("RETURN"))
    public void onPlace(CallbackInfo info) {
        if(isVariant(BLPaintings.BRUH)) {
            playSound(BLSounds.BRUH);
        }
    }

    @Inject(method = "onBreak", at=@At("RETURN"))
    public void onBreak(Entity breaker, CallbackInfo info) {
        if(isVariant(BLPaintings.BRUH)) {
            playSound(BLSounds.REVERSED_BRUH);
        }
    }

    private boolean isVariant(PaintingVariant paintingVariant) {
        RegistryEntry<PaintingVariant> variant = entity.getVariant();
        return variant.getKey().isPresent() && variant.getKey().get().getValue().equals(Registry.PAINTING_VARIANT.getId(paintingVariant));
    }

    private void playSound(SoundEvent sound) {
        entity.getWorld().playSound(null, entity.getBlockPos(), sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}