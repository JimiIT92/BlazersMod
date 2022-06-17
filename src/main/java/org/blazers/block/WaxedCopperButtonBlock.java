package org.blazers.block;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link ButtonBlock Waxed Copper Button Block}
 */
public class WaxedCopperButtonBlock extends CopperButtonBlock {

    /**
     * Constructor. Sets the {@link Properties Copper Button Properties}
     */
    public WaxedCopperButtonBlock() {
        super();
    }

    /**
     * Sets the {@link RotatedPillarBlock Stripped Block} variant on
     * right click with an {@link AxeItem Axe}
     *
     * @param state {@link BlockState Block State}
     * @param context {@link UseOnContext Use Context}
     * @param toolAction {@link ToolAction Tool action}
     * @param simulate {@link Boolean If this is a simulation}
     * @return Modified {@link BlockState Block State}
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction == ToolActions.AXE_WAX_OFF) {
            Block block = Blocks.AIR;
            if(state.is(BLBlocks.WAXED_COPPER_BUTTON.get())) {
                block = BLBlocks.COPPER_BUTTON.get();
            } else if(state.is(BLBlocks.WAXED_EXPOSED_COPPER_BUTTON.get())) {
                block = BLBlocks.EXPOSED_COPPER_BUTTON.get();
            } else if(state.is(BLBlocks.WAXED_WEATHERED_COPPER_BUTTON.get())) {
                block = BLBlocks.WEATHERED_COPPER_BUTTON.get();
            } else if(state.is(BLBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get())) {
                block = BLBlocks.OXIDIZED_COPPER_BUTTON.get();
            }
            if(!Blocks.AIR.equals(block)) {
                return block.defaultBlockState().setValue(POWERED, Boolean.FALSE).setValue(FACING, state.getValue(FACING)).setValue(FACE, state.getValue(FACE));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}