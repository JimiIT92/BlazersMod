package org.blazers.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.blazers.BlazersMod;
import org.blazers.core.BLInstruments;
import org.blazers.core.BLItems;
import org.blazers.core.BLTags;
import org.blazers.helper.PropertyHelper;
import org.blazers.helper.RegistryHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link BlazersMod Blazers Mod} {@link Item Copper Horn Item}
 */
public final class CopperHornItem extends InstrumentItem {

    /**
     * Upgradable {@link Instrument Instruments}
     */
    private static final Supplier<BiMap<ResourceKey<Instrument>, ResourceKey<Instrument>>> UPGRADABLE_INSTRUMENTS = Suppliers.memoize(() -> ImmutableBiMap.<ResourceKey<Instrument>, ResourceKey<Instrument>>builder()
            .put(Instruments.PONDER_GOAT_HORN, BLInstruments.GREAT_SKY_FALLING_COPPER_HORN)
            .put(Instruments.SING_GOAT_HORN, BLInstruments.OLD_HYMN_RESTING_COPPER_HORN)
            .put(Instruments.SEEK_GOAT_HORN, BLInstruments.PURE_WATER_DESIRE_COPPER_HORN)
            .put(Instruments.FEEL_GOAT_HORN, BLInstruments.MUMBLE_FIRE_MEMORY_COPPER_HORN)
            .put(Instruments.ADMIRE_GOAT_HORN, BLInstruments.DRY_URGE_ANGER_COPPER_HORN)
            .put(Instruments.CALL_GOAT_HORN, BLInstruments.CLEAR_TEMPER_JOURNEY_COPPER_HORN)
            .put(Instruments.YEARN_GOAT_HORN, BLInstruments.FRESH_NEST_THOUGHT_COPPER_HORN)
            .put(Instruments.DREAM_GOAT_HORN, BLInstruments.SECRET_LAKE_TEAR_COPPER_HORN)
            .put(BLInstruments.FLY_GOAT_HORN, BLInstruments.FEARLESS_RIVER_GIFT_COPPER_HORN)
            .put(BLInstruments.RESIST_GOAT_HORN, BLInstruments.SWEET_MOON_LOVE_COPPER_HORN)
            .build());

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public CopperHornItem() {
        super(PropertyHelper.item().stacksTo(1), BLTags.Instruments.COPPER_HORNS);
    }

    /**
     * Use the {@link Item Copper Horn}
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player that is using the item}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @return {@link InteractionResultHolder < ItemStack > The interaction result}
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        final Optional<? extends Holder<Instrument>> optionalInstrument = getInstrument(itemStack, getInstrumentTagKey(player));
        if (optionalInstrument.isPresent()) {
            final Instrument instrument = optionalInstrument.get().value();
            player.startUsingItem(hand);
            play(level, player, instrument);
            player.getCooldowns().addCooldown(this, instrument.useDuration());
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }

    /**
     * Upgrade a {@link Items#GOAT_HORN Goat Horn} {@link Instrument Instrument}
     * to the relative {@link BLItems#COPPER_HORN Copper Horn}
     *
     * @param itemStack {@link BLItems#COPPER_HORN Copper Horn Item Stack}
     * @param baseInstrument {@link Holder<Instrument> Base Instrument}
     */
    public static void upgradeInstrument(final ItemStack itemStack, final Holder<Instrument> baseInstrument) {
        final ResourceKey<Instrument> instrument = getUpgradedInstrument(baseInstrument.unwrapKey().orElse(null)).orElse(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN);
        itemStack.set(DataComponents.INSTRUMENT, BuiltInRegistries.INSTRUMENT.getHolderOrThrow(instrument));
    }

    /**
     * Get the {@link TagKey <Instrument> Copper Horn Instrument Tag Key}
     *
     * @param player {@link Player The Player} using the {@link BLItems#COPPER_HORN Copper Horn}
     * @return {@link TagKey<Instrument> Copper Horn Instrument Tag Key}
     */
    private TagKey<Instrument> getInstrumentTagKey(final Player player) {
        return player.isShiftKeyDown() ? BLTags.Instruments.BASS_COPPER_HORNS :
                player.getXRot() == -90F ? BLTags.Instruments.HARMONY_COPPER_HORNS : BLTags.Instruments.MELODY_COPPER_HORNS;
    }

    /**
     * Get the {@link InstrumentItem Item Stack} {@link Instrument Instrument}
     *
     * @param itemStack {@link ItemStack Instrument Item Stack}
     * @param instrumentTagKey {@link TagKey<Instrument> Instrument Tag Key}
     * @return {@link Holder<Instrument> Instrument}
     */
    public static Optional<? extends Holder<Instrument>> getInstrument(final ItemStack itemStack, final TagKey<Instrument> instrumentTagKey) {
        final Holder<Instrument> instrument = itemStack.get(DataComponents.INSTRUMENT);
        if (instrument != null) {
            final ResourceLocation instrumentResourceLocation = getInstrumentResourceLocation(instrument.unwrapKey().orElseThrow().location(), instrumentTagKey);
            return BuiltInRegistries.INSTRUMENT.getHolder(ResourceKey.create(Registries.INSTRUMENT, instrumentResourceLocation));
        }
        final Iterator<Holder<Instrument>> iterator = BuiltInRegistries.INSTRUMENT.getTagOrEmpty(instrumentTagKey).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    /**
     * Get an {@link Instrument Instrument} {@link ResourceKey Resource Location}
     * based on the {@link TagKey<Instrument> Instrument Tag Key}
     *
     * @param resourceLocation {@link ResourceLocation Base Resource Location}
     * @param instrumentTagKey {@link TagKey<Instrument> Instrument Tag Key}
     * @return {@link Instrument Instrument} {@link ResourceKey Resource Location}
     */
    private static ResourceLocation getInstrumentResourceLocation(final ResourceLocation resourceLocation, final TagKey<Instrument> instrumentTagKey) {
        if(instrumentTagKey.equals(BLTags.Instruments.MELODY_COPPER_HORNS) || instrumentTagKey.equals(InstrumentTags.GOAT_HORNS)
                || instrumentTagKey.equals(InstrumentTags.REGULAR_GOAT_HORNS)  || instrumentTagKey.equals(InstrumentTags.SCREAMING_GOAT_HORNS)) {
            return resourceLocation;
        }
        return RegistryHelper.location(resourceLocation.getNamespace(), resourceLocation.getPath() + "_" + (instrumentTagKey.equals(BLTags.Instruments.BASS_COPPER_HORNS) ? "bass" : "harmony"));
    }

    /**
     * Play the {@link Instrument Instrument} {@link net.minecraft.sounds.SoundEvent Sound}
     *
     * @param level {@link Level World reference}
     * @param player {@link Player The Player} using the {@link BLItems#COPPER_HORN Copper Horn}
     * @param instrument {@link Instrument Instrument}
     */
    private static void play(final Level level, final Player player, final Instrument instrument) {
        level.playSound(player, player, instrument.soundEvent().value(), SoundSource.RECORDS, instrument.range() / 16.0F, 1.0F);
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
    }

    /**
     * Get the Upgraded {@link Instrument Instrument}
     * based on the base {@link Instrument Instrument}
     *
     * @param baseInstrument Base {@link Instrument Instrument}
     * @return Upgraded {@link Instrument Instrument}
     */
    private static Optional<ResourceKey<Instrument>> getUpgradedInstrument(final ResourceKey<Instrument> baseInstrument) {
        return Optional.ofNullable(UPGRADABLE_INSTRUMENTS.get().get(baseInstrument));
    }

    /**
     * Copper Horn Variants
     */
    public enum CopperHornVariant {
        REGULAR(""),
        BASS("_bass"),
        HARMONY("_harmony");

        /**
         * The {@link String variant suffix}
         */
        private final String suffix;

        /**
         * Constructor. Set the {@link String variant suffix}
         *
         * @param suffix {@link String The variant suffix}
         */
        CopperHornVariant(final String suffix) {
            this.suffix = suffix;
        }

        /**
         * Get the {@link String variant suffix}
         *
         * @return {@link String The variant suffix}
         */
        public String suffix() {
            return this.suffix;
        }
    }

}