package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;

public class GUIUtils {
    public static void renderLeftSlider(GuiGraphics guiGraphics, int width, int height, Color color){
        guiGraphics.fill(0, 0, width, height, color.getRGB());
    }
    public static void renderLeftSlider(GuiGraphics guiGraphics, int width, int height, int color){
        renderLeftSlider(guiGraphics, width, height, new Color(color, true));
    }
}

