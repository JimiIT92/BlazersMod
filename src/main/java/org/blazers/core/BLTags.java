package org.blazers.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
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
}