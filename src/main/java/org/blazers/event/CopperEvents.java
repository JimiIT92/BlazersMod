package org.blazers.event;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.block.WeatheringCopperButtonBlock;
import org.blazers.block.WeatheringCutCopperBricks;
import org.blazers.core.BLBlocks;

import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.ButtonBlock.POWERED;

/**
 * Event Listener for the {@link WeatheringCopper Copper Blocks} related events
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final  class CopperEvents {

    /**
     * Waxable {@link WeatheringCopper Copper Blocks}
     */
    private static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(BLBlocks.COPPER_BUTTON.get(), BLBlocks.WAXED_COPPER_BUTTON.get())
            .put(BLBlocks.EXPOSED_COPPER_BUTTON.get(), BLBlocks.WAXED_EXPOSED_COPPER_BUTTON.get())
            .put(BLBlocks.WEATHERED_COPPER_BUTTON.get(), BLBlocks.WAXED_WEATHERED_COPPER_BUTTON.get())
            .put(BLBlocks.OXIDIZED_COPPER_BUTTON.get(), BLBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get())
            .put(BLBlocks.CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.EXPOSED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_EXPOSED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.WEATHERED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_WEATHERED_CUT_COPPER_BRICKS.get())
            .put(BLBlocks.OXIDIZED_CUT_COPPER_BRICKS.get(), BLBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICKS.get())
           .build());

    /**
     * Wax a {@link WeatheringCopper Copper Block}
     * if the {@link Player Player} shift-right click with an {@link HoneycombItem Honeycomb}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Right Click Button Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        Level world = event.getLevel();
        BlockPos blockPos = event.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if(((block instanceof WeatheringCopperButtonBlock && player.isShiftKeyDown()) || block instanceof WeatheringCutCopperBricks) && item instanceof HoneycombItem) {

            getWaxed(blockState).ifPresent(state -> {
                if(state.getBlock() instanceof WeatheringCopperButtonBlock) {
                    state = state.setValue(POWERED, false);
                }
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
                }
                if(!player.isCreative()) {
                    itemStack.shrink(1);
                }
                world.setBlock(blockPos, state, 11);
                world.levelEvent(player, 3003, blockPos, 0);
                if(world.isClientSide()) {
                    player.swing(event.getHand());
                }
            });
        }
    }

    /**
     * Get the Waxed {@link Block Block}
     * based on the current {@link BlockState Block State}
     *
     * @param state Current {@link BlockState Block State}
     * @return Waxed {@link Block Block}
     */
    private static Optional<BlockState> getWaxed(BlockState state) {
        return Optional.ofNullable(WAXABLES.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
    }
}