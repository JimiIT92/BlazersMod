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

    public static RegistryObject<CreativeModeTab> TOOLS = CreativeModeTabHelper.tab("tools", BLItems.EMERALD_PICKAXE);
    public static RegistryObject<CreativeModeTab> COMBAT = CreativeModeTabHelper.tab("combat", BLItems.KATANA, TOOLS.getKey());
    public static RegistryObject<CreativeModeTab> FOOD_AND_DRINK = CreativeModeTabHelper.tab("food_and_drink", BLItems.SASHIMI, COMBAT.getKey());
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
        if(CreativeModeTabHelper.isTab(event, TOOLS)) {
            CreativeModeTabHelper.addItems(event,
                    BLItems.SAPPHIRE_SHOVEL,
                    BLItems.SAPPHIRE_PICKAXE,
                    BLItems.SAPPHIRE_AXE,
                    BLItems.SAPPHIRE_HOE,
                    BLItems.RUBY_SHOVEL,
                    BLItems.RUBY_PICKAXE,
                    BLItems.RUBY_AXE,
                    BLItems.RUBY_HOE,
                    BLItems.EMERALD_SHOVEL,
                    BLItems.EMERALD_PICKAXE,
                    BLItems.EMERALD_AXE,
                    BLItems.EMERALD_HOE,
                    BLItems.ONICE_SHOVEL,
                    BLItems.ONICE_PICKAXE,
                    BLItems.ONICE_AXE,
                    BLItems.ONICE_HOE
            );
            return;
        }
        if(CreativeModeTabHelper.isTab(event, COMBAT)) {
            CreativeModeTabHelper.addItems(event,
                    BLItems.SAPPHIRE_SWORD,
                    BLItems.RUBY_SWORD,
                    BLItems.EMERALD_SWORD,
                    BLItems.AMETHYST_SWORD,
                    BLItems.PEARL_SWORD,
                    BLItems.TOPAZ_HAMMER,
                    BLItems.ONICE_SICKLE,
                    BLItems.KATANA,
                    BLItems.WHITE_KATANA,
                    BLItems.ORANGE_KATANA,
                    BLItems.MAGENTA_KATANA,
                    BLItems.LIGHT_BLUE_KATANA,
                    BLItems.YELLOW_KATANA,
                    BLItems.LIME_KATANA,
                    BLItems.PINK_KATANA,
                    BLItems.GRAY_KATANA,
                    BLItems.LIGHT_GRAY_KATANA,
                    BLItems.CYAN_KATANA,
                    BLItems.PURPLE_KATANA,
                    BLItems.BLUE_KATANA,
                    BLItems.BROWN_KATANA,
                    BLItems.GREEN_KATANA,
                    BLItems.RED_KATANA,
                    BLItems.BLACK_KATANA
            );
            return;
        }
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