package org.blazers.helper;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.CreativeModeTab;
import org.blazers.BlazersMod;

/**
 * Helper methods for {@link MutableComponent text components}
 */
public final class TextHelper {

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab name}
     * @return The {@link CreativeModeTab Creative Mode Tab} {@link MutableComponent Mutable Translatable Component}
     */
    public static MutableComponent tab(final String name) {
        return get("itemGroup", name);
    }

    /**
     * Get a {@link BlazersMod Blazers Mod} {@link MutableComponent Mutable Translatable Component}
     *
     * @param prefix {@link String The Component translation key prefix}
     * @param suffix {@link String The Component translation key suffix}
     * @param args {@link Object The Component translation arguments}
     * @return {@link MutableComponent The Mutable Translatable Component}
     */
    private static MutableComponent get(final String prefix, final String suffix, final Object... args) {
        return get(prefix + "." + BlazersMod.MOD_ID + "." + suffix, args);
    }

    /**
     * Get a {@link MutableComponent Mutable Translatable Component}
     *
     * @param key {@link String The Component translation key}
     * @param args {@link Object The Component translation arguments}
     * @return {@link MutableComponent The Mutable Translatable Component}
     */
    public static MutableComponent get(final String key, final Object... args) {
        return Component.translatable(key, args);
    }

}