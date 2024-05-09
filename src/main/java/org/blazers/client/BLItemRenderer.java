package org.blazers.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.blazers.BlazersMod;
import org.blazers.client.model.ThrownMalachiteSpearModel;
import org.blazers.client.model.ThrownSpearModel;
import org.blazers.client.renderer.projectile.ThrownMalachiteSpearRenderer;
import org.blazers.client.renderer.projectile.ThrownSpearRenderer;
import org.blazers.core.BLItems;
import org.blazers.entity.projectile.ThrownSpear;
import org.blazers.item.SpearItem;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Item renderer}
 */
@OnlyIn(Dist.CLIENT)
public final class BLItemRenderer extends BlockEntityWithoutLevelRenderer {

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
     * and initialize the {@link BlazersMod Blazers Mod} models
     */
    public BLItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.entityModelSet = Minecraft.getInstance().getEntityModels();
        initModels();
    }

    /**
     * Initialize the {@link BlazersMod Blazers Mod} models
     *
     * @param resourceManager {@link ResourceManager Resource Manager}
     */
    public void onResourceManagerReload(final @NotNull ResourceManager resourceManager) {
        initModels();
    }

    /**
     * Initialize the {@link BlazersMod Blazers Mod} models
     */
    private void initModels() {
        this.spearModel = new ThrownSpearModel(this.entityModelSet.bakeLayer(ThrownSpearModel.LAYER_LOCATION));
        this.malachiteSpearModel = new ThrownMalachiteSpearModel(this.entityModelSet.bakeLayer(ThrownMalachiteSpearModel.LAYER_LOCATION));
    }

    /**
     * Render custom models for selected {@link ItemStack Item Stacks}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param transformType {@link ItemDisplayContext The Transform Type}
     * @param pose {@link PoseStack The Item Pose}
     * @param buffer {@link MultiBufferSource The Multi Buffer Source}
     * @param packedLight {@link Integer The client packed light}
     * @param packedOverlay {@link Integer The client packed overlay}
     */
    @Override
    public void renderByItem(final ItemStack itemStack, final @NotNull ItemDisplayContext transformType, final @NotNull PoseStack pose, final @NotNull MultiBufferSource buffer, final int packedLight, final int packedOverlay) {
        if(itemStack.getItem() instanceof SpearItem) {
            pose.pushPose();
            pose.scale(1.0F, -1.0F, -1.0F);
            boolean isMalachiteSpear = itemStack.is(BLItems.MALACHITE_SPEAR.get());
            final Model model = isMalachiteSpear ? this.malachiteSpearModel : this.spearModel;
            final ResourceLocation layerLocation = isMalachiteSpear ? ThrownMalachiteSpearRenderer.SPEAR_LOCATION : ThrownSpearRenderer.SPEAR_LOCATION;
            VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(buffer, model.renderType(layerLocation), false, itemStack.hasFoil());
            model.renderToBuffer(pose, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            pose.popPose();
        }
    }

}