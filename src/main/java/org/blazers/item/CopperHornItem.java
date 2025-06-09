package org.blazers.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.InstrumentComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.blazers.core.BLInstruments;
import org.blazers.core.BLTags;
import org.hendrix.helper.PlayerHelper;
import org.hendrix.helper.RegistryHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link GoatHornItem Copper Horn}
 */
public final class CopperHornItem extends GoatHornItem {

    /**
     * The {@link Supplier<BiMap> Map Supplier} for upgradable instruments
     */
    private static final Supplier<BiMap<RegistryKey<Instrument>, RegistryKey<Instrument>>> UPGRADABLE_INSTRUMENTS = Suppliers.memoize(() -> ImmutableBiMap.<RegistryKey<Instrument>, RegistryKey<Instrument>>builder()
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
     * The {@link Supplier<BiMap> Map Supplier} for mapping {@link RegistryKey<Instrument> Instruments} to their bass counterpart
     */
    private static final Supplier<BiMap<RegistryKey<Instrument>, RegistryKey<Instrument>>> INSTRUMENT_TO_BASS_INSTRUMENT = Suppliers.memoize(() -> ImmutableBiMap.<RegistryKey<Instrument>, RegistryKey<Instrument>>builder()
            .put(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN, BLInstruments.GREAT_SKY_FALLING_COPPER_HORN_BASS)
            .put(BLInstruments.OLD_HYMN_RESTING_COPPER_HORN, BLInstruments.OLD_HYMN_RESTING_COPPER_HORN_BASS)
            .put(BLInstruments.PURE_WATER_DESIRE_COPPER_HORN, BLInstruments.PURE_WATER_DESIRE_COPPER_HORN_BASS)
            .put(BLInstruments.MUMBLE_FIRE_MEMORY_COPPER_HORN, BLInstruments.MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS)
            .put(BLInstruments.DRY_URGE_ANGER_COPPER_HORN, BLInstruments.DRY_URGE_ANGER_COPPER_HORN_BASS)
            .put(BLInstruments.CLEAR_TEMPER_JOURNEY_COPPER_HORN, BLInstruments.CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS)
            .put(BLInstruments.FRESH_NEST_THOUGHT_COPPER_HORN, BLInstruments.FRESH_NEST_THOUGHT_COPPER_HORN_BASS)
            .put(BLInstruments.SECRET_LAKE_TEAR_COPPER_HORN, BLInstruments.SECRET_LAKE_TEAR_COPPER_HORN_BASS)
            .put(BLInstruments.FEARLESS_RIVER_GIFT_COPPER_HORN, BLInstruments.FEARLESS_RIVER_GIFT_COPPER_HORN_BASS)
            .put(BLInstruments.SWEET_MOON_LOVE_COPPER_HORN, BLInstruments.SWEET_MOON_LOVE_COPPER_HORN_BASS)
            .build());

    /**
     * The {@link Supplier<BiMap> Map Supplier} for mapping {@link RegistryKey<Instrument> Instruments} to their harmony counterpart
     */
    private static final Supplier<BiMap<RegistryKey<Instrument>, RegistryKey<Instrument>>> INSTRUMENT_TO_HARMONY_INSTRUMENT = Suppliers.memoize(() -> ImmutableBiMap.<RegistryKey<Instrument>, RegistryKey<Instrument>>builder()
            .put(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN, BLInstruments.GREAT_SKY_FALLING_COPPER_HORN_HARMONY)
            .put(BLInstruments.OLD_HYMN_RESTING_COPPER_HORN, BLInstruments.OLD_HYMN_RESTING_COPPER_HORN_HARMONY)
            .put(BLInstruments.PURE_WATER_DESIRE_COPPER_HORN, BLInstruments.PURE_WATER_DESIRE_COPPER_HORN_HARMONY)
            .put(BLInstruments.MUMBLE_FIRE_MEMORY_COPPER_HORN, BLInstruments.MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY)
            .put(BLInstruments.DRY_URGE_ANGER_COPPER_HORN, BLInstruments.DRY_URGE_ANGER_COPPER_HORN_HARMONY)
            .put(BLInstruments.CLEAR_TEMPER_JOURNEY_COPPER_HORN, BLInstruments.CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY)
            .put(BLInstruments.FRESH_NEST_THOUGHT_COPPER_HORN, BLInstruments.FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY)
            .put(BLInstruments.SECRET_LAKE_TEAR_COPPER_HORN, BLInstruments.SECRET_LAKE_TEAR_COPPER_HORN_HARMONY)
            .put(BLInstruments.FEARLESS_RIVER_GIFT_COPPER_HORN, BLInstruments.FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY)
            .put(BLInstruments.SWEET_MOON_LOVE_COPPER_HORN, BLInstruments.SWEET_MOON_LOVE_COPPER_HORN_HARMONY)
            .build());

    /**
     * Constructor. Set the {@link Item.Settings Item properties}
     *
     * @param settings The {@link Settings Item settings}
     */
    public CopperHornItem(final Settings settings) {
        super(settings);
    }

    /**
     * Use the Item
     *
     * @param world The {@link World World reference}
     * @param player The {@link PlayerEntity Player using the Item}
     * @param hand The {@link Hand hand the Player is using}
     * @return The {@link ActionResult Item Use Action Result}
     */
    @Override
    public ActionResult use(final World world, final PlayerEntity player, final Hand hand) {
        final ItemStack itemStack = player.getStackInHand(hand);
        final Optional<? extends RegistryEntry<Instrument>> optionalInstrument = this.getInstrument(itemStack, player.getRegistryManager(), this.getInstrumentTagKey(player));
        if (optionalInstrument.isPresent()) {
            final Instrument instrument = (Instrument)((RegistryEntry<?>)optionalInstrument.get()).value();
            player.setCurrentHand(hand);
            playSound(world, player, instrument);
            player.getItemCooldownManager().set(itemStack, MathHelper.floor(instrument.useDuration() * 20.0F));
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.CONSUME;
        }
        return ActionResult.FAIL;
    }

    /**
     * Get the appropriate {@link Instrument Instrument} based on the provided {@link TagKey<Instrument> Instrument Tag Key}
     *
     * @param stack The {@link ItemStack current Item Stack}
     * @param registryWrapperLookup The {@link RegistryWrapper.WrapperLookup Registry Wrapper Lookup}
     * @param instrumentTagKey The {@link TagKey<Instrument> Instrument Tag Key}
     * @return The {@link RegistryEntry<Instrument> Instrument}, if any
     */
    private Optional<RegistryEntry<Instrument>> getInstrument(final ItemStack stack, final RegistryWrapper.WrapperLookup registryWrapperLookup, final TagKey<Instrument> instrumentTagKey) {
        final InstrumentComponent instrumentComponent = stack.get(DataComponentTypes.INSTRUMENT);
        RegistryEntry<Instrument> registryEntry = null;
        if(instrumentComponent != null) {
            registryEntry = instrumentComponent.getInstrument(registryWrapperLookup).orElse(null);
            if (registryEntry != null) {
                final Optional<RegistryKey<Instrument>> optionalInstrumentKey = getInstrumentRegistryKey(registryEntry, instrumentTagKey);
                if(optionalInstrumentKey.isPresent()) {
                    final Optional<Instrument> instrument = RegistryHelper.getValue(registryWrapperLookup, RegistryKeys.INSTRUMENT, optionalInstrumentKey.get());
                    if(instrument.isPresent()) {
                        return Optional.of(RegistryEntry.of(instrument.get()));
                    }
                }
            }
        }
        return Optional.ofNullable(registryEntry);
    }

    /**
     * Get the {@link RegistryKey<Instrument> Instrument Registry Key} based on the current {@link RegistryEntry<Instrument> Instrument Registry Entry}
     * and the provided {@link TagKey<Instrument> Instrument Tag Key}
     *
     * @param baseInstrument The {@link RegistryEntry<Instrument> current Instrument Registry Entry}
     * @param instrumentTagKey The {@link TagKey<Instrument> Instrument Tag Key}
     * @return The {@link RegistryKey<Instrument> Instrument Registry Key}
     */
    private Optional<RegistryKey<Instrument>> getInstrumentRegistryKey(final RegistryEntry<Instrument> baseInstrument, final TagKey<Instrument> instrumentTagKey) {
        return baseInstrument.getKey().flatMap(instrument -> {
            if(instrumentTagKey.equals(BLTags.Instruments.BASS_COPPER_HORNS)) {
                return Optional.ofNullable(INSTRUMENT_TO_BASS_INSTRUMENT.get().get(instrument));
            }
            if(instrumentTagKey.equals(BLTags.Instruments.HARMONY_COPPER_HORNS)) {
                return Optional.ofNullable(INSTRUMENT_TO_HARMONY_INSTRUMENT.get().get(instrument));
            }
            return Optional.of(instrument);
        });
    }

    /**
     * Get the {@link TagKey<Instrument> Instrument Tag Key} to use based
     * on the {@link PlayerEntity Player's pose}
     *
     * @param player The {@link PlayerEntity Player that is using the Item}
     * @return The {@link TagKey<Instrument> Instrument Tag Key to use}
     */
    private TagKey<Instrument> getInstrumentTagKey(final PlayerEntity player) {
        return player.isSneaking() ? BLTags.Instruments.BASS_COPPER_HORNS :
                PlayerHelper.isLookingUp(player) ? BLTags.Instruments.HARMONY_COPPER_HORNS : BLTags.Instruments.MELODY_COPPER_HORNS;
    }

    /**
     * Play the {@link Instrument Instrument} sound
     *
     * @param world The {@link World World reference}
     * @param player The {@link PlayerEntity Player that is using the Item}
     * @param instrument The {@link Instrument Instrument to play}
     */
    private static void playSound(final World world, final PlayerEntity player, final Instrument instrument) {
        world.playSoundFromEntity(player, player, instrument.soundEvent().value(), SoundCategory.RECORDS, instrument.range() / 16.0F, 1.0F);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }

    /**
     * Upgrade a {@link GoatHornItem Goat Horn}
     *
     * @param stack The {@link ItemStack current Item Stack}
     * @param registryWrapperLookup The {@link RegistryWrapper.WrapperLookup Registry Wrapper Lookup}
     * @param baseInstrument The {@link RegistryEntry<Instrument> Goat Horn Instrument}
     */
    public static void upgradeInstrument(final ItemStack stack, final RegistryWrapper.WrapperLookup registryWrapperLookup, final InstrumentComponent baseInstrument) {
        baseInstrument.instrument().getKey().ifPresent(baseInstrumentKey -> {
            final RegistryKey<Instrument> instrument = getUpgradedInstrument(baseInstrumentKey).orElse(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN);
            getInstrumentRegistry(registryWrapperLookup).getOptional(instrument).ifPresent(upgradedInstrument -> stack.set(DataComponentTypes.INSTRUMENT, new InstrumentComponent(RegistryEntry.of(upgradedInstrument.value()))));
        });
    }

    /**
     * Get the {@link RegistryKey<Instrument> upgraded Instrument Registry Key} for the provided {@link RegistryKey<Instrument> Instrument}
     *
     * @param baseInstrument The {@link RegistryKey<Instrument> Instrument to upgrade}
     * @return The {@link RegistryKey<Instrument> upgraded Instrument Registry Key}
     */
    private static Optional<RegistryKey<Instrument>> getUpgradedInstrument(final RegistryKey<Instrument> baseInstrument) {
        return Optional.ofNullable(UPGRADABLE_INSTRUMENTS.get().get(baseInstrument));
    }

    /**
     * Get the {@link RegistryWrapper.Impl<Instrument> Instrument Registry reference}
     *
     * @param registryWrapperLookup The {@link RegistryWrapper.WrapperLookup Registry Wrapper Lookup}
     * @return The {@link RegistryWrapper.Impl<Instrument> Instrument Registry reference}
     */
    private static RegistryWrapper.Impl<Instrument> getInstrumentRegistry(final RegistryWrapper.WrapperLookup registryWrapperLookup) {
        return RegistryHelper.getRegistry(registryWrapperLookup, RegistryKeys.INSTRUMENT);
    }

}