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

    public static final RegistryObject<SoundEvent> MUSIC_DISC_SURVIVAL = registerSound("music_disc_survival");
    public static final RegistryObject<SoundEvent> MUSIC_DISC_ENDERMAN_VS_BLAZE = registerSound("music_disc_enderman_vs_blaze");

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