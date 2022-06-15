package org.blazers.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import org.blazers.BlazersMod;
import org.blazers.core.BLTabs;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod} {@link HorseArmorItem Horse Armor Item}
 */
public class BLHorseArmorItem extends HorseArmorItem {

    /**
     * Constructor. Sets the {@link HorseArmorItem Horse Armor Item}
     *
     * @param protection {@link Integer Armor Protection}
     * @param name {@link String Armor name}
     */
    public BLHorseArmorItem(int protection, String name) {
        super(protection, new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/horse/armor/horse_armor_" + name + ".png"), new Properties().stacksTo(1).tab(BLTabs.TAB_MISC));
    }
}