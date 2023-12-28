package ru.kelcuprum.alinlib.gui.components.text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;

public class TextBox extends AbstractWidget {
    private final boolean isCentred;
    private Component tooltipMessage;
    private final OnPress onPress;
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter){
        this(x, y, width, height, label, isCenter, null);
    }
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter, OnPress onPress){
        super(x, y, width, height, label);
        this.isCentred = isCenter;
        this.onPress = onPress;
        this.setActive(this.onPress != null);
    }

    public void setTooltip(Component tooltipMessage){
        this.tooltipMessage = tooltipMessage;
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
        this.onPress.onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        if(isHovered() && tooltipMessage != null){
            guiGraphics.renderTooltip(Minecraft.getInstance().font, tooltipMessage, i, j);
        }
        if(isDoesNotFit()) this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        else if(this.isCentred) guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
    }
    private boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
    @Override
    public void onClick(double d, double e) {
        if(onPress != null) this.onPress();
    }
    @Override
    public boolean keyPressed(int i, int j, int k) {
        if (this.active && this.visible) {
            if (CommonInputs.selected(i)) {
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                this.onPress();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(TextBox button);
    }
}
