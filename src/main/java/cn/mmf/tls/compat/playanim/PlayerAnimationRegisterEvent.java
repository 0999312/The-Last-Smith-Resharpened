package cn.mmf.tls.compat.playanim;

import cn.mmf.tls.combo.ComboStateRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.compat.playerAnim.PlayerAnimationOverrider;
import mods.flammpfeil.slashblade.compat.playerAnim.VmdAnimation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class PlayerAnimationRegisterEvent {
    private static final ResourceLocation MotionLocation = new ResourceLocation(SlashBlade.MODID,
            "model/pa/player_motion.vmd");
    
	@SubscribeEvent
	public static void onRegisterPlayerAnim(FMLClientSetupEvent event) {
		PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A1.getId(), 
				new VmdAnimation(MotionLocation, 400, 488, false));
		PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A2.getId(), 
				new VmdAnimation(MotionLocation, 814, 894, false));
		PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.ODACHI_COMBO_A3.getId(), 
				new VmdAnimation(MotionLocation, 900, 1061, false));
	}
}
