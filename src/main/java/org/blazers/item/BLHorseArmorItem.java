package org.blazers.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import org.blazers.BlazersMod;
import org.blazers.helper.ItemHelper;
import org.blazers.helper.PropertyHelper;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link AnimalArmorItem Horse Armor Item}
 */
public final class BLHorseArmorItem extends AnimalArmorItem {

    /**
     * {@link Tier The Horse Armor Item Tier}
     */
    private final Tier tier;

    /**
     * Constructor. Set the {@link ItemProperties Item propertier}
     *
     * @param tier {@link Tier The Horse Armor Item Tier}
     * @param armorMaterialHolder {@link Holder<ArmorMaterial> The Horse Armor Material Holder}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public BLHorseArmorItem(final Tier tier, final Holder<ArmorMaterial> armorMaterialHolder, final FeatureFlag... featureFlags) {
        super(ArmorMaterials.IRON, BodyType.EQUESTRIAN, false, PropertyHelper.item(featureFlags).stacksTo(1));
        this.tier = tier;
    }

    /**
     * Get the {@link ResourceLocation Horse Armor Texture location}
     *
     * @return {@link ResourceLocation The Horse Armor Texture location}
     */
    @Override
    public @NotNull ResourceLocation getTexture() {
        return RegistryHelper.location("textures/entity/horse/armor/horse_armor_" + ItemHelper.tierName(this.tier) + ".png");
    }

}