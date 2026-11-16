package cn.mmf.tls.data.tag;

import java.util.concurrent.CompletableFuture;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.enchantments.EnchantmentsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * 2026-10-06:16-30 Added so the two custom enchantments appear in the enchanting table.
 * Vanilla's {@code minecraft:in_enchanting_table} tag includes {@code #minecraft:non_treasure},
 * so adding them to non_treasure is enough. Mirrors the old canApplyAtEnchantingTable behaviour.
 * 2026-10-06:16-30 新增该 Provider，使两个自定义附魔可出现在附魔台中。
 * 原版 {@code minecraft:in_enchanting_table} 标签包含 {@code #minecraft:non_treasure}，
 * 因此将其加入 non_treasure 即可，对应旧版的 canApplyAtEnchantingTable 行为。
 */
public class TLSEnchantmentTagsProvider extends EnchantmentTagsProvider {

    public TLSEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheLastSmith.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.NON_TREASURE).add(EnchantmentsRegistry.SPIRIT_SLASH, EnchantmentsRegistry.SPIRIT_ABSORPTION);
    }
}
