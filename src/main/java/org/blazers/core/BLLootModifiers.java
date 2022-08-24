package org.blazers.core;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.loot.MusicDiscAdditionModifier;

/**
 * {@link BlazersMod Blazers Mod} {@link IGlobalLootModifier Global Loot Modifiers}
 */
public final class BLLootModifiers {

    /**
     * {@link IGlobalLootModifier Global Loot Modifier} {@link DeferredRegister<IGlobalLootModifier> Registry}
     */
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, BlazersMod.MOD_ID);

    //#region Loot Modifiers

    public static final RegistryObject<Codec<MusicDiscAdditionModifier>> MUSIC_DISC_LOOT_MODIFIER = LOOT_MODIFIERS
            .register("music_disc_loot_modifier", MusicDiscAdditionModifier.CODEC);

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link IGlobalLootModifier Global Loot Modifiers}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}
