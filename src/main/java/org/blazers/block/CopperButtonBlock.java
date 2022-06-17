package org.blazers.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.blazers.BlazersMod;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation class for a {@link BlazersMod Blazers Mod}  {@link ButtonBlock Copper Button Block}
 */
public class CopperButtonBlock extends ButtonBlock {

    /**
     * Constructor. Sets the {@link Properties Copper Button Properties}
     */
    public CopperButtonBlock() {
        super(false, Properties.of(Material.DECORATION, MaterialColor.COLOR_ORANGE)
                .noCollission()
                .strength(0.5F)
                .sound(SoundType.COPPER));
    }

    /**
     * Get the {@link SoundEvent Button Sound}
     *
     * @param isOn {@link Boolean If the button is pressed}
     * @return {@link SoundEvent Button Sound}
     */
    @Override
    protected @NotNull SoundEvent getSound(boolean isOn) {
        return isOn ? SoundEvents.COPPER_PLACE : SoundEvents.COPPER_BREAK;
    }

    /**
     * Update neighbours on {@link ButtonBlock Button} press
     * and set the {@link #POWERED Powered state} to {@link Boolean true}
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     */
    @Override
    public void press(BlockState state, Level level, @NotNull BlockPos pos) {
        level.setBlock(pos, state.setValue(POWERED, Boolean.TRUE), 3);
        this.updateNeighbours(state, level, pos);
        level.scheduleTick(pos, this, 40);
    }

    /**
     * Update neighbours on {@link ButtonBlock Button} press
     *
     * @param state {@link BlockState Block State}
     * @param level {@link Level World reference}
     * @param pos {@link BlockPos Block Pos}
     */
    private void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }
}