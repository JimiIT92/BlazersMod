package org.blazers.core;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;
import org.blazers.helper.ItemHelper;
import org.blazers.helper.RegistryHelper;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class BLArmorMaterials {

    //#region Registry

    /**
     * The {@link DeferredRegister<ArmorMaterial> Armor Material Registry}
     */
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = RegistryHelper.registry(Registries.ARMOR_MATERIAL);

    //#endregion

    //#region Armor Materials

    public static final Supplier<ArmorMaterial> EMERALD = createArmorMaterial(
            ItemHelper.tierName(BLTiers.EMERALD),
            3,
            6,
            8,
            3,
            13,
            13,
            2.5F,
            0F,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            Suppliers.memoize(() -> Items.EMERALD)
    );
    public static final RegistryObject<ArmorMaterial> AMETHYST = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.AMETHYST),
            2,
            4,
            4,
            2,
            4,
            12,
            0F,
            0F,
            Holder.direct(SoundEvents.AMETHYST_BLOCK_CHIME),
            Suppliers.memoize(() -> Items.AMETHYST_SHARD)
    );
    public static final RegistryObject<ArmorMaterial> SAPPHIRE = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.SAPPHIRE),
            3,
            6,
            8,
            3,
            13,
            13,
            2.5F,
            0F,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            Suppliers.memoize(BLItems.SAPPHIRE::get)
    );
    public static final RegistryObject<ArmorMaterial> TOPAZ = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.TOPAZ),
            2,
            6,
            7,
            2,
            8,
            9,
            1F,
            0F,
            SoundEvents.ARMOR_EQUIP_IRON,
            Suppliers.memoize(BLItems.TOPAZ::get)
    );
    public static final RegistryObject<ArmorMaterial> PEARL = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.PEARL),
            2,
            4,
            4,
            2,
            4,
            12,
            0F,
            0F,
            Holder.direct(SoundEvents.BONE_BLOCK_BREAK),
            Suppliers.memoize(BLItems.PEARL::get)
    );
    public static final RegistryObject<ArmorMaterial> RUBY = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.RUBY),
            3,
            6,
            8,
            3,
            13,
            13,
            2.5F,
            0F,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            Suppliers.memoize(BLItems.RUBY::get)
    );
    public static final RegistryObject<ArmorMaterial> ONICE = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.ONICE),
            2,
            6,
            7,
            2,
            7,
            9,
            1F,
            0F,
            SoundEvents.ARMOR_EQUIP_IRON,
            Suppliers.memoize(BLItems.ONICE::get)
    );
    public static final RegistryObject<ArmorMaterial> MALACHITE = registerArmorMaterial(
            ItemHelper.tierName(BLTiers.MALACHITE),
            2,
            6,
            7,
            2,
            8,
            9,
            1F,
            0F,
            SoundEvents.ARMOR_EQUIP_IRON,
            Suppliers.memoize(BLItems.MALACHITE::get)
    );

    //#endregion

    //#region Methods

    /**
     * Register an {@link ArmorMaterial Armor Material}
     *
     * @param name {@link String The armor material name}
     * @param bootDefence {@link Integer The defence amount of boots}
     * @param leggingsDefence {@link Integer The defence amount of leggings}
     * @param chestplateDefence {@link Integer The defence amount of chestplates}
     * @param helmetDefence {@link Integer The defence amount of helmets}
     * @param bodyDefence {@link Integer The defence amount of armor when equipped on the body, like horse armors}
     * @param enchantmentValue {@link Integer The armor enchantability value}
     * @param armorThoughness {@link Integer The armor thoughness}
     * @param knockbackResistance {@link Integer The armor knockback resistance}
     * @param equipSound {@link Holder<SoundEvent> The armor equip sound}
     * @param repairItemSupplier {@link Supplier<Item> The supplier for the Item used to repair the armor}
     * @return {@link RegistryObject<ArmorMaterial> The registered Armor Material}
     */
    private static RegistryObject<ArmorMaterial> registerArmorMaterial(final String name, final int bootDefence, final int leggingsDefence, final int chestplateDefence, final int helmetDefence, final int bodyDefence, final int enchantmentValue, final float armorThoughness, final float knockbackResistance, final Holder<SoundEvent> equipSound, final Supplier<Item> repairItemSupplier) {
        return ARMOR_MATERIALS.register(name, createArmorMaterial(name, bootDefence, leggingsDefence, chestplateDefence, helmetDefence, bodyDefence, enchantmentValue, armorThoughness, knockbackResistance, equipSound, repairItemSupplier));
    }

    /**
     * Register an {@link ArmorMaterial Armor Material}
     *
     * @param name {@link String The armor material name}
     * @param bootDefence {@link Integer The defence amount of boots}
     * @param leggingsDefence {@link Integer The defence amount of leggings}
     * @param chestplateDefence {@link Integer The defence amount of chestplates}
     * @param helmetDefence {@link Integer The defence amount of helmets}
     * @param bodyDefence {@link Integer The defence amount of armor when equipped on the body, like horse armors}
     * @param enchantmentValue {@link Integer The armor enchantability value}
     * @param armorThoughness {@link Integer The armor thoughness}
     * @param knockbackResistance {@link Integer The armor knockback resistance}
     * @param equipSound {@link Holder<SoundEvent> The armor equip sound}
     * @param repairItemSupplier {@link Supplier<Item> The supplier for the Item used to repair the armor}
     * @return {@link RegistryObject<ArmorMaterial> The registered Armor Material}
     */
    private static Supplier<ArmorMaterial> createArmorMaterial(final String name, final int bootDefence, final int leggingsDefence, final int chestplateDefence, final int helmetDefence, final int bodyDefence, final int enchantmentValue, final float armorThoughness, final float knockbackResistance, final Holder<SoundEvent> equipSound, final Supplier<Item> repairItemSupplier) {
        return Suppliers.memoize(() -> new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, bootDefence);
                    map.put(ArmorItem.Type.LEGGINGS, leggingsDefence);
                    map.put(ArmorItem.Type.CHESTPLATE, chestplateDefence);
                    map.put(ArmorItem.Type.HELMET, helmetDefence);
                    map.put(ArmorItem.Type.BODY, bodyDefence);
                }),
                enchantmentValue,
                equipSound,
                Suppliers.memoize(() -> Ingredient.of(repairItemSupplier.get())),
                List.of(new ArmorMaterial.Layer(RegistryHelper.location(name), "", true)),
                armorThoughness,
                knockbackResistance
        ));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlazersMod Blazers Mod} {@link ArmorMaterial Armor Materials}
     *
     * @param eventBus {@link IEventBus The Forge Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }

    //#endregion

}
