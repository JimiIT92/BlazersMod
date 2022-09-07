package org.blazers.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Adds a {@link net.minecraft.world.item.RecordItem Music Disc} to a Loot Table
 */
public class MusicDiscAdditionModifier extends LootModifier {

    /**
     * {@link Codec< MusicDiscAdditionModifier > Codec Supplier}
     */
    public static final Supplier<Codec<MusicDiscAdditionModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder
            .create(inst -> codecStart(inst)
                    .and(inst.group(
                            ForgeRegistries.ITEMS.getCodec().fieldOf("music_disc").forGetter(m -> m.musicDisc),
                            Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance)
                        )
                    ).apply(inst, MusicDiscAdditionModifier::new)
            ));

    /**
     * {@link Item Music Disc} to add to the Loot Table
     */
    private final Item musicDisc;

    /**
     * {@link Float Rarity} inside the Loot Table
     */
    private final float chance;

    /**
     * Constructor. Sets the {@link Item Item} to add to the Loot Table
     *
     * @param conditionsIn {@link LootItemCondition Loot Table Conditions} to verify
     * @param musicDisc {@link Item Item to add}
     * @param chance {@link Float Rarity} inside the Loot Table
     */
    public MusicDiscAdditionModifier(LootItemCondition[] conditionsIn, Item musicDisc, float chance) {
        super(conditionsIn);
        this.musicDisc = musicDisc;
        this.chance = chance;
    }

    /**
     * Adds the {@link #musicDisc Addition Item} to the Loot Table
     *
     * @param generatedLoot {@link ObjectArrayList<ItemStack> Loot Table Items}
     * @param context {@link LootContext Loot Context}
     * @return {@link ObjectArrayList<ItemStack> Loot Table Items} with the {@link #musicDisc Addition Item} added
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getRandom().nextFloat() <= chance)  {
            generatedLoot.add(musicDisc.getDefaultInstance());
        }
        return generatedLoot;
    }

    /**
     * Get the {@link Codec<MusicDiscAdditionModifier> Loot Table Modifier Coded}
     *
     * @return {@link Codec<MusicDiscAdditionModifier> Loot Table Modifier Coded}
     */
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}