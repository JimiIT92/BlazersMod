package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.RegistryHelper;
import org.blazers.helper.TextHelper;

import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class BLTabs {

    //#region Registry

    /**
     * The {@link DeferredRegister<CreativeModeTab> Creative Mode Tabs Registry}
     */
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = RegistryHelper.registry(Registries.CREATIVE_MODE_TAB);

    //#endregion

    //#region Creative Tabs

    public static RegistryObject<CreativeModeTab> BUILDING_BLOCKS = registerCreativeTab("building_blocks", CreativeModeTabs.SPAWN_EGGS, Suppliers.memoize(() -> BlockHelper.itemStack(BLBlocks.RUBY_BLOCK)));
    public static RegistryObject<CreativeModeTab> COLORED_BLOCKS = registerCreativeTab("colored_blocks", BUILDING_BLOCKS, Suppliers.memoize(() -> BlockHelper.itemStack(BLBlocks.YELLOW_CONCRETE_STAIRS)));
    public static RegistryObject<CreativeModeTab> NATURAL = registerCreativeTab("natural", COLORED_BLOCKS, Suppliers.memoize(() -> BlockHelper.itemStack(BLBlocks.HOLLOW_BIRCH_LOG)));
    public static RegistryObject<CreativeModeTab> FUNCTIONAL = registerCreativeTab("functional", NATURAL, Suppliers.memoize(Items.PAINTING::getDefaultInstance));
    public static RegistryObject<CreativeModeTab> REDSTONE = registerCreativeTab("redstone", FUNCTIONAL, Suppliers.memoize(() -> BlockHelper.itemStack(BLBlocks.COPPER_BUTTON)));
    public static RegistryObject<CreativeModeTab> TOOLS = registerCreativeTab("tools", REDSTONE, Suppliers.memoize(() -> BLItems.EMERALD_PICKAXE.get().getDefaultInstance()));
    public static RegistryObject<CreativeModeTab> COMBAT = registerCreativeTab("combat", TOOLS, Suppliers.memoize(() -> BLItems.KATANA.get().getDefaultInstance()));
    public static RegistryObject<CreativeModeTab> FOOD_AND_DRINK = registerCreativeTab("food_and_drink", COMBAT, Suppliers.memoize(() -> BLItems.SASHIMI.get().getDefaultInstance()));
    public static RegistryObject<CreativeModeTab> INGREDIENTS = registerCreativeTab("ingredients", FOOD_AND_DRINK, Suppliers.memoize(() -> BLItems.RUBY.get().getDefaultInstance()));
    public static RegistryObject<CreativeModeTab> SPAWN_EGGS = registerCreativeTab("spawn_eggs", INGREDIENTS, Suppliers.memoize(Items.DIAMOND::getDefaultInstance));

    //#endregion

    //#region Methods

    /**
     * Register a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab name}
     * @param beforeTab {@link RegistryObject <CreativeModeTab> Before which Tab this Creative Mode Tab should appear}
     * @param iconSupplier {@link Supplier The Creative Mode  Tab  Icon supplier}
     * @return {@link RegistryObject<CreativeModeTab> The registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerCreativeTab(final String name, final RegistryObject<CreativeModeTab> beforeTab, final Supplier<ItemStack> iconSupplier) {
        return registerCreativeTab(name, beforeTab.getKey(), iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab name}
     * @param beforeTab {@link ResourceKey <CreativeModeTab> Before which Tab this Creative Mode Tab should appear}
     * @param iconSupplier {@link Supplier The Creative Mode  Tab  Icon supplier}
     * @return {@link RegistryObject<CreativeModeTab> The registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerCreativeTab(final String name, final ResourceKey<CreativeModeTab> beforeTab, final Supplier<ItemStack> iconSupplier) {
        return CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .title(TextHelper.tab(name))
                .icon(iconSupplier)
                .withTabsBefore(beforeTab).build()
        );
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }

    //#endregion

}