package org.blazers.core;

import net.minecraft.item.Instrument;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.blazers.BlazersMod;

public final class BLInstruments {

    public static final RegistryKey<Instrument> BRO_GOAT_HORN = create("bro_goat_horn");
    public static final RegistryKey<Instrument> FLY_GOAT_HORN = create("fly_goat_horn");
    public static final RegistryKey<Instrument> RESIST_GOAT_HORN = create("resist_goat_horn");

    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN = create("great_sky_falling_copper_horn");
    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_BASS = create("great_sky_falling_copper_horn_bass");
    public static final RegistryKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_HARMONY = create("great_sky_falling_copper_horn_harmony");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN = create("old_hymn_resting_copper_horn");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_BASS = create("old_hymn_resting_copper_horn_bass");
    public static final RegistryKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_HARMONY = create("old_hymn_resting_copper_horn_harmony");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN = create("pure_water_desire_copper_horn");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_BASS = create("pure_water_desire_copper_horn_bass");
    public static final RegistryKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_HARMONY = create("pure_water_desire_copper_horn_harmony");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN = create("mumble_fire_memory_copper_horn");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS = create("mumble_fire_memory_copper_horn_bass");
    public static final RegistryKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY = create("mumble_fire_memory_copper_horn_harmony");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN = create("dry_urge_anger_copper_horn");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_BASS = create("dry_urge_anger_copper_horn_bass");
    public static final RegistryKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_HARMONY = create("dry_urge_anger_copper_horn_harmony");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN = create("clear_temper_journey_copper_horn");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS = create("clear_temper_journey_copper_horn_bass");
    public static final RegistryKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY = create("clear_temper_journey_copper_horn_harmony");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN = create("fresh_nest_thought_copper_horn");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_BASS = create("fresh_nest_thought_copper_horn_bass");
    public static final RegistryKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY = create("fresh_nest_thought_copper_horn_harmony");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN = create("secret_lake_tear_copper_horn");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_BASS = create("secret_lake_tear_copper_horn_bass");
    public static final RegistryKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_HARMONY = create("secret_lake_tear_copper_horn_harmony");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN = create("fearless_river_gift_copper_horn");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_BASS = create("fearless_river_gift_copper_horn_bass");
    public static final RegistryKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY = create("fearless_river_gift_copper_horn_harmony");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN = create("sweet_moon_love_copper_horn");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_BASS = create("sweet_moon_love_copper_horn_bass");
    public static final RegistryKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_HARMONY = create("sweet_moon_love_copper_horn_harmony");

    private static RegistryKey<Instrument> create(String name) {
        return RegistryKey.of(Registry.INSTRUMENT_KEY, new Identifier(BlazersMod.MOD_ID, name));
    }


    public static void register() {
        Registry.register(Registry.INSTRUMENT, BRO_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_BRO));
        Registry.register(Registry.INSTRUMENT, FLY_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_FLY));
        Registry.register(Registry.INSTRUMENT, RESIST_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_RESIST));
        Registry.register(Registry.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING));
        Registry.register(Registry.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING_BASS));
        Registry.register(Registry.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING_HARMONY));
        Registry.register(Registry.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING));
        Registry.register(Registry.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING_BASS));
        Registry.register(Registry.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING_HARMONY));
        Registry.register(Registry.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE));
        Registry.register(Registry.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE_BASS));
        Registry.register(Registry.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE_HARMONY));
        Registry.register(Registry.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY));
        Registry.register(Registry.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS));
        Registry.register(Registry.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY));
        Registry.register(Registry.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER));
        Registry.register(Registry.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER_BASS));
        Registry.register(Registry.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER_HARMONY));
        Registry.register(Registry.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY));
        Registry.register(Registry.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS));
        Registry.register(Registry.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY));
        Registry.register(Registry.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT));
        Registry.register(Registry.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT_BASS));
        Registry.register(Registry.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY));
        Registry.register(Registry.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR));
        Registry.register(Registry.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR_BASS));
        Registry.register(Registry.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR_HARMONY));
        Registry.register(Registry.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT));
        Registry.register(Registry.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_BASS));
        Registry.register(Registry.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY));
        Registry.register(Registry.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE));
        Registry.register(Registry.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE_BASS));
        Registry.register(Registry.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE_HARMONY));
    }

    private static Instrument createInstrument(SoundEvent sound) {
        return new Instrument(sound, 140, 256.0F);
    }
}