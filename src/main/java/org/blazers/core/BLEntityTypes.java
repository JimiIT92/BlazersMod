package org.blazers.core;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.entity.WitherSkeletonHorse;
import org.blazers.entity.client.WitherSkeletonHorseRenderer;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link EntityType Entity Types}
 */
public final class BLEntityTypes {

    /**
     * {@link EntityType Entity Types} {@link DeferredRegister <Block> Registry}
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, BlazersMod.MOD_ID);

    //#region Entity Types

    public static final RegistryObject<EntityType<WitherSkeletonHorse>> WITHER_SKELETON_HORSE = ENTITY_TYPES.register("wither_skeleton_horse",
            () -> EntityType.Builder.of(WitherSkeletonHorse::new, MobCategory.CREATURE)
                    .sized(1.3964844F, 1.6F).clientTrackingRange(10)
                    .build(new ResourceLocation(BlazersMod.MOD_ID, "wither_skeleton_horse").toString()));

    //#endregion

    /**
     * Register Entity Renderers
     */
    public static void registerRenderers() {
        EntityRenderers.register(WITHER_SKELETON_HORSE.get(), WitherSkeletonHorseRenderer::new);
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