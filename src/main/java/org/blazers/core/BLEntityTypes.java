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
import org.blazers.client.renderer.entity.CopperGolemRenderer;
import org.blazers.client.renderer.entity.FireflyRenderer;
import org.blazers.client.renderer.entity.WitherSkeletonHorseRenderer;
import org.blazers.client.renderer.projectile.ThrownMalachiteSpearRenderer;
import org.blazers.client.renderer.projectile.ThrownSpearRenderer;
import org.blazers.entity.CopperGolem;
import org.blazers.entity.animal.Firefly;
import org.blazers.entity.animal.WitherSkeletonHorse;
import org.blazers.entity.block.PrimedAtomicTnt;
import org.blazers.entity.projectile.ThrownSpear;
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

    public static final RegistryObject<EntityType<ThrownSpear>> SPEAR = registerEntityType("spear", EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
            .sized(0.5F, 0.5F).
            clientTrackingRange(4)
            .updateInterval(20)
    );

    public static final RegistryObject<EntityType<ThrownSpear>> MALACHITE_SPEAR = registerEntityType("malachite_spear", EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
    );

    public static final RegistryObject<EntityType<WitherSkeletonHorse>> WITHER_SKELETON_HORSE = registerEntityType("wither_skeleton_horse", EntityType.Builder.of(WitherSkeletonHorse::new, MobCategory.CREATURE)
            .sized(1.3964844F, 1.6F)
            .eyeHeight(1.52F)
            .passengerAttachments(1.31875F)
            .clientTrackingRange(10)
            .fireImmune()
    );

    public static final RegistryObject<EntityType<Firefly>> FIREFLY = registerEntityType("firefly", EntityType.Builder.of(Firefly::new, MobCategory.AMBIENT)
            .sized(0.2F, 0.2F)
            .eyeHeight(0.1F)
            .clientTrackingRange(10)
    );

    public static final RegistryObject<EntityType<CopperGolem>> COPPER_GOLEM = registerEntityType("copper_golem", EntityType.Builder.of(CopperGolem::new, MobCategory.MISC)
            .sized(0.8F, 1.3F)
            .eyeHeight(0.8F)
            .clientTrackingRange(10)
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
        EntityRenderers.register(SPEAR.get(), ThrownSpearRenderer::new);
        EntityRenderers.register(MALACHITE_SPEAR.get(), ThrownMalachiteSpearRenderer::new);
        EntityRenderers.register(WITHER_SKELETON_HORSE.get(), WitherSkeletonHorseRenderer::new);
        EntityRenderers.register(FIREFLY.get(), FireflyRenderer::new);
        EntityRenderers.register(COPPER_GOLEM.get(), CopperGolemRenderer::new);
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