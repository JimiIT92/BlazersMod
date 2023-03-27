package org.blazers.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.blazers.BlazersMod;
import org.blazers.item.*;

import java.util.*;

import static net.minecraft.item.ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS;

public final class BLItems {

    private static final HashMap<ItemGroup, List<ItemStack>> itemGroups = new HashMap<>();

    public static final Item RAW_URANIUM = registerSimpleItem("raw_uranium");
    public static final Item SAPPHIRE = registerSimpleItem("sapphire");
    public static final Item TOPAZ = registerSimpleItem("topaz");
    public static final Item PEARL = registerSimpleItem("pearl");
    public static final Item RUBY = registerSimpleItem("ruby");
    public static final Item MALACHITE = registerSimpleItem("malachite");
    public static final Item ONICE = registerSimpleItem("onice");
    public static final Item BLAZERITE = registerRareItem("blazerite");
    public static final Item GYULIANITE = registerRareItem("gyulianite");
    public static final Item URANIUM_NUGGET = registerSimpleItem("uranium_nugget");
    public static final Item URANIUM_INGOT = registerSimpleItem("uranium_ingot");
    public static final Item CARBON = registerSimpleItem("carbon");

    public static final Item HOSOMAKI = registerFood("hosomaki", BLFoods.HOSOMAKI);
    public static final Item NIGIRI = registerFood("nigiri", BLFoods.NIGIRI);
    public static final Item SASHIMI = registerFood("sashimi", BLFoods.SASHIMI);

    public static final Item EMERALD_SWORD = registerSword("emerald_sword", BLToolMaterials.EMERALD);
    public static final Item EMERALD_SHOVEL = registerShovel("emerald_shovel", BLToolMaterials.EMERALD);
    public static final Item EMERALD_PICKAXE = registerPickaxe("emerald_pickaxe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_AXE = registerAxe("emerald_axe", BLToolMaterials.EMERALD);
    public static final Item EMERALD_HOE = registerHoe("emerald_hoe", BLToolMaterials.EMERALD);
    public static final Item AMETHYST_SWORD = registerSword("amethyst_sword", BLToolMaterials.AMETHYST);
    public static final Item SAPPHIRE_SWORD = registerSword("sapphire_sword", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_SHOVEL = registerShovel("sapphire_shovel", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_PICKAXE = registerPickaxe("sapphire_pickaxe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_AXE = registerAxe("sapphire_axe", BLToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_HOE = registerHoe("sapphire_hoe", BLToolMaterials.SAPPHIRE);
    public static final Item TOPAZ_HAMMER = registerSword("topaz_hammer", BLToolMaterials.TOPAZ, 5, -3.0F);
    public static final Item PEARL_SWORD = registerSword("pearl_sword", BLToolMaterials.PEARL);
    public static final Item RUBY_SWORD = registerSword("ruby_sword", BLToolMaterials.RUBY);
    public static final Item RUBY_SHOVEL = registerShovel("ruby_shovel", BLToolMaterials.RUBY);
    public static final Item RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", BLToolMaterials.RUBY);
    public static final Item RUBY_AXE = registerAxe("ruby_axe", BLToolMaterials.RUBY);
    public static final Item RUBY_HOE = registerHoe("ruby_hoe", BLToolMaterials.RUBY);
    public static final Item ONICE_SHOVEL = registerShovel("onice_shovel", BLToolMaterials.ONICE);
    public static final Item ONICE_PICKAXE = registerPickaxe("onice_pickaxe", BLToolMaterials.ONICE);
    public static final Item ONICE_AXE = registerAxe("onice_axe", BLToolMaterials.ONICE, 5.5F, -3.0F);
    public static final Item ONICE_HOE = registerHoe("onice_hoe", BLToolMaterials.ONICE, -2, -0.5F);
    public static final Item ONICE_SICKLE = registerSword("onice_sickle", BLToolMaterials.ONICE);

    public static final Item SPEAR = registerSpearItem("spear", BLToolMaterials.FLINT, 1);
    public static final Item MALACHITE_SPEAR = registerSpearItem("malachite_spear", BLToolMaterials.MALACHITE, 3);

    public static final Item BLAZERITE_SWORD = registerItem("blazerite_sword",
            new PreEnchantedSwordItem(BLToolMaterials.BLAZERITE, Enchantments.FIRE_ASPECT, 10), BLTabs.COMBAT);
    public static final Item GYULIANITE_SWORD = registerItem("gyulianite_sword",
            new PreEnchantedSwordItem(BLToolMaterials.GYULIANITE, Enchantments.KNOCKBACK, 10), BLTabs.COMBAT);

    public static final Item KATANA = registerKatana("katana");
    public static final Item WHITE_KATANA = registerKatana("white_katana");
    public static final Item ORANGE_KATANA = registerKatana("orange_katana");
    public static final Item MAGENTA_KATANA = registerKatana("magenta_katana");
    public static final Item LIGHT_BLUE_KATANA = registerKatana("light_blue_katana");
    public static final Item YELLOW_KATANA = registerKatana("yellow_katana");
    public static final Item LIME_KATANA = registerKatana("lime_katana");
    public static final Item PINK_KATANA = registerKatana("pink_katana");
    public static final Item GRAY_KATANA = registerKatana("gray_katana");
    public static final Item LIGHT_GRAY_KATANA = registerKatana("light_gray_katana");
    public static final Item CYAN_KATANA = registerKatana("cyan_katana");
    public static final Item PURPLE_KATANA = registerKatana("purple_katana");
    public static final Item BLUE_KATANA = registerKatana("blue_katana");
    public static final Item BROWN_KATANA = registerKatana("brown_katana");
    public static final Item GREEN_KATANA = registerKatana("green_katana");
    public static final Item RED_KATANA = registerKatana("red_katana");
    public static final Item BLACK_KATANA = registerKatana("black_katana");

    public static final Item EMERALD_HELMET = registerArmorItem("emerald_helmet", BLArmorMaterials.EMERALD, ArmorItem.Type.HELMET);
    public static final Item EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", BLArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE);
    public static final Item EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", BLArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS);
    public static final Item EMERALD_BOOTS = registerArmorItem("emerald_boots", BLArmorMaterials.EMERALD, ArmorItem.Type.BOOTS);
    public static final Item AMETHYST_HELMET = registerArmorItem("amethyst_helmet", BLArmorMaterials.AMETHYST, ArmorItem.Type.HELMET);
    public static final Item AMETHYST_CHESTPLATE = registerArmorItem("amethyst_chestplate", BLArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE);
    public static final Item AMETHYST_LEGGINGS = registerArmorItem("amethyst_leggings", BLArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS);
    public static final Item AMETHYST_BOOTS = registerArmorItem("amethyst_boots", BLArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS);
    public static final Item SAPPHIRE_HELMET = registerArmorItem("sapphire_helmet", BLArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET);
    public static final Item SAPPHIRE_CHESTPLATE = registerArmorItem("sapphire_chestplate", BLArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE);
    public static final Item SAPPHIRE_LEGGINGS = registerArmorItem("sapphire_leggings", BLArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS);
    public static final Item SAPPHIRE_BOOTS = registerArmorItem("sapphire_boots", BLArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS);
    public static final Item TOPAZ_HELMET = registerArmorItem("topaz_helmet", BLArmorMaterials.TOPAZ, ArmorItem.Type.HELMET);
    public static final Item TOPAZ_CHESTPLATE = registerArmorItem("topaz_chestplate", BLArmorMaterials.TOPAZ, ArmorItem.Type.CHESTPLATE);
    public static final Item TOPAZ_LEGGINGS = registerArmorItem("topaz_leggings", BLArmorMaterials.TOPAZ, ArmorItem.Type.LEGGINGS);
    public static final Item TOPAZ_BOOTS = registerArmorItem("topaz_boots", BLArmorMaterials.TOPAZ, ArmorItem.Type.BOOTS);
    public static final Item PEARL_HELMET = registerArmorItem("pearl_helmet", BLArmorMaterials.PEARL, ArmorItem.Type.HELMET);
    public static final Item PEARL_CHESTPLATE = registerArmorItem("pearl_chestplate", BLArmorMaterials.PEARL, ArmorItem.Type.CHESTPLATE);
    public static final Item PEARL_LEGGINGS = registerArmorItem("pearl_leggings", BLArmorMaterials.PEARL, ArmorItem.Type.LEGGINGS);
    public static final Item PEARL_BOOTS = registerArmorItem("pearl_boots", BLArmorMaterials.PEARL, ArmorItem.Type.BOOTS);
    public static final Item RUBY_HELMET = registerArmorItem("ruby_helmet", BLArmorMaterials.RUBY, ArmorItem.Type.HELMET);
    public static final Item RUBY_CHESTPLATE = registerArmorItem("ruby_chestplate", BLArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE);
    public static final Item RUBY_LEGGINGS = registerArmorItem("ruby_leggings", BLArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS);
    public static final Item RUBY_BOOTS = registerArmorItem("ruby_boots", BLArmorMaterials.RUBY, ArmorItem.Type.BOOTS);
    public static final Item MALACHITE_HELMET = registerArmorItem("malachite_helmet", BLArmorMaterials.MALACHITE, ArmorItem.Type.HELMET);
    public static final Item MALACHITE_CHESTPLATE = registerArmorItem("malachite_chestplate", BLArmorMaterials.MALACHITE, ArmorItem.Type.CHESTPLATE);
    public static final Item MALACHITE_LEGGINGS = registerArmorItem("malachite_leggings", BLArmorMaterials.MALACHITE, ArmorItem.Type.LEGGINGS);
    public static final Item MALACHITE_BOOTS = registerArmorItem("malachite_boots", BLArmorMaterials.MALACHITE, ArmorItem.Type.BOOTS);
    public static final Item ONICE_HELMET = registerArmorItem("onice_helmet", BLArmorMaterials.ONICE, ArmorItem.Type.HELMET);
    public static final Item ONICE_CHESTPLATE = registerArmorItem("onice_chestplate", BLArmorMaterials.ONICE, ArmorItem.Type.CHESTPLATE);
    public static final Item ONICE_LEGGINGS = registerArmorItem("onice_leggings", BLArmorMaterials.ONICE, ArmorItem.Type.LEGGINGS);
    public static final Item ONICE_BOOTS = registerArmorItem("onice_boots", BLArmorMaterials.ONICE, ArmorItem.Type.BOOTS);

    public static final Item BLAZERITE_HELMET = registerItem("blazerite_helmet", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  ArmorItem.Type.HELMET, Enchantments.FIRE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item BLAZERITE_CHESTPLATE = registerItem("blazerite_chestplate", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  ArmorItem.Type.CHESTPLATE, Enchantments.FIRE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item BLAZERITE_LEGGINGS = registerItem("blazerite_leggings", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  ArmorItem.Type.LEGGINGS, Enchantments.FIRE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item BLAZERITE_BOOTS = registerItem("blazerite_boots", new PreEnchantedArmorItem(BLArmorMaterials.BLAZERITE,  ArmorItem.Type.BOOTS, Enchantments.FIRE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item GYULIANITE_HELMET = registerItem("gyulianite_helmet", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  ArmorItem.Type.HELMET, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item GYULIANITE_CHESTPLATE = registerItem("gyulianite_chestplate", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  ArmorItem.Type.CHESTPLATE, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item GYULIANITE_LEGGINGS = registerItem("gyulianite_leggings", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  ArmorItem.Type.LEGGINGS, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.COMBAT);
    public static final Item GYULIANITE_BOOTS = registerItem("gyulianite_boots", new PreEnchantedArmorItem(BLArmorMaterials.GYULIANITE,  ArmorItem.Type.BOOTS, Enchantments.PROJECTILE_PROTECTION, 4), BLTabs.COMBAT);

    public static final Item EMERALD_HORSE_ARMOR = registerHorseArmorItem("emerald_horse_armor",13, "emerald");
    public static final Item SAPPHIRE_HORSE_ARMOR = registerHorseArmorItem("sapphire_horse_armor",13, "sapphire");
    public static final Item TOPAZ_HORSE_ARMOR = registerHorseArmorItem("topaz_horse_armor",8, "topaz");
    public static final Item RUBY_HORSE_ARMOR = registerHorseArmorItem("ruby_horse_armor",13, "ruby");
    public static final Item MALACHITE_HORSE_ARMOR = registerHorseArmorItem("malachite_horse_armor",8, "malachite");

    public static final Item CARBON_BOW = registerItem("carbon_bow", new CarbonBowItem(), BLTabs.COMBAT);

    public static final Item WITHER_SKELETON_HORSE_SPAWN_EGG = registerSpawnEgg("wither_skeleton_horse_spawn_egg", BLEntityTypes.WITHER_SKELETON_HORSE, 4672845, 1315860);
    public static final Item FIREFLY_SPAWN_EGG = registerSpawnEgg("firefly_spawn_egg", BLEntityTypes.FIREFLY, 0x0A0A0A, 0xF0C43E);
    public static final Item COPPER_GOLEM_SPAWN_EGG = registerSpawnEgg("copper_golem_spawn_egg", BLEntityTypes.COPPER_GOLEM, 0xCC6600, 0x00CC99);

    public static final Item MUSIC_DISC_SURVIVAL = registerMusicDisc("music_disc_survival", BLSounds.MUSIC_DISC_SURVIVAL, 28);
    public static final Item MUSIC_DISC_ENDERMAN_VS_BLAZE = registerMusicDisc("music_disc_enderman_vs_blaze", BLSounds.MUSIC_DISC_ENDERMAN_VS_BLAZE, 173);

    public static final Item COPPER_HORN = registerItem("copper_horn", new CopperHornItem(), BLTabs.TOOLS);

    private static FabricItemSettings createSimpleItemSettings() {
        return new FabricItemSettings();
    }

    private static Item registerFood(String name, FoodComponent foodProperties) {
        return registerItem(name, new Item(createSimpleItemSettings().food(foodProperties)), BLTabs.FOOD_AND_DRINK);
    }

    private static Item registerShovel(String name, ToolMaterial toolMaterial) {
        return registerItem(name, new ShovelItem(toolMaterial, 1.5F, -3.0F, createSimpleItemSettings()), BLTabs.TOOLS);
    }

    private static Item registerPickaxe(String name, ToolMaterial toolMaterial) {
        return registerItem(name, new BLPickaxeItem(toolMaterial), BLTabs.TOOLS);
    }

    private static Item registerAxe(String name, ToolMaterial toolMaterial) {
        return registerAxe(name, toolMaterial, 5.0F, -3.0F);
    }

    private static Item registerAxe(String name, ToolMaterial toolMaterial, float attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, new BLAxeItem(toolMaterial, attackDamageModifier, attackSpeedModifier), BLTabs.TOOLS);
    }

    private static Item registerHoe(String name, ToolMaterial toolMaterial) {
        return registerHoe(name, toolMaterial, -3, 0F);
    }

    private static Item registerHoe(String name, ToolMaterial toolMaterial, int attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, new BLHoeItem(toolMaterial, attackDamageModifier, attackSpeedModifier), BLTabs.TOOLS);
    }

    private static Item registerKatana(String name) {
        return registerSword(name, BLToolMaterials.CARBON, 3, 0);
    }

    private static Item registerSword(String name, ToolMaterial toolMaterial) {
        return registerSword(name, toolMaterial, 3, -2.4F);
    }

    private static Item registerSword(String name, ToolMaterial toolMaterial, int attackDamageModifier, float attackSpeedModifier) {
        return registerItem(name, new SwordItem(toolMaterial, attackDamageModifier, attackSpeedModifier, createSimpleItemSettings()), BLTabs.COMBAT);
    }

    private static Item registerArmorItem(String name, ArmorMaterial armorMaterial, ArmorItem.Type slot) {
        return registerItem(name, new ArmorItem(armorMaterial, slot, createSimpleItemSettings()), BLTabs.COMBAT);
    }

    private static Item registerHorseArmorItem(String name, int protection, String armorName) {
        return registerItem(name, new BLHorseArmorItem(protection, armorName), BLTabs.COMBAT);
    }

    private static Item registerSpearItem(String name, ToolMaterial toolMaterial, int attackDamage) {
        return registerItem(name, new SpearItem(toolMaterial, attackDamage, -2.4F), BLTabs.COMBAT);
    }

    private static Item registerMusicDisc(String name, SoundEvent sound, int length) {
        return registerItem(name, new BLMusicDiscItem(sound, length), BLTabs.TOOLS);
    }

    private static Item registerSpawnEgg(String name, EntityType entity, int primaryColor, int secondaryColor) {
        return registerItem(name, new SpawnEggItem(entity, primaryColor, secondaryColor, new FabricItemSettings().maxCount(1)), BLTabs.SPAWN_EGGS);
    }

    private static Item registerRareItem(final String name) {
        return registerItem(name, new Item(createSimpleItemSettings().rarity(Rarity.RARE)), BLTabs.INGREDIENTS);
    }

    private static Item registerSimpleItem(final String name) {
        return registerItem(name, new Item(createSimpleItemSettings()), BLTabs.INGREDIENTS);
    }

    public static Item registerItem(final String name, final Item item, final ItemGroup... tabs) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(BlazersMod.MOD_ID, name), item);
        if(!(item instanceof CopperHornItem)) {
            if(tabs != null) {
                Arrays.stream(tabs).forEach(tab -> {
                    if(!itemGroups.containsKey(tab)) {
                        itemGroups.put(tab, new ArrayList<>());
                    }
                    itemGroups.get(tab).add(registeredItem.getDefaultStack());
                });
            }
        }
        return registeredItem;
    }

    public static void addItemsToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.getSearchTabStacks().removeIf(BLItems::isEblPainting);
            entries.getDisplayStacks().removeIf(BLItems::isEblPainting);
        });
        itemGroups.forEach((tab, items) -> ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAll(items)));
        ItemGroupEvents.modifyEntriesEvent(BLTabs.TOOLS).register(entries -> {
            for (RegistryEntry<Instrument> registryEntry : Registries.INSTRUMENT.iterateEntries(BLTags.Instruments.MELODY_COPPER_HORNS)) {
                entries.add(CopperHornItem.getStackForInstrument(COPPER_HORN, registryEntry));
            }
        });
        ItemGroupEvents.modifyEntriesEvent(BLTabs.FUNCTIONAL).register(entries -> {
            for (RegistryEntry<PaintingVariant> registryEntry : Registries.PAINTING_VARIANT.iterateEntries(BLTags.Paintings.EBL_PAINTINGS)) {
                ItemStack itemStack = new ItemStack(Items.PAINTING);
                NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("EntityTag");
                PaintingEntity.writeVariantToNbt(nbtCompound, registryEntry);
                entries.add(itemStack, PARENT_AND_SEARCH_TABS);
            }
        });
    }

    private static boolean isEblPainting(ItemStack stack) {
        if(stack.isOf(Items.PAINTING) && stack.getSubNbt("EntityTag") != null) {
            Optional<RegistryEntry.Reference<PaintingVariant>> optionalPaintingVariant = Registries.PAINTING_VARIANT.getEntry(RegistryKey.of(Registries.PAINTING_VARIANT.getKey(), Identifier.tryParse(stack.getSubNbt("EntityTag").getString("variant"))));
            return optionalPaintingVariant.map(paintingVariantReference -> paintingVariantReference.isIn(BLTags.Paintings.EBL_PAINTINGS)).orElse(false);
        }
        return false;
    }

    public static void register() {
        BlazersMod.LOGGER.info("Registering " + BlazersMod.MOD_ID + " items");
        addItemsToItemGroups();
    }
}