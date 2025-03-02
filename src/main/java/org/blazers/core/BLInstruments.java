package org.blazers.core;

import net.minecraft.item.Instrument;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import org.blazers.BlazersMod;
import org.hendrix.helper.RegistryKeyHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link Instrument Instruments}
 */
public final class BLInstruments {

    //#region Instruments

    public static final RegistryKey<Instrument> BRO_GOAT_HORN = goatHorn("bro");
    public static final RegistryKey<Instrument> FLY_GOAT_HORN = goatHorn("fly");
    public static final RegistryKey<Instrument> RESIST_GOAT_HORN = goatHorn("resist");

    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN = copperHorn("great_sky_falling");
    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_BASS = bassCopperHorn("great_sky_falling");
    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_HARMONY = harmonyCopperHorn("great_sky_falling");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN = copperHorn("old_hymn_resting");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_BASS = bassCopperHorn("old_hymn_resting");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_HARMONY = harmonyCopperHorn("old_hymn_resting");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN = copperHorn("pure_water_desire");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_BASS = bassCopperHorn("pure_water_desire");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_HARMONY = harmonyCopperHorn("pure_water_desire");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN = copperHorn("mumble_fire_memory");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS = bassCopperHorn("mumble_fire_memory");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY = harmonyCopperHorn("mumble_fire_memory");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN = copperHorn("dry_urge_anger");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_BASS = bassCopperHorn("dry_urge_anger");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_HARMONY = harmonyCopperHorn("dry_urge_anger");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN = copperHorn("clear_temper_journey");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS = bassCopperHorn("clear_temper_journey");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY = harmonyCopperHorn("clear_temper_journey");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN = copperHorn("fresh_nest_thought");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_BASS = bassCopperHorn("fresh_nest_thought");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY = harmonyCopperHorn("fresh_nest_thought");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN = copperHorn("secret_lake_tear");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_BASS = bassCopperHorn("secret_lake_tear");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_HARMONY = harmonyCopperHorn("secret_lake_tear");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN = copperHorn("fearless_river_gift");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_BASS = bassCopperHorn("fearless_river_gift");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY = harmonyCopperHorn("fearless_river_gift");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN = copperHorn("sweet_moon_love");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_BASS = copperHorn("sweet_moon_love", "bass");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_HARMONY = harmonyCopperHorn("sweet_moon_love");

    //#endregion

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} for a {@link Items#GOAT_HORN Goat Horn}
     *
     * @param name The {@link String instrument name}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private static RegistryKey<Instrument> goatHorn(final String name) {
        return RegistryKeyHelper.instrument(name + "_goat_horn");
    }

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} for a base Copper Horn
     *
     * @param name The {@link String instrument name}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private static RegistryKey<Instrument> copperHorn(final String name) {
        return copperHorn(name, "");
    }

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} for a bass Copper Horn
     *
     * @param name The {@link String instrument name}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private static RegistryKey<Instrument> bassCopperHorn(final String name) {
        return copperHorn(name, "bass");
    }

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} for a harmony Copper Horn
     *
     * @param name The {@link String instrument name}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private static RegistryKey<Instrument> harmonyCopperHorn(final String name) {
        return copperHorn(name, "harmony");
    }

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} for a Copper Horn
     *
     * @param name The {@link String instrument name}
     * @param variant The {@link String copper horn variant}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private static RegistryKey<Instrument> copperHorn(final String name, final String variant) {
        return RegistryKeyHelper.instrument(name + "_copper_horn" + (variant.isEmpty() ? "" : "_") + variant);
    }

}