package cn.mmf.tls.client.screen;

import java.util.Optional;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.menus.ResearchMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ResearchTableScreen extends ItemCombinerScreen<ResearchMenu> {
    private static final ResourceLocation GUI_LOCATION = ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID,
            "textures/gui/research_table.png");
    private static final Component ERROR_TOOLTIP = Component
            .translatable("container.last_smith.research.error_tooltip");
    private static final Component MISSING_BLADE_TOOLTIP = Component
            .translatable("container.last_smith.research.missing_blade_tooltip");
    private static final Component MISSING_PAPER_TOOLTIP = Component
            .translatable("container.last_smith.research.missing_paper_tooltip");
    private static final Component MISSING_INK_TOOLTIP = Component
            .translatable("container.last_smith.research.missing_ink_tooltip");

    public ResearchTableScreen(ResearchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, GUI_LOCATION);
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderOnboardingTooltips(pGuiGraphics, pMouseX, pMouseY);
    }

    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
    }

    private void renderOnboardingTooltips(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Optional<Component> optional = Optional.empty();
        if (this.hasRecipeError() && this.isHovering(74, 22, 28, 21, (double) pMouseX, (double) pMouseY)) {
            optional = Optional.of(ERROR_TOOLTIP);
        }

        if (this.hoveredSlot != null) {
            ItemStack itemstack = this.menu.getSlot(0).getItem();
            ItemStack itemstack1 = this.hoveredSlot.getItem();
            if (itemstack.isEmpty()) {
                if (this.hoveredSlot.index == 0) {
                    optional = Optional.of(MISSING_BLADE_TOOLTIP);
                }
            } else {
                if (itemstack1.isEmpty()) {
                    if (this.hoveredSlot.index == 1) {
                        optional = Optional.of(MISSING_PAPER_TOOLTIP);
                    } else if (this.hoveredSlot.index == 2) {
                        optional = Optional.of(MISSING_INK_TOOLTIP);
                    }
                }
            }
        }

        optional.ifPresent((component) -> {
            pGuiGraphics.renderTooltip(this.font, this.font.split(component, 115), pMouseX, pMouseY);
        });
    }

    private boolean hasRecipeError() {
        return this.menu.getSlot(0).hasItem() && this.menu.getSlot(1).hasItem() && this.menu.getSlot(2).hasItem()
                && !this.menu.getSlot(this.menu.getResultSlot()).hasItem();
    }

    @Override
    protected void renderErrorIcon(GuiGraphics graphics, int pX, int pY) {
        if (this.hasRecipeError()) {
            graphics.blit(GUI_LOCATION, pX + 74, pY + 22, this.imageWidth, 0, 28, 21);
        }
    }
}
