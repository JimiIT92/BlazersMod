package org.blazers;

import net.fabricmc.api.ModInitializer;
import org.blazers.core.BLItems;
import org.blazers.core.BLSounds;
import org.blazers.core.BLTabs;
import org.hendrix.HendrixCore;

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
        HendrixCore.init(MOD_ID);

        BLSounds.register();
        BLTabs.register();
        BLItems.register();

    }

}