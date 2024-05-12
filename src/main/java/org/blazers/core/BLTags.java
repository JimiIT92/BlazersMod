package org.blazers.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.level.block.Block;
import org.blazers.BlazersMod;
import org.blazers.helper.RegistryHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link TagKey Tags}
 */
public final class BLTags {

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<Block> Block Tags }
     */
    public static class Blocks {

        //#region Tags

        public static final TagKey<Block> CATTAIL_PLACEABLE = tag("cattail_placeable");

        //#endregion

        //#region Methods

        /**
         * Create a {@link TagKey<Block> Block Tag}
         *
         * @param name {@link String The tag name}
         * @return {@link TagKey<Block> The Block Tag}
         */
        private static TagKey<Block> tag(final String name) {
            return BlockTags.create(RegistryHelper.location(name));
        }

        //#endregion

    }

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<Instrument> Instrument Tags }
     */
    public static class Instruments {

        //#region Instruments

        public static final TagKey<Instrument> BASS_COPPER_HORNS = tag("bass_copper_horns");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = tag("harmony_copper_horns");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = tag("melody_copper_horns");
        public static final TagKey<Instrument> COPPER_HORNS = tag("copper_horns");

        //#endregion

        /**
         * Create a {@link TagKey<Instrument> Instrument Tag}
         *
         * @param name {@link String The tag name}
         * @return {@link TagKey<Instrument> The Instrument Tag}
         */
        private static TagKey<Instrument> tag(final String name) {
            return TagKey.create(Registries.INSTRUMENT, RegistryHelper.location(name));
        }

    }

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<PaintingVariant> Painting Tags }
     */
    public static class Paintings {

        //#region Tags

        public static final TagKey<PaintingVariant> EBL_PAINTINGS = tag("ebl_paintings");

        //#endregion

        //#region Methods

        /**
         * Create a {@link TagKey<PaintingVariant> Painting Tag}
         *
         * @param name {@link String The tag name}
         * @return {@link TagKey<PaintingVariant> The Painting Tag}
         */
        private static TagKey<PaintingVariant> tag(final String name) {
            return TagKey.create(Registries.PAINTING_VARIANT, RegistryHelper.location(name));
        }

        //#endregion

    }

}