package org.blazers.core;

import net.minecraft.block.Block;
import net.minecraft.item.Instrument;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.BlazersMod;

public final class BLTags {

    public static class Blocks {

        public static final TagKey<Block> CATTAIL_PLACEABLE = tag("cattail_placeable");

        private static TagKey<Block> tag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(BlazersMod.MOD_ID, name));
        }

    }

    public static class Instruments {

        public static final TagKey<Instrument> BASS_COPPER_HORNS = tag("bass_copper_horns");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = tag("harmony_copper_horns");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = tag("melody_copper_horns");
        public static final TagKey<Instrument> COPPER_HORNS = tag("copper_horns");


        private static TagKey<Instrument> tag(String name) {
            return TagKey.of(Registry.INSTRUMENT_KEY, new Identifier(BlazersMod.MOD_ID, name));
        }
    }

}