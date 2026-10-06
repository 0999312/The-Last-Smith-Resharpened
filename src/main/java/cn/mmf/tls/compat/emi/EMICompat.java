package cn.mmf.tls.compat.emi;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.compat.emi.recipes.TLSCauldronEMIRecipe;
import cn.mmf.tls.compat.emi.recipes.TLSResearchEMIRecipe;
import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.recipe.RecipeSerializerRegistry;
import cn.mmf.tls.recipe.TLSResearchRecipe;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;

/**
 * 2026-10-06:18-20 Re-added EMI compatibility, ported from the 1.20.1 version to the 1.21.1 EMI API.
 * EMI is an optional companion mod: this class is only discovered/loaded by EMI (@EmiEntrypoint),
 * so it never affects the base mod when EMI is absent.
 * 2026-10-06:18-20 重新加入 EMI 兼容，从 1.20.1 版本移植到 1.21.1 的 EMI API。
 * EMI 为可选联动模组：本类仅由 EMI 通过 @EmiEntrypoint 发现并加载，未安装 EMI 时不影响本体运行。
 */
@EmiEntrypoint
public class EMICompat implements EmiPlugin {
    public static final EmiRecipeCategory RESEARCH_CATEGORY = new EmiRecipeCategory(
            TheLastSmith.prefix("research"), EmiStack.of(ItemRegistry.RESEARCH_TABLE.get()));
    public static final EmiRecipeCategory CAULDRON_CATEGORY = new EmiRecipeCategory(
            TheLastSmith.prefix("cauldron"), EmiStack.of(Items.CAULDRON));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(RESEARCH_CATEGORY);
        registry.addCategory(CAULDRON_CATEGORY);

        for (RecipeHolder<TLSResearchRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeSerializerRegistry.RESEARCH_RECIPE_TYPE.get())) {
            registry.addRecipe(new TLSResearchEMIRecipe(holder.value(), holder.id()));
        }

        registry.addRecipe(new TLSCauldronEMIRecipe(
                ItemRegistry.BLADE_SAKURA_UNFINISHED_3.get().getDefaultInstance(),
                ItemRegistry.BLADE_SAKURA_UNFINISHED_4.get().getDefaultInstance(),
                TheLastSmith.prefix("cauldron_sakura_blade")));
        registry.addRecipe(new TLSCauldronEMIRecipe(
                ItemRegistry.BLADE_UNFINISHED_3.get().getDefaultInstance(),
                ItemRegistry.BLADE_UNFINISHED_4.get().getDefaultInstance(),
                TheLastSmith.prefix("cauldron_normal_blade")));

        registry.addWorkstation(RESEARCH_CATEGORY, EmiStack.of(ItemRegistry.RESEARCH_TABLE.get()));
        registry.addWorkstation(CAULDRON_CATEGORY, EmiStack.of(Items.CAULDRON));
    }
}
