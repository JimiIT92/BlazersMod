package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link SoundEvent Sounds}
 */
public final class BLSounds {

    //#region Registry

    /**
     * The {@link DeferredRegister<SoundEvent> Sound Registry}
     */
    private static final DeferredRegister<SoundEvent> SOUNDS = RegistryHelper.registry(ForgeRegistries.SOUND_EVENTS);

    //#endregion

    //#region Sounds

    //#region Music Discs

    public static final RegistryObject<SoundEvent> MUSIC_DISC_SURVIVAL = registerSound("music_disc_survival");
    public static final RegistryObject<SoundEvent> MUSIC_DISC_ENDERMAN_VS_BLAZE = registerSound("music_disc_enderman_vs_blaze");

    //#endregion

    //#region Goat Horns

    public static final RegistryObject<SoundEvent> GOAT_HORN_BRO = registerSound("goat_horn_bro");
    public static final RegistryObject<SoundEvent> GOAT_HORN_FLY = registerSound("goat_horn_fly");
    public static final RegistryObject<SoundEvent> GOAT_HORN_RESIST = registerSound("goat_horn_resist");

    //#endregion

    //#region Copper Horns

    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING = registerSound("copper_horn_great_sky_falling");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_BASS = registerSound("copper_horn_great_sky_falling_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_HARMONY = registerSound("copper_horn_great_sky_falling_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING = registerSound("copper_horn_old_hymn_resting");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_BASS = registerSound("copper_horn_old_hymn_resting_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_HARMONY = registerSound("copper_horn_old_hymn_resting_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE = registerSound("copper_horn_pure_water_desire");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_BASS = registerSound("copper_horn_pure_water_desire_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_HARMONY = registerSound("copper_horn_pure_water_desire_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY = registerSound("copper_horn_mumble_fire_memory");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS = registerSound("copper_horn_mumble_fire_memory_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY = registerSound("copper_horn_mumble_fire_memory_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER = registerSound("copper_horn_dry_urge_anger");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_BASS = registerSound("copper_horn_dry_urge_anger_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_HARMONY = registerSound("copper_horn_dry_urge_anger_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY = registerSound("copper_horn_clear_temper_journey");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS = registerSound("copper_horn_clear_temper_journey_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY = registerSound("copper_horn_clear_temper_journey_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT = registerSound("copper_horn_fresh_nest_thought");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_BASS = registerSound("copper_horn_fresh_nest_thought_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY = registerSound("copper_horn_fresh_nest_thought_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR = registerSound("copper_horn_secret_lake_tear");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_BASS = registerSound("copper_horn_secret_lake_tear_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_HARMONY = registerSound("copper_horn_secret_lake_tear_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT = registerSound("copper_horn_fearless_river_gift");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_BASS = registerSound("copper_horn_fearless_river_gift_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY = registerSound("copper_horn_fearless_river_gift_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE = registerSound("copper_horn_sweet_moon_love");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_BASS = registerSound("copper_horn_sweet_moon_love_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_HARMONY = registerSound("copper_horn_sweet_moon_love_harmony");

    //#endregion

    //#region Misc

    public static final RegistryObject<SoundEvent> BRUH = registerSound("bruh");
    public static final RegistryObject<SoundEvent> REVERSED_BRUH = registerSound("reversed_bruh");

    //#endregion

    //#endregion

    //#region Methods

    private static RegistryObject<SoundEvent> registerSound(final String name) {
        return SOUNDS.register(name, Suppliers.memoize(() -> SoundEvent.createVariableRangeEvent(RegistryHelper.location(name))));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link SoundEvent Sounds}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }

    //#endregion

}