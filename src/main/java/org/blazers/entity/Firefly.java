package org.blazers.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link FlyingAnimal Firefly}
 */
public class Firefly extends Animal {

    /**
     * {@link Firefly Firefly} {@link BlockPos }
     */
    @Nullable
    private BlockPos targetPosition;

    /**
     * Constructor. Sets the {@link FlyingAnimal Firefly properties}
     *
     * @param entityType {@link EntityType Entity Type}
     * @param world {@link Level World reference}
     */
    public Firefly(EntityType<? extends Firefly> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Sets the {@link AttributeSupplier.Builder Firefly Attributes}
     *
     * @return {@link AttributeSupplier.Builder Firefly Attributes}
     */
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 0.5D)
                .add(Attributes.FLYING_SPEED, 0.01F)
                .add(Attributes.MOVEMENT_SPEED, 0.01F);
    }

    /**
     * Check if the {@link Firefly Firefly} should drop experience when it dies
     *
     * @return {@link Boolean True} if the {@link Firefly Firefly} should drop experience when it dies
     */
    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    /**
     * Get the {@link Float Firefly Eye Sight}
     *
     * @param pose {@link Pose Firefly Pose}
     * @param size {@link EntityDimensions Firefly Size}
     * @return {@link Float Firefly Eye Sight}
     */
    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, EntityDimensions size) {
        return size.height / 2.0F;
    }

    /**
     * Check if the {@link Firefly Firefly} is silent
     *
     * @return {@link Boolean True} if the {@link Firefly Firefly} is silent
     */
    @Override
    public boolean isSilent() {
        return true;
    }

    /**
     * Check if the {@link Firefly Firefly} should take {@link Float Fall Damage}
     *
     * @param fallDistance {@link Float Fall distance}
     * @param multiplier {@link Float Damage multiplier}
     * @param damageSource {@link DamageSource Damage Source}
     * @return {@link Boolean True} if the {@link Firefly Firefly} should take {@link Float Fall Damage}
     */
    public boolean causeFallDamage(float fallDistance, float multiplier, @NotNull DamageSource damageSource) {
        return false;
    }

    /**
     * Check if the {@link Firefly Firefly} can take {@link Float Fall Damage}
     *
     * @param height {@link Double Fall height}
     * @param onGround {@link Boolean If the Firefly is on ground}
     * @param state {@link BlockState Block State}
     * @param pos {@link BlockPos Block Pos}
     */
    protected void checkFallDamage(double height, boolean onGround, @NotNull BlockState state, @NotNull BlockPos pos) { }

    /**
     * Get if the {@link Firefly Firefly} can be pushed
     *
     * @return {@link Boolean True} if the {@link Firefly Firefly} can be pushed
     */
    @Override
    public boolean isPushable() {
        return false;
    }

    /**
     * Check if the {@link Firefly Firefly} can be pushed by a {@link FluidType Fluid}
     *
     * @param type {@link FluidType Fluid}
     * @return {@link Boolean True} if the {@link Firefly Firefly} can be pushed by a {@link FluidType Fluid}
     */
    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    /**
     * Makes the {@link Firefly Firefly} unable to push other entities
     */
    @Override
    protected void pushEntities() {

    }

    /**
     * Get the {@link Firefly Firefly} {@link ResourceLocation Loot Table}
     *
     * @return {@link Firefly Firefly} {@link ResourceLocation Loot Table}
     */
    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(BlazersMod.MOD_ID, "entities/firefly");
    }

    /**
     * Check if the {@link Firefly Firefly} should despawn if is too far away from the {@link net.minecraft.world.entity.player.Player Player}
     *
     * @param distance {@link Double Distance} to the {@link net.minecraft.world.entity.player.Player Player}
     * @return {@link Boolean True} if the {@link Firefly Firefly} should despawn if is too far away from the {@link net.minecraft.world.entity.player.Player Player}
     */
    @Override
    public boolean removeWhenFarAway(double distance) {
        return true;
    }

    /**
     * Make the {@link Firefly Firefly} wander around
     */
    @Override
    public void aiStep() {
        if(this.isAlive()) {
            if(random.nextInt(5) == 0) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.5D, 0.3D, 0.5D));
            }
        }
        super.aiStep();
    }

    /**
     * Make the {@link Firefly Firefly} wander around
     */
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.targetPosition != null && (!this.level.isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level.getMinBuildHeight())) {
            this.targetPosition = null;
        }

        if (this.targetPosition == null || this.random.nextInt(10) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2.0D)) {
            this.targetPosition = new BlockPos(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(6) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
        }

        double d2 = (double)this.targetPosition.getX() + 0.5D - this.getX();
        double d0 = (double)this.targetPosition.getY() + 0.1D - this.getY();
        double d1 = (double)this.targetPosition.getZ() + 0.5D - this.getZ();
        Vec3 vec3 = this.getDeltaMovement();
        Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vec3.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double)0.1F);
        this.setDeltaMovement(vec31);
        float f = (float)(Mth.atan2(vec31.z, vec31.x) * (double)(180F / (float)Math.PI)) - 90.0F;
        float f1 = Mth.wrapDegrees(f - this.getYRot());
        this.zza = 0.5F;
        this.setYRot(this.getYRot() + f1);
    }

    /**
     * Check if the {@link Firefly Firefly} should ignore pressure plates
     *
     * @return {@link Boolean True} if the {@link Firefly Firefly} should ignore pressure plates
     */
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    /**
     * Get the valid {@link Float Light Level} for a {@link Firefly Firefly} to spawn
     *
     * @param pos {@link BlockPos Block Pos}
     * @param level {@link LevelReader World reference}
     * @return {@link Float Light Level}
     */
    @Override
    public float getWalkTargetValue(@NotNull BlockPos pos, LevelReader level) {
        return 0.5F - level.getBrightness(LightLayer.SKY, pos);
    }

    /**
     * Get the {@link Firefly Baby Firefly}
     *
     * @param level {@link Level World reference}
     * @param ageableMob {@link AgeableMob Ageable Mob}
     * @return {@link Firefly Baby Firefly}
     */
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob ageableMob) {
        return null;
    }
}