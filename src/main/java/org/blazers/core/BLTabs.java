package org.blazers.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

import java.util.function.Supplier;

public final class BLTabs {

    public static ItemGroup TAB_BUILDING_BLOCKS;
    public static ItemGroup TAB_DECORATIONS;
    public static ItemGroup TAB_REDSTONE;
    public static ItemGroup TAB_TOOLS;
    public static ItemGroup TAB_COMBAT;
    public static ItemGroup TAB_FOOD;
    public static ItemGroup TAB_MISC;

    private static ItemGroup createTab(final String name, final Supplier<ItemStack> item) {
        return FabricItemGroup.builder(new Identifier(BlazersMod.MOD_ID, name)).icon(item).build();
    }

    public static void registerItemGroup() {
        TAB_BUILDING_BLOCKS = createTab("building_blocks", () -> new ItemStack(BLBlocks.SAPPHIRE_ORE));
        TAB_DECORATIONS = createTab("decorations", () -> new ItemStack(BLBlocks.CATTAIL));
        TAB_REDSTONE = createTab("redstone", () -> new ItemStack(BLBlocks.COPPER_BUTTON));
        TAB_TOOLS = createTab("tools", () -> new ItemStack(BLItems.EMERALD_PICKAXE));
        TAB_COMBAT = createTab("combat", () -> new ItemStack(BLItems.KATANA));
        TAB_FOOD = createTab("food", () -> new ItemStack(BLItems.SASHIMI));
        TAB_MISC = createTab("misc", () -> new ItemStack(BLItems.SAPPHIRE));
    }
}