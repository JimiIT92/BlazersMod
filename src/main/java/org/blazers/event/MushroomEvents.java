package org.blazers.event;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.blazers.BlazersMod;
import org.blazers.core.BLBlocks;
import org.blazers.helper.ItemHelper;

import java.util.function.Supplier;

/**
 * Handle all events for {@link MushroomBlock Mushrooms}
 */
@Mod.EventBusSubscriber(modid = BlazersMod.MOD_ID)
public final class MushroomEvents {

    /**
     * {@link Supplier<BiMap> Mushroom Wall Fans by Mushrooms}
     */
    public static final Supplier<BiMap<Item, Block>> MUSHROOM_WALL_FANS = Suppliers.memoize(() -> ImmutableBiMap.<Item, Block>builder()
            .put(Items.BROWN_MUSHROOM, BLBlocks.BROWN_MUSHROOM_WALL_FAN.get())
            .put(Items.RED_MUSHROOM, BLBlocks.RED_MUSHROOM_WALL_FAN.get())
    .build());

    /**
     * Place a {@link CoralWallFanBlock Mushroom Wall Fan Block} when right clicking on a {@link Block Block} with a {@link Item Mushroom Item}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final ItemStack itemStack = event.getItemStack();
            final Item item = itemStack.getItem();
            if(isValidItem(item)) {
                final Block mushroomWallFan = getMushroomWallFanBlockFor(item);
                final Direction face = event.getFace();
                if(mushroomWallFan != null && face != null) {
                    final Player player = event.getEntity();
                    final BlockPos clickedPos = event.getPos();
                    final Level level = event.getLevel();
                    final BlockState blockState = level.getBlockState(clickedPos);
                    final BlockPos placePos = clickedPos.offset(face.getNormal());
                    boolean isValidFace = face != Direction.DOWN && face != Direction.UP && blockState.isFaceSturdy(level, placePos, face) && level.getBlockState(placePos).canBeReplaced();
                    if(isValidFace) {
                        if(!level.isClientSide()) {
                            level.setBlockAndUpdate(placePos, mushroomWallFan.defaultBlockState()
                                    .setValue(BaseCoralPlantTypeBlock.WATERLOGGED, level.getFluidState(placePos).is(Fluids.WATER))
                                    .setValue(BaseCoralWallFanBlock.FACING, face)
                            );
                        }
                        ItemHelper.hurt(itemStack, player, event.getHand(), SoundEvents.GRASS_PLACE);
                    }
                }
            }
        }
    }

    /**
     * Get a {@link Block Mushroom Wall Fan Block} for the given {@link Item Item}
     *
     * @param item {@link Item The Item to get the Mushroom Wall Fan Block for}
     * @return {@link Block The Mushroom Wall Fan Block}
     */
    private static Block getMushroomWallFanBlockFor(final Item item) {
        return MUSHROOM_WALL_FANS.get().getOrDefault(item, null);
    }

    /**
     * Check if an {@link Item Item} is a placeable {@link BaseCoralWallFanBlock Mushroom Wall Fan Block}
     *
     * @param item {@link Item The Item to check}
     * @return {@link Boolean True if is a placeable Mushroom Wall Fan Block}
     */
    private static boolean isValidItem(final Item item) {
        return MUSHROOM_WALL_FANS.get().containsKey(item);
    }

}