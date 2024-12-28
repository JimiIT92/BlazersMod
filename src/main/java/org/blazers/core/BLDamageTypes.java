package org.blazers.core;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import org.blazers.BlazersMod;
import org.hendrix.registry.HCDamageTypes;

/**
 * {@link BlazersMod Blazers Mod} {@link DamageType Damage Types}
 */
public final class BLDamageTypes {

    //#region Damage Types

    public static final RegistryKey<DamageType> SPEAR = HCDamageTypes.damageType("spear");

    //#endregion

}