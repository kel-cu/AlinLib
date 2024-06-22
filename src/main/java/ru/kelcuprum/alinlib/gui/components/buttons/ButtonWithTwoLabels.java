package ru.kelcuprum.alinlib.gui.components.buttons;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class ButtonWithTwoLabels extends Button {
    protected Component rightLabel;
    protected Component leftLabel;
    private OnPress onPress;

    public ButtonWithTwoLabels(Component leftLabel, Component rightLabel) {
        this(0,0, leftLabel, rightLabel);
    }
    public ButtonWithTwoLabels(int x, int y, Component leftLabel, Component rightLabel) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, leftLabel, rightLabel);
    }
    public ButtonWithTwoLabels(int x, int y, int width, int height, Component leftLabel, Component rightLabel) {
        this(x, y, width, height, GuiUtils.getSelected(), leftLabel, rightLabel);
    }
    public ButtonWithTwoLabels(int x, int y, int width, int height, AbstractStyle style, Component leftLabel, Component rightLabel) {
        this(x, y, width, height, style, leftLabel, rightLabel, null);
    }
    public ButtonWithTwoLabels(int x, int y, int width, int height, AbstractStyle style, Component leftLabel, Component rightLabel, OnPress onPress) {
        super(x, y, width, height, style, false, Component.empty(), null);
        this.rightLabel = rightLabel;
        this.leftLabel = leftLabel;
        setMessage(Component.empty().append(leftLabel).append(": ").append(rightLabel));
        this.onPress = onPress;
    }

    @Override
    public void onPress() {
        if(!active) return;
        if(this.onPress != null) this.onPress.onPress(this);
    }

    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(Component.empty().append(leftLabel).append(": ").append(rightLabel), getWidth(), getHeight())){
            setMessage(Component.empty().append(leftLabel).append(": ").append(rightLabel));
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, leftLabel, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            guiGraphics.drawString(AlinLib.MINECRAFT.font, rightLabel, getX() + getWidth()-AlinLib.MINECRAFT.font.width(rightLabel.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    public ButtonWithTwoLabels setLeftLabel(Component leftLabel){
        this.leftLabel = leftLabel;
        return this;
    }
    public ButtonWithTwoLabels setRightLabel(Component rightLabel){
        this.rightLabel = rightLabel;
        return this;
    }
    public ButtonWithTwoLabels setMessages(Component leftLabel, Component rightLabel){
        setLeftLabel(leftLabel);
        setRightLabel(rightLabel);
        return this;
    }

    //

    public ButtonWithTwoLabels setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }

    public interface OnPress {
        void onPress(ButtonWithTwoLabels volume);
    }

    //
    protected Component description;
    public ButtonWithTwoLabels setDescription(Component description){
        this.description = description;
        return this;
    }

    public Component getDescription(){
        return this.description;
    }
}
