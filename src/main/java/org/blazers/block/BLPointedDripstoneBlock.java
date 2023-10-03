package org.blazers.block;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.*;
import net.minecraft.block.enums.Thickness;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.blazers.core.BLBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BLPointedDripstoneBlock extends Block implements LandingBlock, Waterloggable {

    static Supplier<BiMap<Block, Block>> DRIPSTONES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.STONE, BLBlocks.POINTED_STONE_DRIPSTONE)
            .put(Blocks.GRANITE, BLBlocks.POINTED_GRANITE_DRIPSTONE)
            .put(Blocks.ANDESITE, BLBlocks.POINTED_ANDESITE_DRIPSTONE)
            .put(Blocks.DIORITE, BLBlocks.POINTED_DIORITE_DRIPSTONE)
            .put(Blocks.ICE, BLBlocks.POINTED_ICE_DRIPSTONE)
            .build()
    );
    public static final DirectionProperty VERTICAL_DIRECTION = Properties.VERTICAL_DIRECTION;
    public static final EnumProperty<Thickness> THICKNESS = Properties.THICKNESS;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final int field_31205 = 11;
    private static final int field_31207 = 2;
    private static final float field_31208 = 0.02f;
    private static final float field_31209 = 0.12f;
    private static final int field_31210 = 11;
    private static final float field_31211 = 0.17578125f;
    private static final float field_31212 = 0.05859375f;
    private static final double field_31213 = 0.6;
    private static final float field_31214 = 1.0f;
    private static final int field_31215 = 40;
    private static final int field_31200 = 6;
    private static final float field_31201 = 2.0f;
    private static final int field_31202 = 2;
    private static final float field_33566 = 5.0f;
    private static final float field_33567 = 0.011377778f;
    private static final int MAX_STALACTITE_GROWTH = 7;
    private static final int STALACTITE_FLOOR_SEARCH_RANGE = 10;
    private static final float field_31203 = 0.6875f;
    private static final VoxelShape TIP_MERGE_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape UP_TIP_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
    private static final VoxelShape DOWN_TIP_SHAPE = Block.createCuboidShape(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    private static final VoxelShape FRUSTUM_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    private static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    private static final float field_31204 = 0.125f;
    private static final VoxelShape DRIP_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    private final Block MATERIAL_BLOCK;

    public BLPointedDripstoneBlock(Block materialBlock) {
        super(AbstractBlock.Settings.create().mapColor(materialBlock.getDefaultMapColor()).nonOpaque().sounds(materialBlock.getDefaultState().getSoundGroup()).ticksRandomly().strength(1.5f, 6.0f).dynamicBounds().offset(AbstractBlock.OffsetType.XZ));
        this.setDefaultState(this.stateManager.getDefaultState().with(VERTICAL_DIRECTION, Direction.UP).with(THICKNESS, Thickness.TIP).with(WATERLOGGED, false));
        this.MATERIAL_BLOCK = materialBlock;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VERTICAL_DIRECTION, THICKNESS, WATERLOGGED);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAtWithDirection(world, pos, state.get(VERTICAL_DIRECTION));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction != Direction.UP && direction != Direction.DOWN) {
            return state;
        }
        Direction direction2 = state.get(VERTICAL_DIRECTION);
        if (direction2 == Direction.DOWN && world.getBlockTickScheduler().isQueued(pos, this)) {
            return state;
        }
        if (direction == direction2.getOpposite() && !this.canPlaceAt(state, world, pos)) {
            if (direction2 == Direction.DOWN) {
                world.scheduleBlockTick(pos, this, 2);
            } else {
                world.scheduleBlockTick(pos, this, 1);
            }
            return state;
        }
        boolean bl = state.get(THICKNESS) == Thickness.TIP_MERGE;
        Thickness thickness = getThickness(world, pos, direction2, bl);
        return state.with(THICKNESS, thickness);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (!world.isClient && projectile.canModifyAt(world, blockPos) && projectile instanceof TridentEntity && projectile.getVelocity().length() > 0.6) {
            world.breakBlock(blockPos, true);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (state.get(VERTICAL_DIRECTION) == Direction.UP && state.get(THICKNESS) == Thickness.TIP) {
            entity.handleFallDamage(fallDistance + 2.0f, 2.0f, world.getDamageSources().stalagmite());
        } else {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!canDrip(state)) {
            return;
        }
        float f = random.nextFloat();
        if (f > 0.12f) {
            return;
        }
        getFluid(world, pos, state).filter(fluid -> f < 0.02f || isFluidLiquid(fluid.fluid)).ifPresent(fluid -> createParticle(world, pos, state, fluid.fluid));
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (isPointingUp(state) && !this.canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
        } else {
            spawnFallingBlock(state, world, pos);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        dripTick(state, world, pos, random.nextFloat());
        if (random.nextFloat() < 0.011377778f && isHeldByPointedDripstone(state, world, pos)) {
            tryGrow(state, world, pos, random);
        }
    }

    @VisibleForTesting
    public void dripTick(BlockState state, ServerWorld world, BlockPos pos, float dripChance) {
        float f;
        if (dripChance > 0.17578125f && dripChance > 0.05859375f) {
            return;
        }
        if (!isHeldByPointedDripstone(state, world, pos)) {
            return;
        }
        Optional<DrippingFluid> optional = getFluid(world, pos, state);
        if (optional.isEmpty()) {
            return;
        }
        Fluid fluid = optional.get().fluid;
        if (fluid == Fluids.WATER) {
            f = 0.17578125f;
        } else if (fluid == Fluids.LAVA) {
            f = 0.05859375f;
        } else {
            return;
        }
        if (dripChance >= f) {
            return;
        }
        BlockPos blockPos = getTipPos(state, world, pos, 11, false);
        if (blockPos == null) {
            return;
        }
        if (optional.get().sourceState.isOf(Blocks.MUD) && fluid == Fluids.WATER) {
            BlockState blockState = Blocks.CLAY.getDefaultState();
            world.setBlockState(optional.get().pos, blockState);
            Block.pushEntitiesUpBeforeBlockChange(optional.get().sourceState, blockState, world, optional.get().pos);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS, blockPos, 0);
            return;
        }
        BlockPos blockPos2 = getCauldronPos(world, blockPos, fluid);
        if (blockPos2 == null) {
            return;
        }
        world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS, blockPos, 0);
        int i = blockPos.getY() - blockPos2.getY();
        int j = 50 + i;
        BlockState blockState2 = world.getBlockState(blockPos2);
        world.scheduleBlockTick(blockPos2, blockState2.getBlock(), j);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction;
        BlockPos blockPos;
        World worldAccess = ctx.getWorld();
        Direction direction2 = getDirectionToPlaceAt(worldAccess, blockPos = ctx.getBlockPos(), ctx.getVerticalPlayerLookDirection().getOpposite());
        if (direction2 == null) {
            return null;
        }
        boolean bl = !ctx.shouldCancelInteraction();
        Thickness thickness = getThickness(worldAccess, blockPos, direction2, bl);
        if (thickness == null) {
            return null;
        }
        return this.getDefaultState().with(VERTICAL_DIRECTION, direction2).with(THICKNESS, thickness).with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Thickness thickness = state.get(THICKNESS);
        VoxelShape voxelShape = thickness == Thickness.TIP_MERGE ? TIP_MERGE_SHAPE : (thickness == Thickness.TIP ? (state.get(VERTICAL_DIRECTION) == Direction.DOWN ? DOWN_TIP_SHAPE : UP_TIP_SHAPE) : (thickness == Thickness.FRUSTUM ? BASE_SHAPE : (thickness == Thickness.MIDDLE ? FRUSTUM_SHAPE : MIDDLE_SHAPE)));
        Vec3d vec3d = state.getModelOffset(world, pos);
        return voxelShape.offset(vec3d.x, 0.0, vec3d.z);
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public float getMaxHorizontalModelOffset() {
        return 0.125f;
    }

    @Override
    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingStalactite(attacker);
    }

    private void spawnFallingBlock(BlockState state, ServerWorld world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        BlockState blockState = state;
        while (isPointingDown(blockState)) {
            FallingBlockEntity fallingBlockEntity = fall(world, mutable, blockState);
            if (isTip(blockState, true)) {
                int i = Math.max(1 + pos.getY() - mutable.getY(), 6);
                fallingBlockEntity.setHurtEntities((float) i, 40);
                break;
            }
            mutable.move(Direction.DOWN);
            blockState = world.getBlockState(mutable);
        }
    }

    public FallingBlockEntity fall(World world, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingBlockEntity = getFallingBlockEntity(world, pos, state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), Block.NOTIFY_ALL);
        world.spawnEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    @VisibleForTesting
    public void tryGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState blockState2;
        BlockState blockState = world.getBlockState(pos.up(1));
        if (!canGrow(blockState, world.getBlockState(pos.up(2)))) {
            return;
        }
        BlockPos blockPos = getTipPos(state, world, pos, 7, false);
        if (blockPos == null) {
            return;
        }
        BlockState blockState3 = world.getBlockState(blockPos);
        if (!canDrip(blockState3) || !canGrow(blockState3, world, blockPos)) {
            return;
        }
        if (random.nextBoolean()) {
            tryGrow(world, blockPos, Direction.DOWN);
        } else {
            tryGrowStalagmite(world, blockPos);
        }
    }

    private void tryGrowStalagmite(ServerWorld world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (int i = 0; i < 10; ++i) {
            mutable.move(Direction.DOWN);
            BlockState blockState = world.getBlockState(mutable);
            if (!blockState.getFluidState().isEmpty()) {
                return;
            }
            if (isTip(blockState, Direction.UP) && canGrow(blockState, world, mutable)) {
                tryGrow(world, mutable, Direction.UP);
                return;
            }
            if (canPlaceAtWithDirection(world, mutable, Direction.UP) && !world.isWater(mutable.down())) {
                tryGrow(world, mutable.down(), Direction.UP);
                return;
            }
            if (canDripThrough(world, mutable, blockState)) continue;
            return;
        }
    }

    private void tryGrow(ServerWorld world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        if (isTip(blockState, direction.getOpposite())) {
            growMerged(blockState, world, blockPos);
        } else if (blockState.isAir() || blockState.isOf(Blocks.WATER)) {
            place(world, blockPos, direction, Thickness.TIP);
        }
    }

    private void place(WorldAccess world, BlockPos pos, Direction direction, Thickness thickness) {
        BlockState blockState = getDripstone().getDefaultState().with(VERTICAL_DIRECTION, direction).with(THICKNESS, thickness).with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
        world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
    }

    private void growMerged(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos blockPos2;
        BlockPos blockPos;
        if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
            blockPos = pos;
            blockPos2 = pos.up();
        } else {
            blockPos2 = pos;
            blockPos = pos.down();
        }
        place(world, blockPos2, Direction.DOWN, Thickness.TIP_MERGE);
        place(world, blockPos, Direction.UP, Thickness.TIP_MERGE);
    }

    public void createParticle(World world, BlockPos pos, BlockState state) {
        getFluid(world, pos, state).ifPresent(fluid -> createParticle(world, pos, state, fluid.fluid));
    }

    private void createParticle(World world, BlockPos pos, BlockState state, Fluid fluid) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        double d = 0.0625;
        double e = (double)pos.getX() + 0.5 + vec3d.x;
        double f = (double)((float)(pos.getY() + 1) - 0.6875f) - 0.0625;
        double g = (double)pos.getZ() + 0.5 + vec3d.z;
        Fluid fluid2 = getDripFluid(world, fluid);
        DefaultParticleType particleEffect = fluid2.isIn(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        world.addParticle(particleEffect, e, f, g, 0.0, 0.0, 0.0);
    }

    @Nullable
    private BlockPos getTipPos(BlockState state2, WorldAccess world, BlockPos pos2, int range, boolean allowMerged) {
        if (isTip(state2, allowMerged)) {
            return pos2;
        }
        Direction direction = state2.get(VERTICAL_DIRECTION);
        BiPredicate<BlockPos, BlockState> biPredicate = (pos, state) -> state.isOf(getDripstone()) && state.get(VERTICAL_DIRECTION) == direction;
        return searchInDirection(world, pos2, direction.getDirection(), biPredicate, state -> isTip(state, allowMerged), range).orElse(null);
    }

    @Nullable
    private Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        Direction direction2;
        if (canPlaceAtWithDirection(world, pos, direction)) {
            direction2 = direction;
        } else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
            direction2 = direction.getOpposite();
        } else {
            return null;
        }
        return direction2;
    }

    private Thickness getThickness(WorldView world, BlockPos pos, Direction direction, boolean tryMerge) {
        Direction direction2 = direction.getOpposite();
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (isPointedDripstoneFacingDirection(blockState, direction2)) {
            if (tryMerge || blockState.get(THICKNESS) == Thickness.TIP_MERGE) {
                return Thickness.TIP_MERGE;
            }
            return Thickness.TIP;
        }
        if (!isPointedDripstoneFacingDirection(blockState, direction)) {
            return Thickness.TIP;
        }
        Thickness thickness = blockState.get(THICKNESS);
        if (thickness == Thickness.TIP || thickness == Thickness.TIP_MERGE) {
            return Thickness.FRUSTUM;
        }
        BlockState blockState2 = world.getBlockState(pos.offset(direction2));
        if (!isPointedDripstoneFacingDirection(blockState2, direction)) {
            return Thickness.BASE;
        }
        return Thickness.MIDDLE;
    }

    public boolean canDrip(BlockState state) {
        return isPointingDown(state) && state.get(THICKNESS) == Thickness.TIP && !state.get(WATERLOGGED);
    }

    private boolean canGrow(BlockState state, ServerWorld world, BlockPos pos) {
        Direction direction = state.get(VERTICAL_DIRECTION);
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        if (!blockState.getFluidState().isEmpty()) {
            return false;
        }
        if (blockState.isAir()) {
            return true;
        }
        return isTip(blockState, direction.getOpposite());
    }

    private Optional<BlockPos> getSupportingPos(World world, BlockPos pos2, BlockState state2, int range) {
        Direction direction = state2.get(VERTICAL_DIRECTION);
        BiPredicate<BlockPos, BlockState> biPredicate = (pos, state) -> state.isOf(getDripstone()) && state.get(VERTICAL_DIRECTION) == direction;
        return searchInDirection(world, pos2, direction.getOpposite().getDirection(), biPredicate, state -> !state.isOf(getDripstone()), range);
    }

    private boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction) || isPointedDripstoneFacingDirection(blockState, direction);
    }

    private boolean isTip(BlockState state, boolean allowMerged) {
        if (!state.isOf(getDripstone())) {
            return false;
        }
        Thickness thickness = state.get(THICKNESS);
        return thickness == Thickness.TIP || allowMerged && thickness == Thickness.TIP_MERGE;
    }

    private boolean isTip(BlockState state, Direction direction) {
        return isTip(state, false) && state.get(VERTICAL_DIRECTION) == direction;
    }

    private boolean isPointingDown(BlockState state) {
        return isPointedDripstoneFacingDirection(state, Direction.DOWN);
    }

    private boolean isPointingUp(BlockState state) {
        return isPointedDripstoneFacingDirection(state, Direction.UP);
    }

    private boolean isHeldByPointedDripstone(BlockState state, WorldView world, BlockPos pos) {
        return isPointingDown(state) && !world.getBlockState(pos.up()).isOf(getDripstone());
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private boolean isPointedDripstoneFacingDirection(BlockState state, Direction direction) {
        return state.isOf(getDripstone()) && state.get(VERTICAL_DIRECTION) == direction;
    }

    @Nullable
    private static BlockPos getCauldronPos(World world, BlockPos pos2, Fluid fluid) {
        Predicate<BlockState> predicate = state -> state.getBlock() instanceof AbstractCauldronBlock;
        BiPredicate<BlockPos, BlockState> biPredicate = (pos, state) -> canDripThrough(world, pos, state);
        return searchInDirection(world, pos2, Direction.DOWN.getDirection(), biPredicate, predicate, 11).orElse(null);
    }

    @Nullable
    public BlockPos getDripPos(World world, BlockPos pos2) {
        BiPredicate<BlockPos, BlockState> biPredicate = (pos, state) -> canDripThrough(world, pos, state);
        return searchInDirection(world, pos2, Direction.UP.getDirection(), biPredicate, this::canDrip, 11).orElse(null);
    }

    public Fluid getDripFluid(ServerWorld world, BlockPos pos) {
        return getFluid(world, pos, world.getBlockState(pos)).map(fluid -> fluid.fluid).filter(this::isFluidLiquid).orElse(Fluids.EMPTY);
    }

    private Optional<DrippingFluid> getFluid(World world, BlockPos pos2, BlockState state) {
        if (!isPointingDown(state)) {
            return Optional.empty();
        }
        return getSupportingPos(world, pos2, state, 11).map(pos -> {
            BlockPos blockPos = pos.up();
            BlockState blockState = world.getBlockState(blockPos);
            Fluid fluid = blockState.isOf(Blocks.MUD) && !world.getDimension().ultrawarm() ? Fluids.WATER : world.getFluidState(blockPos).getFluid();
            return new DrippingFluid(blockPos, fluid, blockState);
        });
    }

    /**
     * {@return whether the provided {@code fluid} is liquid, namely lava or water}
     */
    private boolean isFluidLiquid(Fluid fluid) {
        return fluid == Fluids.LAVA || fluid == Fluids.WATER;
    }

    private static boolean canGrow(BlockState dripstoneBlockState, BlockState waterState) {
        return dripstoneBlockState.isOf(Blocks.STONE) && waterState.isOf(Blocks.WATER) && waterState.getFluidState().isStill();
    }

    private static Fluid getDripFluid(World world, Fluid fluid) {
        if (fluid.matchesType(Fluids.EMPTY)) {
            return world.getDimension().ultrawarm() ? Fluids.LAVA : Fluids.WATER;
        }
        return fluid;
    }

    private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
        Direction direction2 = Direction.get(direction, Direction.Axis.Y);
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (int i = 1; i < range; ++i) {
            mutable.move(direction2);
            BlockState blockState = world.getBlockState(mutable);
            if (stopPredicate.test(blockState)) {
                return Optional.of(mutable.toImmutable());
            }
            if (!world.isOutOfHeightLimit(mutable.getY()) && continuePredicate.test(mutable, blockState)) continue;
            return Optional.empty();
        }
        return Optional.empty();
    }

    /**
     * {@return whether it can drip through the block {@code block} at {@code pos}}
     *
     * @apiNote This is used for checking which block can obstruct the stalagmites
     * growing or the cauldrons filling with liquids.
     */
    private static boolean canDripThrough(BlockView world, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        }
        if (state.isOpaqueFullCube(world, pos)) {
            return false;
        }
        if (!state.getFluidState().isEmpty()) {
            return false;
        }
        VoxelShape voxelShape = state.getCollisionShape(world, pos);
        return !VoxelShapes.matchesAnywhere(DRIP_COLLISION_SHAPE, voxelShape, BooleanBiFunction.AND);
    }

    record DrippingFluid(BlockPos pos, Fluid fluid, BlockState sourceState) {
    }

    public FallingBlockEntity getFallingBlockEntity(World world, BlockPos pos, BlockState state) {
        return new FallingBlockEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_LANDS, pos, 0);
        }
    }

    public Block getDripstone() {
        return getDripstoneFor(this.MATERIAL_BLOCK);
    }

    public static Block getDripstoneFor(Block block) {
        Optional<Block> dripstone = Optional.ofNullable(DRIPSTONES.get().get(block));
        return dripstone.orElse(Blocks.POINTED_DRIPSTONE);
    }
}