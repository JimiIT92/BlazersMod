package org.blazers;

import net.fabricmc.api.ModInitializer;
import org.blazers.core.*;
import org.hendrix.common.HendrixCore;

import javax.naming.OperationNotSupportedException;

/**
 * Blazers Mod. A Minecraft mod made for ErenBlaze
 */
public final class BlazersMod implements ModInitializer {

    /**
     * The {@link String Mod Id}
     */
    public static final String MOD_ID = "blazersmod";

    /**
     * Initialize the mod
     */
    @Override
    public void onInitialize() {
        try {
            HendrixCore.init(MOD_ID);
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }

        BLSounds.register();
        BLTabs.register();

        BLBlocks.register();
        BLItems.register();

        BLEvents.register();
    }

}