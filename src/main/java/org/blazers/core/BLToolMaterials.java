package org.blazers.core;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum BLToolMaterials implements ToolMaterial {

    RUBY(3, 1796, 8.5F, 3.5F, 13, () -> Ingredient.ofItems(BLItems.RUBY)),
    SAPPHIRE(3, 1796, 8.5F, 3.5F, 13, () -> Ingredient.ofItems(BLItems.SAPPHIRE)),
    EMERALD(3, 1796, 8.5F, 3.5F, 13, () -> Ingredient.ofItems(Items.EMERALD)),
    MALACHITE(2, 905, 7.0F, 2.5F, 12,  () -> Ingredient.ofItems(BLItems.MALACHITE)),
    ONICE(2, 905, 7.0F, 2.5F, 12,  () -> Ingredient.ofItems(BLItems.ONICE)),
    TOPAZ(2, 905, 7.0F, 2.5F, 12,  () -> Ingredient.ofItems(BLItems.TOPAZ)),
    PEARL(1, 191, 5.0F, 1.5F, 12,  () -> Ingredient.ofItems(BLItems.PEARL)),
    AMETHYST(1, 191, 5.0F, 1.5F, 12,  () -> Ingredient.ofItems(Items.AMETHYST_SHARD)),
    BLAZERITE(4, 2266, 10.0F, 5.0F, 20, () -> Ingredient.ofItems(BLItems.BLAZERITE)),
    GYULIANITE(4, 2266, 10.0F, 5.0F, 20, () -> Ingredient.ofItems(BLItems.GYULIANITE)),
    FLINT(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.ofItems(Items.FLINT)),
    CARBON(2, 250, 8.0F, 2.0F, 14, () -> Ingredient.ofItems(BLItems.CARBON));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    BLToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}