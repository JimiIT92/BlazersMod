package org.blazers.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link Animal Firefly}
 */
public class Firefly extends AmbientCreature {

    /**
     * {@link BlockPos The entity target position}
     */
    private BlockPos targetPosition;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType The entity type}
     * @param level {@link Level The level reference}
     */
    public Firefly(final EntityType<? extends Firefly> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Create the entity attributes
     *
     * @return {@link AttributeSupplier.Builder The attribute supplier builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return AgeableMob.createMobAttributes().add(Attributes.MAX_HEALTH, 0.5D).add(Attributes.MOVEMENT_SPEED, 0.005F).add(Attributes.FLYING_SPEED, 0.005F);
    }

    /**
     * Tick the entity
     */
    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.0625D, 0.0375D, 0.0625D));
    }

    /**
     * Make the entity randomly move around
     */
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        final BlockPos aboveBlockPos = this.blockPosition().above();
        if (this.targetPosition != null && (!this.level().isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level().getMinBuildHeight())) {
            this.targetPosition = null;
        }

        if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2D)) {
            this.targetPosition = BlockPos.containing(
                    this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7),
                    this.getY() + (double)this.random.nextInt(6) - 2D,
                    this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7)
            );
        }

        final double x = (double)this.targetPosition.getX() + 0.5 - this.getX();
        final double y = (double)this.targetPosition.getY() + 0.1 - this.getY();
        final double z = (double)this.targetPosition.getZ() + 0.5 - this.getZ();
        final Vec3 deltaMovement = this.getDeltaMovement();
        final Vec3 offsetMovement = deltaMovement.add((Math.signum(x) * 0.5 - deltaMovement.x) * 0.1F, (Math.signum(y) * 0.7F - deltaMovement.y) * 0.1F, (Math.signum(z) * 0.5 - deltaMovement.z) * 0.1F);
        this.setDeltaMovement(offsetMovement);
        final float angle = (float)(Mth.atan2(offsetMovement.z, offsetMovement.x) * 180.0F / (float)Math.PI) - 90.0F;
        this.zza = 0.5F;
        this.setYRot(this.getYRot() + Mth.wrapDegrees(angle - this.getYRot()));
    }

    /**
     * Check if the entity should ignore block triggers like pressure plates
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    /**
     * Check if the entity should despawn when too far away
     *
     * @param distance {@link Double The distance}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean removeWhenFarAway(final double distance) {
        return true;
    }

    /**
     * Prevent the entity from pushing other entities
     */
    @Override
    protected void pushEntities() {

    }

    /**
     * Check if the entity can be pushed by other entities
     *
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isPushable() {
        return false;
    }

    /**
     * Check if the entity can be pushed by a {@link FluidType Fluid}
     *
     * @param type {@link FluidType The fluid type}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isPushedByFluid(final FluidType type) {
        return false;
    }

    /**
     * Prevent the entity from checking any fall damage
     *
     * @param height {@link Double The fall height}
     * @param onGround {@link Boolean If the entity is on ground}
     * @param blockState {@link BlockState The current Block State}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    @Override
    protected void checkFallDamage(final double height, final boolean onGround, final @NotNull BlockState blockState, final @NotNull BlockPos blockPos) {

    }

    /**
     * Check if the entity should take fall damage
     *
     * @param distance {@link Float The fall distance}
     * @param damageMultiplier {@link Float The damage multiplier}
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean causeFallDamage(final float distance, final float damageMultiplier, final @NotNull DamageSource damageSource) {
        return false;
    }

    /**
     * Check if the entity is silent
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isSilent() {
        return true;
    }

    /**
     * Check if the entity should drop any experience when killed
     *
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean shouldDropExperience() {
        return false;
    }

}