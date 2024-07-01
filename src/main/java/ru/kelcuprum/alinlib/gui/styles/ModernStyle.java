package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;

public class ModernStyle extends AbstractStyle {
    public ModernStyle() {
        super("modern", Component.translatable("alinlib.style.modern"));
    }

    @Override
    public void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
        float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int background = (int) (255.0F * f);
        guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
        if (isHoveredOrFocused) {
            guiGraphics.fill(x - 1, y - 1, x + 1 + width, y, 0xFFFFFFFF);
            guiGraphics.fill(x - 1, y + height, x + 1 + width, y + height + 1, 0xFFFFFFFF);

            guiGraphics.fill(x - 1, y, x, y + height, 0xFFFFFFFF);
            guiGraphics.fill(x + width, y, x + 1 + width, y + height, 0xFFFFFFFF);
        }
    }

    @Override
    public void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
        float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int background = (int) (255.0F * f);
        guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
        if (isHoveredOrFocused) {
            int xS = x + (int) (position * (double) (width - 4));
            int yS = y + (height - 8) / 2;
            guiGraphics.fill(xS, yS, xS + 4, yS + AlinLib.MINECRAFT.font.lineHeight, Colors.CLOWNFISH);
            //
            guiGraphics.fill(x - 1, y - 1, x + 1 + width, y, 0xFFFFFFFF);
            guiGraphics.fill(x - 1, y + height, x + 1 + width, y + height + 1, 0xFFFFFFFF);

            guiGraphics.fill(x - 1, y, x, y + height, 0xFFFFFFFF);
            guiGraphics.fill(x + width, y, x + 1 + width, y + height, 0xFFFFFFFF);
        }
    }
}
