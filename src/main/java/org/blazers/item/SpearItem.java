package org.blazers.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.blazers.core.BLTabs;
import org.blazers.core.BLTiers;
import org.jetbrains.annotations.NotNull;

public class SpearItem extends SwordItem implements Vanishable {

    public SpearItem(Tier tier, int attackModifier, float attackSpeedModifier) {
        super(tier, attackModifier, attackSpeedModifier, new Properties().durability(tier.getUses()).tab(BLTabs.TAB_COMBAT));
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 72000;
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void releaseUsing(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;
            if (i >= 10) {
                if (!pLevel.isClientSide) {
                    pStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(pEntityLiving.getUsedItemHand()));

//                    ThrownTrident thrownSpear = getThrownEntity(pLevel, pStack, player);
//                    thrownSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
//                    if (player.getAbilities().instabuild) {
//                        thrownSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
//                    }
//
//                    pLevel.addFreshEntity(thrownSpear);
//                    pLevel.playSound(null, thrownSpear, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(pStack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    public AbstractArrow getThrownEntity(Level level, ItemStack stack, Player player) {
        if (BLTiers.MALACHITE.equals(this.getTier())) {
            return new ThrownTrident(level, player, stack);
        }
        return new ThrownTrident(level, player, stack);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

}