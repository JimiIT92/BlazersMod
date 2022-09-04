package org.blazers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;
import org.blazers.core.BLTabs;

public class BLMusicDiscItem extends MusicDiscItem {

    public BLMusicDiscItem(SoundEvent sound, int lengthInSeconds) {
        super(15, sound, new FabricItemSettings().group(BLTabs.TAB_MISC).maxCount(1).rarity(Rarity.RARE), lengthInSeconds);
    }
}
