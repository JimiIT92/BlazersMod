package org.blazers.block;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.blazers.event.EventUtils;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for the {@link BlazersMod Blazers Mod} {@link Block Waxed Cut Copper Bricks}
 */
public class WaxedCutCopperBricksBlock extends Block {

    /**
     * Constructor. Sets the {@link Properties Waxed Cut Copper Bricks Properties}
     *
     * @param parentBlock {@link BlockBehaviour Parent Block Properties}
     */
    public WaxedCutCopperBricksBlock(BlockBehaviour parentBlock) {
        super(BlockBehaviour.Properties.copy(parentBlock));
    }

    /**
     * Sets the {@link WeatheringCutCopperBricks Cut Copper Bricks} variant on
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
            if(state.is(BLBlocks.WAXED_CUT_COPPER_BRICKS.get())) {
                block = BLBlocks.CUT_COPPER_BRICKS.get();
            } else if(state.is(BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS.get())) {
                block = BLBlocks.EXPOSED_CUT_COPPER_BRICKS.get();
            } else if(state.is(BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS.get())) {
                block = BLBlocks.WEATHERED_CUT_COPPER_BRICKS.get();
            } else if(state.is(BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS.get())) {
                block = BLBlocks.OXIDIZED_CUT_COPPER_BRICKS.get();
            }
            if(!Blocks.AIR.equals(block)) {
                EventUtils.fireWorldEvent(context.getLevel(), context.getPlayer(), EventUtils.WorldEvents.AXE_WAX_OFF, context.getClickedPos());
                return block.defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}