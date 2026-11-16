package cn.mmf.tls.event;

import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.capability.slashblade.BladeStateAccess;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * 2026-10-06:19-00 Adds effect descriptions for this mod's own SA and SE on top of the vanilla SlashBlade tooltip.
 * The referenced addon's code was reviewed but contains no SE-description hook, so this is a self-contained support:
 * descriptions are read from the lang keys {@code <nameKey>.desc}.
 * 2026-10-06:19-00 在原版拔刀剑提示之上补充本模组 SA/SE 的效果描述。
 * 已阅读参考附属代码，其中没有 SE 描述钩子，故此处为自实现支持：描述取自语言键 {@code <名称键>.desc}。
 */
@EventBusSubscriber(modid = TheLastSmith.MODID, value = Dist.CLIENT)
public class SpecialEffectTooltipHandler {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) {
            return;
        }
        BladeStateAccess.of(stack).ifPresent(state -> {
            SlashArts slashArts = state.getSlashArts();
            if (slashArts != null) {
                ResourceLocation saKey = SlashArtsRegistry.REGISTRY.getKey(slashArts);
                if (saKey != null && saKey.getNamespace().equals(TheLastSmith.MODID)) {
                    event.getToolTip().add(Component.translatable(slashArts.getDescriptionId() + ".desc")
                            .withStyle(ChatFormatting.DARK_GRAY));
                }
            }
            for (ResourceLocation se : state.getSpecialEffects()) {
                if (se.getNamespace().equals(TheLastSmith.MODID)) {
                    event.getToolTip().add(Component.translatable(Util.makeDescriptionId("se", se) + ".desc")
                            .withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        });
    }
}
