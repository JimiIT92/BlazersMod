package org.blazers.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import org.blazers.BlazersMod;
import org.blazers.block.weathering.CopperButtonBlock;
import org.blazers.core.BLEntityTypes;
import org.blazers.core.BLSounds;
import org.blazers.core.BLTags;
import org.blazers.helper.BlockHelper;
import org.blazers.helper.ItemHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

/**
 * {@link BlazersMod Blazers Mod} {@link LivingEntity Copper Golem}
 */
public final class CopperGolem extends PathfinderMob {

    /**
     * The {@link EntityDataAccessor<String> Weather State Entity Data Accessor}
     */
    private static final EntityDataAccessor<String> WEATHER_STATE = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.STRING);
    /**
     * The {@link EntityDataAccessor<Boolean> Waxed Entity Data Accessor}
     */
    private static final EntityDataAccessor<Boolean> IS_WAXED = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    /**
     * The {@link EntityDataAccessor<Boolean> Pressing Copper Button State Entity Data Accessor}
     */
    private static final EntityDataAccessor<Boolean> IS_PRESSING_COPPER_BUTTON = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);
    /**
     * The {@link EntityDataAccessor<Integer> Oxidation Time Entity Data Accessor}
     */
    private static final EntityDataAccessor<Integer> OXIDATION_TIME = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    /**
     * {@link String The Weather State NBT Tag Key}
     */
    private final String WEATHER_STATE_NBT_TAG = "WeatherState";
    /**
     * {@link String The Waxed NBT Tag Key}
     */
    private final String WAXED_NBT_TAG = "IsWaxed";
    /**
     * {@link String The Pressing Copper Button NBT Tag Key}
     */
    private final String IS_PRESSING_COPPER_BUTTON_NBT_TAG = "IsPressingCopperButton";
    /**
     * {@link String The Oxidation Time NBT Tag Key}
     */
    private final String OXIDATION_TIME_NBT_TAG = "Oxidation";
    /**
     * {@link Integer The maximum ticks} for oxidation
     */
    private final int maxOxidationTicks = 1200;
    /**
     * {@link Integer The ticks elapsed} since last oxidation
     */
    private int oxidationTicks = 0;
    /**
     * {@link Integer The ticks for} pressing a {@link CopperButtonBlock Copper Button}
     */
    private final int pressingButtonTicks = 40;
    /**
     * Current {@link Integer The ticks for} pressing a {@link CopperButtonBlock Copper Button}
     */
    private int currentPressingButtonTicks = pressingButtonTicks;
    /**
     * {@link Boolean Wether the Copper Golem can press Copper Buttons}
     */
    private boolean canPressCopperButtons = true;
    /**
     * {@link Integer The minimum ticks} that must be elapsed before two presses of a {@link CopperButtonBlock Copper Button}
     */
    private final int ticksBeforeNextPress = 400;
    /**
     * {@link Integer The ticks elapsed} since the last press of a {@link CopperButtonBlock Copper Button}
     */
    private int ticksSinceLastPress = 0;
    /**
     * {@link AnimationState The entity idle animation state}
     */
    public final AnimationState idleAnimationState = new AnimationState();
    /**
     * The {@link Integer entity idle animation timeout}
     */
    private int idleAnimationTimeout = 0;
    /**
     * {@link AnimationState The entity interact animation state}
     */
    public final AnimationState interactAnimationState = new AnimationState();
    /**
     * The {@link Integer entity interact animation timeout}
     */
    private int interactAnimationTimeout = 0;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType The entity type}
     * @param level {@link Level The level reference}
     */
    public CopperGolem(final EntityType<? extends PathfinderMob> entityType, final Level level) {
        super(entityType, level);
        this.xpReward = 1 + this.level().random.nextInt(3);
    }

    /**
     * Create the entity attributes
     *
     * @return {@link AttributeSupplier.Builder The attribute supplier builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return AgeableMob.createMobAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    /**
     * Register the entity goals
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
     * Define the entity NBT data
     *
     * @param dataBuilder The {@link SynchedEntityData.Builder The Synced Entity Data Builder}
     */
    @Override
    protected void defineSynchedData(final @NotNull SynchedEntityData.Builder dataBuilder) {
        super.defineSynchedData(dataBuilder);
        dataBuilder.define(WEATHER_STATE, this.getWeatherStateName(WeatheringCopper.WeatherState.UNAFFECTED));
        dataBuilder.define(IS_WAXED, false);
        dataBuilder.define(IS_PRESSING_COPPER_BUTTON, false);
        dataBuilder.define(OXIDATION_TIME, this.maxOxidationTicks);
    }

    /**
     * Save the entity data to the {@link CompoundTag NBT}
     *
     * @param nbt {@link CompoundTag The entity NBT data}
     */
    @Override
    public void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString(WEATHER_STATE_NBT_TAG, this.getWeatherStateName(this.getCurrentWeatherState()));
        nbt.putBoolean(WAXED_NBT_TAG, this.isWaxed());
        nbt.putBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG, this.isPressingCopperButton());
        nbt.putInt(OXIDATION_TIME_NBT_TAG, this.oxidationTicks);
    }

    /**
     * Read the entity data from the {@link CompoundTag NBT}
     *
     * @param nbt {@link CompoundTag The entity NBT data}
     */
    @Override
    public void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setWeatherState(getWeatherState(nbt.getString(WEATHER_STATE_NBT_TAG)));
        this.setWaxed(nbt.getBoolean(WAXED_NBT_TAG));
        this.setPressingCopperButton(nbt.getBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG));
        this.oxidationTicks = nbt.getInt(OXIDATION_TIME_NBT_TAG);
    }

    /**
     * Send the entity debug packets
     */
    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    /**
     * Make the entity tick
     */
    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    /**
     * Setup the entity animations
     */
    private void setupAnimationStates() {
        if(this.isOxidized() && this.idleAnimationState.isStarted()) {
            this.idleAnimationState.stop();
            return;
        }
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(60) + 120;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isPressingCopperButton() && this.interactAnimationTimeout <= 0) {
            this.interactAnimationTimeout = 25;
            this.interactAnimationState.start(this.tickCount);
        } else {
            --this.interactAnimationTimeout;
        }

        if(!this.isPressingCopperButton()) {
            this.interactAnimationState.stop();
        }
    }

    /**
     * Update the entity walk animation
     *
     * @param partialTicks {@link Float The entity partial ticks}
     */
    @Override
    protected void updateWalkAnimation(final float partialTicks) {
        float ticks = 0F;
        if(this.getPose().equals(Pose.STANDING)) {
            ticks = Math.min(partialTicks * 6F, 1F);
        }
        this.walkAnimation.update(ticks, 0.2F);
    }

    /**
     * Make the entity goals tick
     */
    @Override
    public void aiStep() {
        if(this.isAlive()) {
            this.ticksSinceLastPress = Math.min(this.ticksSinceLastPress + 1, this.ticksBeforeNextPress);
            if(this.isPressingCopperButton()) {
                this.currentPressingButtonTicks--;
                if(this.currentPressingButtonTicks <= 0) {
                    this.setPressingCopperButton(false);
                }
            }
            if(!this.isOxidized() && !this.isWaxed()) {
                this.oxidationTicks++;
                if(this.oxidationTicks >= this.maxOxidationTicks) {
                    this.oxidize();
                }
            }
        }
        super.aiStep();
    }

    /**
     * Make a {@link Player Player} interact with the entity
     *
     * @param player {@link Player The Player interacting with the entity}
     * @param hand {@link InteractionHand The hand used to interact with the entity}
     * @return {@link InteractionResult The interaction result}
     */
    @Override
    protected @NotNull InteractionResult mobInteract(final @NotNull Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if(!itemStack.isEmpty()) {
            if(itemStack.getItem() instanceof AxeItem) {
                if(this.isWaxed()) {
                    this.setWaxedBy(false, player, hand, itemStack);
                } else {
                    this.unoxidizeBy(player, hand, itemStack);
                }
            } else if (itemStack.is(Items.HONEYCOMB)) {
                this.setWaxedBy(true, player, hand, itemStack);
            }
        }
        return super.mobInteract(player, hand);
    }

    /**
     * Set the entity {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     */
    private void setWeatherState(final WeatheringCopper.WeatherState weatherState) {
        if(weatherState.equals(WeatheringCopper.WeatherState.OXIDIZED)) {
            this.goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
            this.goalSelector.removeAllGoals(goal -> true);
        } else if(this.goalSelector.getAvailableGoals().isEmpty()) {
            this.registerGoals();
        }
        this.entityData.set(WEATHER_STATE, getWeatherStateName(weatherState));
    }

    /**
     * Get a {@link WeatheringCopper.WeatherState Weather State} given its name
     *
     * @param weatherStateName {@link String The weather state name}
     * @return {@link WeatheringCopper.WeatherState The Weather State}
     */
    private WeatheringCopper.WeatherState getWeatherState(final String weatherStateName) {
        return weatherStateName == null || weatherStateName.isEmpty() ? WeatheringCopper.WeatherState.UNAFFECTED : WeatheringCopper.WeatherState.valueOf(weatherStateName.toUpperCase(Locale.ROOT));
    }

    /**
     * Get the {@link WeatheringCopper.WeatherState current Weather State}
     *
     * @return {@link WeatheringCopper.WeatherState The current Weather State}
     */
    public WeatheringCopper.WeatherState getCurrentWeatherState() {
        return getWeatherState(this.entityData.get(WEATHER_STATE));
    }

    /**
     * Get the {@link String weather state texture location}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @return {@link String The weather state texture location}
     */
    public String getWeatherStateName(final WeatheringCopper.WeatherState weatherState) {
        return weatherState.equals(WeatheringCopper.WeatherState.UNAFFECTED) ? "unaffected" : BlockHelper.weatherStateName(weatherState);
    }

    /**
     * Make the entity oxidize over time
     */
    private void oxidize() {
        switch(this.getCurrentWeatherState()) {
            case UNAFFECTED -> this.setWeatherState(WeatheringCopper.WeatherState.EXPOSED);
            case EXPOSED -> this.setWeatherState(WeatheringCopper.WeatherState.WEATHERED);
            case WEATHERED -> this.setWeatherState(WeatheringCopper.WeatherState.OXIDIZED);
            case OXIDIZED -> { }
        }
    }

    /**
     * Make the entity unoxidize
     */
    private void unoxidize() {
        switch(this.getCurrentWeatherState()) {
            case UNAFFECTED -> { }
            case EXPOSED -> this.setWeatherState(WeatheringCopper.WeatherState.UNAFFECTED);
            case WEATHERED -> this.setWeatherState(WeatheringCopper.WeatherState.EXPOSED);
            case OXIDIZED -> this.setWeatherState(WeatheringCopper.WeatherState.WEATHERED);
        }
        this.resetOxidationTime();
    }

    /**
     * Make the entity unoxidize when interacted by a {@link Player Player}
     *
     * @param player {@link Player The Player interacting with the entity}
     * @param hand {@link InteractionHand The hand used to interact with the entity}
     * @param itemStack {@link ItemStack The Item Stack used on the entity}
     */
    private void unoxidizeBy(final Player player, final InteractionHand hand, final ItemStack itemStack) {
        if(!this.getCurrentWeatherState().equals(WeatheringCopper.WeatherState.UNAFFECTED)) {
            this.unoxidize();
            this.playSound(SoundEvents.AXE_SCRAPE);
            this.fireUnoxidizeLevelEvent(player);
            ItemHelper.hurt(itemStack, player, hand);
        }
    }

    /**
     * Fire the unoxidize level event
     *
     * @param player {@link Player The player firing the event}
     */
    private void fireUnoxidizeLevelEvent(final Player player) {
        this.level().levelEvent(player, 3005, this.blockPosition(), 0);
    }

    /**
     * Check if the entity is oxidized
     *
     * @return {@link Boolean True if the entity is oxidized}
     */
    private boolean isOxidized() {
        return getCurrentWeatherState().equals(WeatheringCopper.WeatherState.OXIDIZED);
    }

    /**
     * Check if the entity is waxed
     *
     * @return {@link Boolean True if the entity is waxed}
     */
    private boolean isWaxed() {
        return this.entityData.get(IS_WAXED);
    }

    /**
     * Set the entity waxed status
     *
     * @param isWaxed {@link Boolean Whether the entity is waxed or not}
     */
    private void setWaxed(final boolean isWaxed) {
        this.entityData.set(IS_WAXED, isWaxed);
        this.resetOxidationTime();
    }

    /**
     * Make the entity waxed o unwaxed when interacted by a {@link Player Player}
     *
     * @param isWaxed {@link Boolean Whether the entity is waxed or not}
     * @param player {@link Player The Player interacting with the entity}
     * @param hand {@link InteractionHand The hand used to interact with the entity}
     * @param itemStack {@link ItemStack The Item Stack used on the entity}
     */
    private void setWaxedBy(final boolean isWaxed, final Player player, final InteractionHand hand, final ItemStack itemStack) {
        this.setWaxed(isWaxed);
        this.playSound(isWaxed ? SoundEvents.HONEYCOMB_WAX_ON : SoundEvents.AXE_WAX_OFF);
        this.level().levelEvent(player, isWaxed ? 3003 : 3004, this.blockPosition(), 0);
        ItemHelper.hurt(itemStack, player, hand);
    }

    /**
     * Reset the entity oxidation time
     */
    private void resetOxidationTime() {
        this.oxidationTicks = 0;
    }

    /**
     * Check if the entity is invulnerable to the given {@link DamageSource Damage Source}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link Boolean True if the damage source is a lightning bolt}
     */
    @Override
    public boolean isInvulnerableTo(final @NotNull DamageSource damageSource) {
        return damageSource.is(DamageTypes.LIGHTNING_BOLT);
    }

    /**
     * Unoxidize or unwax the entity when hit by a lightning bolt
     *
     * @param level {@link ServerLevel The Level reference}
     * @param lightningBolt {@link LightningBolt The lightning bolt reference}
     */
    @Override
    public void thunderHit(final @NotNull ServerLevel level, final @NotNull LightningBolt lightningBolt) {
        if(this.isWaxed()) {
            this.setWaxed(false);
            return;
        }
        unoxidize();
    }

    /**
     * Check if the entity is silent
     *
     * @return {@link Boolean True if the entity is oxidized}
     */
    @Override
    public boolean isSilent() {
        return this.isOxidized();
    }

    /**
     * Get the {@link SoundEvent entity ambient sound}
     *
     * @return {@link SoundEvent The entity ambient sound}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return BLSounds.COPPER_GOLEM_AMBIENT.get();
    }

    /**
     * Get the {@link SoundEvent entity hurt sound}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link SoundEvent The entity hurt sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return BLSounds.COPPER_GOLEM_AMBIENT.get();
    }

    /**
     * Get the {@link SoundEvent entity death sound}
     *
     * @return {@link SoundEvent The entity death sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return BLSounds.COPPER_GOLEM_AMBIENT.get();
    }

    /**
     * Play the {@link SoundEvent entity step sound}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    @Override
    protected void playStepSound(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        this.playSound(this.getAmbientSound());
    }

    /**
     * Get the {@link Integer ambient sound interval}
     *
     * @return {@link Integer The ambient sound interval}
     */
    @Override
    public int getAmbientSoundInterval() {
        return 200 + random.nextInt(100);
    }

    /**
     * Get the entity movement speed based on its oxidation state
     *
     * @return {@link Float The entity movement speed}
     */
    @Override
    public float getSpeed() {
        return switch (this.getCurrentWeatherState()) {
            case UNAFFECTED -> 0.3F;
            case EXPOSED -> 0.2F;
            case WEATHERED -> 0.1F;
            case OXIDIZED -> 0F;
        };
    }

    /**
     * Get the valid light level for the entity to be able to spawn
     *
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param level {@link LevelReader The level reference}
     * @return {@link Float The entity valid light level for spawn}
     */
    @Override
    public float getWalkTargetValue(final @NotNull BlockPos blockPos, final @NotNull LevelReader level) {
        return level.getPathfindingCostFromLightLevels(blockPos);
    }

    /**
     * Check if the entity can press a {@link CopperButtonBlock Copper Button}
     *
     * @return {@link Boolean True if the entity can press a copper button}
     */
    private boolean canPressCopperButton() {
        return this.canPressCopperButtons && this.ticksSinceLastPress >= this.ticksBeforeNextPress;
    }

    /**
     * Check if the entity is pressing a {@link Boolean Copper Button}
     *
     * @return {@link Boolean True if the entity is pressing a copper button}
     */
    private boolean isPressingCopperButton() {
        return this.entityData.get(IS_PRESSING_COPPER_BUTTON);
    }

    /**
     * Set the entity pressing button state
     *
     * @param isPressing {@link Boolean Whether the entity is pressing a copper button}
     */
    private void setPressingCopperButton(final boolean isPressing) {
        this.currentPressingButtonTicks = pressingButtonTicks;
        this.canPressCopperButtons = !this.canPressCopperButtons;
        if(isPressing) {
            this.ticksSinceLastPress = 0;
        }
        this.entityData.set(IS_PRESSING_COPPER_BUTTON, isPressing);
    }

    /**
     * Try spawn a golem
     *
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param player {@link Player The Player trying to spawn the golem}
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param hand {@link InteractionHand The hand used to try to spawn the golem}
     */
    public static void trySpawnGolem(final Level level, final BlockPos blockPos, final Player player, final ItemStack itemStack, final InteractionHand hand) {
        final BlockPos belowBlockPos = blockPos.below();
        final BlockState belowBlockState = level.getBlockState(belowBlockPos);
        if(belowBlockState.is(BLTags.Blocks.COPPER_GOLEM_SPAWNABLES)) {
            level.destroyBlock(blockPos, false);
            level.destroyBlock(belowBlockPos, false);
            final CopperGolem copperGolem = new CopperGolem(BLEntityTypes.COPPER_GOLEM.get(), level);
            copperGolem.setPos(blockPos.getX() + 0.5F, blockPos.getY() - 0.5F, blockPos.getZ() + 0.5F);
            copperGolem.setWeatherState(getWeatherStateFromBlock(belowBlockState));
            copperGolem.setWaxed(belowBlockState.is(BLTags.Blocks.WAXED_COPPER_GOLEM_SPAWNABLES));
            copperGolem.fireUnoxidizeLevelEvent(player);
            if(player != null) {
                copperGolem.setPersistenceRequired();
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE);
                player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger((ServerPlayer)player, copperGolem);
                }
                ItemHelper.hurt(itemStack, player, hand);
            }
            level.addFreshEntity(copperGolem);
        }
    }

    /**
     * Get a {@link WeatheringCopper.WeatherState Weather State} based on the current {@link BlockState Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link WeatheringCopper.WeatherState The Weather State}
     */
    private static WeatheringCopper.WeatherState getWeatherStateFromBlock(final BlockState blockState) {
        if(blockState.is(BLTags.Blocks.WEATHERED_COPPER_GOLEM_SPAWNABLES)) {
            return WeatheringCopper.WeatherState.WEATHERED;
        }
        if(blockState.is(BLTags.Blocks.EXPOSED_COPPER_GOLEM_SPAWNABLES)) {
            return WeatheringCopper.WeatherState.EXPOSED;
        }
        if(blockState.is(BLTags.Blocks.OXIDIZED_COPPER_GOLEM_SPAWNABLES)) {
            return WeatheringCopper.WeatherState.OXIDIZED;
        }
        return WeatheringCopper.WeatherState.UNAFFECTED;
    }

    /**
     * Make the {@link CopperGolem Copper Golem} randomly searching for {@link CopperButtonBlock Copper Buttons}
     */
    static class CopperGolemRandomStrollGoal extends RandomStrollGoal {

        /**
         * Constructors. Set the goal properties
         *
         * @param copperGolem {@link CopperGolem Copper Golem}
         */
        public CopperGolemRandomStrollGoal(final CopperGolem copperGolem) {
            super(copperGolem, 1D, 120);
        }

        /**
         * Push a {@link CopperButtonBlock Copper Button} if is in range when the {@link CopperGolem Copper Golem} stops
         */
        @Override
        public void stop() {
            super.stop();
            getRandomNearbyCopperButton().ifPresent(copperButton -> {
                final CopperGolem copperGolem = ((CopperGolem)this.mob);
                if(copperGolem.canPressCopperButton() && !copperGolem.isPressingCopperButton()) {
                    final Path path = this.mob.getNavigation().createPath(copperButton, 2);
                    if(path != null) {
                        path.advance();
                        final Level level = this.mob.level();
                        final BlockState blockState = level.getBlockState(copperButton);
                        copperGolem.setPressingCopperButton(true);
                        ((CopperButtonBlock)blockState.getBlock()).press(blockState, level, copperButton);
                    }
                }
            });
        }

        /**
         * Get the nearby {@link CopperButtonBlock Copper Buttons}
         *
         * @return {@link Optional<Block> The Random Nearby Copper Button}
         */
        private Optional<BlockPos> getRandomNearbyCopperButton() {
            final ArrayList<BlockPos> copperButtons = new ArrayList<>();
            final int searchRange = 3;
            for (int x = -searchRange; x <= searchRange; x++) {
                for (int y = -searchRange; y <= searchRange; y++) {
                    for (int z = -searchRange; z <= searchRange; z++) {
                        final BlockPos offsetPos = this.mob.blockPosition().offset(x, y, z);
                        if(this.mob.level().getBlockState(offsetPos).getBlock() instanceof CopperButtonBlock) {
                            copperButtons.add(offsetPos);
                        }
                    }
                }
            }

            return copperButtons.isEmpty() ? Optional.empty() : Optional.of(copperButtons.get(this.mob.getRandom().nextInt(copperButtons.size())));
        }
    }
}