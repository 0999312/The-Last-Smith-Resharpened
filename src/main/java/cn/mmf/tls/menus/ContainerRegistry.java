package cn.mmf.tls.menus;

import cn.mmf.tls.TheLastSmith;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ContainerRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister
            .create(Registries.MENU, TheLastSmith.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ResearchMenu>> RESEARCH_TABLE = CONTAINER_TYPES
            .register("research_table", () -> new MenuType<>((ResearchMenu::new), FeatureFlags.DEFAULT_FLAGS));
}
