package org.blazers.core;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import org.blazers.BlazersMod;
import org.hendrix.helper.RegistryKeyHelper;

import java.util.EnumMap;

/**
 * {@link BlazersMod Blazers Mod} {@link ArmorMaterial Armor Materials}
 */
public final class BLArmorMaterials {

    //#region Armor Materials

    public static final ArmorMaterial EMERALD = gemArmorMaterial("emerald", BLTags.ItemTags.REPAIRS_EMERALD_ARMOR);
    public static final ArmorMaterial SAPPHIRE = gemArmorMaterial("sapphire", BLTags.ItemTags.REPAIRS_SAPPHIRE_ARMOR);
    public static final ArmorMaterial RUBY = gemArmorMaterial("ruby", BLTags.ItemTags.REPAIRS_RUBY_ARMOR);
    public static final ArmorMaterial TOPAZ = rockArmorMaterial("topaz", BLTags.ItemTags.REPAIRS_TOPAZ_ARMOR);
    public static final ArmorMaterial MALACHITE = rockArmorMaterial("malachite", BLTags.ItemTags.REPAIRS_MALACHITE_ARMOR);
    public static final ArmorMaterial ONICE = rockArmorMaterial("onice", BLTags.ItemTags.REPAIRS_ONICE_ARMOR);
    public static final ArmorMaterial AMETHYST = crystalArmorMaterial("amethyst", RegistryEntry.of(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME), BLTags.ItemTags.REPAIRS_AMETHYST_ARMOR);
    public static final ArmorMaterial PEARL = crystalArmorMaterial("pearl", RegistryEntry.of(SoundEvents.BLOCK_BONE_BLOCK_BREAK), BLTags.ItemTags.REPAIRS_PEARL_ARMOR);
    public static final ArmorMaterial BLAZERITE = powerfulArmorMaterial("blazerite", RegistryEntry.of(SoundEvents.ITEM_FIRECHARGE_USE), BLTags.ItemTags.REPAIRS_BLAZERITE_ARMOR);
    public static final ArmorMaterial GYULIANITE = powerfulArmorMaterial("gyulianite", RegistryEntry.of(SoundEvents.BLOCK_NETHERRACK_HIT), BLTags.ItemTags.REPAIRS_GYULIANITE_ARMOR);

    //#endregion

    /**
     * Get an {@link ArmorMaterial Armor Material} for a {@link Item gem-like Item}
     *
     * @param name The {@link String armor name}
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ArmorMaterial Armor Material}
     */
    private static ArmorMaterial gemArmorMaterial(final String name, final TagKey<Item> repairItemsTag) {
        return new ArmorMaterial(35,
                Util.make(new EnumMap<>(EquipmentType.class), (map) -> {
                    map.put(EquipmentType.BOOTS, 3);
                    map.put(EquipmentType.LEGGINGS, 6);
                    map.put(EquipmentType.CHESTPLATE, 8);
                    map.put(EquipmentType.HELMET, 3);
                    map.put(EquipmentType.BODY, 13);
                }),
                13,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                2.5F,
                0F,
                repairItemsTag,
                RegistryKeyHelper.equipment(name)
        );
    }

    /**
     * Get an {@link ArmorMaterial Armor Material} for a {@link Item rock-like Item}
     *
     * @param name The {@link String armor name}
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ArmorMaterial Armor Material}
     */
    private static ArmorMaterial rockArmorMaterial(final String name, final TagKey<Item> repairItemsTag) {
        return new ArmorMaterial(24,
                Util.make(new EnumMap<>(EquipmentType.class), (map) -> {
                    map.put(EquipmentType.BOOTS, 2);
                    map.put(EquipmentType.LEGGINGS, 6);
                    map.put(EquipmentType.CHESTPLATE, 7);
                    map.put(EquipmentType.HELMET, 2);
                    map.put(EquipmentType.BODY, 8);
                }),
                9,
                SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                1.0F,
                0F,
                repairItemsTag,
                RegistryKeyHelper.equipment(name)
        );
    }

    /**
     * Get an {@link ArmorMaterial Armor Material} for a {@link Item crystal-like Item}
     *
     * @param name The {@link String armor name}
     * @param equipSound The {@link RegistryEntry<SoundEvent> Armor Equip Sound}
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ArmorMaterial Armor Material}
     */
    private static ArmorMaterial crystalArmorMaterial(final String name, final RegistryEntry<SoundEvent> equipSound, final TagKey<Item> repairItemsTag) {
        return new ArmorMaterial(10,
                Util.make(new EnumMap<>(EquipmentType.class), (map) -> {
                    map.put(EquipmentType.BOOTS, 2);
                    map.put(EquipmentType.LEGGINGS, 4);
                    map.put(EquipmentType.CHESTPLATE, 4);
                    map.put(EquipmentType.HELMET, 2);
                }),
                12,
                equipSound,
                0F,
                0F,
                repairItemsTag,
                RegistryKeyHelper.equipment(name)
        );
    }

    /**
     * Get an {@link ArmorMaterial Armor Material} for a {@link Item powerful Item}
     *
     * @param name The {@link String armor name}
     * @param equipSound The {@link RegistryEntry<SoundEvent> Armor Equip Sound}
     * @param repairItemsTag The {@link TagKey<Item> Item Tag} containing all {@link Item repair Items} for this material
     * @return The {@link ArmorMaterial Armor Material}
     */
    private static ArmorMaterial powerfulArmorMaterial(final String name, final RegistryEntry<SoundEvent> equipSound, final TagKey<Item> repairItemsTag) {
        return new ArmorMaterial(39,
                Util.make(new EnumMap<>(EquipmentType.class), (map) -> {
                    map.put(EquipmentType.BOOTS, 3);
                    map.put(EquipmentType.LEGGINGS, 6);
                    map.put(EquipmentType.CHESTPLATE, 8);
                    map.put(EquipmentType.HELMET, 3);
                }),
                18,
                equipSound,
                4.0F,
                0.2F,
                repairItemsTag,
                RegistryKeyHelper.equipment(name)
        );
    }

}