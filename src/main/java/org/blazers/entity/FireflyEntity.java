package org.blazers.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.Nullable;

public class FireflyEntity extends AnimalEntity {

    @Nullable
    private BlockPos targetPosition;


    public FireflyEntity(EntityType<? extends FireflyEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 0.5D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.01F)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.01F);
    }

    @Override
    public boolean shouldDropXp() {
        return false;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height / 2.0F;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {

    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    protected void pushAway(Entity entity) {

    }

    @Override
    protected Identifier getLootTableId() {
        return new Identifier(BlazersMod.MOD_ID, "entities/firefly");
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

    @Override
    public boolean canAvoidTraps() {
        return true;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public void tickMovement() {
        if(this.isAlive()) {
            if(random.nextInt(5) == 0) {
                this.setVelocity(this.getVelocity().multiply(0.5, 0.3, 0.5));
            }
        }
        super.tickMovement();
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        if (this.targetPosition != null && (!this.world.isAir(this.targetPosition) || this.targetPosition.getY() <= this.world.getBottomY())) {
            this.targetPosition = null;
        }

        if (this.targetPosition == null || this.random.nextInt(10) == 0 || this.targetPosition.isWithinDistance(this.getPos(), 2.0D)) {
            this.targetPosition = new BlockPos(this.getBlockX() + this.random.nextInt(7) - this.random.nextInt(7), this.getBlockY() + this.random.nextInt(6) - 2, this.getBlockZ() + this.random.nextInt(7) - this.random.nextInt(7));
        }

        double d2 = (double)this.targetPosition.getX() + 0.5D - this.getX();
        double d0 = (double)this.targetPosition.getY() + 0.1D - this.getY();
        double d1 = (double)this.targetPosition.getZ() + 0.5D - this.getZ();
        Vec3d vec3 = this.getVelocity();
        Vec3d vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vec3.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double)0.1F);
        this.setVelocity(vec31);
        float f = (float)(MathHelper.atan2(vec31.z, vec31.x) * (double)(180F / (float)Math.PI)) - 90.0F;
        float f1 = MathHelper.wrapDegrees(f - this.getYaw());
        this.forwardSpeed = 0.5F;
        this.setYaw(this.getYaw() + f1);
    }
}
