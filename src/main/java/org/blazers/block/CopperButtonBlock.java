package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;
import net.minecraft.sound.BlockSoundGroup;

public class CopperButtonBlock extends ButtonBlock {

    public CopperButtonBlock() {
        super(BlockSetType.COPPER, 40, FabricBlockSettings.create().noCollision().strength(0.5f).sounds(BlockSoundGroup.COPPER));
    }
}
