package org.blazers.core;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.blazers.loot.MusicDiscAdditionModifier;

/**
 * {@link BlazersMod Blazers Mod} {@link IGlobalLootModifier Global Loot Modifiers}
 */
public final class BLLootModifiers {

    //#region Registry

    /**
     * The {@link DeferredRegister<IGlobalLootModifier> Global Loot Modifiers Registry}
     */
    private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = RegistryHelper.registry(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    //#endregion

    //#region Loot Modifiers

    public static final RegistryObject<MapCodec<MusicDiscAdditionModifier>> MUSIC_DISC = registerLootModifier("music_disc", MusicDiscAdditionModifier.CODEC);

    //#endregion

    //#region Methods

    /**
     * Register a {@link IGlobalLootModifier Loot Modifier}
     *
     * @param name {@link String The Loot Modifier name}
     * @param lootModifierCodec {@link MapCodec<IGlobalLootModifier> The Loot Modifier Codec}
     * @return {@link RegistryObject<IGlobalLootModifier> The registered Loot Modifier}
     */
    private static <T extends IGlobalLootModifier> RegistryObject<MapCodec<T>> registerLootModifier(final String name, final MapCodec<T> lootModifierCodec) {
        return LOOT_MODIFIERS.register(name + "_loot_modifier", Suppliers.memoize(() -> lootModifierCodec));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link IGlobalLootModifier Loot Modifiers}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }

    //#endregion

}