package org.blazers.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class BLTabs {

    //#region Creative Mode Tabs

    public static final CreativeModeTab TAB_BUILDING_BLOCKS = new CreativeModeTab(BlazersMod.MOD_ID + ".building_blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLBlocks.SAPPHIRE_ORE.get());
        }
    };

    public static final CreativeModeTab TAB_TOOLS = new CreativeModeTab(BlazersMod.MOD_ID + ".tools") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.SASHIMI.get());
        }
    };

    public static final CreativeModeTab TAB_COMBAT = new CreativeModeTab(BlazersMod.MOD_ID + ".combat") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.SASHIMI.get());
        }
    };

    public static final CreativeModeTab TAB_FOOD = new CreativeModeTab(BlazersMod.MOD_ID + ".food") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.SASHIMI.get());
        }
    };

    public static final CreativeModeTab TAB_MISC = new CreativeModeTab(BlazersMod.MOD_ID + ".misc") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BLItems.RUBY.get());
        }
    };

    //#endregion
}