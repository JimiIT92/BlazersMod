package org.blazers.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * {@link BlazersMod Blazers Mod} Music Disc Addition {@link LootModifier Loot Modifier}
 */
public final class MusicDiscAdditionModifier extends LootModifier {

    /**
     * {@link MapCodec<MusicDiscAdditionModifier> The Loot Table Modifier Coded}
     */
    public static final MapCodec<MusicDiscAdditionModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> codecStart(instance).and(instance.group(
                    ForgeRegistries.ITEMS.getCodec().fieldOf("music_disc").forGetter(m -> m.musicDisc),
                    Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance)
                )
            )
        .apply(instance, MusicDiscAdditionModifier::new)
    );

    /**
     * {@link Item The Music Disc Item}
     */
    private final Item musicDisc;
    /**
     * {@link Float The chance for the Item to be added to the Loot}
     */
    private final float chance;

    /**
     * Constructor. Sets the Loot Modifier properties
     *
     * @param conditionsIn {@link LootItemCondition The Loot Table Conditions} to verify
     * @param musicDisc {@link Item The Music Disc Item}
     * @param chance {@link Float The chance for the Item to be added to the Loot}
     */
    public MusicDiscAdditionModifier(final LootItemCondition[] conditionsIn, final Item musicDisc, final float chance) {
        super(conditionsIn);
        this.musicDisc = musicDisc;
        this.chance = chance;
    }

    /**
     * Adds the {@link #musicDisc Music Disc} to the Loot Table
     *
     * @param generatedLoot {@link ObjectArrayList<ItemStack> The Loot Table Items}
     * @param context {@link LootContext The Loot Context}
     * @return {@link ObjectArrayList<ItemStack> The Loot Table Items} with the {@link #musicDisc Music Disc} added
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(final ObjectArrayList<ItemStack> generatedLoot, final LootContext context) {
        if(context.getRandom().nextFloat() <= chance)  {
            generatedLoot.add(musicDisc.getDefaultInstance());
        }
        return generatedLoot;
    }

    /**
     * Get the {@link MapCodec<MusicDiscAdditionModifier> Loot Table Modifier Coded}
     *
     * @return {@link MapCodec<MusicDiscAdditionModifier> The Loot Table Modifier Coded}
     */
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

}
