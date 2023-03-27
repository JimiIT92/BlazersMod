package org.blazers.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod} {@link HorseArmorItem Horse Armor Item}
 */
public class BLHorseArmorItem extends HorseArmorItem {

    /**
     * {@link DispenserBlock Dispenser Block} {@link net.minecraft.core.dispenser.DispenseItemBehavior Dispense Item Behavior}
     */
    DefaultDispenseItemBehavior HORSE_ARMOR_DISPENSE_ITEM_BEHAVIOR = new OptionalDispenseItemBehavior() {
        /**
         * Dispense the specified {@link ItemStack Item Stack}
         *
         * @param blockSource  {@link BlockSource Block Source}
         * @param itemStack The {@link ItemStack Item Stack} to dispense
         */
        protected @NotNull ItemStack execute(BlockSource blockSource, @NotNull ItemStack itemStack) {
            BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
            for(AbstractHorse horse : blockSource.getLevel().getEntitiesOfClass(AbstractHorse.class, new AABB(blockpos), entity -> entity.isAlive() && entity.canWearArmor())) {
                if (horse.isArmor(itemStack) && !horse.isWearingArmor() && horse.isTamed()) {
                    horse.getSlot(401).set(itemStack.split(1));
                    this.setSuccess(true);
                    return itemStack;
                }
            }
            return super.execute(blockSource, itemStack);
        }
    };

    /**
     * Constructor. Sets the {@link HorseArmorItem Horse Armor Item}
     *
     * @param protection {@link Integer Armor Protection}
     * @param name {@link String Armor name}
     */
    public BLHorseArmorItem(int protection, String name) {
        super(protection, new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/horse/armor/horse_armor_" + name + ".png"), new Properties().stacksTo(1));
        DispenserBlock.registerBehavior(this, HORSE_ARMOR_DISPENSE_ITEM_BEHAVIOR);
    }
}