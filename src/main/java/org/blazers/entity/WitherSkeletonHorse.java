package org.blazers.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import org.blazers.BlazersMod;
import org.blazers.core.BLEntityTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

/**
 * Implementation class for a {@link Horse Wither Skeleton Horse}
 */
public class WitherSkeletonHorse extends SkeletonHorse {

    /**
     * Constructor. Sets the {@link Horse Horse properties}
     *
     * @param entityType {@link EntityType Entity Type}
     * @param world {@link Level World reference}
     */
    public WitherSkeletonHorse(EntityType<? extends SkeletonHorse> entityType, Level world) {
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
    protected void randomizeAttributes() {
        Objects.requireNonNull(this.getAttribute(Attributes.JUMP_STRENGTH)).setBaseValue(this.generateRandomJumpStrength());
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
     * Sets if {@link WitherSkeletonHorse Wither Skeleton Horse} can spawn the skeleton trap
     *
     * @return {@link Boolean True} if {@link WitherSkeletonHorse Wither Skeleton Horse} can spawn the skeleton trap
     */
    @Override
    public boolean isTrap() {
        return false;
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
     * Sets if the {@link WitherSkeletonHorse Wither Skeleton Horse} can wear an {@link net.minecraft.world.item.HorseArmorItem Horse Armor}
     *
     * @return {@link Boolean True} if the {@link WitherSkeletonHorse Wither Skeleton Horse} can wear an {@link net.minecraft.world.item.HorseArmorItem Horse Armor}
     */
    @Override
    public boolean canWearArmor() {
        return true;
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
     * {@link WitherSkeletonHorse Wither Skeleton Horse} spawn rules
     *
     * @param entity {@link EntityType Entity Type}
     * @param level {@link LevelAccessor World reference}
     * @param spawnType {@link MobSpawnType Spawn Type}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link Random Random variable}
     * @return {@link Boolean True} if the {@link WitherSkeletonHorse Wither Skeleton Horse} should spawn
     */
    public static boolean checkWitherSkeletonHorseSpawnRules(EntityType<WitherSkeletonHorse> entity, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, Random random) {
        return true;
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