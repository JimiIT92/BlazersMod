package org.blazers.entity.renderer;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.model.SpearEntityModel;
import org.blazers.item.SpearItem;
import org.hendrix.client.renderer.HCItemModelRenderer;

/**
 * Renderer class for a {@link SpearItem Spear Item}
 */
@Environment(EnvType.CLIENT)
public final class SpearModelRenderer extends HCItemModelRenderer<SpearEntityModel> {

    /**
     * Constructor. Set the {@link SpearEntityModel Entity Model}
     *
     * @param model The {@link SpearEntityModel Entity Model}
     */
    public SpearModelRenderer(final SpearEntityModel model) {
        super(model, SpearEntityModel.TEXTURE);
    }

    /**
     * Record class for the {@link SpecialModelRenderer.Unbaked Unbaked model}
     */
    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked {

        /**
         * The {@link MapCodec<Unbaked> Model Codec}
         */
        public static final MapCodec<Unbaked> CODEC = MapCodec.unit(new Unbaked());

        /**
         * Get the {@link MapCodec<Unbaked> Model Codec}
         *
         * @return The {@link #CODEC Model Codec}
         */
        @Override
        public MapCodec<Unbaked> getCodec() {
            return CODEC;
        }

        /**
         * Get the {@link SpecialModelRenderer Item Model Renderer instance}
         *
         * @param entityModels The {@link LoadedEntityModels Entity Models}
         * @return The {@link SpecialModelRenderer Item Model Renderer instance}
         */
        @Override
        public SpecialModelRenderer<?> bake(final LoadedEntityModels entityModels) {
            return new SpearModelRenderer(new SpearEntityModel(entityModels.getModelPart(BLModelLayers.SPEAR)));
        }

    }
}