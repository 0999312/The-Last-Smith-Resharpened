package cn.mmf.tls.se;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID)
public class FushigiriSE extends SpecialEffect {
    private final int seLevel;
    public FushigiriSE(int seLevel, int requestLevel, boolean isCopiable, boolean isRemovable) {
        super(requestLevel, isCopiable, isRemovable);
        this.seLevel = seLevel;
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();

        setEffect(event, state, TLSSpecialEffectRegistry.SMITE_SE);
        setEffect(event, state, TLSSpecialEffectRegistry.EXTRA_SMITE);
        setEffect(event, state, TLSSpecialEffectRegistry.FUSHIGIRI_SE);
    }

    private static void setEffect(SlashBladeEvent.HitEvent event, ISlashBladeState state, DeferredHolder<SpecialEffect, FushigiriSE> se) {
        if(state.hasSpecialEffect(se.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();

            int level = player.experienceLevel;

            // 2026-10-06:18-55 Level requirement and bonus are now read from Config.
            // 2026-10-06:18-55 生效等级与加成改为从 Config 读取。
            int requestLevel;
            double bonus;
            if (se.get() == TLSSpecialEffectRegistry.FUSHIGIRI_SE.get()) {
                requestLevel = cn.mmf.tls.Config.fushigiriRequestLevel;
                bonus = cn.mmf.tls.Config.fushigiriBonus;
            } else if (se.get() == TLSSpecialEffectRegistry.EXTRA_SMITE.get()) {
                requestLevel = cn.mmf.tls.Config.extraSmiteRequestLevel;
                bonus = cn.mmf.tls.Config.extraSmiteBonus;
            } else {
                requestLevel = cn.mmf.tls.Config.smiteRequestLevel;
                bonus = cn.mmf.tls.Config.smiteBonus;
            }
            if (level >= requestLevel) {
                LivingEntity target = event.getTarget();
                if(target.getType().is(EntityTypeTags.SENSITIVE_TO_SMITE)) {
                    target.hurt(player.damageSources().indirectMagic(player, target), 
                            (float) (event.getBlade().getEnchantmentLevel(player.level().registryAccess()
                                    .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                                    .getOrThrow(Enchantments.SMITE)) * bonus));
                }
            }
        }
    }

    public int getSELevel() {
        return seLevel;
    }

}
