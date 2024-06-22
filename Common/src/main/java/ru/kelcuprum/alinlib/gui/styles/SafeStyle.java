package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;

public class SafeStyle extends AbstractStyle{
    public SafeStyle() {
        super("safe", Component.translatable("alinlib.style.safe"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        guiGraphics.fill(x, y, x + width, y + height, Colors.CLOWNFISH-0x7f000000);
    }

    @Override
    public void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        guiGraphics.fill(x, y, x + width, y + height, Colors.CLOWNFISH-0x7f000000);
        if(isHoveredOrFocused){
            int xS = x + (int)(position * (double)(width - 4));
            int yS = y+(height - 8) / 2;
            guiGraphics.fill(xS, yS, xS+4, yS+ AlinLib.MINECRAFT.font.lineHeight, Colors.CLOWNFISH);
        }
    }
}
