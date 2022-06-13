package org.blazers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blazers.core.BLItems;

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
     * {@link Logger Logger Instance}
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initialize the {@link BlazersMod Blazers Mod}
     */
    public BlazersMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLItems.register(eventBus);

        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Setup the {@link BlazersMod Blazers Mod}
     *
     * @param event {@link FMLCommonSetupEvent Common Setup Event}
     */
    private void setup(final FMLCommonSetupEvent event) {

    }
}