package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;
import ru.kelcuprum.alinlib.gui.styles.SafeStyle;

import java.util.*;

public class GuiUtils {
    // Utils
    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.drawString(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }

    public static boolean isDoesNotFit(Component message, Number width, Number height) {
        int size = AlinLib.MINECRAFT.font.width(message) + (height.intValue() - 8) * 2;
        return size > width.intValue();
    }

    public static int DEFAULT_WIDTH() {
        return Minecraft.getInstance().getWindow().getWidth() - ((AlinLib.bariumConfig.getBoolean("CONFIG_SCREEN.SMALL_PANEL_SIZE", false) ? 130 : 190) - 20);
    }

    public static final int DEFAULT_HEIGHT = 20;

    public static net.minecraft.resources.ResourceLocation getResourceLocation(String path) {
        return
                //#if MC >= 12100
        net.minecraft.resources.ResourceLocation.withDefaultNamespace(path);
        //#elseif MC < 12100
        //$$ new net.minecraft.resources.ResourceLocation(path);
        //#endif
    }

    public static net.minecraft.resources.ResourceLocation getResourceLocation(String id, String path) {
        return
                //#if MC >= 12100
        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(id, path);
        //#elseif MC < 12100
        //$$ new net.minecraft.resources.ResourceLocation(id, path);
        //#endif
    }

    // Styles
    protected static Map<String, AbstractStyle> styles = new HashMap<>();
    protected static List<String> stylesID = new ArrayList<>();
    protected static AbstractStyle safeStyle = new SafeStyle();

    public static void registerStyle(AbstractStyle style) {
        if (styles.get(style.id) == null) {
            stylesID.add(style.id);
        }
        styles.put(style.id, style);
        AlinLib.log(String.format("[Style] Registration of %s by id %s", style.name.getString(), style.id));
    }
    
    public static String[] getStylesName(){
        String[] list = new String[stylesID.size()];
        int i = 0;
        for(String id : stylesID){
            list[i] = styles.getOrDefault(id, safeStyle).name.getString();
            i++;
        }
        return list;
    }

    public static int getPositionOnStylesID(String name){
        int i = 0;
        for(String id : getStylesName()){
            if(id.equals(name)) break;
            else i++;
        }
        return i;
    }

    public static AbstractStyle getStyleByName(String name){
        AbstractStyle style = safeStyle;
        for(String id : stylesID){
            AbstractStyle styleById = getStyleByID(id);
            if(Objects.equals(styleById.name.getString(), name)){
                style=styleById;
            };
        }
        return style;
    }

    public static AbstractStyle getStyleByID(String id){
        return styles.getOrDefault(id, safeStyle);
    }

    public static AbstractStyle getSelected(){
        String id = AlinLib.bariumConfig.getString("DEFAULT_DESIGN_TYPE", stylesID.isEmpty() ? safeStyle.id : stylesID.get(0));
        return getStyleByID(id);
    }
}
