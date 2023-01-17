package org.blazers.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.blazers.core.BLEntityTypes;

import javax.annotation.Nullable;

public class AtomicTntEntity extends TntEntity {

    @Nullable
    private LivingEntity causingEntity;

    public AtomicTntEntity(EntityType<? extends AtomicTntEntity> entityType, World world) {
        super(entityType, world);
    }

    public AtomicTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(BLEntityTypes.PRIMED_ATOMIC_TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
        this.setFuse(160);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 32.0f, World.ExplosionSourceType.TNT);
    }

    @org.jetbrains.annotations.Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }
}
