package cn.mmf.tls;

import cn.mmf.tls.item.ItemRegistry;
import mods.flammpfeil.slashblade.SlashBladeCreativeGroup;
import mods.flammpfeil.slashblade.capability.slashblade.BladeStateAccess;
import mods.flammpfeil.slashblade.registry.SlashBladeItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLSCreativeGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, TheLastSmith.MODID);

    private static final CreativeModeTab SLASHBLADE = CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.the_last_smith")).icon(() -> {
                ItemStack stack = new ItemStack(SlashBladeItems.SLASHBLADE);
                BladeStateAccess.of(stack).ifPresent(s -> {
                    s.setModel(ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, "model/nagasada/model.obj"));
                    s.setTexture(ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, "model/nagasada/texture.png"));
                });
                return stack;
            }).displayItems((features, output) -> {
                ItemRegistry.ITEMS.getEntries().forEach(item -> {
                    output.accept(item.get());
                });

            }).withTabsBefore(SlashBladeCreativeGroup.SLASHBLADE_GROUP.getKey()).build();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SLASHBLADE_GROUP = CREATIVE_MODE_TABS.register("slashblade",
            () -> SLASHBLADE);

}
