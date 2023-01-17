package org.blazers.client.itemrenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.blazers.core.BLItems;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.SpearEntity;
import org.blazers.entity.client.MalachiteSpearEntityRenderer;
import org.blazers.entity.client.model.MalachiteSpearModel;

@Environment(EnvType.CLIENT)
public class MalachiteSpearItemRenderer extends SpearItemRenderer {

    public MalachiteSpearItemRenderer() {
        super(Registries.ITEM.getId(BLItems.MALACHITE_SPEAR), MalachiteSpearEntityRenderer.TEXTURE, BLModelLayers.MALACHITE_SPEAR);
    }

    @Override
    public EntityModel<SpearEntity> getSpearModel(MinecraftClient mc) {
        return new MalachiteSpearModel(mc.getEntityModelLoader().getModelPart(this.modelLayer));
    }

    @Override
    public Identifier getSpearId() {
        return Registries.ITEM.getId(BLItems.MALACHITE_SPEAR);
    }
}
