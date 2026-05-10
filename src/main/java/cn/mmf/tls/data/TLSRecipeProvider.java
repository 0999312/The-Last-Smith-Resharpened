package cn.mmf.tls.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import cn.mcmod_mmf.mmlib.data.AbstractRecipeProvider;
import cn.mmf.energyblade.Energyblade;
import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.block.BlockRegistry;
import cn.mmf.tls.data.builtin.TLSSlashBladeRegistry;
import cn.mmf.tls.data.tag.TLSItemTags;
import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.item.ScrollItem;
import cn.mmf.tls.recipe.TLSResearchRecipeBuilder;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import mods.flammpfeil.slashblade.recipe.SlashBladeSmithingRecipeBuilder;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tracen.umapyoi.data.builtin.UmaDataRegistry;
import net.tracen.umapyoi.recipe.RequestUma;
import net.tracen.umapyoi.recipe.UmasoulIngredient;

public class TLSRecipeProvider extends AbstractRecipeProvider {

	public TLSRecipeProvider(PackOutput gen) {
		super(gen);
		
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.SAKURA.get(), 1)
        .requires(SBItems.proudsoul_tiny).requires(TLSItemTags.LEAVES_CHERRY)
        .requires(TLSItemTags.LEAVES_CHERRY)
        .unlockedBy("has_item", has(SBItems.proudsoul_tiny))
        .save(consumer);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.YAKIBATSUCHI.get(), 1)
        .requires(Items.CLAY_BALL).requires(ItemTags.COALS)
        .requires(Items.FLINT)
        .unlockedBy("has_item", has(Items.CLAY_BALL))
        .save(consumer);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.BLADE_UNFINISHED_2.get(), 1)
        .requires(ItemRegistry.BLADE_UNFINISHED_1.get()).requires(ItemRegistry.YAKIBATSUCHI.get())
        .unlockedBy("has_item", has(ItemRegistry.BLADE_UNFINISHED_1.get()))
        .save(consumer, TheLastSmith.prefix("blade_step_2"));
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.BLADE_SAKURA_UNFINISHED_2.get(), 1)
        .requires(ItemRegistry.BLADE_SAKURA_UNFINISHED_1.get()).requires(ItemRegistry.YAKIBATSUCHI.get())
        .unlockedBy("has_item", has(ItemRegistry.BLADE_SAKURA_UNFINISHED_1.get()))
        .save(consumer, TheLastSmith.prefix("blade_sakura_step_2"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SAKURA_FULL.get())
        .pattern("AAA")
        .pattern("ASA")
        .pattern("AAA")
        .define('S', SBItems.proudsoul)
        .define('A', ItemRegistry.SAKURA.get())
        .unlockedBy("has_item", has(ItemRegistry.SAKURA.get())).save(consumer);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.RESEARCH_TABLE.get())
        .pattern("P I")
        .pattern("AAA")
        .pattern("F F")
        .define('A', ItemTags.WOODEN_SLABS)
        .define('F', Tags.Items.FENCES_WOODEN)
        .define('P', Items.PAPER)
        .define('I', Tags.Items.DYES_BLACK)
        .unlockedBy("has_item", has(Items.PAPER)).save(consumer);
        
        oreBlasting(consumer, 
        		List.of(ItemRegistry.BLADE_UNFINISHED_2.get()), 
        		RecipeCategory.COMBAT, 
        		ItemRegistry.BLADE_UNFINISHED_3.get(), 0.7F, 100, "blade_sakura_step_3");
        
        oreBlasting(consumer, 
        		List.of(ItemRegistry.BLADE_SAKURA_UNFINISHED_2.get()), 
        		RecipeCategory.COMBAT, 
        		ItemRegistry.BLADE_SAKURA_UNFINISHED_3.get(), 1.0F, 100, "blade_sakura_step_3");
        
        oreBlasting(consumer, 
        		List.of(ItemRegistry.SAKURA_STEEL_INGOT.get()), 
        		RecipeCategory.COMBAT, 
        		ItemRegistry.SAKURA_SPHERE.get(), 1.0F, 100, "sakura_sphere");
        
        SmithingTransformRecipeBuilder.smithing(
        		Ingredient.of(ItemRegistry.SCROLL_SAKURA.get()), 
        		Ingredient.of(SBItems.proudsoul_ingot),  
        		Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT,  ItemRegistry.SAKURA_STEEL_INGOT.get())
        .unlocks("has_item", has(ItemRegistry.SCROLL_SAKURA.get()))
        .save(consumer, TheLastSmith.prefix("sakura_steel"));
		
        SmithingTransformRecipeBuilder.smithing(
        		Ingredient.of(ItemRegistry.SCROLL_BASIC.get()), 
        		Ingredient.of(Tags.Items.INGOTS_IRON),
        		Ingredient.of(SBItems.proudsoul_ingot),  
				RecipeCategory.COMBAT,  ItemRegistry.BLADE_UNFINISHED_1.get())
        .unlocks("has_item", has(ItemRegistry.SCROLL_BASIC.get()))
        .save(consumer, TheLastSmith.prefix("blade_step_1"));
        
        SmithingTransformRecipeBuilder.smithing(
        		Ingredient.of(ItemRegistry.SCROLL_BLADE.get()), 
        		Ingredient.of(SBItems.proudsoul_ingot),
        		Ingredient.of(ItemRegistry.SAKURA_STEEL_INGOT.get()),  
				RecipeCategory.COMBAT,  ItemRegistry.BLADE_SAKURA_UNFINISHED_1.get())
        .unlocks("has_item", has(ItemRegistry.SAKURA_STEEL_INGOT.get()))
        .save(consumer, TheLastSmith.prefix("blade_sakura_step_1"));
        
        SmithingTransformRecipeBuilder.smithing(
        		Ingredient.of(ItemRegistry.SCROLL_BASIC.get()), 
        		Ingredient.of(ItemRegistry.BLADE_UNFINISHED_4.get()),  
        		Ingredient.of(Tags.Items.INGOTS_GOLD),
				RecipeCategory.COMBAT,  ItemRegistry.BLADE.get())
        .unlocks("has_item", has(ItemRegistry.SCROLL_BASIC.get()))
        .save(consumer, TheLastSmith.prefix("blade_step_5"));
        
        SmithingTransformRecipeBuilder.smithing(
        		Ingredient.of(ItemRegistry.SCROLL_BLADE.get()), 
        		Ingredient.of(ItemRegistry.BLADE_SAKURA_UNFINISHED_4.get()),  
        		Ingredient.of(Tags.Items.INGOTS_GOLD),
				RecipeCategory.COMBAT,  ItemRegistry.BLADE_SAKURA.get())
        .unlocks("has_item", has(ItemRegistry.SAKURA_STEEL_INGOT.get()))
        .save(consumer, TheLastSmith.prefix("blade_sakura_step_5"));
        
		vanillaSlashBladeRecipes(consumer);
		tlsSlashBladeRecipes(consumer);
		researchRecipes(consumer);
		sjapRecipes(consumer);
	}

	private void tlsSlashBladeRecipes(Consumer<FinishedRecipe> consumer) {
        SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.EIEVUI.location())
        .pattern("LIL")
        .pattern("IBI")
        .pattern("LIL")
        .define('B', SBItems.slashblade_white)
        .define('L', Ingredient.of(Items.DANDELION))
        .define('I', Ingredient.of(ItemRegistry.SAKURA.get()))
        .unlockedBy(getHasName(SBItems.slashblade_white), has(SBItems.slashblade_white)).save(consumer);
        
        SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.SWEAPON.location())
        .pattern("LI ")
        .pattern("LI ")
        .pattern(" B ")
        .define('B', Items.IRON_SWORD)
        .define('L', Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
        .define('I', Ingredient.of(Tags.Items.INGOTS_IRON))
        .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD)).save(consumer);
        
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .build()),  
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.SWEAPON.location())
                        .build()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.SLASHBLADE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_NAMED.get()), has(ItemRegistry.SCROLL_NAMED.get()))
		.save(consumer, TheLastSmith.prefix("old_slashblade"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_WOOD_KIWAMI.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(SlashBladeBuiltInRegistry.TAGAYASAN.location())
                        .killCount(230).refineCount(10)
                        .build()),  
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.SAGEQUOIA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_WOOD_KIWAMI.get()), has(ItemRegistry.SCROLL_WOOD_KIWAMI.get()))
		.save(consumer, TheLastSmith.prefix("sagaquoia"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SHARPNESS.get()), 
				SlashBladeIngredient.of(
						RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.ROUKANKEN.location())
                        .refineCount(10)
                        .build()),
				SlashBladeIngredient.of(
						Energyblade.FORGE_ENERGY_BLADE.get(),
						RequestDefinition.Builder.newInstance()
                        .name(cn.mmf.energyblade.data.BuiltInSlashBladeRegistry.HF_BLADE.location())
                        .build()),
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.BUNSHI.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SHARPNESS.get()), has(ItemRegistry.SCROLL_SHARPNESS.get())), 
		"energyblade",  TheLastSmith.prefix("bunshi"))
		.build(consumer,  TheLastSmith.prefix("bunshi"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_STAR.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(SlashBladeBuiltInRegistry.YAMATO.location())
                        .refineCount(10)
                        .build()),  
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.YAMATO.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("yamato_full"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_FULL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.BAMBOO_TOP.location())
                        .proudSoul(1000).refineCount(10)
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SOUL_SPEED), 1))
                        .build()),  
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.BAMBOO_HERMIT.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("bamboo_hermit"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_FULL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.SILVERBAMBOO_TOP.location())
                        .killCount(1000).refineCount(20)
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SMITE), 1))
                        .build()),  
				Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.GOLDENBAMBOO.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("goldenbamboo"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_FULL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.SILVERBAMBOO_TOP.location())
                        .killCount(1000).refineCount(20)
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SHARPNESS), 1))
                        .build()),  
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.SILVERBAMBOO_BLOOD.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("bloodybamboo"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.fromValues(Stream.of(
						new Ingredient.ItemValue(ItemRegistry.SCROLL_BLOOD.get().getDefaultInstance()),
						new Ingredient.ItemValue(ItemRegistry.SCROLL_SHURA.get().getDefaultInstance())
						)), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAMELESS_ODACHI.location())
                        .killCount(1000).refineCount(10)
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SHARPNESS), 1))
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SWEEPING_EDGE), 1))
                        .build()),  
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.FUSHIGIRI.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("fushigiri"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA.get()), 
				Ingredient.of(SBItems.slashblade_silverbamboo),  
				Ingredient.of(ItemRegistry.BLADE_SAKURA.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.NAGASADA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("nagasada"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_ODACHI.get()), 
				Ingredient.of(SBItems.slashblade),  
				Ingredient.of(SBItems.proudsoul_ingot), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.NAMELESS_ODACHI.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_ODACHI.get()), has(ItemRegistry.SCROLL_ODACHI.get()))
		.save(consumer, TheLastSmith.prefix("nameless_odachi"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_MURAMASA.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAMELESS_ODACHI.location())
                        .proudSoul(1000).refineCount(10)
                        .build()), 
				Ingredient.of(SBItems.proudsoul_sphere), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.MURAMASA_KAGURA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_MURAMASA.get()), has(ItemRegistry.SCROLL_MURAMASA.get()))
		.save(consumer, TheLastSmith.prefix("muramasa_kagura"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_MURAMASA.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .proudSoul(1000).refineCount(10)
                        .build()), 
				Ingredient.of(SBItems.proudsoul_sphere), 
				RecipeCategory.COMBAT, 
				SlashBladeBuiltInRegistry.MURAMASA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_MURAMASA.get()), has(ItemRegistry.SCROLL_MURAMASA.get()))
		.save(consumer, TheLastSmith.prefix("muramasa_chizuki"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_WOOD_BASIC.get()), 
				Ingredient.of(SBItems.slashblade_bamboo),  
				Ingredient.of(ItemRegistry.SAKURA.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.BAMBOO_TOP.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_WOOD_BASIC.get()), has(ItemRegistry.SCROLL_WOOD_BASIC.get()))
		.save(consumer, TheLastSmith.prefix("bamboo_top"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_WOOD_BASIC.get()), 
				Ingredient.of(SBItems.slashblade_silverbamboo),  
				Ingredient.of(ItemRegistry.SAKURA.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.SILVERBAMBOO_TOP.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_WOOD_BASIC.get()), has(ItemRegistry.SCROLL_WOOD_BASIC.get()))
		.save(consumer, TheLastSmith.prefix("silverbamboo_top"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .proudSoul(1000)
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.KUSABIMARU.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get()))
		.save(consumer, TheLastSmith.prefix("kusabimaru"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SMITE), 1))
                        .build()), 
				Ingredient.of(Tags.Items.DYES_PINK), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EXORCISM_SAKURA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("exorcism_sakura"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SMITE), 1))
                        .build()), 
				Ingredient.of(Tags.Items.DYES_YELLOW), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EXORCISM_GINKGO.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("exorcism_ginkgo"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .addEnchantment(new EnchantmentDefinition(
                                getEnchantmentID(Enchantments.SMITE), 1))
                        .build()), 
				Ingredient.of(Tags.Items.DYES_LIGHT_BLUE), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EXORCISM_YUKI.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("exorcism_yuki"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_BLADE.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.EXORCISM_SAKURA.location())
                        .killCount(100)
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EVIL_KATAWARE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("kataware_exorcism_sakura"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_BLADE.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.EXORCISM_GINKGO.location())
                        .killCount(100)
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EVIL_KATAWARE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("kataware_exorcism_ginkgo"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_BLADE.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.EXORCISM_YUKI.location())
                        .killCount(100)
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EVIL_KATAWARE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("kataware_exorcism_yuki"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_FULL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.EVIL_KATAWARE.location())
                        .killCount(250).addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS),1))
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.EVIL_SHURA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("evil_shura"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_EXORCISM.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAMELESS_ODACHI.location())
                        .refineCount(10).addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS),1))
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.ROUKANKEN.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("roukanken"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_YAMATO.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.ROUKANKEN.location())
                        .killCount(1500).proudSoul(5000).addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1))
                        .build()), 
				Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.ROUKANKEN_NETHER.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("roukanken_inferno"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_EXORCISM.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .refineCount(10).addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE),1))
                        .build()), 
				Ingredient.of(Items.GHAST_TEAR), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.HAKUROUKEN.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("hakurouken"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_MORTAL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.HAKUROUKEN.location())
                        .killCount(1500).proudSoul(5000).addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT),1))
                        .build()), 
				Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.HAKUROUKEN_NETHER.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("hakurouken_inferno"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_TENGU_FULL.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .killCount(2000).proudSoul(50000).refineCount(50)
                        .build()), 
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.AMAGUMO_MUNIN.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_TENGU_FULL.get()), has(ItemRegistry.SCROLL_TENGU_FULL.get()))
		.save(consumer, TheLastSmith.prefix("amagumo_munin"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_SHARPNESS.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .killCount(500).refineCount(10)
                        .build()), 
				Ingredient.of(Items.FEATHER), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.AMAGUMO_KAZE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_GOD.get()), has(ItemRegistry.SCROLL_GOD.get()))
		.save(consumer, TheLastSmith.prefix("amagumo_kaze"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_BEWITCHED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .killCount(500).refineCount(10)
                        .build()), 
				Ingredient.of(Items.FEATHER), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.AMAGUMO_KUMO.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_BEWITCHED.get()), has(ItemRegistry.SCROLL_BEWITCHED.get()))
		.save(consumer, TheLastSmith.prefix("amagumo_kumo"));
		
		SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.OBORO_MURAMASA.location())
		.pattern("DFD")
		.pattern("CSU")
		.pattern("DFD")
		.define('S', ItemRegistry.SCROLL_MURAMASA.get())
		.define('F', ItemRegistry.SAKURA_FULL.get())
		.define('D', SBItems.proudsoul_sphere)
		.define('C',
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
						.name(TLSSlashBladeRegistry.MURAMASA_KAGURA.location()).killCount(1500).proudSoul(5000).refineCount(20).build()))
		.define('U',
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
						.name(SlashBladeBuiltInRegistry.MURAMASA.location()).killCount(1500).proudSoul(5000).refineCount(20).build()))
		.unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .refineCount(10)
                        .build()), 
				UmasoulIngredient.of(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get(), 
						new RequestUma(Optional.empty(), Optional.of(UmaDataRegistry.RICE_SHOWER.location()), new ArrayList<>())), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.UMA_RICE_SHOWER.location())
		.unlocks(getHasName(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get()), 
				has(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get())), 
		"blades_derby", TheLastSmith.prefix("uma_rice_shower"))
		.build(consumer, TheLastSmith.prefix("uma_rice_shower"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .refineCount(10)
                        .build()), 
				UmasoulIngredient.of(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get(), 
						new RequestUma(Optional.empty(), Optional.of(UmaDataRegistry.MANHATTAN_CAFE.location()), new ArrayList<>())), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.UMA_CAFE.location())
		.unlocks(getHasName(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get()), 
				has(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get())), 
		"blades_derby", TheLastSmith.prefix("uma_cafe"))
		.build(consumer, TheLastSmith.prefix("uma_cafe"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.NAGASADA.location())
                        .refineCount(10)
                        .build()), 
				UmasoulIngredient.of(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get(), 
						new RequestUma(Optional.empty(), Optional.of(UmaDataRegistry.AGNES_DIGITAL.location()), new ArrayList<>())), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.UMA_DIGITAL.location())
		.unlocks(getHasName(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get()), 
				has(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get())), 
		"blades_derby", TheLastSmith.prefix("uma_digital"))
		.build(consumer, TheLastSmith.prefix("uma_digital"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.AMAGUMO_MUNIN.location())
                        .build()), 
				UmasoulIngredient.of(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get(), 
						new RequestUma(Optional.empty(), Optional.of(UmaDataRegistry.AGNUS_TACHYON.location()), new ArrayList<>())), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.UMA_TACHYON.location())
		.unlocks(getHasName(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get()), 
				has(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get())), 
		"blades_derby", TheLastSmith.prefix("uma_tachyon"))
		.build(consumer, TheLastSmith.prefix("uma_tachyon"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_NAMED.get()), 
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .name(TLSSlashBladeRegistry.UMA_TACHYON.location())
                        .build()), 
				UmasoulIngredient.of(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get(), 
						new RequestUma(Optional.empty(), Optional.of(UmaDataRegistry.SYAMEIMARU_ZHENG.location()), new ArrayList<>())), 
				RecipeCategory.COMBAT, 
				TLSSlashBladeRegistry.AMAGUMO_MUNIN.location())
		.unlocks(getHasName(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get()), 
				has(net.tracen.umapyoi.item.ItemRegistry.UMA_SOUL.get())), 
		"blades_derby", TheLastSmith.prefix("munin_from_tachyon"))
		.build(consumer, TheLastSmith.prefix("munin_from_tachyon"));
	}

    private static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }
    
    private void sjapRecipes(Consumer<FinishedRecipe> consumer) {
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()), 
				Ingredient.of(Tags.Items.DYES_BLUE), 
				RecipeCategory.COMBAT, 
				SlashBladeAddonBuiltInRegistry.BLUE.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get())), 
		"slashblade_addon", new ResourceLocation("slashblade_addon", "blue"))
		.build(consumer, new ResourceLocation("slashblade_addon", "blue"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()), 
				Ingredient.of(Tags.Items.DYES_RED), 
				RecipeCategory.COMBAT, 
				SlashBladeAddonBuiltInRegistry.KATANA.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get())), 
		"slashblade_addon", new ResourceLocation("slashblade_addon", "katana"))
		.build(consumer, new ResourceLocation("slashblade_addon", "katana"));
		
		whenModLoaded(SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()), 
				Ingredient.of(Tags.Items.DYES_YELLOW), 
				RecipeCategory.COMBAT, 
				SlashBladeAddonBuiltInRegistry.TACHI.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get())), 
		"slashblade_addon", new ResourceLocation("slashblade_addon", "tachi"))
		.build(consumer, new ResourceLocation("slashblade_addon", "tachi"));
		whenModLoaded(
				SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.NIHILUL.location()).pattern("SNS")
				.pattern("DBD").pattern("SYS").define('S', SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()) )
				.define('Y',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeBuiltInRegistry.YAMATO.location()).build()))
				.define('N', Items.NETHER_STAR).define('D', Items.DIAMOND_BLOCK)
				.define('B',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeAddonBuiltInRegistry.NIHILEX.location()).killCount(750).proudSoul(6000)
								.refineCount(3).build()))
				.unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)),
				"slashblade_addon", new ResourceLocation("slashblade_addon", "nihilul")
				)
		.build(consumer, new ResourceLocation("slashblade_addon", "nihilul"));
		
		whenModLoaded(
				SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.CRIMSONCHERRY.location()).pattern("DUD")
				.pattern("DED").pattern("DDD")
				.define('E',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeAddonBuiltInRegistry.NIHIL.location()).build()))
				.define('D', Items.DIAMOND_BLOCK)
				.define('U',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeAddonBuiltInRegistry.NIHILEX.location()).killCount(750).proudSoul(6000)
								.refineCount(3).build()))
				.unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)),
				"slashblade_addon", new ResourceLocation("slashblade_addon", "crimsoncherry")
				)
		.build(consumer, new ResourceLocation("slashblade_addon", "crimsoncherry"));
		
		whenModLoaded(
				SlashBladeShapedRecipeBuilder.shaped(TLSSlashBladeRegistry.NIHILBX.location())
				.pattern("DAD")
				.pattern("CSU")
				.pattern("DAD")
				.define('S', SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()) )
				.define('D', Items.DIAMOND_BLOCK)
				.define('A', ItemRegistry.SAKURA_SPHERE.get())
				.define('C',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeAddonBuiltInRegistry.CRIMSONCHERRY.location()).killCount(1500)
								.proudSoul(10000).refineCount(10).build()))
				.define('U',
						SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
								.name(SlashBladeAddonBuiltInRegistry.NIHILUL.location()).killCount(1500).proudSoul(10000)
								.refineCount(10).build()))
				.unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)),
				"slashblade_addon", new ResourceLocation("slashblade_addon", "nihilbx")
				)
		.build(consumer, new ResourceLocation("slashblade_addon", "nihilbx"));
	}

	private void vanillaSlashBladeRecipes(Consumer<FinishedRecipe> consumer) {
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_BASIC.get()), 
				Ingredient.of(SBItems.slashblade_wood), 
				Ingredient.of(TLSItemTags.BAMBOO), 
				RecipeCategory.COMBAT, 
				SlashBlade.prefix("slashblade_bamboo"))
		.unlocks(getHasName(SBItems.slashblade_wood), has(SBItems.slashblade_wood))
		.save(consumer, SlashBlade.prefix("slashblade_bamboo"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_BASIC.get()), 
				Ingredient.of(SBItems.slashblade_wood), 
				Ingredient.of(ItemRegistry.BLADE.get()), 
				RecipeCategory.COMBAT, 
				SlashBlade.prefix("slashblade_white"))
		.unlocks(getHasName(SBItems.slashblade_wood), has(SBItems.slashblade_wood))
		.save(consumer, SlashBlade.prefix("slashblade_white"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_BLADE.get()), 
				Ingredient.of(SBItems.slashblade_white), 
				 SlashBladeIngredient.of(SBItems.slashblade_silverbamboo,
                         RequestDefinition.Builder.newInstance().addSwordType(SwordType.BROKEN).build()), 
				RecipeCategory.COMBAT, 
				SlashBlade.prefix("slashblade"))
		.unlocks(getHasName(SBItems.slashblade_white), has(SBItems.slashblade_white))
		.save(consumer, SlashBlade.prefix("slashblade"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				SlashBladeIngredient.of(SBItems.slashblade_silverbamboo,
                         RequestDefinition.Builder.newInstance().addSwordType(SwordType.BROKEN).build()), 
				Ingredient.of(ItemRegistry.BLADE.get()), 
				RecipeCategory.COMBAT, 
				SlashBlade.prefix("slashblade"))
		.unlocks(getHasName(SBItems.slashblade_white), has(SBItems.slashblade_white))
		.save(consumer, TheLastSmith.prefix("slashblade_from_bamboolight"));
		
		SlashBladeSmithingRecipeBuilder.smithing(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()), 
				Ingredient.of(ItemRegistry.SAKURA.get()), 
				RecipeCategory.COMBAT, 
				SlashBladeBuiltInRegistry.RUBY.location())
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get()))
		.save(consumer, SlashBladeBuiltInRegistry.RUBY.location());
	}

	private void researchRecipes(Consumer<FinishedRecipe> consumer) {
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(SBItems.slashblade_wood), 
				ItemRegistry.SCROLL_BASIC.get()
				)	
		.unlocks(getHasName(SBItems.slashblade_wood), has(SBItems.slashblade_wood))
		.save(consumer, TheLastSmith.prefix("research/scroll_basic"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(SBItems.slashblade_bamboo), 
				ItemRegistry.SCROLL_WOOD_BASIC.get()
				)	
		.unlocks(getHasName(SBItems.slashblade_bamboo), has(SBItems.slashblade_bamboo))
		.save(consumer, TheLastSmith.prefix("research/scroll_wood_basic"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(SBItems.slashblade_white), 
				ItemRegistry.SCROLL_BLADE.get()
				)	
		.unlocks(getHasName(SBItems.slashblade_white), has(SBItems.slashblade_white))
		.save(consumer, TheLastSmith.prefix("research/scroll_blade"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                        .build()),
				ItemRegistry.SCROLL_KATANA.get()
				)	
		.unlocks(getHasName(SBItems.slashblade), has(SBItems.slashblade))
		.save(consumer, TheLastSmith.prefix("research/scroll_katana"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SAKURA_FULL.get()), 
				ItemRegistry.SCROLL_SAKURA.get()
				)	
		.unlocks(getHasName(ItemRegistry.SAKURA_FULL.get()), has(ItemRegistry.SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_sakura"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						SlashBladeBuiltInRegistry.MURAMASA.location()), 
				ItemRegistry.SCROLL_MURAMASA.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLADE.get()), has(ItemRegistry.SCROLL_BLADE.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_muramasa_from_chizuki"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.MURAMASA_KAGURA.location()), 
				ItemRegistry.SCROLL_MURAMASA.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLADE.get()), has(ItemRegistry.SCROLL_BLADE.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_muramasa_from_kagura"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.OBORO_MURAMASA.location()), 
				ItemRegistry.SCROLL_CAUSALITY.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_MURAMASA.get()), has(ItemRegistry.SCROLL_MURAMASA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_oboro_muramasa"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SCROLL_GOD.get()), 
				Ingredient.of(ItemRegistry.SCROLL_CAUSALITY.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_SHARPNESS.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_CAUSALITY.get()), has(ItemRegistry.SCROLL_CAUSALITY.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_sharpness"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SCROLL_KATANA.get()), 
				Ingredient.of(ItemRegistry.SCROLL_BLADE.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_ODACHI.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_odachi"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SAKURA_SPHERE.get()), 
				Ingredient.of(ItemRegistry.SCROLL_SAKURA_BLADE.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_SAKURA_FULL.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_BLADE.get()), has(ItemRegistry.SCROLL_SAKURA_BLADE.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_sakura_full"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.NAGASADA.location()), 
				ItemRegistry.SCROLL_SAKURA_BLADE.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_sakura_blade"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.AMAGUMO_KAZE.location()), 
				ItemRegistry.SCROLL_TENGU_VOL1.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_tengu_1"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.AMAGUMO_KUMO.location()), 
				ItemRegistry.SCROLL_TENGU_VOL2.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_tengu_2"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SCROLL_TENGU_VOL2.get()),  
				Ingredient.of(ItemRegistry.SCROLL_TENGU_VOL1.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_TENGU_FULL.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_TENGU_VOL2.get()), has(ItemRegistry.SCROLL_TENGU_VOL2.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_last_1"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SCROLL_TENGU_VOL1.get()),  
				Ingredient.of(ItemRegistry.SCROLL_TENGU_VOL2.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_TENGU_FULL.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_TENGU_VOL1.get()), has(ItemRegistry.SCROLL_TENGU_VOL1.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_last_2"));
		
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(ItemRegistry.SCROLL_STAR.get()),  
				Ingredient.of(ItemRegistry.SCROLL_HEIL.get()), 
				Ingredient.of(Tags.Items.DYES_BLACK),  
				RecipeCategory.MISC, 
				ItemRegistry.SCROLL_BEWITCHED.get().getDefaultInstance()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_STAR.get()), has(ItemRegistry.SCROLL_STAR.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_bewitched_inferno"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.EXORCISM_SAKURA.location()), 
				ItemRegistry.SCROLL_EXORCISM.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_exorcism_sakura"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.EXORCISM_GINKGO.location()), 
				ItemRegistry.SCROLL_EXORCISM.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_exorcism_ginkgo"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.EXORCISM_YUKI.location()), 
				ItemRegistry.SCROLL_EXORCISM.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA.get()), has(ItemRegistry.SCROLL_SAKURA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_exorcism_yuki"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.EVIL_SHURA.location()), 
				ItemRegistry.SCROLL_SHURA.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_EXORCISM.get()), has(ItemRegistry.SCROLL_EXORCISM.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_shura"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.ROUKANKEN_NETHER.location()), 
				ItemRegistry.SCROLL_HEIL.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_inferno_roukanken"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.HAKUROUKEN_NETHER.location()), 
				ItemRegistry.SCROLL_HEIL.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_inferno_hakurouken"));
		
		whenModLoaded(TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						SlashBladeAddonBuiltInRegistry.NIHILBX.location()), 
				ItemRegistry.SCROLL_HEIL.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get())), 
		"slashblade_addon", "research/scroll_inferno_nihilbx")
		.build(consumer, TheLastSmith.MODID, "research/scroll_inferno_nihilbx")
		;
		
		TLSResearchRecipeBuilder.researching(
				CompoundIngredient.of(
						SlashBladeIngredient.of(TLSSlashBladeRegistry.GOLDENBAMBOO.location())
						),
				ItemRegistry.SCROLL_GOD.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_NAMED.get()), has(ItemRegistry.SCROLL_NAMED.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_god"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.BAMBOO_HERMIT.location()), 
				ItemRegistry.SCROLL_WOOD_KIWAMI.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_WOOD_BASIC.get()), has(ItemRegistry.SCROLL_WOOD_BASIC.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_wood_kiwami"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.FUSHIGIRI.location()), 
				ItemRegistry.SCROLL_MORTAL.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLOOD.get()), has(ItemRegistry.SCROLL_BLOOD.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_mortal"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.SILVERBAMBOO_BLOOD.location()), 
				ItemRegistry.SCROLL_BLOOD.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_WOOD_BASIC.get()), has(ItemRegistry.SCROLL_WOOD_BASIC.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_blood"));
		
		TLSResearchRecipeBuilder.researching(
				SlashBladeIngredient.of(
						TLSSlashBladeRegistry.YAMATO.location()), 
				ItemRegistry.SCROLL_YAMATO.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_SAKURA_FULL.get()), has(ItemRegistry.SCROLL_SAKURA_FULL.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_yamato"));
		
		TLSResearchRecipeBuilder.researching(
				CompoundIngredient.of(
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.SANGE.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.YASHA_TRUE.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.OROTIAGITO.location())
						)
				, 
				ItemRegistry.SCROLL_STAR.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_star"));
		
		whenModLoaded(TLSResearchRecipeBuilder.researching(
				CompoundIngredient.of(
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.SNOW_CROW.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.MURAKUMO.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.DARK_RAVEN.location())
						)
				, 
				ItemRegistry.SCROLL_STAR.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get())), 
		"slashblade_addon", "research/scroll_star_sjap")
		.build(consumer, TheLastSmith.MODID, "research/scroll_star_sjap");
		
		TLSResearchRecipeBuilder.researching(
				CompoundIngredient.of(
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.DOUTANUKI.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RUBY.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.TUKUMO.location()),
						
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_WOODEN.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_STONE.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_IRON.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_GOLDEN.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_DIAMOND.location()),
						SlashBladeIngredient.of(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
						)
				, 
				ItemRegistry.SCROLL_NAMED.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLADE.get()), has(ItemRegistry.SCROLL_BLADE.get()))
		.save(consumer, TheLastSmith.prefix("research/scroll_named"));
		
		whenModLoaded(TLSResearchRecipeBuilder.researching(
				CompoundIngredient.of(
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.BLUE.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.WANDERER.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.TBOEN.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.KATANA.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.TACHI.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.FROST_WOLF.location()),
						SlashBladeIngredient.of(SlashBladeAddonBuiltInRegistry.FROSTY_CHERRY.location())
						)
				, 
				ItemRegistry.SCROLL_NAMED.get()
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_BLADE.get()), has(ItemRegistry.SCROLL_BLADE.get())), 
		"slashblade_addon", "research/scroll_named_sjap")
		.build(consumer, TheLastSmith.MODID, "research/scroll_named_sjap");
		
    	ItemRegistry.ITEMS.getEntries().forEach(ro->{
    		if(ro.get() instanceof ScrollItem && ro.get().getRarity(ro.get().getDefaultInstance()) != Rarity.EPIC)
    			researchCopy(ro, consumer);
    	});
	}
	
	private void researchCopy(RegistryObject<Item> research, Consumer<FinishedRecipe> consumer) {
		TLSResearchRecipeBuilder.researching(
				Ingredient.of(research.get())
				, 
				new ItemStack(research.get(), 2)
				)	
		.unlocks(getHasName(ItemRegistry.SCROLL_KATANA.get()), has(ItemRegistry.SCROLL_KATANA.get()))
		.save(consumer, TheLastSmith.prefix("research/")+research.getId().getPath()+"_copy");
	}

    public ConditionalRecipe.Builder whenModLoaded(TLSResearchRecipeBuilder recipe, String modid, String path) {
        return ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(consumer -> recipe.save(consumer, TheLastSmith.prefix(path)));
    }
    
    public ConditionalRecipe.Builder whenModLoaded(SlashBladeSmithingRecipeBuilder recipe, String modid, String path) {
        return ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(consumer -> recipe.save(consumer, TheLastSmith.prefix(path)));
    }
    
    public ConditionalRecipe.Builder whenModLoaded(SlashBladeSmithingRecipeBuilder recipe, String modid, ResourceLocation loc) {
        return ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(consumer -> recipe.save(consumer, loc));
    }
    
    public ConditionalRecipe.Builder whenModLoaded(SlashBladeShapedRecipeBuilder recipe, String modid, ResourceLocation loc) {
        return ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(consumer -> recipe.save(consumer, loc));
    }
}
