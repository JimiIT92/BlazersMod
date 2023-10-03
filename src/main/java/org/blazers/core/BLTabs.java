package org.blazers.core;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
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
    /**
     * {@link DeferredRegister <CreativeModeTab> The creative mode tab registry}
     */
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlazersMod.MOD_ID);

    //#region Creative Mode Tabs

    public static RegistryObject<CreativeModeTab> BUILDING_BLOCKS = registerTab("building_blocks", () -> new ItemStack(BLBlocks.RUBY_BLOCK.get()));;
    public static RegistryObject<CreativeModeTab> COLORED_BLOCKS = registerTab("colored_blocks", BUILDING_BLOCKS.getKey(),()  -> new ItemStack(BLBlocks.YELLOW_CONCRETE_STAIRS.get()));
    public static RegistryObject<CreativeModeTab> NATURAL = registerTab("natural", COLORED_BLOCKS.getKey(), () -> new ItemStack(BLBlocks.HOLLOW_BIRCH_LOG.get()));
    public static RegistryObject<CreativeModeTab> FUNCTIONAL = registerTab("functional", NATURAL.getKey(), () -> new ItemStack(Items.PAINTING));
    public static RegistryObject<CreativeModeTab> REDSTONE = registerTab("redstone", FUNCTIONAL.getKey(), () -> new ItemStack(BLBlocks.COPPER_BUTTON.get()));
    public static RegistryObject<CreativeModeTab> TOOLS = registerTab("tools", REDSTONE.getKey(), () -> new ItemStack(BLItems.EMERALD_PICKAXE.get()));
    public static RegistryObject<CreativeModeTab> COMBAT = registerTab("combat", TOOLS.getKey(), () -> new ItemStack(BLItems.KATANA.get()));
    public static RegistryObject<CreativeModeTab> FOOD_AND_DRINK = registerTab("food_and_drink", COMBAT.getKey(), () -> new ItemStack(BLItems.SASHIMI.get()));
    public static RegistryObject<CreativeModeTab> INGREDIENTS = registerTab("ingredients", FOOD_AND_DRINK.getKey(), () -> new ItemStack(BLItems.RUBY.get()));
    public static RegistryObject<CreativeModeTab> SPAWN_EGGS = registerTab("spawn_eggs", INGREDIENTS.getKey(), () -> new ItemStack(BLItems.COPPER_GOLEM_SPAWN_EGG.get()));

    //#endregion

    /**
     * Register all creative tabs
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerTab(String name, Supplier<ItemStack> iconSupplier) {
        return registerTab(name, CreativeModeTabs.SPAWN_EGGS, iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerTab(String name, ResourceKey<CreativeModeTab> afterTab, Supplier<ItemStack> iconSupplier) {
        return registerTab(name, afterTab, null, iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Tab}
     *
     * @param name {@link String Creative Mode Tab Name}
     * @param iconSupplier {@link Supplier<ItemStack> Icon Supplier}
     * @return {@link CreativeModeTab Registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerTab(String name, ResourceKey<CreativeModeTab> afterTab, ResourceKey<CreativeModeTab> beforeTab, Supplier<ItemStack> iconSupplier) {
        return CREATIVE_MODE_TABS.register(name, () -> {
            CreativeModeTab.Builder builder = CreativeModeTab.builder()
                    .icon(iconSupplier)
                    .title(Component.translatable("itemGroup." + BlazersMod.MOD_ID + "." + name));

            if(beforeTab != null) {
                builder = builder.withTabsBefore(beforeTab);
            }

            return builder.build();
        });
    }

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
     * @param event {@link BuildCreativeModeTabContentsEvent Creative Mode Tab Build Contents Event}
     */
    @SubscribeEvent
    public static void addItemsToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        final ResourceKey<CreativeModeTab> tab = event.getTabKey();
        BLTabKeys tabKey = BLTabKeys.fromTab(tab);
        if(tab.equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> iterator = event.getEntries().iterator();
            ArrayList<ItemStack> itemsToRemove = new ArrayList<>();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next().getKey();
                if(isEblPainting(stack)) {
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

        public static BLTabKeys fromTab(ResourceKey<CreativeModeTab> tab) {
            if(tab.equals(BLTabs.BUILDING_BLOCKS.getKey())) {
                return BLTabKeys.BUILDING_BLOCKS;
            }
            else if(tab.equals(BLTabs.COLORED_BLOCKS.getKey())) {
                return BLTabKeys.COLORED_BLOCKS;
            }
            else if(tab.equals(BLTabs.NATURAL.getKey())) {
                return BLTabKeys.NATURAL;
            }
            else if(tab.equals(BLTabs.FUNCTIONAL.getKey())) {
                return BLTabKeys.FUNCTIONAL;
            }
            else if(tab.equals(BLTabs.REDSTONE.getKey())) {
                return BLTabKeys.REDSTONE;
            }
            else if(tab.equals(BLTabs.TOOLS.getKey())) {
                return BLTabKeys.TOOLS;
            }
            else if(tab.equals(BLTabs.COMBAT.getKey())) {
                return BLTabKeys.COMBAT;
            }
            else if(tab.equals(BLTabs.FOOD_AND_DRINK.getKey())) {
                return BLTabKeys.FOOD_AND_DRINK;
            }
            else if(tab.equals(BLTabs.INGREDIENTS.getKey())) {
                return BLTabKeys.INGREDIENTS;
            }
            else if(tab.equals(BLTabs.SPAWN_EGGS.getKey())) {
                return BLTabKeys.SPAWN_EGGS;
            }
            return null;
        }
    }
}