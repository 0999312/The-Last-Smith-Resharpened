package cn.mmf.tls.recipe;

import java.util.stream.Stream;

import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.item.ScrollItem;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record TLSResearchRecipe(ItemStack output, Ingredient blade, Ingredient paper,
                                Ingredient ink) implements Recipe<TLSResearchRecipeInput> {

    @Override
    public boolean matches(TLSResearchRecipeInput wrapper, @NotNull Level level) {
        return this.blade.test(wrapper.getItem(TLSResearchRecipeInput.BLADE))
                && this.paper.test(wrapper.getItem(TLSResearchRecipeInput.PAPER))
                && this.ink.test(wrapper.getItem(TLSResearchRecipeInput.INK));
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.blade, this.paper, this.ink).anyMatch(Ingredient::isEmpty);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull TLSResearchRecipeInput input, HolderLookup.@NotNull Provider registries) {
        // 2026-09-20:17-47 Fixed: the recipe broke once the crafted note was moved into the player inventory.
        // Returning the shared output stack made the result slot, the recipe and the inventory reference the same
        // ItemStack, so merging/picking it up in the inventory zeroed the recipe output. Vanilla assemble() always
        // returns a copy (see ShapedRecipe / AbstractCookingRecipe).
        // 2026-09-20:17-47 修复：合成出的笔记放入背包后配方失效。直接返回共享的 output 会导致结果槽、配方与背包
        // 引用同一个 ItemStack，在背包中合并/拾取时会把该引用的数量清零。原版 assemble() 始终返回副本。
        return this.getResultItem(registries).copy();
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 3;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return this.output;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeSerializerRegistry.RESEARCH_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeSerializerRegistry.RESEARCH_RECIPE_TYPE.get();
    }

    public boolean isBladeIngredient(ItemStack stack) {
        return this.blade.test(stack);
    }

    public boolean isPaperIngredient(ItemStack stack) {
        return this.paper.test(stack);
    }

    public boolean isInkIngredient(ItemStack stack) {
        return this.ink.test(stack);
    }

    public static class Serializer implements RecipeSerializer<TLSResearchRecipe> {

        public static final MapCodec<TLSResearchRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output),
                Ingredient.CODEC.fieldOf("blade").forGetter(r -> r.blade),
                Ingredient.CODEC.fieldOf("paper").forGetter(r -> r.paper),
                Ingredient.CODEC.fieldOf("ink").forGetter(r -> r.ink)
        ).apply(inst, TLSResearchRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TLSResearchRecipe> STREAM_CODEC =
                ByteBufCodecs.fromCodecWithRegistries(CODEC.codec());

        @Override
        public @NotNull MapCodec<TLSResearchRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, TLSResearchRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }

    @Override
    public Ingredient blade() {
        return blade;
    }

    @Override
    public Ingredient paper() {
        return paper;
    }

    @Override
    public Ingredient ink() {
        return ink;
    }
}
