package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.blazers.BlazersMod;
import org.blazers.entity.PrimedAtomicTnt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link TntBlock Atomic TNT Block}
 */
public class AtomicTntBlock extends TntBlock implements IForgeBlock {

    /**
     * Constructor. Sets the {@link TntBlock Atomic TNT Block} properties
     */
    public AtomicTntBlock() {
        super(BlockBehaviour.Properties.of().instabreak().sound(SoundType.GRASS));
    }

    /**
     * Fuse the {@link TntBlock Atomic TNT} if the block is destroyed
     * by an {@link Explosion explosion}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param explosion {@link Explosion Explosion}
     */
    @Override
    public void wasExploded(Level level, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        if (!level.isClientSide) {
            PrimedAtomicTnt primedtnt = getPrimedAtomicTnt(level, pos, explosion.getIndirectSourceEntity());
            int fuse = primedtnt.getFuse();
            primedtnt.setFuse((short)(level.random.nextInt(fuse / 4) + fuse / 8));
            level.addFreshEntity(primedtnt);
        }
    }

    /**
     * Fuse the {@link TntBlock Atomic TNT} if the block catches fire
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param face {@link Direction Direction}
     * @param igniter {@link LivingEntity Igniter}
     */
    @Override
    public void onCaughtFire(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            PrimedAtomicTnt primedtnt = getPrimedAtomicTnt(level, pos, igniter);
            level.addFreshEntity(primedtnt);
            level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    /**
     * Get the {@link PrimedAtomicTnt Primed Atomic TNT Entity}
     *
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     * @param igniter {@link LivingEntity Igniter}
     * @return {@link PrimedAtomicTnt Primed Atomic TNT Entity}
     */
    public static PrimedAtomicTnt getPrimedAtomicTnt(Level level, BlockPos pos, @Nullable LivingEntity igniter) {
        return new PrimedAtomicTnt(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, igniter);
    }
}