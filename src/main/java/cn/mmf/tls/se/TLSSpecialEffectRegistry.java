package cn.mmf.tls.se;

import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLSSpecialEffectRegistry {
    public static final DeferredRegister<SpecialEffect> SPECIAL_EFFECT = DeferredRegister.create(SpecialEffect.REGISTRY_KEY,
            TheLastSmith.MODID);

    /* 魂樱利刃 */
    public static final DeferredHolder<SpecialEffect, ExtraSharpnessSE> EXTRA_SHARPNESS = SPECIAL_EFFECT.register("extra_sharpness", 
            () -> new ExtraSharpnessSE(1, 15, true, true));
    /* 极致锋利 */
    public static final DeferredHolder<SpecialEffect, ExtraSharpnessSE> EXTREME_SHARPNESS = SPECIAL_EFFECT.register("extreme_sharpness", 
            () -> new ExtraSharpnessSE(2, 30, false, false));
    /* 魂灵之力 */
    public static final DeferredHolder<SpecialEffect, ExtraPowerSE> EXTRA_POWER = SPECIAL_EFFECT.register("extra_power", 
            () -> new ExtraPowerSE(1, 15, true, true));
    /* 极致妖力 */
    public static final DeferredHolder<SpecialEffect, ExtraPowerSE> BEWITCHED_POWER = SPECIAL_EFFECT.register("bewitched_power", 
            () -> new ExtraPowerSE(2, 30, false, false));
    /* 驱魔刃 */
    public static final DeferredHolder<SpecialEffect, FushigiriSE> SMITE_SE = SPECIAL_EFFECT.register("smite_se", 
            () -> new FushigiriSE(1, 15, true, true));
    /* 一念成佛 */
    public static final DeferredHolder<SpecialEffect, FushigiriSE> EXTRA_SMITE = SPECIAL_EFFECT.register("extra_smite", 
            () -> new FushigiriSE(2, 30, true, true));
    /* 不死斩 */
    public static final DeferredHolder<SpecialEffect, FushigiriSE> FUSHIGIRI_SE = SPECIAL_EFFECT.register("fushigiri_se", 
            () -> new FushigiriSE(3, 30, false, false));
    /* 隐藏SE，效果似乎与攻击评级的颜色变化有关 */
    public static final DeferredHolder<SpecialEffect, SectumsempraSE> SECTUMSEMPRA_SE = SPECIAL_EFFECT.register("sectumsempra_se",
            () -> new SectumsempraSE(40, false, false));

    // 2026-10-06:17-40 Removed the redundant TLS "uma_special" SE; the UMA blades now use
    // blades_derby:uma_special (provided by the Blades Derby mod). TODO 修改具体的SE值
}
