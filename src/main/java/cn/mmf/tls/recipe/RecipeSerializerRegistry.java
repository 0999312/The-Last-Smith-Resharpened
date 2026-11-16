package cn.mmf.tls.recipe;

import cn.mmf.tls.TheLastSmith;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeSerializerRegistry {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
            .create(BuiltInRegistries.RECIPE_TYPE, TheLastSmith.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister
            .create(BuiltInRegistries.RECIPE_SERIALIZER, TheLastSmith.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<TLSResearchRecipe>> RESEARCH_RECIPE_TYPE = RECIPE_TYPES
            .register("research", () -> recipeType("research"));
    public static final DeferredHolder<RecipeSerializer<?>, TLSResearchRecipe.Serializer> RESEARCH_RECIPE_SERIALIZER = RECIPE_SERIALIZER
            .register("research", TLSResearchRecipe.Serializer::new);


    private static <T extends Recipe<?>> RecipeType<T> recipeType(String name) {
        return new RecipeType<>() {
            public String toString() {
                return ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, name).toString();
            }
        };
    }
}
