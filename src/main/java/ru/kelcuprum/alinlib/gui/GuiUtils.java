package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;
import ru.kelcuprum.alinlib.gui.styles.SafeStyle;

import java.util.*;

public class GuiUtils {
    // Utils
    public static void drawCenteredString(GuiGraphicsExtractor guiGraphics, Font font, Component component, int x, int y, int color, boolean shadow) {
        FormattedCharSequence formattedCharSequence = component.getVisualOrderText();
        guiGraphics.text(font, formattedCharSequence, x - font.width(formattedCharSequence) / 2, y, color, shadow);
    }

    public static boolean isDoesNotFit(Component message, Number width, Number height) {
        int size = AlinLib.MINECRAFT.font.width(message) + (height.intValue() - 8) * 2;
        return size > width.intValue();
    }

    public static int DEFAULT_WIDTH() {
        return Minecraft.getInstance().getWindow().getWidth() - ((AlinLib.bariumConfig.getBoolean("CONFIG_SCREEN.SMALL_PANEL_SIZE", false) ? 130 : 190) - 20);
    }

    public static final int DEFAULT_HEIGHT = 20;

    public static net.minecraft.resources.
            //#if MC < 12111
            //$$ResourceLocation
            //#else
            Identifier
            //#endif
            getResourceLocation(String path) {
        return net.minecraft.resources.
                //#if MC < 12111
                //$$ResourceLocation
                //#else
                        Identifier
                //#endif
                .withDefaultNamespace(path);
    }

    public static net.minecraft.resources.
            //#if MC < 12111
            //$$ResourceLocation
            //#else
            Identifier
        //#endif
    getResourceLocation(String id, String path) {
        return net.minecraft.resources.
                //#if MC < 12111
                //$$ResourceLocation
                //#else
                        Identifier
                //#endif
                .fromNamespaceAndPath(id, path);
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
        AlinLib.LOG.log(String.format("[Style] Registration of %s by id %s", style.name.getString(), style.id));
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
    //
    public static int interpolate(int color1, int color2, float progress) {
        //Разделяем оба цвета на составляющие
        int a1 = (color1 & 0xff000000) >>> 24;
        int r1 = (color1 & 0x00ff0000) >>> 16;
        int g1 = (color1 & 0x0000ff00) >>> 8;
        int b1 = color1 & 0x000000ff;

        int a2 = (color2 & 0xff000000) >>> 24;
        int r2 = (color2 & 0x00ff0000) >>> 16;
        int g2 = (color2 & 0x0000ff00) >>> 8;
        int b2 = color2 & 0x000000ff;

        //И рассчитываем новые
        float progress2 = (1 - progress);
        int newA = clip((int) (a1 * progress2 + a2 * progress));
        int newR = clip((int) (r1 * progress2 + r2 * progress));
        int newG = clip((int) (g1 * progress2 + g2 * progress));
        int newB = clip((int) (b1 * progress2 + b2 * progress));

        //Собираем и возвращаем полученный цвет
        return (newA << 24) + (newR << 16) + (newG << 8) + newB;
    }

    public static int clip(int num) {
        return num <= 0 ? 0 : Math.min(num, 255);
    }
}
