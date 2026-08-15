package cn.mmf.tls.data.advancements;

import java.util.List;
import java.util.function.Consumer;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.item.ItemRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.advancement.SlashBladeItemPredicate;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.registry.SlashBladeItems;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static cn.mmf.tls.data.builtin.TLSSlashBladeRegistry.*;

/**
 * 2026-06-14:09-05 Complete advancement tree DataGen.
 * Uses a shared CompletableFuture&lt;HolderLookup.Provider&gt; that includes
 * blade registries, enabling proper ItemStack icons with NBT data.
 */
public class TLSAdvancementsProvider implements AdvancementGenerator {

    private static final Logger log = LogManager.getLogger(TLSAdvancementsProvider.class);
    // 2026-06-14:09-10 Captured during generate() for use by icon helpers
    private Provider currentRegistries;

    @Override
    public void generate(@NotNull Provider registries, @NotNull Consumer<AdvancementHolder> saver,
                         @NotNull ExistingFileHelper fileHelper) {
        this.currentRegistries = registries;
        var root = simple(saver, fileHelper, null, "root",
                ItemRegistry.SCROLL_WOOD_BASIC.get(),
                tl("root.title"), tl("root.desc"), AdvancementType.TASK,
                true, false, false, bg("textures/advancements/backgrounds.png"),
                c("has_wood", item(rl("slashblade", "slashblade_wood"))));

        var rt = simple(saver, fileHelper, root, "research_table",
                ItemRegistry.RESEARCH_TABLE.get(),
                tl("research_table.title"), tl("research_table.desc"),
                AdvancementType.TASK, true, false, false, null,
                c("has_table", item(tls("research_table"))));

        var blade = simple(saver, fileHelper, root, "blade",
                ItemRegistry.BLADE.get(),
                tl("blade.title"), tl("blade.desc"),
                AdvancementType.TASK, true, false, false, null,
                c("has_scroll", item(tls("blade"))));

        var bladeSakura = simple(saver, fileHelper, blade, "blade_sakura",
                ItemRegistry.BLADE_SAKURA.get(),
                tl("blade_sakura.title"), tl("blade_sakura.desc"),
                AdvancementType.TASK, true, false, false, null,
                c("has_blade", item(tls("blade_sakura"))));

        var scrolls = simple(saver, fileHelper, rt, "scrolls",
                ItemRegistry.SCROLL_BASIC.get(),
                tl("scrolls.title"), tl("scrolls.desc"),
                AdvancementType.TASK, false, false, false, null,
                c("has_table", tagPred(tls("scroll"))));

        var ssf = simple(saver, fileHelper, scrolls, "scrolls_sakura_full",
                ItemRegistry.SCROLL_SAKURA_FULL.get(),
                tl("scrolls_sakura_full.title"), tl("scrolls_sakura_full.desc"),
                AdvancementType.TASK, false, false, false, null,
                c("has_scroll", item(tls("scroll_sakura_full"))));

        var sn = simple(saver, fileHelper, scrolls, "scroll_named",
                ItemRegistry.SCROLL_NAMED.get(),
                tl("scroll_named.title"), tl("scroll_named.desc"),
                AdvancementType.TASK, false, false, false, null,
                c("has_scroll", item(tls("scroll_named"))));

        // Named blade advancements (icons via getBlade with fallback)
        var nag = bladeAdv(saver, fileHelper, bladeSakura, "nagasada",
                NAGASADA, tl("nagasada.title"), tl("nagasada.desc"),
                AdvancementType.TASK, false, tls("nagasada"));

        var odachi = simple(saver, fileHelper, blade, "odachi",
                ItemRegistry.SCROLL_ODACHI.get(),
                tl("odachi.title"), tl("odachi.desc"),
                AdvancementType.TASK, true, false, false, null,
                c("has_scroll", item(tls("scroll_odachi"))));

        var sb = multi(saver, fileHelper, ssf, "scroll_bamboo",
                BAMBOO_HERMIT, tl("scroll_bamboo.title"), tl("scroll_bamboo.desc"),
                AdvancementType.GOAL, false, List.of(List.of("bamboo_hermit", "bloody_bamboo")),
                c("bamboo_hermit", bladePred(tls("bamboo_hermit"))),
                c("bloody_bamboo", bladePred(tls("silverbamboo_blood"))));

        var sm = simple(saver, fileHelper, sn, "scroll_muramasa",
                ItemRegistry.SCROLL_MURAMASA.get(),
                tl("scroll_muramasa.title"), tl("scroll_muramasa.desc"),
                AdvancementType.TASK, false, false, false, null,
                c("has_scroll", item(tls("scroll_muramasa"))));

        var ex = multi(saver, fileHelper, nag, "exorcism_sakura",
                EXORCISM_SAKURA, tl("exorcism_sakura.title"), tl("exorcism_sakura.desc"),
                AdvancementType.TASK, false, List.of(List.of("sakura", "ginkgo", "yuki")),
                c("sakura", bladePred(tls("exorcism_sakura"))),
                c("ginkgo", bladePred(tls("exorcism_ginkgo"))),
                c("yuki", bladePred(tls("exorcism_yuki"))));

        var rh = multi(saver, fileHelper, ex, "roukan_hakurou",
                ROUKANKEN, tl("roukan_hakurou.title"), tl("roukan_hakurou.desc"),
                AdvancementType.GOAL, false, List.of(List.of("roukanken", "hakurouken")),
                c("roukanken", bladePred(tls("roukanken"))),
                c("hakurouken", bladePred(tls("hakurouken"))));

        var inf = bladeAdv(saver, fileHelper, rh, "inferno",
                ROUKANKEN_NETHER, tl("inferno.title"), tl("inferno.desc"),
                AdvancementType.CHALLENGE, false, tls("roukanken_nether"));

        bladeAdv(saver, fileHelper, inf, "bewitched",
                HAKUROUKEN_NETHER, tl("bewitched.title"), tl("bewitched.desc"),
                AdvancementType.CHALLENGE, false, tls("hakurouken_nether"));

        var met = bladeAdv(saver, fileHelper, sn, "meteorn",
                AMAGUMO_MUNIN, tl("meteorn.title"), tl("meteorn.desc"),
                AdvancementType.CHALLENGE, false, tls("amagumo_munin"));

        bladeAdv(saver, fileHelper, met, "yamato",
                YAMATO, tl("yamato.title"), tl("yamato.desc"),
                AdvancementType.GOAL, false, tls("yamato_neo"));

        bladeAdv(saver, fileHelper, nag, "munin",
                AMAGUMO_MUNIN, tl("munin.title"), tl("munin.desc"),
                AdvancementType.CHALLENGE, true, tls("amagumo_munin"));

        bladeAdv(saver, fileHelper, odachi, "fushigiri",
                FUSHIGIRI, tl("fushigiri.title"), tl("fushigiri.desc"),
                AdvancementType.GOAL, false, tls("fushigiri"));

        var om = bladeAdv(saver, fileHelper, sm, "oboro_muramasa",
                OBORO_MURAMASA, tl("oboro_muramasa.title"), tl("oboro_muramasa.desc"),
                AdvancementType.GOAL, false, tls("oboro_muramasa"));

        bladeAdv(saver, fileHelper, om, "sharpness",
                AMAGUMO_MUNIN, tl("sharpness.title"), tl("sharpness.desc"),
                AdvancementType.CHALLENGE, false, tls("amagumo_kaze"));

        bladeAdv(saver, fileHelper, ex, "shura",
                EVIL_SHURA, tl("shura.title"), tl("shura.desc"),
                AdvancementType.GOAL, false, tls("evil_shura"));

        bladeAdv(saver, fileHelper, sb, "sagequoia",
                SAGEQUOIA, tl("sagequoia.title"), tl("sagequoia.desc"),
                AdvancementType.GOAL, true, tls("sagequoia"));
    }

    // ─── Helpers ───

    private static ResourceLocation tls(String p) { return TheLastSmith.prefix(p); }
    private static ResourceLocation rl(String ns, String p) { return ResourceLocation.fromNamespaceAndPath(ns, p); }
    private static Component tl(String k) { return Component.translatable("advancement.last_smith." + k); }
    private static ResourceLocation bg(String p) { return tls(p); }

    /**
     * 2026-08-15:16-45: Item icon overload -> wraps into a bare ItemStack.
     * 2026-08-15:16-45: Item 图标重载 -> 包装为无数据组件的 ItemStack。
     */
    private AdvancementHolder simple(Consumer<AdvancementHolder> s, ExistingFileHelper fh,
            AdvancementHolder parent, String id, ItemLike icon,
            Component title, Component desc, AdvancementType type,
            boolean toast, boolean announce, boolean hidden, ResourceLocation bg,
            CriterionEntry c) {
        return simple(s, fh, parent, id, new ItemStack(icon), title, desc, type,
                toast, announce, hidden, bg, c);
    }

    /**
     * Single-criterion advancement with simple item trigger
     * 2026-08-15:16-45: ItemStack overload keeps NBT/data-components (blade_state) in the JSON.
     * 2026-08-15:16-45: ItemStack 重载保留 NBT/数据组件（blade_state），使生成的图标 JSON 正确。
     */
    private AdvancementHolder simple(Consumer<AdvancementHolder> s, ExistingFileHelper fh,
            AdvancementHolder parent, String id, ItemStack icon,
            Component title, Component desc, AdvancementType type,
            boolean toast, boolean announce, boolean hidden, ResourceLocation bg,
            CriterionEntry c) {
        var b = Advancement.Builder.recipeAdvancement();
        if (parent != null) b.parent(parent);
        b.display(icon, title, desc, bg, type, toast, announce, hidden);
        b.addCriterion(c.name, c.trigger);
        b.requirements(AdvancementRequirements.Strategy.OR);
        return b.save(s, tls(id), fh);
    }

    /** Blade advancement with icon from registry (fallback to slashblade item) */
    private AdvancementHolder bladeAdv(Consumer<AdvancementHolder> s, ExistingFileHelper fh,
              AdvancementHolder parent, String id,
            ResourceKey<SlashBladeDefinition> key, Component title, Component desc,
            AdvancementType type, boolean hidden, ResourceLocation bladeName) {
        return simple(s, fh, parent, id, getIcon(key), title, desc, type,
                true, false, hidden, null,
                c("has_blade", bladePred(bladeName)));
    }

    /** Multi-criterion advancement with requirements list */
    private AdvancementHolder multi(Consumer<AdvancementHolder> s, ExistingFileHelper fh,
              AdvancementHolder parent, String id,
            ResourceKey<SlashBladeDefinition> key, Component title, Component desc,
            AdvancementType type, boolean hidden, List<List<String>> reqs,
            CriterionEntry... criteria) {
        var b = Advancement.Builder.recipeAdvancement().parent(parent);
        b.display(getIcon(key), title, desc, null, type, true, false, hidden);
        for (var c : criteria) b.addCriterion(c.name, c.trigger);
        b.requirements(new AdvancementRequirements(reqs));
        return b.save(s, tls(id), fh);
    }

    // ─── Item predicates ───

    private ItemPredicate item(ResourceLocation id) {
        var it = BuiltInRegistries.ITEM.get(id);
        return ItemPredicate.Builder.item().of(it).build();
    }

    private ItemPredicate tagPred(ResourceLocation tag) {
        return ItemPredicate.Builder.item().of(TagKey.create(Registries.ITEM, tag)).build();
    }

    @SuppressWarnings("unchecked")
    private ItemPredicate bladePred(ResourceLocation name) {
        var type = (ItemSubPredicate.Type<SlashBladeItemPredicate>)
                BuiltInRegistries.ITEM_SUB_PREDICATE_TYPE.get(SlashBlade.prefix("slashblade"));
        var req = RequestDefinition.Builder.newInstance().name(name).build();
        if (type != null) {
            return ItemPredicate.Builder.item().of(SlashBladeItems.SLASHBLADE.get())
                    .withSubPredicate(type, new SlashBladeItemPredicate(req)).build();
        }
        // Fallback: match any slashblade item
        return ItemPredicate.Builder.item().of(SlashBladeItems.SLASHBLADE.get()).build();
    }

    // ─── Icons ───

    /**
     * 2026-06-14:09-10: Uses generated registries to get proper blade ItemStack
     * with NBT data. Falls back to default SlashBlade on error.
     * 2026-08-15:16-45: Return the full ItemStack so the blade_state component is kept.
     * 2026-08-15:16-45: 返回完整 ItemStack，保留 blade_state 数据组件。
     */
    private ItemStack getIcon(ResourceKey<SlashBladeDefinition> key) {
        try {
            return currentRegistries.lookupOrThrow(SlashBladeDefinition.REGISTRY_KEY)
                    .getOrThrow(key).value()
                    .getBlade(SlashBladeItems.SLASHBLADE.get(), currentRegistries);
        } catch (Exception e) {
            log.error(e);
            return new ItemStack(SlashBladeItems.SLASHBLADE.get());
        }
    }

    // ─── Criterion helper ───

    private record CriterionEntry(String name,
            net.minecraft.advancements.Criterion<?> trigger) {}

    private static CriterionEntry c(String name, ItemPredicate pred) {
        return new CriterionEntry(name, InventoryChangeTrigger.TriggerInstance.hasItems(pred));
    }
}
