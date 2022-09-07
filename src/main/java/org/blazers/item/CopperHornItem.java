package org.blazers.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.blazers.BlazersMod;
import org.blazers.core.*;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for the {@link BlazersMod Blazers Mod} {@link org.blazers.core.BLItems#COPPER_HORN Copper Horn}
 */
public class CopperHornItem extends InstrumentItem {

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
     * Constructor. Sets the {@link org.blazers.core.BLItems#COPPER_HORN Copper Horn} {@link net.minecraft.world.item.Item.Properties Properties}
     */
    public CopperHornItem() {
        super(new Item.Properties().tab(BLTabs.TAB_MISC).stacksTo(1), BLTags.Instruments.COPPER_HORNS);
    }

    /**
     * Shows the different {@link CopperHornItem Copper Horn} base sounds inside the Inventory
     *
     * @param tab {@link CreativeModeTab Creative Tab}
     * @param items {@link ItemStack Items}
     */
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> items) {
        if (this.allowedIn(tab)) {
            for(Holder<Instrument> holder : Registry.INSTRUMENT.getTagOrEmpty(BLTags.Instruments.MELODY_COPPER_HORNS)) {
                items.add(create(BLItems.COPPER_HORN.get(), holder));
            }
        }
    }

    /**
     * Play the {@link Instrument Copper Horn Instrument} {@link net.minecraft.sounds.SoundEvent Sound}
     *
     * @param level {@link Level World reference}
     * @param player {@link Player The Player} using the {@link Items#GOAT_HORN Goat Horn}
     * @param hand {@link InteractionHand The hand} which holds the {@link Items#GOAT_HORN Goat Horn}
     * @return {@link InteractionResultHolder<ItemStack> Interaction Result}
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Optional<Holder<Instrument>> optional = getInstrument(itemstack, getInstrumentTagKey(player));
        if (optional.isPresent()) {
            Instrument instrument = optional.get().value();
            player.startUsingItem(hand);
            play(level, player, instrument);
            player.getCooldowns().addCooldown(this, instrument.useDuration());
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    /**
     * Upgrade a {@link Items#GOAT_HORN Goat Horn} {@link Instrument Instrument}
     * to the relative {@link BLItems#COPPER_HORN Copper Horn} one
     *
     * @param stack {@link BLItems#COPPER_HORN Copper Horn Item Stack}
     * @param baseInstrument {@link Holder<Instrument> Base Instrument}
     */
    public static void upgradeInstrument(ItemStack stack, Holder<Instrument> baseInstrument) {
        ResourceKey<Instrument> instrument = getUpgradedInstrument(baseInstrument.unwrapKey().get()).orElse(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN);
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putString("instrument", instrument.location().toString());
    }

    /**
     * Get the {@link TagKey<Instrument> Copper Horn Instrument Tag Key}
     *
     * @param player {@link Player The Player} using the {@link BLItems#COPPER_HORN Copper Horn}
     * @return {@link TagKey<Instrument> Copper Horn Instrument Tag Key}
     */
    private TagKey<Instrument> getInstrumentTagKey(Player player) {
        return player.isShiftKeyDown() ? BLTags.Instruments.BASS_COPPER_HORNS :
                player.getXRot() == -90F ? BLTags.Instruments.HARMONY_COPPER_HORNS : BLTags.Instruments.MELODY_COPPER_HORNS;
    }

    /**
     * Get the {@link InstrumentItem Item Stack} {@link Instrument Instrument}
     *
     * @param stack {@link ItemStack Instrument Item Stack}
     * @param instrumentTagKey {@link TagKey<Instrument> Instrument Tag Key}
     * @return {@link Holder<Instrument> Instrument}
     */
    public static Optional<Holder<Instrument>> getInstrument(ItemStack stack, TagKey<Instrument> instrumentTagKey) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null) {
            ResourceLocation resourcelocation = ResourceLocation.tryParse(compoundtag.getString("instrument"));
            if (resourcelocation != null) {
                resourcelocation = getInstrumentResourceLocation(resourcelocation, instrumentTagKey);
                return Registry.INSTRUMENT.getHolder(ResourceKey.create(Registry.INSTRUMENT_REGISTRY, resourcelocation));
            }
        }

        Iterator<Holder<Instrument>> iterator = Registry.INSTRUMENT.getTagOrEmpty(instrumentTagKey).iterator();
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
    private static ResourceLocation getInstrumentResourceLocation(ResourceLocation resourceLocation, TagKey<Instrument> instrumentTagKey) {
        if(instrumentTagKey.equals(BLTags.Instruments.MELODY_COPPER_HORNS) || instrumentTagKey.equals(InstrumentTags.GOAT_HORNS)
                || instrumentTagKey.equals(InstrumentTags.REGULAR_GOAT_HORNS)  || instrumentTagKey.equals(InstrumentTags.SCREAMING_GOAT_HORNS)) {
            return resourceLocation;
        }
        return new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_" + (instrumentTagKey.equals(BLTags.Instruments.BASS_COPPER_HORNS) ? "bass" : "harmony"));
    }

    /**
     * Play the {@link Instrument Instrument} {@link net.minecraft.sounds.SoundEvent Sound}
     *
     * @param level {@link Level World reference}
     * @param player {@link Player The Player} using the {@link BLItems#COPPER_HORN Copper Horn}
     * @param instrument {@link Instrument Instrument}
     */
    private static void play(Level level, Player player, Instrument instrument) {
        level.playSound(player, player, instrument.soundEvent(), SoundSource.RECORDS, instrument.range() / 16.0F, 1.0F);
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
    }

    /**
     * Get the Upgraded {@link Instrument Instrument}
     * based on the base {@link Instrument Instrument}
     *
     * @param baseInstrument Base {@link Instrument Instrument}
     * @return Upgraded {@link Instrument Instrument}
     */
    private static Optional<ResourceKey<Instrument>> getUpgradedInstrument(ResourceKey<Instrument> baseInstrument) {
        return Optional.ofNullable(UPGRADABLE_INSTRUMENTS.get().get(baseInstrument));
    }
}