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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.blazers.BlazersMod;
import org.blazers.core.BLMenuTypes;
import org.blazers.core.BLRecipeTypes;
import org.blazers.helper.ItemHelper;
import org.blazers.recipe.FletchingRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.OptionalInt;

/**
 * {@link BlazersMod Blazers Mod} {@link ItemCombinerMenu Fletching Menu}
 */
public final class FletchingMenu extends ItemCombinerMenu {

    /**
     * {@link Level The Level reference}
     */
    private final Level level;
    /**
     * {@link RecipeHolder<FletchingRecipe> The selected recipe}
     */
    @Nullable
    private RecipeHolder<FletchingRecipe> selectedRecipe;
    /**
     * {@link List<FletchingRecipe> The Fletching recipes}
     */
    private final List<RecipeHolder<FletchingRecipe>> recipes;

    /**
     * Constructor. Set the menu properties
     *
     * @param id {@link Integer The menu Id}
     * @param inventory {@link Inventory The menu inventory}
     * @param buffer {@link FriendlyByteBuf The network buffer}
     */
    public FletchingMenu(final int id, final Inventory inventory, final FriendlyByteBuf buffer) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    /**
     * Constructor. Set the menu properties
     *
     * @param id {@link Integer The menu Id}
     * @param inventory {@link Inventory The menu inventory}
     * @param containerLevelAccess {@link ContainerLevelAccess The container level access reference}
     */
    public FletchingMenu(final int id, final Inventory inventory, final ContainerLevelAccess containerLevelAccess) {
        super(BLMenuTypes.FLETCHING.get(), id, inventory, containerLevelAccess);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(BLRecipeTypes.FLETCHING.get());
    }

    /**
     * Get the menu slots
     *
     * @return {@link ItemCombinerMenuSlotDefinition The menu slots}
     */
    @Override
    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, itemStack -> this.recipes.stream().anyMatch(recipe -> recipe.value().isBaseIngredient(itemStack)))
                .withSlot(1, 76, 47, itemStack -> this.recipes.stream().anyMatch(recipe -> recipe.value().isAdditionIngredient(itemStack)))
                .withResultSlot(2, 134, 47)
                .build();
    }

    /**
     * Check if the {@link BlockState Block State} is a valid Menu Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if is a Fletching Table}
     */
    @Override
    protected boolean isValidBlock(final BlockState blockState) {
        return blockState.is(Blocks.FLETCHING_TABLE);
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be picked up from the Menu
     *
     * @param player {@link Player The Player picking up the Item}
     * @param hasStack {@link Boolean If the Player has another Stack of the Item}
     * @return {@link Boolean True if the Item can be picked up}
     */
    @Override
    protected boolean mayPickup(final @NotNull Player player, final boolean hasStack) {
        return this.selectedRecipe != null && this.selectedRecipe.value().matches(this.inputSlots, this.level);
    }

    /**
     * Take an {@link ItemStack Item Stack} from the Menu
     *
     * @param player {@link Player The Player picking up the Item}
     * @param itemStack {@link ItemStack The Item Stack to take}
     */
    @Override
    protected void onTake(final @NotNull Player player, final ItemStack itemStack) {
        itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.level.playSound(player, player.blockPosition(), SoundEvents.VILLAGER_WORK_FLETCHER, SoundSource.BLOCKS);
    }

    /**
     * Get the {@link List<ItemStack>  Menu  Relevant Items}
     *
     * @return {@link List<ItemStack> The Menu Relevant Items}
     */
    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    /**
     * Shrink an {@link ItemStack Item Stack} from a slot
     *
     * @param slot {@link Integer The slot Id}
     */
    private void shrinkStackInSlot(final int slot) {
        final ItemStack itemStack = this.inputSlots.getItem(slot);
        if (!itemStack.isEmpty()) {
            ItemHelper.hurt(itemStack, null, null);
            this.inputSlots.setItem(slot, itemStack);
        }
    }

    /**
     * Set the recipe result inside the result slot
     */
    @Override
    public void createResult() {
        final List<RecipeHolder<FletchingRecipe>> recipes = this.level.getRecipeManager().getRecipesFor(BLRecipeTypes.FLETCHING.get(), this.inputSlots, this.level);
        if (recipes.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        final RecipeHolder<FletchingRecipe> recipeholder = recipes.getFirst();
        final ItemStack itemStack = recipeholder.value().assemble(this.inputSlots, this.level.registryAccess());
        if (itemStack.isItemEnabled(this.level.enabledFeatures())) {
            this.selectedRecipe = recipeholder;
            this.resultSlots.setRecipeUsed(recipeholder);
            this.resultSlots.setItem(0, itemStack);
        }
    }

    /**
     * Get the slot Id for the {@link ItemStack Item Stack} to quickly be moved in
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Integer The slot Id}
     */
    @Override
    public int getSlotToQuickMoveTo(final @NotNull ItemStack itemStack) {
        return this.findSlotToQuickMoveTo(itemStack).orElse(0);
    }

    /**
     * Get the {@link OptionalInt slot Id} for the matching recipe
     *
     * @param recipe {@link FletchingRecipe The current recipe}
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link OptionalInt The slot Id}
     */
    private static OptionalInt findSlotMatchingIngredient(final FletchingRecipe recipe, final ItemStack itemStack) {
        if (recipe.isBaseIngredient(itemStack)) {
            return OptionalInt.of(0);
        }
        return recipe.isAdditionIngredient(itemStack) ? OptionalInt.of(1) : OptionalInt.empty();
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be fully picked up
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param slot {@link Slot The current slot}
     * @return {@link Boolean True if the Item can be fully picked up}
     */
    @Override
    public boolean canTakeItemForPickAll(final @NotNull ItemStack itemStack, final Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(itemStack, slot);
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be moved into an input slot
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean True if the Item Stack can be moved into an input slot}
     */
    @Override
    public boolean canMoveIntoInputSlots(final @NotNull ItemStack itemStack) {
        return this.findSlotToQuickMoveTo(itemStack).isPresent();
    }

    /**
     * Get the {@link OptionalInt slot Id} for the matching recipe
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link OptionalInt The slot Id}
     */
    private OptionalInt findSlotToQuickMoveTo(final ItemStack itemStack) {
        return this.recipes
                .stream()
                .flatMapToInt(recipe -> findSlotMatchingIngredient(recipe.value(), itemStack).stream())
                .filter(slotId -> !this.getSlot(slotId).hasItem())
                .findFirst();
    }

}