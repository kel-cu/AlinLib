package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import static ru.kelcuprum.alinlib.gui.Colors.BLACK_ALPHA;

public abstract class AbstractStyle {
    public String id;
    public Component name;

    public AbstractStyle(String id, Component name){
        this.name = name;
        this.id = id;
    }

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
    public int getTextColor(boolean active){
        return active ? -1 : 0xFF5F5F5F;
    }
    public boolean textShadow(){
        return true;
    }
}
