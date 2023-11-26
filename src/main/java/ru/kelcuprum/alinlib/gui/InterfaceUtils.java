package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.Colors;

import java.awt.*;

public class InterfaceUtils {
    public static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("textures/gui/options_background.png");

    // LEFT PANEL
    public static void renderTextureLeftPanel(GuiGraphics guiGraphics, int width, int height, float alpha, ResourceLocation texture){
        guiGraphics.setColor(alpha, alpha, alpha, 1.0F);
        guiGraphics.blit(texture, 0, 0, 0, 0.0F, 0.0F, width, height, 32, 32);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    public static void renderTextureLeftPanel(GuiGraphics guiGraphics, float alpha , int width, int height){
        renderTextureLeftPanel(guiGraphics, width, height, alpha,  BACKGROUND_LOCATION);
    }
    public static void renderTextureLeftPanel(GuiGraphics guiGraphics, int width, int height){
        renderTextureLeftPanel(guiGraphics, width, height, 0.5F ,BACKGROUND_LOCATION);
    }
    // RIGHT PANEL
    public static void renderTextureRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, float alpha, ResourceLocation texture){
        guiGraphics.setColor(alpha, alpha, alpha, 1.0F);
        guiGraphics.blit(texture, screenWidth-width, 0, 0, 0.0F, 0.0F, screenWidth, height, 32, 32);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    public static void renderTextureRightPanel(GuiGraphics guiGraphics, int screenWidth, float alpha, int width, int height){
        renderTextureRightPanel(guiGraphics, screenWidth, width, height, alpha, BACKGROUND_LOCATION);
    }
    public static void renderTextureRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height){
        renderTextureRightPanel(guiGraphics, screenWidth, width, height, 0.5F, BACKGROUND_LOCATION);
    }

    // LEFT PANEL
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height, Color color){
        guiGraphics.fill(0, 0, width, height, color.getRGB());
    }
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height, int color){
        renderLeftPanel(guiGraphics, width, height, new Color(color, true));
    }
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height){
        renderLeftPanel(guiGraphics, width, height, new Color(Colors.DARK_PURPLE_ALPHA, true));
    }
    // RIGHT PANEL
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, Color color){
        guiGraphics.fill(screenWidth-width, 0, screenWidth, height, color.getRGB());
    }
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, int color){
        renderRightPanel(guiGraphics, screenWidth, width, height, new Color(color, true));
    }
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height){
        renderRightPanel(guiGraphics, screenWidth, width, height, new Color(Colors.DARK_PURPLE_ALPHA, true));
    }

    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.drawString(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }
}

