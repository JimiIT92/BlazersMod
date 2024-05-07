package org.blazers.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
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
}