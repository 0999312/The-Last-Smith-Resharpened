package cn.mmf.tls.recipe;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import org.jetbrains.annotations.NotNull;

public class TLSResearchRecipeBuilder implements RecipeBuilder {
    private final Ingredient blade;
    private Ingredient paper = Ingredient.of(Items.PAPER);
    private Ingredient ink = CompoundIngredient.of(
            Ingredient.of(Items.INK_SAC),
            Ingredient.of(Items.BLACK_DYE)
    );
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public TLSResearchRecipeBuilder(Ingredient blade, ItemStack result) {
        this.blade = blade;
        this.result = result;
    }

    public static TLSResearchRecipeBuilder researching(Ingredient blade, Item result) {
        return new TLSResearchRecipeBuilder(blade, new ItemStack(result));
    }

    public static TLSResearchRecipeBuilder researching(Ingredient blade, Item result, int count) {
        return new TLSResearchRecipeBuilder(blade, new ItemStack(result, count));
    }

    public static TLSResearchRecipeBuilder researching(Ingredient blade, ItemStack result) {
        return new TLSResearchRecipeBuilder(blade, result);
    }

    public TLSResearchRecipeBuilder paper(Ingredient paper) {
        this.paper = paper;
        return this;
    }

    public TLSResearchRecipeBuilder ink(Ingredient ink) {
        this.ink = ink;
        return this;
    }

    @Override
    public @NotNull TLSResearchRecipeBuilder unlockedBy(@NotNull String key, @NotNull Criterion<?> trigger) {
        this.criteria.put(key, trigger);
        return this;
    }

    @Override
    public @NotNull TLSResearchRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(@NotNull RecipeOutput output) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(this.result.getItem());
        this.save(output, id);
    }

    @Override
    public void save(RecipeOutput output, @NotNull ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);

        TLSResearchRecipe recipe = new TLSResearchRecipe(this.result, this.blade, this.paper, this.ink);
        output.accept(id, recipe, advancementBuilder.build(
                id.withPrefix("recipes/research/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
