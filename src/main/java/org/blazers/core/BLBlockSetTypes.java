package org.blazers.core;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link BlockSetType Block Set Types}
 */
public final class BLBlockSetTypes {

    //#region Block Set Types

    public static final BlockSetType COPPER_BLOCK_SET = new BlockSetType("copper", SoundType.COPPER,
            SoundEvents.COPPER_BREAK, SoundEvents.COPPER_PLACE,
            SoundEvents.COPPER_BREAK, SoundEvents.COPPER_PLACE,
            SoundEvents.COPPER_BREAK, SoundEvents.COPPER_PLACE,
            SoundEvents.COPPER_BREAK, SoundEvents.COPPER_PLACE);

    //#endregion

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link BlockSetType Block Set Types}
     */
    public static void register() {
        BlockSetType.register(COPPER_BLOCK_SET);
    }
}
