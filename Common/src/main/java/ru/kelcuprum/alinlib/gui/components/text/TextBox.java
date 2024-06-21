package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.Description;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class TextBox extends AbstractWidget implements Description {
    private final boolean isCentred;
    private final OnPress onPress;

    public TextBox(Component label){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, true, null);
    }
    public TextBox(Component label, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, true, onPress);
    }
    ///
    public TextBox(Component label, boolean isCenter){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, null);
    }
    public TextBox(Component label, boolean isCenter, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, onPress);
    }
    ///
    public TextBox(int x, int y, Component label, boolean isCenter){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, null);
    }
    public TextBox(int x, int y, Component label, boolean isCenter, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, onPress);
    }
    ///
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter){
        this(x, y, width, height, label, isCenter, null);
    }
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter, OnPress onPress){
        super(x, y, width, height, label);
        this.isCentred = isCenter;
        this.onPress = onPress;
        this.setActive(this.onPress != null);
    }

    public void setActive(boolean active){
        this.active = active;
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }
    @Override
    public void setY(int y) {
        super.setY(y);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }
    public void onPress() {
        if(onPress != null) this.onPress.onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        if(isDoesNotFit()) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        else if(this.isCentred) guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        else guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
    }
    private boolean isDoesNotFit(){
        int size = AlinLib.MINECRAFT.font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
    @Override
    public void onClick(double d, double e) {
        this.onPress();
    }
    @Override
    public boolean keyPressed(int i, int j, int k) {
        if (this.active && this.visible) {
            if (CommonInputs.selected(i)) {
                this.playDownSound(AlinLib.MINECRAFT.getSoundManager());
                this.onPress();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public interface OnPress {
        void onPress(TextBox button);
    }

    protected Component description;
    public TextBox setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
