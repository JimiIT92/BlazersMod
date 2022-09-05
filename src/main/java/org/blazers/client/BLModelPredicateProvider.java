package org.blazers.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.blazers.core.BLItems;

@Environment(EnvType.CLIENT)
public class BLModelPredicateProvider {

    public static void registerModels() {
        registerBow(BLItems.CARBON_BOW, 2.0F);
        registerActivePredicate("throwing", BLItems.SPEAR);
        registerActivePredicate("throwing", BLItems.MALACHITE_SPEAR);
        registerActivePredicate("tooting", BLItems.COPPER_HORN);
    }

    private static void registerBow(Item bow, float durationModifier) {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if(entity == null || entity.getActiveItem() != stack) {
                        return 0F;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / durationModifier;
                });
        registerActivePredicate("pulling", bow);
    }

    private static void registerActivePredicate(String name, Item item) {
        FabricModelPredicateProviderRegistry.register(item, new Identifier(name),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0F);
    }
}