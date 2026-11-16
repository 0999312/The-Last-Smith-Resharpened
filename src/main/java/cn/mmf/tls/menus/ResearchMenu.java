package cn.mmf.tls.menus;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import cn.mmf.tls.block.BlockRegistry;
import cn.mmf.tls.data.tag.TLSItemTags;
import cn.mmf.tls.recipe.RecipeSerializerRegistry;
import cn.mmf.tls.recipe.TLSResearchRecipe;
import cn.mmf.tls.recipe.TLSResearchRecipeInput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ResearchMenu extends ItemCombinerMenu {
    private final Level level;
    @Nullable
    private RecipeHolder<TLSResearchRecipe> selectedRecipe;
    private final List<RecipeHolder<TLSResearchRecipe>> recipes;

    public ResearchMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }

    public ResearchMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(ContainerRegistry.RESEARCH_TABLE.get(), pContainerId, pPlayerInventory, pAccess);
        this.level = pPlayerInventory.player.level();
        this.recipes = this.level.getRecipeManager()
                .getAllRecipesFor(RecipeSerializerRegistry.RESEARCH_RECIPE_TYPE.get());
    }

    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(TLSResearchRecipeInput.BLADE, 43, 13, (stack) -> {
                    return this.recipes.stream().anyMatch((recipe) -> {
                        return recipe.value().isBladeIngredient(stack);
                    });
                }).withSlot(TLSResearchRecipeInput.PAPER, 33, 37, (stack) -> {
                    return this.recipes.stream().anyMatch((recipe) -> {
                        return recipe.value().isPaperIngredient(stack);
                    });
                }).withSlot(TLSResearchRecipeInput.INK, 53, 37, (stack) -> {
                    return this.recipes.stream().anyMatch((recipe) -> {
                        return recipe.value().isInkIngredient(stack);
                    });
                }).withResultSlot(3, 111, 25).build();
    }

    protected boolean isValidBlock(BlockState pState) {
        return pState.is(BlockRegistry.RESEARCH_TABLE.get());
    }

    protected boolean mayPickup(@NotNull Player pPlayer, boolean pHasStack) {
        return this.selectedRecipe != null && this.selectedRecipe.value().matches(ContainerToRecipeInput(this.inputSlots), this.level);
    }

    protected void onTake(@NotNull Player pPlayer, ItemStack pStack) {
        pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());
        this.resultSlots.awardUsedRecipes(pPlayer, this.getRelevantItems());
        if (this.inputSlots.getItem(TLSResearchRecipeInput.BLADE).is(TLSItemTags.RESEARCH_CONSUMABLE))
            this.shrinkStackInSlot(TLSResearchRecipeInput.BLADE);
        this.shrinkStackInSlot(TLSResearchRecipeInput.PAPER);
        this.shrinkStackInSlot(TLSResearchRecipeInput.INK);
        this.access.execute((level, pos) -> {
            player.playSound(SoundEvents.BOOK_PAGE_TURN, 1F, 1F);
        });
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(
                this.inputSlots.getItem(TLSResearchRecipeInput.BLADE),
                this.inputSlots.getItem(TLSResearchRecipeInput.PAPER),
                this.inputSlots.getItem(TLSResearchRecipeInput.INK)
        );
    }

    private void shrinkStackInSlot(int pIndex) {
        ItemStack itemstack = this.inputSlots.getItem(pIndex);
        if (!itemstack.isEmpty()) {
            itemstack.shrink(1);
            this.inputSlots.setItem(pIndex, itemstack);
        }
    }

    public void createResult() {
        List<RecipeHolder<TLSResearchRecipe>> list = this.level.getRecipeManager()
                .getRecipesFor(RecipeSerializerRegistry.RESEARCH_RECIPE_TYPE.get(), ContainerToRecipeInput(this.inputSlots), this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            RecipeHolder<TLSResearchRecipe> holder = list.getFirst();
            ItemStack itemstack = holder.value().assemble(ContainerToRecipeInput(this.inputSlots), this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = holder;
                this.resultSlots.setRecipeUsed(holder);
                this.resultSlots.setItem(0, itemstack);
            }
        }
    }

    public int getSlotToQuickMoveTo(@NotNull ItemStack pStack) {
        return this.recipes.stream().map((holder) -> {
            return findSlotMatchingIngredient(holder.value(), pStack);
        }).filter(Optional::isPresent).findFirst().orElse(Optional.of(0)).get();
    }

    private static Optional<Integer> findSlotMatchingIngredient(TLSResearchRecipe pRecipe, ItemStack pStack) {
        if (pRecipe.isBladeIngredient(pStack)) {
            return Optional.of(0);
        } else if (pRecipe.isPaperIngredient(pStack)) {
            return Optional.of(1);
        } else {
            return pRecipe.isInkIngredient(pStack) ? Optional.of(2) : Optional.empty();
        }
    }

    public boolean canTakeItemForPickAll(@NotNull ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }

    public boolean canMoveIntoInputSlots(@NotNull ItemStack pStack) {
        return this.recipes.stream().map((holder) -> {
            return findSlotMatchingIngredient(holder.value(), pStack);
        }).anyMatch(Optional::isPresent);
    }

    private TLSResearchRecipeInput ContainerToRecipeInput(Container container) {
        return new TLSResearchRecipeInput(
                container.getItem(TLSResearchRecipeInput.BLADE),
                container.getItem(TLSResearchRecipeInput.PAPER),
                container.getItem(TLSResearchRecipeInput.INK)
        );
    }
}
