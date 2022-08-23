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
    public static final RegistryObject<SoundEvent> BRO = register("bro");

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