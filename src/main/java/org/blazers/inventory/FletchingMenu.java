package org.blazers.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.core.BLMenuTypes;
import org.blazers.recipe.FletchingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link ItemCombinerMenu Menu provider} for the {@link Blocks#FLETCHING_TABLE Fletching Table}
 */
public class FletchingMenu extends ItemCombinerMenu {

    /**
     * {@link Level World Instance}
     */
    private final Level level;
    /**
     * {@link FletchingRecipe Selected Recipe}
     */
    private FletchingRecipe selectedRecipe;
    /**
     * {@link List<FletchingRecipe> Recipes}
     */
    private final List<FletchingRecipe> recipes;

    /**
     * Forge Constructor. Sets the {@link ItemCombinerMenu Menu} default properties
     *
     * @param id {@link Integer Menu ID}
     * @param inventory {@link Inventory Inventory}
     * @param buffer {@link FriendlyByteBuf Byte Buffer}
     */
    public FletchingMenu(int id, Inventory inventory, FriendlyByteBuf buffer) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    /**
     * Constructor. Sets the {@link ItemCombinerMenu Menu} properties
     *
     * @param id {@link Integer Menu ID}
     * @param inventory {@link Inventory Inventory}
     * @param containerLevelAccess {@link ContainerLevelAccess Container Level Access}
     */
    public FletchingMenu(int id, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(BLMenuTypes.FLETCHING.get(), id, inventory, containerLevelAccess);
        this.level = inventory.player.level;
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(FletchingRecipe.Type.INSTANCE);
    }

    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, (p_266883_) -> true)
                .withSlot(1, 76, 47, (p_267323_) -> true)
                .withResultSlot(2, 134, 47).build();
    }

    /**
     * Check if the {@link BlockState Block State} represents a valid
     * {@link Block Block} for the Menu to stay open
     *
     * @param state {@link BlockState Block State}
     * @return {@link Boolean True} if the {@link BlockState Block State} represents a valid
     *          {@link Block Block} for the Menu to stay open
     */
    protected boolean isValidBlock(BlockState state) {
        return state.is(Blocks.FLETCHING_TABLE);
    }

    /**
     * Check if the {@link Player Player} can take the output result
     *
     * @param player {@link Player Player}
     * @param hasItem {@link Boolean If the Player has the Item}
     * @return {@link Boolean True} if the {@link Player Player} can take the output result
     */
    protected boolean mayPickup(@NotNull Player player, boolean hasItem) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }

    /**
     * Take the {@link ItemStack Crafting Result}
     *
     * @param player {@link Player Player}
     * @param stack {@link ItemStack Crafting Result}
     */
    protected void onTake(@NotNull Player player, ItemStack stack) {
        stack.onCraftedBy(player.level, player, stack.getCount());
        this.resultSlots.awardUsedRecipes(player);
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.level.playSound(player, player.blockPosition(), SoundEvents.VILLAGER_WORK_FLETCHER, SoundSource.BLOCKS, 0.75F, 1.0F);
    }

    /**
     * Decrease an {@link ItemStack Item Stack} in a {@link Integer Slot}
     *
     * @param slot {@link Integer Slot}
     */
    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    /**
     * Create the {@link ItemStack Crafting Result}
     */
    public void createResult() {
        List<FletchingRecipe> list = this.level.getRecipeManager().getRecipesFor(FletchingRecipe.Type.INSTANCE, this.inputSlots, this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            this.selectedRecipe = list.get(0);
            ItemStack itemstack = this.selectedRecipe.assemble(this.inputSlots, this.level.registryAccess());
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemstack);
        }
    }

    public int getSlotToQuickMoveTo(ItemStack stack) {
        return this.shouldQuickMoveToAdditionalSlot(stack) ? 1 : 0;
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be quick move to a crafting slot
     *
     * @param stack {@link ItemStack Item Stack}
     * @return {@link Boolean True} if an {@link ItemStack Item Stack} can be quick move to a crafting slot
     */
    protected boolean shouldQuickMoveToAdditionalSlot(@NotNull ItemStack stack) {
        return this.recipes.stream().anyMatch(recipe -> recipe.isAdditionIngredient(stack));
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be merged by double-click
     *
     * @param stack {@link ItemStack Item Stack}
     * @param slot {@link Slot Slot}
     * @return {@link Boolean True} if an {@link ItemStack Item Stack} can be merged by double-click
     */
    public boolean canTakeItemForPickAll(@NotNull ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }
}