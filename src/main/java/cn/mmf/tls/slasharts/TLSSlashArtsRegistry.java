package cn.mmf.tls.slasharts;

import cn.mmf.tls.TheLastSmith;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import cn.mmf.tls.combo.ComboStateRegistry;

public class TLSSlashArtsRegistry {
    public static final DeferredRegister<SlashArts> SLASH_ARTS = DeferredRegister.create(SlashArts.REGISTRY_KEY,
            TheLastSmith.MODID);
    /* SA 生死流转斩 */
    public static final DeferredHolder<SlashArts, SlashArts> Transmigration_Slash = SLASH_ARTS.register("transmigration_slash",
            () -> new SlashArts(e -> ComboStateRegistry.ODACHI_COMBO_A1.getId()));
    /* SA 绝技 不死斩 */
    public static final DeferredHolder<SlashArts, SlashArts> FUSHIGIRI = SLASH_ARTS.register("fushigiri",
            () -> new SlashArts(e -> ComboStateRegistry.FUSHIGIRI_LEFT.getId()));
    /* SA 绝技 苇名十字斩 */
    public static final DeferredHolder<SlashArts, SlashArts> IAI_CROSS = SLASH_ARTS.register("iai_cross",
            () -> new SlashArts(e -> ComboStateRegistry.IAI_CROSS.getId()));
    /* SA 樱流 烈风 */
    public static final DeferredHolder<SlashArts, SlashArts> SAKURA_BLISTERING_SWORDS = SLASH_ARTS.register("sakura_blistering_swords",
            () -> new SlashArts(e -> ComboStateRegistry.SAKURA_BLISTERING_SWORDS.getId()));
}
