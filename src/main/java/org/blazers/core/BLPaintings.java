package org.blazers.core;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;

public final class BLPaintings {

    public static PaintingVariant ERENBLAZE = register("erenblaze", new PaintingVariant(32, 32));
    public static PaintingVariant SURVIVAL = register("survival", new PaintingVariant(16, 32));
    public static PaintingVariant BRUH = register("bruh", new PaintingVariant(16, 16));

    private static PaintingVariant register(String name, PaintingVariant variant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(BlazersMod.MOD_ID, name), variant);
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " paintings");
    }
}