package cn.mmf.tls.enchantments;

import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.registry.SlashBladeItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentsRegistry {
    // 2026-10-06:16-30 Enchantment became final and data-driven in 1.21.1, so it can no longer be subclassed.
    // 2026-10-06:16-30 1.21.1 中 Enchantment 变为 final 且数据驱动，无法再继承实现。
    // Register the two enchantments as datapack registry objects via DataGen's RegistrySetBuilder instead.
    // 改为通过 DataGen 的 RegistrySetBuilder 将两个附魔注册为数据包注册表对象。
    public static final ResourceKey<Enchantment> SPIRIT_SLASH = register("spirit_slash");
    public static final ResourceKey<Enchantment> SPIRIT_ABSORPTION = register("spirit_absorption");

    // 2026-10-06:16-30 Both enchantments only support SlashBlade items (matching the old blade-state check).
    // 2026-10-06:16-30 两个附魔只支持拔刀剑物品（对应旧版的拔刀剑状态判定）。
    public static void registerAll(BootstrapContext<Enchantment> bootstrap) {
        HolderGetter<Item> items = bootstrap.lookup(Registries.ITEM);
        HolderSet<Item> blades = HolderSet.direct(
                items.getOrThrow(SlashBladeItems.SLASHBLADE.getKey()),
                items.getOrThrow(SlashBladeItems.SLASHBLADE_WOOD.getKey()),
                items.getOrThrow(SlashBladeItems.SLASHBLADE_BAMBOO.getKey()),
                items.getOrThrow(SlashBladeItems.SLASHBLADE_SILVERBAMBOO.getKey()),
                items.getOrThrow(SlashBladeItems.SLASHBLADE_WHITE.getKey()));

        // Original: Rarity.UNCOMMON (weight 5), EnchantmentCategory.WEAPON, MAINHAND + OFFHAND, max level 4.
        // 原版：UNCOMMON（权重 5）、WEAPON 类别、主手+副手、最大等级 4。
        bootstrap.register(SPIRIT_SLASH, Enchantment.enchantment(
                        Enchantment.definition(blades, 5, 4,
                                Enchantment.dynamicCost(1, 10),
                                Enchantment.dynamicCost(6, 10),
                                2, EquipmentSlotGroup.HAND))
                .build(TheLastSmith.prefix("spirit_slash")));

        // Original: Rarity.UNCOMMON (weight 5), EnchantmentCategory.WEAPON, MAINHAND + OFFHAND, max level 5.
        // 原版：UNCOMMON（权重 5）、WEAPON 类别、主手+副手、最大等级 5。
        bootstrap.register(SPIRIT_ABSORPTION, Enchantment.enchantment(
                        Enchantment.definition(blades, 5, 5,
                                Enchantment.dynamicCost(1, 10),
                                Enchantment.dynamicCost(6, 10),
                                2, EquipmentSlotGroup.HAND))
                .build(TheLastSmith.prefix("spirit_absorption")));
    }

    private static ResourceKey<Enchantment> register(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, TheLastSmith.prefix(id));
    }
}
