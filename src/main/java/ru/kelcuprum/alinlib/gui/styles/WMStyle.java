package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.screens.DialogScreen.replaceAlpha;

public class WMStyle extends AbstractStyle{
    public WMStyle() {
        super("twm", Component.translatable("alinlib.style.twm"));
    }

    @Override
    public void renderBackground$widget(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        int color = Colors.getWMButton() - (!active ? 0x9F000000 : isHoveredOrFocused ? 0x7F000000 : 0x00000000);//!active ? 0xFF000000 : isHoveredOrFocused ? 0xFFFFFFFF : Colors.getWMButton();

        guiGraphics.fill(x, y, x+width, y+height, 0xFF000000);
        //
        guiGraphics.fill(x, y, x+width, y+1, color);
        guiGraphics.fill(x, y+1, x+1, y+height-1, color);

        guiGraphics.fill(x, y+height, x+width, y+height-1, color);
        guiGraphics.fill(x+width, y+1, x+width-1, y+height-1, color);
    }

    @Override
    public void renderBackground$slider(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        int color = Colors.getWMButton() - (!active ? 0x9F000000 : isHoveredOrFocused ? 0x7F000000 : 0x00000000);//!active ? 0xFF000000 : isHoveredOrFocused ? 0xFFFFFFFF : Colors.getWMButton();

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

    @Override
    public void renderBackground(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height) {
        guiGraphics.fill(x, y, width, height, 0xFF000000);
//        //
        guiGraphics.fill(x, y, width, y + 1, Colors.getWMButton());
        guiGraphics.fill(x, y + 1, x + 1, height - 1, Colors.getWMButton());

        guiGraphics.fill(x, height, width, height - 1, Colors.getWMButton());
        guiGraphics.fill(width, y + 1, width - 1, height - 1, Colors.getWMButton());
    }

    @Override
    public void renderTitleBackground(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height) {
        guiGraphics.fill(x, y, width, height, 0xFF000000);
//        //
        guiGraphics.fill(x, y, width, y + 1, Colors.getWMButton());
        guiGraphics.fill(x, y + 1, x + 1, height - 1, Colors.getWMButton());

        guiGraphics.fill(x, height, width, height - 1, Colors.getWMButton());
        guiGraphics.fill(width, y + 1, width - 1, height - 1, Colors.getWMButton());
    }

    @Override
    public void renderToastBackground(ToastBuilder toastBuilder, GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, double timeline) {
        y+=1; height-=1; width-=1;
        guiGraphics.fill(x, y, width, height, 0xFF000000);
        int color = replaceAlpha(Colors.getWMButton(), (int) (255-(255.0*timeline)));
        if(!AlinLib.bariumConfig.getBoolean("TOAST.TIMELINE")) color = Colors.getWMButton();
//        //
        guiGraphics.fill(x, y, width, y + 2, color);
        guiGraphics.fill(x, y + 2, x + 2, height - 2, color);

        guiGraphics.fill(x, height, width, height - 2, color);
        guiGraphics.fill(width, y + 2, width - 2, height - 2, color);
    }

    @Override
    public int getCheckBoxColor(boolean isActive) {
        return isActive ? Colors.getWMButton() : Colors.getWMButton()-0x9F000000;
    }

    @Override
    public int getCursorColor() {
        return Colors.getWMButton();
    }

    @Override
    public int getHorizontalRuleColor() {
        return Colors.getWMButton()-0x7F000000;
    }

    @Override
    public int getScrollerColor() {
        return Colors.getWMButton();
    }

    @Override
    public int[] getBlockquoteColors() {
        return new int[]{Colors.getWMButton(), (Colors.getWMButton()-0xE1000000)};
    }
}
