package org.blazers.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.Identifier;
import org.blazers.entity.projectile.SpearEntity;
import org.hendrix.helper.IdentifierHelper;

/**
 * Model class for a {@link SpearEntity Spear Entity}
 */
@Environment(EnvType.CLIENT)
public final class SpearEntityModel extends Model {

    /**
     * The {@link Identifier Model Texture}
     */
    public static final Identifier TEXTURE = IdentifierHelper.modded("textures/entity/spear/spear.png");

    /**
     * Constructor. Set the {@link ModelPart root model part}
     *
     * @param root The {@link ModelPart root model part}
     */
    public SpearEntityModel(final ModelPart root) {
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
                .uv(9, 0).cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .uv(28, 0).cuboid(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.pivot(0f, -3.0f, 0f));
        return TexturedModelData.of(modelData, 32, 32);
    }

}