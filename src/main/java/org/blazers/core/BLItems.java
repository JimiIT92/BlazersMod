package org.blazers.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.hendrix.forge.helper.ItemHelper;
import org.hendrix.forge.registries.HCRegistries;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Items

    public static final RegistryObject<Item> RUBY = ItemHelper.registerSimpleItem("ruby");

    //#endregion

    /**
     * Register all {@link Item Items}
     *
     * @param eventBus The {@link IEventBus Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        HCRegistries.ITEMS.register(eventBus);
    }

}