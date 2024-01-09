package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class CategoryBox extends TextBox {
    public final List<AbstractWidget> values = new ArrayList<>();
    private boolean state = true;
    public CategoryBox(int x, int y, Component label) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, label);
    }
    public CategoryBox(int x, int y, int width, int height, Component label) {
        super(x, y, width, height, label, true);
        this.setActive(true);
    }

    public void addValue(AbstractWidget widget) {
        if (widget == null)
            return;

        values.add(widget);
    }
    public void addValues(List<AbstractWidget> widgets) {
        if (widgets == null)
            return;
        values.addAll(widgets);
    }

    public List<AbstractWidget> getValues(){
        return values;
    }

    public void changeState(){
        state = !state;
        for (AbstractWidget widget : values) {
            widget.visible = state;
        }
    }

    @Override
    public void onClick(double d, double e) {
        changeState();
        super.onClick(d, e);
    }
    @Override
    public boolean keyPressed(int i, int j, int k) {
        if (this.active && this.visible) {
            if (CommonInputs.selected(i)) {
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                changeState();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        if (visible) {
            int y = getY() + (getHeight() - 8) / 2;
            Font font = Minecraft.getInstance().font;
            guiGraphics.drawString(font, state ? "▼" : "▶", getX() + (getHeight() - 8) / 2, y, -1);
            guiGraphics.drawCenteredString(font, getMessage(), getX() + getWidth() / 2, y, -1);
            int textWidth = font.width(getMessage());
            int x = (getX() + getWidth() / 2) - (textWidth/2);
            guiGraphics.fill(x, y+font.lineHeight+1, x+textWidth, y+font.lineHeight+2, 0xFFFFFFFF);
        }
    }
}
