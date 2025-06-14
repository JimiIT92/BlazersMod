package org.blazers.core;

import net.minecraft.block.Block;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import org.blazers.BlazersMod;
import org.hendrix.registry.HCTags;

/**
 * {@link BlazersMod Blazers Mod} {@link TagKey Tags}
 */
public final class BLTags {

    //#region Item Tags

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<Item> Item Tags}
     */
    public static class ItemTags {
        public static final TagKey<Item> EMERALD_TOOL_MATERIALS = toolMaterialsTag("emerald");
        public static final TagKey<Item> SAPPHIRE_TOOL_MATERIALS = toolMaterialsTag("sapphire");
        public static final TagKey<Item> RUBY_TOOL_MATERIALS = toolMaterialsTag("ruby");
        public static final TagKey<Item> TOPAZ_TOOL_MATERIALS = toolMaterialsTag("topaz");
        public static final TagKey<Item> MALACHITE_TOOL_MATERIALS = toolMaterialsTag("malachite");
        public static final TagKey<Item> ONICE_TOOL_MATERIALS = toolMaterialsTag("onice");
        public static final TagKey<Item> AMETHYST_TOOL_MATERIALS = toolMaterialsTag("amethyst");
        public static final TagKey<Item> PEARL_TOOL_MATERIALS = toolMaterialsTag("pearl");
        public static final TagKey<Item> BLAZERITE_TOOL_MATERIALS = toolMaterialsTag("blazerite");
        public static final TagKey<Item> GYULIANITE_TOOL_MATERIALS = toolMaterialsTag("gyulianite");
        public static final TagKey<Item> FLINT_TOOL_MATERIALS = toolMaterialsTag("flint");
        public static final TagKey<Item> CARBON_TOOL_MATERIALS = toolMaterialsTag("carbon");

        public static final TagKey<Item> REPAIRS_EMERALD_ARMOR = repairsArmorTag("emerald");
        public static final TagKey<Item> REPAIRS_SAPPHIRE_ARMOR = repairsArmorTag("sapphire");
        public static final TagKey<Item> REPAIRS_RUBY_ARMOR = repairsArmorTag("ruby");
        public static final TagKey<Item> REPAIRS_TOPAZ_ARMOR = repairsArmorTag("topaz");
        public static final TagKey<Item> REPAIRS_MALACHITE_ARMOR = repairsArmorTag("malachite");
        public static final TagKey<Item> REPAIRS_ONICE_ARMOR = repairsArmorTag("onice");
        public static final TagKey<Item> REPAIRS_AMETHYST_ARMOR = repairsArmorTag("amethyst");
        public static final TagKey<Item> REPAIRS_PEARL_ARMOR = repairsArmorTag("pearl");
        public static final TagKey<Item> REPAIRS_BLAZERITE_ARMOR = repairsArmorTag("blazerite");
        public static final TagKey<Item> REPAIRS_GYULIANITE_ARMOR = repairsArmorTag("gyulianite");

        /**
         * Get the {@link TagKey<Item> Item Tag} for some Tool Materials
         *
         * @param name The {@link String tag name}
         * @return The {@link TagKey<Item> Item Tag}
         */
        private static TagKey<Item> toolMaterialsTag(final String name) {
            return HCTags.itemTag(name + "_tool_materials");
        }

        /**
         * Get the {@link TagKey<Item> Item Tag} for some Armor Materials
         *
         * @param name The {@link String tag name}
         * @return The {@link TagKey<Item> Item Tag}
         */
        private static TagKey<Item> repairsArmorTag(final String name) {
            return HCTags.itemTag("repairs_" + name + "_armor");
        }
    }

    //#endregion

    //#region Block Tags

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<Block> Block Tags}
     */
    public static class BlockTags {

    }

    //#endregion

    //#region Instrument Tags

    /**
     * {@link BlazersMod Blazers Mod} {@link TagKey<Instrument> Instrument Tags}
     */
    public static class Instruments {
        public static final TagKey<Instrument> BASS_COPPER_HORNS = copperHornsTag("bass");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = copperHornsTag("harmony");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = copperHornsTag("melody");
        public static final TagKey<Instrument> COPPER_HORNS = copperHornsTag("");

        /**
         * Get the {@link TagKey<Instrument> Instrument Tag} for some Armor Materials
         *
         * @param name The {@link String tag name}
         * @return The {@link TagKey<Instrument> Instrument Tag}
         */
        private static TagKey<Instrument> copperHornsTag(final String name) {
            return HCTags.instrumentTag(name + (name.isEmpty() ? "" : "_") + "copper_horns");
        }
    }

    //#endregion

}