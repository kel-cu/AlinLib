package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.Colors;

public class WinStyle extends AbstractStyle{
    public WinStyle() {
        super("windows", Component.translatable("alinlib.style.windows"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        int color = isHoveredOrFocused && active ? Colors.getWinColor(): 0xffc0c0c0;

        int color1 = 0x20000000;
        int color2 = 0x3F000000;
        int color3 = 0x7F000000;
        int color4 = 0xF5000000;

        // light
        // dark light
        // dark
        // very dark
        guiGraphics.fill(x, y, x+width, y+height, color);
        //
        guiGraphics.fill(x+1, y+1, x+width-2, y+2, color1);
        guiGraphics.fill(x+1, y+2, x+2, y+height-1, color1);
        //
        guiGraphics.fill(x+2, y+2, x+width-2, y+height-2, color2);
        //
        guiGraphics.fill(x+width-2, y+1, x+width-1, y+height-2, color3);
        guiGraphics.fill(x+1, y+height-2, x+width-1, y+height-1, color3);
        //
        guiGraphics.fill(x+width-1, y, x+width, y+height-1, color4);
        guiGraphics.fill(x, y+height-1, x+width, y+height, color4);
    }

    @Override
    public void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        int color = 0xff717171;
        int colorb = isHoveredOrFocused ? Colors.getWinColor() : 0xFFC0C0C0;

        int color1 = 0x20000000;
        int color2 = 0x3F000000;
        int color3 = 0x7F000000;
        int color4 = 0xF5000000;

        // light
        // dark light
        // dark
        // very dark
        guiGraphics.fill(x, y, x+width, y+height, color);
        //
        guiGraphics.fill(x, y+1, x+width-1, y+2, color3);
        guiGraphics.fill(x, y+2, x+1, y+height-1, color3);
        //
        guiGraphics.fill(x+1, y+2, x+width-2, y+height-2, color2);
        //
        guiGraphics.fill(x+width-2, y+2, x+width-1, y+height-2, color1);
        guiGraphics.fill(x+1, y+height-2, x+width-1, y+height-1, color1);
        //
        guiGraphics.fill(x-1, y, x, y+height, color4);
        guiGraphics.fill(x, y+1, x+width, y, color4);
//        if(isHoveredOrFocused){
            int widthS = 6;
            int xS = x + (int)(position * (double)(width - widthS));
            guiGraphics.fill(xS, y, xS+widthS, y+height, colorb);
            //
            guiGraphics.fill(xS+1, y+1, xS+widthS-2, y+2, color1);
            guiGraphics.fill(xS+1, y+2, xS+2, y+height-1, color1);
            //
            guiGraphics.fill(xS+2, y+2, xS+widthS-2, y+height-2, color2);
            //
            guiGraphics.fill(xS+widthS-2, y+1, xS+widthS-1, y+height-2, color3);
            guiGraphics.fill(xS+1, y+height-2, xS+widthS-1, y+height-1, color3);
            //
            guiGraphics.fill(xS+widthS-1, y, xS+widthS, y+height-1, color4);
            guiGraphics.fill(xS, y+height-1, xS+widthS, y+height, color4);
//        }
    }
}
