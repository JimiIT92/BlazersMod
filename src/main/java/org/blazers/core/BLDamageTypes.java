package org.blazers.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;

/**
 * {@link BlazersMod BlazersMod} {@link DamageType Damage Types}
 */
public final class BLDamageTypes {

    //#region Damage Types

    public static final ResourceKey<DamageType> SPEAR = ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.location("spear"));

    //#endregion

    //#region Methods

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link DamageType Damage Types}
     *
     * @param context {@link BootstrapContext<DamageType> The Bootstrap Context}
     */
    public static void bootstrap(final BootstrapContext<DamageType> context) {
        context.register(SPEAR, new DamageType("spear", 0.1F));
    }

    //#endregion

}