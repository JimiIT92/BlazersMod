package org.blazers.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Implementation class for a {@link BowItem Carbon Bow}
 */
public final class CarbonBowItem extends BowItem {

    /**
     * Constructor. Set the {@link Item.Settings Item properties}
     *
     * @param settings The {@link Settings Item settings}
     */
    public CarbonBowItem(final Settings settings) {
        super(settings);
    }

    /**
     * Throw the {@link Item Spear}
     *
     * @param stack The {@link ItemStack current Item Stack}
     * @param world The {@link World World reference}
     * @param entity The {@link LivingEntity entity using the Item}
     * @param remainingUseTicks The {@link Integer reamining Item use ticks}
     * @return {@link Boolean True if the Item has been successfully used}
     */
    @Override
    public boolean onStoppedUsing(final ItemStack stack, final World world, final LivingEntity entity, final int remainingUseTicks) {
        if (!(entity instanceof PlayerEntity playerEntity)) {
            return false;
        }
        final ItemStack projectileStack = playerEntity.getProjectileType(stack);
        if (projectileStack.isEmpty()) {
            return false;
        }
        final int useTime = this.getMaxUseTime(stack, entity) - remainingUseTicks;
        final float pullProgress = this.getCarbonBowPullProgress(useTime);
        if ((double)pullProgress < 0.1) {
            return false;
        }
        final List<ItemStack> projectiles = load(stack, projectileStack, playerEntity);
        if (world instanceof ServerWorld serverWorld && !projectiles.isEmpty()) {
            this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, projectiles, pullProgress * 3.0F, 1.0F, pullProgress == 1.0F, null);
        }

        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        return true;
    }

    /**
     * Shoot an {@link ArrowEntity Arrow}
     *
     * @param shooter The {@link LivingEntity Entity that is shooting with the Bow}
     * @param projectile The {@link ProjectileEntity Projectile that is being shot by the Bow}
     * @param index The {@link Integer projectile index}
     * @param speed The {@link Float projectile speed}
     * @param divergence The {@link Float projectile divergence}
     * @param yaw The {@link Float projectile yaw}
     * @param target The {@link LivingEntity projectile target}
     */
    @Override
    protected void shoot(final LivingEntity shooter, final ProjectileEntity projectile, final int index, final float speed, final float divergence, final float yaw, @Nullable final LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed * 3.0F, divergence);
    }

    /**
     * Get the {@link Integer Item use time}
     *
     * @param stack The {@link ItemStack current Item Stack}
     * @param entity The {@link LivingEntity entity using the Item}
     * @return The {@link Integer Item use time}
     */
    @Override
    public int getMaxUseTime(final ItemStack stack, final LivingEntity entity) {
        return 24000;
    }

    /**
     * Get the {@link Float Carbon Bow Pull Progress}
     *
     * @param useTicks The {@link Integer Item reamining use ticks}
     * @return The {@link Float Bow Pull Progress}
     */
    private float getCarbonBowPullProgress(final int useTicks) {
        final float chargeTime = (float)useTicks / 6.5F;
        return Math.min(1.0F, (chargeTime * chargeTime + chargeTime * 2.0F) / 3.0F);
    }

}