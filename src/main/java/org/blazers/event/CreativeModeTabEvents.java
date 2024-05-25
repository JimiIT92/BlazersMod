package org.blazers.event;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLItems;
import org.blazers.core.BLTabs;
import org.blazers.core.BLTags;
import org.blazers.item.CopperHornItem;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handle all events for {@link CreativeModeTab Creative Mode Tabs}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CreativeModeTabEvents {

    /**
     * Set the {@link BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tab} contents
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    @SubscribeEvent
    public static void onTabContentsEvent(final BuildCreativeModeTabContentsEvent event) {
        final ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if(isTab(tabKey, CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            removeEblPaintings(event);
        }
        if(isTab(tabKey, BLTabs.BUILDING_BLOCKS)) {
            setBuildingBlocksTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.COLORED_BLOCKS)) {
            setColoredBlocksTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.NATURAL_BLOCKS)) {
            setNaturalTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.FUNCTIONAL_BLOCKS)) {
            setFunctionalTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.REDSTONE_BLOCKS)) {
            setRedstoneTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.TOOLS_AND_UTILITIES)) {
            setToolsTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.COMBAT)) {
            setCombatTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.FOOD_AND_DRINKS)) {
            setFoodAndDrinkTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.INGREDIENTS)) {
            setIngredientsTabContent(event);
        }
        else if(isTab(tabKey, BLTabs.SPAWN_EGGS)) {
            setSpawnEggsTabContent(event);
        }
    }

    /**
     * Remove the {@link BlazersMod Blazers Mod} {@link PaintingVariant Painting Variants}
     * from the {@link CreativeModeTabs#FUNCTIONAL_BLOCKS Vanilla Functional Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void removeEblPaintings(final BuildCreativeModeTabContentsEvent event) {
        final Set<ItemStack> paintingsToRemove = BuiltInRegistries.PAINTING_VARIANT.asLookup()
                .listElements().filter(painting -> painting.is(PaintingVariantTags.PLACEABLE) && painting.is(BLTags.Paintings.EBL_PAINTINGS))
                .map(
                        painting -> {
                            final ItemStack itemStack = new ItemStack(Items.PAINTING);
                            itemStack.set(DataComponents.ENTITY_DATA, CustomData.EMPTY.update(Painting.VARIANT_MAP_CODEC, painting).getOrThrow().update(nbt -> nbt.putString("id", "minecraft:painting")));
                            return itemStack;
                        }
                )
                .collect(Collectors.toSet());
        paintingsToRemove.forEach(painting -> event.getEntries().remove(painting));
    }

    /**
     * Set the content of the {@link BLTabs#BUILDING_BLOCKS Building Blocks Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setBuildingBlocksTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLBlocks.HOLLOW_OAK_LOG,
            BLBlocks.STRIPPED_HOLLOW_OAK_LOG,
            BLBlocks.HOLLOW_SPRUCE_LOG,
            BLBlocks.STRIPPED_HOLLOW_SPRUCE_LOG,
            BLBlocks.HOLLOW_BIRCH_LOG,
            BLBlocks.STRIPPED_HOLLOW_BIRCH_LOG,
            BLBlocks.HOLLOW_JUNGLE_LOG,
            BLBlocks.STRIPPED_HOLLOW_JUNGLE_LOG,
            BLBlocks.HOLLOW_ACACIA_LOG,
            BLBlocks.STRIPPED_HOLLOW_ACACIA_LOG,
            BLBlocks.HOLLOW_DARK_OAK_LOG,
            BLBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG,
            BLBlocks.HOLLOW_MANGROVE_LOG,
            BLBlocks.STRIPPED_HOLLOW_MANGROVE_LOG,
            BLBlocks.HOLLOW_CHERRY_LOG,
            BLBlocks.STRIPPED_HOLLOW_CHERRY_LOG,
            BLBlocks.HOLLOW_BAMBOO_BLOCK,
            BLBlocks.STRIPPED_HOLLOW_BAMBOO_BLOCK,
            BLBlocks.HOLLOW_CRIMSON_STEM,
            BLBlocks.STRIPPED_HOLLOW_CRIMSON_STEM,
            BLBlocks.HOLLOW_WARPED_STEM,
            BLBlocks.STRIPPED_HOLLOW_WARPED_STEM,
            BLBlocks.STONE_TILES,
            BLBlocks.MOSSY_STONE_TILES,
            BLBlocks.COBBLED_GRANITE,
            BLBlocks.COBBLED_DIORITE,
            BLBlocks.COBBLED_ANDESITE,
            BLBlocks.CUT_DEEPSLATE_BRICKS,
            BLBlocks.POLISHED_DEEPSLATE_BRICKS,
            BLBlocks.CUT_BRICKS,
            BLBlocks.SANDSTONE_BRICKS,
            BLBlocks.RED_SANDSTONE_BRICKS,
            BLBlocks.CUT_PRISMARINE_BRICKS,
            BLBlocks.DARK_PRISMARINE_BRICKS,
            BLBlocks.POLISHED_BLACKSTONE_TILES,
            BLBlocks.END_STONE_TILES,
            BLBlocks.PURPUR_TILES,
            BLBlocks.QUARTZ_TILES,
            BLBlocks.SAPPHIRE_BLOCK,
            BLBlocks.TOPAZ_BLOCK,
            BLBlocks.PEARL_BLOCK,
            BLBlocks.RUBY_BLOCK,
            BLBlocks.MALACHITE_BLOCK,
            BLBlocks.ONICE_BLOCK,
            BLBlocks.RAW_URANIUM_BLOCK,
            BLBlocks.URANIUM_BLOCK,
            BLBlocks.CUT_COPPER_BRICKS,
            BLBlocks.COPPER_BUTTON,
            BLBlocks.EXPOSED_CUT_COPPER_BRICKS,
            BLBlocks.EXPOSED_COPPER_BUTTON,
            BLBlocks.WEATHERED_CUT_COPPER_BRICKS,
            BLBlocks.WEATHERED_COPPER_BUTTON,
            BLBlocks.OXIDIZED_CUT_COPPER_BRICKS,
            BLBlocks.OXIDIZED_COPPER_BUTTON,
            BLBlocks.WAXED_CUT_COPPER_BRICKS,
            BLBlocks.WAXED_COPPER_BUTTON,
            BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS,
            BLBlocks.WAXED_EXPOSED_COPPER_BUTTON,
            BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS,
            BLBlocks.WAXED_WEATHERED_COPPER_BUTTON,
            BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS,
            BLBlocks.WAXED_OXIDIZED_COPPER_BUTTON,
            BLBlocks.POINTED_STONE_DRIPSTONE,
            BLBlocks.POINTED_GRANITE_DRIPSTONE,
            BLBlocks.POINTED_DIORITE_DRIPSTONE,
            BLBlocks.POINTED_ANDESITE_DRIPSTONE,
            BLBlocks.POINTED_ICE_DRIPSTONE
        );
    }

    /**
     * Set the content of the {@link BLTabs#COLORED_BLOCKS Colored Blocks Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setColoredBlocksTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLBlocks.WHITE_CONCRETE_SLAB,
            BLBlocks.ORANGE_CONCRETE_SLAB,
            BLBlocks.MAGENTA_CONCRETE_SLAB,
            BLBlocks.LIGHT_BLUE_CONCRETE_SLAB,
            BLBlocks.YELLOW_CONCRETE_SLAB,
            BLBlocks.LIME_CONCRETE_SLAB,
            BLBlocks.PINK_CONCRETE_SLAB,
            BLBlocks.GRAY_CONCRETE_SLAB,
            BLBlocks.LIGHT_GRAY_CONCRETE_SLAB,
            BLBlocks.CYAN_CONCRETE_SLAB,
            BLBlocks.PURPLE_CONCRETE_SLAB,
            BLBlocks.BLUE_CONCRETE_SLAB,
            BLBlocks.BROWN_CONCRETE_SLAB,
            BLBlocks.GREEN_CONCRETE_SLAB,
            BLBlocks.RED_CONCRETE_SLAB,
            BLBlocks.BLACK_CONCRETE_SLAB,
            BLBlocks.WHITE_CONCRETE_STAIRS,
            BLBlocks.ORANGE_CONCRETE_STAIRS,
            BLBlocks.MAGENTA_CONCRETE_STAIRS,
            BLBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
            BLBlocks.YELLOW_CONCRETE_STAIRS,
            BLBlocks.LIME_CONCRETE_STAIRS,
            BLBlocks.PINK_CONCRETE_STAIRS,
            BLBlocks.GRAY_CONCRETE_STAIRS,
            BLBlocks.LIGHT_GRAY_CONCRETE_STAIRS,
            BLBlocks.CYAN_CONCRETE_STAIRS,
            BLBlocks.PURPLE_CONCRETE_STAIRS,
            BLBlocks.BLUE_CONCRETE_STAIRS,
            BLBlocks.BROWN_CONCRETE_STAIRS,
            BLBlocks.GREEN_CONCRETE_STAIRS,
            BLBlocks.RED_CONCRETE_STAIRS,
            BLBlocks.BLACK_CONCRETE_STAIRS
        );
    }

    /**
     * Set the content of the {@link BLTabs#NATURAL_BLOCKS Natural Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setNaturalTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLBlocks.SAPPHIRE_ORE,
            BLBlocks.DEEPSLATE_SAPPHIRE_ORE,
            BLBlocks.TOPAZ_ORE,
            BLBlocks.DEEPSLATE_TOPAZ_ORE,
            BLBlocks.PEARL_ORE,
            BLBlocks.RUBY_ORE,
            BLBlocks.MALACHITE_ORE,
            BLBlocks.ONICE_ORE,
            BLBlocks.URANIUM_ORE,
            BLBlocks.HOLLOW_BIRCH_LOG,
            BLBlocks.CATTAIL
        );
    }

    /**
     * Set the content of the {@link BLTabs#FUNCTIONAL_BLOCKS Functional Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setFunctionalTabContent(final BuildCreativeModeTabContentsEvent event) {
        BuiltInRegistries.PAINTING_VARIANT.asLookup()
                .listElements().filter(painting -> painting.is(PaintingVariantTags.PLACEABLE) && painting.is(BLTags.Paintings.EBL_PAINTINGS))
                .sorted(Comparator.comparing(Holder::value, Comparator.<PaintingVariant>comparingInt(painting -> painting.getHeight() * painting.getWidth()).thenComparing(PaintingVariant::getWidth)))
                .forEach(
                        painting -> {
                            final ItemStack itemStack = new ItemStack(Items.PAINTING);
                            itemStack.set(DataComponents.ENTITY_DATA, CustomData.EMPTY.update(Painting.VARIANT_MAP_CODEC, painting).getOrThrow().update(nbt -> nbt.putString("id", "minecraft:painting")));
                            event.accept(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        }
                );
    }

    /**
     * Set the content of the {@link BLTabs#REDSTONE_BLOCKS Redstone Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setRedstoneTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLBlocks.COPPER_BUTTON,
            BLBlocks.EXPOSED_COPPER_BUTTON,
            BLBlocks.WEATHERED_COPPER_BUTTON,
            BLBlocks.OXIDIZED_COPPER_BUTTON,
            BLBlocks.WAXED_COPPER_BUTTON,
            BLBlocks.WAXED_EXPOSED_COPPER_BUTTON,
            BLBlocks.WAXED_WEATHERED_COPPER_BUTTON,
            BLBlocks.WAXED_OXIDIZED_COPPER_BUTTON,
            BLBlocks.ATOMIC_TNT
        );
    }

    /**
     * Set the content of the {@link BLTabs#TOOLS_AND_UTILITIES Tools Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setToolsTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLItems.EMERALD_SHOVEL,
            BLItems.EMERALD_PICKAXE,
            BLItems.EMERALD_AXE,
            BLItems.EMERALD_HOE,
            BLItems.SAPPHIRE_SHOVEL,
            BLItems.SAPPHIRE_PICKAXE,
            BLItems.SAPPHIRE_AXE,
            BLItems.SAPPHIRE_HOE,
            BLItems.RUBY_SHOVEL,
            BLItems.RUBY_PICKAXE,
            BLItems.RUBY_AXE,
            BLItems.RUBY_HOE,
            BLItems.ONICE_SHOVEL,
            BLItems.ONICE_PICKAXE,
            BLItems.ONICE_AXE,
            BLItems.ONICE_HOE
        );
        BuiltInRegistries.INSTRUMENT.asLookup().get(BLTags.Instruments.MELODY_COPPER_HORNS).ifPresent(instruments -> instruments.forEach(instrumentHolder -> event.accept(CopperHornItem.create(BLItems.COPPER_HORN.get(), instrumentHolder), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)));
        addToTab(event,
            BLItems.MUSIC_DISC_SURVIVAL,
            BLItems.MUSIC_DISC_ENDERMAN_VS_BLAZE
        );
    }

    /**
     * Set the content of the {@link BLTabs#COMBAT Combat Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setCombatTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLItems.EMERALD_SWORD,
            BLItems.AMETHYST_SWORD,
            BLItems.SAPPHIRE_SWORD,
            BLItems.PEARL_SWORD,
            BLItems.RUBY_SWORD,
            BLItems.BLAZERITE_SWORD,
            BLItems.GYULIANITE_SWORD,
            BLItems.EMERALD_AXE,
            BLItems.SAPPHIRE_AXE,
            BLItems.RUBY_AXE,
            BLItems.ONICE_AXE,
            BLItems.TOPAZ_HAMMER,
            BLItems.ONICE_SICKLE,
            BLItems.EMERALD_HELMET,
            BLItems.EMERALD_CHESTPLATE,
            BLItems.EMERALD_LEGGINGS,
            BLItems.EMERALD_BOOTS,
            BLItems.AMETHYST_HELMET,
            BLItems.AMETHYST_CHESTPLATE,
            BLItems.AMETHYST_LEGGINGS,
            BLItems.AMETHYST_BOOTS,
            BLItems.SAPPHIRE_HELMET,
            BLItems.SAPPHIRE_CHESTPLATE,
            BLItems.SAPPHIRE_LEGGINGS,
            BLItems.SAPPHIRE_BOOTS,
            BLItems.TOPAZ_HELMET,
            BLItems.TOPAZ_CHESTPLATE,
            BLItems.TOPAZ_LEGGINGS,
            BLItems.TOPAZ_BOOTS,
            BLItems.PEARL_HELMET,
            BLItems.PEARL_CHESTPLATE,
            BLItems.PEARL_LEGGINGS,
            BLItems.PEARL_BOOTS,
            BLItems.RUBY_HELMET,
            BLItems.RUBY_CHESTPLATE,
            BLItems.RUBY_LEGGINGS,
            BLItems.RUBY_BOOTS,
            BLItems.ONICE_HELMET,
            BLItems.ONICE_CHESTPLATE,
            BLItems.ONICE_LEGGINGS,
            BLItems.ONICE_BOOTS,
            BLItems.MALACHITE_HELMET,
            BLItems.MALACHITE_CHESTPLATE,
            BLItems.MALACHITE_LEGGINGS,
            BLItems.MALACHITE_BOOTS,
            BLItems.BLAZERITE_HELMET,
            BLItems.BLAZERITE_CHESTPLATE,
            BLItems.BLAZERITE_LEGGINGS,
            BLItems.BLAZERITE_BOOTS,
            BLItems.GYULIANITE_HELMET,
            BLItems.GYULIANITE_CHESTPLATE,
            BLItems.GYULIANITE_LEGGINGS,
            BLItems.GYULIANITE_BOOTS,
            BLItems.EMERALD_HORSE_ARMOR,
            BLItems.SAPPHIRE_HORSE_ARMOR,
            BLItems.TOPAZ_HORSE_ARMOR,
            BLItems.RUBY_HORSE_ARMOR,
            BLItems.MALACHITE_HORSE_ARMOR,
            BLBlocks.ATOMIC_TNT,
            BLItems.CARBON_BOW,
            BLItems.SPEAR,
            BLItems.MALACHITE_SPEAR,
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
    }

    /**
     * Set the content of the {@link BLTabs#FOOD_AND_DRINKS Food And Drink Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setFoodAndDrinkTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLItems.HOSOMAKI,
            BLItems.NIGIRI,
            BLItems.SASHIMI
        );
    }

    /**
     * Set the content of the {@link BLTabs#INGREDIENTS Ingredients Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setIngredientsTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLItems.CARBON,
            BLItems.RAW_URANIUM,
            BLItems.SAPPHIRE,
            BLItems.TOPAZ,
            BLItems.PEARL,
            BLItems.RUBY,
            BLItems.MALACHITE,
            BLItems.ONICE,
            BLItems.URANIUM_NUGGET,
            BLItems.URANIUM_INGOT,
            BLItems.BLAZERITE,
            BLItems.GYULIANITE
        );
    }

    /**
     * Set the content of the {@link BLTabs#SPAWN_EGGS Spawn Eggs Creative Mode Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setSpawnEggsTabContent(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
            BLItems.WITHER_SKELETON_HORSE_SPAWN_EGG
        );
    }

    /**
     * Check if the provided {@link ResourceKey<CreativeModeTab> Creative Mode Tab Key} corresponds to a
     * {@link BlazersMod Blazers Mod} {@link RegistryObject<CreativeModeTab> Registered Creative Mode Tab}
     *
     * @param tabKey {@link ResourceKey<CreativeModeTab> The Creative Mode Tab Key}
     * @param target {@link RegistryObject<CreativeModeTab> The target Creative Mode Tab}
     * @return {@link Boolean True if the key corresponds to the target tab}
     */
    private static boolean isTab(final ResourceKey<CreativeModeTab> tabKey, final RegistryObject<CreativeModeTab> target) {
        return isTab(tabKey, target.getKey());
    }

    /**
     * Check if the provided {@link ResourceKey<CreativeModeTab> Creative Mode Tab Key} corresponds to a
     * {@link BlazersMod Blazers Mod} {@link RegistryObject<CreativeModeTab> Registered Creative Mode Tab}
     *
     * @param tabKey {@link ResourceKey<CreativeModeTab> The Creative Mode Tab Key}
     * @param target {@link ResourceKey<CreativeModeTab> The target Creative Mode Tab}
     * @return {@link Boolean True if the key corresponds to the target tab}
     */
    private static boolean isTab(final ResourceKey<CreativeModeTab> tabKey, final ResourceKey<CreativeModeTab> target) {
        return tabKey.equals(target);
    }

    /**
     * Add some {@link T items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link T The items to add}
     */
    @SafeVarargs
    private static <T extends ItemLike> void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final T... items) {
        addToTab(event, Arrays.stream(items).map(item -> item.asItem().getDefaultInstance()).toList());
    }

    /**
     * Add some {@link RegistryObject items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link RegistryObject The items to add}
     */
    @SafeVarargs
    private static void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final RegistryObject<? extends ItemLike>... items) {
        addToTab(event, Arrays.stream(items).map(item -> item.get().asItem().getDefaultInstance()).toList());
    }

    /**
     * Add some {@link ItemStack items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link List <ItemStack> The items to add}
     */
    private static void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final List<ItemStack> items) {
        event.acceptAll(items);
    }

}