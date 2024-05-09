package org.blazers.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.blazers.BlazersMod;
import org.blazers.block.AtomicTntBlock;
import org.blazers.block.weathering.IBLWaxedBlock;
import org.blazers.helper.ItemHelper;
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

    /**
     * Get the {@link DispenseItemBehavior Dispense Behavior} for a {@link TntBlock TNT Block}
     *
     * @return {@link DispenseItemBehavior The TNT Dispense Behavior}
     */
    private static DispenseItemBehavior tntDispenseBehavior() {
        return new DefaultDispenseItemBehavior() {
            /**
             * Dispense the specified {@link ItemStack Item Stack}
             *
             * @param blockSource  {@link BlockSource Block Source}
             * @param itemStack The {@link ItemStack Item Stack} to dispense
             */
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final Level level = blockSource.level();
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                final PrimedTnt primedTnt = AtomicTntBlock.getPrimedAtomicTnt(level, blockPos, null);
                level.addFreshEntity(primedTnt);
                level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockPos);
                ItemHelper.hurt(itemStack, null, null, 1);
                return itemStack;
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for an {@link AnimalArmorItem Horse Armor}
     *
     * @return {@link DispenseItemBehavior The Horse Armor Dispense Item Behavior}
     */
    private static DispenseItemBehavior horseArmorItemDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {

            /**
             * Equip the {@link AnimalArmorItem Horse Armor} if there's a Horse in front of the dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            @Override
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));

                for (final AbstractHorse abstractHorse : blockSource.level().getEntitiesOfClass(AbstractHorse.class, new AABB(blockPos), horse -> horse.isAlive() && horse.canWearBodyArmor())) {
                    if (abstractHorse.isBodyArmorItem(itemStack) && !abstractHorse.isWearingBodyArmor() && abstractHorse.isTamed()) {
                        abstractHorse.setBodyArmorItem(itemStack.split(1));
                        this.setSuccess(true);
                        return itemStack;
                    }
                }

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
        registerDispenseBehavior(BLBlocks.ATOMIC_TNT.get(), tntDispenseBehavior());
        registerDispenseBehavior(BLItems.EMERALD_HORSE_ARMOR.get(), horseArmorItemDispenseBehavior());
        registerDispenseBehavior(BLItems.SAPPHIRE_HORSE_ARMOR.get(), horseArmorItemDispenseBehavior());
        registerDispenseBehavior(BLItems.TOPAZ_HORSE_ARMOR.get(), horseArmorItemDispenseBehavior());
        registerDispenseBehavior(BLItems.RUBY_HORSE_ARMOR.get(), horseArmorItemDispenseBehavior());
        registerDispenseBehavior(BLItems.MALACHITE_HORSE_ARMOR.get(), horseArmorItemDispenseBehavior());
    }

    //#endregion

}