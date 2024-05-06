package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.blazers.BlazersMod;
import org.blazers.entity.block.PrimedAtomicTnt;
import org.blazers.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link BlazersMod Blazers Mod} {@link TntBlock Atomic TNT Block}
 */
public final class AtomicTntBlock extends TntBlock implements IForgeBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block properties}
     */
    public AtomicTntBlock() {
        super(PropertyHelper.from(Blocks.TNT).instabreak().sound(SoundType.GRASS));
    }

    /**
     * Fuse the {@link TntBlock Atomic TNT} if the block is destroyed by an {@link Explosion explosion}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param explosion {@link Explosion The Explosion}
     */
    @Override
    public void wasExploded(final Level level, final @NotNull BlockPos blockPos, final @NotNull Explosion explosion) {
        if (!level.isClientSide) {
            final PrimedAtomicTnt primedTnt = getPrimedAtomicTnt(level, blockPos, explosion.getIndirectSourceEntity());
            final int fuse = primedTnt.getFuse();
            primedTnt.setFuse((short)(level.random.nextInt(fuse / 4) + fuse / 8));
            level.addFreshEntity(primedTnt);
        }
    }

    /**
     * Fuse the {@link TntBlock Atomic TNT} if the block catches fire
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The Direction the fire is coming}
     * @param entity {@link LivingEntity The entity that ignited the TNT}
     */
    @Override
    public void onCaughtFire(final @NotNull BlockState state, final Level level, final @NotNull BlockPos blockPos, final @Nullable Direction direction, final @Nullable LivingEntity entity) {
        if (!level.isClientSide) {
            final PrimedAtomicTnt primedTnt = getPrimedAtomicTnt(level, blockPos, entity);
            level.addFreshEntity(primedTnt);
            level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    /**
     * Get the {@link PrimedAtomicTnt Primed Atomic TNT Entity}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param entity {@link LivingEntity The TNT owner}
     * @return {@link PrimedAtomicTnt The Primed Atomic TNT Entity}
     */
    public static PrimedAtomicTnt getPrimedAtomicTnt(final Level level, final BlockPos blockPos, final @Nullable LivingEntity entity) {
        return new PrimedAtomicTnt(level, (double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, entity);
    }

}