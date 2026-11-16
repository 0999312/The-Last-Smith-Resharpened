package cn.mmf.tls.event;

import mods.flammpfeil.slashblade.event.SlashBladeRegistryEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = cn.mmf.tls.TheLastSmith.MODID)
public class CommonEventsHandler {
    @SubscribeEvent
    public static void onLoadingBlade(SlashBladeRegistryEvent.Pre event){
        if(!ModList.get().isLoaded(event.getSlashBladeDefinition().getName().getNamespace()))
            if (event instanceof ICancellableEvent cancellable)
                cancellable.setCanceled(true);
    }
}
