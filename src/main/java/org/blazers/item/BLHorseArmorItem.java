package org.blazers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.blazers.BlazersMod;

import java.util.List;

public class BLHorseArmorItem extends HorseArmorItem {

    private final String name;
    public static final DispenserBehavior DISPENSER_BEHAVIOR = new FallibleItemDispenserBehavior(){

        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
            List<AbstractHorseEntity> list = pointer.world().getEntitiesByClass(AbstractHorseEntity.class, new Box(blockPos), entity -> entity.isAlive() && entity.hasArmorSlot());
            for (AbstractHorseEntity abstractHorseEntity : list) {
                if (!abstractHorseEntity.isHorseArmor(stack) || abstractHorseEntity.hasArmorInSlot() || !abstractHorseEntity.isTame()) continue;
                abstractHorseEntity.getStackReference(401).set(stack.split(1));
                this.setSuccess(true);
                return stack;
            }
            return super.dispenseSilently(pointer, stack);
        }
    };

    public BLHorseArmorItem(int protection, String name) {
        super(protection, name, new FabricItemSettings());
        this.name = name;
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
    }

    public static boolean dispenseArmor(BlockPointer pointer, ItemStack armor) {
        BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
        List<HorseEntity> list = pointer.world().getEntitiesByClass(HorseEntity.class, new Box(blockPos), new EntityPredicates.Equipable(armor));
        if (list.isEmpty()) {
            return false;
        }
        HorseEntity horse = list.get(0);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(armor);
        ItemStack itemStack = armor.split(1);
        horse.equipStack(equipmentSlot, itemStack);
        horse.setEquipmentDropChance(equipmentSlot, 2.0f);
        horse.setPersistent();
        return true;
    }

    @Override
    public Identifier getEntityTexture() {
        return new Identifier(BlazersMod.MOD_ID, "textures/entity/horse/armor/horse_armor_" + name + ".png");
    }
}
