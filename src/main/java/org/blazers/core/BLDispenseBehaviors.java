package org.blazers.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import org.blazers.BlazersMod;
import org.blazers.block.weathering.IBLWaxedBlock;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link DispenseItemBehavior Dispense Behaviors}
 */
public final class BLDispenseBehaviors {

    //#region Dispense Behaviors

    /**
     * Get the {@link DispenseItemBehavior Dispense Behavior} for the {@link HoneycombItem Honeycomb}
     *
     * @return {@link DispenseItemBehavior The Honeycomb Dispense Behavior}
     */
    private static DispenseItemBehavior honeycombDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {

            /**
             * Wax a {@link WeatheringCopper weathering Block} if is in front of the dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                final Level level = blockSource.level();
                this.setSuccess(IBLWaxedBlock.wax(level.getBlockState(blockPos), null, level, itemStack, blockPos, null));
                return super.execute(blockSource, itemStack);
            }
        };
    }

    //#endregion

    //#region Methods

    /**
     * Register a {@link DispenseItemBehavior Dispense Behavior}
     *
     * @param item {@link ItemLike The item associated with the Dispense Behavior}
     * @param dispenseBehavior {@link DispenseItemBehavior The Dispense Behavior}
     */
    private static void registerDispenseBehavior(final ItemLike item, final DispenseItemBehavior dispenseBehavior) {
        DispenserBlock.registerBehavior(item, dispenseBehavior);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link DispenseItemBehavior Dispense Behaviors}
     */
    public static void registerDispenseBehaviors() {
        registerDispenseBehavior(Items.HONEYCOMB, honeycombDispenseBehavior());
    }

    //#endregion

}