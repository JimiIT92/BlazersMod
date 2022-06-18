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
     * Creates an {@link Explosion Explosion}
     */
    @Override
    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 32.0F, Explosion.BlockInteraction.BREAK);
    }

    /**
     * Sets the {@link LivingEntity Igniter}
     *
     * @param owner {@link LivingEntity Igniter}
     */
    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
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

    /**
     * Get a {@link PrimedAtomicTnt Primed Atomic TNT} instance
     *
     * @param level {@link Level World reference}
     * @param posX {@link Double X coordinate}
     * @param posY {@link Double Y coordinate}
     * @param posZ {@link Double Z coordinate}
     * @param igniter {@link LivingEntity Igniter}
     * @return {@link PrimedAtomicTnt Primed Atomic TNT} instance
     */
    public static PrimedAtomicTnt from(Level level, double posX, double posY, double posZ, LivingEntity igniter) {
        PrimedAtomicTnt tnt = new PrimedAtomicTnt(BLEntityTypes.PRIMED_ATOMIC_TNT.get(), level);
        tnt.setPos(posX, posY, posZ);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        tnt.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2D, -Math.cos(d0) * 0.02D);
        tnt.setFuse(80);
        tnt.xo = posX;
        tnt.yo = posY;
        tnt.zo = posZ;
        tnt.setOwner(igniter);
        return tnt;
    }
}
