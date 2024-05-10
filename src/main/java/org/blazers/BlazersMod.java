package org.blazers;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.blazers.client.BLItemRenderer;
import org.blazers.core.*;

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
     * {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Custom Item Renderer}
     */
    private static BlockEntityWithoutLevelRenderer ITEMS_RENDERER;

    /**
     * Initialize the {@link BlazersMod Blazers Mod}
     */
    public BlazersMod() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLTabs.register(eventBus);
        BLSounds.register(eventBus);
        BLArmorMaterials.register(eventBus);
        BLItems.register(eventBus);
        BLBlocks.register(eventBus);

        BLEntityTypes.register(eventBus);

        eventBus.addListener(this::onClientSetup);
        eventBus.addListener(this::onCommonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the {@link BlazersMod Blazers Mod} client stuffs, like entity renderers
     *
     * @param event {@link FMLClientSetupEvent The FML Client Setup Event}
     */
    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(BLItems::registerItemProperties);
        event.enqueueWork(BLEntityTypes::registerRenderers);
    }

    /**
     * Set up the {@link BlazersMod Blazers Mod} common stuffs, like flower pots, entity spawns and {@link DispenseItemBehavior Dispense Behaviors}
     *
     * @param event {@link FMLCommonSetupEvent The FML Common Setup Event}
     */
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(BLDispenseBehaviors::registerDispenseBehaviors);
        event.enqueueWork(BLBlocks::registerFlowerPots);
        event.enqueueWork(BLInstruments::registerInstruments);
    }

    /**
     * Get the {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Items Renderer}
     *
     * @return {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Items Renderer}
     */
    public static BlockEntityWithoutLevelRenderer getItemsRenderer() {
        if(ITEMS_RENDERER == null) {
            ITEMS_RENDERER = new BLItemRenderer();
        }
        return ITEMS_RENDERER;
    }

}