package cn.mmf.tls.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record TLSResearchRecipeInput(ItemStack blade, ItemStack paper, ItemStack ink) implements RecipeInput {

    // 槽位定义
    public static final int BLADE = 0;
    public static final int PAPER = 1;
    public static final int INK = 2;

    public TLSResearchRecipeInput(ItemStack blade, ItemStack paper, ItemStack ink) {
        this.blade = blade.copy();
        this.paper = paper.copy();
        this.ink = ink.copy();
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        ItemStack stack;
        switch (i) {
            case BLADE -> stack = blade; // blade
            case PAPER -> stack = paper; // paper
            case INK -> stack = ink; // ink
            default -> throw new IndexOutOfBoundsException("Invalid ingredient index: " + i);
        }
        return stack;
    }

    @Override
    public boolean isEmpty() {
        return this.blade.isEmpty() && this.paper.isEmpty() && this.ink.isEmpty();
    }

    @Override
    public ItemStack blade() {
        return blade;
    }

    @Override
    public ItemStack paper() {
        return paper;
    }

    @Override
    public ItemStack ink() {
        return ink;
    }

    @Override
    public int size() {
        return 3;
    }
}
