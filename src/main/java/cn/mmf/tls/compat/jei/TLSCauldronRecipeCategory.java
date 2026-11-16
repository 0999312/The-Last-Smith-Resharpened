package cn.mmf.tls.compat.jei;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.compat.jei.recipes.TLSCauldronRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class TLSCauldronRecipeCategory implements IRecipeCategory<TLSCauldronRecipe> {

	@Override
	public RecipeType<TLSCauldronRecipe> getRecipeType() {
		return JEICompat.CAULDRON_JEI_TYPE;
	}

	private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public TLSCauldronRecipeCategory(IGuiHelper helper) {
        title = Component.translatable("last_smith.jei.cauldron");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, "textures/gui/jei_compat.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 93, 46);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(Items.CAULDRON));
    }

    @Override
    public void draw(TLSCauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        Optional<ItemStack> outputStack = recipeSlotsView.findSlotByName("outputSlot")
                .flatMap(slot -> slot.getDisplayedIngredient(VanillaTypes.ITEM_STACK));
        outputStack.ifPresent(output -> {
        	Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            var needBookText = Component.translatable("last_smith.jei.cauldron_need_water");
            guiGraphics.drawString(fontRenderer, needBookText,
                    46 - (fontRenderer.width(needBookText.getVisualOrderText()) / 2), 36, 0xFEFEFE);
            RenderSystem.setShaderColor(1, 1, 1, 1);
        });
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TLSCauldronRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 15).setSlotName("inputSlot").addItemStacks(recipe.getInputs());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 63, 15).setSlotName("outputSlot")
                .addItemStacks(recipe.getOutputs());
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
