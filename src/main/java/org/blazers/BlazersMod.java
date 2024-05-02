package org.blazers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLItems;
import org.blazers.core.BLTabs;

/**
 * {@link BlazersMod Blazers Mod} main class
 */
@Mod(BlazersMod.MOD_ID)
public final class BlazersMod {

    /**
     * {@link String Mod ID}
     */
    public static final String MOD_ID = "blazersmod";

    /**
     * Initialize the {@link BlazersMod Blazers Mod}
     */
    public BlazersMod() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLTabs.register(eventBus);
        BLItems.register(eventBus);
        BLBlocks.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}