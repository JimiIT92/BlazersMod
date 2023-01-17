package org.blazers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class BLHoeItem extends HoeItem {

    public BLHoeItem(ToolMaterial material, int attackDamageModifier, float attackSpeedModifier) {
        super(material, attackDamageModifier, attackSpeedModifier, new FabricItemSettings());
    }
}
