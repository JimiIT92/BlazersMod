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
import org.blazers.entity.projectile.ThrownSpear;
import org.blazers.helper.ItemHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * {@link BlazersMod Blazers Mod} {@link SwordItem Spear Item}
 */
public class SpearItem extends SwordItem {

    /**
     * Constructor. Set the {@link Item.Properties Item properties}
     *
     * @param tier {@link Tier Item Tier}
     * @param properties {@link Item.Properties The Item properties}
     */
    public SpearItem(final Tier tier, final Item.Properties properties) {
        super(tier, properties);
    }

    /**
     * Get the {@link UseAnim Animation} to play when the {@link Item Spear} is being used
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link UseAnim Spear Animation}
     */
    public @NotNull UseAnim getUseAnimation(final @NotNull ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    /**
     * Get how long it takes to use the {@link Item Spear}
     *
     * @param itemStack {@link ItemStack Item Stack}
     * @return {@link Integer Use duration}
     */
    public int getUseDuration(final @NotNull ItemStack itemStack) {
        return 36000;
    }

    /**
     * Throw the {@link Item Spear} when an {@link LivingEntity entity} stops using it
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param level {@link Level The Level reference}
     * @param entity {@link LivingEntity The Shooter Entity}
     * @param timeLeft {@link Integer The Animation time left}
     */
    public void releaseUsing(final @NotNull ItemStack itemStack, final @NotNull Level level, final @NotNull LivingEntity entity, final int timeLeft) {
        if (entity instanceof Player player) {
            final int useDurationLeft = this.getUseDuration(itemStack) - timeLeft;
            if (useDurationLeft >= 10) {
                if (!level.isClientSide()) {
                    ItemHelper.hurt(itemStack, player, entity.getUsedItemHand());

                    ThrownSpear thrownSpear = new ThrownSpear(itemStack.is(BLItems.MALACHITE_SPEAR.get()) ? BLEntityTypes.MALACHITE_SPEAR.get() : BLEntityTypes.SPEAR.get(), level, player, itemStack);
                    thrownSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (player.getAbilities().instabuild) {
                        thrownSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    level.addFreshEntity(thrownSpear);
                    level.playSound(null, thrownSpear, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(itemStack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    /**
     * Throw the {@link Item Spear} when an {@link LivingEntity entity} stops using it
     *
     * @param level {@link Level The Level reference}
     * @param player {@link LivingEntity The Shooter Entity}
     * @param hand {@link InteractionHand The hand the entity is using to throw the Spear}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    /**
     * Check if the {@link ItemStack Item} can be enchanted
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isEnchantable(final @NotNull ItemStack itemStack) {
        return false;
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
