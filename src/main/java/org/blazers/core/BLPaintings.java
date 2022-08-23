package org.blazers.core;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.entity.decoration.PaintingVariant Paintings}
 */
public final class BLPaintings {

    /**
     * {@link net.minecraft.world.entity.decoration.PaintingVariant Paintings} {@link DeferredRegister<net.minecraft.world.entity.decoration.PaintingVariant> Registry}
     */
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, BlazersMod.MOD_ID);

    //#region Paintings

    public static RegistryObject<PaintingVariant> ERENBLAZE = PAINTINGS.register("erenblaze", () -> new PaintingVariant(32, 32));
    public static RegistryObject<PaintingVariant> SURVIVAL = PAINTINGS.register("survival", () -> new PaintingVariant(16, 32));
    public static RegistryObject<PaintingVariant> BRUH = PAINTINGS.register("bruh", () -> new PaintingVariant(16, 16));

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link PaintingVariant Paintings}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        PAINTINGS.register(eventBus);
    }

}