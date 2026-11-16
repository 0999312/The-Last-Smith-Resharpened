package cn.mmf.tls.data.tag;

import cn.mmf.tls.TheLastSmith;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class TLSItemTags {
    public static final TagKey<Item> BAMBOO = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild("c", "bamboo")));
    public static final TagKey<Item> LEAVES_CHERRY = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild("c", "leaves/cherry")));
    
    public static final TagKey<Item> RESEARCH_CONSUMABLE = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(TheLastSmith.MODID, "research/consumable")));
    
    public static final TagKey<Item> SCROLL = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(TheLastSmith.MODID, "scroll")));
    public static final TagKey<Item> SCROLL_RARE = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(TheLastSmith.MODID, "scroll/rare")));
    public static final TagKey<Item> SCROLL_EPIC = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(TheLastSmith.MODID, "scroll/epic")));
    
    public static final TagKey<Item> PROUD_SOULS = ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild("slashblade", "proudsouls")));
}
