package cn.mmf.tls.compat.jei;

import java.util.List;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.client.screen.ResearchTableScreen;
import cn.mmf.tls.compat.jei.recipes.TLSCauldronRecipe;
import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.menus.ContainerRegistry;
import cn.mmf.tls.menus.ResearchMenu;
import cn.mmf.tls.recipe.RecipeSerializerRegistry;
import cn.mmf.tls.recipe.TLSResearchRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEICompat implements IModPlugin {
    public static final mezz.jei.api.recipe.RecipeType<TLSResearchRecipe> RESEARCH_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(TheLastSmith.MODID, "research", TLSResearchRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<TLSCauldronRecipe> CAULDRON_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(TheLastSmith.MODID, "cauldron_recipe", TLSCauldronRecipe.class);

    private static final Minecraft MC = Minecraft.getInstance();

    @SuppressWarnings("unchecked")
    private static <T> List<T> findRecipesByType(RecipeType<?> type) {
        // 2026-06-13:17-42 Fixed: unwrap RecipeHolder<T> → T for 1.21.1 API
        if (MC.level == null) return List.of();
        return MC.level.getRecipeManager().getAllRecipesFor((RecipeType) type)
                .stream().map(h -> (T) ((RecipeHolder<?>) h).value()).toList();
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return TheLastSmith.prefix(TheLastSmith.MODID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new TLSCauldronRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new TLSResearchJEICategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RESEARCH_JEI_TYPE, findRecipesByType(RecipeSerializerRegistry.RESEARCH_RECIPE_TYPE.get()));

        registration.addRecipes(CAULDRON_JEI_TYPE, List.of(
                new TLSCauldronRecipe(ItemRegistry.BLADE_SAKURA_UNFINISHED_3.get().getDefaultInstance(), ItemRegistry.BLADE_SAKURA_UNFINISHED_4.get().getDefaultInstance()),
                new TLSCauldronRecipe(ItemRegistry.BLADE_UNFINISHED_3.get().getDefaultInstance(), ItemRegistry.BLADE_UNFINISHED_4.get().getDefaultInstance())
                ));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ItemRegistry.RESEARCH_TABLE.get().getDefaultInstance(), RESEARCH_JEI_TYPE);
        registration.addRecipeCatalyst(Items.CAULDRON.getDefaultInstance(), CAULDRON_JEI_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ResearchTableScreen.class, 74, 22, 28, 21, RESEARCH_JEI_TYPE);

    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(ResearchMenu.class, ContainerRegistry.RESEARCH_TABLE.get(), RESEARCH_JEI_TYPE, 0, 3, 4, 36);
    }

}
