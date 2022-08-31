package org.blazers.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.blazers.block.WaxedCutCopperBricksBlock;
import org.blazers.block.WeatheringCutCopperBricks;
import org.blazers.core.BLEntityTypes;
import org.blazers.entity.goal.CopperGolemRandomStrollGoal;
import org.blazers.event.EventUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Locale;

/**
 * Implementation class for a {@link PathfinderMob Copper Golem}
 */
public class CopperGolem extends PathfinderMob implements IAnimatable {

    /**
     * {@link CopperGolem Copper Golem} {@link AnimationFactory Animation Factory}
     */
    private final AnimationFactory factory = new AnimationFactory(this);
    /**
     * {@link String Weather State} Data
     */
    private static final EntityDataAccessor<String> WEATHER_STATE = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.STRING);
    /**
     * If the {@link CopperGolem Copper Golem} is {@link Boolean Waxed}
     */
    private static final EntityDataAccessor<Boolean> IS_WAXED = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    /**
     * If the {@link CopperGolem Copper Golem} is {@link Boolean Pressing a Copper Button}
     */
    private static final EntityDataAccessor<Boolean> IS_PRESSING_COPPER_BUTTON = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    /**
     * If the {@link CopperGolem Copper Golem} {@link Integer Oxidation Time} in ticks
     */
    private static final EntityDataAccessor<Integer> OXIDATION_TIME = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    /**
     * {@link String Weather State NBT Tag Key}
     */
    private final String WEATHER_STATE_NBT_TAG = "WeatherState";
    /**
     * {@link String Waxed NBT Tag Key}
     */
    private final String WAXED_NBT_TAG = "IsWaxed";
    /**
     * {@link String Oxidation Time NBT Tag Key}
     */
    private final String OXIDATION_TIME_NBT_TAG = "Oxidation";
    /**
     * {@link String Is pressing Copper Button NBT Tag Key}
     */
    private final String IS_PRESSING_COPPER_BUTTON_NBT_TAG = "IsPressingCopperButton";
    /**
     * {@link Integer Ticks elapsed} since last oxidation
     */
    private int oxidationTicks = 0;
    /**
     * {@link Integer Ticks for} pressing a {@link org.blazers.block.CopperButtonBlock Copper Button}
     */
    private final int pressingButtonTicks = 50;
    /**
     * Current {@link Integer Ticks for} pressing a {@link org.blazers.block.CopperButtonBlock Copper Button}
     */
    private int currentPressingButtonTicks = pressingButtonTicks;

    /**
     * Constructor. Sets the {@link PathfinderMob Copper Golem properties}
     *
     * @param entityType {@link EntityType Entity Type}
     * @param world {@link Level World reference}
     */
    public CopperGolem(EntityType<? extends CopperGolem> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Sets the {@link AttributeSupplier.Builder Copper Golem Attributes}
     *
     * @return {@link AttributeSupplier.Builder Copper Golem Attributes}
     */
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    /**
     * Register the {@link CopperGolem Copper Golem} Goals
     */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new CopperGolemRandomStrollGoal(this));
    }

    /**
     * Define the {@link CopperGolem Copper Golem} Synched Data
     */
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WEATHER_STATE, getWeatherStateName(WeatheringCopper.WeatherState.UNAFFECTED));
        this.entityData.define(IS_WAXED, false);
        this.entityData.define(OXIDATION_TIME, 1200);
        this.entityData.define(IS_PRESSING_COPPER_BUTTON, false);
    }

    /**
     * Save the {@link CopperGolem Copper Golem} Data
     *
     * @param nbt {@link CompoundTag NBT Compound}
     */
    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString(WEATHER_STATE_NBT_TAG, this.getCurrentWeatherStateName());
        nbt.putBoolean(WAXED_NBT_TAG, this.isWaxed());
        nbt.putInt(OXIDATION_TIME_NBT_TAG, this.oxidationTicks);
        nbt.putBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG, this.entityData.get(IS_PRESSING_COPPER_BUTTON));
    }

    /**
     * Read the {@link CopperGolem Copper Golem} Data
     *
     * @param nbt {@link CompoundTag NBT Compound}
     */
    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setWeatherState(getWeatherState(nbt.getString(WEATHER_STATE_NBT_TAG)), false);
        this.setWaxed(nbt.getBoolean(WAXED_NBT_TAG), false);
        this.oxidationTicks = nbt.getInt(OXIDATION_TIME_NBT_TAG);
        this.entityData.set(IS_PRESSING_COPPER_BUTTON, nbt.getBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG));
    }

    /**
     * Send the Debug Packets
     */
    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    /**
     * Get a {@link WeatheringCopper.WeatherState Wather State} {@link String Name}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Wather State}
     * @return {@link WeatheringCopper.WeatherState Wather State} {@link String Name}
     */
    private String getWeatherStateName(final WeatheringCopper.WeatherState weatherState) {
        return weatherState.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     *
     * @return {@link CopperGolem Copper Golem} {@link String Weather State}
     */
    public String getCurrentWeatherStateName() {
        return this.entityData.get(WEATHER_STATE);
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     *
     * @return {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     */
    public WeatheringCopper.WeatherState getCurrentWeatherState() {
        return getWeatherState(getCurrentWeatherStateName());
    }

    /**
     * Get a {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param weatherStateName {@link String Weather State Name}
     * @return {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     */
    private WeatheringCopper.WeatherState getWeatherState(final String weatherStateName) {
        return weatherStateName == null || weatherStateName.isEmpty() ? WeatheringCopper.WeatherState.UNAFFECTED : WeatheringCopper.WeatherState.valueOf(weatherStateName.toUpperCase(Locale.ROOT));
    }

    /**
     * Set the {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     * after unoxidation
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Weather State}
     */
    private void setUnoxidationState(final WeatheringCopper.WeatherState weatherState) {
        setWeatherState(weatherState, true);
        fireWorldEvent(EventUtils.WorldEvents.AXE_SCRAPE);
    }

    /**
     * Set the {@link CopperGolem Copper Golem} {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Weather State}
     * @param playSound {@link Boolean If effects should play} after setting the {@link WeatheringCopper.WeatherState Weather State}
     */
    public void setWeatherState(final WeatheringCopper.WeatherState weatherState, boolean playSound) {
        if(weatherState.equals(WeatheringCopper.WeatherState.OXIDIZED)) {
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            this.goalSelector.removeAllGoals();
        } else if(this.goalSelector.getAvailableGoals().isEmpty()) {
            this.registerGoals();
        }
        this.entityData.set(WEATHER_STATE, getWeatherStateName(weatherState));
        if(playSound) {
            this.playSound(SoundEvents.AXE_SCRAPE);
        }
        this.resetOxidationTime();
    }

    /**
     * Check if the {@link CopperGolem Copper Golem} is in the specified {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState Weather State}
     * @return {@link Boolean True} if the {@link CopperGolem Copper Golem} is in the specified {@link WeatheringCopper.WeatherState Weather State}
     */
    private boolean isInWeatherState(final WeatheringCopper.WeatherState weatherState) {
        return getCurrentWeatherState().equals(weatherState);
    }

    /**
     * Check if the {@link CopperGolem Copper Golem} is oxidized
     *
     * @return {@link Boolean True} if the {@link CopperGolem Copper Golem} is oxidized
     */
    public boolean isOxidized() {
        return isInWeatherState(WeatheringCopper.WeatherState.OXIDIZED);
    }

    /**
     * Oxidize the {@link CopperGolem Copper Golem}
     */
    private void oxidize() {
        final WeatheringCopper.WeatherState weatherState = getCurrentWeatherState();
        if(weatherState.equals(WeatheringCopper.WeatherState.UNAFFECTED)) {
            setWeatherState(WeatheringCopper.WeatherState.EXPOSED, true);
        } else if(weatherState.equals(WeatheringCopper.WeatherState.EXPOSED)) {
            setWeatherState(WeatheringCopper.WeatherState.WEATHERED, true);
        } else if(weatherState.equals(WeatheringCopper.WeatherState.WEATHERED)) {
            setWeatherState(WeatheringCopper.WeatherState.OXIDIZED, true);
        }
    }

    /**
     * Unoxidize the {@link CopperGolem Copper Golem}
     */
    private void unoxidize() {
        final WeatheringCopper.WeatherState weatherState = getCurrentWeatherState();
        if(weatherState.equals(WeatheringCopper.WeatherState.OXIDIZED)) {
            setUnoxidationState(WeatheringCopper.WeatherState.WEATHERED);
        } else if(weatherState.equals(WeatheringCopper.WeatherState.WEATHERED)) {
            setUnoxidationState(WeatheringCopper.WeatherState.EXPOSED);
        } else if(weatherState.equals(WeatheringCopper.WeatherState.EXPOSED)) {
            setUnoxidationState(WeatheringCopper.WeatherState.UNAFFECTED);
        }
    }

    /**
     * Check if the {@link CopperGolem Copper Golem} is {@link Boolean Waxed}
     *
     * @return {@link Boolean True} if the {@link CopperGolem Copper Golem} is {@link Boolean Waxed}
     */
    public boolean isWaxed() {
        return this.entityData.get(IS_WAXED);
    }

    /**
     * Set the {@link CopperGolem Copper Golem} {@link #IS_WAXED Waxed} property
     * by a {@link Player Player}
     *
     * @param waxed {@link Boolean True} if the {@link CopperGolem Copper Golem} is waxed
     * @param player {@link Player Player}
     * @param hand {@link InteractionHand Hand}
     */
    private void setWaxedBy(final boolean waxed, final Player player, final InteractionHand hand) {
        this.setWaxed(waxed, true);
        player.swing(hand);
    }

    /**
     * Set the {@link CopperGolem Copper Golem} {@link #IS_WAXED Waxed} property
     *
     * @param waxed {@link Boolean True} if the {@link CopperGolem Copper Golem} is waxed
     * @param playEffects {@link Boolean If effects should be played} after setting the {@link #IS_WAXED Waxed State}
     */
    private void setWaxed(final boolean waxed, final boolean playEffects) {
        this.entityData.set(IS_WAXED, waxed);
        this.resetOxidationTime();
        if(playEffects) {
            fireWorldEvent(waxed ? EventUtils.WorldEvents.HONEYCOMB_WAX_ON : EventUtils.WorldEvents.AXE_WAX_OFF);
            this.playSound(waxed ? SoundEvents.HONEYCOMB_WAX_ON : SoundEvents.AXE_WAX_OFF);
        }
    }

    /**
     * Fire a {@link Level World} Event
     *
     * @param event {@link EventUtils.WorldEvents World Event}
     */
    private void fireWorldEvent(final EventUtils.WorldEvents event) {
        EventUtils.fireWorldEvent(this.level, null, event, this.blockPosition());
    }

    /**
     * Reset the {@link Integer Oxidation Time}
     */
    private void resetOxidationTime() {
        this.oxidationTicks = 0;
    }

    /**
     * Get the {@link Float Copper Golem Eye Sight}
     *
     * @param pose {@link Pose Copper Golem Pose}
     * @param size {@link EntityDimensions Copper Golem Size}
     * @return {@link Float Copper Golem Eye Sight}
     */
    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions size) {
        return 0.8F;
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link ResourceLocation Loot Table}
     *
     * @return {@link CopperGolem Copper Golem} {@link ResourceLocation Loot Table}
     */
    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(BlazersMod.MOD_ID, "entities/copper_golem");
    }

    /**
     * Sets the {@link CopperGolem Copper Golem} immune to {@link LightningBolt Lightning Bolts}
     *
     * @param source {@link DamageSource Damage Source}
     * @return {@link Boolean True} if the {@link DamageSource Damage Source} is a {@link LightningBolt Lightning Bolt}
     */
    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return source.equals(DamageSource.LIGHTNING_BOLT);
    }

    /**
     * Prevent fire from spreading when a {@link LightningBolt Lightning Bolt} hits the {@link CopperGolem Copper Golem}
     *
     * @param world {@link ServerLevel World Reference}
     * @param lightningBolt {@link LightningBolt Lightning Bolt}
     */
    @Override
    public void thunderHit(@NotNull ServerLevel world, @NotNull LightningBolt lightningBolt) {
        if(isWaxed()) {
            setWaxed(false, true);
            return;
        }
        unoxidize();
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link SoundEvent Ambient Sound}
     *
     * @return {@link CopperGolem Copper Golem} {@link SoundEvent Ambient Sound}
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(isOxidized()) {
            return null;
        }
        return switch(random.nextInt(5)) {
            default -> SoundEvents.COPPER_STEP;
            case 1 -> SoundEvents.COPPER_FALL;
            case 2 -> SoundEvents.COPPER_PLACE;
            case 3 -> SoundEvents.COPPER_BREAK;
            case 4 -> SoundEvents.COPPER_HIT;
        };
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link SoundEvent Hurt Sound}
     *
     * @param source {@link DamageSource Damage Source}
     * @return {@link CopperGolem Copper Golem} {@link SoundEvent Hurt Sound}
     */
    @Nullable
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.AXE_WAX_OFF;
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link SoundEvent Death Sound}
     *
     * @return {@link CopperGolem Copper Golem} {@link SoundEvent Death Sound}
     */
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.AXE_SCRAPE;
    }

    /**
     * Play the {@link CopperGolem Copper Golem} {@link SoundEvent Play Sound}
     *
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     */
    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState state) {
        if(!isOxidized()) {
            this.playSound(getAmbientSound());
        }
    }

    /**
     * Get the {@link Integer Ambient Sound Interval}
     *
     * @return {@link Integer Ambient Sound Interval}
     */
    @Override
    public int getAmbientSoundInterval() {
        return 200 + random.nextInt(100);
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link Integer Experience Reward}
     *
     * @return {@link CopperGolem Copper Golem} {@link Integer Experience Reward}
     */
    public int getExperienceReward() {
        return 1 + this.level.random.nextInt(3);
    }

    /**
     * Oxidize the {@link CopperGolem Copper Golem} over time
     */
    @Override
    public void aiStep() {
        if(this.isAlive()) {
            if(this.isPressingCopperButton()) {
                currentPressingButtonTicks--;
                if(currentPressingButtonTicks <= 0) {
                    setPressingCopperButton(false);
                }
            }
            if(!isOxidized() && !isWaxed()) {
                this.oxidationTicks++;
                if(this.oxidationTicks >= 1200) {
                    oxidize();
                }
            }
        }
        super.aiStep();
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link Float Movement Speed}
     *
     * @return {@link CopperGolem Copper Golem} {@link Float Movement Speed}
     */
    @Override
    public float getSpeed() {
        return switch (getCurrentWeatherState()) {
            default -> 0.3F;
            case EXPOSED -> 0.2F;
            case WEATHERED -> 0.1F;
            case OXIDIZED -> 0F;
        };
    }

    /**
     * Sets the {@link WeatheringCopper.WeatherState Weather State} on {@link Player Player} interact
     *
     * @param player {@link Player Player}
     * @param hand {@link InteractionHand Hand}
     * @return {@link InteractionResult Interaction Result}
     */
    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        final ItemStack item = player.getItemInHand(hand);
        if(!item.isEmpty()) {
            if(item.getItem() instanceof AxeItem) {
                if(isWaxed()) {
                    setWaxedBy(false, player, hand);
                    player.swing(hand);
                } else if(!isInWeatherState(WeatheringCopper.WeatherState.UNAFFECTED)) {
                    unoxidize();
                    player.swing(hand);
                }
            } else if(item.is(Items.HONEYCOMB)) {
                setWaxedBy(true, player, hand);
                player.swing(hand);
            }
        }
        return super.mobInteract(player, hand);
    }

    /**
     * Finalize the {@link CopperGolem Copper Golem} Spawn
     *
     * @param level {@link ServerLevelAccessor World Reference}
     * @param difficulty {@link DifficultyInstance Difficulty}
     * @param mobSpawnType {@link MobSpawnType Mob Spawn Type}
     * @param spawnData {@link SpawnGroupData Spawn Data}
     * @param dataTag {@link CompoundTag NBT Tag}
     * @return {@link SpawnGroupData Finalized Spawn Data}
     */
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        if(dataTag != null && dataTag.contains(WEATHER_STATE_NBT_TAG)) {
            setWeatherState(getWeatherState(dataTag.getString(WEATHER_STATE_NBT_TAG)), false);
        } else {
            setWeatherState(WeatheringCopper.WeatherState.UNAFFECTED, false);
        }
        return super.finalizeSpawn(level, difficulty, mobSpawnType, spawnData, dataTag);
    }

    /**
     * Get the {@link Float Walk Target Value}
     *
     * @param pos {@link BlockPos Block Pos}
     * @param level {@link LevelReader World Reference}
     * @return {@link Float Walk Target Value}
     */
    @Override
    public float getWalkTargetValue(@NotNull BlockPos pos, @NotNull LevelReader level) {
        return level.getPathfindingCostFromLightLevels(pos);
    }

    /**
     * Set if the {@link CopperGolem Copper Golem} is pressing a {@link org.blazers.block.CopperButtonBlock Copper Button}
     *
     * @param isPressing {@link Boolean If the Copper Golem is pressing a Copper Button}
     */
    public void setPressingCopperButton(final boolean isPressing) {
        if(isPressing) {
            this.currentPressingButtonTicks = pressingButtonTicks;
        }
        this.entityData.set(IS_PRESSING_COPPER_BUTTON, isPressing);
    }

    /**
     * Check if the {@link CopperGolem Copper Golem} is pressing a {@link org.blazers.block.CopperButtonBlock Copper Button}
     *
     * @return {@link Boolean True} if the {@link CopperGolem Copper Golem} is pressing a {@link org.blazers.block.CopperButtonBlock Copper Button}
     */
    public boolean isPressingCopperButton() {
        return this.entityData.get(IS_PRESSING_COPPER_BUTTON);
    }

    /**
     * Get the {@link CopperGolem Copper Golem} animation
     *
     * @param event {@link AnimationEvent<T> Animation Event}
     * @return {@link CopperGolem Copper Golem} animation
     * @param <T> Animation Event Type
     */
    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if(!isOxidized()) {
            AnimationController<CopperGolem> controller = event.getController();
            if(isPressingCopperButton()) {
                setAnimation(controller, "animation.copper_golem.interact", false);
            }
            else {
                setAnimation(controller, event.isMoving() ? "animation.copper_golem.walk" : "animation.copper_golem.idle", true);
            }
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    /**
     * Play an {@link String Animation}
     *
     * @param controller {@link AnimationController Animation Controller}
     * @param animation {@link String Animation to play}
     * @param loop {@link Boolean If the animation should loop}
     */
    public void setAnimation(final AnimationController<CopperGolem> controller, final String animation, final boolean loop) {
        controller.setAnimation(new AnimationBuilder().addAnimation(animation, loop));
    }

    /**
     * Register the {@link CopperGolem Copper Golem} {@link AnimationController Animaiton Controller}
     *
     * @param data {@link AnimationData Animation Data}
     */
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(getAnimationController());
    }

    /**
     * Get the {@link AnimationController Animation Controller}
     *
     * @return {@link AnimationController Animation Controller}
     */
    public AnimationController<CopperGolem> getAnimationController() {
        return new AnimationController<>(this, "controller", 0, this::predicate);
    }

    /**
     * Get the {@link CopperGolem Copper Golem} {@link AnimationFactory Animation Factory}
     *
     * @return {@link CopperGolem Copper Golem} {@link AnimationFactory Animation Factory}
     */
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /**
     * Check if a {@link Block Block} is a Coppper {@link Block Block}
     *
     * @param block {@link Block Block} to check
     * @return {@link Boolean True} if the {@link Block Block} is a Coppper {@link Block Block}
     */
    private static boolean isValidBlockForGolemSpawning(final Block block) {
        return block instanceof WeatheringCutCopperBricks || block instanceof  WeatheringCopperFullBlock
                || isWaxedBlock(block);
    }

    /**
     * Check if a {@link Block Block} is waxed
     *
     * @param block {@link Block Block} to check
     * @return {@link Boolean True} if the {@link Block Block} is waxed
     */
    private static boolean isWaxedBlock(final Block block) {
        return block instanceof  WaxedCutCopperBricksBlock || block.equals(Blocks.WAXED_COPPER_BLOCK) ||
                block.equals(Blocks.WAXED_EXPOSED_COPPER) || block.equals(Blocks.WAXED_WEATHERED_COPPER) ||
                block.equals(Blocks.WAXED_OXIDIZED_COPPER) || block.equals(Blocks.WAXED_CUT_COPPER) ||
                block.equals(Blocks.WAXED_EXPOSED_CUT_COPPER) || block.equals(Blocks.WAXED_WEATHERED_CUT_COPPER) ||
                block.equals(Blocks.WAXED_OXIDIZED_CUT_COPPER);
    }

    /**
     * Get the {@link WeatheringCopper.WeatherState Weather State} from a {@link Block Block}
     *
     * @param block {@link Block Block}
     * @return {@link WeatheringCopper.WeatherState Weather State}
     */
    private static WeatheringCopper.WeatherState getWeatherStateFromBlock(final Block block) {
        if(!isWaxedBlock(block)) {
            return ((ChangeOverTimeBlock<WeatheringCopper.WeatherState>)block).getAge();
        }
        if(block.equals(Blocks.WAXED_OXIDIZED_COPPER) || block.equals(Blocks.WAXED_OXIDIZED_CUT_COPPER)) {
            return WeatheringCopper.WeatherState.UNAFFECTED;
        }
        if(block.equals(Blocks.WAXED_EXPOSED_COPPER) || block.equals(Blocks.WAXED_EXPOSED_CUT_COPPER)) {
            return WeatheringCopper.WeatherState.EXPOSED;
        }
        if(block.equals(Blocks.WAXED_WEATHERED_COPPER) || block.equals(Blocks.WAXED_WEATHERED_CUT_COPPER)) {
            return WeatheringCopper.WeatherState.WEATHERED;
        }
        return WeatheringCopper.WeatherState.UNAFFECTED;
    }

    /**
     * Try to spawn a {@link CopperGolem Copper Golem}
     *
     * @param world {@link Level World Reference}
     * @param pos {@link BlockPos Block Pos}
     * @param player {@link Player Player}
     * @param itemStack {@link ItemStack Item Stack}
     * @param hand {@link InteractionHand Interaction Hand}
     */
    public static void trySpawnGolem(final Level world, final BlockPos pos, final Player player, final ItemStack itemStack, final InteractionHand hand) {
        Block below = world.getBlockState(pos.below()).getBlock();
        boolean isWaxed = isWaxedBlock(below);
        if(isValidBlockForGolemSpawning(below)) {
            world.destroyBlock(pos, false);
            world.destroyBlock(pos.below(), false);
            CopperGolem copperGolem = new CopperGolem(BLEntityTypes.COPPER_GOLEM.get(), world);
            copperGolem.setPos(pos.getX() + 0.5F, pos.getY() - 0.5F, pos.getZ() + 0.5F);
            copperGolem.setWeatherState(getWeatherStateFromBlock(below), false);
            copperGolem.setWaxed(isWaxed, false);
            EventUtils.fireWorldEvent(world, player, EventUtils.WorldEvents.AXE_SCRAPE, pos);
            if(player != null) {
                copperGolem.setPersistenceRequired();
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE);
                player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE);

                if(!itemStack.isEmpty()) {
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemStack);
                    }
                    if(!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    if(world.isClientSide() && hand != null) {
                        player.swing(hand);
                    }
                }
            }
            world.addFreshEntity(copperGolem);
        }
    }
}