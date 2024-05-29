package org.blazers.block.weathering;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * {@link BlazersMod Blazers Mod} {@link IBLWaxedBlock waxed Block}
 */
public final class BLWaxedBlock extends Block implements IBLWaxedBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block properties}
     *
     * @param properties {@link BlockBehaviour.Properties The Block properties}
     */
    public BLWaxedBlock(final BlockBehaviour.Properties properties) {
        super(properties);
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
            context.getLevel().levelEvent(player, 3004, context.getClickedPos(), 0);
            if(player != null) {
                player.playSound(SoundEvents.AXE_SCRAPE);
            }
            return unwaxedBlockState.get();
        }
        return super.getToolModifiedState(blockState, context, toolAction, simulate);
    }
}