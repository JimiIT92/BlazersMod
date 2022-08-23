package org.blazers.core;

import net.minecraft.core.Registry;
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

    //#endregion

    /**
     * Create an {@link ResourceKey<Instrument> Instrument}
     *
     * @param name {@link String Instrument Name}
     * @return {@link ResourceKey<Instrument> Instrument}
     */
    private static ResourceKey<Instrument> create(String name) {
        return ResourceKey.create(Registry.INSTRUMENT_REGISTRY, new ResourceLocation(BlazersMod.MOD_ID, name));
    }

    /**
     * Register all {@link ResourceKey<Instrument> Instruments}
     */
    public static void register() {
        Registry.register(Registry.INSTRUMENT, BRO_GOAT_HORN, createInstrument(BLSounds.BRO));
    }

    /**
     * Create an {@link Instrument Instrument} from a {@link SoundEvent Sound}
     *
     * @param sound {@link RegistryObject<SoundEvent> Sound}
     * @return {@link Instrument Instrument}
     */
    private static Instrument createInstrument(RegistryObject<SoundEvent> sound) {
        return new Instrument(sound.get(), 140, 256.0F);
    }

}