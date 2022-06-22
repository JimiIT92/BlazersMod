package org.blazers.entity.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.ThrownSpear;
import org.blazers.entity.client.model.ThrownMalachiteSpearModel;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link ThrownSpear Thrown Malachite Spear}
 */
@OnlyIn(Dist.CLIENT)
public class ThrownMalachiteSpearRenderer extends ThrownSpearRenderer {

    /**
     * {@link ResourceLocation Spear Texture Location}
     */
    public static final ResourceLocation SPEAR_LOCATION = new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/spear/malachite.png");

    /**
     * Constructor. Sets the {@link EntityRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public ThrownMalachiteSpearRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownMalachiteSpearModel(context.bakeLayer(ThrownMalachiteSpearModel.LAYER_LOCATION));
    }

    /**
     * Get the {@link ResourceLocation Thrown Malachite Spear texture}
     *
     * @param entity {@link ThrownSpear Thrown Malachite Spear}
     * @return {@link ResourceLocation Thrown Malachite Spear texture}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownSpear entity) {
        return SPEAR_LOCATION;
    }
}