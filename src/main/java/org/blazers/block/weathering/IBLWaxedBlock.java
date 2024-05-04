package org.blazers.block.weathering;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.blazers.helper.ItemHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Interface for a {@link BlazersMod Blazers Mod} {@link Block waxable Block}
 */
public interface IBLWaxedBlock {

    /**
     * The {@link Supplier<BiMap> waxable states map}
     */
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(BLBlocks.CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.OXIDIZED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.COPPER_BUTTON.get(), BLBlocks.WAXED_COPPER_BUTTON.get())
            .put(BLBlocks.EXPOSED_COPPER_BUTTON.get(), BLBlocks.WAXED_EXPOSED_COPPER_BUTTON.get())
            .put(BLBlocks.WEATHERED_COPPER_BUTTON.get(), BLBlocks.WAXED_WEATHERED_COPPER_BUTTON.get())
            .put(BLBlocks.OXIDIZED_COPPER_BUTTON.get(), BLBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get())
    .build());

    /**
     * The {@link Supplier<BiMap> inverted waxable states map}
     */
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    /**
     * Get the previous {@link Block waxable state Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Optional<Block> The previous waxable state Block, if any}
     */
    static Optional<Block> getPrevious(final Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    /**
     * Get the previous {@link BlockState waxable Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The previous waxable Block State, if any}
     */
    static Optional<BlockState> getPrevious(final BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(block -> {
            BlockState previousBlockState = block.withPropertiesOf(blockState);
            if(block instanceof ButtonBlock) {
                previousBlockState = previousBlockState.setValue(ButtonBlock.POWERED, false);
            }
            return previousBlockState;
        });
    }

    /**
     * Get the next {@link Block waxable state Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Optional<Block> The next waxable state Block, if any}
     */
    static Optional<Block> getNext(final Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    /**
     * Get the next {@link BlockState waxable Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The next waxable Block State, if any}
     */
    static @NotNull Optional<BlockState> getNext(final BlockState blockState) {
        return getNext(blockState.getBlock()).map(block -> {
            BlockState nextBlockState = block.withPropertiesOf(blockState);
            if(block instanceof ButtonBlock) {
                nextBlockState = nextBlockState.setValue(ButtonBlock.POWERED, false);
            }
            return nextBlockState;
        });
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the action only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF)) {
            final Optional<BlockState> optionalPreviousState = getPrevious(blockState);
            if(optionalPreviousState.isPresent()) {
                context.getLevel().levelEvent(context.getPlayer(), 3004, context.getClickedPos(), 0);
                return optionalPreviousState.get();
            }
        }
        return blockState;
    }

    /**
     * Wax a {@link Block Block}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param player {@link Player The Player waxing the Block}
     * @param level {@link Level The Level reference}
     * @param itemStack {@link ItemStack The used Item Stack}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param hand {@link InteractionHand The hand used to wax the Block}
     */
    static boolean wax(final BlockState blockState, final Player player, final Level level, final ItemStack itemStack, final BlockPos blockPos, final InteractionHand hand) {
        final Optional<BlockState> waxedBlockState = getNext(blockState);
        if(waxedBlockState.isPresent()) {
            if(player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, itemStack);
            }
            ItemHelper.hurt(itemStack, player, hand);
            BlockState updatedBlockState = waxedBlockState.get().getBlock().withPropertiesOf(blockState);
            if(blockState.getBlock() instanceof ButtonBlock) {
                updatedBlockState = updatedBlockState.setValue(ButtonBlock.POWERED, false);
            }
            level.setBlockAndUpdate(blockPos, updatedBlockState);
            level.levelEvent(player, 3003, blockPos, 0);
            return true;
        }
        return false;
    }

}