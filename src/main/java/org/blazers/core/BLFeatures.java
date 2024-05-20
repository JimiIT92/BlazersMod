package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;
import org.blazers.world.feature.FallenTreeFeature;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Feature Features}
 */
public final class BLFeatures {

    //#region Registry

    /**
     * The {@link DeferredRegister<Feature> Feature Registry}
     */
    private static final DeferredRegister<Feature<?>> FEATURES = RegistryHelper.registry(ForgeRegistries.FEATURES);

    //#endregion

    //#region Features

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> FALLEN_BIRCH_TREE = registerFeature("fallen_birch_tree", Suppliers.memoize(() -> new FallenTreeFeature(Blocks.BIRCH_LOG::defaultBlockState)));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> FALLEN_HOLLOW_BIRCH_TREE = registerFeature("fallen_hollow_birch_tree", Suppliers.memoize(() -> new FallenTreeFeature(Suppliers.memoize(() -> BLBlocks.HOLLOW_BIRCH_LOG.get().defaultBlockState()))));

    //#endregion

    //#region Methods

    /**
     * Register a {@link Feature Feature}
     *
     * @param name {@link String The Feature name}
     * @param featureSupplier {@link Supplier<Feature> The Supplier for the Feature to register}
     * @return {@link RegistryObject<Feature> The registered Feature}
     */
    private static <FC extends FeatureConfiguration> RegistryObject<Feature<FC>> registerFeature(final String name, final Supplier<Feature<FC>> featureSupplier) {
        return FEATURES.register(name, featureSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link Feature Features}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

    //#endregion

}