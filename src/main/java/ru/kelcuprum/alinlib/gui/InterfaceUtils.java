package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.Colors;

import java.awt.*;

public class InterfaceUtils {
    private static final WidgetSprites SPRITES = new WidgetSprites(new ResourceLocation("widget/button"), new ResourceLocation("widget/button_disabled"), new ResourceLocation("widget/button_highlighted"));
    public static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("textures/gui/options_background.png");

    // BACKGROUND
    public static void renderBackground(GuiGraphics guiGraphics, Minecraft minecraft){
        if(minecraft.level == null) renderTextureBackground(guiGraphics);
        else guiGraphics.fillGradient(0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), -1072689136, -804253680);
    }
    public static void renderTextureBackground(GuiGraphics guiGraphics){
        float alpha = 0.25F;
        guiGraphics.setColor(alpha, alpha, alpha, 1.0F);
        guiGraphics.blit(BACKGROUND_LOCATION, 0, 0, 0, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), 32, 32);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }


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

    // String
    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.drawString(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }

    public enum DesignType {
        ALINA(0),
        FLAT(1),
        VANILLA(2);


        public final Integer type;

        DesignType(Integer type) {
            this.type = type;
        }
        public void renderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, int color){
            float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int background = (int) (255.0F * f);
            switch (this.type){
                case 0 -> {
                    guiGraphics.fill(x, y, x + width, y + height-1, background / 2 << 24);
                    guiGraphics.fill(x, y+height-1, x + width, y + height, color);
                }
                case 1 -> {
                    guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                }
                default -> guiGraphics.blitSprite(SPRITES.get(active, isHoveredOrFocused), x, y, width, height);
            }
        }
        public void renderSliderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, int color, double position, AbstractSliderButton component){
            float state = !active ? 3 : isHoveredOrFocused ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int background = (int) (255.0F * f);

            switch (this.type){
                case 0 -> {
                    guiGraphics.fill(x, y, x + width, y + height-1, background / 2 << 24);
                    guiGraphics.fill(x, y + height-1, x + width, y + height, new Color(isHoveredOrFocused ? Colors.CLOWNFISH : Colors.SEADRIVE, true).getRGB());
                    if(isHoveredOrFocused){
                        int xS = x + (int)(position * (double)(width - 4));
                        int yS = y+(height - 8) / 2;
                        guiGraphics.fill(xS, yS, xS+4, yS+Minecraft.getInstance().font.lineHeight, new Color(Colors.CLOWNFISH, true).getRGB());
                    }
                }
                case 1 -> {
                    guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                    if(isHoveredOrFocused){
                        int xS = x + (int)(position * (double)(width - 4));
                        int yS = y+(height - 8) / 2;
                        guiGraphics.fill(xS, yS, xS+4, yS+Minecraft.getInstance().font.lineHeight, new Color(Colors.CLOWNFISH, true).getRGB());
                    }
                }
                default -> {
                    guiGraphics.blitSprite(component.getSprite(), x, y, width, height);
                    if(isHoveredOrFocused){
                        guiGraphics.blitSprite(component.getHandleSprite(), x + (int)(position * (double)(width - 8)), y, 8, height);
                    }
                }
            }
        }
    }
}

