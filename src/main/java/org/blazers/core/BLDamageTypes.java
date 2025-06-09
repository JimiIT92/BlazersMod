package org.blazers.core;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import org.blazers.BlazersMod;
import org.hendrix.helper.RegistryKeyHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link DamageType Damage Types}
 */
public final class BLDamageTypes {

    //#region Damage Types

    public static final RegistryKey<DamageType> SPEAR = RegistryKeyHelper.damageType("spear");

    //#endregion

}