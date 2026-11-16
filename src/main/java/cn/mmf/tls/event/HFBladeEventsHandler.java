package cn.mmf.tls.event;

import cn.mmf.energyblade.EmpowerSlashBladeEvent;
import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.data.builtin.TLSSlashBladeRegistry;
import net.minecraft.Util;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;

/**
 * 2026-10-06:17-40 Re-enabled for 1.21.1 (energyblade 2.1.7 is now available).
 * Only load this class when the energyblade mod is present (see TheLastSmith#setup).
 * 2026-10-06:17-40 已针对 1.21.1 重新启用（energyblade 2.1.7 现已可用）。
 * 仅在 energyblade 模组存在时才加载本类（见 TheLastSmith#setup）。
 */
public enum HFBladeEventsHandler {
    INSTANCE;

    public void init() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onSlashBladeUpdate(EmpowerSlashBladeEvent event) {
        var state = event.getSlashBladeState();
        if (!state.getTranslationKey().equals(Util.makeDescriptionId("item", TLSSlashBladeRegistry.BUNSHI.location()))) {
            return;
        }
        var offTexture = TheLastSmith.prefix("model/named/rf_roukan/texture.png");
        var onTexture = TheLastSmith.prefix("model/named/rf_roukan/texture_on.png");
        if (event.isPowered()) {
            state.setTexture(onTexture);
            state.setBaseAttackModifier(13F);
        } else {
            state.setTexture(offTexture);
            state.setBaseAttackModifier(6F);
        }
    }
}
