package org.blazers.core;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.entity.Firefly;
import org.blazers.entity.PrimedAtomicTnt;
import org.blazers.entity.ThrownSpear;
import org.blazers.entity.WitherSkeletonHorse;
import org.blazers.entity.client.*;
import org.blazers.entity.client.model.FireflyModel;
import org.blazers.entity.client.model.ThrownMalachiteSpearModel;
import org.blazers.entity.client.model.ThrownSpearModel;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link EntityType Entity Types}
 */
public final class BLEntityTypes {

    /**
     * {@link EntityType Entity Types} {@link DeferredRegister<EntityType> Registry}
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BlazersMod.MOD_ID);

    //#region Entity Types

    public static final RegistryObject<EntityType<WitherSkeletonHorse>> WITHER_SKELETON_HORSE = ENTITY_TYPES.register("wither_skeleton_horse",
            () -> EntityType.Builder.of(WitherSkeletonHorse::new, MobCategory.CREATURE)
                    .sized(1.3964844F, 1.6F).clientTrackingRange(10)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "wither_skeleton_horse").toString()));

    public static final RegistryObject<EntityType<Firefly>> FIREFLY = ENTITY_TYPES.register("firefly",
            () -> EntityType.Builder.of(Firefly::new, MobCategory.AMBIENT)
                    .sized(0.2F, 0.2F).clientTrackingRange(10)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "firefly").toString()));

    public static final RegistryObject<EntityType<PrimedAtomicTnt>> PRIMED_ATOMIC_TNT = ENTITY_TYPES.register("primed_atomic_tnt",
            () -> EntityType.Builder.<PrimedAtomicTnt>of(PrimedAtomicTnt::new, MobCategory.MISC)
                    .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "primed_atomic_tnt").toString()));

    public static final RegistryObject<EntityType<ThrownSpear>> SPEAR = ENTITY_TYPES.register("spear",
            () -> EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "spear").toString()));

    public static final RegistryObject<EntityType<ThrownSpear>> MALACHITE_SPEAR = ENTITY_TYPES.register("malachite_spear",
            () -> EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "malachite_spear").toString()));

    //#endregion

    /**
     * Register Entity Renderers
     */
    public static void registerRenderers() {
        EntityRenderers.register(WITHER_SKELETON_HORSE.get(), WitherSkeletonHorseRenderer::new);
        EntityRenderers.register(FIREFLY.get(), FireflyRenderer::new);
        EntityRenderers.register(PRIMED_ATOMIC_TNT.get(), AtomicTntRenderer::new);
        EntityRenderers.register(SPEAR.get(), ThrownSpearRenderer::new);
        EntityRenderers.register(MALACHITE_SPEAR.get(), ThrownMalachiteSpearRenderer::new);
    }

    /**
     * Register Entity Layers
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions Register Layers Definitions Event}
     */
    public static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FireflyModel.LAYER_LOCATION, FireflyModel::createBodyLayer);
        event.registerLayerDefinition(ThrownSpearModel.LAYER_LOCATION, ThrownSpearModel::createBodyLayer);
        event.registerLayerDefinition(ThrownMalachiteSpearModel.LAYER_LOCATION, ThrownMalachiteSpearModel::createBodyLayer);
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link EntityType Entity Types}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}