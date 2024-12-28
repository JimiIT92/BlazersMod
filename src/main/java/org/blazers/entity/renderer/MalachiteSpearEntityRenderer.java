package org.blazers.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.model.MalachiteSpearEntityModel;
import org.blazers.entity.projectile.MalachiteSpearEntity;

/**
 * Renderer class for a {@link MalachiteSpearEntity Malachite Spear}
 */
@Environment(EnvType.CLIENT)
public class MalachiteSpearEntityRenderer extends SpearEntityRenderer {

    /**
     * Constructor. Set the render propertis
     *
     * @param context The {@link EntityRendererFactory.Context Render Context}
     */
    public MalachiteSpearEntityRenderer(final EntityRendererFactory.Context context) {
        super(context);
        this.model = new MalachiteSpearEntityModel(context.getPart(BLModelLayers.MALACHITE_SPEAR));
    }

    /**
     * Get the {@link Identifier Entity Texture}
     *
     * @return The {@link Identifier Entity Texture}
     */
    public Identifier texture() {
        return MalachiteSpearEntityModel.TEXTURE;
    }

}
