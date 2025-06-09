package org.blazers.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.blazers.core.BLItems;
import org.blazers.entity.projectile.MalachiteSpearEntity;
import org.blazers.entity.projectile.SpearEntity;

/**
 * Implementation class for a {@link Item Spear Item}
 */
public final class SpearItem extends Item {

    /**
     * Constructor. Set the {@link Item.Settings Item properties}
     *
     * @param material The {@link ToolMaterial Spear material}
     * @param attackDamage The {@link Float Spear attack damage}
     * @param settings The {@link Settings Item settings}
     */
    public SpearItem(final ToolMaterial material, final float attackDamage, final Item.Settings settings) {
        super(settings.sword(material, attackDamage, -2.4F));
    }

    /**
     * Get the {@link UseAction Item use action}
     *
     * @param stack The {@link ItemStack Item Stack}
     * @return The {@link UseAction Item use action}
     */
    @Override
    public UseAction getUseAction(final ItemStack stack) {
        return UseAction.SPEAR;
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
        return 36000;
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
        if(entity instanceof PlayerEntity player) {
            if((this.getMaxUseTime(stack, entity) - remainingUseTicks) >= 10 && world instanceof ServerWorld serverWorld) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                stack.damage(1, player);

                final SpearEntity spear = ProjectileEntity.spawnWithVelocity(stack.isOf(BLItems.MALACHITE_SPEAR) ? MalachiteSpearEntity::new : SpearEntity::new, serverWorld, stack, player, 0.0F, 2.5F, 1.0F);
                if (player.isInCreativeMode()) {
                    spear.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                } else {
                    player.getInventory().removeOne(stack);
                }
                world.playSoundFromEntity(null, spear, SoundEvents.ITEM_TRIDENT_THROW.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                return true;
            }
        }
        return false;
    }

    /**
     * Use the {@link Item Item}
     *
     * @param world The {@link World World reference}
     * @param player The {@link PlayerEntity Player using the Item}
     * @param hand The {@link Hand Hand the Player is using the Item with}
     * @return The {@link ActionResult Item action result}
     */
    @Override
    public ActionResult use(final World world, final PlayerEntity player, final Hand hand) {
        player.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

}