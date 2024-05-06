package org.blazers.block.weathering;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * {@link BlazersMod Blazers Mod} {@link CopperButtonBlock waxed Button Block}
 */
public class WaxedButtonBlock extends CopperButtonBlock implements IBLWaxedBlock {

    /**
     * Constructor. Set the {@link Properties Block properties}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public WaxedButtonBlock(final FeatureFlag... featureFlags) {
        super(featureFlags);
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param simulate {@link Boolean If the action only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        final Optional<BlockState> unwaxedBlockState = IBLWaxedBlock.getPrevious(blockState);
        if(unwaxedBlockState.isPresent()) {
            final Player player = context.getPlayer();
            final Level level = context.getLevel();
            final BlockPos blockPos = context.getClickedPos();
            level.levelEvent(player, 3004, blockPos, 0);
            if(player != null) {
                player.playSound(SoundEvents.AXE_SCRAPE);
            }
            return unwaxedBlockState.get();
        }
        return super.getToolModifiedState(blockState, context, toolAction, simulate);
    }
}