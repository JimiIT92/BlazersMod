package org.blazers.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.blazers.BlazersMod;
import org.blazers.core.BLItems;
import org.blazers.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link BlazersMod Blazers Mod} {@link BowItem Carbon Bow Item}
 */
public final class CarbonBowItem extends BowItem {

    /**
     * The {@link Float Bow draw speed}
     */
    public static float DRAW_SPEED = 5F;

    /**
     * Constructor. Sets the {@link ItemProperties Item properties}
     */
    public CarbonBowItem() {
        super(PropertyHelper.item().durability(384));
    }

    /**
     * Shoot the {@link ArrowItem Arrow}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param level {@link Level The Level reference}
     * @param entity {@link LivingEntity The Shooter Entity}
     * @param timeLeft {@link Integer The Animation time left}
     */
    @Override
    public void releaseUsing(final @NotNull ItemStack itemStack, final @NotNull Level level, final @NotNull LivingEntity entity, final int timeLeft) {
        if (entity instanceof Player player) {
            final ItemStack projectileItemStack = player.getProjectile(itemStack);
            if (!projectileItemStack.isEmpty()) {
                int useDurationLeft = this.getUseDuration(itemStack) - timeLeft;
                useDurationLeft = ForgeEventFactory.onArrowLoose(itemStack, level, player, useDurationLeft, true);
                if (useDurationLeft < 0) {
                    return;
                }
                final float power = getPowerForTime(useDurationLeft);
                if (!((double)power < 0.1D)) {
                    final List<ItemStack> arrows = draw(itemStack, projectileItemStack, player);
                    if (!level.isClientSide() && !arrows.isEmpty()) {
                        this.shoot(level, player, player.getUsedItemHand(), itemStack, arrows, power * 3F, 1F, power == 1F, null);
                    }

                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1F,
                            1F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    /**
     * Sets the custom {@link Float arrow charge time}
     *
     * @param charge {@link Integer Charge stage}
     * @return {@link Float Arrow charge time}
     */
    public static float getPowerForTime(final int charge) {
        final float chargeTime = (float)charge / DRAW_SPEED;
        return Math.min(1F, (chargeTime * chargeTime + chargeTime * 2F) / 3F);
    }

    /**
     * Get the {@link Integer Item use duration}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Integer 18000}
     */
    public int getUseDuration(final @NotNull ItemStack itemStack) {
        return 18000;
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be used to repair this {@link Item Item}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param repairItemStack {@link ItemStack The Item Stack used to repair the Item}
     * @return {@link Boolean True if the Item Stack used to repair the Item is Carbon}
     */
    @Override
    public boolean isValidRepairItem(final @NotNull ItemStack itemStack, final @NotNull ItemStack repairItemStack) {
        return repairItemStack.is(BLItems.CARBON.get());
    }
}