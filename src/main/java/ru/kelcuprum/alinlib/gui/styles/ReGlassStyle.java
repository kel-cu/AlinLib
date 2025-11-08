package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import restudio.reglass.client.LiquidGlassUniforms;
import restudio.reglass.client.api.ReGlassApi;
import restudio.reglass.client.api.WidgetStyle;

public class ReGlassStyle extends AbstractStyle{
    public ReGlassStyle() {
        super("liquid_ass", Component.translatable("alinlib.style.liquid_ass"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        ReGlassApi.create(guiGraphics).size(width, height).position(x, y)
                .style(backStyle)
                .hover(isHoveredOrFocused ? 1f : 0f)
                .focus(isHoveredOrFocused ? 1f : 0f).render();
    }

    WidgetStyle knobStyle = new WidgetStyle().smoothing(-0.005f).tint(0x000000, 0.1f).shadow(0, 0, 0, 0);
    WidgetStyle backStyle = new WidgetStyle().smoothing(-0.005f).tint(0xff000000, 0.5f);
    @Override
    public void renderBackground$slider(GuiGraphics context, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        int knobX = (int) (x + position * (width - 4));

        ReGlassApi.create(context).position(x, y).size(width, height).style(backStyle).hover(isHoveredOrFocused ? 1f : 0f).focus(isHoveredOrFocused ? 1f : 0f).render();
        ReGlassApi.create(context)
                .size(4, height)
                .position(knobX, y)
                .style(backStyle)
                .hover(isHoveredOrFocused ? 1f : 0f)
                .focus(isHoveredOrFocused ? 1f : 0f)
                .render();

        LiquidGlassUniforms.get().tryApplyBlur(context);
    }

    @Override
    public void renderTitleBackground(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        ReGlassApi.create(guiGraphics).size(width-x, height-y).style(knobStyle).cornerRadius(5f).position(x, y).render();
    }
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height) {
//        ReGlassApi.create(guiGraphics).size(width-x, height-y).style(knobStyle).cornerRadius(5f).position(x, y).render();
    }
}
