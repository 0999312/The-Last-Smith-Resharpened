package cn.mmf.tls.client;

import cn.mmf.tls.client.screen.ResearchTableScreen;
import cn.mmf.tls.menus.ContainerRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID, value = Dist.CLIENT)
public class ClientSetupsEventHander {
    @SubscribeEvent
    public static void registerScreens(final RegisterMenuScreensEvent event) {
        event.register(ContainerRegistry.RESEARCH_TABLE.get(), ResearchTableScreen::new);
    }
}
