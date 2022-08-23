package org.blazers.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;
import org.blazers.core.BLTabs;
import org.blazers.entity.ThrownSpear;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Implementation class for a {@link Item Spear}
 */
public class SpearItem extends SwordItem implements Vanishable {

    /**
     * Constructor. Set the {@link Item Spear} {@link Tier Tier},
     * {@link Integer damage modifier} and {@link Float speed modifier}
     *
     * @param tier {@link Tier Item Tier}
     * @param attackDamageModifier {@link Integer Damage modifier}
     * @param attackSpeedModifier {@link Float Speed modifier}
     */
    public SpearItem(Tier tier, int attackDamageModifier, float attackSpeedModifier) {
        super(tier, attackDamageModifier, attackSpeedModifier, new Properties().durability(tier.getUses()).tab(BLTabs.TAB_COMBAT));
    }

    /**
     * Get the {@link UseAnim Animation} to play when the {@link Item Spear} is being used
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link UseAnim Spear Animation}
     */
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    /**
     * Get how long it takes to use the {@link Item Spear}
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link Integer Use duration}
     */
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 36000;
    }

    /**
     * Throw the {@link Item Spear} when an {@link LivingEntity entity} stops using it
     *
     * @param stack {@link ItemStack Item Stack}
     * @param level {@link Level World reference}
     * @param livingEntity {@link LivingEntity The entity} that is using the {@link Item Spear}
     * @param timeLeft {@link Integer Use duration} left
     */
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(stack) - timeLeft;
            if (i >= 10) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(livingEntity.getUsedItemHand()));

                    ThrownSpear thrownSpear = new ThrownSpear(stack.is(BLItems.MALACHITE_SPEAR.get()) ? BLEntityTypes.MALACHITE_SPEAR.get() : BLEntityTypes.SPEAR.get(), level, player, stack);
                    thrownSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (player.getAbilities().instabuild) {
                        thrownSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(thrownSpear);
                    level.playSound(null, thrownSpear, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(stack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    /**
     * Use the {@link Item Spear}
     *
     * @param level {@link Level World reference}
     * @param player {@link Player The Player} using the {@link Item Spear}
     * @param hand {@link InteractionHand The hand} which holds the {@link Item Spear}
     * @return {@link InteractionResultHolder<ItemStack> Interaction Result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    /**
     * Make the {@link Item Spear} look like the {@link ThrownSpear Thrown Spear} entity
     * in Inventory or while throwing it
     *
     * @param consumer {@link Consumer<IClientItemExtensions> IItemRenderProperties Consumer}
     */
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return BlazersMod.getItemsRenderer();
            }
        });
    }
}