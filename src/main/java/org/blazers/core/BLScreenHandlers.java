package org.blazers.core;

import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import org.blazers.inventory.FletchingScreenHandler;

public final class BLScreenHandlers {

    public static ScreenHandlerType<FletchingScreenHandler> FLETCHING;

    public static void register() {
        FLETCHING =  new ScreenHandlerType<>(FletchingScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    }
}
