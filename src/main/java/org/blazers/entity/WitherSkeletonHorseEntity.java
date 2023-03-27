package org.blazers.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;

import java.util.Objects;

public class WitherSkeletonHorseEntity extends HorseEntity {

    public WitherSkeletonHorseEntity(EntityType<? extends HorseEntity> entityType, World world) {
        super(entityType, world);
        this.setTame(true);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.HORSE_JUMP_STRENGTH, 0.4F);
    }

    @Override
    protected void initAttributes(Random random) {
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH)).setBaseValue(getChildJumpStrengthBonus(random::nextDouble));
    }

    @Override
    protected Identifier getLootTableId() {
        return new Identifier(BlazersMod.MOD_ID, "entities/wither_skeleton_horse");
    }

    @Override
    protected boolean canBreed() {
        return false;
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return BLEntityTypes.WITHER_SKELETON_HORSE.create(world);
    }

    @Override
    public void tickMovement() {
        if(this.isAlive()) {
            if(this.world.isClient) {
                for (int i = 0; i < 2; ++i) {
                    this.world.addParticle(ParticleTypes.SMOKE,
                            this.getParticleX(0.5D),
                            this.getRandomBodyY() - 0.25D,
                            this.getParticleZ(0.5D),
                            0, 0, 0);
                }
            }
        }
        super.tickMovement();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        super.getHurtSound(source);
        return SoundEvents.ENTITY_SKELETON_HORSE_HURT;
    }
}
