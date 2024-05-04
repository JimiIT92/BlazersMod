package org.blazers;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLDispenseBehaviors;
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

        eventBus.addListener(this::onCommonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the {@link BlazersMod Blazers Mod} common stuffs, like flower pots, entity spawns and {@link DispenseItemBehavior Dispense Behaviors}
     *
     * @param event {@link FMLCommonSetupEvent The FML Common Setup Event}
     */
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(BLDispenseBehaviors::registerDispenseBehaviors);
    }

}