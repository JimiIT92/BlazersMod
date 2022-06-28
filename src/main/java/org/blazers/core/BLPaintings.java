package org.blazers.core;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Motive Paintings}
 */
public final class BLPaintings {

    /**
     * {@link Motive Paintings} {@link DeferredRegister<Motive> Registry}
     */
    public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, BlazersMod.MOD_ID);

    //#region Paintings

    public static RegistryObject<Motive> ERENBLAZE = PAINTINGS.register("erenblaze", () -> new Motive(32, 32));
    public static RegistryObject<Motive> SURVIVAL = PAINTINGS.register("survival", () -> new Motive(16, 32));
    public static RegistryObject<Motive> BRUH = PAINTINGS.register("bruh", () -> new Motive(16, 16));

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link Motive Paintings}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        PAINTINGS.register(eventBus);
    }

}