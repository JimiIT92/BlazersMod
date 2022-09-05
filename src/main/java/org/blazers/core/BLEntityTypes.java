package org.blazers.core;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;
import org.blazers.entity.*;
import org.blazers.entity.client.*;

public final class BLEntityTypes {

    public static final EntityType<WitherSkeletonHorseEntity> WITHER_SKELETON_HORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "wither_skeleton_horse"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WitherSkeletonHorseEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.6F))
                    .fireImmune().trackRangeBlocks(10).build());

    public static final EntityType<FireflyEntity> FIREFLY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "firefly"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, FireflyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.2F, 0.2F))
                    .trackRangeBlocks(10).build());

    public static final EntityType<AtomicTntEntity> PRIMED_ATOMIC_TNT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "primed_atomic_tnt"),
            FabricEntityTypeBuilder.<AtomicTntEntity>create(SpawnGroup.MISC, AtomicTntEntity::new)
                    .dimensions(EntityDimensions.fixed(0.98F, 0.98F))
                    .fireImmune().trackedUpdateRate(10).trackRangeBlocks(10).build());

    public static final EntityType<SpearEntity> SPEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "spear"),
            FabricEntityTypeBuilder.<SpearEntity>create(SpawnGroup.MISC, SpearEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .fireImmune().trackedUpdateRate(20).trackRangeBlocks(4).build());

    public static final EntityType<SpearEntity> MALACHITE_SPEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "malachite_spear"),
            FabricEntityTypeBuilder.<SpearEntity>create(SpawnGroup.MISC, SpearEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .fireImmune().trackedUpdateRate(20).trackRangeBlocks(4).build());

    public static final EntityType<CopperGolemEntity> COPPER_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BlazersMod.MOD_ID, "copper_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, CopperGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8F, 1.3F))
                    .trackRangeBlocks(10).build());

    public static void registerRenderers() {
        EntityRendererRegistry.register(WITHER_SKELETON_HORSE, WitherSkeletonHorseRenderer::new);
        EntityRendererRegistry.register(FIREFLY, FireflyEntityRenderer::new);
        EntityRendererRegistry.register(PRIMED_ATOMIC_TNT, AtomicTntEntityRenderer::new);
        EntityRendererRegistry.register(SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.register(MALACHITE_SPEAR, MalachiteSpearEntityRenderer::new);
        EntityRendererRegistry.register(COPPER_GOLEM, CopperGolemEntityRenderer::new);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(WITHER_SKELETON_HORSE, WitherSkeletonHorseEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FIREFLY, FireflyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(COPPER_GOLEM, CopperGolemEntity.createAttributes());
    }
}