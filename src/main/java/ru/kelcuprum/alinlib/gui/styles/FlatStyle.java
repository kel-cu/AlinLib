package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;

public class FlatStyle extends AbstractStyle{
    public FlatStyle() {
        super("flat", Component.translatable("alinlib.style.flat"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int background = (int) (255.0F * f);
        guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
    }

    @Override
    public void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int background = (int) (255.0F * f);
        guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
        if(isHoveredOrFocused){
            int xS = x + (int)(position * (double)(width - 4));
            int yS = y+(height - 8) / 2;
            guiGraphics.fill(xS, yS, xS+4, yS+ AlinLib.MINECRAFT.font.lineHeight, Colors.CLOWNFISH);
        }
    }
}
