package org.blazers.helper;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Helper methods for {@link Item Items}
 */
public final class ItemHelper {

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The Player using the Item Stack}
     * @param hand {@link InteractionHand The hand the Player is holding the Item Stack with}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final InteractionHand hand) {
        hurt(itemStack, player, hand, 1);
    }

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The Player using the Item Stack}
     * @param hand {@link InteractionHand The hand the Player is holding the Item Stack with}
     * @param amount {@link Integer The amount of damage to apply}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final InteractionHand hand, final int amount) {
        hurt(itemStack, player, hand, null, amount);
    }

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The Player using the Item Stack}
     * @param hand {@link InteractionHand The hand the Player is holding the Item Stack with}
     * @param sound {@link SoundEvent The sound to play after damaging the Item Stack}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final InteractionHand hand, final SoundEvent sound) {
        hurt(itemStack, player, hand, sound, 1);
    }

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The Player using the Item Stack}
     * @param hand {@link InteractionHand The hand the Player is holding the Item Stack with}
     * @param sound {@link SoundEvent The sound to play after damaging the Item Stack}
     * @param amount {@link Integer The amount of damage to apply}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final InteractionHand hand, final SoundEvent sound, final int amount) {
        hurt(itemStack, player, hand == null || hand.equals(InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, sound, amount);
    }

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The Player using the Item Stack}
     * @param slot {@link EquipmentSlot The slot where the Item Stack is equipped}
     * @param sound {@link SoundEvent The sound to play after damaging the Item Stack}
     * @param amount {@link Integer The amount of damage to apply}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final EquipmentSlot slot, final SoundEvent sound, final int amount) {
        if(player != null) {
            final InteractionHand hand = slot.equals(EquipmentSlot.MAINHAND) ? InteractionHand.MAIN_HAND : slot.equals(EquipmentSlot.OFFHAND) ? InteractionHand.OFF_HAND : null;
            if(hand != null) {
                player.swing(hand, true);
            }
            if(sound != null) {
                player.playSound(sound);
            }
            if(player.isCreative()) {
                return;
            }
            if(itemStack.isDamageableItem()) {
                itemStack.hurtAndBreak(amount, player, slot);
            } else {
                itemStack.shrink(amount);
            }
            return;
        }
        itemStack.shrink(amount);
    }

}