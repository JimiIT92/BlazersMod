package org.blazers.helper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import org.blazers.BlazersMod;


/**
 * Helper methods for {@link DeferredRegister registries}
 */
public final class RegistryHelper {

    /**
     * Get a {@link BlazersMod Blazers Mod} {@link ResourceLocation Resource Location}
     *
     * @param name {@link String The resource name}
     * @return {@link ResourceLocation The resource location}
     */
    public static ResourceLocation location(final String name) {
        return location(BlazersMod.MOD_ID, name);
    }

    /**
     * Get a {@link BlazersMod Blazers Mod} {@link ResourceLocation Resource Location}
     *
     * @param modId {@link String The mod ID}
     * @param name {@link String The resource name}
     * @return {@link ResourceLocation The resource location}
     */
    public static ResourceLocation location(final String modId, final String name) {
        return new ResourceLocation(modId, name);
    }

    /**
     * Parse a {@link String serialized Resource Location}
     *
     * @param resourceLocation {@link String The serialized Resource Location}
     * @return {@link ResourceLocation The Resource Location}
     */
    public static ResourceLocation parse(final String resourceLocation) {
        return ResourceLocation.tryParse(resourceLocation);
    }

    /**
     * Get a {@link BlazersMod Blazers Mod} string {@link ResourceLocation Resource Location}
     *
     * @param name {@link String The resource name}
     * @return {@link String The resource location string}
     */
    public static String stringLocation(final String name) {
        return location(name).toString();
    }

    /**
     * Create a {@link DeferredRegister<T> Deferred Register}
     *
     * @param registryType {@link IForgeRegistry <T> The registry type}
     * @return {@link DeferredRegister<T> The Deferred Register}
     * @param <T> {@link T The Deferred Register Type}
     */
    public static <T> DeferredRegister<T> registry(final IForgeRegistry<T> registryType) {
        return registry(registryType.getRegistryKey());
    }

    /**
     * Create a {@link DeferredRegister<T> Deferred Register}
     *
     * @param registryKey {@link ResourceKey The registry resource key}
     * @return {@link DeferredRegister<T> The Deferred Register}
     * @param <T> {@link T The Deferred Register Type}
     */
    public static <T> DeferredRegister<T> registry(final ResourceKey<? extends Registry<T>> registryKey) {
        return DeferredRegister.create(registryKey, BlazersMod.MOD_ID);
    }

}