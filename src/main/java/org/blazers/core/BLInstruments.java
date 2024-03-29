package org.blazers.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

/**
 * {@link BlazersMod Blazers Mod}  {@link Instrument Instruments}
 */
public final class BLInstruments {

    //#region Instruments

    public static final ResourceKey<Instrument> BRO_GOAT_HORN = create("bro_goat_horn");
    public static final ResourceKey<Instrument> FLY_GOAT_HORN = create("fly_goat_horn");
    public static final ResourceKey<Instrument> RESIST_GOAT_HORN = create("resist_goat_horn");

    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN = create("great_sky_falling_copper_horn");
    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_BASS = create("great_sky_falling_copper_horn_bass");
    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_HARMONY = create("great_sky_falling_copper_horn_harmony");
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN = create("old_hymn_resting_copper_horn");
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_BASS = create("old_hymn_resting_copper_horn_bass");
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_HARMONY = create("old_hymn_resting_copper_horn_harmony");
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN = create("pure_water_desire_copper_horn");
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_BASS = create("pure_water_desire_copper_horn_bass");
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_HARMONY = create("pure_water_desire_copper_horn_harmony");
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN = create("mumble_fire_memory_copper_horn");
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS = create("mumble_fire_memory_copper_horn_bass");
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY = create("mumble_fire_memory_copper_horn_harmony");
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN = create("dry_urge_anger_copper_horn");
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_BASS = create("dry_urge_anger_copper_horn_bass");
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_HARMONY = create("dry_urge_anger_copper_horn_harmony");
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN = create("clear_temper_journey_copper_horn");
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS = create("clear_temper_journey_copper_horn_bass");
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY = create("clear_temper_journey_copper_horn_harmony");
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN = create("fresh_nest_thought_copper_horn");
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_BASS = create("fresh_nest_thought_copper_horn_bass");
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY = create("fresh_nest_thought_copper_horn_harmony");
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN = create("secret_lake_tear_copper_horn");
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_BASS = create("secret_lake_tear_copper_horn_bass");
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_HARMONY = create("secret_lake_tear_copper_horn_harmony");
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN = create("fearless_river_gift_copper_horn");
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_BASS = create("fearless_river_gift_copper_horn_bass");
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY = create("fearless_river_gift_copper_horn_harmony");
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN = create("sweet_moon_love_copper_horn");
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_BASS = create("sweet_moon_love_copper_horn_bass");
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_HARMONY = create("sweet_moon_love_copper_horn_harmony");

    //#endregion

    /**
     * Create an {@link ResourceKey<Instrument> Instrument}
     *
     * @param name {@link String Instrument Name}
     * @return {@link ResourceKey<Instrument> Instrument}
     */
    private static ResourceKey<Instrument> create(String name) {
        return ResourceKey.create(Registries.INSTRUMENT, new ResourceLocation(BlazersMod.MOD_ID, name));
    }

    /**
     * Register all {@link ResourceKey<Instrument> Instruments}
     */
    public static void register() {
        Registry.register(BuiltInRegistries.INSTRUMENT, BRO_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_BRO));
        Registry.register(BuiltInRegistries.INSTRUMENT, FLY_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_FLY));
        Registry.register(BuiltInRegistries.INSTRUMENT, RESIST_GOAT_HORN, createInstrument(BLSounds.GOAT_HORN_RESIST));
        Registry.register(BuiltInRegistries.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING));
        Registry.register(BuiltInRegistries.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, GREAT_SKY_FALLING_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_GREAT_SKY_FALLING_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING));
        Registry.register(BuiltInRegistries.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, OLD_HYMN_RESTING_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_OLD_HYMN_RESTING_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE));
        Registry.register(BuiltInRegistries.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, PURE_WATER_DESIRE_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_PURE_WATER_DESIRE_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY));
        Registry.register(BuiltInRegistries.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER));
        Registry.register(BuiltInRegistries.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, DRY_URGE_ANGER_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_DRY_URGE_ANGER_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY));
        Registry.register(BuiltInRegistries.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT));
        Registry.register(BuiltInRegistries.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR));
        Registry.register(BuiltInRegistries.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, SECRET_LAKE_TEAR_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_SECRET_LAKE_TEAR_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT));
        Registry.register(BuiltInRegistries.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY));
        Registry.register(BuiltInRegistries.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE));
        Registry.register(BuiltInRegistries.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN_BASS, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE_BASS));
        Registry.register(BuiltInRegistries.INSTRUMENT, SWEET_MOON_LOVE_COPPER_HORN_HARMONY, createInstrument(BLSounds.COPPER_HORN_SWEET_MOON_LOVE_HARMONY));

    }

    /**
     * Create an {@link Instrument Instrument} from a {@link SoundEvent Sound}
     *
     * @param sound {@link RegistryObject<SoundEvent> Sound}
     * @return {@link Instrument Instrument}
     */
    private static Instrument createInstrument(RegistryObject<SoundEvent> sound) {
        return new Instrument(sound.getHolder().get(), 140, 256.0F);
    }

}