package cn.mmf.tls.data.tag;

import java.util.concurrent.CompletableFuture;

import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.item.ScrollItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TLSItemTagsProvider extends ItemTagsProvider {

    public TLSItemTagsProvider(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider,
            ExistingFileHelper existingFileHelper) {
        super(pGenerator, lookupProvider, provider, "umapyoi", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        tag(TLSItemTags.BAMBOO).add(Items.BAMBOO);
        tag(TLSItemTags.LEAVES_CHERRY).add(Items.CHERRY_LEAVES).addOptional(ResourceLocation.fromNamespaceAndPath("sakura", "sakuraleaves"));

        ItemRegistry.ITEMS.getEntries().forEach(ro->{
            if(ro.get() instanceof ScrollItem)
                tag(TLSItemTags.SCROLL).add(ro.get());
            var rarity = ro.get().getDefaultInstance().getRarity();
            if(rarity == Rarity.RARE)
                tag(TLSItemTags.SCROLL_RARE).add(ro.get());
            if(rarity == Rarity.EPIC)
                tag(TLSItemTags.SCROLL_EPIC).add(ro.get());
        });
        tag(TLSItemTags.PROUD_SOULS).add(ItemRegistry.SAKURA.get()).add(ItemRegistry.SAKURA_FULL.get());
        tag(TLSItemTags.RESEARCH_CONSUMABLE).add(ItemRegistry.SAKURA_FULL.get()).addTag(TLSItemTags.SCROLL);

    }

}
