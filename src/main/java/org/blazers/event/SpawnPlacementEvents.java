package org.blazers.event;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;

import java.util.function.Supplier;

/**
 * Handle all events for entity spawns
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SpawnPlacementEvents {

    /**
     * Register all spawn placements
     *
     * @param event {@link SpawnPlacementRegisterEvent The spawn placement register event}
     */
    @SubscribeEvent
    public static void onSpawnPlacementRegister(final SpawnPlacementRegisterEvent event) {
        registerSpawnPlacement(event, BLEntityTypes.WITHER_SKELETON_HORSE, (entityType, level, spawnType, blockPos, randomSource) -> true);
        registerSpawnPlacement(event, BLEntityTypes.FIREFLY, (entityType, level, spawnType, blockPos, randomSource) -> level.getBlockState(blockPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON));
    }

    /**
     * Register a spawn placement
     *
     * @param event {@link SpawnPlacementRegisterEvent The spawn placement register event}
     * @param entityTypeSupplier {@link Supplier<EntityType> The entity type supplier}
     * @param spawnPredicate {@link SpawnPlacements.SpawnPredicate The spawn predicate}
     * @param <T> {@link Entity The entity type}
     */
    private static <T extends Entity> void registerSpawnPlacement(final SpawnPlacementRegisterEvent event, final Supplier<EntityType<T>> entityTypeSupplier, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {
        event.register(entityTypeSupplier.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawnPredicate, SpawnPlacementRegisterEvent.Operation.AND);
    }

}