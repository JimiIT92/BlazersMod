package org.blazers;

import net.fabricmc.api.ModInitializer;
import org.blazers.core.*;
import org.blazers.world.gen.BLEntitiesGeneration;
import org.blazers.world.gen.BLFlowersGeneration;
import org.blazers.world.gen.BLOreGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BlazersMod implements ModInitializer {

    public static final String MOD_ID = "blazersmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BLFeatures.register();
        BLConfiguredFeatures.register();
        BLItems.register();
        BLBlocks.register();
        BLPaintings.register();
        BLScreenHandlers.register();
        BLRecipes.register();
        BLLootModifiers.register();
        BLInstruments.register();
        BLBlocks.registerWaxedBlocks();
        BLBlocks.registerOxidizableBlocks();
        BLBlocks.registerStrippables();
        BLBlocks.registerFlammableBlocks();
        BLBlocks.registerDispenserBehaviors();
        BLEntityTypes.registerAttributes();
        BLFlowersGeneration.generateFlowers();
        BLOreGeneration.generateOres();
        BLEntitiesGeneration.generateEntities();
    }
}