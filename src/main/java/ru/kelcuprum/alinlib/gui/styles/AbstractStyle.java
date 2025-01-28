package ru.kelcuprum.alinlib.gui.styles;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public abstract class AbstractStyle {
    public String id;
    public Component name;

    public AbstractStyle(String id, Component name){
        this.name = name;
        this.id = id;
    }

    public abstract void renderBackground$widget(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused);
    public abstract void renderBackground$slider(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean active, boolean isHoveredOrFocused, double position);
    public int getTextColor(boolean active){
        return active ? 16777215 : 10526880;
    }
    public boolean textShadow(){
        return true;
    }
}
