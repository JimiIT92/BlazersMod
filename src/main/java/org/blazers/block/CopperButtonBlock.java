package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import org.blazers.core.BLBlockSetTypes;

public class CopperButtonBlock extends ButtonBlock {

    public CopperButtonBlock() {
        super(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.COPPER),
                BLBlockSetTypes.COPPER, 40, false);
    }
}
