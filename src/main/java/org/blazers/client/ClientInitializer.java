package org.blazers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceType;
import org.blazers.client.itemrenderer.MalachiteSpearItemRenderer;
import org.blazers.client.itemrenderer.SpearItemRenderer;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLItems;
import org.blazers.core.BLModelLayers;

@Environment(EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BLBlocks.BROWN_MUSHROOM_WALL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BLBlocks.RED_MUSHROOM_WALL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BLBlocks.POINTED_STONE_DRIPSTONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BLBlocks.POINTED_ICE_DRIPSTONE, RenderLayer.getCutout());

        BLModelPredicateProvider.registerModels();
        BLEntityTypes.registerRenderers();
        BLModelLayers.registerModelLayers();

        registerSpearEntityItemModel(BLItems.SPEAR, BLEntityTypes.SPEAR, new SpearItemRenderer());
        registerSpearEntityItemModel(BLItems.MALACHITE_SPEAR, BLEntityTypes.MALACHITE_SPEAR, new MalachiteSpearItemRenderer());
    }

    private void registerSpearEntityItemModel(Item item, EntityType entityType, SpearItemRenderer itemRenderer) {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(itemRenderer);
        BuiltinItemRendererRegistry.INSTANCE.register(item, itemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ModelIdentifier(itemRenderer.getSpearId() + "_in_inventory", "inventory")));
    }
}