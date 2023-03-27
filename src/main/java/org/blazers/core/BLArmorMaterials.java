package org.blazers.core;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link ArmorMaterial Armor materials}
 */
public enum BLArmorMaterials implements ArmorMaterial {

    //#region Armor Materials

    RUBY("ruby", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(BLItems.RUBY.get())),
    SAPPHIRE("sapphire", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(BLItems.SAPPHIRE.get())),
    EMERALD("emerald", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(Items.EMERALD)),
    ONICE("onice", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(BLItems.ONICE.get())),
    MALACHITE("malachite", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(BLItems.MALACHITE.get())),
    TOPAZ("topaz", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(BLItems.TOPAZ.get())),
    PEARL("pearl", 10, new int[]{2, 4, 4, 2}, 12, SoundEvents.BONE_BLOCK_BREAK, 0.0F, 0.0F, () -> Ingredient.of(BLItems.PEARL.get())),
    AMETHYST("amethyst", 10, new int[]{2, 4, 4, 2}, 12, SoundEvents.AMETHYST_BLOCK_CHIME, 0.0F, 0.0F, () -> Ingredient.of(Items.AMETHYST_SHARD)),
    BLAZERITE("blazerite", 39, new int[]{3, 6, 8, 3}, 18, SoundEvents.FIRECHARGE_USE, 4.0F, 0.2F, () -> Ingredient.of(BLItems.BLAZERITE.get())),
    GYULIANITE("gyulianite", 39, new int[]{3, 6, 8, 3}, 18, SoundEvents.NETHERRACK_HIT, 4.0F, 0.2F, () -> Ingredient.of(BLItems.GYULIANITE.get()));

    //#endregion

    /**
     * Item {@link Integer slots durability}
     */
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    /**
     * {@link String Armor name}
     */
    private final String name;
    /**
     * {@link Integer Durability multiplier}
     */
    private final int durabilityMultiplier;
    /**
     * {@link Integer Slot protection values}
     */
    private final int[] slotProtections;
    /**
     * {@link Integer Enchantability value}
     */
    private final int enchantmentValue;
    /**
     * {@link SoundEvent Armor equip sound effect}
     */
    private final SoundEvent sound;
    /**
     * {@link Float Armor toughness}
     */
    private final float toughness;
    /**
     * {@link Float Armor knockback resistance}
     */
    private final float knockbackResistance;
    /**
     * {@link LazyLoadedValue<Ingredient> Armor repair ingredient}
     */
    private final LazyLoadedValue<Ingredient> repairIngredient;

    /**
     * Constructor. Sets the {@link ArmorMaterial Armor material} properties
     *
     * @param name {@link String Armor name}
     * @param durabilityMultiplier {@link Integer Durability multiplier}
     * @param slotProtections {@link Integer Slot protection values}
     * @param enchantmentValue {@link Integer Enchantability value}
     * @param sound {@link SoundEvent Armor equip sound effect}
     * @param toughness {@link Float Armor toughness}
     * @param knockbackResistance {@link Float Armor knockback resistance}
     * @param repairIngredient {@link LazyLoadedValue<Ingredient> Armor repair ingredient}
     */
    BLArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    /**
     * Get the {@link Integer Slot durability value}
     *
     * @param slot {@link EquipmentSlot Slot}
     * @return {@link Integer Slot durability value}
     */
    public int getDurabilityForType(ArmorItem.Type slot) {
        return HEALTH_PER_SLOT[slot.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    /**
     * Get the {@link Integer Slot protection value}
     *
     * @param slot {@link EquipmentSlot Slot}
     * @return {@link Integer Slot protection value}
     */
    public int getDefenseForType(ArmorItem.Type slot) {
        return this.slotProtections[slot.getSlot().getIndex()];
    }

    /**
     * Get the {@link Integer Armor enchantability value}
     *
     * @return {@link Integer Armor enchantability value}
     */
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    /**
     * Get the {@link SoundEvent Armor equip sound effect}
     *
     * @return {@link SoundEvent Armor equip sound effect}
     */
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    /**
     * Get the {@link Ingredient Armor repair ingredient}
     *
     * @return {@link Ingredient Armor repair ingredient}
     */
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    /**
     * Get the {@link String Armor name}
     *
     * @return {@link String Armor name}
     */
    public @NotNull String getName() {
        return BlazersMod.MOD_ID + ":" + this.name;
    }

    /**
     * Get the {@link Float Armor toughness}
     *
     * @return {@link Float Armor toughness}
     */
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Get the {@link Float Armor knockback resistance}
     *
     * @return {@link Float Armor knockback resistance}
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}