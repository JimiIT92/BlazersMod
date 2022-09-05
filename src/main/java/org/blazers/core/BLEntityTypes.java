package org.blazers.core;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;
import org.blazers.entity.AtomicTntEntity;
import org.blazers.entity.SpearEntity;
import org.blazers.entity.client.AtomicTntRenderer;
import org.blazers.entity.client.MalachiteSpearEntityRenderer;
import org.blazers.entity.client.SpearEntityRenderer;

public final class BLEntityTypes {

    //with ske
    //firefly
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

    //cop gol

    public static void registerRenderers() {
        EntityRendererRegistry.register(PRIMED_ATOMIC_TNT, AtomicTntRenderer::new);
        EntityRendererRegistry.register(SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.register(MALACHITE_SPEAR, MalachiteSpearEntityRenderer::new);
    }
}