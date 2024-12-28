package org.blazers.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;

/**
 * Implementation class for a {@link SpearEntity Malachite Spear Entity}
 */
public final class MalachiteSpearEntity extends SpearEntity {

    /**
     * Constructor. Set the entity properties
     *
     * @param world The {@link World World reference}
     */
    public MalachiteSpearEntity(final World world) {
        this(BLEntityTypes.MALACHITE_SPEAR, world);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     */
    public MalachiteSpearEntity(final EntityType<? extends SpearEntity> entityType, final World world) {
        super(entityType, world);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param world The {@link World World reference}
     * @param owner The {@link LivingEntity Owner of this Entity}
     * @param stack The {@link ItemStack Item Stack associated with this Entity}
     */
    public MalachiteSpearEntity(final World world, final LivingEntity owner, final ItemStack stack) {
        super(BLEntityTypes.MALACHITE_SPEAR, world, owner, stack);
    }

    /**
     * Get the {@link Float Spear base damage}
     *
     * @return The {@link Float Spear base damage}
     */
    protected float damage() {
        return 12.0F;
    }

    /**
     * Get the {@link ItemStack Entity Item Stack}
     *
     * @return The {@link ItemStack Entity Item Stack}
     */
    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(BLItems.MALACHITE_SPEAR);
    }

}