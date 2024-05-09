package org.blazers.client.renderer.projectile;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.client.model.ThrownMalachiteSpearModel;
import org.blazers.entity.projectile.ThrownSpear;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link ThrownSpear Thrown Malachite Spear}
 */
@OnlyIn(Dist.CLIENT)
public final class ThrownMalachiteSpearRenderer extends ThrownSpearRenderer {

    /**
     * {@link ResourceLocation The Spear texture location}
     */
    public static ResourceLocation SPEAR_LOCATION = RegistryHelper.location("textures/entity/spear/malachite.png");

    /**
     * Constructor. Sets the {@link EntityRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public ThrownMalachiteSpearRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrownMalachiteSpearModel(context.bakeLayer(ThrownMalachiteSpearModel.LAYER_LOCATION));
    }

    /**
     * Get the {@link ResourceLocation Thrown Spear texture}
     *
     * @param entity {@link ThrownSpear Thrown Spear}
     * @return {@link ResourceLocation Thrown Spear texture}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull ThrownSpear entity) {
        return SPEAR_LOCATION;
    }
}