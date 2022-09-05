package org.blazers.client.itemrenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.blazers.core.BLItems;
import org.blazers.core.BLModelLayers;
import org.blazers.entity.SpearEntity;
import org.blazers.entity.client.SpearEntityRenderer;
import org.blazers.entity.client.model.SpearModel;

import java.util.Collection;
import java.util.Collections;

@Environment(EnvType.CLIENT)
public class SpearItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, SimpleSynchronousResourceReloadListener {

    private final Identifier id;
    private final Identifier spearId;
    private final Identifier texture;
    protected final EntityModelLayer modelLayer;
    private ItemRenderer itemRenderer;
    private EntityModel<SpearEntity> spearModel;
    private BakedModel spearInventoryModel;

    public SpearItemRenderer() {
        this(Registry.ITEM.getId(BLItems.SPEAR), SpearEntityRenderer.TEXTURE, BLModelLayers.SPEAR);
    }

    public SpearItemRenderer(Identifier id, Identifier texture, EntityModelLayer modelLayer) {
        this.id = new Identifier(id.getNamespace(), id.getPath() + "_renderer");
        this.spearId = id;
        this.texture = texture;
        this.modelLayer = modelLayer;
    }

    @Override
    public void reload(ResourceManager manager) {
        MinecraftClient mc = MinecraftClient.getInstance();
        this.itemRenderer = mc.getItemRenderer();
        this.spearModel = getSpearModel(mc);
        this.spearInventoryModel = mc.getBakedModelManager().getModel(new ModelIdentifier(this.spearId + "_in_inventory", "inventory"));
    }

    public EntityModel<SpearEntity> getSpearModel(MinecraftClient mc) {
        return new SpearModel(mc.getEntityModelLoader().getModelPart(this.modelLayer));
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return Collections.singletonList(ResourceReloadListenerKeys.MODELS);
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        assert this.spearModel != null;
        if (mode == ModelTransformation.Mode.GUI || mode == ModelTransformation.Mode.GROUND || mode == ModelTransformation.Mode.FIXED) {
            matrices.pop();
            matrices.push();
            itemRenderer.renderItem(stack, mode, false, matrices, vertexConsumers, light, overlay, this.spearInventoryModel);
        } else {
            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.spearModel.getLayer(this.texture), false, stack.hasGlint());
            this.spearModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }
    }

    @Override
    public Identifier getFabricId() {
        return id;
    }

    public Identifier getSpearId() {
        return Registry.ITEM.getId(BLItems.SPEAR);
    }
}
