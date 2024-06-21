package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;

public class GuiUtils {

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

    public static ResourceLocation getResourceLocation(String path){
        return ResourceLocation.withDefaultNamespace(path);
    }
    public static ResourceLocation getResourceLocation(String id, String path){
        return ResourceLocation.fromNamespaceAndPath(id, path);
    }
}
