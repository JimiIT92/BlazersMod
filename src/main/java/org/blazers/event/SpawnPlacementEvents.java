package org.blazers.event;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;

@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class SpawnPlacementEvents {

    @SubscribeEvent
    public static void onSpawnPlacementsRegistry(final SpawnPlacementRegisterEvent event) {
        event.register(BLEntityTypes.WITHER_SKELETON_HORSE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entity, level, spawnType, pos, random) -> true,
                SpawnPlacementRegisterEvent.Operation.AND);
        event.register(BLEntityTypes.FIREFLY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entity, level, spawn, pos, random) -> level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON),
                SpawnPlacementRegisterEvent.Operation.AND);
    }

}