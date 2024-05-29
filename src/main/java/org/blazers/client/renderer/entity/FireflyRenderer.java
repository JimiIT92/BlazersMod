package org.blazers.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.client.model.entity.FireflyModel;
import org.blazers.entity.animal.Firefly;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link Firefly Firefly}
 */
@OnlyIn(Dist.CLIENT)
public final class FireflyRenderer extends MobRenderer<Firefly, FireflyModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Entity Renderer Context}
     */
    public FireflyRenderer(final EntityRendererProvider.Context context) {
        super(context, new FireflyModel(context.bakeLayer(FireflyModel.LAYER_LOCATION)), 0F);
    }


    /**
     * Get the {@link ResourceLocation entity texture location}
     *
     * @param entity {@link Firefly The entity}
     * @return {@link ResourceLocation The entity texture location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull Firefly entity) {
        return RegistryHelper.location("textures/entity/firefly.png");
    }

    /**
     * Get the entity renderer light level
     *
     * @param firefly {@link Firefly The entity}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Integer 15}
     */
    @Override
    protected int getBlockLightLevel(final @NotNull Firefly firefly, final @NotNull BlockPos blockPos) {
        return 15;
    }
}