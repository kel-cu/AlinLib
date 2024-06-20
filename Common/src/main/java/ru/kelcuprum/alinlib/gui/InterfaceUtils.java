package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
//import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;

public class InterfaceUtils {
//    private static final WidgetSprites SPRITES = new WidgetSprites(getResourceLocation("widget/button"), getResourceLocation("widget/button_disabled"), getResourceLocation("widget/button_highlighted"));
    public static final ResourceLocation BACKGROUND_LOCATION = getResourceLocation("textures/block/dirt.png");

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
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height, int color){
        guiGraphics.fill(0, 0, width, height, color);
    }
    public static void renderLeftPanel(GuiGraphics guiGraphics, int width, int height){
        renderLeftPanel(guiGraphics, width, height, Colors.BLACK_ALPHA);
    }
    // RIGHT PANEL
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height, int color){
        guiGraphics.fill(screenWidth-width, 0, screenWidth, height, color);
    }
    public static void renderRightPanel(GuiGraphics guiGraphics, int screenWidth, int width, int height){
        renderRightPanel(guiGraphics, screenWidth, width, height, Colors.BLACK_ALPHA);
    }

    // String
    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.drawString(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }

    public static boolean isDoesNotFit(Component message, Number width, Number height){
        int size = AlinLib.MINECRAFT.font.width(message) + (height.intValue() - 8) * 2;
        return size > width.intValue();
    }
    public static int DEFAULT_WIDTH(){
        return Minecraft.getInstance().getWindow().getWidth()-((AlinLib.bariumConfig.getBoolean("CONFIG_SCREEN.SMALL_PANEL_SIZE", false) ?  130 : 190)-20);
    }
    public static final int DEFAULT_HEIGHT = 20;
    public enum DesignType {
//        @Deprecated ALINA(0),
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
                case 0 -> guiGraphics.fill(x, y, x + width, y + height, background / 2 << 24);
                default -> {
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
        public void renderSliderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position, AbstractSliderButton component){
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
    public static ResourceLocation getResourceLocation(String path){
        return new ResourceLocation(path);
    }
    public static ResourceLocation getResourceLocation(String id, String path){
        return new ResourceLocation(id, path);
    }

    public interface Colors {
        int[] SPECKLE = {0xFFffdc78, 0xFFcbbaa6};
        int SEADRIVE = 0xFF79c738;
        int CLOWNFISH = 0xFFf1ae31;
        int SELFISH = 0xFFff366e;
        int GROUPIE = 0xFFfc1a47;
        int KENNY = 0xFF627921;
        int CONVICT = 0xFFffdc32;
        int SEABIRD = 0xFFf1ae31;
        int TETRA = 0xFFff67d1;
        int FORGOT = 0xFF4f3e60;
        int WHITE = 0xFFFFFFFF;
        int BLACK = 0xFF000000;
        int BLACK_ALPHA = 0x37000000;
    }

    public interface Icons {
        ResourceLocation RESET = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/reset.png");
        ResourceLocation OPTIONS = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/options.png");
        ResourceLocation LIST = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/list.png");
        ResourceLocation ADD = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/add.png");
        ResourceLocation REMOVE = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/remove.png");


        ResourceLocation BOTTOM = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/button.png");
        ResourceLocation DISLIKE = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/dislike.png");
        ResourceLocation DONT = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/dont.png");
        ResourceLocation LEFT = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/left.png");
        ResourceLocation LIKE = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/like.png");
        ResourceLocation MUSIC = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/music.png");
        ResourceLocation NOTES = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/notes.png");
        ResourceLocation RIGHT = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/right.png");
        ResourceLocation TOP = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/top.png");
        ResourceLocation WARN = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/warn.png");
        ResourceLocation WARNING = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/warning.png");
        ResourceLocation WHAT = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/what.png");
        ResourceLocation CLOWNFISH = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/clownfish.png");
        ResourceLocation WIKI = getResourceLocation(AlinLib.MODID, "textures/gui/sprites/wiki.png");
    }
}

