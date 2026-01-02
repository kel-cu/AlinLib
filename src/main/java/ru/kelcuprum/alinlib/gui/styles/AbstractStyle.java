package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Colors.BLACK_ALPHA;
import static ru.kelcuprum.alinlib.gui.Colors.CPM_BLUE;
import static ru.kelcuprum.alinlib.info.Player.getY;

public abstract class AbstractStyle {
    public String id;
    public Component name;

    public AbstractStyle(String id, Component name){
        this.name = name;
        this.id = id;
    }

    // -=-=-=- Рендеры
    public abstract void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused);
    public abstract void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position);
    public void renderBackground$editbox(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused){
        renderBackground$widget(guiGraphics, x, y, width, height, active, isHoveredOrFocused);
    }
    public void renderBackground(GuiGraphics guiGraphics, int x, int y, int width, int height){
        guiGraphics.fill(x, y, width, height, BLACK_ALPHA);
    }
    public void renderTitleBackground(GuiGraphics guiGraphics, int x, int y, int width, int height){
        guiGraphics.fill(x, y, width, height, BLACK_ALPHA);
    }

    public void renderToastBackground(ToastBuilder toastBuilder, GuiGraphics guiGraphics, int x, int y, int width, int height, double timeline){
        guiGraphics.fill(x, y, width, height, BLACK_ALPHA);
    }

    public void renderBlockquoteBackground(TextBuilder builder, GuiGraphics guiGraphics, int x, int y, int width, int height, int[] colors){
        ModernStyle.renderDefaultBlockquoteBackground(builder, guiGraphics, x, y, width, height, colors);
    }
    public int[] getCheckBoxSizes(int width, int height){
        return new int[]{height-10, height-10};
    }
    public void renderCheckBox(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean value){
        int cW = getCheckBoxSizes(width, height)[0];
        int cH = getCheckBoxSizes(width, height)[1];
        //#if MC >= 12108
        guiGraphics.
                //#if MC < 12111
                //$$submitOutline
                //#else
                renderOutline
        //#endif
        (x, y, cW, cH, getCheckBoxColor(value));
        //#else
        //$$ guiGraphics.fill(x, y, x + cW, y+1, getCheckBoxColor(value));
        //$$ guiGraphics.fill(x, y+cH-1, x + cW, y+cH, getCheckBoxColor(value));
        //$$ guiGraphics.fill(x, y+1, x+1, y+cH, getCheckBoxColor(value));
        //$$ guiGraphics.fill(x+cW-1, y+1, x+cW, y+cH, getCheckBoxColor(value));
        //#endif
        if(value){
            guiGraphics.fill(x+2, y+2, x+cW-2, y+cH-2, getCheckBoxColor(value));
        }
    }
    // -=-=-=- Разноцветные хуйни
    public int getCheckBoxColor(boolean isActive){
        return isActive ? Colors.getCheckBoxColor() : 0xFFFFFFFF;
    }
    public int[] getBlockquoteColors(){
        return new int[]{AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR", CPM_BLUE).intValue(), AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR.BACKGROUND", CPM_BLUE - 0xE1000000).intValue()};
    }
    public int getHorizontalRuleColor(){
        return Colors.getHorizontalRuleColor();
    }
    public int getScrollerColor(){
        return Colors.getScrollerColor();
    }

    public int getTextColor(boolean active){
        return active ? -1 : 0xFF5F5F5F;
    }
    public int getCursorColor(){
        return getEditBoxColor(true);
    }
    public int getTextTitleColor(){
        return getTextColor(true);
    }
    public int getTextColor(TextBuilder.TYPE type){
        return type == TextBuilder.TYPE.TITLE ? getTextTitleColor() : getTextColor(true);
    }
    public int getToastTextColor(){ return -1; }
    public int getTextSliderColor(boolean active){
        return getTextColor(active);
    }
    public int getEditBoxColor(boolean active){
        return getTextColor(active);
    }
    public boolean supportWhiteIcons(){ return true; }
    public boolean textShadow(){
        return true;
    }
    public boolean sliderShadow(){
        return textShadow();
    }
    public boolean editBoxShadow(){
        return textShadow();
    }
    public boolean titleShadow(){
        return textShadow();
    }
    public boolean textShadow(TextBuilder.TYPE type){
        return true;
    }
}
