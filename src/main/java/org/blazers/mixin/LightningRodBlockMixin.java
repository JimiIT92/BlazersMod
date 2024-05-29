package org.blazers.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.blazers.entity.CopperGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Try spawning a {@link CopperGolem Copper Golem} when a {@link LightningBolt Lightning Bolt} hits a {@link LightningRodBlock Lightning Rod Block}
 */
@Mixin(LightningRodBlock.class)
public final class LightningRodBlockMixin {

    /**
     * Try spawning a {@link CopperGolem Copper Golem} when a {@link LightningBolt Lightning Bolt} hits a {@link LightningRodBlock Lightning Rod Block}
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param info {@link CallbackInfo The callback info}
     */
    @Inject(method = "onLightningStrike", at = @At("RETURN"))
    public void onLightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos, final CallbackInfo info) {
        if(blockState.hasProperty(BlockStateProperties.FACING) && blockState.getValue(BlockStateProperties.FACING).equals(Direction.UP)) {
            CopperGolem.trySpawnGolem(level, blockPos, null, null, null);
        }
    }
}