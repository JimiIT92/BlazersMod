package org.blazers.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.blazers.core.BLEntityTypes;

public class BLEntitiesGeneration {

    public static void generateEntities() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), SpawnGroup.CREATURE, BLEntityTypes.WITHER_SKELETON_HORSE, 20, 2, 4);

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.SWAMP,
                        BiomeKeys.MANGROVE_SWAMP
                ), SpawnGroup.AMBIENT, BLEntityTypes.FIREFLY, 40, 5, 6
        );


        SpawnRestriction.register(BLEntityTypes.WITHER_SKELETON_HORSE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> true);
        SpawnRestriction.register(BLEntityTypes.FIREFLY, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON));
    }
}