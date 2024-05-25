package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.client.screen.FletchingScreen;
import org.blazers.helper.RegistryHelper;
import org.blazers.inventory.FletchingMenu;
import org.blazers.recipe.FletchingRecipe;

/**
 * {@link BlazersMod Blazers Mod} {@link MenuType Menu Types}
 */
public final class BLMenuTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<MenuType> Menu Type Registry}
     */
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = RegistryHelper.registry(ForgeRegistries.MENU_TYPES);

    //#endregion

    //#region Menu Types

    public static final RegistryObject<MenuType<FletchingMenu>> FLETCHING = registerMenuType(FletchingRecipe.ID, FletchingMenu::new);

    //#endregion

    //#region Methods

    /**
     * Register a {@link MenuType Menu Type}
     *
     * @param name {@link String The Menu Type name}
     * @param menuTypeSupplier {@link IContainerFactory<MenuType> The Supplier for the Menu Type}
     * @return {@link RegistryObject<MenuType> The registered Menu Type}
     * @param <T> The Menu Type
     */
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(final String name, final IContainerFactory<T> menuTypeSupplier) {
        return MENU_TYPES.register(name, Suppliers.memoize(() -> IForgeMenuType.create(menuTypeSupplier)));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link Screen Menu Screens}
     */
    public static void registerMenuScreens() {
        MenuScreens.register(FLETCHING.get(), FletchingScreen::new);
    }

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link MenuType Menu Types}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

    //#endregion

}