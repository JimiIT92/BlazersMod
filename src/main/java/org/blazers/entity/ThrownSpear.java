package org.blazers.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.blazers.core.BLDamageTypes;
import org.blazers.core.BLItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link AbstractArrow Thrown Spear}
 */
public class ThrownSpear extends AbstractArrow {

    /**
     * Deafult {@link ItemStack Spear Item}
     */
    private ItemStack spearItem = new ItemStack(BLItems.SPEAR.get());
    /**
     * {@link Boolean If the Spear dealt damage}
     */
    private boolean dealtDamage;

    /**
     * Constructor. Sets the {@link ThrownSpear Thrown Spear} properties
     *
     * @param type {@link EntityType Entity Type}
     * @param level {@link Level World reference}
     */
    public ThrownSpear(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    /**
     * {@link LivingEntity Living Entity} Constructor.
     * Sets the {@link ThrownSpear Thrown Spear} properties and position
     *
     * @param world {@link Level World reference}
     * @param entity {@link LivingEntity Living Entity}
     * @param stack {@link ItemStack Spear Item}
     */
    public ThrownSpear(EntityType<? extends AbstractArrow> type, Level world, LivingEntity entity, ItemStack stack) {
        super(type, entity, world);
        this.spearItem = stack.copy();
    }

    /**
     * Sets the {@link #dealtDamage dealtDamage} property
     */
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        super.tick();
    }

    /**
     * Get the {@link ItemStack Pickup Item}
     *
     * @return {@link #spearItem Spear Item}
     */
    protected @NotNull ItemStack getPickupItem() {
        return this.spearItem.copy();
    }

    /**
     * Check if the {@link AbstractArrow Spear} hit an entity
     *
     * @param startVec {@link Vec3 Start position}
     * @param endVec {@link Vec3 End position}
     * @return {@link EntityHitResult Entity Hit Result}
     */
    @Nullable
    protected EntityHitResult findHitEntity(@NotNull Vec3 startVec, @NotNull Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    /**
     * Damage the hit {@link Entity Entity}
     *
     * @param hitResult {@link EntityHitResult Entity Hit Result}
     */
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingentity) {
            f += EnchantmentHelper.getDamageBonus(this.spearItem, livingentity.getMobType());
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource = new DamageSource(this.level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(BLDamageTypes.SPEAR), this, entity1 == null ? this : entity1);
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingentity1) {
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
                }

                this.doPostHurtEffects(livingentity1);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        this.playSound(soundevent, 1.0F,  1.0F);
    }

    /**
     * Check if the {@link Player Player} can pickup the {@link AbstractArrow Spear}
     *
     * @param player {@link Player Player}
     * @return {@link Boolean True} if the {@link Player Player} can pickup the {@link AbstractArrow Spear}
     */
    protected boolean tryPickup(@NotNull Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    /**
     * Get the {@link SoundEvent Sound Effect} for when the {@link AbstractArrow Spear} hits the ground
     *
     * @return {@link SoundEvent Sound Effect} for when the {@link AbstractArrow Spear} hits the ground
     */
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT;
    }

    /**
     * Let the {@link Player Player} try pickup the {@link AbstractArrow Spear}
     *
     * @param player {@link Player Player}
     */
    public void playerTouch(@NotNull Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }
    }

    /**
     * Read {@link AbstractArrow Spear} data from the {@link CompoundTag Compound Tag}
     *
     * @param compound {@link CompoundTag Compound Tag}
     */
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Spear")) {
            this.spearItem = ItemStack.of(compound.getCompound("Spear"));
        }
        this.dealtDamage = compound.getBoolean("DealtDamage");
    }

    /**
     * Save {@link AbstractArrow Spear} data to the {@link CompoundTag Compound Tag}
     *
     * @param compound {@link CompoundTag Compound Tag}
     */
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("Spear", this.spearItem.save(new CompoundTag()));
        compound.putBoolean("DealtDamage", this.dealtDamage);
    }

    /**
     * Check if the {@link AbstractArrow Spear} should render at the given location
     *
     * @param x {@link Double X coordinate}
     * @param y {@link Double Y coordinate}
     * @param z {@link Double Z coordinate}
     * @return {@link Boolean True} if the {@link AbstractArrow Spear} should render at the given location
     */
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }
}