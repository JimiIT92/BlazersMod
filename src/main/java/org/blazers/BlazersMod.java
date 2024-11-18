package org.blazers;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.blazers.core.BLItems;
import org.hendrix.forge.HendrixCoreForge;

/**
 * Blazers Mod - A Minecraft mod made for ErenBlaze and his community
 */
@Mod(BlazersMod.MOD_ID)
public final class BlazersMod {

    /**
     * The {@link String Mod Id}
     */
    public static final String MOD_ID = "blazersmod";

    /**
     * Constructor. Initialize the mod
     *
     * @param context The {@link FMLJavaModLoadingContext Mod Loading Context}
     */
    public BlazersMod(final FMLJavaModLoadingContext context) {
        HendrixCoreForge.init(MOD_ID);

        final IEventBus eventBus = context.getModEventBus();

        BLItems.register(eventBus);
    }

}