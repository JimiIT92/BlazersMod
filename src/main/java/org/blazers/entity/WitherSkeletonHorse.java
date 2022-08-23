package org.blazers.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Implementation class for a {@link Horse Wither Skeleton Horse}
 */
public class WitherSkeletonHorse extends Horse {

    /**
     * Constructor. Sets the {@link Horse Horse properties}
     *
     * @param entityType {@link EntityType Entity Type}
     * @param world {@link Level World reference}
     */
    public WitherSkeletonHorse(EntityType<? extends Horse> entityType, Level world) {
        super(entityType, world);
        this.setTamed(true);
    }

    /**
     * Sets the {@link WitherSkeletonHorse Wither Skeleton Horse} {@link Boolean fire immune}
     *
     * @return {@link Boolean True} if the {@link WitherSkeletonHorse Wither Skeleton Horse} is fire immune
     */
    @Override
    public boolean fireImmune() {
        return true;
    }

    /**
     * Sets the {@link AttributeSupplier.Builder Wither Skeleton Horse Attributes}
     *
     * @return {@link AttributeSupplier.Builder Wither Skeleton Horse Attributes}
     */
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    /**
     * Randomize the {@link AttributeSupplier.Builder Wither Skeleton Horse Attributes}
     */
    protected void randomizeAttributes(@NotNull RandomSource random) {
        Objects.requireNonNull(this.getAttribute(Attributes.JUMP_STRENGTH)).setBaseValue(this.generateRandomJumpStrength(random));
    }

    /**
     * Get the {@link WitherSkeletonHorse Wither Skeleton Horse} {@link ResourceLocation Loot Table}
     *
     * @return {@link WitherSkeletonHorse Wither Skeleton Horse} {@link ResourceLocation Loot Table}
     */
    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(BlazersMod.MOD_ID, "entities/wither_skeleton_horse");
    }

    /**
     * Sets if the {@link WitherSkeletonHorse Wither Skeleton Horse} can mate with another {@link Animal animal}
     *
     * @param animal {@link Animal Animal}
     * @return {@link Boolean True} if the {@link WitherSkeletonHorse Wither Skeleton Horse} can mate with another {@link Animal animal}
     */
    @Override
    public boolean canMate(@NotNull Animal animal) {
        return false;
    }

    /**
     * Get the {@link WitherSkeletonHorse Baby Wither Skeleton Horse}
     *
     * @param level {@link Level World reference}
     * @param ageableMob {@link AgeableMob Ageable Mob}
     * @return {@link WitherSkeletonHorse Baby Wither Skeleton Horse}
     */
    @Nullable
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob ageableMob) {
        return BLEntityTypes.WITHER_SKELETON_HORSE.get().create(level);
    }

    /**
     * Spawn {@link ParticleTypes smoke particles}
     * on {@link Horse Horse} step
     */
    public void aiStep() {
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.SMOKE,
                        this.getRandomX(0.5D),
                        this.getRandomY() - 0.25D,
                        this.getRandomZ(0.5D),
                        0,
                        0,
                        0);
            }
        }
        super.aiStep();
    }

    /**
     * Get the valid {@link Float Light Level} for a {@link WitherSkeletonHorse Wither Skeleton Horse} to spawn
     *
     * @param pos {@link BlockPos Block Pos}
     * @param level {@link LevelReader World reference}
     * @return {@link Float Light Level}
     */
    @Override
    public float getWalkTargetValue(@NotNull BlockPos pos, LevelReader level) {
        return 0.5F - level.getBrightness(LightLayer.SKY, pos);
    }
}