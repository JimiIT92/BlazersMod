package org.blazers.core;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

public final class BLBlockSetTypes {
    public static final BlockSetType COPPER = BlockSetTypeRegistry.register(
            new Identifier(BlazersMod.MOD_ID, "copper"),
            false,
            BlockSoundGroup.COPPER,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundEvents.BLOCK_COPPER_PLACE,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundEvents.BLOCK_COPPER_PLACE,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundEvents.BLOCK_COPPER_PLACE,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundEvents.BLOCK_COPPER_PLACE
    );

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " block set types");
    }
}
