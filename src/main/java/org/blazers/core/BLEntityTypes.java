package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.blazers.BlazersMod;
import org.blazers.entity.projectile.MalachiteSpearEntity;
import org.blazers.entity.projectile.SpearEntity;
import org.hendrix.registry.HCEntities;

/**
 * {@link BlazersMod Blazers Mod} {@link EntityType Entity Types}
 */
public final class BLEntityTypes {

    //#region Entity Types

    public static final EntityType<SpearEntity> SPEAR = HCEntities.register(
            "spear",
            Suppliers.memoize(() -> EntityType.Builder.<SpearEntity>create(SpearEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
            )
    );

    public static final EntityType<MalachiteSpearEntity> MALACHITE_SPEAR = HCEntities.register(
            "malachite_spear",
            Suppliers.memoize(() -> EntityType.Builder.<MalachiteSpearEntity>create(MalachiteSpearEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
            )
    );

    //#endregion

}