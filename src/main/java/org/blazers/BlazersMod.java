package org.blazers;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blazers.block.AtomicTntBlock;
import org.blazers.core.*;
import org.blazers.recipe.FletchingRecipe;
import org.blazers.screen.FletchingScreen;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.GeckoLib;

/**
 * {@link BlazersMod Blazers Mod} main class
 */
@Mod(BlazersMod.MOD_ID)
public final class BlazersMod {

    /**
     * {@link String Mod ID}
     */
    public static final String MOD_ID = "blazersmod";

    /**
     * {@link Logger Logger Instance}
     */
    public static final Logger LOGGER = LogManager.getLogger();
    /**
     * {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Custom Item Renderer}
     */
    private static BlockEntityWithoutLevelRenderer ITEMS_RENDERER;

    /**
     * Initialize the {@link BlazersMod Blazers Mod}
     */
    public BlazersMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLItems.register(eventBus);
        BLBlocks.register(eventBus);
        BLPaintings.register(eventBus);
        BLSounds.register(eventBus);
        BLEntityTypes.register(eventBus);
        BLFeatures.register(eventBus);
        BLConfiguredFeatures.register(eventBus);
        BLPlacedFeatures.register(eventBus);
        BLMenuTypes.register(eventBus);
        BLRecipeSerializers.register(eventBus);
        BLLootModifiers.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::entityAttributeSetup);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the {@link BlazersMod Blazers Mod} Client stuffs
     *
     * @param event {@link FMLClientSetupEvent FML Client Setup Event}
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        BLItems.registerItemProperties();
        BLEntityTypes.registerRenderers();
        MenuScreens.register(BLMenuTypes.FLETCHING.get(), FletchingScreen::new);
        RecipeBookCategories.create(BlazersMod.MOD_ID + ":" + FletchingRecipe.ID, new ItemStack(BLItems.CARBON_BOW.get()));
    }

    /**
     * Set up the {@link BlazersMod Blazers Mod} Common stuffs
     *
     * @param event {@link FMLCommonSetupEvent FML Common Setup Event}
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(BLEntityTypes.WITHER_SKELETON_HORSE.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entity, level, spawnType, pos, random) -> true);
            SpawnPlacements.register(BLEntityTypes.FIREFLY.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entity, level, spawn, pos, random) ->
                            level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON));
        });
        event.enqueueWork(() -> ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BLBlocks.CATTAIL.getId(), BLBlocks.POTTED_CATTAIL));
        event.enqueueWork(BLInstruments::register);
        event.enqueueWork(() -> DispenserBlock.registerBehavior(BLBlocks.ATOMIC_TNT.get(), new DefaultDispenseItemBehavior() {
            /**
             * Dispense the specified {@link ItemStack Item Stack}
             *
             * @param blockSource  {@link BlockSource Block Source}
             * @param itemStack The {@link ItemStack Item Stack} to dispense
             */
            protected @NotNull ItemStack execute(@NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                Level level = blockSource.getLevel();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                PrimedTnt primedTnt = AtomicTntBlock.getPrimedAtomicTnt(level, blockpos, null);
                level.addFreshEntity(primedTnt);
                level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
                itemStack.shrink(1);
                return itemStack;
            }
        }));
    }

    /**
     * Setup the {@link AttributeSupplier Entity Attributes}
     *
     * @param event {@link EntityAttributeCreationEvent Entity Attribute Creation Event}
     */
    private void entityAttributeSetup(final EntityAttributeCreationEvent event) {
        BLEntityTypes.registerEntityAttributes(event);
    }

    /**
     * Get the {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Items Renderer}
     *
     * @return {@link BlazersMod Blazers Mod} {@link BlockEntityWithoutLevelRenderer Items Renderer}
     */
    public static BlockEntityWithoutLevelRenderer getItemsRenderer() {
        if(ITEMS_RENDERER == null) {
            ITEMS_RENDERER = new BLItemRenderer();
        }
        return ITEMS_RENDERER;
    }
}