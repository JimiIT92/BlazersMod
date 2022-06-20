package org.blazers.entity.client;

import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.entity.Firefly;
import org.blazers.entity.client.model.FireflyModel;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer class for a {@link Firefly Firefly}
 */
@OnlyIn(Dist.CLIENT)
public class FireflyRenderer extends MobRenderer<Firefly, FireflyModel> {

    /**
     * Constructor. Sets the {@link AbstractHorseRenderer Renderer properties}
     *
     * @param context {@link EntityRendererProvider.Context Renderer Context}
     */
    public FireflyRenderer(EntityRendererProvider.Context context) {
        super(context, new FireflyModel(context.bakeLayer(FireflyModel.LAYER_LOCATION)), 0F);
    }

    /**
     * Get the {@link ResourceLocation Firefly texture}
     *
     * @param entity {@link Firefly Firefly}
     * @return {@link ResourceLocation Firefly texture}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Firefly entity) {
        return new ResourceLocation(BlazersMod.MOD_ID, "textures/entity/firefly.png");
    }

    /**
     * Get the {@link Firefly Firefly} {@link Integer Block Light Level}
     *
     * @param entity {@link Firefly Firefly}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Firefly Firefly} {@link Integer Block Light Level}
     */
    @Override
    protected int getBlockLightLevel(@NotNull Firefly entity, @NotNull BlockPos pos) {
        return 15;
    }
}