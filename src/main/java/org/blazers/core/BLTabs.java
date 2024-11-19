package org.blazers.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.hendrix.forge.helper.CreativeModeTabHelper;
import org.hendrix.forge.registries.HCRegistries;

/**
 * {@link BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BLTabs {

    //#region Creative Mode Tabs

    public static RegistryObject<CreativeModeTab> FOOD_AND_DRINK = CreativeModeTabHelper.tab("food_and_drink", BLItems.SASHIMI);
    public static RegistryObject<CreativeModeTab> INGREDIENTS = CreativeModeTabHelper.tab("ingredients", BLItems.RUBY, FOOD_AND_DRINK.getKey());

    //#endregion

    /**
     * Add {@link ItemLike Items} to the {@link CreativeModeTab Creative Mode Tabs}
     *
     * @param event The {@link BuildCreativeModeTabContentsEvent Build Creative Mode Tab Contents Event}
     */
    @SubscribeEvent
    public static void addItemsToCreativeModeTabs(final BuildCreativeModeTabContentsEvent event) {
        final CreativeModeTab tab = event.getTab();
        if(CreativeModeTabHelper.isTab(event, INGREDIENTS)) {
            CreativeModeTabHelper.addItems(event,
                    BLItems.RAW_URANIUM,
                    BLItems.RUBY,
                    BLItems.SAPPHIRE,
                    BLItems.TOPAZ,
                    BLItems.PEARL,
                    BLItems.MALACHITE,
                    BLItems.ONICE,
                    BLItems.BLAZERITE,
                    BLItems.GYULIANITE,
                    BLItems.URANIUM_NUGGET,
                    BLItems.URANIUM_INGOT,
                    BLItems.CARBON
            );
            return;
        }
        if(CreativeModeTabHelper.isTab(event, FOOD_AND_DRINK)) {
            CreativeModeTabHelper.addItems(event,
                    BLItems.HOSOMAKI,
                    BLItems.NIGIRI,
                    BLItems.SASHIMI
            );
        }
    }

    /**
     * Register all {@link CreativeModeTab Creative Mode Tabs}
     *
     * @param eventBus The {@link IEventBus Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        HCRegistries.CREATIVE_MODE_TABS.register(eventBus);
    }

}