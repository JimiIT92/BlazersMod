package org.blazers;

import net.fabricmc.api.ModInitializer;
import org.blazers.core.*;
import org.blazers.world.gen.BLEntitiesGeneration;
import org.blazers.world.gen.BLFlowersGeneration;
import org.blazers.world.gen.BLOreGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public final class BlazersMod implements ModInitializer {

    public static final String MOD_ID = "blazersmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BLTabs.registerItemGroup();
        BLInstruments.register();
        BLFeatures.register();
        BLItems.register();
        BLBlocks.register();
        BLPaintings.register();
        BLScreenHandlers.register();
        BLRecipes.register();
        BLLootModifiers.register();
        BLBlocks.registerWaxedBlocks();
        BLBlocks.registerOxidizableBlocks();
        BLBlocks.registerStrippables();
        BLBlocks.registerFlammableBlocks();
        BLBlocks.registerDispenserBehaviors();
        BLEntityTypes.registerAttributes();
        BLOreGeneration.generateOres();
        BLFlowersGeneration.generateFlowers();
        BLEntitiesGeneration.generateEntities();

        GeckoLib.initialize();
    }
}