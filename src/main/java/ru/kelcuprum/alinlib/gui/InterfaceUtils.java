package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.awt.*;

public class InterfaceUtils {
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

    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.drawString(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }
}

