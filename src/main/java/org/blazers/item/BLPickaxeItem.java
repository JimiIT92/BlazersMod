package org.blazers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import org.blazers.core.BLTabs;

public class BLPickaxeItem extends PickaxeItem {

    public BLPickaxeItem(ToolMaterial material) {
        super(material, 1, -2.8F, new FabricItemSettings().group(BLTabs.TAB_TOOLS));
    }
}
