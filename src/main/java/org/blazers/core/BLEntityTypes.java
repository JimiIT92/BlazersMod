package org.blazers.core;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.client.renderer.AtomicTntRenderer;
import org.blazers.entity.block.PrimedAtomicTnt;
import org.blazers.helper.RegistryHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link EntityType Entity Types}
 */
public final class BLEntityTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<EntityType> Entity Type Registry}
     */
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = RegistryHelper.registry(ForgeRegistries.ENTITY_TYPES);

    //#endregion

    //#region Entity Types

    public static final RegistryObject<EntityType<PrimedAtomicTnt>> PRIMED_ATOMIC_TNT = registerEntityType("primed_atomic_tnt", EntityType.Builder.<PrimedAtomicTnt>of(PrimedAtomicTnt::new, MobCategory.MISC)
            .fireImmune()
            .sized(0.98F, 0.98F)
            .clientTrackingRange(10)
            .updateInterval(10)
    );

    //#endregion

    //#region Methods

    /**
     * Register an {@link EntityType Entity Type}
     *
     * @param name {@link String The Entity Type name}
     * @param entityTypeBuilder {@link EntityType.Builder The Entity Type Builder}
     * @return {@link RegistryObject<EntityType> The registered Entity Type}
     * @param <T> {@link T The Entity type}
     */
    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(name, () -> entityTypeBuilder.build(RegistryHelper.stringLocation(name)));
    }

    //#endregion

    //#region Entity Renderers

    /**
     * Register {@link BlazersMod Blazers Mod} {@link EntityRenderer Entity Renderers}
     */
    public static void registerRenderers() {
        EntityRenderers.register(PRIMED_ATOMIC_TNT.get(), AtomicTntRenderer::new);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link EntityType Entity Types}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    //#endregion

}