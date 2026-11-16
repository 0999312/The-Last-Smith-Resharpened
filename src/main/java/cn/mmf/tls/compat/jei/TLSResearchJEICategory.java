package cn.mmf.tls.compat.jei;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.block.BlockRegistry;
import cn.mmf.tls.recipe.TLSResearchRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TLSResearchJEICategory implements IRecipeCategory<TLSResearchRecipe> {
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public TLSResearchJEICategory(IGuiHelper helper) {
        title = Component.translatable("last_smith.jei.research");
        ResourceLocation backgroundImage = TheLastSmith.prefix("textures/gui/research_table_jei.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 105, 54);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockRegistry.RESEARCH_TABLE.get()));
    }
    
    @Override
    public RecipeType<TLSResearchRecipe> getRecipeType() {
        return JEICompat.RESEARCH_JEI_TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TLSResearchRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 7).setSlotName("bladeSlot").addIngredients(recipe.blade());
        builder.addSlot(RecipeIngredientRole.INPUT, 4, 31).setSlotName("paperSlot").addIngredients(recipe.paper());
        builder.addSlot(RecipeIngredientRole.INPUT, 24, 31).setSlotName("inkSlot").addIngredients(recipe.ink());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 19).setSlotName("outputSlot").addItemStack(recipe.output());
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    @SuppressWarnings("removal")
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

}
