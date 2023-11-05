package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;

public class GUIUtils {
    // LEFT PANEL
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height, Color color){
        guiGraphics.fill(0, 0, width, height, color.getRGB());
    }
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height, int color){
        renderLeftPanel(guiGraphics, width, height, new Color(color, true));
    }
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height){
        renderLeftPanel(guiGraphics, width, height, new Color(0x3F090B21, true));
    }
    // RIGHT PANEL
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, Color color){
        guiGraphics.fill(screenWidth-width, 0, screenWidth, height, color.getRGB());
    }
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, int color){
        renderRightPanel(guiGraphics, screenWidth, width, height, new Color(color, true));
    }
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height){
        renderRightPanel(guiGraphics, screenWidth, width, height, new Color(0x3F090B21, true));
    }
}

