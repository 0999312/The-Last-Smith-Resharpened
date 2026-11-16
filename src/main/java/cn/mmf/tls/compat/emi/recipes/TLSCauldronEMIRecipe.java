package cn.mmf.tls.compat.emi.recipes;

import java.util.List;

import cn.mmf.tls.compat.emi.EMICompat;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * 2026-10-06:18-20 Ported from the 1.20.1 EMI compat (cauldron quenching display).
 * 2026-10-06:18-20 从 1.20.1 的 EMI 兼容移植（炼药锅淬火展示）。
 */
public class TLSCauldronEMIRecipe extends EMISimpleRecipe {
    public TLSCauldronEMIRecipe(ItemStack input, ItemStack output, ResourceLocation id) {
        super(List.of(EmiStack.of(input)), List.of(EmiStack.of(output)), id);
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMICompat.CAULDRON_CATEGORY;
    }

    @Override
    public int getDisplayWidth() {
        return 93;
    }

    @Override
    public int getDisplayHeight() {
        return 46;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(inputs.get(0), 14, 15);
        widgets.addSlot(outputs.get(0), 63, 15);
        widgets.addText(Component.translatable("last_smith.jei.cauldron_need_water"), 16, 36, 0xFEFEFE, true);
    }
}
