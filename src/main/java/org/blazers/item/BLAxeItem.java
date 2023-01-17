package org.blazers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class BLAxeItem extends AxeItem {

    public BLAxeItem(ToolMaterial material, float attackDamageModifier, float attackSpeedModifier) {
        super(material, attackDamageModifier, attackSpeedModifier, new FabricItemSettings());
    }
}
