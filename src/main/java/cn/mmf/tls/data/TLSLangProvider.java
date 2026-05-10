package cn.mmf.tls.data;

import cn.mcmod_mmf.mmlib.data.AbstractLangProvider;
import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.block.BlockRegistry;
import cn.mmf.tls.data.builtin.TLSSlashBladeRegistry;
import cn.mmf.tls.enchantments.EnchantmentsRegistry;
import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.se.TLSSpecialEffectRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class TLSLangProvider extends AbstractLangProvider {

	public TLSLangProvider(PackOutput gen) {
		super(gen, TheLastSmith.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.the_last_smith", "The Last Smith");
		add("last_smith.jei.research", "Researching");
		add("container.last_smith.research", "Researching");
		
		add("last_smith.jei.cauldron", "Cauldron Quenching");
		add("last_smith.jei.cauldron_need_water", "Need some water in the cauldron.");

		add("emi.category.last_smith.research", "Researching");
		add("emi.category.last_smith.cauldron", "Cauldron Quenching");


		add("guide.smith_guide.title", "The Way of Bewitched Blade");
		add("guide.smith_guide.landing_text", "This book records many ancient stories...");
		add("guide.smith_guide.subtitle", "The Bewitched Power and The Extreme Sharpness.");

		add("container.last_smith.research.error_tooltip", "Can't do any research this way");
		add("container.last_smith.research.missing_blade_tooltip", "Add the blade or research");
		add("container.last_smith.research.missing_paper_tooltip", "Add papers or research");
		add("container.last_smith.research.missing_ink_tooltip", "Add ink");
		
		addAdvTitle("last_smith.root", "Swordsmith's Story");
		addAdvDesc("last_smith.root", "Start the story by crafting Anonymity -Wood-.");

		addAdvTitle("last_smith.research_table", "Research the Slashblade");
		addAdvDesc("last_smith.research_table", "Craft a research table for researching.");

		addAdvTitle("last_smith.scrolls", "Historical Scroll");
		addAdvDesc("last_smith.scrolls", "Finish a research and get the scroll.");

		addAdvTitle("last_smith.blade", "First Iron Blade");
		addAdvDesc("last_smith.blade", "Finish a Iron Blade.");

		addAdvTitle("last_smith.blade_sakura", "Sprite Sakura Blade");
		addAdvDesc("last_smith.blade_sakura", "Finish a Sakura Blade Blade.");

		addAdvTitle("last_smith.scrolls_sakura_full", "The Full Research of Sprite Sakura");
		addAdvDesc("last_smith.scrolls_sakura_full", "Got the scroll that contains all things about sprite sakura.");

		addAdvTitle("last_smith.scroll_named", "Stories of Named Blades");
		addAdvDesc("last_smith.scroll_named", "Find named blades and their stories.");

		addAdvTitle("last_smith.scroll_muramasa", "Muramasa");
		addAdvDesc("last_smith.scroll_muramasa", "Find the stories of muramasa.");

		addAdvTitle("last_smith.oboro_muramasa", "The Hazy Causality");
		addAdvDesc("last_smith.oboro_muramasa", "Get the Oboro Muramasa.");

		addAdvTitle("last_smith.meteorn", "The Meteorn");
		addAdvDesc("last_smith.meteorn", "Find the stories of The Meteorn.");

		addAdvTitle("last_smith.yamato", "The Yamato");
		addAdvDesc("last_smith.yamato", "Get the awaked yamato.");

		addAdvTitle("last_smith.sharpness", "Extreme Sharpness");
		addAdvDesc("last_smith.sharpness", "Find the Story of The Extreme Sharpness.");

		addAdvTitle("last_smith.scroll_bamboo", "Bamboo Light");
		addAdvDesc("last_smith.scroll_bamboo", "Get the top of bamboo blades.");

		addAdvTitle("last_smith.nagasada", "Nagasada");
		addAdvDesc("last_smith.nagasada", "Get the Nagasada.");

		addAdvTitle("last_smith.odachi", "Odachi");
		addAdvDesc("last_smith.odachi", "Craft a odachi.");

		addAdvTitle("last_smith.fushigiri", "Fushigiri");
		addAdvDesc("last_smith.fushigiri", "Find the mortal blade Fushigiri.");

		addAdvTitle("last_smith.exorcism_sakura", "The Exorcism Blade");
		addAdvDesc("last_smith.exorcism_sakura", "Find the exorcism blades.");

		addAdvTitle("last_smith.shura", "Shura");
		addAdvDesc("last_smith.shura", "Get the shura's blade.");

		addAdvTitle("last_smith.roukan_hakurou", "Twin Spirit Blades");
		addAdvDesc("last_smith.roukan_hakurou", "Find the Roukanken or the Hakurouken.");

		addAdvTitle("last_smith.inferno", "The Inferno");
		addAdvDesc("last_smith.inferno", "Get the inferno blades.");

		addAdvTitle("last_smith.bewitched", "The Bewitched Power");
		addAdvDesc("last_smith.bewitched", "Find the stories of the bewitched power.");

		addAdvTitle("last_smith.munin", "The Last Smith");
		addAdvDesc("last_smith.munin", "Find the Amagumo -Munin-.");

		addAdvTitle("last_smith.sagequoia", "The Ultimate Wood");
		addAdvDesc("last_smith.sagequoia", "Find the Sagequoia.");

		addBlock(BlockRegistry.RESEARCH_TABLE, "Researching Table");
		
		addSlashBlade(TLSSlashBladeRegistry.AMAGUMO_KAZE, "Amagumo -Kaze-");
		addSlashBlade(TLSSlashBladeRegistry.AMAGUMO_KUMO, "Amagumo -Kumo-");
		addSlashBlade(TLSSlashBladeRegistry.AMAGUMO_MUNIN, "Amagumo -Munin-");
		addSlashBlade(TLSSlashBladeRegistry.BUNSHI, "-BunShi-");
		addSlashBlade(TLSSlashBladeRegistry.SAKURAGIRI, "-Sakura Giri-");
		addSlashBlade(TLSSlashBladeRegistry.YAMATO, "-Yamato-");
		addSlashBlade(TLSSlashBladeRegistry.ROUKANKEN, "-Roukanken-");
		addSlashBlade(TLSSlashBladeRegistry.ROUKANKEN_NETHER, "Inferno -Roukanken-");
		addSlashBlade(TLSSlashBladeRegistry.HAKUROUKEN, "-Hakuroken-");
		addSlashBlade(TLSSlashBladeRegistry.HAKUROUKEN_NETHER, "Inferno -Hakuroken-");
		
		addSlashBlade(TLSSlashBladeRegistry.NAMELESS_ODACHI, "Odachi --");
		addSlashBlade(TLSSlashBladeRegistry.MURAMASA_KAGURA, "-Kagura- Muramasa");
		
		addSlashBlade(TLSSlashBladeRegistry.EXORCISM_SAKURA, "Exorcism -Sakura-");
		addSlashBlade(TLSSlashBladeRegistry.EXORCISM_GINKGO, "Exorcism -Ginkgo-");
		addSlashBlade(TLSSlashBladeRegistry.EXORCISM_YUKI, "Exorcism -Yukikage-");
		addSlashBlade(TLSSlashBladeRegistry.EVIL_KATAWARE, "Evil -Kataware-");
		addSlashBlade(TLSSlashBladeRegistry.EVIL_SHURA, "Evil -Shura-");
		
		addSlashBlade(TLSSlashBladeRegistry.NAGASADA, "Spirit -Nagasada-");
		
		addSlashBlade(TLSSlashBladeRegistry.KUSABIMARU, "Named -Kusabimaru-");
		addSlashBlade(TLSSlashBladeRegistry.FUSHIGIRI, "Fushigiri -Hairui-");
		
		addSlashBlade(TLSSlashBladeRegistry.SAGEQUOIA, "Named -Sagequoia-");
		
		addSlashBlade(TLSSlashBladeRegistry.OBORO_MURAMASA, "-Oboro Muramasa-");

		addSlashBlade(TLSSlashBladeRegistry.BAMBOO_TOP, "Top -Bamboo Light-");
		addSlashBlade(TLSSlashBladeRegistry.BAMBOO_HERMIT, "The Hermit's Bamboo Light");
		addSlashBlade(TLSSlashBladeRegistry.GOLDENBAMBOO, "Noted -Golden Bamboo Light-");
		addSlashBlade(TLSSlashBladeRegistry.SILVERBAMBOO_TOP, "Top -Silver Bamboo Light-");
		addSlashBlade(TLSSlashBladeRegistry.SILVERBAMBOO_BLOOD, "-Bloody Bamboo Light-");
		
		addSlashBlade(TLSSlashBladeRegistry.SWEAPON, "SWeapon--");
		addSlashBlade(TLSSlashBladeRegistry.SLASHBLADE, "SlashBlade--");
		addSlashBlade(TLSSlashBladeRegistry.EIEVUI, "-Eievui-");
		
		addSlashBlade(TLSSlashBladeRegistry.UMA_TACHYON, "Tachyon -Lightless-");
		addSlashBlade(TLSSlashBladeRegistry.UMA_DIGITAL, "Digital -Superstan-");
		addSlashBlade(TLSSlashBladeRegistry.UMA_RICE_SHOWER, "Rice -Chaser-");
		addSlashBlade(TLSSlashBladeRegistry.UMA_CAFE, "Cafe -Black Skyscraper-");

		add("slash_art.last_smith.transmigration_slash", "Transmigration Slash");
		add("slash_art.last_smith.fushigiri", "Mortal Draw");
		add("slash_art.last_smith.iai_cross", "Ashina Cross");
		add("slash_art.last_smith.sakura_blistering_swords", "Sakura -Blistering-");
		
		addEnchantment(EnchantmentsRegistry.SPIRIT_SLASH, "Spirit Slash");
		
		addEnchantment(EnchantmentsRegistry.SPIRIT_ABSORPTION, "Spirit Absorption");

		addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId(), "Spirit Power");
		addSpecialEffect(TLSSpecialEffectRegistry.BEWITCHED_POWER.getId(), "Bewitched Power");
		addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId(), "Spirit Sharpness");
		addSpecialEffect(TLSSpecialEffectRegistry.EXTREME_SHARPNESS.getId(), "Extreme Sharpness");
		
		addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId(), "Exorcism");
		addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SMITE.getId(), "Nirvana");
		addSpecialEffect(TLSSpecialEffectRegistry.FUSHIGIRI_SE.getId(), "Fushigiri");
		
		addItem(ItemRegistry.SAKURA, "Spirit Sakura Leaf");
        addItem(ItemRegistry.SAKURA_FULL, "Spirit Sakura");
        addItem(ItemRegistry.SAKURA_STEEL_INGOT, "Spirit Sakura Steel");
        addItem(ItemRegistry.SAKURA_SPHERE, "Sakura Spirit Sphere");
        addItem(ItemRegistry.YAKIBATSUCHI, "Yakibatsuchi");
        
		addItem(ItemRegistry.BLADE, "Iron Blade");
        addItem(ItemRegistry.BLADE_UNFINISHED_1, "Unfinished Blade");
        addItem(ItemRegistry.BLADE_UNFINISHED_2, "Unfinished Blade");
        addItem(ItemRegistry.BLADE_UNFINISHED_3, "Unfinished Blade");
        addItem(ItemRegistry.BLADE_UNFINISHED_4, "Unfinished Blade");
        
		addItem(ItemRegistry.BLADE_SAKURA, "Sprite Sakura Blade");
        addItem(ItemRegistry.BLADE_SAKURA_UNFINISHED_1, "Unfinished Sprite Blade");
        addItem(ItemRegistry.BLADE_SAKURA_UNFINISHED_2, "Unfinished Sprite Blade");
        addItem(ItemRegistry.BLADE_SAKURA_UNFINISHED_3, "Unfinished Sprite Blade");
        addItem(ItemRegistry.BLADE_SAKURA_UNFINISHED_4, "Unfinished Sprite Blade");
		
        addItem(ItemRegistry.SCROLL_BASIC, "Basic Note");
        addItem(ItemRegistry.SCROLL_WOOD_BASIC, "Note of Bamboo Blade");
        addItem(ItemRegistry.SCROLL_BLADE, "Note of Iron Blade");
        addItem(ItemRegistry.SCROLL_WOOD_KIWAMI, "Story of The Ultimate Wood");
        addItem(ItemRegistry.SCROLL_NAMED, "Stories of Named Blades");
        addItem(ItemRegistry.SCROLL_SAKURA, "Note of Sprite Sakura");
        addItem(ItemRegistry.SCROLL_SAKURA_BLADE, "Note of Sprite Blade");
        addItem(ItemRegistry.SCROLL_SAKURA_FULL, "The Full Research of Sprite Sakura");
        addItem(ItemRegistry.SCROLL_EXORCISM, "Story of The Exorcism Blades");
        addItem(ItemRegistry.SCROLL_KATANA, "Note of Katana");
        addItem(ItemRegistry.SCROLL_ODACHI, "Notes of Odachi");
        addItem(ItemRegistry.SCROLL_SHURA, "Story of The Shura");
        addItem(ItemRegistry.SCROLL_MURAMASA, "Story of The Muramasa");
        addItem(ItemRegistry.SCROLL_STAR, "Story of The Meteorn");
        addItem(ItemRegistry.SCROLL_GOD, "Story of The God's Blade");
        addItem(ItemRegistry.SCROLL_BLOOD, "Story of The Blood Blade");
        addItem(ItemRegistry.SCROLL_YAMATO, "Story of The Yamato");
        addItem(ItemRegistry.SCROLL_CAUSALITY, "Story of The Hazy Causality");
        addItem(ItemRegistry.SCROLL_MORTAL, "Story of The Mortal Blade");
        addItem(ItemRegistry.SCROLL_SHARPNESS, "Story of The Extreme Sharpness");
        addItem(ItemRegistry.SCROLL_BEWITCHED, "Story of The Bewitched Power");
        addItem(ItemRegistry.SCROLL_HEIL, "Story of The Inferno Blade");
        addItem(ItemRegistry.SCROLL_TENGU_VOL1, "History of Tengu Vol.1");
        addItem(ItemRegistry.SCROLL_TENGU_VOL2, "History of Tengu Vol.2");
        addItem(ItemRegistry.SCROLL_TENGU_FULL, "The Story of The Last Smith");
	}
    private void addSlashBlade(ResourceKey<SlashBladeDefinition> data, String name) {
        this.addSlashBlade(data.location(), name);
    }

    private void addSlashBlade(ResourceLocation key, String name) {
        add(Util.makeDescriptionId("item", key), name);
    }
    
    private void addSpecialEffect(ResourceLocation key, String name) {
        add(Util.makeDescriptionId("se", key), name);
    }

}
