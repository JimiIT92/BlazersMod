package org.blazers.world.feature.gen;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.blazers.core.BLEntityTypes;

import java.util.List;

/**
 * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.entity.Entity Entities} into the {@link net.minecraft.world.level.Level World}
 */
public final class BLEntityGeneration {

    /**
     * Generate {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.level.block.Block Ores} into the {@link net.minecraft.world.level.Level World}
     *
     * @param event {@link BiomeLoadingEvent Biome Loading Event}
     */
    public static void spawnEntities(final BiomeLoadingEvent event) {
        Biome.BiomeCategory category = event.getCategory();

        if(Biome.BiomeCategory.NETHER.equals(category)) {
            addEntityToBiomeSpawns(event, BLEntityTypes.WITHER_SKELETON_HORSE.get(), 20, 2, 4);
        } else if(Biome.BiomeCategory.SWAMP.equals(category)) {
            addEntityToBiomeSpawns(event, BLEntityTypes.FIREFLY.get(), 100, 15, 30);
        }
    }

    /**
     * Add the {@link net.minecraft.world.entity.Entity Entity} to the list of {@link Biome Biome} {@link MobSpawnSettings Mob Spawns}
     *
     * @param event {@link BiomeLoadingEvent Biome Loading Event}
     * @param type {@link EntityType Entity Type}
     * @param weight {@link Integer Entity spawn weight}
     * @param minCount {@link Integer Entity spawn min count}
     * @param maxCount {@link Integer Entity spawn min count}
     */
    private static void addEntityToBiomeSpawns(BiomeLoadingEvent event, EntityType<?> type, int weight, int minCount, int maxCount) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
        base.add(new MobSpawnSettings.SpawnerData(type, weight, minCount, maxCount));
    }
}