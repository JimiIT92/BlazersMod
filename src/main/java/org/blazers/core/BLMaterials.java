package org.blazers.core;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.blazers.BlazersMod;

/**
 * {@link BlazersMod Blazers Mod} materials
 */
public enum BLMaterials {
    SAPPHIRE(MapColor.COLOR_BLUE),
    TOPAZ(MapColor.COLOR_ORANGE),
    PEARL(MapColor.COLOR_PINK, SoundType.CALCITE, 0.75F, 0.75F),
    RUBY(MapColor.COLOR_RED),
    MALACHITE(MapColor.TERRACOTTA_GREEN),
    ONICE(MapColor.TERRACOTTA_BLACK),
    URANIUM(MapColor.COLOR_GREEN, SoundType.COPPER, 3.0F, 6.0F),
    RAW_URANIUM(MapColor.COLOR_ORANGE);

    /**
     * The {@link MapColor material Map Color}
     */
    private final MapColor color;
    /**
     * The {@link SoundType material Sound}
     */
    private final SoundType sound;
    /**
     * The {@link Float material strength}
     */
    private final float strength;
    /**
     * The {@link Float material blast resistance}
     */
    private final float blastResistance;

    /**
     * Default constructor. Set the material properties
     */
    BLMaterials() {
        this(MapColor.STONE);
    }

    /**
     * Default constructor. Set the material properties
     *
     * @param color The {@link MapColor material Map Color}
     */
    BLMaterials(final MapColor color) {
        this(color, SoundType.METAL, 5.0F, 6.0F);
    }

    /**
     * Constructor. Set the material properties
     *
     * @param color The {@link MapColor material Map Color}
     * @param sound The {@link SoundType material Sound}
     * @param strength The {@link Float material strength}
     * @param blastResistance The {@link Float material blast resistance}
     */
    BLMaterials(final MapColor color, final SoundType sound, final float strength, final float blastResistance) {
        this.color = color;
        this.sound = sound;
        this.strength = strength;
        this.blastResistance = blastResistance;
    }

    /**
     * Get the {@link MapColor material color}
     *
     * @return The {@link MapColor material Map Color}
     */
    public MapColor color() {
        return this.color;
    }

    /**
     * Get the {@link SoundType material Sound}
     *
     * @return The {@link SoundType material Sound}
     */
    public SoundType sound() {
        return this.sound;
    }

    /**
     * Get the {@link Float material strength}
     *
     * @return The {@link Float material strength}
     */
    public float strength() {
        return this.strength;
    }

    /**
     * Get the {@link Float material blast resistance}
     *
     * @return The {@link Float material blast resistance}
     */
    public float blastResistance() {
        return this.blastResistance;
    }

}