package org.blazers.core;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.inventory.FletchingMenu;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link MenuType Menu Types}
 */
public final class BLMenuTypes {

    /**
     * {@link MenuType Menu Types} {@link DeferredRegister<MenuType> Registry}
     */
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, BlazersMod.MOD_ID);

    //#region Menu Types

    public static final RegistryObject<MenuType<FletchingMenu>> FLETCHING = register(FletchingMenu::new, "fletching");

    //#endregion

    /**
     * Register a {@link MenuType<T> Menu Type}
     *
     * @param factory {@link IContainerFactory<T> Container Factory}
     * @param name {@link String Menu Name}
     * @return {@link RegistryObject<MenuType> Registered Menu Type}
     * @param <T> Menu class
     */
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> register(IContainerFactory<T> factory, String name) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link MenuType Menu Types}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}