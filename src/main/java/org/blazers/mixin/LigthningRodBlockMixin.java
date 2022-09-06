package org.blazers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.blazers.entity.CopperGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningRodBlock.class)
public final class LigthningRodBlockMixin {

    @Inject(method = "setPowered", at = @At("RETURN"))
    public void setPowered(BlockState blockState, World world, BlockPos pos, CallbackInfo info) {
        if(blockState.get(Properties.FACING).equals(Direction.UP)) {
            CopperGolemEntity.trySpawnGolem(world, pos, null, null, null);
        }
    }
}