package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.PropertyHelper;
import org.blazers.helper.RegistryHelper;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Items}
 */
public final class BLItems {

    //#region Registry

    /**
     * The {@link DeferredRegister<Item> Item Registry}
     */
    private static final DeferredRegister<Item> ITEMS = RegistryHelper.registry(ForgeRegistries.ITEMS);

    //#endregion

    //#region Items

    //#endregion

    //#region Methods

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The item name}
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Block Item}
     */
    static RegistryObject<Item> registerBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerItem(name, Suppliers.memoize(() -> new BlockItem(blockSupplier.get(), PropertyHelper.item(featureFlags))));
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name {@link String The item name}
     * @param itemSupplier {@link Supplier<Item> The Item supplier}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    static RegistryObject<Item> registerItem(final String name, final Supplier<? extends Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link Item Items}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //#endregion

}