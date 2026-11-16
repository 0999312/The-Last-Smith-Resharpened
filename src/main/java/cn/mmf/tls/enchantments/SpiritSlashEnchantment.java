package cn.mmf.tls.enchantments;

import cn.mmf.tls.Config;
import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.capability.slashblade.BladeStateAccess;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * 2026-10-06:16-30 Migrated "Spirit Slash" from an Enchantment subclass to a data-driven enchantment.
 * The effect logic is unchanged: each kill adds the enchantment level to the blade's kill count.
 * 2026-10-06:16-30 将「灵魂切裂」从 Enchantment 子类迁移为数据驱动附魔，效果逻辑不变：
 * 每次击杀使拔刀剑的击杀数增加等同于附魔等级的值。
 */
@EventBusSubscriber(modid = TheLastSmith.MODID)
public class SpiritSlashEnchantment {
    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        Entity killer = event.getSource().getEntity();
        if (!(killer instanceof LivingEntity living)) {
            return;
        }
        ItemStack blade = living.getMainHandItem();
        if (blade.isEmpty()) {
            return;
        }
        BladeStateAccess.of(blade).ifPresent(state -> {
            ItemEnchantments enchantments = blade.getEnchantments();
            for (Holder<Enchantment> holder : enchantments.keySet()) {
                if (holder.is(EnchantmentsRegistry.SPIRIT_SLASH)) {
                    int level = enchantments.getLevel(holder);
                    if (level > 0) {
                        // 2026-10-06:17-40 The bonus per level is configurable via Config.
                        // 2026-10-06:17-40 每级加成可通过 Config 配置。
                        state.setKillCount(state.getKillCount() + level * Config.spiritSlashBonusPerLevel);
                    }
                }
            }
        });
    }
}
