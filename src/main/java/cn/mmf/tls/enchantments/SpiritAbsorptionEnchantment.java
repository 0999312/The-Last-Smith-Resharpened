package cn.mmf.tls.enchantments;

import cn.mmf.tls.Config;
import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

/**
 * 2026-10-06:16-30 Migrated "Spirit Absorption" from an Enchantment subclass to a data-driven enchantment.
 * The effect logic is unchanged: proud soul gain is multiplied by (1 + level * 0.2).
 * 2026-10-06:16-30 将「灵魂汲取」从 Enchantment 子类迁移为数据驱动附魔，效果逻辑不变：
 * 获得的魂灵数量乘以 (1 + 等级 * 0.2)。
 */
@EventBusSubscriber(modid = TheLastSmith.MODID)
public class SpiritAbsorptionEnchantment {
    @SubscribeEvent
    public static void onAddProudSoul(SlashBladeEvent.AddProudSoulEvent event) {
        ItemStack blade = event.getBlade();
        if (blade.isEmpty()) {
            return;
        }
        ItemEnchantments enchantments = blade.getEnchantments();
        for (Holder<Enchantment> holder : enchantments.keySet()) {
            if (holder.is(EnchantmentsRegistry.SPIRIT_ABSORPTION)) {
                int level = enchantments.getLevel(holder);
                if (level > 0) {
                    // 2026-10-06:17-40 The bonus per level is configurable via Config.
                    // 2026-10-06:17-40 每级加成可通过 Config 配置。
                    event.setNewCount((int) (event.getOriginCount()
                            * (1.0D + level * Config.spiritAbsorptionBonusPerLevel)));
                }
            }
        }
    }
}
