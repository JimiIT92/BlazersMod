package org.blazers.core;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

public class BLDamageTypes {
    public static RegistryKey<DamageType> SPEAR = registerKey("spear");

    public static void bootstrap(Registerable<DamageType> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.DAMAGE_TYPE);
        context.register(SPEAR, new DamageType("spear", 0.1F));
    }

    public static RegistryKey<DamageType> registerKey(final String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(BlazersMod.MOD_ID, name));
    }
}
