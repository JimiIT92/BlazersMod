package org.blazers.entity.renderer;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.model.MalachiteSpearEntityModel;
import org.blazers.item.SpearItem;
import org.hendrix.client.renderer.HCItemModelRenderer;

/**
 * Renderer class for a {@link SpearItem Malachite Spear Item}
 */
@Environment(EnvType.CLIENT)
public final class MalachiteSpearModelRenderer extends HCItemModelRenderer<MalachiteSpearEntityModel> {

    /**
     * Constructor. Set the {@link MalachiteSpearEntityModel Entity Model}
     *
     * @param model The {@link MalachiteSpearEntityModel Entity Model}
     */
    public MalachiteSpearModelRenderer(final MalachiteSpearEntityModel model) {
        super(model, MalachiteSpearEntityModel.TEXTURE);
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
            return new MalachiteSpearModelRenderer(new MalachiteSpearEntityModel(entityModels.getModelPart(BLModelLayers.MALACHITE_SPEAR)));
        }

    }
}