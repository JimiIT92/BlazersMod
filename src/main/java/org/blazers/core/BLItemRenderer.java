package org.blazers.core;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.entity.ThrownSpear;
import org.blazers.entity.client.ThrownMalachiteSpearRenderer;
import org.blazers.entity.client.ThrownSpearRenderer;
import org.blazers.entity.client.model.ThrownMalachiteSpearModel;
import org.blazers.entity.client.model.ThrownSpearModel;
import org.blazers.item.SpearItem;
import org.jetbrains.annotations.NotNull;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Custom Item Renderer}
 */
@OnlyIn(Dist.CLIENT)
public class BLItemRenderer extends BlockEntityWithoutLevelRenderer {

    /**
     * {@link ThrownSpear Thrown Spear} {@link ThrownSpearModel Model}
     */
    private ThrownSpearModel spearModel;
    /**
     * {@link ThrownSpear Thrown Malachite Spear} {@link ThrownMalachiteSpearModel Model}
     */
    private ThrownMalachiteSpearModel malachiteSpearModel;
    /**
     * {@link EntityModelSet Entity Model Set}
     */
    private final EntityModelSet entityModelSet;

    /**
     * Constructor. Sets the {@link EntityModelSet Entity Model Set}
     * and initialize the {@link org.blazers.BlazersMod Blazers Mod} models
     */
    public BLItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.entityModelSet = Minecraft.getInstance().getEntityModels();
        initModels();
    }

    /**
     * Initialize the {@link org.blazers.BlazersMod Blazers Mod} models
     *
     * @param resourceManager {@link ResourceManager Resource Manager}
     */
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        initModels();
    }

    /**
     * Initialize the {@link org.blazers.BlazersMod Blazers Mod} models
     */
    private void initModels() {
        this.spearModel = new ThrownSpearModel(this.entityModelSet.bakeLayer(ThrownSpearModel.LAYER_LOCATION));
        this.malachiteSpearModel = new ThrownMalachiteSpearModel(this.entityModelSet.bakeLayer(ThrownMalachiteSpearModel.LAYER_LOCATION));
    }

    /**
     * Render custom models for selected {@link ItemStack Item Stacks}
     *
     * @param stack {@link ItemStack Item Stack}
     * @param transformType {@link ItemTransforms.TransformType Transform Type}
     * @param pose {@link PoseStack Pose}
     * @param buffer {@link MultiBufferSource Buffer}
     * @param packedLight {@link Integer Packed Light}
     * @param packedOverlay {@link Integer Packed Overlay}
     */
    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.@NotNull TransformType transformType, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(stack.getItem() instanceof SpearItem) {
            pose.pushPose();
            pose.scale(1.0F, -1.0F, -1.0F);
            boolean isMalachiteSpear = stack.is(BLItems.MALACHITE_SPEAR.get());
            Model model = isMalachiteSpear ? this.malachiteSpearModel : this.spearModel;
            ResourceLocation layerLocation = isMalachiteSpear ? ThrownMalachiteSpearRenderer.SPEAR_LOCATION : ThrownSpearRenderer.SPEAR_LOCATION;
            VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(buffer, model.renderType(layerLocation), false, stack.hasFoil());
            model.renderToBuffer(pose, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            pose.popPose();
        }
    }
}