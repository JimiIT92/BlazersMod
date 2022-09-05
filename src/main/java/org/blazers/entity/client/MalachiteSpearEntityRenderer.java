package org.blazers.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.blazers.BlazersMod;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.SpearEntity;
import org.blazers.entity.client.model.MalachiteSpearModel;

@Environment(value= EnvType.CLIENT)
public class MalachiteSpearEntityRenderer extends SpearEntityRenderer {

    public static final Identifier TEXTURE = new Identifier(BlazersMod.MOD_ID, "textures/entity/spear/malachite.png");
    private final MalachiteSpearModel model;

    public MalachiteSpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new MalachiteSpearModel(context.getPart(BLModelLayers.MALACHITE_SPEAR));
    }

    @Override
    public Identifier getTexture(SpearEntity tridentEntity) {
        return TEXTURE;
    }
}
