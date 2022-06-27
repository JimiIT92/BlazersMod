package org.blazers.core;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.world.feature.FallenTreeFeature;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Feature Features}
 */
public final class BLFeatures {

    /**
     * {@link Feature Features} {@link DeferredRegister<Feature> Registry}
     */
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BlazersMod.MOD_ID);

    //#region Features

    public static final RegistryObject<FallenTreeFeature> FALLEN_BIRCH_TREE = FEATURES.register("fallen_birch_tree", () -> new FallenTreeFeature(Blocks.BIRCH_LOG));
    public static final RegistryObject<FallenTreeFeature> FALLEN_HOLLOW_BIRCH_TREE = FEATURES.register("fallen_hollow_birch_tree", () -> new FallenTreeFeature(BLBlocks.HOLLOW_BIRCH_LOG.get()));

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link Feature Features}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}