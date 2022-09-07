package org.blazers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.blazers.entity.AtomicTntEntity;
import org.jetbrains.annotations.Nullable;

public class AtomicTntBlock extends TntBlock {

    public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior(){

        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            ServerWorld world = pointer.getWorld();
            BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            TntEntity tntEntity = getPrimedAtomicTnt(world, blockPos, null);
            world.spawnEntity(tntEntity);
            world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.ENTITY_PLACE, blockPos);
            stack.decrement(1);
            return stack;
        }
    };

    public static final DispenserBehavior FLINT_AND_STEEL_BEHAVIOR = new FallibleItemDispenserBehavior(){

        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            ServerWorld world = pointer.getWorld();
            this.setSuccess(true);
            Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
            BlockPos blockPos = pointer.getPos().offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (AbstractFireBlock.canPlaceAt(world, blockPos, direction)) {
                world.setBlockState(blockPos, AbstractFireBlock.getState(world, blockPos));
                world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
            } else if (CampfireBlock.canBeLit(blockState) || CandleBlock.canBeLit(blockState) || CandleCakeBlock.canBeLit(blockState)) {
                world.setBlockState(blockPos, blockState.with(Properties.LIT, true));
                world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
            } else if (blockState.getBlock() instanceof AtomicTntBlock) {
                AtomicTntBlock.primeTnt(world, blockPos);
                world.removeBlock(blockPos, false);
            } else if (blockState.getBlock() instanceof TntBlock) {
                TntBlock.primeTnt(world, blockPos);
                world.removeBlock(blockPos, false);
            } else {
                this.setSuccess(false);
            }
            if (this.isSuccess() && stack.damage(1, world.random, null)) {
                stack.setCount(0);
            }
            return stack;
        }
    };

    public AtomicTntBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TNT));
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isClient) {
            TntEntity tntEntity = getPrimedAtomicTnt(world, pos, explosion.getCausingEntity());
            int fuse = tntEntity.getFuse();
            tntEntity.setFuse((short)(world.random.nextInt(fuse / 4) + fuse / 8));
            world.spawnEntity(tntEntity);
        }
    }

    public static void primeTnt(World world, BlockPos pos) {
        primeTnt(world, pos, null);
    }

    public static void primeTnt(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClient) {
            TntEntity tntEntity = getPrimedAtomicTnt(world, pos, igniter);
            world.spawnEntity(tntEntity);
            world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player2, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player2.getStackInHand(hand);
        if (itemStack.isOf(Items.FLINT_AND_STEEL) || itemStack.isOf(Items.FIRE_CHARGE)) {
            primeTnt(world, pos, player2);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            Item item = itemStack.getItem();
            if (!player2.isCreative()) {
                if (itemStack.isOf(Items.FLINT_AND_STEEL)) {
                    itemStack.damage(1, player2, player -> player.sendToolBreakStatus(hand));
                } else {
                    itemStack.decrement(1);
                }
            }
            player2.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player2, hand, hit);
    }

    private static TntEntity getPrimedAtomicTnt(World world, BlockPos pos, LivingEntity igniter) {
        return new AtomicTntEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, igniter);
    }
}
