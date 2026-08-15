package cn.mmf.tls.data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.data.advancements.TLSAdvancementsProvider;
import cn.mmf.tls.data.builtin.TLSSlashBladeRegistry;
import cn.mmf.tls.data.loot.TLSBlockLoot;
import cn.mmf.tls.data.tag.TLSBlockTagsProvider;
import cn.mmf.tls.data.tag.TLSItemTagsProvider;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = TheLastSmith.MODID)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        RegistrySetBuilder bladeBuilder = new RegistrySetBuilder().add(SlashBladeDefinition.REGISTRY_KEY,
                TLSSlashBladeRegistry::registerAll);

        dataGenerator.addProvider(event.includeClient(), new TLSLangProvider(packOutput));
        dataGenerator.addProvider(event.includeClient(), new TLSBlockStateProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(event.includeClient(), new TLSItemModelProvider(packOutput, existingFileHelper));

        // 2026-08-15:16-45 Keep the provider so its combined lookup (vanilla + last_smith named_blades) can be reused.
        // 2026-08-15:16-45 保留该 provider 实例，以便复用其合并 lookup（原版注册表 + last_smith named_blades 注册表）。
        DatapackBuiltinEntriesProvider bladeProvider =
                new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, bladeBuilder, Set.of(TheLastSmith.MODID)) {
                    @Override
                    public @NotNull String getName() {
                        return "TLS SlashBlade Definition Registry";
                    }
                };
        dataGenerator.addProvider(event.includeServer(), bladeProvider);
        var blockTagsProvider = new TLSBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);

        dataGenerator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(TLSBlockLoot::new, LootContextParamSets.BLOCK)),
                lookupProvider));

        dataGenerator.addProvider(event.includeServer(), blockTagsProvider);
        dataGenerator.addProvider(event.includeServer(), new TLSItemTagsProvider(packOutput, lookupProvider,
                blockTagsProvider.contentsGetter(), existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new TLSRecipeProvider(packOutput, lookupProvider));
        // 2026-08-15:16-45 Use the combined provider so getIcon() can resolve slashblade:named_blades.
        // 2026-08-15:16-45 使用合并后的 provider，使 getIcon() 能解析 slashblade:named_blades 注册表。
        dataGenerator.addProvider(event.includeServer(), new AdvancementProvider(
                packOutput, bladeProvider.getRegistryProvider(), existingFileHelper,
                List.of(new TLSAdvancementsProvider())));
    }
}
