package org.blazers.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.level.block.Block;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link Block Blocks}
 */
public final class BLTags {

    /**
     * {@link org.blazers.BlazersMod Blazers Mod} {@link TagKey<Block> Block Tags}
     */
    public static class Blocks {

        //#region Tags

        public static final TagKey<Block> CATTAIL_PLACEABLE = tag("cattail_placeable");

        //#endregion

        /**
         * Create a {@link TagKey<Block> Block Tag}
         *
         * @param name {@link String Tag Name}
         * @return {@link TagKey<Block> Block Tag}
         */
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BlazersMod.MOD_ID, name));
        }
    }

    /**
     * {@link org.blazers.BlazersMod Blazers Mod} {@link TagKey<Instrument> Instrument Tags}
     */
    public static class Instruments {

        //#region Tags

        public static final TagKey<Instrument> BASS_COPPER_HORNS = tag("bass_copper_horns");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = tag("harmony_copper_horns");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = tag("melody_copper_horns");
        public static final TagKey<Instrument> COPPER_HORNS = tag("copper_horns");

        /**
         * Create a {@link TagKey<Instrument> Instrument Tag}
         *
         * @param name {@link String Tag Name}
         * @return {@link TagKey<Instrument> Instrument Tag}
         */
        private static TagKey<Instrument> tag(String name) {
            return TagKey.create(Registry.INSTRUMENT_REGISTRY, new ResourceLocation(BlazersMod.MOD_ID, name));
        }
    }
}