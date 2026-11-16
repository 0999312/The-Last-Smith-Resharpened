package cn.mmf.tls.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// TODO: CauldronInteraction API changed in 1.21.1 - WATER.map is no longer public
// Need to use CauldronInteraction.addDefaultInteractions or similar
@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID)
public class SetupEventsHandler {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        // TODO: Re-enable cauldron interactions when API is fixed
    }
}
