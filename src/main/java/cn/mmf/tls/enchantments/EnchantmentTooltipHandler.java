package cn.mmf.tls.enchantments;

import cn.mmf.tls.Config;
import cn.mmf.tls.TheLastSmith;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * 2026-10-06:17-40 Appends effect descriptions for the two custom enchantments, using the configured values.
 * 2026-10-06:17-40 为两个自定义附魔追加效果描述，数值取自 Config 配置。
 */
@EventBusSubscriber(modid = TheLastSmith.MODID)
public class EnchantmentTooltipHandler {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) {
            return;
        }
        ItemEnchantments enchantments = stack.getEnchantments();
        if (enchantments.isEmpty()) {
            return;
        }
        for (Holder<Enchantment> holder : enchantments.keySet()) {
            int level = enchantments.getLevel(holder);
            if (level <= 0) {
                continue;
            }
            if (holder.is(EnchantmentsRegistry.SPIRIT_SLASH)) {
                event.getToolTip().add(Component.translatable("enchantment.last_smith.spirit_slash.desc",
                        level * Config.spiritSlashBonusPerLevel).withStyle(ChatFormatting.DARK_GRAY));
            } else if (holder.is(EnchantmentsRegistry.SPIRIT_ABSORPTION)) {
                int percent = (int) Math.round(Config.spiritAbsorptionBonusPerLevel * 100.0D * level);
                event.getToolTip().add(Component.translatable("enchantment.last_smith.spirit_absorption.desc",
                        percent).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}
