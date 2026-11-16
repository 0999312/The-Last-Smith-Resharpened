package cn.mmf.tls.compat.emi.recipes;

import java.util.List;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

/**
 * 2026-10-06:18-20 Ported from the 1.20.1 EMI compat (simple inputs/outputs base recipe).
 * 2026-10-06:18-20 从 1.20.1 的 EMI 兼容移植（简单的输入/输出基类配方）。
 */
public abstract class EMISimpleRecipe implements EmiRecipe {
    protected final List<EmiIngredient> inputs;
    protected final List<EmiStack> outputs;
    protected final ResourceLocation id;

    public EMISimpleRecipe(List<EmiIngredient> inputs, List<EmiStack> outputs, ResourceLocation id) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.id = id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}
