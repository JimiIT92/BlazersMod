package org.blazers.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.InstrumentTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.blazers.core.BLInstruments;
import org.blazers.core.BLItems;
import org.blazers.core.BLTabs;
import org.blazers.core.BLTags;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

public class CopperHornItem extends GoatHornItem {

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

    public CopperHornItem() {
        super(new FabricItemSettings().group(BLTabs.TAB_MISC).maxCount(1), BLTags.Instruments.COPPER_HORNS);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if(isIn(group)) {
            for (RegistryEntry<Instrument> registryEntry : Registry.INSTRUMENT.iterateEntries(BLTags.Instruments.MELODY_COPPER_HORNS)) {
                stacks.add(GoatHornItem.getStackForInstrument(BLItems.COPPER_HORN, registryEntry));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        Optional<RegistryEntry<Instrument>> optional = getInstrument(itemStack, getInstrumentTagKey(user));
        if (optional.isPresent()) {
            Instrument instrument = optional.get().value();
            user.setCurrentHand(hand);
            play(world, user, instrument);
            user.getItemCooldownManager().set(this, instrument.useDuration());
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    private TagKey<Instrument> getInstrumentTagKey(PlayerEntity player) {
        return player.isSneaking() ? BLTags.Instruments.BASS_COPPER_HORNS :
                player.getRotationClient().x == -90F ? BLTags.Instruments.HARMONY_COPPER_HORNS : BLTags.Instruments.MELODY_COPPER_HORNS;
    }

    public static Optional<RegistryEntry<Instrument>> getInstrument(ItemStack stack, TagKey<Instrument> instrumentTagKey) {
        NbtCompound compoundtag = stack.getNbt();
        if (compoundtag != null) {
            Identifier identifier = Identifier.tryParse(compoundtag.getString("instrument"));
            if (identifier != null) {
                identifier = getInstrumentIdentifier(identifier, instrumentTagKey);
                return Registry.INSTRUMENT.getEntry(RegistryKey.of(Registry.INSTRUMENT_KEY, identifier));
            }
        }

        Iterator<RegistryEntry<Instrument>> iterator = Registry.INSTRUMENT.iterateEntries(instrumentTagKey).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    private static Identifier getInstrumentIdentifier(Identifier resourceLocation, TagKey<Instrument> instrumentTagKey) {
        if(instrumentTagKey.equals(BLTags.Instruments.MELODY_COPPER_HORNS) || instrumentTagKey.equals(InstrumentTags.GOAT_HORNS)
                || instrumentTagKey.equals(InstrumentTags.REGULAR_GOAT_HORNS)  || instrumentTagKey.equals(InstrumentTags.SCREAMING_GOAT_HORNS)) {
            return resourceLocation;
        }
        return new Identifier(resourceLocation.getNamespace(), resourceLocation.getPath() + "_" + (instrumentTagKey.equals(BLTags.Instruments.BASS_COPPER_HORNS) ? "bass" : "harmony"));
    }

    private static void play(World world, PlayerEntity player, Instrument instrument) {
        world.playSoundFromEntity(player, player, instrument.soundEvent(), SoundCategory.RECORDS, instrument.range() / 16.0f, 1.0f);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }

    public static void upgradeInstrument(ItemStack stack, RegistryEntry<Instrument> baseInstrument) {
        RegistryKey<Instrument> instrument = getUpgradedInstrument(baseInstrument.getKey().get()).orElse(BLInstruments.GREAT_SKY_FALLING_COPPER_HORN);
        NbtCompound compoundtag = stack.getOrCreateNbt();
        compoundtag.putString("instrument", instrument.getValue().toString());
    }

    private static Optional<RegistryKey<Instrument>> getUpgradedInstrument(RegistryKey<Instrument> baseInstrument) {
        return Optional.ofNullable(UPGRADABLE_INSTRUMENTS.get().get(baseInstrument));
    }
}
