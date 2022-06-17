package org.blazers;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;
import org.blazers.core.BLPaintings;
import org.blazers.entity.WitherSkeletonHorse;

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
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initialize the {@link BlazersMod Blazers Mod}
     */
    public BlazersMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLItems.register(eventBus);
        BLBlocks.register(eventBus);
        BLPaintings.register(eventBus);
        BLEntityTypes.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::entityAttributeSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Setup the {@link BlazersMod Blazers Mod} Client stuffs
     *
     * @param event {@link FMLClientSetupEvent FML Client Setup Event}
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        BLItems.registerItemProperties();
        BLBlocks.registerTransparentBlocks();
        BLEntityTypes.registerRenderers();
    }

    /**
     * Setup the {@link BlazersMod Blazers Mod} Common stuffs
     *
     * @param event {@link FMLCommonSetupEvent FML Common Setup Event}
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> SpawnPlacements.register(BLEntityTypes.WITHER_SKELETON_HORSE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WitherSkeletonHorse::checkWitherSkeletonHorseSpawnRules));
    }

    /**
     * Setup the {@link net.minecraft.world.entity.ai.attributes.AttributeSupplier Entity Attributes}
     *
     * @param event {@link EntityAttributeCreationEvent Entity Attribute Creation Event}
     */
    private void entityAttributeSetup(final EntityAttributeCreationEvent event) {
        event.put(BLEntityTypes.WITHER_SKELETON_HORSE.get(), WitherSkeletonHorse.createAttributes().build());
    }
}