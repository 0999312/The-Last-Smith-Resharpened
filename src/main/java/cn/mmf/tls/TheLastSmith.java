package cn.mmf.tls;

import com.mojang.logging.LogUtils;

import cn.mmf.tls.block.BlockRegistry;
import cn.mmf.tls.combo.ComboStateRegistry;
import cn.mmf.tls.event.HFBladeEventsHandler;
import cn.mmf.tls.item.ItemRegistry;
import cn.mmf.tls.menus.ContainerRegistry;
import cn.mmf.tls.recipe.RecipeSerializerRegistry;
import cn.mmf.tls.se.TLSSpecialEffectRegistry;
import cn.mmf.tls.slasharts.TLSSlashArtsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(TheLastSmith.MODID)
public class TheLastSmith {
    public static final String MODID = "last_smith";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public TheLastSmith(IEventBus modEventBus, ModContainer modContainer) {
        // 2026-10-06:17-40 Register the common config (enchantment effect values).
        // 2026-10-06:17-40 注册公共配置（附魔效果数值）。
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        TLSCreativeGroup.CREATIVE_MODE_TABS.register(modEventBus);
        RecipeSerializerRegistry.RECIPE_TYPES.register(modEventBus);
        RecipeSerializerRegistry.RECIPE_SERIALIZER.register(modEventBus);
        ContainerRegistry.CONTAINER_TYPES.register(modEventBus);
        ComboStateRegistry.COMBO_STATE.register(modEventBus);
        TLSSlashArtsRegistry.SLASH_ARTS.register(modEventBus);
        TLSSpecialEffectRegistry.SPECIAL_EFFECT.register(modEventBus);

        modEventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // 2026-10-06:17-40 Only load the energyblade (HF Blade) handler class when the mod is present,
        // so a missing optional mod never affects the rest of the mod.
        // 2026-10-06:17-40 仅在 energyblade（HF Blade）存在时才加载其处理类，
        // 未安装该可选模组时不会影响其余功能。
        if (ModList.get().isLoaded("energyblade")) {
            HFBladeEventsHandler.INSTANCE.init();
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
