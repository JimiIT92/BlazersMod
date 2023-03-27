package org.blazers.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link DamageType Damage Types}
 */
public final class BLDamageTypes {


    //#region Damage Types

    public static final ResourceKey<DamageType> SPEAR = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlazersMod.MOD_ID, "spear"));

    //#endregion

    /**
     * Register the {@link BLDamageTypes Damage Types}
     *
     * @param context {@link BootstapContext<DamageType> Bootstrap Context}
     */
    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(SPEAR, new DamageType("spear", 0.1F));
    }
}
