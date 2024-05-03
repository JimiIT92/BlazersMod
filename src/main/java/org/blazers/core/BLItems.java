package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.BlockHelper;
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

    public static final RegistryObject<Item> SAPPHIRE = registerGem(BLMaterials.SAPPHIRE);
    public static final RegistryObject<Item> TOPAZ = registerGem(BLMaterials.TOPAZ);
    public static final RegistryObject<Item> PEARL = registerGem(BLMaterials.PEARL);
    public static final RegistryObject<Item> RUBY = registerGem(BLMaterials.RUBY);
    public static final RegistryObject<Item> MALACHITE = registerGem(BLMaterials.MALACHITE);
    public static final RegistryObject<Item> ONICE = registerGem(BLMaterials.ONICE);
    public static final RegistryObject<Item> RAW_URANIUM = registerRawGem(BLMaterials.URANIUM);

    //#endregion

    //#region Methods

    /**
     * Register a {@link Item gem}
     *
     * @param material {@link BLMaterials The gem material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered gem}
     */
    private static RegistryObject<Item> registerGem(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerGem(material, Rarity.COMMON, featureFlags);
    }

    /**
     * Register a {@link Item gem}
     *
     * @param material {@link BLMaterials The gem material}
     * @param rarity {@link Rarity The Item rarity}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered gem}
     */
    private static RegistryObject<Item> registerGem(final BLMaterials material, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(BlockHelper.materialName(material), rarity, featureFlags);
    }

    /**
     * Register a {@link Item raw gem}
     *
     * @param material {@link BLMaterials The raw gem material}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered raw gem}
     */
    private static RegistryObject<Item> registerRawGem(final BLMaterials material, final FeatureFlag... featureFlags) {
        return registerItem("raw_" + BlockHelper.materialName(material), featureFlags);
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerItem(final String name, final FeatureFlag... featureFlags) {
        return registerItem(name, Rarity.COMMON, featureFlags);
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param rarity {@link Rarity The Item rarity}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerItem(final String name, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(name, Suppliers.memoize(() -> new Item(PropertyHelper.item(featureFlags).rarity(rarity))));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The Item name}
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
     * @param name {@link String The Item name}
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