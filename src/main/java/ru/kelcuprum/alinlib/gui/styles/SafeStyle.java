package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class SafeStyle extends AbstractStyle{
    public SafeStyle() {
        super("safe", Component.translatable("alinlib.style.safe"));
    }

    @Override
    public void renderBackground$widget(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused) {
    }

    @Override
    public void renderBackground$slider(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position) {
    }
}
