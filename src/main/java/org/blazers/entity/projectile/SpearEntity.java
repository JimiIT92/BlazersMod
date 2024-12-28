package org.blazers.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.blazers.core.BLDamageTypes;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;
import org.hendrix.registry.HCDamageTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Implementation class for a {@link PersistentProjectileEntity Spear Entity}
 */
public class SpearEntity extends PersistentProjectileEntity {

    /**
     * {@link Boolean Whether the Spear dealt damage}
     */
    private boolean dealtDamage;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     */
    public SpearEntity(final EntityType<? extends SpearEntity> entityType, final World world) {
        super(entityType, world);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param world The {@link World World reference}
     * @param owner The {@link LivingEntity Owner of this Entity}
     * @param stack The {@link ItemStack Item Stack associated with this Entity}
     */
    public SpearEntity(final World world, final LivingEntity owner, final ItemStack stack) {
        this(BLEntityTypes.SPEAR, world, owner, stack);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     * @param owner The {@link LivingEntity Owner of this Entity}
     * @param stack The {@link ItemStack Item Stack associated with this Entity}
     */
    public SpearEntity(final EntityType<? extends SpearEntity> entityType, final World world, final LivingEntity owner, final ItemStack stack) {
        super(entityType, owner, world, stack, null);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param world The {@link World World reference}
     * @param x The {@link Double Entity X Coordinate}
     * @param y The {@link Double Entity Y Coordinate}
     * @param z The {@link Double Entity Z Coordinate}
     * @param stack The {@link ItemStack Item Stack associated with this Entity}
     */
    public SpearEntity(final World world, final double x, final double y, final double z, final ItemStack stack) {
        super(BLEntityTypes.SPEAR, x, y, z, world, stack, stack);
    }

    /**
     * Tick the Entity
     */
    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        super.tick();
    }

    /**
     * Make the Entity collide
     *
     * @param currentPosition The {@link Vec3d current Entity Position}
     * @param nextPosition The {@link Vec3d next Entity Position}
     * @return The {@link EntityHitResult Entity Hit Result}
     */
    @Nullable
    @Override
    protected EntityHitResult getEntityCollision(final Vec3d currentPosition, final Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    /**
     * Damage another Entity on hit
     *
     * @param entityHitResult The {@link EntityHitResult Entity Hit Result}
     */
    @Override
    protected void onEntityHit(final EntityHitResult entityHitResult) {
        final Entity entity = entityHitResult.getEntity();
        float damage = damage();
        final Entity owner = this.getOwner();
        final DamageSource damageSource = HCDamageTypes.damageSource(BLDamageTypes.SPEAR, this.getWorld(), this, owner == null ? this : owner);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            damage = EnchantmentHelper.getDamage(serverWorld, Objects.requireNonNull(this.getWeaponStack()), entity, damageSource, damage);
        }

        this.dealtDamage = true;
        if (entity.sidedDamage(damageSource, damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource, this.getWeaponStack(), item -> this.kill(serverWorld));
            }

            if (entity instanceof LivingEntity livingEntity) {
                this.knockback(livingEntity, damageSource);
                this.onHit(livingEntity);
            }
        }

        this.deflect(ProjectileDeflection.SIMPLE, entity, this.getOwner(), false);
        this.setVelocity(this.getVelocity().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0F, 1.0F);
    }

    /**
     * Get the {@link Float Spear base damage}
     *
     * @return The {@link Float Spear base damage}
     */
    protected float damage() {
        return 8.0F;
    }

    /**
     * Get the {@link ItemStack Weapon Item Stack}
     *
     * @return The {@link ItemStack Weapon Item Stack}
     */
    @Override
    public ItemStack getWeaponStack() {
        return this.getItemStack();
    }

    /**
     * Check if a {@link PlayerEntity Player} can pickup the {@link PersistentProjectileEntity projectile}
     *
     * @param player The {@link PlayerEntity Player}
     * @return {@link Boolean True if the Player can pickup the projectile}
     */
    @Override
    protected boolean tryPickup(final PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    /**
     * Get the {@link ItemStack Entity Item Stack}
     *
     * @return The {@link ItemStack Entity Item Stack}
     */
    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(BLItems.SPEAR);
    }

    /**
     * Get the {@link SoundEvent Entity Hit Sound}
     *
     * @return The {@link SoundEvent Entity Hit Sound}
     */
    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

    /**
     * Make the Entity collide with a {@link PlayerEntity Player}
     *
     * @param player The {@link PlayerEntity Player the Entity is colliding with}
     */
    @Override
    public void onPlayerCollision(final PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    /**
     * Read the {@link NbtCompound Entity NBT data}
     *
     * @param nbt {@link NbtCompound Entity NBT data}
     */
    @Override
    public void readCustomDataFromNbt(final NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    /**
     * Save the {@link NbtCompound Entity NBT data}
     *
     * @param nbt {@link NbtCompound Entity NBT data}
     */
    @Override
    public void writeCustomDataToNbt(final NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    /**
     * Despawn the Entity after a certain time has passed
     */
    @Override
    public void age() {
        if (this.pickupType != PersistentProjectileEntity.PickupPermission.ALLOWED) {
            super.age();
        }
    }

    /**
     * Check whether the Entity should be rendered
     *
     * @param cameraX The {@link Double camera X position}
     * @param cameraY The {@link Double camera Y position}
     * @param cameraZ The {@link Double camera Z position}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean shouldRender(final double cameraX, final double cameraY, final double cameraZ) {
        return true;
    }

}