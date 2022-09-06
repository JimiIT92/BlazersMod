package org.blazers.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.blazers.core.BLItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public final class AbstractClientPlayerEntityMixin {

    AbstractClientPlayerEntity entity = (AbstractClientPlayerEntity) (Object)this;

    @Inject(method = "getFovMultiplier", at=@At("HEAD"), cancellable = true)
    public void getFovMultiplier(CallbackInfoReturnable<Float> infoReturnable) {
        float fov = 1.0f;
        if (entity.getAbilities().flying) {
            fov *= 1.1f;
        }
        fov *= ((float)entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) / entity.getAbilities().getWalkSpeed() + 1.0f) / 2.0f;
        if (entity.getAbilities().getWalkSpeed() == 0.0f || Float.isNaN(fov) || Float.isInfinite(fov)) {
            fov = 1.0f;
        }
        ItemStack itemStack = entity.getActiveItem();
        if (entity.isUsingItem() && itemStack.isOf(BLItems.CARBON_BOW)) {
            int i = entity.getItemUseTime();
            float fovMultiplier = (float)i / 5.0f;
            fovMultiplier = fovMultiplier > 1.0f ? 1.0f : fovMultiplier * fovMultiplier;
            fov *= 1.0f - fovMultiplier * 0.15f;
            infoReturnable.setReturnValue(MathHelper.lerp(MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(), 1.0f, fov));
        }
    }
}