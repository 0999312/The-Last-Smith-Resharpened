package cn.mmf.tls.data;

import java.util.concurrent.CompletableFuture;

import cn.mcmod_mmf.mmlib.data.AbstractRecipeProvider;
import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.data.tag.TLSItemTags;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import mods.flammpfeil.slashblade.recipe.SlashBladeSmithingRecipeBuilder;
import mods.flammpfeil.slashblade.registry.SlashBladeItems;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import static cn.mmf.tls.data.builtin.TLSSlashBladeRegistry.*;
import static cn.mmf.tls.item.ItemRegistry.*;
import static mods.flammpfeil.slashblade.registry.SlashBladeItems.*;

public class TLSRecipeProvider extends AbstractRecipeProvider {
    // 2026-06-13:17-35 Store lookup for enchantment holder resolution
    private final CompletableFuture<HolderLookup.Provider> lookup;

    public TLSRecipeProvider(PackOutput gen, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(gen, TheLastSmith.MODID, lookupProvider);
        this.lookup = lookupProvider;
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput consumer) {
        // 2026-06-13:17-35 Added all non-research recipe generation
        addAllResearchRecipe(consumer);
        addSmithingTransformRecipes(consumer);
        addShapelessRecipes(consumer);
        addShapedRecipes(consumer);
        addShapedBladeRecipes(consumer);
        addBladeSmithingRecipes(consumer);

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(BLADE_UNFINISHED_2.get()),
                        RecipeCategory.MISC,
                        BLADE_UNFINISHED_3.get(),
                        1.0f,
                        200
                ).unlockedBy(getHasName(BLADE_UNFINISHED_2.get()), has(BLADE_UNFINISHED_2.get()))
                .save(consumer, prefix("blade_step_3"));
        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(BLADE_SAKURA_UNFINISHED_2.get()),
                        RecipeCategory.MISC,
                        BLADE_SAKURA_UNFINISHED_3.get(),
                        1.0f,
                        200
                ).unlockedBy(getHasName(BLADE_SAKURA_UNFINISHED_2.get()), has(BLADE_SAKURA_UNFINISHED_2.get()))
                .save(consumer, prefix("blade_sakura_step_3"));
        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(SAKURA_STEEL_INGOT.get()),
                        RecipeCategory.MISC,
                        SAKURA_SPHERE.get(),
                        1.0f,
                        200
                ).unlockedBy(getHasName(SAKURA_STEEL_INGOT.get()), has(SAKURA_STEEL_INGOT.get()))
                .save(consumer, prefix("sakura_sphere"));
    }

    private void addAllResearchRecipe(RecipeOutput consumer) {
        addResearchRecipe(consumer, "scroll_basic", SLASHBLADE_WOOD, SCROLL_BASIC);
        addResearchRecipeCopy(consumer, "scroll_basic_copy", SCROLL_BASIC, SCROLL_BASIC, 2);

        addResearchRecipeSetPaper(consumer, "scroll_bewitched_inferno", SCROLL_HEIL, SCROLL_STAR, SCROLL_BEWITCHED);

        addResearchRecipe(consumer, "scroll_blade", SLASHBLADE_WHITE, SCROLL_BLADE);
        addResearchRecipeCopy(consumer, "scroll_blade_copy", SCROLL_BLADE, SCROLL_BLADE, 2);

        addResearchRecipe(consumer, "scroll_blood", SILVERBAMBOO_BLOOD, SCROLL_BLOOD);
        addResearchRecipeCopy(consumer, "scroll_blood_copy", SCROLL_BLOOD, SCROLL_BLOOD, 2);

        addResearchRecipeCopy(consumer, "scroll_exorcism_copy", SCROLL_EXORCISM, SCROLL_EXORCISM, 2);
        addResearchRecipe(consumer, "scroll_exorcism_ginkgo", EXORCISM_GINKGO, SCROLL_EXORCISM);
        addResearchRecipe(consumer, "scroll_exorcism_sakura", EXORCISM_SAKURA, SCROLL_EXORCISM);
        addResearchRecipe(consumer, "scroll_exorcism_yuki", EXORCISM_YUKI, SCROLL_EXORCISM);

        addResearchRecipe(consumer, "scroll_god", GOLDENBAMBOO, SCROLL_GOD);
        addResearchRecipeCopy(consumer, "scroll_god_copy", SCROLL_GOD, SCROLL_GOD, 2);

        addResearchRecipe(consumer, "scroll_inferno_hakurouken", HAKUROUKEN_NETHER, SCROLL_HEIL);
        // TODO scroll_inferno_nihilbx 似蛭，待添加
        addResearchRecipe(consumer, "scroll_inferno_roukanken", ROUKANKEN_NETHER, SCROLL_HEIL);

        // 2026-06-13:17-38 Fixed: use blankNameless() to only match un-named blades, preventing conflict with named blade recipes
        addResearchRecipe(consumer, "scroll_katana", SlashBladeIngredient.blankNameless(), SCROLL_KATANA);
        addResearchRecipeCopy(consumer, "scroll_katana_copy", SCROLL_KATANA, SCROLL_KATANA, 2);

        addResearchRecipeSetPaper(consumer, "scroll_last_1", SCROLL_TENGU_VOL1, SCROLL_TENGU_VOL2, SCROLL_TENGU_FULL);
        addResearchRecipeSetPaper(consumer, "scroll_last_2", SCROLL_TENGU_VOL2, SCROLL_TENGU_VOL1, SCROLL_TENGU_FULL);

        addResearchRecipe(consumer, "scroll_mortal", FUSHIGIRI, SCROLL_MORTAL);

        addResearchRecipeCopy(consumer, "scroll_muramasa_copy", SCROLL_MURAMASA, SCROLL_MURAMASA, 2);
        addResearchRecipe(consumer, "scroll_muramasa_from_chizuki", SlashBladeBuiltInRegistry.MURAMASA, SCROLL_MURAMASA);
        addResearchRecipe(consumer, "scroll_muramasa_from_kagura", MURAMASA_KAGURA, SCROLL_MURAMASA);

        addResearchRecipe(consumer, "scroll_named", CompoundIngredient.of(
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.DOUTANUKI.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RUBY.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.TUKUMO.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_WOODEN.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_STONE.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_IRON.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_GOLDEN.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_DIAMOND.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
        ), SCROLL_NAMED);
        addResearchRecipeCopy(consumer, "scroll_named_copy", SCROLL_NAMED, SCROLL_NAMED, 2);
        // TODO scroll_named_sjap 日系附属包名刀，待添加

        addResearchRecipe(consumer, "scroll_oboro_muarmasa", OBORO_MURAMASA, SCROLL_CAUSALITY);
        addResearchRecipeSetPaper(consumer, "scroll_odachi", SCROLL_BLADE, SCROLL_KATANA, SCROLL_ODACHI);
        addResearchRecipeCopy(consumer, "scroll_odachi_copy", SCROLL_ODACHI, SCROLL_ODACHI, 2);

        addResearchRecipe(consumer, "scroll_sakura", SAKURA_FULL, SCROLL_SAKURA);
        addResearchRecipe(consumer, "scroll_sakura_blade", NAGASADA, SCROLL_SAKURA_BLADE);
        addResearchRecipeCopy(consumer, "scroll_sakura_blade_copy", SCROLL_SAKURA_BLADE, SCROLL_SAKURA_BLADE, 2);
        addResearchRecipeCopy(consumer, "scroll_sakura_copy", SCROLL_SAKURA, SCROLL_SAKURA, 2);

        addResearchRecipe(consumer, "scroll_sakura_full", SAKURA_SPHERE, SCROLL_SAKURA_FULL);
        addResearchRecipeCopy(consumer, "scroll_sakura_full_copy", SCROLL_SAKURA_FULL, SCROLL_SAKURA_FULL, 2);

        addResearchRecipeSetPaper(consumer, "scroll_sharpness", SCROLL_CAUSALITY, SCROLL_GOD, SCROLL_SHARPNESS);

        addResearchRecipe(consumer, "scroll_shura", EVIL_SHURA, SCROLL_SHURA);
        addResearchRecipeCopy(consumer, "scroll_shura_copy", SCROLL_SHURA, SCROLL_SHURA, 2);

        addResearchRecipe(consumer, "scroll_star", CompoundIngredient.of(
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.SANGE.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.YASHA_TRUE.location()),
                SlashBladeIngredient.of(SlashBladeBuiltInRegistry.OROTIAGITO.location())
        ), SCROLL_STAR);
        addResearchRecipeCopy(consumer, "scroll_star_copy", SCROLL_STAR, SCROLL_STAR, 2);
        // TODO scroll_star_sjap 日系附属包星落者，待添加

        addResearchRecipe(consumer, "scroll_tengu_1", AMAGUMO_KAZE, SCROLL_TENGU_VOL1);
        addResearchRecipe(consumer, "scroll_tengu_2", AMAGUMO_KUMO, SCROLL_TENGU_VOL2);

        addResearchRecipe(consumer, "scroll_wood_basic", SLASHBLADE_BAMBOO, SCROLL_WOOD_BASIC);
        addResearchRecipeCopy(consumer, "scroll_wood_basic_copy", SCROLL_WOOD_BASIC, SCROLL_WOOD_BASIC, 2);
        addResearchRecipe(consumer, "scroll_wood_kiwami", BAMBOO_HERMIT, SCROLL_WOOD_KIWAMI);
        addResearchRecipeCopy(consumer, "scroll_wood_kiwami_copy", SCROLL_WOOD_KIWAMI, SCROLL_WOOD_KIWAMI, 2);

        addResearchRecipe(consumer, "scroll_yamato", YAMATO, SCROLL_YAMATO);
    }

    private void addResearchRecipeCopy(RecipeOutput consumer, String id, DeferredHolder<Item, Item> blade, DeferredHolder<Item, Item> result, int resultCount) {
        addResearchRecipeCopy(consumer, id, Ingredient.of(blade.get()), result.get(), resultCount);

    }

    private void addResearchRecipeCopy(RecipeOutput consumer, String id, Ingredient blade,
                                       net.minecraft.world.level.ItemLike result, int resultCount) {
        // 2026-06-13:17-39 Fixed: call result.asItem() to convert ItemLike → Item for researching()
        cn.mmf.tls.recipe.TLSResearchRecipeBuilder
                .researching(blade, result.asItem(), resultCount)
                .unlockedBy(getHasName(result.asItem()), has(result.asItem()))
                .save(consumer, TheLastSmith.prefix("research/" + id));
    }

    private void addResearchRecipe(RecipeOutput consumer, String id, Ingredient blade,
                                   net.minecraft.world.level.ItemLike result) {
        // 2026-06-13:17-39 Fixed: call result.asItem() to convert ItemLike → Item for researching()
        cn.mmf.tls.recipe.TLSResearchRecipeBuilder
                .researching(blade, result.asItem())
                .unlockedBy(getHasName(result.asItem()), has(result.asItem()))
                .save(consumer, TheLastSmith.prefix("research/" + id));
    }

    private void addResearchRecipe(RecipeOutput con, String id, Ingredient blade, DeferredHolder<Item, Item> result) {
        addResearchRecipe(con, id, blade, result.get());
    }

    private void addResearchRecipe(RecipeOutput con, String id, ResourceKey<SlashBladeDefinition> blade, DeferredHolder<Item, Item> result) {
        addResearchRecipe(con, id, SlashBladeIngredient.of(blade.location()), result.get());
    }

    private void addResearchRecipe(RecipeOutput con, String id, DeferredHolder<Item, Item> blade, DeferredHolder<Item, Item> result) {
        addResearchRecipe(con, id, Ingredient.of(blade.get()), result.get());
    }

    private void addResearchRecipeSetPaper(RecipeOutput con, String id, DeferredHolder<Item, Item> paper, DeferredHolder<Item, Item> blade, DeferredHolder<Item, Item> result) {
        addResearchRecipeSetPaper(con, id, Ingredient.of(paper.get()), Ingredient.of(blade.get()), result.get());
    }

    private void addResearchRecipeSetPaper(RecipeOutput consumer, String id, Ingredient paper, Ingredient blade, net.minecraft.world.level.ItemLike result) {
        cn.mmf.tls.recipe.TLSResearchRecipeBuilder
                .researching(blade, result.asItem()).paper(paper)
                .unlockedBy(getHasName(result.asItem()), has(result.asItem()))
                .save(consumer, TheLastSmith.prefix("research/" + id));
    }

    // ─── 2026-06-13:17-35 Smithing Transform Recipes (vanilla smithing table) ───

    private void addSmithingTransformRecipes(RecipeOutput consumer) {
        // blade_step_1: scroll_basic + iron ingot + proudsoul_ingot → blade_unfinished_1
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(SCROLL_BASIC.get()),
                        Ingredient.of(Tags.Items.INGOTS_IRON),
                        Ingredient.of(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_INGOT.get()),
                        RecipeCategory.MISC, BLADE_UNFINISHED_1.get())
                .unlocks(getHasName(SCROLL_BASIC.get()), has(SCROLL_BASIC.get()))
                .save(consumer, prefix("blade_step_1"));

        // blade_step_5: scroll_basic + blade_unfinished_4 + gold ingot → blade
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(SCROLL_BASIC.get()),
                        Ingredient.of(BLADE_UNFINISHED_4.get()),
                        Ingredient.of(Tags.Items.INGOTS_GOLD),
                        RecipeCategory.MISC, BLADE.get())
                .unlocks(getHasName(BLADE.get()), has(BLADE.get()))
                .save(consumer, prefix("blade_step_5"));

        // blade_sakura_step_1: scroll_blade + proudsoul_ingot + sakura_steel_ingot → blade_sakura_unfinished_1
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(SCROLL_BLADE.get()),
                        Ingredient.of(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_INGOT.get()),
                        Ingredient.of(SAKURA_STEEL_INGOT.get()),
                        RecipeCategory.MISC, BLADE_SAKURA_UNFINISHED_1.get())
                .unlocks(getHasName(SAKURA_STEEL_INGOT.get()), has(SAKURA_STEEL_INGOT.get()))
                .save(consumer, prefix("blade_sakura_step_1"));

        // blade_sakura_step_5: scroll_blade + blade_sakura_unfinished_4 + gold ingot → blade_sakura
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(SCROLL_BLADE.get()),
                        Ingredient.of(BLADE_SAKURA_UNFINISHED_4.get()),
                        Ingredient.of(Tags.Items.INGOTS_GOLD),
                        RecipeCategory.MISC, BLADE_SAKURA.get())
                .unlocks(getHasName(BLADE_SAKURA.get()), has(BLADE_SAKURA.get()))
                .save(consumer, prefix("blade_sakura_step_5"));

        // sakura_steel: scroll_sakura + proudsoul_ingot + sakura_full → sakura_steel_ingot
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(SCROLL_SAKURA.get()),
                        Ingredient.of(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_INGOT.get()),
                        Ingredient.of(SAKURA_FULL.get()),
                        RecipeCategory.MISC, SAKURA_STEEL_INGOT.get())
                .unlocks(getHasName(SAKURA_FULL.get()), has(SAKURA_FULL.get()))
                .save(consumer, prefix("sakura_steel"));
    }

    // ─── 2026-06-13:17-35 Shapeless Crafting Recipes ───

    private void addShapelessRecipes(RecipeOutput consumer) {

        // yakibatsuchi: clay_ball + coal + flint → yakibatsuchi
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, YAKIBATSUCHI.get())
                .requires(Items.CLAY_BALL)
                .requires(ItemTags.COALS)
                .requires(Items.FLINT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                .save(consumer, prefix("yakibatsuchi"));

        // blade_step_2: blade_unfinished_1 + yakibatsuchi → blade_unfinished_2
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BLADE_UNFINISHED_2.get())
                .requires(BLADE_UNFINISHED_1.get())
                .requires(YAKIBATSUCHI.get())
                .unlockedBy(getHasName(YAKIBATSUCHI.get()), has(YAKIBATSUCHI.get()))
                .save(consumer, prefix("blade_step_2"));

        // blade_sakura_step_2: blade_sakura_unfinished_1 + yakibatsuchi → blade_sakura_unfinished_2
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BLADE_SAKURA_UNFINISHED_2.get())
                .requires(BLADE_SAKURA_UNFINISHED_1.get())
                .requires(YAKIBATSUCHI.get())
                .unlockedBy(getHasName(YAKIBATSUCHI.get()), has(YAKIBATSUCHI.get()))
                .save(consumer, prefix("blade_sakura_step_2"));

        // sakura: proudsoul_tiny + cherry leaves ×2 → sakura
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SAKURA.get())
                .requires(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_TINY.get())
                .requires(tag("c:leaves/cherry"))
                .requires(tag("c:leaves/cherry"))
                .unlockedBy(getHasName(SAKURA.get()), has(SAKURA.get()))
                .save(consumer, prefix("sakura"));
    }

    // ─── 2026-06-13:17-35 Shaped Crafting Recipes ───

    private void addShapedRecipes(RecipeOutput consumer) {
        // research_table: paper + black dye, wooden slabs, wooden fences
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RESEARCH_TABLE.get())
                .pattern("P I")
                .pattern("AAA")
                .pattern("F F")
                .define('P', Items.PAPER)
                .define('I', tag("c:dyes/black"))
                .define('A', ItemTags.WOODEN_SLABS)
                .define('F', ItemTags.WOODEN_FENCES)
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer, prefix("research_table"));

        // sakura_full: sakura ×8 surrounding proudsoul
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SAKURA_FULL.get())
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', SAKURA.get())
                .define('S', mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL.get())
                .unlockedBy(getHasName(SAKURA.get()), has(SAKURA.get()))
                .save(consumer, prefix("sakura_full"));
    }

    // ─── 2026-06-13:17-35 Shaped Blade Recipes (slashblade:shaped_blade) ───

    private void addShapedBladeRecipes(RecipeOutput consumer) {
        // eievui: dandelion + sakura + white sheath blade → eievui
        SlashBladeShapedRecipeBuilder.shaped(EIEVUI.location())
                .pattern("LIL")
                .pattern("IBI")
                .pattern("LIL")
                .define('L', Ingredient.of(Items.DANDELION))
                .define('I', Ingredient.of(SAKURA.get()))
                .define('B', Ingredient.of(SlashBladeItems.SLASHBLADE_WHITE.get()))
                .unlockedBy(getHasName(SAKURA.get()), has(SAKURA.get()))
                .save(consumer, TheLastSmith.prefix("eievui"));

        // sweapon: lapis blocks + iron ingots + iron sword → sweapon
        SlashBladeShapedRecipeBuilder.shaped(SWEAPON.location())
                .pattern("LI ")
                .pattern("LI ")
                .pattern(" B ")
                .define('L', Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .define('I', Ingredient.of(Tags.Items.INGOTS_IRON))
                .define('B', Ingredient.of(Items.IRON_SWORD))
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .save(consumer, TheLastSmith.prefix("sweapon"));

        // 胧村正配方
        SlashBladeShapedRecipeBuilder.shaped(OBORO_MURAMASA.location())
                .pattern("OFO")
                .pattern("SBK")
                .pattern("OFO")
                .define('O', Ingredient.of(PROUDSOUL_SPHERE.get()))
                .define('F', Ingredient.of(SAKURA_FULL.get()))
                .define('S', namedBladeBase(tlsLoc("muramasa_kagura"),
                        req().name(tlsLoc("muramasa_kagura")).proudSoul(5000).killCount(1500).refineCount(20).build()))
                .define('B', SCROLL_MURAMASA.get())
                .define('K', namedBladeBase(ResourceLocation.fromNamespaceAndPath(SlashBlade.MODID, "muramasa"),
                        req().name(ResourceLocation.fromNamespaceAndPath(SlashBlade.MODID, "muramasa")).proudSoul(5000).killCount(1500).refineCount(20).build()))
                .unlockedBy(getHasName(SCROLL_MURAMASA.get()), has(SCROLL_MURAMASA.get()))
                .save(consumer, TheLastSmith.prefix("oboro_muramasa"));
    }

    // ─── 2026-06-13:17-35 Blade Smithing Recipes (slashblade:slashblade_smithing) ───

    private void addBladeSmithingRecipes(RecipeOutput consumer) {
        ResourceLocation modLoc = ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, "unused");
        // Using inline lambdas for clarity

        // 原拔刀剑配方覆盖
        addBladeSmithingCover(consumer, "slashblade_bamboo",
                bladeItem(SCROLL_BASIC.get()),
                simpleBase(SLASHBLADE_WOOD.get()),
                Ingredient.of(TLSItemTags.BAMBOO),
                SlashBladeItems.SLASHBLADE_BAMBOO.getId());
        addBladeSmithingCover(consumer, "slashblade_white",
                bladeItem(SCROLL_BASIC.get()),
                simpleBase(SLASHBLADE_WOOD.get()),
                bladeItem(BLADE.get()),
                SlashBladeItems.SLASHBLADE_WHITE.getId());
        addBladeSmithingCover(consumer, "slashblade",
                bladeItem(SCROLL_BLADE.get()),
                simpleBase(SLASHBLADE_WHITE.get()),
                namedBladeBase(SLASHBLADE_SILVERBAMBOO.getId(), req().name(ResourceLocation.fromNamespaceAndPath(SlashBlade.MODID, "slashblade_silverbamboo")).addSwordType(SwordType.BROKEN).build()),
                SlashBladeItems.SLASHBLADE.getId());
        addBladeSmithingCover(consumer, "ruby",
                bladeItem(SCROLL_KATANA.get()),
                simpleBase(SlashBladeItems.SLASHBLADE.get()),
                bladeItem(SAKURA.get()),
                SlashBladeBuiltInRegistry.RUBY.location());

        // --- nagasada: blade_sakura + scroll_sakura → nagasada (simple item base)
        addBladeSmithing(consumer, "nagasada",
                bladeItem(SCROLL_SAKURA.get()), simpleBase(SlashBladeItems.SLASHBLADE_SILVERBAMBOO.get()),
                bladeItem(BLADE_SAKURA.get()), tlsLoc("nagasada"));

        // --- nameless_odachi: proudsoul_ingot + scroll_odachi → nameless_odachi (simple item base)
        addBladeSmithing(consumer, "nameless_odachi",
                bladeItem(SCROLL_ODACHI.get()), simpleBase(SlashBladeItems.SLASHBLADE.get()),
                bladeItem(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_INGOT.get()),
                tlsLoc("nameless_odachi"));

        // --- bamboo_top: sakura + scroll_wood_basic → bamboo_top (simple item base)
        addBladeSmithing(consumer, "bamboo_top",
                bladeItem(SCROLL_WOOD_BASIC.get()), simpleBase(SlashBladeItems.SLASHBLADE_BAMBOO.get()),
                bladeItem(SAKURA.get()), tlsLoc("bamboo_top"));

        // --- silverbamboo_top: sakura + scroll_wood_basic → silverbamboo_top (simple item base)
        addBladeSmithing(consumer, "silverbamboo_top",
                bladeItem(SCROLL_WOOD_BASIC.get()), simpleBase(SlashBladeItems.SLASHBLADE_SILVERBAMBOO.get()),
                bladeItem(SAKURA.get()), tlsLoc("silverbamboo_top"));

        // --- bamboo_hermit: sakura_sphere + scroll_sakura_full → bamboo_hermit
        addBladeSmithing(consumer, "bamboo_hermit",
                bladeItem(SCROLL_SAKURA_FULL.get()),
                namedBladeBase(tlsLoc("bamboo_top"), req().name(tlsLoc("bamboo_top")).proudSoul(1000).refineCount(10)
                        .addEnchantment(ench(Enchantments.SOUL_SPEED)).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("bamboo_hermit"));

        // --- bloodybamboo: sakura_full + scroll_sakura_full → silverbamboo_blood
        addBladeSmithing(consumer, "bloodybamboo",
                bladeItem(SCROLL_SAKURA_FULL.get()),
                namedBladeBase(tlsLoc("silverbamboo_top"), req().name(tlsLoc("silverbamboo_top")).killCount(1000).refineCount(20)
                        .addEnchantment(ench(Enchantments.SHARPNESS)).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("silverbamboo_blood"));

        // --- goldenbamboo: gold block + scroll_sakura_full → goldenbamboo
        addBladeSmithing(consumer, "goldenbamboo",
                bladeItem(SCROLL_SAKURA_FULL.get()),
                namedBladeBase(tlsLoc("silverbamboo_top"), req().name(tlsLoc("silverbamboo_top")).killCount(1000).refineCount(20)
                        .addEnchantment(ench(Enchantments.SMITE)).build()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD), tlsLoc("goldenbamboo"));

        // --- amagumo_kaze: feather + scroll_sharpness → amagumo_kaze
        addBladeSmithing(consumer, "amagumo_kaze",
                bladeItem(SCROLL_SHARPNESS.get()),
                namedBladeBase(tlsLoc("nagasada"),
                        req().name(tlsLoc("nagasada")).killCount(500).refineCount(10).build()),
                bladeItem(Items.FEATHER), tlsLoc("amagumo_kaze"));

        // --- amagumo_kumo: feather + scroll_bewitched → amagumo_kumo
        addBladeSmithing(consumer, "amagumo_kumo",
                bladeItem(SCROLL_BEWITCHED.get()),
                namedBladeBase(tlsLoc("nagasada"),
                        req().name(tlsLoc("nagasada")).killCount(500).refineCount(10).build()),
                bladeItem(Items.FEATHER), tlsLoc("amagumo_kumo"));

        // --- amagumo_munin: sakura_sphere + scroll_tengu_full → amagumo_munin
        addBladeSmithing(consumer, "amagumo_munin",
                bladeItem(SCROLL_TENGU_FULL.get()),
                namedBladeBase(tlsLoc("nagasada"),
                        req().name(tlsLoc("nagasada")).killCount(2000).proudSoul(50000).refineCount(50).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("amagumo_munin"));

        // --- hakurouken: ghast_tear + scroll_exorcism → hakurouken
        addBladeSmithing(consumer, "hakurouken",
                bladeItem(SCROLL_EXORCISM.get()),
                namedBladeBase(tlsLoc("nagasada"), req().name(tlsLoc("nagasada")).refineCount(10)
                        .addEnchantment(ench(Enchantments.SMITE)).build()),
                bladeItem(Items.GHAST_TEAR), tlsLoc("hakurouken"));

        // --- roukanken: sakura_sphere + scroll_exorcism → roukanken
        addBladeSmithing(consumer, "roukanken",
                bladeItem(SCROLL_EXORCISM.get()),
                namedBladeBase(tlsLoc("nameless_odachi"), req().name(tlsLoc("nameless_odachi")).refineCount(10)
                        .addEnchantment(ench(Enchantments.SHARPNESS)).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("roukanken"));

        // --- hakurouken_inferno: netherite block + scroll_mortal → hakurouken_nether
        addBladeSmithing(consumer, "hakurouken_inferno",
                bladeItem(SCROLL_MORTAL.get()),
                namedBladeBase(tlsLoc("hakurouken"), req().name(tlsLoc("hakurouken")).killCount(1500).proudSoul(5000)
                        .addEnchantment(ench(Enchantments.FIRE_ASPECT)).build()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE), tlsLoc("hakurouken_nether"));

        // --- roukanken_inferno: netherite block + scroll_yamato → roukanken_nether
        addBladeSmithing(consumer, "roukanken_inferno",
                bladeItem(SCROLL_YAMATO.get()),
                namedBladeBase(tlsLoc("roukanken"), req().name(tlsLoc("roukanken")).killCount(1500).proudSoul(5000)
                        .addEnchantment(ench(Enchantments.FIRE_ASPECT)).build()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE), tlsLoc("roukanken_nether"));

        // --- kusabimaru: sakura_full + scroll_named → kusabimaru
        addBladeSmithing(consumer, "kusabimaru",
                bladeItem(SCROLL_NAMED.get()),
                namedBladeBase(tlsLoc("nagasada"),
                        req().name(tlsLoc("nagasada")).proudSoul(1000).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("kusabimaru"));

        // --- fushigiri: sakura_full + (scroll_blood OR scroll_shura) → fushigiri
        addBladeSmithing(consumer, "fushigiri",
                CompoundIngredient.of(bladeItem(SCROLL_BLOOD.get()), bladeItem(SCROLL_SHURA.get())),
                namedBladeBase(tlsLoc("nameless_odachi"), req().name(tlsLoc("nameless_odachi")).killCount(1000).refineCount(10)
                        .addEnchantment(ench(Enchantments.SHARPNESS)).addEnchantment(ench(Enchantments.SWEEPING_EDGE)).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("fushigiri"));

        // --- exorcism_sakura: pink dye + scroll_named → exorcism_sakura
        addBladeSmithing(consumer, "exorcism_sakura",
                bladeItem(SCROLL_NAMED.get()),
                namedBladeBase(tlsLoc("nagasada"), req().name(tlsLoc("nagasada"))
                        .addEnchantment(ench(Enchantments.SMITE)).build()),
                tagIngredient("c:dyes/pink"), tlsLoc("exorcism_sakura"));

        // --- exorcism_ginkgo: yellow dye + scroll_named → exorcism_ginkgo
        addBladeSmithing(consumer, "exorcism_ginkgo",
                bladeItem(SCROLL_NAMED.get()),
                namedBladeBase(tlsLoc("nagasada"), req().name(tlsLoc("nagasada"))
                        .addEnchantment(ench(Enchantments.SMITE)).build()),
                tagIngredient("c:dyes/yellow"), tlsLoc("exorcism_ginkgo"));

        // --- exorcism_yuki: light_blue dye + scroll_named → exorcism_yuki
        addBladeSmithing(consumer, "exorcism_yuki",
                bladeItem(SCROLL_NAMED.get()),
                namedBladeBase(tlsLoc("nagasada"), req().name(tlsLoc("nagasada"))
                        .addEnchantment(ench(Enchantments.SMITE)).build()),
                tagIngredient("c:dyes/light_blue"), tlsLoc("exorcism_yuki"));

        // --- kataware_exorcism variants: all produce evil_kataware
        addBladeSmithing(consumer, "kataware_exorcism_sakura",
                bladeItem(SCROLL_SAKURA_BLADE.get()),
                namedBladeBase(tlsLoc("exorcism_sakura"),
                        req().name(tlsLoc("exorcism_sakura")).killCount(100).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("evil_kataware"));

        addBladeSmithing(consumer, "kataware_exorcism_ginkgo",
                bladeItem(SCROLL_SAKURA_BLADE.get()),
                namedBladeBase(tlsLoc("exorcism_ginkgo"),
                        req().name(tlsLoc("exorcism_ginkgo")).killCount(100).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("evil_kataware"));

        addBladeSmithing(consumer, "kataware_exorcism_yuki",
                bladeItem(SCROLL_SAKURA_BLADE.get()),
                namedBladeBase(tlsLoc("exorcism_yuki"),
                        req().name(tlsLoc("exorcism_yuki")).killCount(100).build()),
                bladeItem(SAKURA_FULL.get()), tlsLoc("evil_kataware"));

        // --- evil_shura: sakura_sphere + scroll_sakura_full → evil_shura
        addBladeSmithing(consumer, "evil_shura",
                bladeItem(SCROLL_SAKURA_FULL.get()),
                namedBladeBase(tlsLoc("evil_kataware"), req().name(tlsLoc("evil_kataware")).killCount(250)
                        .addEnchantment(ench(Enchantments.POWER)).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("evil_shura"));

        // --- muramasa_chizuki: proudsoul_sphere + scroll_muramasa → muramasa (no name requirement)
        addBladeSmithing(consumer, "muramasa_chizuki",
                bladeItem(SCROLL_MURAMASA.get()),
                namedBladeBase(null, req().proudSoul(1000).refineCount(10).build()),
                bladeItem(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_SPHERE.get()),
                ResourceLocation.fromNamespaceAndPath("slashblade", "muramasa"));

        // --- muramasa_kagura: proudsoul_sphere + scroll_muramasa → muramasa_kagura
        addBladeSmithing(consumer, "muramasa_kagura",
                bladeItem(SCROLL_MURAMASA.get()),
                namedBladeBase(tlsLoc("nameless_odachi"),
                        req().name(tlsLoc("nameless_odachi")).proudSoul(1000).refineCount(10).build()),
                bladeItem(mods.flammpfeil.slashblade.registry.SlashBladeItems.PROUDSOUL_SPHERE.get()),
                tlsLoc("muramasa_kagura"));

        // --- sagaquoia: sakura_sphere + scroll_wood_kiwami → sagequoia
        addBladeSmithing(consumer, "sagaquoia",
                bladeItem(SCROLL_WOOD_KIWAMI.get()),
                namedBladeBase(ResourceLocation.fromNamespaceAndPath("slashblade", "tagayasan"),
                        req().name(ResourceLocation.fromNamespaceAndPath("slashblade", "tagayasan"))
                                .killCount(230).refineCount(10).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("sagequoia"));

        // --- yamato_full: sakura_sphere + scroll_star → yamato_neo
        addBladeSmithing(consumer, "yamato_full",
                bladeItem(SCROLL_STAR.get()),
                namedBladeBase(ResourceLocation.fromNamespaceAndPath("slashblade", "yamato"),
                        req().name(ResourceLocation.fromNamespaceAndPath("slashblade", "yamato"))
                                .refineCount(10).build()),
                bladeItem(SAKURA_SPHERE.get()), tlsLoc("yamato_neo"));

        // --- slashblade_from_bamboolight: blade + scroll_katana → slashblade (broken sword_type base)
        addBladeSmithing(consumer, "slashblade_from_bamboolight",
                bladeItem(SCROLL_KATANA.get()),
                namedBladeBase(null, req()
                                .addSwordType(mods.flammpfeil.slashblade.item.SwordType.BROKEN).build(),
                        SlashBladeItems.SLASHBLADE_SILVERBAMBOO.get()),
                bladeItem(BLADE.get()), ResourceLocation.fromNamespaceAndPath("slashblade", "slashblade"));

        // --- old_slashblade: sweapon blade + scroll_named → slashblade
        addBladeSmithing(consumer, "old_slashblade",
                bladeItem(SCROLL_NAMED.get()),
                namedBladeBase(null, req().build()),
                SlashBladeIngredient.of(tlsLoc("sweapon")),
                tlsLoc("slashblade"));

        // 2026-06-13:17-35: bunshi recipe depends on energyblade (conditional), kept as TODO
        // TODO: bunshi recipe — requires energyblade mod for 1.21.1
    }

    // ─── 2026-06-13:17-35 Helper methods for blade smithing recipes ───

    private void addBladeSmithing(RecipeOutput consumer, String name,
                                  Ingredient template, Ingredient base, Ingredient addition,
                                  ResourceLocation resultBlade) {
        SlashBladeSmithingRecipeBuilder.smithing(template, base, addition,
                        RecipeCategory.MISC, resultBlade)
                .unlocks("has_" + name, has(template.getItems()[0].getItem()))
                .save(consumer, TheLastSmith.prefix(name));
    }

    private void addBladeSmithingCover(RecipeOutput consumer, String name,
                                       Ingredient template, Ingredient base, Ingredient addition,
                                       ResourceLocation resultBlade) {
        ICondition condition = new ModLoadedCondition(SlashBlade.MODID);
        RecipeOutput output = consumer.withConditions(condition);
        SlashBladeSmithingRecipeBuilder.smithing(template, base, addition, RecipeCategory.MISC, resultBlade)
                .unlocks("has_" + name, has(template.getItems()[0].getItem()))
                .save(output, SlashBlade.prefix(name));

    }

    private Ingredient simpleBase(net.minecraft.world.level.ItemLike item) {
        return Ingredient.of(item);
    }

    private Ingredient namedBladeBase(ResourceLocation name, RequestDefinition request) {
        return namedBladeBase(name, request, SlashBladeItems.SLASHBLADE.get());
    }

    private Ingredient namedBladeBase(ResourceLocation name, RequestDefinition request,
                                      net.minecraft.world.level.ItemLike item) {
        // 2026-06-13:17-35 Use factory method instead of protected constructor
        return SlashBladeIngredient.of(item, request);
    }

    private Ingredient bladeItem(net.minecraft.world.level.ItemLike item) {
        return Ingredient.of(item);
    }

    private Ingredient tagIngredient(String tagPath) {
        return Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse(tagPath)));
    }

    private static RequestDefinition.Builder req() {
        return RequestDefinition.Builder.newInstance();
    }

    private EnchantmentDefinition ench(ResourceKey<Enchantment> enchantmentKey) {
        // 2026-06-13:17-35 Get Holder<Enchantment> from registry lookup
        var holder = this.lookup.join().lookup(Registries.ENCHANTMENT).orElseThrow()
                .getOrThrow(enchantmentKey);
        return new EnchantmentDefinition(holder, 1);
    }

    private static ResourceLocation tlsLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, name);
    }

    private static TagKey<Item> tag(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.parse(path));
    }
}
