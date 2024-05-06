package org.blazers.entity.block;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.jetbrains.annotations.Nullable;

/**
 * {@link BlazersMod Blazers Mod} {@link PrimedTnt Primed Atomic TNT}
 */
public final class PrimedAtomicTnt extends PrimedTnt {

    /**
     * {@link LivingEntity The TNT owner}
     */
    @Nullable
    private LivingEntity owner;

    /**
     * Constructor. Set the entity properties
     *
     * @param type {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public PrimedAtomicTnt(final EntityType<? extends PrimedAtomicTnt> type, final Level level) {
        super(type, level);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The entity X coordinate}
     * @param posY {@link Double The entity Y coordinate}
     * @param posZ {@link Double The entity Z coordinate}
     * @param owner {@link LivingEntity The TNT owner}
     */
    public PrimedAtomicTnt(final Level level, final double posX, final double posY, final double posZ, final @Nullable LivingEntity owner) {
        this(BLEntityTypes.PRIMED_ATOMIC_TNT.get(), level);
        this.setPos(posX, posY, posZ);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(160);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = owner;
    }

    /**
     * Make the TNT explode
     */
    @Override
    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 32.0F, Level.ExplosionInteraction.TNT);
    }

    /**
     * Get the {@link LivingEntity TNT owner}
     *
     * @return {@link LivingEntity The TNT owner}
     */
    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.owner;
    }
}