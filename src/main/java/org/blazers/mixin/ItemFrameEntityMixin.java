package org.blazers.mixin;

import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public final class ItemFrameEntityMixin {

    ItemFrameEntity entity = (ItemFrameEntity) (Object)this;

    @Inject(method = "interact", at=@At("HEAD"), cancellable = true)
    public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> infoReturnable) {
        ItemStack stack = player.getStackInHand(hand);
        if(player.isSneaking() && !stack.isEmpty() && stack.isOf(Items.SHEARS)) {
            boolean invisible = !entity.isInvisible();
            entity.setInvisible(invisible);
            entity.world.playSound(player, entity.getBlockPos(), invisible ? SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM : SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 0.75F, 1.0F);
            stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            infoReturnable.setReturnValue(ActionResult.CONSUME);
        }
    }
}