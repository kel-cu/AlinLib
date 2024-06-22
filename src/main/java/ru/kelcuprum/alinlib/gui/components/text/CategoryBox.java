package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;

import java.util.ArrayList;
import java.util.List;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class CategoryBox extends TextBox {
    public final List<AbstractWidget> values = new ArrayList<>();
    private boolean state = true;
    private Component name;

    public CategoryBox(Component label) {
        this(0, 0, label);
    }
    public CategoryBox(int x, int y, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label);
    }
    public CategoryBox(int x, int y, int width, int height, Component label) {
        super(x, y, width, height, label, true);
        this.name = label;
        this.setActive(true);
    }

    public CategoryBox addValue(AbstractWidget widget) {
        if (widget == null)
            return this;

        values.add(widget);
        return this;
    }
    public CategoryBox addValues(List<AbstractWidget> widgets) {
        if (widgets == null)
            return this;
        values.addAll(widgets);
        return this;
    }
    public CategoryBox setName(Component name){
        this.name = name;
        return this;
    }
    public Component getName(){
        return name;
    }
    public List<AbstractWidget> getValues(){
        return values;
    }

    public CategoryBox changeState(){
        return changeState(!state);
    }

    public CategoryBox changeState(boolean state){
        this.state = state;
        for (AbstractWidget widget : values) {
            widget.visible = state;
        }
        return this;
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
                this.playDownSound(AlinLib.MINECRAFT.getSoundManager());
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
            Font font = AlinLib.MINECRAFT.font;
            if(GuiUtils.isDoesNotFit(Component.literal(state ? "▼" : "▶").append("   ").append(getName()), getWidth(), getHeight())){
                this.setMessage(Component.literal(state ? "▼" : "▶").append("   ").append(getName()));
                this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
                int textWidth = font.width(getMessage());
                int x = (getX() + getWidth() / 2) - (textWidth/2);
                guiGraphics.fill(Math.max(x, getX()), y+font.lineHeight+1, Math.max(x, getX())+Math.min(textWidth, getWidth()), y+font.lineHeight+2, 0xFFFFFFFF);
            } else {
                guiGraphics.drawString(font, state ? "▼" : "▶", getX() + (getHeight() - 8) / 2, y, -1);
                guiGraphics.drawCenteredString(font, getName(), getX() + getWidth() / 2, y, -1);
                int textWidth = font.width(getName());
                int x = (getX() + getWidth() / 2) - (textWidth/2);
                guiGraphics.fill(x, y+font.lineHeight+1, x+textWidth, y+font.lineHeight+2, 0xFFFFFFFF);
            }
        }
    }

    protected Component description;
    public CategoryBox setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
