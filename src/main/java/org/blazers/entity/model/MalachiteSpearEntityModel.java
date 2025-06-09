package org.blazers.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.Identifier;
import org.blazers.entity.projectile.MalachiteSpearEntity;
import org.hendrix.helper.IdentifierHelper;

/**
 * Model class for a {@link MalachiteSpearEntity Malachite Spear Entity}
 */
@Environment(EnvType.CLIENT)
public final class MalachiteSpearEntityModel extends Model {

    /**
     * The {@link Identifier Model Texture}
     */
    public static final Identifier TEXTURE = IdentifierHelper.modded("textures/entity/spear/malachite.png");

    /**
     * Constructor. Set the {@link ModelPart root model part}
     *
     * @param root The {@link ModelPart root model part}
     */
    public MalachiteSpearEntityModel(final ModelPart root) {
        super(root, RenderLayer::getEntitySolid);
    }

    /**
     * Get the {@link TexturedModelData Textured Model Data}
     *
     * @return The {@link TexturedModelData Textured Model Data}
     */
    public static TexturedModelData getTextureModelData() {
        final ModelData modelData = new ModelData();
        final ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create()
                .uv(0, 0).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F)
                .uv(4, 0).cuboid(-2.5F, -5.0F, -2.5F, 5.0F, 7.0F, 5.0F)
                .uv(4, 12).cuboid(-1.5F, 6.0F, -1.5F, 3.0F, 2.0F, 3.0F)
                .uv(12, 17).cuboid(-1.0F, 7.0F, 1.5F, 2.0F, 2.0F, 2.0F)
                .uv(4, 17).cuboid(-1.0F, 7.0F, -3.5F, 2.0F, 2.0F, 2.0F)
                .uv(4, 21).cuboid(-1.0F, 2.0F, -3.5F, 2.0F, 2.0F, 2.0F)
                .uv(12, 21).cuboid(-1.0F, 2.0F, 1.5F, 2.0F, 2.0F, 2.0F)
                .uv(1, 26).cuboid(-1.5F, -8.0F, -1.5F, 3.0F, 3.0F, 3.0F)
                .uv(14, 28).cuboid(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F),  ModelTransform.origin(0f, -3.0f, 0f));
        return TexturedModelData.of(modelData, 32, 32);
    }

}