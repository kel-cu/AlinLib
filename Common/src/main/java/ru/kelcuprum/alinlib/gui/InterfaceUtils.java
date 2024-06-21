package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import ru.kelcuprum.alinlib.AlinLib;

@Deprecated
public class InterfaceUtils {
    public enum DesignType {
        FLAT(0),
        MODERN(1);


        public final Integer type;

        DesignType(Integer type) {
            this.type = type;
        }
        public void renderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused){
            float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int background = (int) (255.0F * f);
            switch (this.type){
                default -> guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                case 1 -> {
                    guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                    if(isHoveredOrFocused){
                        guiGraphics.fill(x-1, y-1, x+1+width, y, 0xFFFFFFFF);
                        guiGraphics.fill(x-1, y+height, x+1+width, y+height+1, 0xFFFFFFFF);

                        guiGraphics.fill(x-1, y, x, y+height, 0xFFFFFFFF);
                        guiGraphics.fill(x+width, y, x+1+width, y+height, 0xFFFFFFFF);
                    }
                }
            }
        }
        public void renderSliderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position){
            float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int background = (int) (255.0F * f);

            switch (this.type){
                default -> {
                    guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                    if(isHoveredOrFocused){
                        int xS = x + (int)(position * (double)(width - 4));
                        int yS = y+(height - 8) / 2;
                        guiGraphics.fill(xS, yS, xS+4, yS+AlinLib.MINECRAFT.font.lineHeight, Colors.CLOWNFISH);
                        if(this.type == 1){
                            guiGraphics.fill(x-1, y-1, x+1+width, y, 0xFFFFFFFF);
                            guiGraphics.fill(x-1, y+height, x+1+width, y+height+1, 0xFFFFFFFF);

                            guiGraphics.fill(x-1, y, x, y+height, 0xFFFFFFFF);
                            guiGraphics.fill(x+width, y, x+1+width, y+height, 0xFFFFFFFF);
                        }
                    }
                }
            }
        }
    }
}

