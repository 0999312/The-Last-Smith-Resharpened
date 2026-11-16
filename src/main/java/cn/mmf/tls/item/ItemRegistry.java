package cn.mmf.tls.item;

import java.util.function.Supplier;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.block.BlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM,
            TheLastSmith.MODID);

    public static final DeferredHolder<Item, Item> RESEARCH_TABLE = register("research_table",
            () -> new BlockItem(BlockRegistry.RESEARCH_TABLE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> YAKIBATSUCHI = register("yakibatsuchi", ItemRegistry::newMaterial);

    public static final DeferredHolder<Item, Item> SAKURA = register("sakura", () -> new Item(new Item.Properties()) {
        @Override
        public int getEnchantmentValue(@NotNull ItemStack stack) {
            return 20;
        }
    });
    public static final DeferredHolder<Item, Item> SAKURA_FULL = register("sakura_full", () -> new Item(new Item.Properties()) {
        @Override
        public int getEnchantmentValue(@NotNull ItemStack stack) {
            return 200;
        }
    });
    public static final DeferredHolder<Item, Item> SAKURA_STEEL_INGOT = register("sakura_steel_ingot", ItemRegistry::newMaterial);
    public static final DeferredHolder<Item, Item> SAKURA_SPHERE = register("sakura_sphere", ItemRegistry::newMaterial);

    public static final DeferredHolder<Item, Item> BLADE_UNFINISHED_1 = register("blade_unfinished_1", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_UNFINISHED_2 = register("blade_unfinished_2", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_UNFINISHED_3 = register("blade_unfinished_3", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_UNFINISHED_4 = register("blade_unfinished_4", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE = register("blade", ItemRegistry::newMaterial);

    public static final DeferredHolder<Item, Item> BLADE_SAKURA_UNFINISHED_1 = register("blade_sakura_unfinished_1", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_SAKURA_UNFINISHED_2 = register("blade_sakura_unfinished_2", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_SAKURA_UNFINISHED_3 = register("blade_sakura_unfinished_3", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_SAKURA_UNFINISHED_4 = register("blade_sakura_unfinished_4", ItemRegistry::newBladeMaterial);
    public static final DeferredHolder<Item, Item> BLADE_SAKURA = register("blade_sakura", ItemRegistry::newMaterial);

    public static final DeferredHolder<Item, Item> SCROLL_BASIC = register("scroll_basic", ItemRegistry::commonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_WOOD_BASIC = register("scroll_wood_basic", ItemRegistry::commonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_BLADE = register("scroll_blade", ItemRegistry::commonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_SAKURA = register("scroll_sakura", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_KATANA = register("scroll_katana", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_ODACHI = register("scroll_odachi", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_NAMED = register("scroll_named", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_SAKURA_BLADE = register("scroll_sakura_blade", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_EXORCISM = register("scroll_exorcism", ItemRegistry::uncommonScroll);
    public static final DeferredHolder<Item, Item> SCROLL_WOOD_KIWAMI = register("scroll_wood_kiwami", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_MURAMASA = register("scroll_muramasa", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_SHURA = register("scroll_shura", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_STAR = register("scroll_star", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_SAKURA_FULL = register("scroll_sakura_full", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_GOD = register("scroll_god", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_BLOOD = register("scroll_blood", ItemRegistry::rareScroll);
    public static final DeferredHolder<Item, Item> SCROLL_YAMATO= register("scroll_yamato", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_CAUSALITY= register("scroll_causality", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_MORTAL = register("scroll_mortal", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_HEIL = register("scroll_heil", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_SHARPNESS = register("scroll_sharpness", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_BEWITCHED = register("scroll_bewitched", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_TENGU_VOL1 = register("scroll_tengu_vol1", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_TENGU_VOL2 = register("scroll_tengu_vol2", ItemRegistry::epicScroll);
    public static final DeferredHolder<Item, Item> SCROLL_TENGU_FULL = register("scroll_tengu_full", ItemRegistry::epicScroll);

    private static <V extends Item> DeferredHolder<Item, V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }

    private static Item newMaterial() {
        return new Item(new Item.Properties());
    }

    private static Item newBladeMaterial() {
        return new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    private static Item commonScroll() {
        return new ScrollItem(new Item.Properties());
    }

    private static Item uncommonScroll() {
        return new ScrollItem(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    private static Item rareScroll() {
        return new ScrollItem(new Item.Properties().rarity(Rarity.RARE));
    }

    private static Item epicScroll() {
        return new ScrollItem(new Item.Properties().rarity(Rarity.EPIC));
    }
}
