package org.blazers.core;

import net.minecraft.block.Block;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;

public final class BLTags {

    public static class Blocks {

        public static final TagKey<Block> CATTAIL_PLACEABLE = tag("cattail_placeable");

        private static TagKey<Block> tag(String name) {
            return TagKey.of(Registries.BLOCK.getKey(), new Identifier(BlazersMod.MOD_ID, name));
        }

    }

    public static class Instruments {

        public static final TagKey<Instrument> BASS_COPPER_HORNS = tag("bass_copper_horns");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = tag("harmony_copper_horns");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = tag("melody_copper_horns");
        public static final TagKey<Instrument> COPPER_HORNS = tag("copper_horns");


        private static TagKey<Instrument> tag(String name) {
            return TagKey.of(Registries.INSTRUMENT.getKey(), new Identifier(BlazersMod.MOD_ID, name));
        }
    }

    public static class Paintings {

        public static final TagKey<PaintingVariant> EBL_PAINTINGS = tag("ebl_paintings");


        private static TagKey<PaintingVariant> tag(String name) {
            return TagKey.of(Registries.PAINTING_VARIANT.getKey(), new Identifier(BlazersMod.MOD_ID, name));
        }
    }

}