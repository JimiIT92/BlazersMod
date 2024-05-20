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

    public static final ResourceKey<DamageType> SPEAR = registerDamageTypeKey("spear");

    //#endregion

    //#region Methods

    /**
     * Register a {@link DamageType Damage Type} {@link ResourceKey Resource Key}
     *
     * @param name {@link String The Damage Type name}
     * @return {@link ResourceKey<DamageType> The Damage Type Resource Key}
     */
    private static ResourceKey<DamageType> registerDamageTypeKey(final String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.location(name));
    }

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