package org.blazers.helper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import org.blazers.BlazersMod;


/**
 * Helper methods for {@link DeferredRegister registries}
 */
public final class RegistryHelper {

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