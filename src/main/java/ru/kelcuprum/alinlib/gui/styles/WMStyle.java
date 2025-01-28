package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.Colors;

public class WMStyle extends AbstractStyle{
    public WMStyle() {
        super("twm", Component.translatable("alinlib.style.twm"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        int color = !active ? 0xFF000000 : isHoveredOrFocused ? 0xFFFFFFFF : Colors.getWMButton();

        guiGraphics.fill(x, y, x+width, y+height, 0xFF000000);
        //
        guiGraphics.fill(x, y, x+width, y+1, color);
        guiGraphics.fill(x, y+1, x+1, y+height-1, color);

        guiGraphics.fill(x, y+height, x+width, y+height-1, color);
        guiGraphics.fill(x+width, y+1, x+width-1, y+height-1, color);
    }

    @Override
    public void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        int color = !active ? 0xFF000000 : isHoveredOrFocused ? 0xFFFFFFFF : Colors.getWMButton();

        guiGraphics.fill(x, y, x+width, y+height, 0xFF000000);
        //
        guiGraphics.fill(x, y, x+width, y+1, Colors.getWMButton());
        guiGraphics.fill(x, y+1, x+1, y+height-1, Colors.getWMButton());

        guiGraphics.fill(x, y+height, x+width, y+height-1, Colors.getWMButton());
        guiGraphics.fill(x+width, y+1, x+width-1, y+height-1, Colors.getWMButton());
        if(isHoveredOrFocused){
            int widthS = 6;
            int xS = x + (int)(position * (double)(width - widthS));
            guiGraphics.fill(xS, y, xS+widthS, y +1, color);
            guiGraphics.fill(xS, y +1, xS+1, y + height -1, color);

            guiGraphics.fill(xS, y + height, xS+widthS, y + height -1, color);
            guiGraphics.fill(xS+widthS, y +1, xS+widthS-1, y + height -1, color);
        }
    }
}
