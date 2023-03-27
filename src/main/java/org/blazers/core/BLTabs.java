package org.blazers.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

import java.util.function.Supplier;

public final class BLTabs {

    public static ItemGroup BUILDING_BLOCKS;
    public static ItemGroup COLORED_BLOCKS;
    public static ItemGroup NATURAL;
    public static ItemGroup FUNCTIONAL;
    public static ItemGroup REDSTONE;
    public static ItemGroup TOOLS;
    public static ItemGroup COMBAT;
    public static ItemGroup FOOD_AND_DRINK;
    public static ItemGroup INGREDIENTS;
    public static ItemGroup SPAWN_EGGS;

    private static ItemGroup createTab(final String name, final Supplier<ItemStack> item) {
        return FabricItemGroup.builder(new Identifier(BlazersMod.MOD_ID, name)).icon(item).build();
    }

    public static void registerItemGroup() {
        BUILDING_BLOCKS = createTab("building_blocks", () -> new ItemStack(BLBlocks.RUBY_BLOCK));
        COLORED_BLOCKS = createTab("colored_blocks", () -> new ItemStack(BLBlocks.YELLOW_CONCRETE_STAIRS));
        NATURAL = createTab("natural", () -> new ItemStack(BLBlocks.HOLLOW_BIRCH_LOG));
        FUNCTIONAL = createTab("functional", () -> new ItemStack(Items.PAINTING));
        REDSTONE = createTab("redstone", () -> new ItemStack(BLBlocks.COPPER_BUTTON));
        TOOLS = createTab("tools", () -> new ItemStack(BLItems.EMERALD_PICKAXE));
        COMBAT = createTab("combat", () -> new ItemStack(BLItems.KATANA));
        FOOD_AND_DRINK = createTab("food_and_drink", () -> new ItemStack(BLItems.SASHIMI));
        INGREDIENTS = createTab("ingredients", () -> new ItemStack(BLItems.RUBY));
        SPAWN_EGGS = createTab("spawn_eggs", () -> new ItemStack(BLItems.COPPER_GOLEM_SPAWN_EGG));
    }
}