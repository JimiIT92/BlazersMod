package org.blazers.core;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum BLArmorMaterials implements ArmorMaterial {

    RUBY("ruby", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.ofItems(BLItems.RUBY)),
    SAPPHIRE("sapphire", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.ofItems(BLItems.SAPPHIRE)),
    EMERALD("emerald", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.ofItems(Items.EMERALD)),
    ONICE("onice", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.ofItems(BLItems.ONICE)),
    MALACHITE("malachite", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.ofItems(BLItems.MALACHITE)),
    TOPAZ("topaz", 24, new int[]{2, 6, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.ofItems(BLItems.TOPAZ)),
    PEARL("pearl", 10, new int[]{2, 4, 4, 2}, 12, SoundEvents.BLOCK_BONE_BLOCK_BREAK, 0.0F, 0.0F, () -> Ingredient.ofItems(BLItems.PEARL)),
    AMETHYST("amethyst", 10, new int[]{2, 4, 4, 2}, 12, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, 0.0F, 0.0F, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)),
    BLAZERITE("blazerite", 39, new int[]{3, 6, 8, 3}, 18, SoundEvents.ITEM_FIRECHARGE_USE, 4.0F, 0.2F, () -> Ingredient.ofItems(BLItems.BLAZERITE)),
    GYULIANITE("gyulianite", 39, new int[]{3, 6, 8, 3}, 18, SoundEvents.BLOCK_NETHERRACK_HIT, 4.0F, 0.2F, () -> Ingredient.ofItems(BLItems.GYULIANITE));

    private static final int[] BASE_DURABILITY;
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;

    BLArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    static {
        BASE_DURABILITY = new int[]{13, 15, 16, 11};
    }
}
