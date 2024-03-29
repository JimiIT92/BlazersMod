package org.blazers.block;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.blazers.event.EventUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod} {@link Block Pointed Dripstone Block}
 */
public class BLPointedDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {

    /**
     * Dripstone Blocks from {@link Block Material Blocks}
     */
    static Supplier<BiMap<Block, Block>> DRIPSTONES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.STONE, BLBlocks.POINTED_STONE_DRIPSTONE.get())
            .put(Blocks.GRANITE, BLBlocks.POINTED_GRANITE_DRIPSTONE.get())
            .put(Blocks.DIORITE, BLBlocks.POINTED_DIORITE_DRIPSTONE.get())
            .put(Blocks.ANDESITE, BLBlocks.POINTED_ANDESITE_DRIPSTONE.get())
            .put(Blocks.ICE, BLBlocks.POINTED_ICE_DRIPSTONE.get())
            .build()
    );

    /**
     * {@link DirectionProperty Tip Direction}
     */
    public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    /**
     * {@link DripstoneThickness Thickness Value}
     */
    public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    /**
     * {@link BooleanProperty Waterlogged Property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape Tip Merge Shape}
     */
    private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    /**
     * {@link VoxelShape Tip Shape Up}
     */
    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
    /**
     * {@link VoxelShape Tip Shape Down}
     */
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    /**
     * {@link VoxelShape Frustum Shape}
     */
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    /**
     * {@link VoxelShape Middle Shape}
     */
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    /**
     * {@link VoxelShape Base Shape}
     */
    private static final VoxelShape BASE_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    /**
     * {@link VoxelShape Space to drip through non solid block}
     */
    private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    /**
     * The {@link Block Block} the Dripstone is based on
     */
    private final Block MATERIAL_BLOCK;

    /**
     * Constructor. Sets the {@link Block Pointed Dripstone} properties
     *
     * @param materialBlock {@link Block Material Block}
     */
    public BLPointedDripstoneBlock(Block materialBlock) {
        super(BlockBehaviour.Properties.of().mapColor(materialBlock.defaultMapColor()).noOcclusion().sound(materialBlock.defaultBlockState().getSoundType()).randomTicks().strength(0.5F, 0.5F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ));
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, Boolean.FALSE));
        this.MATERIAL_BLOCK = materialBlock;
    }

    /**
     * Create the {@link BlockState Block State Definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder State Definition Builder}
     */
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    /**
     * Check if the {@link Block Dripstone} can survive at the given {@link BlockPos Position}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link Block Dripstone} can survive at the given {@link BlockPos Position}
     */
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return isValidPointedDripstonePlacement(level, pos, state.getValue(TIP_DIRECTION));
    }

    /**
     * Updated the {@link BlockState Block State} if the
     * {@link Block Dripstone} is waterlogged or the neighbor changes
     *
     * @param state {@link BlockState Block State}
     * @param facing {@link Direction Direction}
     * @param facingState {@link BlockState Facing Block State}
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param facingPos {@link BlockPos Facing Block Pos}
     * @return Placed {@link BlockState Block State}
     */
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (facing != Direction.UP && facing != Direction.DOWN) {
            return state;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            if (direction == Direction.DOWN && level.getBlockTicks().hasScheduledTick(pos, this)) {
                return state;
            } else if (facing == direction.getOpposite() && !this.canSurvive(state, level, pos)) {
                if (direction == Direction.DOWN) {
                    level.scheduleTick(pos, this, 2);
                } else {
                    level.scheduleTick(pos, this, 1);
                }

                return state;
            } else {
                boolean flag = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstonethickness = calculateDripstoneThickness(level, pos, direction, flag);
                return state.setValue(THICKNESS, dripstonethickness);
            }
        }
    }



    /**
     * Destroy the {@link Block Dripstone} when hit by a {@link Projectile projectile}
     *
     * @param level {@link Level World reference}
     * @param state {@link BlockState Block State}
     * @param hitResult {@link BlockHitResult Block Hit Result}
     * @param projectile {@link Projectile Projectile}
     */
    public void onProjectileHit(Level level, @NotNull BlockState state, BlockHitResult hitResult, @NotNull Projectile projectile) {
        BlockPos blockpos = hitResult.getBlockPos();
        if (!level.isClientSide && projectile.mayInteract(level, blockpos) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > 0.6D) {
            level.destroyBlock(blockpos, true);
        }
    }

    /**
     * Cause fall damage when the {@link Block Dripstone} falls
     *
     * @param level {@link Level World reference}
     * @param state {@link BlockState Block State}
     * @param pos {@link BlockPos Block Pos}
     * @param entity {@link Entity Entity}
     * @param fallDistance {@link Float Fall distance}
     */
    public void fallOn(@NotNull Level level, BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP) {
            entity.causeFallDamage(fallDistance + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    /**
     * Shows the drip particles
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link RandomSource Random variable}
     */
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (canDrip(state)) {
            float randomFloat = random.nextFloat();
            if (!(randomFloat > 0.12F)) {
                getFluidAboveStalactite(level, pos, state).filter(fluid -> randomFloat < 0.02F || canFillCauldron(fluid)).ifPresent(fluid -> spawnDripParticle(level, pos, state, fluid));
            }
        }
    }

    /**
     * Tick the {@link Block Dripstone Block}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link RandomSource Random variable}
     */
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (isStalagmite(state) && !this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        } else {
            spawnFallingStalactite(state, level, pos);
        }
    }

    /**
     * Tick the {@link Block Dripstone Block} randomly
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link RandomSource Random variable}
     */
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        maybeFillCauldron(state, level, pos, random.nextFloat());
        if (random.nextFloat() < 0.011377778F && isStalactiteStartPos(state, level, pos)) {
            growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
        }

    }

    /**
     * Check if the {@link Block Dripstone} can fill the cauldron
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param distance {@link Float Distance from cauldron}
     */
    @VisibleForTesting
    public void maybeFillCauldron(BlockState state, ServerLevel level, BlockPos pos, float distance) {
        if (!(distance > 0.17578125F)) {
            if (isStalactiteStartPos(state, level, pos)) {
                Fluid fluid = getCauldronFillFluidType(level, pos);
                float cauldronDistance;
                if (fluid == Fluids.WATER) {
                    cauldronDistance = 0.17578125F;
                } else {
                    if (fluid != Fluids.LAVA) {
                        return;
                    }

                    cauldronDistance = 0.05859375F;
                }

                if (!(distance >= cauldronDistance)) {
                    BlockPos blockpos = findTip(state, level, pos, 11, false);
                    if (blockpos != null) {
                        BlockPos blockpos1 = findFillableCauldronBelowStalactiteTip(level, blockpos);
                        if (blockpos1 != null) {
                            BlockState cauldronBlockState = level.getBlockState(blockpos1);
                            Block cauldron = cauldronBlockState.getBlock();
                            if(cauldron instanceof AbstractCauldronBlock && !((AbstractCauldronBlock)cauldron).isFull(cauldronBlockState))  {
                                if(cauldronBlockState.is(Blocks.CAULDRON)) {
                                    if(Fluids.LAVA.equals(fluid)) {
                                        level.setBlock(blockpos1, Blocks.LAVA_CAULDRON.defaultBlockState(), 11);
                                    } else {
                                        level.setBlock(blockpos1, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1), 11);
                                    }
                                } else if(cauldronBlockState.is(Blocks.WATER_CAULDRON)) {
                                    cauldronBlockState.setValue(LayeredCauldronBlock.LEVEL, cauldronBlockState.getValue(LayeredCauldronBlock.LEVEL) + 1);
                                }
                            }
                            level.scheduleTick(blockpos1, cauldron, 50 + (blockpos.getY() - blockpos1.getY()));
                        }
                    }
                }
            }
        }
    }

    /**
     * Destroy the {@link Block Dripstone} on push by pistons
     *
     * @param state {@link BlockState Block State}
     * @return {@link PushReaction Piston push reaction}
     */
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState state) {
        return PushReaction.DESTROY;
    }

    /**
     * Get the {@link BlockState Block State} when the
     * {@link Block Dripstone} is placed
     *
     * @param context {@link BlockPlaceContext Place Context}
     * @return Placed {@link BlockState Block State}
     */
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getNearestLookingVerticalDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if (direction1 == null) {
            return null;
        } else {
            boolean flag = !context.isSecondaryUseActive();
            DripstoneThickness dripstonethickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
            return dripstonethickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, direction1).setValue(THICKNESS, dripstonethickness).setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
        }
    }

    /**
     * Get the {@link FluidState Fluid State}
     *
     * @param state {@link BlockState Block State}
     * @return {@link FluidState Fluid State}
     */
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Get the {@link VoxelShape Occlusion Shape}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link VoxelShape Occlusion Shape}
     */
    public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return Shapes.empty();
    }

    /**
     * Get the {@link Block Dripstone} {@link VoxelShape Shape}
     * based on its properties
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param context {@link CollisionContext Collision Context}
     * @return {@link RotatedPillarBlock Block} {@link VoxelShape Shape}
     */
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        DripstoneThickness dripstonethickness = state.getValue(THICKNESS);
        VoxelShape voxelshape;
        if (dripstonethickness == DripstoneThickness.TIP_MERGE) {
            voxelshape = TIP_MERGE_SHAPE;
        } else if (dripstonethickness == DripstoneThickness.TIP) {
            if (state.getValue(TIP_DIRECTION) == Direction.DOWN) {
                voxelshape = TIP_SHAPE_DOWN;
            } else {
                voxelshape = TIP_SHAPE_UP;
            }
        } else if (dripstonethickness == DripstoneThickness.FRUSTUM) {
            voxelshape = FRUSTUM_SHAPE;
        } else if (dripstonethickness == DripstoneThickness.MIDDLE) {
            voxelshape = MIDDLE_SHAPE;
        } else {
            voxelshape = BASE_SHAPE;
        }

        Vec3 vec3 = state.getOffset(level, pos);
        return voxelshape.move(vec3.x, 0.0D, vec3.z);
    }

    /**
     * Check if the {@link VoxelShape Collision Shape} represents a full block
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link VoxelShape Collision Shape} represents a full block
     */
    public boolean isCollisionShapeFullBlock(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return false;
    }

    /**
     * Get the {@link Float Maximum horizontal offset}
     *
     * @return {@link Float Maximum horizontal offset}
     */
    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    /**
     * Get the {@link DamageSource Fall Damage Source}
     *
     * @return {@link DamageSource Fall Damage Source}
     */
    public @NotNull DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    /**
     * Get the {@link Predicate <Entity> Entity Predicate} for the damage functions
     *
     * @return {@link Predicate<Entity> Entity Predicate} for the damage functions
     */
    public @NotNull Predicate<Entity> getHurtsEntitySelector() {
        return EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
    }

    /**
     * Spawn the {@link FallingBlockEntity Falling Dripstones} when is falling
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     */
    private void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();

        for(BlockState blockstate = state; isStalactite(blockstate); blockstate = level.getBlockState(mutableBlockPos)) {
            FallingBlockEntity fallingblockentity = fall(level, mutableBlockPos, blockstate);
            if (isTip(blockstate, true)) {
                float f = Math.max(1 + pos.getY() - mutableBlockPos.getY(), 6);
                fallingblockentity.setHurtsEntities(f, 40);
                break;
            }

            mutableBlockPos.move(Direction.DOWN);
        }
    }

    /**
     * Spawn a {@link FallingBlockEntity Falling Dripstone} that doesn't drop anything when it breaks
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @return {@link FallingBlockEntity Falling Dripstone}
     */
    public FallingBlockEntity fall(Level level, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingblockentity = getFallingBlockEntity(level, pos, state);
        level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }

    /**
     * Grow the {@link Block Dripstone} if possible
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param random {@link RandomSource Random variable}
     */
    @VisibleForTesting
    public void growStalactiteOrStalagmiteIfPossible(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = level.getBlockState(pos.above(1));
        BlockState blockstate1 = level.getBlockState(pos.above(2));
        if (canGrow(blockstate, blockstate1)) {
            BlockPos blockpos = findTip(state, level, pos, 7, false);
            if (blockpos != null) {
                BlockState blockstate2 = level.getBlockState(blockpos);
                if (canDrip(blockstate2) && canTipGrow(blockstate2, level, blockpos)) {
                    if (random.nextBoolean()) {
                        grow(level, blockpos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, blockpos);
                    }

                }
            }
        }
    }

    /**
     * Grow a {@link Block Dripstone} below
     *
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     */
    private void growStalagmiteBelow(ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();

        for(int i = 0; i < 10; ++i) {
            mutableBlockPos.move(Direction.DOWN);
            BlockState blockstate = level.getBlockState(mutableBlockPos);
            if (!blockstate.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection(blockstate, Direction.UP) && canTipGrow(blockstate, level, mutableBlockPos)) {
                grow(level, mutableBlockPos, Direction.UP);
                return;
            }

            if (isValidPointedDripstonePlacement(level, mutableBlockPos, Direction.UP) && !level.isWaterAt(mutableBlockPos.below())) {
                grow(level, mutableBlockPos.below(), Direction.UP);
                return;
            }

            if (!canDripThrough(level, mutableBlockPos, blockstate)) {
                return;
            }
        }
    }

    /**
     * Grow a {@link Block Dripstone}
     *
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Direction}
     */
    private void grow(ServerLevel level, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);
        if (isUnmergedTipWithDirection(blockstate, direction.getOpposite())) {
            createMergedTips(blockstate, level, blockpos);
        } else if (blockstate.isAir() || blockstate.is(Blocks.WATER)) {
            createDripstone(level, blockpos, direction, DripstoneThickness.TIP);
        }
    }

    /**
     * Create a {@link Block Dripstone} at the given {@link BlockPos position}
     *
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Direction}
     * @param thickness {@link DripstoneThickness Dripstone Thickness}
     */
    private void createDripstone(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
        BlockState blockstate = getDripstone().defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
        level.setBlock(pos, blockstate, 3);
    }

    /**
     * Create a {@link Block Merged Dripstone}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     */
    private void createMergedTips(BlockState state, LevelAccessor level, BlockPos pos) {
        BlockPos blockpos;
        BlockPos blockpos1;
        if (state.getValue(TIP_DIRECTION) == Direction.UP) {
            blockpos1 = pos;
            blockpos = pos.above();
        } else {
            blockpos = pos;
            blockpos1 = pos.below();
        }

        createDripstone(level, blockpos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(level, blockpos1, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    /**
     * Spawn the drip particles
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @param fluid {@link Fluid Fluid}
     */
    private static void spawnDripParticle(Level level, BlockPos pos, BlockState state, Fluid fluid) {
        Vec3 vec3 = state.getOffset(level, pos);
        double d1 = (double)pos.getX() + 0.5D + vec3.x;
        double d2 = (double)((float)(pos.getY() + 1) - 0.6875F) - 0.0625D;
        double d3 = (double)pos.getZ() + 0.5D + vec3.z;
        Fluid dripFluid = getDripFluid(level, fluid);
        ParticleOptions particleoptions = dripFluid.is(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        level.addParticle(particleoptions, d1, d2, d3, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Find the {@link Block Dripstone Tip}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param distance {@link Integer Distance}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link BlockPos Tip Block Pos}
     */
    @Nullable
    private BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int distance, boolean shouldMerge) {
        if (isTip(state, shouldMerge)) {
            return pos;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> bipredicate = (pos1, blockState) -> blockState.is(getDripstone()) && blockState.getValue(TIP_DIRECTION) == direction;
            return findBlockVertical(level, pos, direction.getAxisDirection(), bipredicate, blockState -> isTip(blockState, shouldMerge), distance).orElse(null);
        }
    }

    /**
     * Calculate the {@link Direction Tip direction}
     *
     * @param level {@link LevelReader World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Direction}
     * @return {@link Direction Tip direction}
     */
    @Nullable
    private Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction direction) {
        if (isValidPointedDripstonePlacement(level, pos, direction)) {
            return direction;
        } else {
            if (!isValidPointedDripstonePlacement(level, pos, direction.getOpposite())) {
                return null;
            }

            return direction.getOpposite();
        }
    }

    /**
     * Calculate the {@link DripstoneThickness Dripstone Thickness}
     *
     * @param level {@link LevelReader World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Direction}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link DripstoneThickness Dripstone Thickness}
     */
    private DripstoneThickness calculateDripstoneThickness(LevelReader level, BlockPos pos, Direction direction, boolean shouldMerge) {
        Direction opposite = direction.getOpposite();
        BlockState blockstate = level.getBlockState(pos.relative(direction));
        if (isPointedDripstoneWithDirection(blockstate, opposite)) {
            return !shouldMerge && blockstate.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        } else if (!isPointedDripstoneWithDirection(blockstate, direction)) {
            return DripstoneThickness.TIP;
        } else {
            DripstoneThickness dripstonethickness = blockstate.getValue(THICKNESS);
            if (dripstonethickness != DripstoneThickness.TIP && dripstonethickness != DripstoneThickness.TIP_MERGE) {
                BlockState blockstate1 = level.getBlockState(pos.relative(opposite));
                return !isPointedDripstoneWithDirection(blockstate1, direction) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
                return DripstoneThickness.FRUSTUM;
            }
        }
    }

    /**
     * Check if the {@link Block Dripstone} should drip
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link Block Dripstone} should drip
     */
    public boolean canDrip(BlockState state) {
        return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !state.getValue(WATERLOGGED);
    }

    /**
     * Check if the {@link Block Dripstone Tip} can grow
     *
     * @param state {@link BlockState Block State}
     * @param level {@link ServerLevel World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link Block Dripstone Tip} can grow
     */
    private boolean canTipGrow(BlockState state, ServerLevel level, BlockPos pos) {
        Direction direction = state.getValue(TIP_DIRECTION);
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);
        if (!blockstate.getFluidState().isEmpty()) {
            return false;
        } else {
            return blockstate.isAir() || isUnmergedTipWithDirection(blockstate, direction.getOpposite());
        }
    }

    /**
     * Get the {@link Optional <BlockPos> Dripstone root Block Pos}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @param distance {@link Integer Distance}
     * @return {@link Optional<BlockPos> Dripstone root Block Pos}
     */
    private Optional<BlockPos> findRootBlock(Level level, BlockPos pos, BlockState state, int distance) {
        Direction direction = state.getValue(TIP_DIRECTION);
        BiPredicate<BlockPos, BlockState> bipredicate = (pos1, blockState) -> blockState.is(getDripstone()) && blockState.getValue(TIP_DIRECTION) == direction;
        return findBlockVertical(level, pos, direction.getOpposite().getAxisDirection(), bipredicate, blockState -> !blockState.is(getDripstone()), distance);
    }

    /**
     * Check if the {@link BlockPos Current Location} is valid for the {@link Block Dripstone} placement
     *
     * @param level {@link LevelReader World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction Direction}
     * @return {@link Boolean True} if the {@link BlockPos Current Location} is valid for the {@link Block Dripstone} placement
     */
    private boolean isValidPointedDripstonePlacement(LevelReader level, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, direction) || isPointedDripstoneWithDirection(blockstate, direction);
    }

    /**
     * Check if the {@link BlockState Current Block State} is a tip
     *
     * @param state {@link BlockState Block State}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link Boolean True} if the {@link BlockState Current Block State} is a tip
     */
    private boolean isTip(BlockState state, boolean shouldMerge) {
        if (!state.is(getDripstone())) {
            return false;
        } else {
            DripstoneThickness dripstonethickness = state.getValue(THICKNESS);
            return dripstonethickness == DripstoneThickness.TIP || shouldMerge && dripstonethickness == DripstoneThickness.TIP_MERGE;
        }
    }

    /**
     * Check if the {@link BlockState Current Block State} is an unmerged tip
     *
     * @param state {@link BlockState Block State}
     * @param direction {@link Direction Direction}
     * @return {@link Boolean True} if the {@link BlockState Current Block State} is an unmerged tip
     */
    private boolean isUnmergedTipWithDirection(BlockState state, Direction direction) {
        return isTip(state, false) && state.getValue(TIP_DIRECTION) == direction;
    }

    /**
     * Check if the {@link BlockState Current Block State} is a stalactite
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link BlockState Current Block State} is a stalactite
     */
    private boolean isStalactite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.DOWN);
    }

    /**
     * Check if the {@link BlockState Current Block State} is a stalagmite
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link BlockState Current Block State} is a stalagmite
     */
    private boolean isStalagmite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.UP);
    }

    /**
     * Check if the {@link BlockPos Current Block Pos} is a stalactite starting pos
     *
     * @param pState {@link BlockState Block State}
     * @param pLevel {@link LevelReader World reference}
     * @param pPos {@link BlockPos Block Pos}
     * @return {@link Boolean True} if the {@link BlockPos Current Block Pos} is a stalactite starting pos
     */
    private boolean isStalactiteStartPos(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return isStalactite(pState) && !pLevel.getBlockState(pPos.above()).is(getDripstone());
    }

    /**
     * Check if the {@link Block Dripstone} is pathfindable by mobs
     *
     * @param state {@link BlockState Block State}
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param pathComputationType {@link PathComputationType Path Computation Type}
     * @return {@link Boolean True} if the {@link Block Dripstone} is pathfindable by mobs
     */
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Check if the {@link BlockState Current Block State} is a {@link Block Pointed Dripstone}
     *
     * @param state {@link BlockState Block State}
     * @param direction {@link Direction Direction}
     * @return {@link Boolean True} if the {@link BlockState Current Block State} is a {@link Block Pointed Dripstone}
     */
    private boolean isPointedDripstoneWithDirection(BlockState state, Direction direction) {
        return state.is(getDripstone()) && state.getValue(TIP_DIRECTION) == direction;
    }

    /**
     * Find a {@link AbstractCauldronBlock Cauldron} to fill
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link BlockPos Cauldron Block Pos}
     */
    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos) {
        Predicate<BlockState> predicate = blockState -> blockState.getBlock() instanceof AbstractCauldronBlock;
        BiPredicate<BlockPos, BlockState> bipredicate = (pos1, blockState) -> canDripThrough(level, pos1, blockState);
        return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse(null);
    }

    /**
     * Get the {@link Fluid Cauldron Fill Fluid}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @return {@link Fluid Cauldron Fill Fluid}
     */
    public Fluid getCauldronFillFluidType(Level level, BlockPos pos) {
        return getFluidAboveStalactite(level, pos, level.getBlockState(pos)).filter(this::canFillCauldron).orElse(Fluids.EMPTY);
    }

    /**
     * Get the {@link Optional<Fluid> Fluid} above the stalactite
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @return {@link Optional<Fluid> Fluid} above the stalactite
     */
    private Optional<Fluid> getFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
        return !isStalactite(state) ? Optional.empty() : findRootBlock(level, pos, state, 11).map(pos1 -> level.getFluidState(pos1.above()).getType());
    }

    /**
     * Check if the {@link Block Dripstone} can fill a {@link AbstractCauldronBlock Cauldron}
     *
     * @param fluid {@link Fluid Cauldron Fill Fluid}
     * @return {@link Boolean True} if the {@link Block Dripstone} can fill a {@link AbstractCauldronBlock Cauldron}
     */
    private boolean canFillCauldron(Fluid fluid) {
        return fluid == Fluids.LAVA || fluid == Fluids.WATER;
    }

    /**
     * Check if the {@link Block Dripstone} can grow
     *
     * @param state {@link BlockState Block State}
     * @param adjacentState {@link BlockState Adjacent Block State}
     * @return {@link Boolean True} if the {@link Block Dripstone} can grow
     */
    private static boolean canGrow(BlockState state, BlockState adjacentState) {
        return state.is(Blocks.ICE) && adjacentState.is(Blocks.WATER) && adjacentState.getFluidState().isSource();
    }

    /**
     * Get the {@link Fluid Drip Fluid}
     *
     * @param level {@link Level World reference}
     * @param fluid {@link Fluid Fluid}
     * @return {@link Fluid Drip Fluid}
     */
    private static Fluid getDripFluid(Level level, Fluid fluid) {
        if (fluid.isSame(Fluids.EMPTY)) {
            return level.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER;
        } else {
            return fluid;
        }
    }

    /**
     * Find another {@link Block Dripstone} above or below
     *
     * @param level {@link LevelAccessor World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param direction {@link Direction.AxisDirection Axis Direction}
     * @param posBlockStateBiPredicate {@link BiPredicate BlockPos/BlockState BiPredicate}
     * @param statePredicate {@link Predicate<BlockState> BlockState Predicate}
     * @param distance {@link Integer Distance}
     * @return {@link Optional<BlockPos> Dripstone Block Pos}
     */
    private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> posBlockStateBiPredicate, Predicate<BlockState> statePredicate, int distance) {
        Direction axisDirection = Direction.get(direction, Direction.Axis.Y);
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();

        for(int i = 1; i < distance; ++i) {
            mutableBlockPos.move(axisDirection);
            BlockState blockstate = level.getBlockState(mutableBlockPos);
            if (statePredicate.test(blockstate)) {
                return Optional.of(mutableBlockPos.immutable());
            }

            if (level.isOutsideBuildHeight(mutableBlockPos.getY()) || !posBlockStateBiPredicate.test(mutableBlockPos, blockstate)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    /**
     * Check if the {@link Block Dripstone} can drip through another {@link Block Block}
     *
     * @param level {@link BlockGetter World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link Block Dripstone} can drip through another {@link Block Block}
     */
    private static boolean canDripThrough(BlockGetter level, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        } else if (state.isSolidRender(level, pos)) {
            return false;
        } else if (!state.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelshape = state.getCollisionShape(level, pos);
            return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelshape, BooleanOp.AND);
        }
    }

    /**
     * Get the {@link Block Pointed Dripstone Block}
     *
     * @return {@link Block Pointed Dripstone Block}
     */
    public Block getDripstone() {
        return getDripstoneFor(this.MATERIAL_BLOCK);
    }

    /**
     * Get a {@link Block Pointed Dripstone Block} from the given {@link Block Block}
     *
     * @return {@link Block Pointed Dripstone Block}
     */
    public static Block getDripstoneFor(final Block block) {
        Optional<Block> dripstone = Optional.ofNullable(DRIPSTONES.get().get(block));
        return dripstone.orElse(Blocks.POINTED_DRIPSTONE);
    }

    /**
     * Drop the {@link Block falling dripstones} after they fall
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param fallingBlockEntity {@link FallingBlockEntity Falling Block Entity}
     */
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            EventUtils.fireWorldEvent(level, null, EventUtils.WorldEvents.POINTED_DRIPSTONE_FALLING, pos);
        }
    }

    /**
     * Get the {@link FallingBlockEntity Falling Dripstone Entity}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param state {@link BlockState Block State}
     * @return {@link FallingBlockEntity Falling Dripstone Entity}
     */
    public FallingBlockEntity getFallingBlockEntity(Level level, BlockPos pos, BlockState state) {
        return new FallingBlockEntity(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE) : state);
    }
}