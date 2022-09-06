package org.blazers.core;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public final class BLLootModifiers {

    private static final Identifier JUNGLE_TEMPLE_CHEST_ID = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier NETHER_FORTRESS_BRIDGE_CHEST_ID = new Identifier("minecraft", "chests/nether_bridge");

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(JUNGLE_TEMPLE_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1F))
                        .with(ItemEntry.builder(BLItems.MUSIC_DISC_SURVIVAL))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(NETHER_FORTRESS_BRIDGE_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1F))
                        .with(ItemEntry.builder(BLItems.MUSIC_DISC_ENDERMAN_VS_BLAZE))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}