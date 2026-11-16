package cn.mmf.tls.se;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID)
public class ExtraSharpnessSE extends SpecialEffect {
    private final int seLevel;
    public ExtraSharpnessSE(int seLevel, int requestLevel, boolean isCopiable, boolean isRemovable) {
        super(requestLevel, isCopiable, isRemovable);
        this.seLevel = seLevel;
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();

        setEffect(event, state, TLSSpecialEffectRegistry.EXTRA_SHARPNESS);
        setEffect(event, state, TLSSpecialEffectRegistry.EXTREME_SHARPNESS);
    }

    private static void setEffect(SlashBladeEvent.DoSlashEvent event, ISlashBladeState state, DeferredHolder<SpecialEffect, ExtraSharpnessSE> se) {
        if(state.hasSpecialEffect(se.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();

            int level = player.experienceLevel;

            // 2026-10-06:18-55 Level requirement and bonus are now read from Config.
            // 2026-10-06:18-55 生效等级与加成改为从 Config 读取。
            boolean extreme = se.get() == TLSSpecialEffectRegistry.EXTREME_SHARPNESS.get();
            int requestLevel = extreme ? cn.mmf.tls.Config.extremeSharpnessRequestLevel
                    : cn.mmf.tls.Config.extraSharpnessRequestLevel;
            if (level >= requestLevel) {
                float bonus = (float) (extreme ? cn.mmf.tls.Config.extremeSharpnessBonus
                        : cn.mmf.tls.Config.extraSharpnessBonus);
                event.setDamage(event.getDamage() * 
                        1 + (event.getBlade().getEnchantmentLevel(player.level().registryAccess()
                                .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.SHARPNESS)) 
                                * bonus));
            }
        }
    }

    public int getSELevel() {
        return seLevel;
    }

}
