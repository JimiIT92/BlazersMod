package org.blazers.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.blazers.BlazersMod;
import org.blazers.block.OxidizableCutCopperBricksBlock;
import org.blazers.core.BLBlocks;
import org.blazers.core.BLEntityTypes;
import org.blazers.entity.goal.CopperGolemRandomStrollGoal;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Locale;

public class CopperGolemEntity extends PathAwareEntity implements GeoEntity {

    private final AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    private static final TrackedData<String> WEATHER_STATE = DataTracker.registerData(CopperGolemEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<Boolean> IS_WAXED = DataTracker.registerData(CopperGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_PRESSING_COPPER_BUTTON = DataTracker.registerData(CopperGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> OXIDATION_TIME = DataTracker.registerData(CopperGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final String WEATHER_STATE_NBT_TAG = "WeatherState";
    private final String WAXED_NBT_TAG = "IsWaxed";
    private final String OXIDATION_TIME_NBT_TAG = "Oxidation";
    private final String IS_PRESSING_COPPER_BUTTON_NBT_TAG = "IsPressingCopperButton";
    private int oxidationTicks = 0;
    private final int pressingButtonTicks = 40;
    private int currentPressingButtonTicks = pressingButtonTicks;
    private boolean canPressCopperButtons = true;
    private final int ticksBeforeNextPress = 400;
    private int ticksSinceLastPress = 0;

    public CopperGolemEntity(EntityType<? extends CopperGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.0));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(4, new CopperGolemRandomStrollGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WEATHER_STATE, getWeatherStateName(Oxidizable.OxidationLevel.UNAFFECTED));
        this.dataTracker.startTracking(IS_WAXED, false);
        this.dataTracker.startTracking(OXIDATION_TIME, 1200);
        this.dataTracker.startTracking(IS_PRESSING_COPPER_BUTTON, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString(WEATHER_STATE_NBT_TAG, this.getCurrentWeatherStateName());
        nbt.putBoolean(WAXED_NBT_TAG, this.isWaxed());
        nbt.putInt(OXIDATION_TIME_NBT_TAG, this.oxidationTicks);
        nbt.putBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG, this.dataTracker.get(IS_PRESSING_COPPER_BUTTON));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setWeatherState(getWeatherState(nbt.getString(WEATHER_STATE_NBT_TAG)), false);
        this.setWaxed(nbt.getBoolean(WAXED_NBT_TAG), false);
        this.oxidationTicks = nbt.getInt(OXIDATION_TIME_NBT_TAG);
        this.dataTracker.set(IS_PRESSING_COPPER_BUTTON, nbt.getBoolean(IS_PRESSING_COPPER_BUTTON_NBT_TAG));
    }

    private String getWeatherStateName(final Oxidizable.OxidationLevel weatherState) {
        return weatherState.name().toLowerCase(Locale.ROOT);
    }

    public String getCurrentWeatherStateName() {
        return this.dataTracker.get(WEATHER_STATE);
    }

    public Oxidizable.OxidationLevel getCurrentWeatherState() {
        return getWeatherState(getCurrentWeatherStateName());
    }

    private Oxidizable.OxidationLevel getWeatherState(final String weatherStateName) {
        return weatherStateName == null || weatherStateName.isEmpty() ? Oxidizable.OxidationLevel.UNAFFECTED : Oxidizable.OxidationLevel.valueOf(weatherStateName.toUpperCase(Locale.ROOT));
    }

    private void setUnoxidationState(final Oxidizable.OxidationLevel weatherState) {
        setWeatherState(weatherState, true);
        fireWorldEvent(WorldEvents.BLOCK_SCRAPED);
    }

    public void setWeatherState(final Oxidizable.OxidationLevel weatherState, boolean playSound) {
        if(weatherState.equals(Oxidizable.OxidationLevel.OXIDIZED)) {
            this.goalSelector.getRunningGoals().forEach(PrioritizedGoal::stop);
            this.goalSelector.clear(goal -> true);
        } else if(this.goalSelector.getGoals().isEmpty()) {
            this.initGoals();
        }
        this.dataTracker.set(WEATHER_STATE, getWeatherStateName(weatherState));
        if(playSound) {
            this.playSound(SoundEvents.ITEM_AXE_SCRAPE, 1.0F, 1.0F);
        }
        this.resetOxidationTime();
    }

    private boolean isInWeatherState(final Oxidizable.OxidationLevel weatherState) {
        return getCurrentWeatherState().equals(weatherState);
    }

    public boolean isOxidized() {
        return isInWeatherState(Oxidizable.OxidationLevel.OXIDIZED);
    }

    private void oxidize() {
        final Oxidizable.OxidationLevel weatherState = getCurrentWeatherState();
        if(weatherState.equals(Oxidizable.OxidationLevel.UNAFFECTED)) {
            setWeatherState(Oxidizable.OxidationLevel.EXPOSED, true);
        } else if(weatherState.equals(Oxidizable.OxidationLevel.EXPOSED)) {
            setWeatherState(Oxidizable.OxidationLevel.WEATHERED, true);
        } else if(weatherState.equals(Oxidizable.OxidationLevel.WEATHERED)) {
            setWeatherState(Oxidizable.OxidationLevel.OXIDIZED, true);
        }
    }

    private void unoxidize() {
        final Oxidizable.OxidationLevel weatherState = getCurrentWeatherState();
        if(weatherState.equals(Oxidizable.OxidationLevel.OXIDIZED)) {
            setUnoxidationState(Oxidizable.OxidationLevel.WEATHERED);
        } else if(weatherState.equals(Oxidizable.OxidationLevel.WEATHERED)) {
            setUnoxidationState(Oxidizable.OxidationLevel.EXPOSED);
        } else if(weatherState.equals(Oxidizable.OxidationLevel.EXPOSED)) {
            setUnoxidationState(Oxidizable.OxidationLevel.UNAFFECTED);
        }
    }

    public boolean isWaxed() {
        return this.dataTracker.get(IS_WAXED);
    }

    private void setWaxedBy(final boolean waxed, final PlayerEntity player, final Hand hand) {
        this.setWaxed(waxed, true);
        player.swingHand(hand);
    }

    private void setWaxed(final boolean waxed, final boolean playEffects) {
        this.dataTracker.set(IS_WAXED, waxed);
        this.resetOxidationTime();
        if(playEffects) {
            fireWorldEvent(waxed ? WorldEvents.BLOCK_WAXED : WorldEvents.WAX_REMOVED);
            this.playSound(waxed ? SoundEvents.ITEM_HONEYCOMB_WAX_ON : SoundEvents.ITEM_AXE_WAX_OFF, 1.0F, 1.0F);
        }
    }

    private void fireWorldEvent(final int event) {
        world.syncWorldEvent(null, event, this.getBlockPos(), 0);
    }

    private void resetOxidationTime() {
        this.oxidationTicks = 0;
    }

    @Override
    public float getEyeHeight(EntityPose pose) {
        return 0.8F;
    }

    @Override
    protected Identifier getLootTableId() {
        return new Identifier(BlazersMod.MOD_ID, "entities/copper_golem");
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return damageSource.isOf(DamageTypes.LIGHTNING_BOLT);
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        if(isWaxed()) {
            setWaxed(false, true);
            return;
        }
        unoxidize();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(isOxidized()) {
            return null;
        }
        return switch(random.nextInt(5)) {
            default -> SoundEvents.BLOCK_COPPER_STEP;
            case 1 -> SoundEvents.BLOCK_COPPER_FALL;
            case 2 -> SoundEvents.BLOCK_COPPER_PLACE;
            case 3 -> SoundEvents.BLOCK_COPPER_BREAK;
            case 4 -> SoundEvents.BLOCK_COPPER_HIT;
        };
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return isOxidized() ? null : SoundEvents.ITEM_AXE_WAX_OFF;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return isOxidized() ? null : SoundEvents.ITEM_AXE_SCRAPE;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if(!isOxidized()) {
            this.playSound(getAmbientSound(), 1.0F, 1.0F);
        }
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 200 + random.nextInt(100);
    }

    @Override
    public int getXpToDrop() {
        return 1 + this.world.random.nextInt(3);
    }

    @Override
    public void tickMovement() {
        if(this.isAlive()) {
            ticksSinceLastPress = Math.min(ticksSinceLastPress + 1, ticksBeforeNextPress);
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
        super.tickMovement();
    }

    @Override
    public float getMovementSpeed() {
        return switch (getCurrentWeatherState()) {
            default -> 0.3F;
            case EXPOSED -> 0.2F;
            case WEATHERED -> 0.1F;
            case OXIDIZED -> 0F;
        };
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        final ItemStack item = player.getStackInHand(hand);
        if(!item.isEmpty()) {
            if(item.getItem() instanceof AxeItem) {
                if(isWaxed()) {
                    setWaxedBy(false, player, hand);
                } else if(!isInWeatherState(Oxidizable.OxidationLevel.UNAFFECTED)) {
                    unoxidize();
                    player.swingHand(hand);
                }
            } else if(item.isOf(Items.HONEYCOMB)) {
                setWaxedBy(true, player, hand);
            }
        }
        return super.interactMob(player, hand);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if(entityNbt != null && entityNbt.contains(WEATHER_STATE_NBT_TAG)) {
            setWeatherState(getWeatherState(entityNbt.getString(WEATHER_STATE_NBT_TAG)), false);
        } else {
            setWeatherState(Oxidizable.OxidationLevel.UNAFFECTED, false);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public void setPressingCopperButton(final boolean isPressing) {
        this.currentPressingButtonTicks = pressingButtonTicks;
        this.canPressCopperButtons = !this.canPressCopperButtons;
        if(isPressing) {
            this.ticksSinceLastPress = 0;
        }
        this.dataTracker.set(IS_PRESSING_COPPER_BUTTON, isPressing);
    }

    public boolean canPressCopperButton() {
        return this.canPressCopperButtons && this.ticksSinceLastPress >= this.ticksBeforeNextPress;
    }

    public boolean isPressingCopperButton() {
        return this.dataTracker.get(IS_PRESSING_COPPER_BUTTON);
    }

    private PlayState predicate(AnimationState state) {
        if(!isOxidized()) {
            AnimationController<CopperGolemEntity> controller = state.getController();
            if(isPressingCopperButton()) {
                setAnimation(controller, "animation.copper_golem.interact", false);
            }
            else {
                setAnimation(controller, state.isMoving() ? "animation.copper_golem.walk" : "animation.copper_golem.idle", true);
            }
        }
        return PlayState.CONTINUE;
    }

    public void setAnimation(final AnimationController<CopperGolemEntity> controller, final String animation, final boolean loop) {
        controller.setAnimation(RawAnimation.begin().then(animation, loop ? Animation.LoopType.LOOP : Animation.LoopType.PLAY_ONCE));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(getAnimationController());
    }

    public AnimationController<CopperGolemEntity> getAnimationController() {
        return new AnimationController<>(this, "controller", 0, this::predicate);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    private static boolean isValidBlockForGolemSpawning(final Block block) {
        return block instanceof OxidizableCutCopperBricksBlock || block instanceof OxidizableBlock
                || isWaxedBlock(block);
    }

    private static boolean isWaxedBlock(final Block block) {
        return block.equals(Blocks.WAXED_COPPER_BLOCK) ||
                block.equals(Blocks.WAXED_EXPOSED_COPPER) || block.equals(Blocks.WAXED_WEATHERED_COPPER) ||
                block.equals(Blocks.WAXED_OXIDIZED_COPPER) || block.equals(Blocks.WAXED_CUT_COPPER) ||
                block.equals(Blocks.WAXED_EXPOSED_CUT_COPPER) || block.equals(Blocks.WAXED_WEATHERED_CUT_COPPER) ||
                block.equals(Blocks.WAXED_OXIDIZED_CUT_COPPER) || block.equals(BLBlocks.WAXED_CUT_COPPER_BRICKS) ||
                block.equals(BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS) || block.equals(BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS) ||
                block.equals(BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS);
    }

    private static Oxidizable.OxidationLevel getWeatherStateFromBlock(final Block block) {
        if(!isWaxedBlock(block)) {
            return ((OxidizableBlock)block).getDegradationLevel();
        }
        if(block.equals(Blocks.WAXED_OXIDIZED_COPPER) || block.equals(Blocks.WAXED_OXIDIZED_CUT_COPPER)) {
            return Oxidizable.OxidationLevel.UNAFFECTED;
        }
        if(block.equals(Blocks.WAXED_EXPOSED_COPPER) || block.equals(Blocks.WAXED_EXPOSED_CUT_COPPER)) {
            return Oxidizable.OxidationLevel.EXPOSED;
        }
        if(block.equals(Blocks.WAXED_WEATHERED_COPPER) || block.equals(Blocks.WAXED_WEATHERED_CUT_COPPER)) {
            return Oxidizable.OxidationLevel.WEATHERED;
        }
        return Oxidizable.OxidationLevel.UNAFFECTED;
    }

    public static void trySpawnGolem(final World world, final BlockPos pos, final PlayerEntity player, final ItemStack itemStack, final Hand hand) {
        Block below = world.getBlockState(pos.down()).getBlock();
        boolean isWaxed = isWaxedBlock(below);
        if(isValidBlockForGolemSpawning(below)) {
            world.breakBlock(pos, false);
            world.breakBlock(pos.down(), false);
            CopperGolemEntity copperGolem = new CopperGolemEntity(BLEntityTypes.COPPER_GOLEM, world);
            copperGolem.setPos(pos.getX() + 0.5F, pos.getY() - 0.5F, pos.getZ() + 0.5F);
            copperGolem.setWeatherState(getWeatherStateFromBlock(below), false);
            copperGolem.setWaxed(isWaxed, false);
            world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);
            if(player != null) {
                copperGolem.setPersistent();
                player.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 1.0F, 1.0F);
                player.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.0F, 1.0F);
                if (player instanceof ServerPlayerEntity) {
                    Criteria.SUMMONED_ENTITY.trigger((ServerPlayerEntity) player, copperGolem);
                }
                if(!itemStack.isEmpty()) {
                    if(!player.isCreative()) {
                        itemStack.decrement(1);
                    }
                    if(world.isClient && hand != null) {
                        player.swingHand(hand);
                    }
                }
            }
            world.spawnEntity(copperGolem);
        }
    }
}
