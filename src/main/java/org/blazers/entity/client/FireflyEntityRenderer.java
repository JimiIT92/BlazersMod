package org.blazers.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.blazers.BlazersMod;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.FireflyEntity;
import org.blazers.entity.client.model.FireflyModel;

public class FireflyEntityRenderer extends MobEntityRenderer<FireflyEntity, FireflyModel> {

    public FireflyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FireflyModel(context.getPart(BLModelLayers.FIREFLY)), 0);
    }

    @Override
    public Identifier getTexture(FireflyEntity entity) {
        return new Identifier(BlazersMod.MOD_ID, "textures/entity/firefly.png");
    }

    @Override
    protected int getBlockLight(FireflyEntity entity, BlockPos pos) {
        return 15;
    }
}
