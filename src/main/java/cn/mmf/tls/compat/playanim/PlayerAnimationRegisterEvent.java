package cn.mmf.tls.compat.playanim;

import cn.mmf.tls.combo.ComboStateRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.compat.playerAnim.PlayerAnimationOverrider;
import mods.flammpfeil.slashblade.compat.playerAnim.VmdAnimation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID, value = Dist.CLIENT)
public class PlayerAnimationRegisterEvent {
    private static final ResourceLocation MotionLocation = ResourceLocation.fromNamespaceAndPath(SlashBlade.MODID,
            "model/pa/player_motion.vmd");

    @SubscribeEvent
    public static void onRegisterPlayerAnim(FMLClientSetupEvent event) {
        if (ModList.get().isLoaded("playeranimator")) {
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.SAKURA_BLISTERING_SWORDS.getId(), 
                    new VmdAnimation(MotionLocation, 400, 488, false));

            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A1.getId(), 
                    new VmdAnimation(MotionLocation, 400, 488, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A2.getId(), 
                    new VmdAnimation(MotionLocation, 814, 894, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A3.getId(), 
                    new VmdAnimation(MotionLocation, 900, 1061, false));

            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.FUSHIGIRI_LEFT.getId(), 
                    new VmdAnimation(MotionLocation, 400, 415, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.FUSHIGIRI_RIGHT.getId(), 
                    new VmdAnimation(MotionLocation, 1816, 1886, false));

            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.IAI_CROSS.getId(),
                    new VmdAnimation(MotionLocation, 200, 306, false));
        }
    }
}
