package org.blazers.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link SoundEvent Sounds}
 */
public final class BLSounds {

    /**
     * {@link SoundEvent Paintings} {@link DeferredRegister <SoundEvent> Registry}
     */
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BlazersMod.MOD_ID);

    //#region Sounds

    public static final RegistryObject<SoundEvent> BRUH = register("bruh");
    public static final RegistryObject<SoundEvent> REVERSED_BRUH = register("reversed_bruh");
    public static final RegistryObject<SoundEvent> GOAT_HORN_BRO = register("goat_horn_bro");
    public static final RegistryObject<SoundEvent> GOAT_HORN_FLY = register("goat_horn_fly");
    public static final RegistryObject<SoundEvent> GOAT_HORN_RESIST = register("goat_horn_resist");

    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING = register("copper_horn_great_sky_falling");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_BASS = register("copper_horn_great_sky_falling_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_HARMONY = register("copper_horn_great_sky_falling_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING = register("copper_horn_old_hymn_resting");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_BASS = register("copper_horn_old_hymn_resting_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_HARMONY = register("copper_horn_old_hymn_resting_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE = register("copper_horn_pure_water_desire");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_BASS = register("copper_horn_pure_water_desire_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_HARMONY = register("copper_horn_pure_water_desire_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY = register("copper_horn_mumble_fire_memory");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS = register("copper_horn_mumble_fire_memory_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY = register("copper_horn_mumble_fire_memory_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER = register("copper_horn_dry_urge_anger");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_BASS = register("copper_horn_dry_urge_anger_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_HARMONY = register("copper_horn_dry_urge_anger_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY = register("copper_horn_clear_temper_journey");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS = register("copper_horn_clear_temper_journey_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY = register("copper_horn_clear_temper_journey_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT = register("copper_horn_fresh_nest_thought");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_BASS = register("copper_horn_fresh_nest_thought_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY = register("copper_horn_fresh_nest_thought_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR = register("copper_horn_secret_lake_tear");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_BASS = register("copper_horn_secret_lake_tear_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_HARMONY = register("copper_horn_secret_lake_tear_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT = register("copper_horn_fearless_river_gift");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_BASS = register("copper_horn_fearless_river_gift_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY = register("copper_horn_fearless_river_gift_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE = register("copper_horn_sweet_moon_love");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_BASS = register("copper_horn_sweet_moon_love_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_HARMONY = register("copper_horn_sweet_moon_love_harmony");

    public static final RegistryObject<SoundEvent> MUSIC_DISC_SURVIVAL = register("music_disc_survival");
    public static final RegistryObject<SoundEvent> MUSIC_DISC_ENDERMAN_VS_BLAZE = register("music_disc_enderman_vs_blaze");

    //#endregion

    /**
     * Register a {@link SoundEvent Sound}
     *
     * @param name {@link String Sound Name}
     * @return {@link RegistryObject<SoundEvent> Registered sound}
     */
    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(BlazersMod.MOD_ID, name)));
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link SoundEvent Sounds}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}