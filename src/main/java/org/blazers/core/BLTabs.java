package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.item.CopperHornItem;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BLTabs {

    /**
     * {@link HashMap Map} for the association of {@link ItemStack Items} with {@link CreativeModeTab Creative Tabs}
     */
    public static final HashMap<BLTabKeys, List<RegistryObject<Item>>> ITEM_GROUPS = new HashMap<>();

    //#region Creative Mode Tabs

    public static CreativeModeTab BUILDING_BLOCKS;
    public static CreativeModeTab COLORED_BLOCKS;
    public static CreativeModeTab NATURAL;
    public static CreativeModeTab FUNCTIONAL;
    public static CreativeModeTab REDSTONE;
    public static CreativeModeTab TOOLS;
    public static CreativeModeTab COMBAT;
    public static CreativeModeTab FOOD_AND_DRINK;
    public static CreativeModeTab INGREDIENTS;
    public static CreativeModeTab SPAWN_EGGS;

    //#endregion

    /**
     * Register the {@link CreativeModeTab Creative Tabs}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative Mode Tab Register Event}
     */
    @SubscribeEvent
    public static void register(CreativeModeTabEvent.Register event) {
        BUILDING_BLOCKS = registerTab(event, "building_blocks", () -> new ItemStack(BLBlocks.RUBY_BLOCK.get()));
        COLORED_BLOCKS = registerTab(event, "colored_blocks", BUILDING_BLOCKS,()  -> new ItemStack(BLBlocks.YELLOW_CONCRETE_STAIRS.get()));
        NATURAL = registerTab(event, "natural", COLORED_BLOCKS, () -> new ItemStack(BLBlocks.HOLLOW_BIRCH_LOG.get()));
        FUNCTIONAL = registerTab(event, "functional", NATURAL, () -> new ItemStack(Items.PAINTING));
        REDSTONE = registerTab(event, "redstone", FUNCTIONAL, () -> new ItemStack(BLBlocks.COPPER_BUTTON.get()));
        TOOLS = registerTab(event, "tools", REDSTONE, () -> new ItemStack(BLItems.EMERALD_PICKAXE.get()));
        COMBAT = registerTab(event, "combat", TOOLS, () -> new ItemStack(BLItems.KATANA.get()));
        FOOD_AND_DRINK = registerTab(event, "food_and_drink", COMBAT, () -> new ItemStack(BLItems.SASHIMI.get()));
        INGREDIENTS = registerTab(event, "ingredients", FOOD_AND_DRINK, () -> new ItemStack(BLItems.RUBY.get()));
        SPAWN_EGGS = registerTab(event, "spawn_eggs", INGREDIENTS, () -> new ItemStack(BLItems.COPPER_GOLEM_SPAWN_EGG.get()));
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative Mode Tab Register Event}
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static CreativeModeTab registerTab(CreativeModeTabEvent.Register event, String name, Supplier<ItemStack> iconSupplier) {
        return registerTab(event, name, CreativeModeTabs.SPAWN_EGGS, iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative Mode Tab Register Event}
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static CreativeModeTab registerTab(CreativeModeTabEvent.Register event, String name, CreativeModeTab afterTab, Supplier<ItemStack> iconSupplier) {
        return registerTab(event, name, afterTab, null, iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative Mode Tab Register Event}
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static CreativeModeTab registerTab(CreativeModeTabEvent.Register event, String name, CreativeModeTab afterTab, CreativeModeTab beforeTab, Supplier<ItemStack> iconSupplier) {
       return event.registerCreativeModeTab(new ResourceLocation(BlazersMod.MOD_ID, name),
               beforeTab != null ? List.of(beforeTab) : List.of(),
               afterTab != null ? List.of(afterTab) : List.of(),
               builder -> builder
                       .icon(iconSupplier)
                       .title(Component.translatable("itemGroup." + BlazersMod.MOD_ID + "." + name))
                       .build());
    }

    //DEFAULT_AFTER_ENTRIES, List.of()
    /**
     * Get an {@link ItemStack Item Stack} for a {@link RegistryObject<Block> Block}
     *
     * @param block {@link RegistryObject<Block> Block}
     * @return {@link ItemStack Item Stack}
     */
    private static ItemStack getItemStack(RegistryObject<Block> block) {
        return block.get().asItem().getDefaultInstance();
    }

    /**
     * Add the {@link ItemStack Items} inside the {@link CreativeModeTab Creative Mode Tabs}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative Mode Tab Build Contents Event}
     */
    public static void addItemsToCreativeTabs(CreativeModeTabEvent.BuildContents event) {
        CreativeModeTab tab = event.getTab();
        BLTabKeys tabKey = BLTabKeys.fromTab(tab);
        if(tab.equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> iterator = event.getEntries().iterator();
            ArrayList<ItemStack> itemsToRemove = new ArrayList<>();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next().getKey();
                if(stack.is(Items.PAINTING)) {
                    itemsToRemove.add(stack);
                }
            }
            itemsToRemove.forEach(stack -> event.getEntries().remove(stack));
        }
        if(tabKey != null) {
            List<RegistryObject<Item>> items = BLTabs.ITEM_GROUPS.get(tabKey);
            if(items != null) {
                event.acceptAll(items.stream().map(item -> item.get().getDefaultInstance()).collect(Collectors.toList()));
            }
            if(tabKey.equals(BLTabKeys.TOOLS)) {
                BuiltInRegistries.INSTRUMENT.asLookup().get(BLTags.Instruments.MELODY_COPPER_HORNS).ifPresent(instruments -> {
                    instruments.forEach(instrumentHolder -> event.accept(CopperHornItem.create(BLItems.COPPER_HORN.get(), instrumentHolder)));
                });
            }
            if(tabKey.equals(BLTabKeys.FUNCTIONAL)) {
                ForgeRegistries.PAINTING_VARIANTS.getValues().stream().map(ForgeRegistries.PAINTING_VARIANTS::getHolder)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .filter(paintingVariantHolder -> paintingVariantHolder.is(BLTags.Paintings.EBL_PAINTINGS))
                        .forEach(paintingVariantHolder -> {
                            ItemStack itemstack = new ItemStack(Items.PAINTING);
                            CompoundTag compoundtag = itemstack.getOrCreateTagElement("EntityTag");
                            Painting.storeVariant(compoundtag, paintingVariantHolder);
                            event.accept(itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        });
            }
        }
    }

    private static boolean isEblPainting(ItemStack stack) {
        if(stack.is(Items.PAINTING) && stack.getTagElement("EntityTag") != null) {
            Optional<Holder<PaintingVariant>> paintingVariant = ForgeRegistries.PAINTING_VARIANTS.getHolder(ResourceLocation.tryParse(stack.getTagElement("EntityTag").getString("variant")));
            return paintingVariant.map(paintingVariantHolder -> paintingVariantHolder.is(BLTags.Paintings.EBL_PAINTINGS)).orElse(false);
        }
        return false;
    }

    public enum BLTabKeys {
        BUILDING_BLOCKS,
        COLORED_BLOCKS,
        NATURAL,
        FUNCTIONAL,
        REDSTONE,
        TOOLS,
        COMBAT,
        FOOD_AND_DRINK,
        INGREDIENTS,
        SPAWN_EGGS;

        public static BLTabKeys fromTab(CreativeModeTab tab) {
            if(tab.equals(BLTabs.BUILDING_BLOCKS)) {
                return BLTabKeys.BUILDING_BLOCKS;
            }
            else if(tab.equals(BLTabs.COLORED_BLOCKS)) {
                return BLTabKeys.COLORED_BLOCKS;
            }
            else if(tab.equals(BLTabs.NATURAL)) {
                return BLTabKeys.NATURAL;
            }
            else if(tab.equals(BLTabs.FUNCTIONAL)) {
                return BLTabKeys.FUNCTIONAL;
            }
            else if(tab.equals(BLTabs.REDSTONE)) {
                return BLTabKeys.REDSTONE;
            }
            else if(tab.equals(BLTabs.TOOLS)) {
                return BLTabKeys.TOOLS;
            }
            else if(tab.equals(BLTabs.COMBAT)) {
                return BLTabKeys.COMBAT;
            }
            else if(tab.equals(BLTabs.FOOD_AND_DRINK)) {
                return BLTabKeys.FOOD_AND_DRINK;
            }
            else if(tab.equals(BLTabs.INGREDIENTS)) {
                return BLTabKeys.INGREDIENTS;
            }
            else if(tab.equals(BLTabs.SPAWN_EGGS)) {
                return BLTabKeys.SPAWN_EGGS;
            }
            return null;
        }
    }
}