package org.blazers.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * {@link BlazersMod Blazers Mod} {@link Horse Wither Skeleton Horse}
 */
public final class WitherSkeletonHorse extends Horse {

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType The entity type}
     * @param level {@link Level The level reference}
     */
    public WitherSkeletonHorse(final EntityType<? extends Horse> entityType, final Level level) {
        super(entityType, level);
        this.setTamed(true);
    }

    /**
     * Create the entity attributes
     *
     * @return {@link AttributeSupplier.Builder The attribute supplier builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 15D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    /**
     * Randomize the entity attributes
     *
     * @param random {@link RandomSource The random reference}
     */
    @Override
    protected void randomizeAttributes(final RandomSource random) {
        Objects.requireNonNull(this.getAttribute(Attributes.JUMP_STRENGTH)).setBaseValue(generateJumpStrength(random::nextDouble));
    }

    /**
     * Check if the entity can mate with another one
     *
     * @param animal {@link Animal The entity to mate with}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean canMate(final @NotNull Animal animal) {
        return false;
    }

    /**
     * Get the baby variant of this entity
     *
     * @param level {@link ServerLevel The level reference}
     * @param mob {@link AgeableMob The parent entity}
     * @return {@link AgeableMob The baby entity}
     */
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(final @NotNull ServerLevel level, final @NotNull AgeableMob mob) {
        return BLEntityTypes.WITHER_SKELETON_HORSE.get().create(level);
    }

    /**
     * Get the valid light level for the entity to be able to spawn
     *
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param level {@link LevelReader The level reference}
     * @return {@link Float The entity valid light level for spawn}
     */
    @Override
    public float getWalkTargetValue(final @NotNull BlockPos blockPos, final LevelReader level) {
        return 0.5F - level.getBrightness(LightLayer.SKY, blockPos);
    }

    /**
     * Get the {@link SoundEvent entity ambient sound}
     *
     * @return {@link SoundEvent The entity ambient sound}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isEyeInFluid(FluidTags.WATER) ? SoundEvents.SKELETON_HORSE_AMBIENT_WATER : SoundEvents.SKELETON_HORSE_AMBIENT;
    }

    /**
     * Get the {@link SoundEvent entity death sound}
     *
     * @return {@link SoundEvent The entity death sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_HORSE_DEATH;
    }

    /**
     * Get the {@link SoundEvent entity hurt sound}
     *
     * @param damageSource The {@link DamageSource damage source}
     * @return {@link SoundEvent The entity hurt sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return SoundEvents.SKELETON_HORSE_HURT;
    }

    /**
     * Get the {@link SoundEvent entity swim sound}
     *
     * @return {@link SoundEvent The entity swim sound}
     */
    @Override
    protected @NotNull SoundEvent getSwimSound() {
        if (this.onGround()) {
            if (!this.isVehicle()) {
                return SoundEvents.SKELETON_HORSE_STEP_WATER;
            }

            this.gallopSoundCounter++;
            if (this.gallopSoundCounter > 5 && this.gallopSoundCounter % 3 == 0) {
                return SoundEvents.SKELETON_HORSE_GALLOP_WATER;
            }

            if (this.gallopSoundCounter <= 5) {
                return SoundEvents.SKELETON_HORSE_STEP_WATER;
            }
        }

        return SoundEvents.SKELETON_HORSE_SWIM;
    }

    /**
     * Play the entity swim sound
     *
     * @param volume {@link Float The sound volume}
     */
    @Override
    protected void playSwimSound(float volume) {
        if (this.onGround()) {
            super.playSwimSound(0.3F);
        } else {
            super.playSwimSound(Math.min(0.1F, volume * 25.0F));
        }
    }

    /**
     * Play the entity jump sound
     */
    @Override
    protected void playJumpSound() {
        if (this.isInWater()) {
            this.playSound(SoundEvents.SKELETON_HORSE_JUMP_WATER, 0.4F, 1.0F);
        } else {
            super.playJumpSound();
        }
    }

    /**
     * Spawn some {@link ParticleTypes#SMOKE Smoke particles} around the entity
     */
    @Override
    public void aiStep() {
        if(this.isAlive() && this.level().isClientSide()) {
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), 0, 0, 0);
            }
        }
        super.aiStep();
    }

}