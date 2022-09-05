package org.blazers;

import net.fabricmc.api.ModInitializer;
import org.blazers.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BlazersMod implements ModInitializer {

    public static final String MOD_ID = "blazersmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BLItems.register();
        BLBlocks.register();
        BLPaintings.register();
        //features
        //conf features
        //placed features
        //menu types
        //serializers
        //loot modifiers
        BLInstruments.register();
        BLBlocks.registerWaxedBlocks();
        BLBlocks.registerOxidizableBlocks();
        BLBlocks.registerStrippables();
        BLBlocks.registerFlammableBlocks();
        BLEntityTypes.registerAttributes();
    }
}