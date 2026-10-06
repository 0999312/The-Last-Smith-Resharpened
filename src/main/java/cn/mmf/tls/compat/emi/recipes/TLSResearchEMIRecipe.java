package cn.mmf.tls.compat.emi.recipes;

import java.util.ArrayList;
import java.util.List;

import cn.mmf.tls.compat.emi.EMICompat;
import cn.mmf.tls.recipe.TLSResearchRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;

/**
 * 2026-10-06:18-20 Ported from the 1.20.1 EMI compat (research table recipe display).
 * 2026-10-06:18-20 从 1.20.1 的 EMI 兼容移植（研究台配方展示）。
 */
public class TLSResearchEMIRecipe extends EMISimpleRecipe {
    private final TLSResearchRecipe recipe;

    public TLSResearchEMIRecipe(TLSResearchRecipe recipe, ResourceLocation id) {
        super(createInputs(recipe), List.of(EmiStack.of(recipe.output())), id);
        this.recipe = recipe;
    }

    private static List<EmiIngredient> createInputs(TLSResearchRecipe recipe) {
        List<EmiIngredient> list = new ArrayList<>();
        list.add(EmiIngredient.of(recipe.blade()));
        list.add(EmiIngredient.of(recipe.paper()));
        list.add(EmiIngredient.of(recipe.ink()));
        return list;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMICompat.RESEARCH_CATEGORY;
    }

    @Override
    public int getDisplayWidth() {
        return 105;
    }

    @Override
    public int getDisplayHeight() {
        return 54;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(EmiIngredient.of(recipe.blade()), 14, 7);
        widgets.addSlot(EmiIngredient.of(recipe.paper()), 4, 31);
        widgets.addSlot(EmiIngredient.of(recipe.ink()), 24, 31);
        widgets.addSlot(outputs.get(0), 82, 19);
    }
}
