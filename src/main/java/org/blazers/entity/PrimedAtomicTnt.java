package org.blazers.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.blazers.core.BLEntityTypes;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link PrimedTnt Primed Atomic TNT}
 */
public class PrimedAtomicTnt extends PrimedTnt {

    /**
     * {@link LivingEntity Igniter}
     */
    @Nullable
    private LivingEntity owner;

    /**
     * Constructor. Sets the {@link PrimedTnt Primed Atomic TNT} properties
     *
     * @param type {@link EntityType Entity Type}
     * @param level {@link Level World reference}
     */
    public PrimedAtomicTnt(EntityType<? extends PrimedTnt> type, Level level) {
        super(type, level);
    }


    /**
     * {@link LivingEntity Living Entity} Constructor.
     * Sets the {@link PrimedTnt Primed Atomic TNT} properties and position
     *
     * @param level {@link Level World reference}
     * @param posX {@link Double X coordinate}
     * @param posY {@link Double Y coordinate}
     * @param posZ {@link Double Z coordinate}
     * @param igniter {@link LivingEntity Igniter}
     */
    public PrimedAtomicTnt(Level level, double posX, double posY, double posZ, @Nullable LivingEntity igniter) {
        this(BLEntityTypes.PRIMED_ATOMIC_TNT.get(), level);
        this.setPos(posX, posY, posZ);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(160);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = igniter;
    }

    /**
     * Creates an {@link Explosion Explosion}
     */
    @Override
    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 32.0F, Level.ExplosionInteraction.TNT);
    }

    /**
     * Get the {@link LivingEntity Igniter}
     *
     * @return {@link LivingEntity Igniter}
     */
    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }
}
