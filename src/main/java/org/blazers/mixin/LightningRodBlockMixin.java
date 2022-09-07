package org.blazers.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
 * Change the behavior of the {@link LightningRodBlock Lightning Rod} being hit by a {@link net.minecraft.world.entity.LightningBolt Lightning Bolt}
 */
@Mixin(LightningRodBlock.class)
public final class LightningRodBlockMixin {

    /**
     * Spawn a {@link CopperGolem Copper Golem} if there is a {@link net.minecraft.world.level.block.WeatheringCopperFullBlock Copper Block}
     * below the Lightning Rod
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World Reference}
     * @param pos {@link BlockPos Block Pos}
     * @param info {@link CallbackInfo Callback Info}
     */
    @Inject(method = "onLightningStrike", at = @At("RETURN"))
    public void onLightningStrike(BlockState state, Level level, BlockPos pos, CallbackInfo info) {
        if(state.hasProperty(BlockStateProperties.FACING) && state.getValue(BlockStateProperties.FACING).equals(Direction.UP)) {
            CopperGolem.trySpawnGolem(level, pos, null, null, null);
        }
    }
}